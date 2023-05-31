package game.shop;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;

import game.Game;
import game.PlayerAttributes.InventoryManager;
import game.generalSprites.BasicSpriteWithImage;

/**
 * The SpriteDealerItem class represents an item being sold by the sprite dealer in the shop.
 * It extends the BasicSpriteWithImage class.
 */
public class SpriteDealerItem extends BasicSpriteWithImage {

    private float mBrightnessFactor;
    private SpriteDealer.ItemSelected itemType;
    private InventoryManager.Item hackyInventoryItemType;

    /**
     * Constructs a SpriteDealerItem object with the specified item type, image file name, and hitbox.
     *
     * @param pItemType   the item type of the sprite dealer item
     * @param pFileName   the image file name of the sprite dealer item
     * @param pHitbox     the hitbox of the sprite dealer item
     */
    public SpriteDealerItem(SpriteDealer.ItemSelected pItemType, String pFileName, Rectangle pHitbox) {
        super(pFileName, pHitbox);
        itemType = pItemType;
        switch (itemType) {
            case THWACKER:
                hackyInventoryItemType = InventoryManager.Item.kThwacker;
                break;
            case GEAR:
                hackyInventoryItemType = InventoryManager.Item.kGear;
                break;
            case BOLT:
                hackyInventoryItemType = InventoryManager.Item.kBolt;
                break;
        }
    }

    /**
     * Updates the sprite dealer item's state.
     * It adjusts the brightness factor of the image based on the selected item.
     */
    @Override
    public void tick() {
        SpriteDealer dealer = Game.getBoard().getSpriteDealer();
        if (dealer.getItemSelected() == itemType) {
            mBrightnessFactor = (float) Math.sin((System.currentTimeMillis() - dealer.getTimeStartedSelected() + (300 * Math.PI)) / 300.0) + 1.2f;
        } else {
            mBrightnessFactor = 1;
        }
    }

    /**
     * Draws the sprite dealer item on the graphics context.
     * It applies the brightness adjustment to the image before drawing.
     *
     * @param g        the Graphics object to draw on
     * @param observer the ImageObserver object
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        RescaleOp rescaleOp = new RescaleOp(mBrightnessFactor, 0, null);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(img, rescaleOp, mHitbox.x, mHitbox.y);
    }

    /**
     * Handles the key pressed events for the sprite dealer item.
     * It allows the player to purchase the item if it is currently selected and the player has enough inventory.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && Game.getBoard().getSpriteDealer().getItemSelected() == itemType) {
            if (InventoryManager.getItemCount(InventoryManager.Item.kSprite) <= 1) return;
            InventoryManager.removeItem(InventoryManager.Item.kSprite, 2);
            InventoryManager.addItem(hackyInventoryItemType, 1);
        }
    }
}
