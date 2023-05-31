package game;

import java.awt.Point;
import java.util.Hashtable;

/**
 * A class representing the game states and gameplay states in the game.
 */
public class GameStates {
    /**
     * An enumeration of the different game states.
     */
    public enum States {
        TITLE_SCREEN, SCROLLING_TEXT, GAMEPLAY, FINAL_BOSS, END_SCREEN, SHOP, DEATH, VICTORY
    }

    /**
     * An enumeration of the different gameplay states.
     */
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

    /**
     * Returns the current game state.
     *
     * @return the current game state
     */
    public static States getState() {
        return mState;
    }

    /**
     * Returns the previous game state.
     *
     * @return the previous game state
     */
    public static States getPreviousState() {
        return mPreviousState;
    }

    /**
     * Returns the current gameplay state.
     *
     * @return the current gameplay state
     */
    public static GameplayStates getGameplayState() {
        return mGameplayState;
    }

    /**
     * Returns the previous gameplay state.
     *
     * @return the previous gameplay state
     */
    public static GameplayStates getPreviousGameplayState() {
        return mPreviousGameplayState;
    }

    /**
     * Sets the game state.
     *
     * @param s the new game state
     */
    public static void setState(States s) {
        if (board == null)
            board = Game.getBoard();
        mPreviousState = mState;
        board.exitingState(s);
        mState = s;
    }

    /**
     * Sets the gameplay state.
     *
     * @param s the new gameplay state
     */
    public static void setGameplayState(GameplayStates s) {
        if (board == null)
            board = Game.getBoard();
        mPreviousGameplayState = mGameplayState;
        // board.exitingState(s);
        mGameplayState = s;
    }
}
