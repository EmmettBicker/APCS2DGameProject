package game.generalSprites;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.interfaces.BasicRoomSprite;

import game.Game;
import game.GameStates;
import game.utils.GeneralUtils;

public class GeneralImage implements BasicRoomSprite {

    // image that represents the player's position on the board
    private BufferedImage img;
    private GameStates.GameplayStates mDestination;
    private Point mPlayerEndPos;
    private Rectangle mHitbox;
    private String filename;

    public GeneralImage(String pFilename, Rectangle pHitbox) {
        // load the assets
        
        filename = pFilename;
        loadImage();
        mHitbox = pHitbox;

    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
    
            img = ImageIO.read(new File("src/main/resources/images/generalSprites/" + filename));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.

        g.drawImage(
                img,
                mHitbox.x,
                mHitbox.y,
                mHitbox.width,
                mHitbox.height,
                observer);

    }

    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void tick() {

        // no special behavior
    }

    @Override
    public void onDelete() {
        img = null;
    }

}