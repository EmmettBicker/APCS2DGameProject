package game;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.interfaces.BasicRoomSprite;

/**
 * A class representing a generic background sprite for a room.
 */
public class GenericBackground implements BasicRoomSprite {

    // image that represents the player's position on the board
    private BufferedImage background;
    private Point pos;
    private String mBackgroundName;

    /**
     * Constructs a new GenericBackground object with the specified background name.
     *
     * @param backgroundName the name of the background image file
     */
    public GenericBackground(String backgroundName) {
        // load the assets
        mBackgroundName = "src/main/resources/images/backgrounds/" + backgroundName + ".png";
        loadImage(mBackgroundName);
        pos = new Point(0, 0);
    }

    private void loadImage(String path) {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            background = ImageIO.read(new File(path));
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    /**
     * Draws the background on the graphics context.
     *
     * @param g        the graphics context to draw on
     * @param observer the image observer
     */
    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        g.drawImage(background, pos.x, pos.y, observer);
    }

    /**
     * Handles the key pressed event.
     *
     * @param e the key event
     */
    public void keyPressed(KeyEvent e) {
        // no key pressed behavior
    }

    @Override
    public void tick() {
        // no special behavior
    }

    @Override
    public void onDelete() {
        // no delete behavior
    }
}
