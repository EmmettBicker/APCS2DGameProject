package game;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.Player.WeaponOrientationStates;
import game.interfaces.BasicSprite;

public class Weapon implements BasicSprite {

    private BufferedImage weaponImage;
    private Player.WeaponOrientationStates weaponOrientation;

    public enum WeaponStates {
        INVISIBLE, VISIBLE
    }

    private WeaponStates weaponState;
    private long timeEnteredWeaponState;

    public Weapon() {
        loadImage();
        weaponOrientation = WeaponOrientationStates.WEAPON_RIGHT;
        setWeaponState(WeaponStates.INVISIBLE);
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        if (System.currentTimeMillis() - timeEnteredWeaponState > Constants.DELAY_FOR_WEAPON_DEPLOYMENT) {
            setWeaponState(WeaponStates.INVISIBLE);
        }
        if (weaponState == WeaponStates.INVISIBLE)
            return;

        switch (weaponOrientation) {
            case WEAPON_UP:
                g.drawImage(
                        weaponImage,
                        Game.getPlayer().getPlayerPos().x,
                        Game.getPlayer().getPlayerPos().y - weaponImage.getWidth(),
                        observer);
                break;
            case WEAPON_DOWN:
                g.drawImage(
                        weaponImage,
                        Game.getPlayer().getPlayerPos().x,
                        Game.getPlayer().getPlayerPos().y - Game.getPlayer().getPlayerImageWidth(),
                        observer);
                break;
            case WEAPON_LEFT:
                g.drawImage(
                        weaponImage,
                        Game.getPlayer().getPlayerPos().x - weaponImage.getWidth(),
                        Game.getPlayer().getPlayerPos().y,
                        observer);
                break;
            case WEAPON_RIGHT:
                g.drawImage(
                        weaponImage,
                        Game.getPlayer().getPlayerPos().x + weaponImage.getWidth(),
                        Game.getPlayer().getPlayerPos().y,
                        observer);
                break;
        }
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
        if (key == KeyEvent.VK_Q) {
            Game.getWeapon().setWeaponState(WeaponStates.VISIBLE);
        }
    }

    @Override
    public void tick() {
        weaponOrientation = Game.getPlayer().getCurrWeaponOrientation();
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
}
