package game.shop;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.GameStates;
import game.generalSprites.BasicSpriteWithImage;

public class SpriteDealer extends BasicSpriteWithImage{

    public enum ItemSelected {BOLT, GEAR, THWACKER}
    private ItemSelected[] itemOrder;
    private ItemSelected currentItem;
    private int currentItemIndex;
    private long timeStartedSelected;

    public SpriteDealer(String fileName, Rectangle pHitbox) {
        super(fileName, pHitbox);
        itemOrder = new ItemSelected[] {ItemSelected.BOLT, ItemSelected.GEAR, ItemSelected.THWACKER};
        currentItemIndex = 0;
        currentItem = itemOrder[currentItemIndex];
        timeStartedSelected = System.currentTimeMillis();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void tick()
    {
        
        mHitbox.y = (int) (Math.sin(System.currentTimeMillis()/600)*5);
       
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
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

        if (key == KeyEvent.VK_ESCAPE)
        {
            GameStates.setState(GameStates.States.GAMEPLAY);
        }
    }

    public ItemSelected getItemSelected()
    {
        return currentItem;
    }

    public long getTimeStartedSelected()
    {
        return timeStartedSelected;
    }

}
