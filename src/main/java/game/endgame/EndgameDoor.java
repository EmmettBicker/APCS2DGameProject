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

public class EndgameDoor extends GeneralDoor{

    public EndgameDoor(Rectangle pHitbox) {
        super(GameplayStates.ROOM_5, new Point(Constants.CANVAS_WIDTH / 2 - 25, Constants.CANVAS_HEIGHT - 250), pHitbox);
        loadImage();
        //TODO Auto-generated constructor stub
    }
    
    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            img = ImageIO.read(new File("src/main/resources/images/special/lockedDoor.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen imagde file: " + exc.getMessage());
        }
    }
    
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
