package game;

import javax.swing.*;
import java.awt.Point;
import java.awt.Rectangle;

public class Game {
    private static Board board;

    private static void initWindow() {
        // create a window frame and set the title in the toolbar
        JFrame window = new JFrame("Cave Story");
        // when we close the window, stop the app
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the jpanel to draw on.
        // this also initializes the game loop
        board = new Board();
        // add the jpanel to the window
        window.add(board);
        // pass keyboard inputs to the jpanel
        window.addKeyListener(board);
        
        // don't allow the user to resize the window
        window.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        window.pack();
        // open window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);
    }

    public static Board getBoard()
    {
        return board;
    }
    
    public static Point getPlayerPosition()
    {
        return board.getPlayerPosition();
    }

    public static Rectangle getPlayerHitbox()
    {
        return board.getPlayerHitbox();
    }

    public static void setPlayerPosition(Point pPos)
    {
        board.setPlayerPosition(pPos);
    }

    public static boolean getHasChangedRoomAlready()
    {
        return board.getHasChangedRoomAlready();
    }

    public static void setHasChangedRoomAlready(boolean b)
    {
        board.setHasChangedRoomAlready(b);
    }

    public static void main(String[] args) {
        // invokeLater() is used here to prevent our graphics processing from
        // blocking the GUI. https://stackoverflow.com/a/22534931/4655368
        // this is a lot of boilerplate code that you shouldn't be too concerned about.
        // just know that when main runs it will call initWindow() once.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow();
            }
        });
    }
}