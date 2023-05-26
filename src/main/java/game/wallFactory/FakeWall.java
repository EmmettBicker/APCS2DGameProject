package game.wallFactory;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.Constants;
import game.Game;
import game.GameStates.GameplayStates;
import game.enemies.EnemyDropsFactory;
import game.generalSprites.GeneralImage;

public class FakeWall extends GeneralImage
{
    private boolean spriteRecieved = false;
    public FakeWall(Rectangle pHitbox) {
        super("special/fakeWall.png", pHitbox);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && !spriteRecieved && Game.getPlayerHitbox().intersects(mHitbox))
        {
            spriteRecieved = true;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    EnemyDropsFactory.addDrop(new Point(Constants.CANVAS_WIDTH / 2 + 100 + 25 * i, Constants.CANVAS_HEIGHT / 2-200 - j * 25 ), GameplayStates.ROOM_3);
                }
            }
        }
    }
    
}