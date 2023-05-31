package game.enemies;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The EnemyHealthBar class represents a health bar for an enemy in the game.
 * It provides methods to set the current health and draw the health bar on the screen.
 */
public class EnemyHealthBar {
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mWidth;
    private int mHeight;
    private Color mBackgroundColor;
    private Color mForegroundColor;

    /**
     * Constructs an EnemyHealthBar with the specified parameters.
     * 
     * @param maxHealth  the maximum health value
     * @param currentHealth  the current health value
     * @param width  the width of the health bar
     * @param height  the height of the health bar
     * @param backgroundColor  the color of the background of the health bar
     * @param foregroundColor  the color of the foreground of the health bar
     */
    public EnemyHealthBar(int maxHealth, int currentHealth, int width, int height, Color backgroundColor, Color foregroundColor) {
        mMaxHealth = maxHealth;
        mCurrentHealth = currentHealth;
        mWidth = width;
        mHeight = height;
        mBackgroundColor = backgroundColor;
        mForegroundColor = foregroundColor;
    }

    /**
     * Sets the current health value of the enemy.
     * 
     * @param currentHealth  the current health value
     */
    public void setCurrentHealth(int currentHealth) {
        mCurrentHealth = currentHealth;
    }

    /**
     * Draws the health bar on the screen at the specified position.
     * 
     * @param g  the Graphics object to draw on
     * @param x  the x-coordinate of the top-left corner of the health bar
     * @param y  the y-coordinate of the top-left corner of the health bar
     */
    public void draw(Graphics g, int x, int y) {
        // Draw the background of the health bar
        g.setColor(mBackgroundColor);
        g.fillRect(x, y, mWidth, mHeight);

        // Calculate the width of the foreground based on the current health
        int foregroundWidth = (int) (((double) mCurrentHealth / mMaxHealth) * mWidth);

        // Draw the foreground of the health bar
        g.setColor(mForegroundColor);
        g.fillRect(x, y, foregroundWidth, mHeight);
    }
}
