package game.titleScreen;

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
 * The SpaceText class represents the scrolling text on the title screen.
 * It implements the BasicSprite interface.
 */
public class SpaceText implements BasicSprite {

    private BufferedImage text;
    private Point pos;

    /**
     * Constructs a SpaceText object.
     * It loads the text image and initializes the position.
     */
    public SpaceText() {
        loadImage();
        pos = new Point(0, 0);
    }

    /**
     * Loads the text image from the file.
     * If there is an error loading the image, an exception is caught and an error message is printed.
     */
    private void loadImage() {
        try {
            text = ImageIO.read(new File("src/main/resources/images/text/spaceText.png"));
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    /**
     * Draws the text on the graphics context.
     *
     * @param g        the Graphics object to draw on
     * @param observer the ImageObserver object
     */
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(text, pos.x, pos.y, observer);
    }

    /**
     * Handles the key pressed events for the space text.
     * This method is not used for the space text.
     *
     * @param e the KeyEvent object representing the key press event
     */
    public void keyPressed(KeyEvent e) {
        // Not used for space text
    }

    /**
     * Updates the position of the space text.
     * It oscillates the Y position based on the current time.
     */
    @Override
    public void tick() {
        pos.setLocation(0, (int) (Math.sin(System.currentTimeMillis() / 400.0) * 10));
    }

    /**
     * Performs cleanup tasks when the space text is deleted.
     * It sets the text image to null.
     */
    @Override
    public void onDelete() {
        text = null;
    }

}
