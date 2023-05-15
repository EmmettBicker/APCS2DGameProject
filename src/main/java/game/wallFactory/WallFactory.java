package game.wallFactory;

import game.Constants;
import game.GameStates;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

public class WallFactory {

    private static Hashtable<GameStates.GameplayStates, ArrayList<Wall>> mRoomStateToWallArray = new Hashtable<GameStates.GameplayStates, ArrayList<Wall>>();

    // Static initialization code
    static {
        for (GameStates.GameplayStates room : GameStates.GameplayStates.values()) {
            mRoomStateToWallArray.put(room, new ArrayList<Wall>());
        }
    }

    private WallFactory() {
    }

    public static void addWall(GameStates.GameplayStates pRoomState, Rectangle pWall) {
        mRoomStateToWallArray.get(pRoomState).add(new Wall(pWall));
    }

    public static void addHallway(GameStates.GameplayStates pRoomState, int x, int y)
    {
        WallFactory.addWall(pRoomState,
            new Rectangle(x, 0, 100, y));

        WallFactory.addWall(pRoomState,
                new Rectangle(x, y+100, 100, Constants.CANVAS_HEIGHT-300));
        WallFactory.addWall(pRoomState,
                new Rectangle(x+100, 0, Constants.CANVAS_WIDTH-100, y-100));
        WallFactory.addWall(pRoomState,
                new Rectangle(x+100, y+200, Constants.CANVAS_WIDTH-100, Constants.CANVAS_HEIGHT-300));

        WallFactory.addWall(pRoomState,
                new Rectangle(x+Constants.CANVAS_WIDTH-100, y-200, 100, 200));
        WallFactory.addWall(pRoomState,
                new Rectangle(x+Constants.CANVAS_WIDTH-100, y+100, 100, Constants.CANVAS_HEIGHT-300));
    }

    public static ArrayList<Wall> getRoomWallArray(GameStates.GameplayStates pRoomState) {
        return mRoomStateToWallArray.get(pRoomState);
    }
}
