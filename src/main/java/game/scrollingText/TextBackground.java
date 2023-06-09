package game.scrollingText;

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
 * The TextBackground class represents the background image for the scrolling text.
 * It implements the BasicSprite interface.
 */
public class TextBackground implements BasicSprite {

    private BufferedImage background;
    private Point pos;

    /**
     * Constructs a TextBackground object.
     */
    public TextBackground() {
        loadImage();
        pos = new Point(0, 0);
    }

    /**
     * Loads the background image from a file.
     */
    private void loadImage() {
        try {
            background = ImageIO.read(new File("src/main/resources/images/scrollingText/scrollingTextSpaceBackground.jpg"));
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    /**
     * Draws the text background on the given Graphics object.
     *
     * @param g        the Graphics object to draw on
     * @param observer the ImageObserver object
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(background, pos.x, pos.y, observer);
    }

    /**
     * Handles the key pressed events for the text background.
     * If the space key is pressed, it sets the game state to GAMEPLAY and the gameplay state to ROOM_1.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            GameStates.setState(GameStates.States.GAMEPLAY);
            GameStates.setGameplayState(GameStates.GameplayStates.ROOM_1);
        }
    }

    /**
     * Updates the text background.
     */
    @Override
    public void tick() {
        // no special behavior
    }

    /**
     * Performs any necessary cleanup when the text background is deleted.
     */
    @Override
    public void onDelete() {
        background = null;
    }
}
