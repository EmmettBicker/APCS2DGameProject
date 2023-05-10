package game.Enemies;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.utils.GeneralUtils;
import game.Game;

import game.interfaces.EnemyInterface;

public class Enemy implements EnemyInterface{

    private BufferedImage image;
    private Point enemyPos;

    public Enemy(Point pos) {
        loadImage();
        enemyPos = pos;
    }

    public void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            
            image = ImageIO.read(new File("src/main/resources/images/enemies/enemysprite.png"));
    
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage
            (
                image, 
                enemyPos.x,
                enemyPos.y,
                observer
            );
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    
    public void tick() {
        int deltaX = (int) Math.abs((Game.getPlayerPosition().getX() - enemyPos.getX()));
        int deltaY = (int) Math.abs((Game.getPlayerPosition().getY() - enemyPos.getY()));
        if (deltaX > deltaY) {
            enemyPos.x += 1;
        }
        else {
            enemyPos.y +=1;
        }
        GeneralUtils.wallCollision(getPlayerHitboxRectangle(), enemyPos);
    }

    @Override
    public void onDelete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDelete'");
    }

    @Override
    public void onDeath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDeath'");
    }

    public Rectangle getPlayerHitboxRectangle() {
        return new Rectangle((int) enemyPos.getX(), (int) enemyPos.getY(), image.getWidth(), image.getHeight());
    } 
    
}
