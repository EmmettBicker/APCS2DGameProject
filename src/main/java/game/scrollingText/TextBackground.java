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

public class TextBackground implements BasicSprite{

    private BufferedImage background;
    private Point pos;

    public TextBackground() {
        loadImage();
        pos = new Point(0, 0);
    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("src/main/resources/images/scrollingText/scrollingTextSpaceBackground.jpg"));
    
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }
    
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage
            (
                background, 
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
            GameStates.setGameplayState(GameStates.GameplayStates.ROOM_1);
        }
    }

    @Override
    public void tick() {
        //no special behavior
    }

    @Override
    public void onDelete() {
        background = null;
    }    
}
