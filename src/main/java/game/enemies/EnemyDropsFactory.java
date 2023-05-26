package game.enemies;

import java.util.ArrayList;
import java.util.HashMap;

import game.GameStates.GameplayStates;;

public class EnemyDropsFactory {
    private static int uniqueDropId = 0;
    
    private static HashMap<GameplayStates, HashMap<Integer, EnemyDrop>> mRoomStateToHashmapOfIDToEnemyDrop = 
                        new HashMap<GameplayStates, HashMap<Integer, EnemyDrop>>();

    static {
        for (GameplayStates room : GameplayStates.values()) {
            mRoomStateToHashmapOfIDToEnemyDrop.put(room, new HashMap<Integer, EnemyDrop>());
        } 
    }
                    
    //add 
    public static void addDrop(GameplayStates pRoom)
    {
        mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).put(
            uniqueDropId, new EnemyDrop(uniqueDropId));   
        
    System.out.println(  mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).size());
         
    }

    public static ArrayList<EnemyDrop> getAllRoomDrops(GameplayStates pRoom)
    {
        HashMap<Integer, EnemyDrop> roomDropsMap = mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom);
        ArrayList<EnemyDrop> roomDrops = new ArrayList<>(roomDropsMap.values());
        
        return roomDrops;
    }

    public static void removeDrop(GameplayStates pRoom, int uniqueID)
    {
        mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).remove(uniqueID);
    }
    //delete
}
