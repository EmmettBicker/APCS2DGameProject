package game.enemies;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.text.Position;

import game.GameStates;

public class FinalBoss extends Enemy{

    public FinalBoss(Point pos) {
        super(pos);
        try {
            image = ImageIO.read(new File("src/main/resources/images/special/finalBoss.png"));

        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
        enemyMaxHealth = 6;
        enemyCurrentHealth = 6;
        enemyHealthBar = new EnemyHealthBar(enemyMaxHealth, enemyCurrentHealth, image.getWidth(), 5, Color.RED,
                Color.GREEN);
    }
    @Override
    public void tick()
    {   
        if (!isVisible)
        {
            GameStates.setState(GameStates.States.VICTORY);
        }
        super.tick();
    }
    
}
