package game.titleScreen;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.interfaces.BasicSprite;

public class SpaceText implements BasicSprite {

    // image that represents the player's position on the board
    private BufferedImage text;
    private Point pos;

    public SpaceText() {
        // load the assets
        loadImage();
        pos = new Point(0, 0);

    }

    private void loadImage() {
        try {
            text = ImageIO.read(new File("src/main/resources/images/text/spaceText.png"));

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
                text,
                pos.x,
                pos.y,
                observer);

    }

    public void keyPressed(KeyEvent e) {
        // checking occurs in the title screen sprite
    }

    @Override
    public void tick() {
        pos.setLocation(0, Math.sin(System.currentTimeMillis() / 400.0) * 10);
    }

    @Override
    public void onDelete() {
        text = null;
    }

}