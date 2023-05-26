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

public class EnemyDrop implements BasicSprite {

    // image that represents the player's position on the board
    private BufferedImage background;
    private Point pos;
    private int uniqueID;
    private Rectangle mHitbox;
    public EnemyDrop(Point pLocation, int pUniqueID) {
        // load the assets
        uniqueID = pUniqueID;
        pos = pLocation;
        pos.x += Math.random() * 50.0 - 25;
        pos.y += Math.random() * 50.0 - 25;
        mHitbox = new Rectangle(pos.x, pos.y, 30, 40);
        loadImage();
        

    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.

            background = ImageIO.read(new File("src/main/resources/images/enemies/spriteDrink.png"));

        } catch (IOException exc) {
            System.out.println("Error opening soda image file: " + exc.getMessage());
        }
    }
    public Point getPos(){ 
        return pos;
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        g.drawImage(background, 
                mHitbox.x, 
                mHitbox.y, 
                mHitbox.width, 
                mHitbox.height, 
                observer);


    }

    public void keyPressed(KeyEvent e) {}

    @Override
    public void tick() {
        if (Game.getPlayerHitbox().intersects(mHitbox))
        {
            EnemyDropsFactory.removeDrop(GameStates.getGameplayState(), uniqueID);
            InventoryManager.addItem(InventoryManager.Item.kSprite, 1);
        }
        // no special behavior
    }

    @Override
    public void onDelete() {
    
    }

}
