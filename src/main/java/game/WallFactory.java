package game;
import game.interfaces.BasicRoomSprite;
import java.util.Arrays;
import java.util.Hashtable;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import game.interfaces.BasicSprite;
import game.interfaces.BasicRoomSprite;
import game.titleScreen.*;
import game.scrollingText.*;
import game.screen1.*;


public class WallFactory implements BasicRoomSprite{
   
    Rectangle mWall;

    public WallFactory() {
        mWall = new Rectangle(Constants.CANVAS_HEIGHT/2, Constants.CANVAS_WIDTH/2, 50, 10);
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawRect(0, 0, 1280, 720);
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void tick() {}
    

    @Override
    public void onDelete() {}

    
}
