package game.titleScreen;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GameStates;
import game.interfaces.BasicSprite;


public class TitleScreen implements BasicSprite{

    // image that represents the player's position on the board
    private BufferedImage background;
    private Point pos;
    

    public TitleScreen() {
        // load the assets
        loadImage();
        pos = new Point(0, 0);

    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            
            background = ImageIO.read(new File("src/main/resources/images/backgrounds/titleScreen.png"));
    
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
                pos.x,
                pos.y,
                observer
            );
    
    
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
           
            GameStates.setState(GameStates.States.GAME);
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