package game.wallFactory;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.Constants;
import game.Game;
import game.GameStates.GameplayStates;
import game.enemies.EnemyDropsFactory;
import game.generalSprites.GeneralImage;

/**
 * The FakeWall class represents a fake wall in the game.
 * When the player interacts with the fake wall by pressing the spacebar,
 * it triggers the addition of enemy drops to the game.
 */
public class FakeWall extends GeneralImage {
    private boolean spriteReceived = false;

    /**
     * Constructs a FakeWall object with the specified hitbox.
     *
     * @param pHitbox the hitbox of the fake wall
     */
    public FakeWall(Rectangle pHitbox) {
        super("special/fakeWall.png", pHitbox);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && !spriteReceived && Game.getPlayerHitbox().intersects(mHitbox)) {
            spriteReceived = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    EnemyDropsFactory.addDrop(new Point(Constants.CANVAS_WIDTH / 2 + 100 + 25 * i, Constants.CANVAS_HEIGHT / 2 - 200 - j * 25),
                            GameplayStates.ROOM_3);
                }
            }
        }
    }
}
