package game.generalSprites;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.interfaces.BasicRoomSprite;
import game.Game;
import game.GameStates;
import game.utils.GeneralUtils;

/**
 * The GeneralDoor class represents a door sprite in a room.
 * It implements the BasicRoomSprite interface and provides functionality for doors.
 */
public class GeneralDoor implements BasicRoomSprite {

    // image that represents the player's position on the board
    protected BufferedImage img;
    protected GameStates.GameplayStates mDestination;
    protected Point mPlayerEndPos;
    protected Rectangle mHitbox;

    /**
     * Constructs a GeneralDoor object with the specified destination, player end position, and hitbox.
     * 
     * @param pDestination    the destination gameplay state of the door
     * @param pPlayerEndPos   the position where the player will end up after going through the door
     * @param pHitbox         the hitbox of the door
     */
    public GeneralDoor(GameStates.GameplayStates pDestination, Point pPlayerEndPos, Rectangle pHitbox) {
        // load the assets
        loadImage();
        mDestination = pDestination;
        mPlayerEndPos = GeneralUtils.centerDoorEndDestination(pPlayerEndPos);
        mHitbox = pHitbox;
    }

    /**
     * Loads the door image from the file.
     */
    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            img = ImageIO.read(new File("src/main/resources/images/generalSprites/generalDoor.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }
    
    /**
     * Sets the image of the door.
     * 
     * @param pImage  the new image for the door
     */
    public void setImage(BufferedImage pImage) {
        img = pImage;
    }

    /**
     * Draws the door on the graphics context.
     * 
     * @param g         the graphics context
     * @param observer  the image observer
     */
    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        g.drawImage(
                img,
                mHitbox.x,
                mHitbox.y,
                mHitbox.width,
                mHitbox.height,
                observer);

    }

    /**
     * Handles the key press event for the door.
     * 
     * @param e  the key event
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && Game.getPlayerHitbox().intersects(mHitbox)
                && !Game.getHasChangedRoomAlready()) {
            Game.setHasChangedRoomAlready(true);

            GameStates.setGameplayState(mDestination);
            Game.setPlayerPosition(mPlayerEndPos);
        }

    }

    /**
     * Performs the tick update for the door.
     */
    @Override
    public void tick() {
        // no special behavior
    }

    /**
     * Performs the necessary actions when the door is deleted.
     */
    @Override
    public void onDelete() {

    }
}
