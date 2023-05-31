package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.utils.GeneralUtils;
import game.utils.ImageUtils;
import game.GameStates.GameplayStates;
import game.GameStates.States;
import game.Weapon.WeaponStates;
import game.interfaces.BasicSprite;

/**
 * The Player class represents the player character in the game.
 * It handles the player's movement, health, sprite rendering, and other related functionality.
 */
public class Player implements BasicSprite {

    public static WeaponStates weaponState;
    // image that represents the player's position on the board
    private BufferedImage leftImage;
    private BufferedImage rightImage;

    // possible states that the player is facing
    private enum PlayerFacingStates {
        LEFT, RIGHT 
    };

    private PlayerFacingStates playerFacing;

    /**
     * Represents the current orientation of the player's weapon.
     */
    public enum WeaponOrientationStates {
        WEAPON_UP, WEAPON_DOWN, WEAPON_LEFT, WEAPON_RIGHT,
    }

    private WeaponOrientationStates currWeaponOrientation;
    
    // position of player sprite
    private Point playerPos;

    private int currentHealth;
    private int maxHealth;

    private long lastDamageTime = 0;
    private long lastHealthRegenTime = System.currentTimeMillis();
    private boolean canMove;

    /**
     * Constructs a new Player object.
     * It loads the player's image, sets the initial position, orientation, and health.
     */
    public Player() {
        loadImage();

        playerPos = new Point(Constants.CANVAS_WIDTH / 2 - rightImage.getWidth() / 2, Constants.CANVAS_HEIGHT - 250);
        playerFacing = PlayerFacingStates.RIGHT;
        currWeaponOrientation = WeaponOrientationStates.WEAPON_RIGHT;
        maxHealth = 10;
        currentHealth = 10;
        canMove = true;
    }

    /**
     * Loads the player's image from a file.
     */
    private void loadImage() {
        try {
            rightImage = ImageIO.read(new File("src/main/resources/images/player.png"));
            leftImage = ImageUtils.flipImageHorizontally(rightImage);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    /**
     * Renders the player's sprite on the screen.
     *
     * @param g        the Graphics object used for rendering
     * @param observer the ImageObserver object
     */
    public void draw(Graphics g, ImageObserver observer) {
        if (playerFacing == PlayerFacingStates.RIGHT) {
            g.drawImage(leftImage, playerPos.x, playerPos.y, observer);
        } else if (playerFacing == PlayerFacingStates.LEFT) {
            g.drawImage(rightImage, playerPos.x, playerPos.y, observer);
        }
    }

    private boolean isUpPressed = false;
    private boolean isRightPressed = false;
    private boolean isDownPressed = false;
    private boolean isLeftPressed = false;

    /**
     * Handles the key press events for controlling the player's movement.
     *
     * @param e the KeyEvent object representing the key press event
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            isUpPressed = true;
            currWeaponOrientation = WeaponOrientationStates.WEAPON_UP;
        }
        if (key == KeyEvent.VK_RIGHT) {
            isRightPressed = true;
            currWeaponOrientation = WeaponOrientationStates.WEAPON_RIGHT;
        }
        if (key == KeyEvent.VK_DOWN) {
            isDownPressed = true;
            currWeaponOrientation = WeaponOrientationStates.WEAPON_DOWN;
        }
        if (key == KeyEvent.VK_LEFT) {
            isLeftPressed = true;
            currWeaponOrientation = WeaponOrientationStates.WEAPON_LEFT;
        }
    }

    /**
     * Retrieves the current orientation of the player's weapon.
     *
     * @return the current WeaponOrientationStates value
     */
    public WeaponOrientationStates getCurrWeaponOrientation() {
        return currWeaponOrientation;
    }

    /**
     * Handles the key release events for controlling the player's movement.
     *
     * @param e the KeyEvent object representing the key release event
     */
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

    /**
     * Updates the player's movement based on the currently pressed keys.
     */
    public void updateMovement() {
        if (canMove) {
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
    }

    /**
     * Locks the player's movement.
     */
    public void lockMovement() {
        canMove = false;
    }

    /**
     * Allows the player's movement.
     */
    public void allowMovement() {
        canMove = true;
    }

    /**
     * Updates the player's state in each game tick.
     * This includes movement, collision detection, health regeneration, and death detection.
     */
    public void tick() {
        updateMovement();
        screenEdgeDetection();
        GeneralUtils.wallCollision(getPlayerHitboxRectangle(), playerPos);
        passiveHealthRegen();
        deathDetection();
    }

    /**
     * Performs screen edge detection to prevent the player from moving off the edge of the game board.
     */
    public void screenEdgeDetection() {
        if (playerPos.x < 0) {
            playerPos.x = 0;
        } else if (playerPos.x >= Constants.CANVAS_WIDTH - rightImage.getWidth()) {
            playerPos.x = Constants.CANVAS_WIDTH - 1 - rightImage.getHeight();
        }
        if (playerPos.y < 0) {
            playerPos.y = 0;
        } else if (playerPos.y >= Constants.CANVAS_HEIGHT - (int) (rightImage.getHeight())) {
            playerPos.y = Constants.CANVAS_HEIGHT - (int) (rightImage.getHeight()) - 1;
        }
    }

    /**
     * Performs death detection for the player.
     * If the player's health reaches 0 or below, the game state is set to "DEATH".
     */
    public void deathDetection() {
        if (currentHealth <= 0) {
            GameStates.setState(States.DEATH);
        }
    }

    /**
     * Sets the position of the player.
     *
     * @param pPos the new position of the player
     */
    public void setPosition(Point pPos) {
        playerPos.x = pPos.x;
        playerPos.y = pPos.y;
    }

    /**
     * Retrieves the position of the player.
     *
     * @return the position of the player as a Point object
     */
    public Point getPlayerPos() {
        return playerPos;
    }

    /**
     * Retrieves the bounding rectangle of the player's hitbox.
     *
     * @return the bounding rectangle of the player's hitbox
     */
    public Rectangle getPlayerHitboxRectangle() {
        return new Rectangle((int) playerPos.getX(), (int) playerPos.getY(), rightImage.getWidth(),
                rightImage.getHeight());
    }

    @Override
    public void onDelete() {
        // no special action yet
    }

    /**
     * Retrieves the current health of the player.
     *
     * @return the current health of the player
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Retrieves the maximum health of the player.
     *
     * @return the maximum health of the player
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Lowers the player's health by a certain amount.
     * The player's health is reduced only if a certain time has passed since the last damage taken.
     */
    public void lowerPlayerHealth() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime > Constants.DELAY_BETWEEN_DAMAGE_TICKS) {
            currentHealth -= Constants.BASIC_ENEMY_ATTACK_DAMAGE;
            lastDamageTime = currentTime;
        }
    }

    /**
     * Performs passive health regeneration for the player.
     * The player's health gradually increases over time, up to the maximum health.
     */
    public void passiveHealthRegen() {
        if (currentHealth < maxHealth) {
            if (System.currentTimeMillis() - lastHealthRegenTime >= Constants.HEALTH_REGEN_DELAY) {
                currentHealth += Constants.HEALTH_REGEN_AMOUNT;
                lastHealthRegenTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * Retrieves the width of the player's image.
     *
     * @return the width of the player's image
     */
    public int getPlayerImageWidth() {
        return rightImage.getWidth();
    }
}
