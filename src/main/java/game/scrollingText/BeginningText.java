package game.scrollingText;
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

import game.interfaces.BasicSprite;

public class BeginningText implements BasicSprite{

    private BufferedImage text;
    private Point pos;

    public BeginningText() {
        loadImage();
        pos = new Point(0, 0);
    }

    private void loadImage() {
        try {
            text = ImageIO.read(new File("src/main/resources/images/scrollingText/beginningScollingText.png"));
    
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }
    
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage
            (
                text, 
                pos.x,
                pos.y,
                observer
            );
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void tick() {
        //no special behavior
    }

    @Override
    public void onDelete() {
        text = null;
    }    
}
