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
import game.interfaces.BasicSprite;
import game.GameStates;


/**
 * The BasicSpriteWithImage class represents a basic sprite with an image.
 * It implements the BasicSprite interface and provides common functionality for sprites with images.
 */
public class BasicSpriteWithImage implements BasicSprite {

    // image that represents the player's position on the board
    protected BufferedImage img;
    protected GameStates.GameplayStates mDestination;
    protected Point mPlayerEndPos;
    protected Rectangle mHitbox;
    protected String mFileName;

    /**
     * Constructs a BasicSpriteWithImage object with the specified image file name and hitbox.
     * 
     * @param fileName  the file name of the image
     * @param pHitbox   the hitbox of the sprite
     */
    public BasicSpriteWithImage(String fileName, Rectangle pHitbox) {
        // load the assets
        mFileName = fileName;
        if (fileName != null) loadImage();
        mHitbox = pHitbox;
    }

    /**
     * Loads the image from the file.
     */
    private void loadImage() {
        try {
            // project folder, otherwise you need to provide the file path.
            img = ImageIO.read(new File("src/main/resources/images/" + mFileName));

        } catch (IOException exc) {
            System.out.println("Error opening " + mFileName + " file: " + exc.getMessage());
        }
    }
    

    /**
     * Draws the sprite with the image on the graphics context.
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
     * Handles the key press event.
     * 
     * @param e  the key event
     */
    public void keyPressed(KeyEvent e) {
    
    }

    /**
     * Performs the tick update for the sprite.
     */
    @Override
    public void tick() {
        // no special behavior
    }

    /**
     * Performs the necessary actions when the sprite is deleted.
     */
    @Override
    public void onDelete() {

    }
}
