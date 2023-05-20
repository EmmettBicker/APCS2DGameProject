package game.enemies;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.utils.GeneralUtils;
import game.Constants;
import game.Game;

import game.interfaces.EnemyInterface;

public class Enemy implements EnemyInterface {

    private BufferedImage image;
    private Point enemyPos;
    private EnemyHealthBar enemyHealthBar;
    private int enemyCurrentHealth;
    private int enemyMaxHealth;

    private long lastDamageTime = 0;

    public Enemy(Point pos) {
        loadImage();
        enemyPos = pos;
        enemyCurrentHealth = 3;
        enemyMaxHealth = 3;
        enemyHealthBar = new EnemyHealthBar(enemyMaxHealth, enemyCurrentHealth, image.getWidth(), 5, Color.RED,
                Color.GREEN);
    }

    public void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.

            image = ImageIO.read(new File("src/main/resources/images/enemies/enemySprite.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                enemyPos.x,
                enemyPos.y,
                observer);

        enemyHealthBar.draw(g, enemyPos.x, enemyPos.y - 10);

        g.fillRect(enemyMaxHealth, enemyCurrentHealth, enemyMaxHealth, enemyCurrentHealth);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override

    public void tick() {
        
        int deltaX = (int) Math.abs((Game.getPlayerPosition().getX() - enemyPos.getX()));
        int deltaY = (int) Math.abs((Game.getPlayerPosition().getY() - enemyPos.getY()));
        if (deltaX > deltaY) {
            // if player if further down
            if (Game.getPlayerPosition().getX() > enemyPos.getX()) {
                enemyPos.x += Constants.BASIC_ENEMY_SPEED;
            } else {
                enemyPos.x -= Constants.BASIC_ENEMY_SPEED;
            }
        } else {
            if (Game.getPlayerPosition().getY() > enemyPos.getY()) {
                enemyPos.y += Constants.BASIC_ENEMY_SPEED;
            } else {
                enemyPos.y -= Constants.BASIC_ENEMY_SPEED;
            }
        }

        GeneralUtils.wallCollision(getEnemyHitboxRectangle(), enemyPos);

        if (getEnemyHitboxRectangle().intersects(Game.getPlayerHitbox())) {
            Game.getPlayer().lowerPlayerHealth();
        }
    }

    @Override
    public void onDelete() {
    }

    @Override
    public void onDeath() {
    }

    public Rectangle getEnemyHitboxRectangle() {
        return new Rectangle((int) enemyPos.getX(), (int) enemyPos.getY(), image.getWidth(), image.getHeight());
    }

    public void lowerEnemyHealth() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime > Constants.DELAY_BETWEEN_DAMAGE_TICKS) {
            enemyCurrentHealth -= Constants.BASIC_PLAYER_ATTACK_DAMAGE;
            lastDamageTime = currentTime;

            if (enemyCurrentHealth <= 0) {
                onDeath();
            }
            enemyHealthBar.setCurrentHealth(enemyCurrentHealth);
        }
    }

}
