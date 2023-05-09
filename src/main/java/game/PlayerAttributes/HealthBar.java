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
            

      
        int segmentWidth = width / maxHealth; 
        int remainingHealth = currentHealth; 

        // width of the seperator
        int inbetweenWidth = 5;
        // surrounding rectangle
        int padX = 5, padY = 5;
        g.setColor(Color.BLACK);
        g.fillRect(x-padX, y-padY, width+(2*padX) - inbetweenWidth+1, height+(2*padY));
        
        
   
        for (int i = 0; i < maxHealth; i++) 
        {
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
            g.fillRect(x + (i+1) * segmentWidth - inbetweenWidth, y, inbetweenWidth, height); 
            
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
