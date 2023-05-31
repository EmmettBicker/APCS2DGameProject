package game.enemies;

import game.GameStates;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.Point;

/**
 * The EnemyFactory class is responsible for managing enemy creation and retrieval in the game.
 * It provides methods to add enemies to specific gameplay states and retrieve the enemy arrays for each state.
 */
public class EnemyFactory {

    private static Hashtable<GameStates.GameplayStates, ArrayList<Enemy>> mRoomStateToEnemyArray = new Hashtable<GameStates.GameplayStates, ArrayList<Enemy>>();

    // Static initialization code
    static {
        for (GameStates.GameplayStates room : GameStates.GameplayStates.values()) {
            mRoomStateToEnemyArray.put(room, new ArrayList<Enemy>());
        }
    }

    private EnemyFactory() {
    }

    /**
     * Adds a regular enemy to the specified gameplay state with the given position.
     * 
     * @param pRoomState  the gameplay state in which the enemy should be added
     * @param pEnemyPos  the position of the enemy
     */
    public static void addEnemy(GameStates.GameplayStates pRoomState, Point pEnemyPos) {
        mRoomStateToEnemyArray.get(pRoomState).add(new Enemy(pEnemyPos));
    }
    
    /**
     * Adds a final boss enemy to the specified gameplay state with the given position.
     * 
     * @param pRoomState  the gameplay state in which the final boss enemy should be added
     * @param pEnemyPos  the position of the final boss enemy
     */
    public static void addFinalBossEnemy(GameStates.GameplayStates pRoomState, Point pEnemyPos) {
        mRoomStateToEnemyArray.get(pRoomState).add(new FinalBoss(pEnemyPos));
    }

    /**
     * Returns the array of enemies for the specified gameplay state.
     * 
     * @param pRoomState  the gameplay state from which to retrieve the enemy array
     * @return the array of enemies for the specified gameplay state
     */
    public static ArrayList<Enemy> getRoomEnemyArray(GameStates.GameplayStates pRoomState) {
        return mRoomStateToEnemyArray.get(pRoomState);
    }
}
