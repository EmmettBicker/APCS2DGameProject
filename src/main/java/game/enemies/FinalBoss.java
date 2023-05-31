package game.enemies;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import game.GameStates;

/**
 * The FinalBoss class represents a final boss enemy in the game.
 * It extends the Enemy class and provides additional functionality specific to the final boss.
 */
public class FinalBoss extends Enemy {
    private BufferedImage image;
    private int enemyCurrentHealth;
    private int enemyMaxHealth;
    private boolean isVisible;

    /**
     * Constructs a FinalBoss object with the specified position.
     * 
     * @param pos  the position of the final boss
     */
    public FinalBoss(Point pos) {
        super(pos);
        try {
            image = ImageIO.read(new File("src/main/resources/images/special/finalBoss.png"));
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
        enemyMaxHealth = 6;
        enemyCurrentHealth = 6;
        new EnemyHealthBar(enemyMaxHealth, enemyCurrentHealth, image.getWidth(), 5, Color.RED, Color.GREEN);
    }

    /**
     * Performs the tick update for the final boss enemy.
     * If the final boss is not visible, it sets the game state to VICTORY.
     */
    @Override
    public void tick() {
        if (!isVisible) {
            GameStates.setState(GameStates.States.VICTORY);
        }
        super.tick();
    }
}
