package game.shop;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Game;
import game.GameStates;
import game.generalSprites.GeneralDoor;

/**
 * The ShopDoor class represents a door in the game that leads to the shop.
 * It extends the GeneralDoor class.
 */
public class ShopDoor extends GeneralDoor {

    /**
     * Constructs a ShopDoor object with the specified hitbox.
     *
     * @param pHitbox the hitbox of the door
     */
    public ShopDoor(Rectangle pHitbox) {
        super(null, new Point(0, 0), pHitbox);
        loadImage();
    }

    /**
     * Loads the door image from a file.
     */
    private void loadImage() {
        try {
            img = ImageIO.read(new File("src/main/resources/images/shop/spriteDoor.png"));
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    /**
     * Handles the key pressed events for the shop door.
     * If the space key is pressed and the player intersects with the door's hitbox,
     * and the room has not been changed already, it sets the game state to SHOP.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && Game.getPlayerHitbox().intersects(mHitbox)
                && !Game.getHasChangedRoomAlready()) {
            Game.setHasChangedRoomAlready(true);
            GameStates.setState(GameStates.States.SHOP);
        }
    }
}
