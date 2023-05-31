package game.wallFactory;

import game.Constants;
import game.GameStates;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The WallFactory class is responsible for managing walls in different game rooms.
 * It allows adding walls to specific game rooms and retrieving the wall arrays for each room.
 */
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

    /**
     * Adds a wall to the specified game room.
     *
     * @param pRoomState the game room state to add the wall to
     * @param pWall      the wall rectangle
     */
    public static void addWall(GameStates.GameplayStates pRoomState, Rectangle pWall) {
        mRoomStateToWallArray.get(pRoomState).add(new Wall(pWall));
    }

     /**
     * Adds a hallway to the specified game room.
     *
     * @param pRoomState the game room state to add the hallway to
     * @param x          the x-coordinate of the hallway
     * @param y          the y-coordinate of the hallway
     */
    public static void addHallway(GameStates.GameplayStates pRoomState, int x, int y)
    {
        WallFactory.addWall(pRoomState,
            new Rectangle(x, 0, 100, y));

        WallFactory.addWall(pRoomState,
                new Rectangle(x, y+100, 100, Constants.CANVAS_HEIGHT-300));
        WallFactory.addWall(pRoomState,
                new Rectangle(x+100, 0, Constants.CANVAS_WIDTH-100, y-100));
        WallFactory.addWall(pRoomState,
                new Rectangle(x+100, y+200, Constants.CANVAS_WIDTH-600, Constants.CANVAS_HEIGHT-300));
        WallFactory.addWall(pRoomState,
                new Rectangle(Constants.CANVAS_WIDTH-600+200+x, y+200, 500, Constants.CANVAS_HEIGHT-300));

        WallFactory.addWall(pRoomState,
                new Rectangle(x+Constants.CANVAS_WIDTH-100, y-200, 100, 200));
        WallFactory.addWall(pRoomState,
                new Rectangle(x+Constants.CANVAS_WIDTH-100, y+100, 100, Constants.CANVAS_HEIGHT-300));
    }

    /**
     * Returns the array of walls for the specified game room.
     *
     * @param pRoomState the game room state
     * @return the array of walls for the specified game room
     */
    public static ArrayList<Wall> getRoomWallArray(GameStates.GameplayStates pRoomState) {
        return mRoomStateToWallArray.get(pRoomState);
    }
}
