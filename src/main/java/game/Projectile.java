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

public class Projectile implements BasicSprite {

    private BufferedImage projectileImage;
    private Player.WeaponOrientationStates weaponOrientation;
    private Point projectilePos;
    private int projectileSpeed; // Speed of the projectile
    private boolean moving; // Flag to indicate if the projectile is moving

    public enum ProjectileStates {
        INVISIBLE, VISIBLE
    }

    private ProjectileStates projectileState;
    private long timeEnteredWeaponState;

    public Projectile() {
        loadImage();
        weaponOrientation = WeaponOrientationStates.WEAPON_RIGHT;
        setProjectileState(ProjectileStates.INVISIBLE);
        projectilePos = new Point(0, 0);
        projectileSpeed = 5; // Set the desired speed of the projectile
        moving = false;
    }

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
                projectileState = ProjectileStates.INVISIBLE; // Set projectile state to invisible if it's outside the
                                                              // screen
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

    @Override
    public void onDelete() {
    }

    public ProjectileStates getProjectileState() {
        return projectileState;
    }

    public void setProjectileState(ProjectileStates state) {
        projectileState = state;
        timeEnteredWeaponState = System.currentTimeMillis();
    }

    public Rectangle getProjectileHitBox() {
        int width = projectileImage.getWidth();
        int height = projectileImage.getHeight();
        int x = projectilePos.x;
        int y = projectilePos.y;

        return new Rectangle(x, y, width, height);
    }

    private void startProjectileMovement() {
        moving = true;
    }

    private void stopProjectileMovement() {
        moving = false;
    }

    private void updateProjectilePosition() {
        switch (weaponOrientation) {
            case WEAPON_UP:
                projectilePos.y -= projectileSpeed;
                break;
            case WEAPON_DOWN:
                projectilePos.y += projectileSpeed;
                break;
            case WEAPON_LEFT:
                projectilePos.x -= projectileSpeed;
                break;
            case WEAPON_RIGHT:
                projectilePos.x += projectileSpeed;
                break;
        }
    }

    private boolean isProjectileOnScreen() {
        int projectileWidth = projectileImage.getWidth();
        int projectileHeight = projectileImage.getHeight();
        int projectileX = projectilePos.x;
        int projectileY = projectilePos.y;

        return (projectileX >= -projectileWidth && projectileX <= Constants.CANVAS_WIDTH
                && projectileY >= -projectileHeight && projectileY <= Constants.CANVAS_HEIGHT);
    }

}
