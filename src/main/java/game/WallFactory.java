package game;
import game.interfaces.BasicRoomSprite;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

public class WallFactory implements BasicRoomSprite{
   
    private static Rectangle mWall;

    public WallFactory() {
        mWall = new Rectangle(100, 100, 600, 500);
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        
        //fillRect makes a filled in rectangle
        //would likely create an array or some type of data structure to hold the information to create the different type of walls
        g.setColor(Color.BLACK);
        g.fillRect((int) mWall.getX(), (int) mWall.getY(), (int) mWall.getWidth(), (int) mWall.getHeight());
    }

    public static Rectangle getWallHitBox() {
        return mWall;
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void tick() {}
    

    @Override
    public void onDelete() {}

    
}
