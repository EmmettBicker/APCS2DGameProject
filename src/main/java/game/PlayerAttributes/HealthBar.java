package game.PlayerAttributes;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import game.Constants;
import game.Player;

import game.interfaces.BasicSprite;

public class HealthBar implements BasicSprite{

    public HealthBar() {}

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        
        int width = 200;
        int height = 40;
    
        int x = Constants.CANVAS_WIDTH - 10 - width;
        int y = 10;
            
        int maxHealth = 10;
        int currentHealth = 7;
            
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
            
        // draw health segments
        int segmentWidth = (int)Math.floor((width - 2 * Constants.HEALTH_BAR_PADDING) / maxHealth);
        int gapWidth = Constants.HEALTH_BAR_GAP_WIDTH;
        int remainingHealth = currentHealth;
        for (int i = 0; i < maxHealth; i++) {
            if (remainingHealth > 0) {
                g.setColor(Color.GREEN);
                if (remainingHealth >= segmentWidth) {
                    g.fillRect(x + Constants.HEALTH_BAR_PADDING + i * (segmentWidth + gapWidth), y + Constants.HEALTH_BAR_PADDING, segmentWidth, height - 2 * Constants.HEALTH_BAR_PADDING);
                } else {
                    g.fillRect(x + Constants.HEALTH_BAR_PADDING + i * (segmentWidth + gapWidth), y + Constants.HEALTH_BAR_PADDING, remainingHealth, height - 2 * Constants.HEALTH_BAR_PADDING);
                }
                remainingHealth -= segmentWidth;
            } else {
                g.setColor(Color.RED);
                g.fillRect(x + Constants.HEALTH_BAR_PADDING + i * (segmentWidth + gapWidth), y + Constants.HEALTH_BAR_PADDING, segmentWidth, height - 2 * Constants.HEALTH_BAR_PADDING);
            }
        }
    
        // draw current and max health text
        String healthText = currentHealth + " / " + maxHealth;
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(healthText);
        int textHeight = fm.getHeight();
        g.setColor(Color.BLACK);
        g.drawString(healthText, x - textWidth - Constants.HEALTH_BAR_PADDING, y + (height + textHeight) / 2);
    }
    


    @Override
    public void keyPressed(KeyEvent e) {}
    

    @Override
    public void tick() {
        
    }
    
    @Override
    public void onDelete() {}
    
    
}
