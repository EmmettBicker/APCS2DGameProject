package game.PlayerAttributes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import game.Constants;
import game.Game;
import game.interfaces.BasicSprite;

/**
 * The HealthBar class represents the health bar of the player in the game.
 * It implements the BasicSprite interface.
 */
public class HealthBar implements BasicSprite {

    /**
     * Constructs a HealthBar object.
     */
    public HealthBar() {
    }

    /**
     * Draws the health bar on the screen.
     *
     * @param g        the Graphics object to draw on
     * @param observer the ImageObserver to observe the drawing process
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {

        int width = 200;
        int height = 40;

        int x = Constants.CANVAS_WIDTH - 10 - width;
        int y = 10;

        int maxHealth = Game.getPlayer().getMaxHealth();
        int currentHealth = Game.getPlayer().getCurrentHealth();

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        int segmentWidth = width / maxHealth;

        // width of the separator
        int inbetweenWidth = 5;
        // surrounding rectangle
        int padX = 5, padY = 5;
        g.setColor(Color.BLACK);
        g.fillRect(x - padX, y - padY, width + (2 * padX) - inbetweenWidth + 1, height + (2 * padY));

        for (int i = 0; i < maxHealth; i++) {
            // draw as many green health segments as the current health
            if (i < currentHealth) {
                g.setColor(Color.GREEN);
                g.fillRect(x + i * segmentWidth, y, segmentWidth, height);

            }
            // draw the rest red
            else {
                g.setColor(Color.RED);
                g.fillRect(x + i * segmentWidth, y, segmentWidth, height);

            }
            // after drawing every segment draw this black line at the end of it (but not the final segment)
            g.setColor(Color.BLACK);
            g.fillRect(x + (i + 1) * segmentWidth - inbetweenWidth, y, inbetweenWidth, height);
        }
    }

    /**
     * Handles the key events for the health bar.
     * This method is currently empty and does not handle any key events.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press if needed
    }

    /**
     * Updates the health bar logic.
     * This method is currently empty and does not perform any updates.
     */
    @Override
    public void tick() {
        // Update logic if needed
    }

    /**
     * Performs cleanup operations when the health bar is deleted.
     * This method is currently empty and does not perform any cleanup operations.
     */
    @Override
    public void onDelete() {
        // Handle deletion if needed
    }
}
