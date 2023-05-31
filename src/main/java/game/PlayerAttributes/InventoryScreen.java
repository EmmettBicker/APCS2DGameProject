package game.PlayerAttributes;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.util.ArrayList;
import game.npcs.TextBox;
import game.Constants;
import game.Game;
import game.PlayerAttributes.InventoryManager.Item;
import game.interfaces.BasicSprite;

/**
 * The InventoryScreen class represents the inventory screen in the game.
 * It implements the BasicSprite interface.
 */
public class InventoryScreen implements BasicSprite {

    /**
     * Enumeration representing the possible text states of the inventory screen.
     */
    public enum TextState {
        INVISIBLE, ENTERING, DISPLAYED
    };

    private Color mTransparentishWhite;
    private TextState mTextState;
    private BufferedImage mHeadImage;
    private int mTextIndex;
    private int mWordIndex;
    private long timeEnteredState;
    private ArrayList<String> mDesiredText;
    private boolean doneWithSentence;

    /**
     * Constructs an InventoryScreen object.
     */
    public InventoryScreen() {
        mTextState = TextState.INVISIBLE;
        timeEnteredState = System.currentTimeMillis();
        mDesiredText = new ArrayList<String>();
        doneWithSentence = false;
        mTransparentishWhite = new Color(255, 255, 255, 70);
    }

    /**
     * Sets the state of the inventory screen.
     *
     * @param pTextState the text state to set
     */
    public void setState(TextState pTextState) {
        timeEnteredState = System.currentTimeMillis();
        mTextState = pTextState;
        mTextIndex = 0;
    }

    /**
     * Sets the desired text to display on the inventory screen.
     *
     * @param pDesiredText the desired text to display
     */
    public void setText(ArrayList<String> pDesiredText) {
        mDesiredText = pDesiredText;
    }

    /**
     * Sets the head image to display on the inventory screen.
     *
     * @param pHeadImage the head image to display
     */
    public void setHeadImage(BufferedImage pHeadImage) {
        mHeadImage = pHeadImage;
    }

    /**
     * Returns the current text state of the inventory screen.
     *
     * @return the text state of the inventory screen
     */
    public TextState getTextState() {
        return mTextState;
    }

    /**
     * Draws the inventory screen on the given Graphics object.
     *
     * @param g the Graphics object to draw on
     * @param observer the ImageObserver object
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        int textPad = 50;
        int horizontalWidth = 1000;
        int verticalHeight = 150;
        int smallestDimension = Math.min(verticalHeight, horizontalWidth);
        double speedDenmonimator = 500.0;
        int arbiraryDownShift = -200;
        int facePad = 10;

        int tempHorizontalWidth = (int) (horizontalWidth * (System.currentTimeMillis() - timeEnteredState)
                / speedDenmonimator);
        int tempVerticalHeight = (int) (verticalHeight * (System.currentTimeMillis() - timeEnteredState)
                / speedDenmonimator);

        boolean isTalking = Game.getTextBox().getTextState() != TextBox.TextState.INVISIBLE;
        
        if (!isTalking) {
            if (mTextState == TextState.INVISIBLE) {
                Game.getPlayer().allowMovement();
            } else {
                Game.getPlayer().lockMovement();
            }
        }
        if (mTextState == TextState.ENTERING) {
            doneWithSentence = false;
            mWordIndex = 0;
            g.setColor(mTransparentishWhite);
            g.fillRect((Constants.CANVAS_WIDTH - tempHorizontalWidth) / 2,
                    (Constants.CANVAS_HEIGHT - tempVerticalHeight) / 2 + arbiraryDownShift, tempHorizontalWidth,
                    tempVerticalHeight);
            if (tempHorizontalWidth > horizontalWidth) {
                setState(TextState.DISPLAYED);
            }
        } else if (mTextState == TextState.DISPLAYED) {
   
            int textBoxX = (Constants.CANVAS_WIDTH - horizontalWidth) / 2;
            int textBoxY = (Constants.CANVAS_HEIGHT - verticalHeight) / 2 + arbiraryDownShift;
            Rectangle head = new Rectangle(textBoxX + facePad, textBoxY + facePad, smallestDimension - facePad * 2,
                    smallestDimension - facePad * 2);

            g.setColor(mTransparentishWhite);
            g.fillRect(textBoxX, textBoxY, horizontalWidth, verticalHeight);
            g.setColor(Color.BLACK);
            g.drawImage(
                    mHeadImage,
                    head.x,
                    head.y,
                    head.width,
                    head.height,
                    observer);
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.setColor(Color.GREEN);
            int increment = 1;
            g.drawString("Sprite: " + InventoryManager.getItemCount(Item.kSprite), textBoxX , textBoxY + (25 * increment++));
            g.drawString("Thwackers: " + InventoryManager.getItemCount(Item.kThwacker), textBoxX , textBoxY + (25 * increment++));
            g.drawString("Gears: " + InventoryManager.getItemCount(Item.kGear), textBoxX ,  textBoxY + (25 * increment++));
            g.drawString("Bolts: " + InventoryManager.getItemCount(Item.kBolt), textBoxX ,  textBoxY + (25 * increment++));

        }
    }

    /**
     * Handles the key pressed events for the inventory screen.
     *
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_I && mTextState == TextState.DISPLAYED) {
            setState(TextState.INVISIBLE);
        }

    }

    @Override
    public void tick() {

    }

    @Override
    public void onDelete() {

    }
}
