package game.interfaces;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * The BasicRoomSprite interface represents a basic sprite in a room of the game.
 * It defines methods for drawing, handling key events, updating, and deletion of the sprite.
 */
public interface BasicRoomSprite {
    
    /**
     * Draws the sprite on the graphics context.
     *
     * @param g         the graphics context
     * @param observer  the image observer
     */
    public void draw(Graphics g, ImageObserver observer);

    /**
     * Handles the key press event for the sprite.
     *
     * @param e  the key event
     */
    public void keyPressed(KeyEvent e);

    /**
     * Updates the state of the sprite.
     */
    public void tick();

    /**
     * Performs necessary actions when the sprite is deleted.
     */
    public void onDelete();
}
