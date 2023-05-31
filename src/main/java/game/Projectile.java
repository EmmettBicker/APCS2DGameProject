package game;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.Rectangle;

import game.Player.WeaponOrientationStates;
import game.interfaces.BasicSprite;
import game.enemies.*;

/**
 * A class representing a projectile in the game.
 */
public class Projectile implements BasicSprite {

    private BufferedImage projectileImage;
    private Player.WeaponOrientationStates weaponOrientation;
    private Point projectilePos;
    private boolean moving; // Flag to indicate if the projectile is moving

    /**
     * Enumeration of the projectile states.
     */
    public enum ProjectileStates {
        INVISIBLE, VISIBLE
    }

    private ProjectileStates projectileState;
    private long timeEnteredWeaponState;

    /**
     * Constructs a new Projectile object.
     */
    public Projectile() {
        loadImage();
        weaponOrientation = WeaponOrientationStates.WEAPON_RIGHT;
        setProjectileState(ProjectileStates.INVISIBLE);
        projectilePos = new Point(0, 0);
        moving = false;
    }

    /**
     * Draws the projectile on the screen.
     *
     * @param g        the graphics object
     * @param observer the image observer
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        if (System.currentTimeMillis() - timeEnteredWeaponState > Constants.DELAY_FOR_WEAPON_DEPLOYMENT) {
            setProjectileState(ProjectileStates.INVISIBLE);
        }
        if (projectileState == ProjectileStates.INVISIBLE)
            return;

        if (moving) {
            updateProjectilePosition();
            if (!isProjectileOnScreen()) {
                projectileState = ProjectileStates.INVISIBLE; // Set projectile state to invisible if it's outside the screen
            }
        } else {
            projectilePos.x = Game.getPlayer().getPlayerPos().x;
            projectilePos.y = Game.getPlayer().getPlayerPos().y;
        }

        g.drawImage(projectileImage, projectilePos.x, projectilePos.y, observer);
    }

    private void loadImage() {
        try {
            projectileImage = ImageIO.read(new File("src/main/resources/images/projectile.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file weapon: " + exc.getMessage());
        }
    }

    /**
     * Handles the key pressed event.
     *
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W && projectileState == ProjectileStates.INVISIBLE) {
            setProjectileState(ProjectileStates.VISIBLE);
            startProjectileMovement();
            projectilePos.x = Game.getPlayer().getPlayerPos().x;  // Set the initial x position of the projectile to match the player's x position
            projectilePos.y = Game.getPlayer().getPlayerPos().y;  // Set the initial y position of the projectile to match the player's y position
        }
    }


    /**
     * Updates the state of the projectile.
     */
    @Override
    public void tick() {
        if (projectileState == ProjectileStates.INVISIBLE) {
            weaponOrientation = Game.getPlayer().getCurrWeaponOrientation();
        }

        GameStates.GameplayStates currentRoom = GameStates.getGameplayState();
        ArrayList<Enemy> currentRoomEnemies = EnemyFactory.getRoomEnemyArray(currentRoom);

        if (moving && projectileState == ProjectileStates.VISIBLE) {
            updateProjectilePosition();

            for (Enemy enemy : currentRoomEnemies) {
                Rectangle projectileHitbox = getProjectileHitBox();
                Rectangle enemyHitbox = enemy.getEnemyHitboxRectangle();

                if (projectileHitbox.intersects(enemyHitbox)) {
                    enemy.lowerEnemyHealth();
                    stopProjectileMovement();
                    setProjectileState(ProjectileStates.INVISIBLE);
                    break;
                }
            }

            if (projectilePos.x < 0 || projectilePos.x > Constants.CANVAS_WIDTH ||
                    projectilePos.y < 0 || projectilePos.y > Constants.CANVAS_HEIGHT) {
                stopProjectileMovement();
                setProjectileState(ProjectileStates.INVISIBLE);
            }
        }
    }

    /**
     * Called when the projectile is deleted.
     */
    @Override
    public void onDelete() {
        // no delete behavior
    }

    /**
     * Gets the current state of the projectile.
     *
     * @return the projectile state
     */
    public ProjectileStates getProjectileState() {
        return projectileState;
    }

    /**
     * Sets the state of the projectile.
     *
     * @param state the projectile state to set
     */
    public void setProjectileState(ProjectileStates state) {
        projectileState = state;
        timeEnteredWeaponState = System.currentTimeMillis();
    }

    /**
     * Gets the hitbox rectangle of the projectile.
     *
     * @return the projectile hitbox rectangle
     */
    public Rectangle getProjectileHitBox() {
        int width = projectileImage.getWidth();
        int height = projectileImage.getHeight();
        int x = projectilePos.x;
        int y = projectilePos.y;

        return new Rectangle(x, y, width, height);
    }

    /**
     * Starts the movement of the projectile.
     * Sets the 'moving' flag to true.
     */
    private void startProjectileMovement() {
        moving = true;
    }
    
    /**
     * Stops the movement of the projectile.
     * Sets the 'moving' flag to false.
     */
    private void stopProjectileMovement() {
        moving = false;
    }

    /**
     * Updates the position of the projectile based on its current weapon orientation.
     * The position is updated by adding or subtracting the projectile speed from the current position
     * in the corresponding direction.
     */
    private void updateProjectilePosition() {
        switch (weaponOrientation) {
            case WEAPON_UP:
                projectilePos.y -= Constants.PROJECTILE_SPEED;
                break;
            case WEAPON_DOWN:
                projectilePos.y += Constants.PROJECTILE_SPEED;
                break;
            case WEAPON_LEFT:
                projectilePos.x -= Constants.PROJECTILE_SPEED;
                break;
            case WEAPON_RIGHT:
                projectilePos.x += Constants.PROJECTILE_SPEED;
                break;
        }
    }

    /**
     * Checks if the projectile is currently on the screen.
     *
     * @return true if the projectile is on the screen, false otherwise
     */
    private boolean isProjectileOnScreen() {
        int projectileWidth = projectileImage.getWidth();
        int projectileHeight = projectileImage.getHeight();
        int projectileX = projectilePos.x;
        int projectileY = projectilePos.y;

        return (projectileX >= -projectileWidth && projectileX <= Constants.CANVAS_WIDTH
                && projectileY >= -projectileHeight && projectileY <= Constants.CANVAS_HEIGHT);
    }

}
