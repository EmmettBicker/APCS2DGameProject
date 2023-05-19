package game.enemies;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyHealthBar {
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mWidth;
    private int mHeight;
    private Color mBackgroundColor;
    private Color mForegroundColor;

    public EnemyHealthBar(int maxHealth, int currentHealth, int width, int height, Color backgroundColor, Color foregroundColor) {
        mMaxHealth = maxHealth;
        mCurrentHealth = currentHealth;
        mWidth = width;
        mHeight = height;
        mBackgroundColor = backgroundColor;
        mForegroundColor = foregroundColor;
    }

    public void setCurrentHealth(int currentHealth) {
        mCurrentHealth = currentHealth;
    }

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
