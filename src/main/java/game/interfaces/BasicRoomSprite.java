package game.interfaces;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GameStates;

public interface BasicRoomSprite{
    public void draw(Graphics g, ImageObserver observer);
    public void keyPressed(KeyEvent e);
    public void tick();
    public void onDelete();
}
