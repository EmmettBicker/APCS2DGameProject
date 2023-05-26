package game.shop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import game.PlayerAttributes.InventoryManager;
import game.generalSprites.BasicSpriteWithImage;

public class SpriteCostSelector extends BasicSpriteWithImage{

    public SpriteCostSelector() {
        super(null, null);
        
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public void draw(Graphics g, ImageObserver observer) {
       
        g.setFont(new Font("Arial", Font.PLAIN, 20)); 
        g.setColor(Color.GREEN);
        g.drawString("Press esc to exit", 50,50);
        g.setFont(new Font("Arial", Font.PLAIN, 40)); 
        g.drawString(InventoryManager.getItemCount(InventoryManager.Item.kSprite) + " cans of Sprite", 500,50);
    }
    

}
