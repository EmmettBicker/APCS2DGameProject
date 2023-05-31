package game.shop;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.GameStates;
import game.generalSprites.BasicSpriteWithImage;

/**
 * The SpriteDealer class represents a sprite dealer in the shop.
 * It allows the player to select different items for purchase.
 * It extends the BasicSpriteWithImage class.
 */
public class SpriteDealer extends BasicSpriteWithImage {

    public enum ItemSelected { BOLT, GEAR, THWACKER }

    private ItemSelected[] itemOrder;
    private ItemSelected currentItem;
    private int currentItemIndex;
    private long timeStartedSelected;

    /**
     * Constructs a SpriteDealer object with the specified image file name and hitbox.
     *
     * @param fileName the image file name of the sprite dealer
     * @param pHitbox  the hitbox of the sprite dealer
     */
    public SpriteDealer(String fileName, Rectangle pHitbox) {
        super(fileName, pHitbox);
        itemOrder = new ItemSelected[] { ItemSelected.BOLT, ItemSelected.GEAR, ItemSelected.THWACKER };
        currentItemIndex = 0;
        currentItem = itemOrder[currentItemIndex];
        timeStartedSelected = System.currentTimeMillis();
    }
    
    /**
     * Updates the sprite dealer's state.
     * It animates the vertical movement of the hitbox.
     */
    @Override
    public void tick() {
        mHitbox.y = (int) (Math.sin(System.currentTimeMillis() / 600) * 5);
    }

    /**
     * Handles the key pressed events for the sprite dealer.
     * It allows the player to navigate through the available items and exit the shop.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            currentItemIndex = (currentItemIndex + 1) % 3;
            currentItem = itemOrder[currentItemIndex];
            timeStartedSelected = System.currentTimeMillis();
        }
        if (key == KeyEvent.VK_DOWN) {
            currentItemIndex = (currentItemIndex - 1) % 3;
            if (currentItemIndex == -1) currentItemIndex = 2;
            currentItem = itemOrder[currentItemIndex];
            timeStartedSelected = System.currentTimeMillis();
        }

        if (key == KeyEvent.VK_ESCAPE) {
            GameStates.setState(GameStates.States.GAMEPLAY);
        }
    }

    /**
     * Gets the currently selected item by the player.
     *
     * @return the currently selected item
     */
    public ItemSelected getItemSelected() {
        return currentItem;
    }

    /**
     * Gets the time when the current item was selected.
     *
     * @return the time when the current item was selected
     */
    public long getTimeStartedSelected() {
        return timeStartedSelected;
    }
}
