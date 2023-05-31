package game.endgame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Constants;
import game.Game;
import game.GameStates;
import game.GameStates.GameplayStates;
import game.PlayerAttributes.InventoryManager;
import game.PlayerAttributes.InventoryManager.Item;
import game.generalSprites.GeneralDoor;

/**
 * The EndgameDoor class represents a door object that appears in the endgame room of the game.
 * It extends the GeneralDoor class and provides additional functionality specific to the endgame.
 */
public class EndgameDoor extends GeneralDoor {

    /**
     * Constructs an EndgameDoor object with the specified hitbox.
     *
     * @param pHitbox the hitbox of the door
     */
    public EndgameDoor(Rectangle pHitbox) {
        super(GameplayStates.ROOM_5, new Point(Constants.CANVAS_WIDTH / 2 - 25, Constants.CANVAS_HEIGHT - 250), pHitbox);
        loadImage();
    }
    
    /**
     * Loads the image for the locked door from the file.
     * The image file should be located in the "src/main/resources/images/special" directory.
     */
    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            img = ImageIO.read(new File("src/main/resources/images/special/lockedDoor.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }
    
    /**
     * Handles the key pressed event for the endgame door.
     * If the player has collected enough items, pressing the space key and intersecting with the door's hitbox
     * will change the gameplay state to the destination room and move the player to the specified position.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (InventoryManager.getItemCount(Item.kBolt) < 2 || 
            InventoryManager.getItemCount(Item.kThwacker) < 2 || 
            InventoryManager.getItemCount(Item.kGear) < 2)
            return;
        if (key == KeyEvent.VK_SPACE && Game.getPlayerHitbox().intersects(mHitbox)
                && !Game.getHasChangedRoomAlready()) {
            Game.setHasChangedRoomAlready(true);

            GameStates.setGameplayState(mDestination);
            Game.setPlayerPosition(mPlayerEndPos);
            
        }
    }   
}
