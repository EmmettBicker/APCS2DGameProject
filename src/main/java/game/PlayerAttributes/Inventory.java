package game.PlayerAttributes;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import game.npcs.TextBox;
import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.Rectangle;

import game.Game;
import game.Player.WeaponOrientationStates;
import game.interfaces.BasicSprite;
import game.enemies.*;

public class Inventory implements BasicSprite{
    private BufferedImage inventoryImage;
    private Rectangle inventoryLocation;
    
    private boolean mIsScreenOpen;
    
    public Inventory() {
        loadImage();
        inventoryLocation = new Rectangle(25,25,100,100);
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
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_I) {
            mIsScreenOpen = Game.getInventoryScreen().getTextState() != InventoryScreen.TextState.INVISIBLE;
            boolean isTalking = Game.getTextBox().getTextState() != TextBox.TextState.INVISIBLE;
            if (!mIsScreenOpen && !isTalking) {
               
                Game.getInventoryScreen().setState(InventoryScreen.TextState.ENTERING);
         

                mIsScreenOpen = true;
            }

        }
        
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
