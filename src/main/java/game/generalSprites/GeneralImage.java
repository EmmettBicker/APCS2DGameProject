package game.generalSprites;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.interfaces.BasicRoomSprite;


/**
 * The GeneralImage class represents a general image sprite in a room.
 * It implements the BasicRoomSprite interface and provides functionality for displaying images.
 */
public class GeneralImage implements BasicRoomSprite {

    // image that represents the player's position on the board
    private BufferedImage img;
    protected Rectangle mHitbox;
    private String filename;

    /**
     * Constructs a GeneralImage object with the specified filename and hitbox.
     * 
     * @param pFilename  the filename of the image
     * @param pHitbox    the hitbox of the image
     */
    public GeneralImage(String pFilename, Rectangle pHitbox) {
        // load the assets
        filename = pFilename;
        loadImage();
        mHitbox = pHitbox;
    }

    /**
     * Loads the image from the file.
     */
    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            img = ImageIO.read(new File("src/main/resources/images/" + filename));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    /**
     * Draws the image on the graphics context.
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
     * Handles the key press event for the image.
     * 
     * @param e  the key event
     */
    public void keyPressed(KeyEvent e) {
        // no special behavior
    }

    /**
     * Performs the tick update for the image.
     */
    @Override
    public void tick() {
        // no special behavior
    }

    /**
     * Performs the necessary actions when the image is deleted.
     */
    @Override
    public void onDelete() {
        // no special behavior
    }
}