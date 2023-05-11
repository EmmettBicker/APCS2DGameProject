package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.utils.GeneralUtils;
import game.utils.ImageUtils;
import game.interfaces.BasicSprite;

import game.wallFactory.Wall;
import game.wallFactory.WallFactory;

public class Player implements BasicSprite{

    // image that represents the player's position on the board
    private BufferedImage leftImage;
    private BufferedImage rightImage;

    // possible states that the player is facing
    private enum PlayerFacingStates {LEFT, RIGHT};
    private PlayerFacingStates playerFacing;
    
    // position of player sprite
    private Point playerPos;
    
    // needs to be reimplemented    
    private int score;

    private int currentHealth;
    private int maxHealth;

    public Player() {
        loadImage();

        playerPos = new Point(Constants.CANVAS_WIDTH/2-rightImage.getWidth()/2, Constants.CANVAS_HEIGHT-250);
        playerFacing = PlayerFacingStates.RIGHT;
        maxHealth = 10;
        score = 0;
    }

    private void loadImage() {
        try {
            rightImage = ImageIO.read(new File("src/main/resources/images/player.png"));
            leftImage = ImageUtils.flipImageHoriziontally(rightImage);
        } 
        catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but 
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        // System.out.println(pos);

        if (playerFacing == PlayerFacingStates.RIGHT) {

            g.drawImage
            (
                leftImage, 
                playerPos.x, 
                playerPos.y, 
                observer
            );
        }
        else if (playerFacing == PlayerFacingStates.LEFT) {
            g.drawImage
            (
                rightImage, 
                playerPos.x, 
                playerPos.y, 
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
            playerPos.translate(0, -Constants.PLAYER_SPEED);
        }
        if (isRightPressed) {
            playerFacing = PlayerFacingStates.RIGHT;
            playerPos.translate(Constants.PLAYER_SPEED, 0);
        }
        if (isDownPressed) {
            playerPos.translate(0, Constants.PLAYER_SPEED);
        }
        if (isLeftPressed) {
            playerFacing = PlayerFacingStates.LEFT;
            playerPos.translate(-Constants.PLAYER_SPEED, 0);
        }
    }

    
    public void tick() {
        updateMovement();
        // this gets called once every tick, before the repainting process happens.
        // so we can do anything needed in here to update the state of the player.

        // prevent the player from moving off the edge of the board sideways
        screenEdgeDetection();

        GeneralUtils.wallCollision(getPlayerHitboxRectangle(), playerPos);

        
    }

    public void screenEdgeDetection()
    {
        if (playerPos.x < 0) {
            playerPos.x = 0;
        } else if (playerPos.x >= Constants.CANVAS_WIDTH - rightImage.getWidth()) {
            playerPos.x = Constants.CANVAS_WIDTH - 1 - rightImage.getHeight();
        }
        // prevent the player from moving off the edge of the board vertically
        if (playerPos.y < 0) {
            playerPos.y = 0;
        } else if (playerPos.y >= Constants.CANVAS_HEIGHT - (int)(rightImage.getHeight())) {
            playerPos.y = Constants.CANVAS_HEIGHT - (int)(rightImage.getHeight()) - 1;
        }
    }
    
    // public void isPlayerAttacked

    public String getScore() {
        return String.valueOf(score);
    }

    public void setPosition(Point pPos)
    {
        // No mutation
        playerPos.x = pPos.x;
        playerPos.y = pPos.y;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPlayerPos() {
        return playerPos;
    }

    public Rectangle getPlayerHitboxRectangle() {
        return new Rectangle((int) playerPos.getX(), (int) playerPos.getY(), rightImage.getWidth(), rightImage.getHeight());
    } 

    @Override
    public void onDelete() {
        // no special action yet
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCuurentHealth() {
        currentHealth = 10;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth() {
        maxHealth = 10;
    }

}