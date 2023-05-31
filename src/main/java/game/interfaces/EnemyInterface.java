package game.interfaces;

/**
 * The EnemyInterface interface represents an enemy sprite in the game.
 * It extends the BasicRoomSprite interface and adds a method for handling enemy death.
 */
public interface EnemyInterface extends BasicRoomSprite {

    /**
     * Performs necessary actions when the enemy is defeated.
     */
    public void onDeath();
}
