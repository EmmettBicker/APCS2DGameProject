package game.npcs;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Constants;
import game.Game;
import game.interfaces.BasicSprite;

/**
 * The TextBox class represents a text box that displays dialogue in the game.
 * It implements the BasicSprite interface.
 */
public class TextBox implements BasicSprite {

    /**
     * The TextState enum represents the different states of the text box.
     */
    public enum TextState {
        INVISIBLE, ENTERING, DISPLAYED
    };

    private TextState mTextState;
    private BufferedImage mHeadImage;
    private int mTextIndex;
    private int mWordIndex;
    private long timeEnteredState;
    private ArrayList<String> mDesiredText;
    private boolean doneWithSentence;

    /**
     * Constructs a TextBox object.
     */
    public TextBox() {
        mTextState = TextState.INVISIBLE;
        timeEnteredState = System.currentTimeMillis();
        mDesiredText = new ArrayList<String>();
        doneWithSentence = false;
    }

    /**
     * Sets the state of the text box.
     *
     * @param pTextState the text state to set
     */
    public void setState(TextState pTextState) {
        timeEnteredState = System.currentTimeMillis();
        mTextState = pTextState;
        mTextIndex = 0;
    }

    /**
     * Sets the text to be displayed in the text box.
     *
     * @param pDesiredText the desired text to display
     */
    public void setText(ArrayList<String> pDesiredText) {
        mDesiredText = pDesiredText;
    }

    /**
     * Sets the head image to be displayed in the text box.
     *
     * @param pHeadImage the head image to set
     */
    public void setHeadImage(BufferedImage pHeadImage) {
        mHeadImage = pHeadImage;
    }

    /**
     * Returns the current text state of the text box.
     *
     * @return the text state
     */
    public TextState getTextState() {
        return mTextState;
    }

    /**
     * Draws the text box on the graphics context.
     *
     * @param g         the graphics context
     * @param observer  the image observer
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        int textPad = 50;
        int horizontalWidth = 1000;
        int verticalHeight = 150;
        int smallestDimension = Math.min(verticalHeight, horizontalWidth);
        double speedDenmonimator = 500.0;
        int arbiraryDownShift = 200;
        int facePad = 10;

        int tempHorizontalWidth = (int) (horizontalWidth * (System.currentTimeMillis() - timeEnteredState)
                / speedDenmonimator);
        int tempVerticalHeight = (int) (verticalHeight * (System.currentTimeMillis() - timeEnteredState)
                / speedDenmonimator);

        if (mTextState == TextState.INVISIBLE) {
            Game.getPlayer().allowMovement();
        } else {
            Game.getPlayer().lockMovement();
        }
        if (mTextState == TextState.ENTERING) {
            doneWithSentence = false;

            mWordIndex = 0;
            g.setColor(Color.WHITE);
            g.fillRect((Constants.CANVAS_WIDTH - tempHorizontalWidth) / 2,
                    (Constants.CANVAS_HEIGHT - tempVerticalHeight) / 2 + arbiraryDownShift, tempHorizontalWidth,
                    tempVerticalHeight);
            if (tempHorizontalWidth > horizontalWidth) {
                setState(TextState.DISPLAYED);
            }
        } else if (mTextState == TextState.DISPLAYED) {
            mWordIndex += 3;

            String wordsToSay = mDesiredText.get(mTextIndex).substring(0,
                    Math.min(mWordIndex, mDesiredText.get(mTextIndex).length()));
            doneWithSentence = mWordIndex >= mDesiredText.get(mTextIndex).length();

            int textBoxX = (Constants.CANVAS_WIDTH - horizontalWidth) / 2;
            int textBoxY = (Constants.CANVAS_HEIGHT - verticalHeight) / 2 + arbiraryDownShift;
            Rectangle head = new Rectangle(textBoxX + facePad, textBoxY + facePad, smallestDimension - facePad * 2,
                    smallestDimension - facePad * 2);

            g.setColor(Color.WHITE);
            g.fillRect(textBoxX, textBoxY, horizontalWidth, verticalHeight);
            g.setColor(Color.BLACK);
            g.drawImage(
                    mHeadImage,
                    head.x,
                    head.y,
                    head.width,
                    head.height,
                    observer);
            g.drawString(wordsToSay, textBoxX + facePad + smallestDimension, textBoxY + textPad);
        }
    }

    /**
     * Handles the key pressed event.
     *
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && doneWithSentence) {
            mTextIndex += 1;
            mWordIndex = 0;
            if (mTextIndex == mDesiredText.size()) {
                setState(TextState.INVISIBLE);
            }
        }
    }

    /**
     * Updates the text box.
     */
    @Override
    public void tick() {
        // No update logic for the text box
    }

    /**
     * Performs any necessary cleanup when the text box is deleted.
     */
    @Override
    public void onDelete() {
        // No cleanup logic for the text box
    }
}
