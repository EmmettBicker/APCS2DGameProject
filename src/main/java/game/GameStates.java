package game;

public class GameStates
{
    public enum States {
        TITLE_SCREEN, SCROLLING_TEXT, GAME, 
    }


    public static Board board = null;

    public static States mPreviousState = States.TITLE_SCREEN; 
    public static States mState = States.TITLE_SCREEN;

    public static States getState()
    {
        return mState;
    }

    public static States getPreviousState()
    {
        return mPreviousState;
    }

    public static void setState(States s)
    {
        
        if (board == null) board = Game.getBoard();
        mPreviousState = mState;
        board.exitingState(s);
        mState = s;
    }

    
}
