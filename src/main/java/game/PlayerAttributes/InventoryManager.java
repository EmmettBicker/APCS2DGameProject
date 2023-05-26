package game.PlayerAttributes;

import java.util.HashMap;

public class InventoryManager{

    public enum Item {kSprite, kThwacker, kGear, kBolt};

    private static HashMap<Item, Integer> inventoryCounter = new HashMap<Item, Integer>();;

    static
    {
        for (Item item : Item.values())
        {
            inventoryCounter.put(item, 0);
        }
    }
    
    public static void addItem(Item pItem, int amount)
    {
        inventoryCounter.put(pItem, inventoryCounter.get(pItem)+amount);
    }

    public static void removeItem(Item pItem, int amount)
    {
        inventoryCounter.put(pItem, inventoryCounter.get(pItem)-amount);
    }

    public static int getItemCount(Item pItem)
    {
        return inventoryCounter.get(pItem);
    }

    public static void setItemCount(Item pItem, int pCount)
    {
        inventoryCounter.put(pItem, pCount);
    }
    
}