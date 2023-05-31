package game.PlayerAttributes;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import game.npcs.TextBox;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import game.Game;
import game.interfaces.BasicSprite;

/**
 * The Inventory class represents the inventory of the player in the game.
 * It implements the BasicSprite interface.
 */
public class Inventory implements BasicSprite {
    private BufferedImage inventoryImage;
    private Rectangle inventoryLocation;
    private boolean mIsScreenOpen;

    /**
     * Constructs a new Inventory object.
     * Loads the inventory image and sets the initial inventory location.
     */
    public Inventory() {
        loadImage();
        inventoryLocation = new Rectangle(25, 25, 100, 100);
    }

    private void loadImage() {
        try {
            inventoryImage = ImageIO.read(new File("src/main/resources/images/speedracerbag.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file bag: " + exc.getMessage());
        }
    }

    /**
     * Draws the inventory image on the screen.
     *
     * @param g        the Graphics object to draw on
     * @param observer the ImageObserver to observe the drawing process
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(inventoryImage, inventoryLocation.x, inventoryLocation.y, inventoryLocation.width,
                inventoryLocation.height, observer);
    }

    /**
     * Handles the key events for the inventory.
     * Opens the inventory screen when the "I" key is pressed,
     * if the inventory screen and the text box are not already visible.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_I) {
            mIsScreenOpen = Game.getInventoryScreen().getTextState() != InventoryScreen.TextState.INVISIBLE;
            boolean isTalking = Game.getTextBox().getTextState() != TextBox.TextState.INVISIBLE;
            if (!mIsScreenOpen && !isTalking) {
                Game.getInventoryScreen().setState(InventoryScreen.TextState.ENTERING);
                mIsScreenOpen = true;
            }
        }
    }

    /**
     * Updates the inventory logic.
     * This method is currently empty and does not perform any updates.
     */
    @Override
    public void tick() {
        // Update logic if needed
    }

    /**
     * Performs cleanup operations when the inventory is deleted.
     * This method is currently empty and does not perform any cleanup operations.
     */
    @Override
    public void onDelete() {
        // Handle deletion if needed
    }
}
