package game.PlayerAttributes;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.Rectangle;

import game.Player.WeaponOrientationStates;
import game.interfaces.BasicSprite;
import game.enemies.*;

public class Inventory implements BasicSprite{
    private BufferedImage inventoryImage;
    private Rectangle inventoryLocation;
    
    
    
    public Inventory() {
        loadImage();
        inventoryLocation = new Rectangle(100,100,100,100);
    }
    
    private void loadImage() {
        try {
            inventoryImage = ImageIO.read(new File("src/main/resources/images/speedracerbag.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file bag: " + exc.getMessage());
        }
    }
    
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
            inventoryImage,
            inventoryLocation.x,
            inventoryLocation.y,
            inventoryLocation.width,
            inventoryLocation.height,
            observer);
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onDelete() {
        // TODO Auto-generated method stub
        
    }
    
    
    
    
}
