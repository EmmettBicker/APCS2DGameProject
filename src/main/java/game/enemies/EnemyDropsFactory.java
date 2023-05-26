package game.enemies;

import java.awt.Point;
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
    public static void addDrop(Point pLocation, GameplayStates pRoom)
    {
        mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).put(
            uniqueDropId, new EnemyDrop(pLocation, uniqueDropId)); 
            uniqueDropId++;  
        // for (EnemyDrop e : mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).values())
        // {
        //     System.out.println(pRoom + " " +e.getPos());
        // }
        
         
    }

    public static ArrayList<EnemyDrop> getAllRoomDrops(GameplayStates pRoom)
    { 
        HashMap<Integer, EnemyDrop> roomDropsMap = mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom);
        ArrayList<EnemyDrop> roomDrops = new ArrayList<>(roomDropsMap.values());
        // System.out.println(roomDrops.size());
        return roomDrops;
    }

    public static void removeDrop(GameplayStates pRoom, int uniqueID)
    {
        mRoomStateToHashmapOfIDToEnemyDrop.get(pRoom).remove(uniqueID);
    }
    //delete
}
