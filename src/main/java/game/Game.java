package game;

import javax.swing.*;

import game.PlayerAttributes.InventoryScreen;
import game.npcs.TextBox;

import java.awt.Point;
import java.awt.Rectangle;

public class Game {
    private static Board board;

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

    public static Board getBoard() {
        return board;
    }

    public static Point getPlayerPosition() {
        return board.getPlayerPosition();
    }

    public static Rectangle getPlayerHitbox() {
        return board.getPlayerHitbox();
    }

    public static void setPlayerPosition(Point pPos) {
        board.setPlayerPosition(pPos);
    }

    public static boolean getHasChangedRoomAlready() {
        return board.getHasChangedRoomAlready();
    }

    public static void setHasChangedRoomAlready(boolean b) {
        board.setHasChangedRoomAlready(b);
    }

    public static Player getPlayer() {
        return board.getPlayer();
    }

    public static TextBox getTextBox() {
        return board.getTextBox();
    }

    public static InventoryScreen getInventoryScreen() {
        return board.getInventoryScreen();
    }

    public static Weapon getWeapon() {
        return board.getWeapon();
    }


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