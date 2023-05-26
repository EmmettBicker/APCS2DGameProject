package game.enemies;

import game.GameStates;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.Point;

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

    public static void addEnemy(GameStates.GameplayStates pRoomState, Point pEnemyPos) {
        mRoomStateToEnemyArray.get(pRoomState).add(new Enemy(pEnemyPos));
     
    }

    public static ArrayList<Enemy> getRoomEnemyArray(GameStates.GameplayStates pRoomState) {
        return mRoomStateToEnemyArray.get(pRoomState);
    }
}
