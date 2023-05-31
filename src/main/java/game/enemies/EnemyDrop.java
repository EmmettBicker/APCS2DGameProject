package game.enemies;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.Game;
import game.GameStates;
import game.PlayerAttributes.InventoryManager;
import game.interfaces.BasicSprite;

/**
 * The EnemyDrop class represents a drop left behind by an enemy when defeated.
 * It implements the BasicSprite interface and provides functionality for drop behavior.
 */
public class EnemyDrop implements BasicSprite {

    private BufferedImage background;
    private Point pos;
    private int uniqueID;
    private Rectangle mHitbox;

    /**
     * Constructs an EnemyDrop object with the specified location and unique ID.
     *
     * @param pLocation  the location of the drop
     * @param pUniqueID  the unique ID of the drop
     */
    public EnemyDrop(Point pLocation, int pUniqueID) {
        uniqueID = pUniqueID;
        pos = pLocation;
        pos.x += Math.random() * 50.0 - 25;
        pos.y += Math.random() * 50.0 - 25;
        mHitbox = new Rectangle(pos.x, pos.y, 30, 40);
        loadImage();
    }

    /**
     * Loads the image for the enemy drop from the file.
     * The image file should be located in the "src/main/resources/images/enemies" directory.
     */
    private void loadImage() {
        try {
            background = ImageIO.read(new File("src/main/resources/images/enemies/spriteDrink.png"));
        } catch (IOException exc) {
            System.out.println("Error opening soda image file: " + exc.getMessage());
        }
    }

    /**
     * Returns the position of the enemy drop.
     *
     * @return the position of the enemy drop
     */
    public Point getPos() {
        return pos;
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(background, mHitbox.x, mHitbox.y, mHitbox.width, mHitbox.height, observer);
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void tick() {
        if (Game.getPlayerHitbox().intersects(mHitbox)) {
            EnemyDropsFactory.removeDrop(GameStates.getGameplayState(), uniqueID);
            InventoryManager.addItem(InventoryManager.Item.kSprite, 1);
        }
        // no special behavior
    }

    @Override
    public void onDelete() {}
}
