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
import game.GameStates;
import game.interfaces.EnemyInterface;
import java.util.ArrayList;

/**
 * The Enemy class represents an enemy in the game.
 * It implements the EnemyInterface interface.
 */
public class Enemy implements EnemyInterface {

    protected BufferedImage image;
    protected Point enemyPos;
    protected EnemyHealthBar enemyHealthBar;
    protected int enemyCurrentHealth;
    protected int enemyMaxHealth;
    protected boolean isVisible;
    private ArrayList<EnemyDrop> drops;
    private long lastDamageTime = 0;

    /**
     * Constructs an Enemy object with the specified position.
     *
     * @param pos the position of the enemy
     */
    public Enemy(Point pos) {
        loadImage();
        enemyPos = pos;
        enemyCurrentHealth = 3;
        enemyMaxHealth = 3;
        enemyHealthBar = new EnemyHealthBar(enemyMaxHealth, enemyCurrentHealth, image.getWidth(), 5, Color.RED,
                Color.GREEN);
        isVisible = true;
        drops = new ArrayList<>();
    }

    /**
     * Loads the image for the enemy from a file.
     */
    public void loadImage() {
        try {
            image = ImageIO.read(new File("src/main/resources/images/enemies/enemySprite.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    @Override
    public void draw(Graphics g, ImageObserver observer) {
        if (!isVisible) {
            return;
        }

        g.drawImage(
                image,
                enemyPos.x,
                enemyPos.y,
                observer);

        enemyHealthBar.draw(g, enemyPos.x, enemyPos.y - 10);

        drawDrops(g, observer);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press if needed
    }

    @Override
    public void tick() {
        if (!isVisible) {
            return;
        }

        int deltaX = (int) Math.abs((Game.getPlayerPosition().getX() - enemyPos.getX()));
        int deltaY = (int) Math.abs((Game.getPlayerPosition().getY() - enemyPos.getY()));
        if (deltaX > deltaY) {
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

        if (enemyCurrentHealth <= 0) {
            isVisible = false;
            onDeath();
        }
    }

    @Override
    public void onDelete() {
        // Handle deletion if needed
    }

    private void drawDrops(Graphics g, ImageObserver observer) {    
        for (EnemyDrop drop : drops) {
            drop.draw(g, observer);
        }
    }

    @Override
    public void onDeath() {
        for (int i = 0; i < 3; i++) {
           Point randomPoint = new Point(enemyPos);
           randomPoint.x += Math.random() * 50 - 100 + getEnemyHitboxRectangle().width*1.5;
           randomPoint.y += Math.random() * 50 - 100 + getEnemyHitboxRectangle().height*1.5;
           EnemyDropsFactory.addDrop(randomPoint, GameStates.getGameplayState());
        }
    }

    /**
     * Returns the rectangle representing the hitbox of the enemy.
     *
     * @return the hitbox rectangle
     */
    public Rectangle getEnemyHitboxRectangle() {
        return new Rectangle((int) enemyPos.getX(), (int) enemyPos.getY(), image.getWidth(), image.getHeight());
    }

    /**
     * Lowers the enemy's health by the player's attack damage.
     */
    public void lowerEnemyHealth() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime > Constants.DELAY_BETWEEN_DAMAGE_TICKS) {
            enemyCurrentHealth -= Constants.BASIC_PLAYER_ATTACK_DAMAGE;
            lastDamageTime = currentTime;

            enemyHealthBar.setCurrentHealth(enemyCurrentHealth);
        }
    }
}
