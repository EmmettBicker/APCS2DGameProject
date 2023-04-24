package game;

public class GameStates
{
    public enum States {
        TITLE_SCREEN, GAME
    }
    public static Board board = null;

    public static States mState = States.TITLE_SCREEN;

    public static States getState()
    {
        return mState;
    }

    public static void setState(States s)
    {
        if (board == null) board = Game.getBoard();
        board.exitingState(s);
        mState = s;
    }

    
}
