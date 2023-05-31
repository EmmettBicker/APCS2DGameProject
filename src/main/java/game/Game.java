package game;

import javax.swing.*;

import game.PlayerAttributes.InventoryScreen;
import game.npcs.TextBox;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * The main class representing the game.
 */
public class Game {
    private static Board board;

    /**
     * Initializes the game window.
     */
    private static void initWindow() {
        // Code heavily inspired by tutorial on 2D Java programming
        JFrame window = new JFrame("Cave Story");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Board();
        window.add(board);
        window.addKeyListener(board);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Returns the game board.
     *
     * @return the game board
     */
    public static Board getBoard() {
        return board;
    }

    /**
     * Returns the position of the player.
     *
     * @return the position of the player
     */
    public static Point getPlayerPosition() {
        return board.getPlayerPosition();
    }

    /**
     * Returns the hitbox of the player.
     *
     * @return the hitbox of the player
     */
    public static Rectangle getPlayerHitbox() {
        return board.getPlayerHitbox();
    }

    /**
     * Sets the position of the player.
     *
     * @param pPos the new position of the player
     */
    public static void setPlayerPosition(Point pPos) {
        board.setPlayerPosition(pPos);
    }

    /**
     * Checks if the room has already been changed.
     *
     * @return true if the room has already been changed, false otherwise
     */
    public static boolean getHasChangedRoomAlready() {
        return board.getHasChangedRoomAlready();
    }

    /**
     * Sets whether the room has already been changed.
     *
     * @param b true if the room has already been changed, false otherwise
     */
    public static void setHasChangedRoomAlready(boolean b) {
        board.setHasChangedRoomAlready(b);
    }

    /**
     * Returns the player instance.
     *
     * @return the player instance
     */
    public static Player getPlayer() {
        return board.getPlayer();
    }

    /**
     * Returns the text box instance.
     *
     * @return the text box instance
     */
    public static TextBox getTextBox() {
        return board.getTextBox();
    }

    /**
     * Returns the inventory screen instance.
     *
     * @return the inventory screen instance
     */
    public static InventoryScreen getInventoryScreen() {
        return board.getInventoryScreen();
    }

    /**
     * Returns the weapon instance.
     *
     * @return the weapon instance
     */
    public static Weapon getWeapon() {
        return board.getWeapon();
    }

    /**
     * The entry point of the game.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Code heavily inspired by tutorial on 2D Java programming
        SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    initWindow();
                }
            }
        );
    }
}
