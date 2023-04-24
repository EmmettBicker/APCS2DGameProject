package game;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.utils.ImageUtils;
import game.interfaces.BasicSprite;

public class Player implements BasicSprite{

    // image that represents the player's position on the board
    private BufferedImage leftImage;
    private BufferedImage rightImage;
    private Point pos;
    private int score;
    private enum PlayerFacingStates {LEFT, RIGHT};
    private PlayerFacingStates playerFacing;

    


    public Player() {
        // load the assets
        loadImage();
    
        pos = new Point(60, 60);
        playerFacing = PlayerFacingStates.RIGHT;
        score = 0;
    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
           
            rightImage = ImageIO.read(new File("src/main/resources/images/player.png"));
            leftImage = ImageUtils.flipImageHoriziontally(rightImage);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but 
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        // System.out.println(pos);
        if (playerFacing == PlayerFacingStates.RIGHT)
        {
            g.drawImage
            (
                leftImage, 
                pos.x * Board.TILE_SIZE, 
                pos.y * Board.TILE_SIZE, 
                observer
            );
        }
        else if (playerFacing == PlayerFacingStates.LEFT)
        {
            g.drawImage
            (
                rightImage, 
                pos.x * Board.TILE_SIZE, 
                pos.y * Board.TILE_SIZE, 
                observer
            );
        }
        
    }
    
    private boolean isUpPressed = false;
    private boolean isRightPressed = false;
    private boolean isDownPressed = false;
    private boolean isLeftPressed = false;

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isUpPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isRightPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            isDownPressed = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            isLeftPressed = true;
        }
   
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isUpPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isRightPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            isDownPressed = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            isLeftPressed = false;
        }

    }

    public void updateMovement()
    {
        if (isUpPressed) {
            pos.translate(0, -1);
        }
        if (isRightPressed) {
            playerFacing = PlayerFacingStates.RIGHT;
            pos.translate(1, 0);
        }
        if (isDownPressed) {
            pos.translate(0, 1);
        }
        if (isLeftPressed) {
            playerFacing = PlayerFacingStates.LEFT;
            pos.translate(-1, 0);
        }
    }

    public void tick() {
        updateMovement();
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= Board.COLUMNS) {
            pos.x = Board.COLUMNS - 1;
        }
        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= Board.ROWS) {
            pos.y = Board.ROWS - 1;
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPos() {
        return pos;
    }

    @Override
    public void onDelete() {
        // no special action yet
    }

}