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

public class TextBackground implements BasicSprite{

    private BufferedImage text;
    private Point pos;

    public TextBackground() {
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
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {         
            GameStates.setState(GameStates.States.GAMEPLAY);
        }
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void onDelete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDelete'");
    }
    
}
