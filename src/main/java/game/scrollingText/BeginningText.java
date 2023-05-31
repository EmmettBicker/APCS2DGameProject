package game.scrollingText;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.interfaces.BasicSprite;

/**
 * The BeginningText class represents the scrolling text at the beginning of the game.
 * It implements the BasicSprite interface.
 */
public class BeginningText implements BasicSprite {

    private BufferedImage text;
    private Point pos;

    /**
     * Constructs a BeginningText object.
     */
    public BeginningText() {
        loadImage();
        pos = new Point(0, 0);
    }

    private void loadImage() {
        try {
            text = ImageIO.read(new File("src/main/resources/images/scrollingText/beginningScollingText.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    /**
     * Draws the beginning text on the given Graphics object.
     *
     * @param g the Graphics object to draw on
     * @param observer the ImageObserver object
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                text,
                pos.x,
                pos.y,
                observer);
    }

    /**
     * Handles the key pressed events for the beginning text.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void tick() {

    }

    /**
     * Performs any necessary cleanup when the beginning text is deleted.
     */
    @Override
    public void onDelete() {
        text = null;
    }
}
