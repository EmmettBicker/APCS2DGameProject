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

public class Weapon implements BasicSprite {

    private BufferedImage weaponImage;
    private Player.WeaponOrientationStates weaponOrientation;
    private Point weaponPos;

    public enum WeaponStates {
        INVISIBLE, VISIBLE
    }

    private WeaponStates weaponState;
    private long timeEnteredWeaponState;

    public Weapon() {
        loadImage();
        weaponOrientation = WeaponOrientationStates.WEAPON_RIGHT;
        setWeaponState(WeaponStates.INVISIBLE);
        weaponPos = new Point(0, 0);
    }

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

    private void loadImage() {
        try {
            weaponImage = ImageIO.read(new File("src/main/resources/images/weapon.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file weapon: " + exc.getMessage());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_Q && weaponState == WeaponStates.INVISIBLE) {
            setWeaponState(WeaponStates.VISIBLE);
        }
    }

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

    @Override
    public void onDelete() {
    }

    public WeaponStates getWeaponState() {
        return weaponState;
    }

    public void setWeaponState(WeaponStates state) {
        weaponState = state;
        timeEnteredWeaponState = System.currentTimeMillis();
    }

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
