package game.shop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import game.PlayerAttributes.InventoryManager;
import game.generalSprites.BasicSpriteWithImage;

/**
 * The SpriteCostSelector class represents a sprite cost selector in the shop.
 * It displays the number of cans of Sprite the player has in their inventory.
 * It extends the BasicSpriteWithImage class.
 */
public class SpriteCostSelector extends BasicSpriteWithImage {

    /**
     * Constructs a SpriteCostSelector object.
     */
    public SpriteCostSelector() {
        super(null, null);
    }
    
    /**
     * Draws the sprite cost selector on the screen.
     * It displays the number of cans of Sprite in the player's inventory.
     *
     * @param g        the Graphics object used for drawing
     * @param observer the ImageObserver object
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.setFont(new Font("Arial", Font.PLAIN, 20)); 
        g.setColor(Color.GREEN);
        g.drawString("Press esc to exit", 50, 50);
        g.setFont(new Font("Arial", Font.PLAIN, 40)); 
        g.drawString(InventoryManager.getItemCount(InventoryManager.Item.kSprite) + " cans of Sprite", 500, 50);
    }
}
