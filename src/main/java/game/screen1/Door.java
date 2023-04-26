package game.screen1;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GameStates;
import game.GameStates.GameplayStates;
import game.interfaces.BasicRoomSprite;
import game.interfaces.BasicSprite;
import game.Game;
import game.utils.GeneralUtils;


public class Door implements BasicRoomSprite{

    // image that represents the player's position on the board
    private BufferedImage background;
    private Point doorPos;    


    public Door() {
        // load the assets

        loadImage();
        doorPos = new Point(0, 0);

    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            
            background = ImageIO.read(new File("src/main/resources/images/screenOne/hitBox.png"));
    
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but 
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
   
            g.drawImage
            (
                background, 
                doorPos.x,
                doorPos.y,
                observer
            );
    
    
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && GeneralUtils.isClose(Game.getPlayerPosition(), doorPos)) {
            System.out.println("close!");
        }
   
    }

    @Override
    public void tick() {
        // no special behavior
    }

    @Override
    public void onDelete() {
        background = null;
    }

  

}