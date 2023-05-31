package game.wallFactory;

import game.interfaces.BasicRoomSprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

/**
 * The Wall class represents a wall in the game.
 * It is a basic room sprite that draws a filled black rectangle on the screen.
 */
public class Wall implements BasicRoomSprite {
    private Rectangle mWall;

    /**
     * Constructs a Wall object with the specified wall rectangle.
     *
     * @param pWall the rectangle representing the wall
     */
    public Wall(Rectangle pWall) {
        mWall = pWall;
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.setColor(Color.BLACK);
        g.fillRect((int) mWall.getX(), (int) mWall.getY(), (int) mWall.getWidth(), (int) mWall.getHeight());
    }

    /**
     * Returns the hitbox of the wall.
     *
     * @return the hitbox of the wall
     */
    public Rectangle getWallHitBox() {
        return mWall;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // No action on key press for a wall
    }

    @Override
    public void tick() {
        // No special behavior for a wall
    }

    @Override
    public void onDelete() {
        // No action on deletion for a wall
    }
}
