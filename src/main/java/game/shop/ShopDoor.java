package game.shop;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Game;
import game.GameStates;
import game.GameStates.GameplayStates;
import game.generalSprites.GeneralDoor;

public class ShopDoor extends GeneralDoor{

    public ShopDoor(Rectangle pHitbox) {
        super(null, new Point(0,0), pHitbox);
        loadImage();
        //TODO Auto-generated constructor stub
    }
    
    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            img = ImageIO.read(new File("src/main/resources/images/shop/spriteDoor.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen imagde file: " + exc.getMessage());
        }
    }
    
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
