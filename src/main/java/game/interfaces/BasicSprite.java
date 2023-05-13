package game.interfaces;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface BasicSprite {
    public void draw(Graphics g, ImageObserver observer);

    public void keyPressed(KeyEvent e);

    public void tick();

    public void onDelete();
}
