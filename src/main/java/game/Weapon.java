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
 * The Weapon class represents the player's weapon in the game.
 * It handles the rendering, movement, and interaction of the weapon.
 */
public class Weapon implements BasicSprite {

    private BufferedImage weaponImage;
    private Player.WeaponOrientationStates weaponOrientation;
    private Point weaponPos;

    /**
     * Enum representing the different states of the weapon.
     * The weapon can be either invisible or visible.
     */
    public enum WeaponStates {
        INVISIBLE, VISIBLE
    }

    private WeaponStates weaponState;
    private long timeEnteredWeaponState;

    /**
     * Constructs a new Weapon object.
     * It loads the weapon image, sets the initial orientation and state of the weapon,
     * and initializes the weapon position.
     */
    public Weapon() {
        loadImage();
        weaponOrientation = WeaponOrientationStates.WEAPON_RIGHT;
        setWeaponState(WeaponStates.INVISIBLE);
        weaponPos = new Point(0, 0);
    }

    /**
     * Draws the weapon on the screen.
     * It checks the time entered the weapon state and sets the weapon state to invisible if necessary.
     * The weapon is drawn at the player's position based on the weapon orientation.
     *
     * @param g        the Graphics object for drawing
     * @param observer the ImageObserver for the drawing operation
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        if (System.currentTimeMillis() - timeEnteredWeaponState > Constants.DELAY_FOR_WEAPON_DEPLOYMENT) {
            setWeaponState(WeaponStates.INVISIBLE);
        }
        if (weaponState == WeaponStates.INVISIBLE)
            return;

        weaponPos.x = Game.getPlayer().getPlayerPos().x;
        weaponPos.y = Game.getPlayer().getPlayerPos().y;

        switch (weaponOrientation) {
            case WEAPON_UP:
                weaponPos.y -= weaponImage.getHeight();
                break;
            case WEAPON_DOWN:
                weaponPos.y += Game.getPlayer().getPlayerImageWidth();
                break;
            case WEAPON_LEFT:
                weaponPos.x -= weaponImage.getWidth();
                break;
            case WEAPON_RIGHT:
                weaponPos.x += weaponImage.getWidth();
                break;
        }

        g.drawImage(weaponImage, weaponPos.x, weaponPos.y, observer);

    }

    /**
     * Loads the image file for the weapon.
     * The image file path is hardcoded as "src/main/resources/images/weapon.png".
     */
    private void loadImage() {
        try {
            weaponImage = ImageIO.read(new File("src/main/resources/images/weapon.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file weapon: " + exc.getMessage());
        }
    }

    /**
     * Handles the keyPressed event for the weapon.
     * If the Q key is pressed and the weapon state is invisible, it sets the weapon state to visible.
     *
     * @param e the KeyEvent object representing the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_Q && weaponState == WeaponStates.INVISIBLE) {
            setWeaponState(WeaponStates.VISIBLE);
        }
    }

    /**
     * Updates the weapon state and checks for weapon-enemy collisions.
     * If the weapon state is invisible, it updates the weapon orientation based on the player's current weapon orientation.
     * If the weapon state is visible, it iterates over the enemies in the current room and checks for collisions.
     * If a collision is detected, it lowers the enemy's health.
     */
    @Override
    public void tick() {
        if (weaponState == WeaponStates.INVISIBLE) {
            weaponOrientation = Game.getPlayer().getCurrWeaponOrientation();
        }

        GameStates.GameplayStates currentRoom = GameStates.getGameplayState();
        ArrayList<Enemy> currentRoomEnemies = EnemyFactory.getRoomEnemyArray(currentRoom);
        if (weaponState == WeaponStates.VISIBLE) {
            for (Enemy enemy : currentRoomEnemies) {

                Rectangle weaponHitbox = getWeaponHitBox();
                Rectangle enemyHitbox = enemy.getEnemyHitboxRectangle();

                if (weaponHitbox.intersects(enemyHitbox)) {
                    enemy.lowerEnemyHealth();
                }
            }
        }
    }

    /**
     * Called when the weapon is deleted or removed.
     * This method does not perform any specific action.
     */
    @Override
    public void onDelete() {
    }

    /**
     * Retrieves the current state of the weapon.
     *
     * @return the current state of the weapon
     */
    public WeaponStates getWeaponState() {
        return weaponState;
    }

    /**
     * Sets the state of the weapon.
     * It also updates the time entered the weapon state.
     *
     * @param state the new state to set for the weapon
     */
    public void setWeaponState(WeaponStates state) {
        weaponState = state;
        timeEnteredWeaponState = System.currentTimeMillis();
    }

    /**
     * Retrieves the hitbox of the weapon.
     * The hitbox is a Rectangle object representing the position and size of the weapon.
     *
     * @return the hitbox of the weapon
     */
    public Rectangle getWeaponHitBox() {
        int width = weaponImage.getWidth();
        int height = weaponImage.getHeight();
        int x = weaponPos.x;
        int y = weaponPos.y;

        switch (weaponOrientation) {
            case WEAPON_UP:
                y -= height;
                break;
            case WEAPON_DOWN:
                y += Game.getPlayer().getPlayerImageWidth();
                break;
            case WEAPON_LEFT:
                x -= width;
                break;
            case WEAPON_RIGHT:
                x += width;
                break;
        }

        return new Rectangle(x, y, width, height);
    }

}
