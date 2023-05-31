package game.enemies;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import game.GameStates.GameplayStates;

/**
 * The EnemyDropsFactory class is responsible for managing enemy drops in the game.
 * It provides methods to add, retrieve, and remove enemy drops in different gameplay states.
 */
public class EnemyDropsFactory {
    private static int uniqueDropId = 0;
    
    private static HashMap<GameplayStates, HashMap<Integer, EnemyDrop>> mRoomStateToHashmapOfIDToEnemyDrop = 
                        new HashMap<GameplayStates, HashMap<Integer, EnemyDrop>>();

    static {
        for (GameplayStates room : GameplayStates.values()) {
            mRoomStateToHashmapOfIDToEnemyDrop.put(room, new HashMap<Integer, EnemyDrop>());
        } 
    }
                    
    /**
     * Adds an enemy drop at the specified location in the given gameplay state.
     * 
     * @param pLocation  the location of the enemy drop
     * @param pRoom  the gameplay state in which the enemy drop should be added
     */
    public static void addDrop(Point pLocation, GameplayStates pRoom) {
        mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).put(
            uniqueDropId, new EnemyDrop(pLocation, uniqueDropId)); 
        uniqueDropId++;  
    }

    /**
     * Returns a list of all enemy drops in the specified gameplay state.
     * 
     * @param pRoom  the gameplay state from which to retrieve the enemy drops
     * @return a list of all enemy drops in the specified gameplay state
     */
    public static ArrayList<EnemyDrop> getAllRoomDrops(GameplayStates pRoom) { 
        HashMap<Integer, EnemyDrop> roomDropsMap = mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom);
        ArrayList<EnemyDrop> roomDrops = new ArrayList<>(roomDropsMap.values());
        return roomDrops;
    }

    /**
     * Removes the enemy drop with the specified unique ID from the given gameplay state.
     * 
     * @param pRoom  the gameplay state from which to remove the enemy drop
     * @param uniqueID  the unique ID of the enemy drop to be removed
     */
    public static void removeDrop(GameplayStates pRoom, int uniqueID) {
        mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).remove(uniqueID);
    }
}
