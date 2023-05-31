package game.titleScreen;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GameStates;
import game.interfaces.BasicSprite;

/**
 * The TitleScreen class represents the title screen of the game.
 * It implements the BasicSprite interface.
 */
public class TitleScreen implements BasicSprite {

    private BufferedImage background;
    private Point pos;

    /**
     * Constructs a TitleScreen object.
     * It loads the background image and initializes the position.
     */
    public TitleScreen() {
        loadImage();
        pos = new Point(0, 0);
    }

    /**
     * Loads the background image from the file.
     * If there is an error loading the image, an exception is caught and an error message is printed.
     */
    private void loadImage() {
        try {
            background = ImageIO.read(new File("src/main/resources/images/backgrounds/titleScreen.png"));
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    /**
     * Draws the title screen background on the graphics context.
     *
     * @param g        the Graphics object to draw on
     * @param observer the ImageObserver object
     */
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(background, pos.x, pos.y, observer);
    }

    /**
     * Handles the key pressed events for the title screen.
     * It changes the game state to scrolling text when the space key is pressed.
     *
     * @param e the KeyEvent object representing the key press event
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            GameStates.setState(GameStates.States.SCROLLING_TEXT);
        }
    }

    /**
     * Updates the title screen.
     * There is no special behavior for the title screen.
     */
    @Override
    public void tick() {
        // No special behavior for the title screen
    }

    /**
     * Performs cleanup tasks when the title screen is deleted.
     * It sets the background image to null.
     */
    @Override
    public void onDelete() {
        background = null;
    }
}
