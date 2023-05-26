package game;

import java.awt.Point;
import java.util.Hashtable;

public class GameStates {
    public enum States {
        TITLE_SCREEN, SCROLLING_TEXT, GAMEPLAY, FINAL_BOSS, END_SCREEN, SHOP, DEATH, VICTORY
    }

    public enum GameplayStates {
        NOT_IN_GAME, ROOM_1, ROOM_2, ROOM_3, ROOM_4, ROOM_5

    }

    public static Board board = null;

    public static States mState = States.TITLE_SCREEN;
    public static States mPreviousState = States.TITLE_SCREEN;

    public static GameplayStates mGameplayState = GameplayStates.NOT_IN_GAME;
    public static GameplayStates mPreviousGameplayState = GameplayStates.NOT_IN_GAME;

    private static Hashtable<GameplayStates, Point> mPlayerLastPositionInRoom;

    static {
        mPlayerLastPositionInRoom = new Hashtable<GameplayStates, Point>();

        for (GameplayStates room : GameplayStates.values()) {
            mPlayerLastPositionInRoom.put(room, new Point());
        }

    }

    public static States getState() {
        return mState;
    }

    public static States getPreviousState() {
        return mPreviousState;
    }

    public static GameplayStates getGameplayState() {
        return mGameplayState;
    }

    public static GameplayStates getPreviousGameplayState() {
        return mPreviousGameplayState;
    }

    public static void setState(States s) {

        if (board == null)
            board = Game.getBoard();
        mPreviousState = mState;
        board.exitingState(s);
        mState = s;
    }

    public static void setGameplayState(GameplayStates s) {

        if (board == null)
            board = Game.getBoard();
        mPreviousGameplayState = mGameplayState;
        // board.exitingState(s);
        mGameplayState = s;
    }

}
