package game.wallFactory;

import game.GameStates;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

public class WallFactory {

    private static Hashtable<GameStates.GameplayStates, ArrayList<Wall>> mRoomStateToWallArray = new Hashtable<GameStates.GameplayStates, ArrayList<Wall>>();
    
    // Static initialization code
    static {
        for (GameStates.GameplayStates room : GameStates.GameplayStates.values())
        {
            mRoomStateToWallArray.put(room, new ArrayList<Wall>());
        }
    }

    private WallFactory() {}

    public static void addWall(GameStates.GameplayStates pRoomState, Rectangle pWall)
    {
        mRoomStateToWallArray.get(pRoomState).add(new Wall(pWall));
    }

    public static ArrayList<Wall> getRoomWallArray(GameStates.GameplayStates pRoomState)
    {
        return mRoomStateToWallArray.get(pRoomState);
    }
}
