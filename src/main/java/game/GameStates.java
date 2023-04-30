package game;

public class GameStates
{
    public enum States {
        TITLE_SCREEN, SCROLLING_TEXT, GAMEPLAY, FINAL_BOSS, END_SCREEN, NPC
    }

    public enum GameplayStates {
        NOT_IN_GAME, ROOM_1, ROOM_2//, ROOM_3, ROOM_4
    }


    public static Board board = null;

    public static States mState = States.TITLE_SCREEN;
    public static States mPreviousState = States.TITLE_SCREEN; 
    
    public static GameplayStates mGameplayState = GameplayStates.NOT_IN_GAME;
    public static GameplayStates mPreviousGameplayState = GameplayStates.NOT_IN_GAME; 
    

    public static States getState()
    {
        return mState;
    }

    public static States getPreviousState()
    {
        return mPreviousState;
    }

    public static GameplayStates getGameplayState()
    {
        return mGameplayState;
    }

    public static GameplayStates getPreviousGameplayState()
    {
        return mPreviousGameplayState;
    }

    public static void setState(States s)
    {
        
        if (board == null) board = Game.getBoard();
        mPreviousState = mState;
        board.exitingState(s);
        mState = s;
    }

    public static void setGameplayState(GameplayStates s)
    {
        
        if (board == null) board = Game.getBoard();
        mPreviousGameplayState = mGameplayState;
        // board.exitingState(s);
        mGameplayState = s;
    }

    
}
