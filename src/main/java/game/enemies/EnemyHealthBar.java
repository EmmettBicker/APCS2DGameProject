package game.enemies;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyHealthBar {
    private int maxHealth;
    private int currentHealth;
    private int width;
    private int height;
    private Color backgroundColor;
    private Color foregroundColor;

    public EnemyHealthBar(int maxHealth, int currentHealth, int width, int height, Color backgroundColor, Color foregroundColor) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void draw(Graphics g, int x, int y) {
        // Draw the background of the health bar
        g.setColor(backgroundColor);
        g.fillRect(x, y, width, height);

        // Calculate the width of the foreground based on the current health
        int foregroundWidth = (int) (((double) currentHealth / maxHealth) * width);

        // Draw the foreground of the health bar
        g.setColor(foregroundColor);
        g.fillRect(x, y, foregroundWidth, height);
    }
}
