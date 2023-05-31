package game.PlayerAttributes;

import java.util.HashMap;

/**
 * The InventoryManager class manages the inventory system of the game.
 * It keeps track of the items and their counts in the inventory.
 */
public class InventoryManager {

    /**
     * Enum representing the available items in the inventory.
     */
    public enum Item { kSprite, kThwacker, kGear, kBolt }

    private static HashMap<Item, Integer> inventoryCounter = new HashMap<Item, Integer>();

    static {
        for (Item item : Item.values()) {
            inventoryCounter.put(item, 0);
        }
    }

    /**
     * Adds a specified amount of an item to the inventory.
     *
     * @param pItem   the item to add
     * @param amount  the amount of the item to add
     */
    public static void addItem(Item pItem, int amount) {
        inventoryCounter.put(pItem, inventoryCounter.get(pItem) + amount);
    }

    /**
     * Removes a specified amount of an item from the inventory.
     *
     * @param pItem   the item to remove
     * @param amount  the amount of the item to remove
     */
    public static void removeItem(Item pItem, int amount) {
        inventoryCounter.put(pItem, inventoryCounter.get(pItem) - amount);
    }

    /**
     * Returns the current count of a specified item in the inventory.
     *
     * @param pItem  the item to check the count for
     * @return the count of the item in the inventory
     */
    public static int getItemCount(Item pItem) {
        return inventoryCounter.get(pItem);
    }

    /**
     * Sets the count of a specified item in the inventory.
     *
     * @param pItem   the item to set the count for
     * @param pCount  the count to set for the item
     */
    public static void setItemCount(Item pItem, int pCount) {
        inventoryCounter.put(pItem, pCount);
    }
}
