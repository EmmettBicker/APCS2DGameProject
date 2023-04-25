package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import game.interfaces.BasicSprite;
import game.titleScreen.*;
import game.scrollingText.*;
import game.screen1.*;

public class Board extends JPanel implements ActionListener, KeyListener {

    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // controls the size of the board
    public static final int TILE_SIZE = 10;
    public static final int ROWS = 720/TILE_SIZE;
    public static final int COLUMNS = 1280/TILE_SIZE;
    private static final long serialVersionUID = 490905409104883233L;

    
    
    // keep a reference to the timer object that triggers actionPerf ormed() in
    // case we need access to it in another method
    private Timer mTimer;
    // objects that appear on the game board
    private TitleScreen mTitleScreen;
    private SpaceText mSpaceText;
    private GeneralMusic mTitleMusic;

    private Player mPlayer;
    private ScreenOneBg mScreenOneBg;
    
    private Door mDoor;
    
    private BeginningText mBeginningText;
    private  TextBackground mTextBackground;

    private ArrayList<BasicSprite> mTitleScreenSpriteArray;
    private ArrayList<BasicSprite> mGameScreenSpriteArray;
    private ArrayList<BasicSprite> mBeginningTextArray;

    private GameStates.States mState;
    

    public Board() 
    {
        // set the game board size
        mState = GameStates.getState();
        setPreferredSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
        // set the game board background color
        setBackground(new Color(232, 232, 232));

        // initialize the game state
        mTitleScreen = new TitleScreen();
        mSpaceText = new SpaceText();
        mTitleMusic = new GeneralMusic("src/main/resources/music/titleScreenChopin.wav");
        

        mPlayer = new Player();
        mScreenOneBg = new ScreenOneBg();
        
        mDoor = new Door();

        mBeginningText = new BeginningText();
        mTextBackground = new TextBackground();

        // this timer will call the actionPerformed() method every DELAY ms
        mTimer = new Timer(DELAY, this);
        mTimer.start();

        mTitleScreenSpriteArray = new ArrayList<BasicSprite>();
        mTitleScreenSpriteArray.add(mTitleScreen);
        mTitleScreenSpriteArray.add(mSpaceText);
        // mTitleScreenSpriteArray.add(mTitleMusic);

        mGameScreenSpriteArray = new ArrayList<BasicSprite>();
        
        mGameScreenSpriteArray.add(mScreenOneBg);
        mGameScreenSpriteArray.add(mPlayer);
        mGameScreenSpriteArray.add(mDoor);

        mBeginningTextArray.add(mBeginningText);
        mBeginningTextArray.add(mTextBackground);



    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        mState = GameStates.getState();
        switch (mState)
        {
            case TITLE_SCREEN : {
                // this method is called by the timer every DELAY ms.
                // use this space to update the state of your game or animation
                // before the graphics are redrawn.
                for (BasicSprite sprite : mTitleScreenSpriteArray)
                {
                    sprite.tick();
                }
                break;
            }

            case GAMEPLAY : {
                // this method is called by the timer every DELAY ms.
                // use this space to update the state of your game or animation
                // before the graphics are redrawn.

                // prevent the player from disappearing off the board
                for (BasicSprite sprite : mGameScreenSpriteArray)
                {
                    sprite.tick();
                }
       
            }
            
        }
        // calling repaint() will trigger paintComponent() to run again,
                // which will refresh/redraw the graphics.
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        // when calling g.drawImage() we can use "this" for the ImageObserver 
        // because Component implements the ImageObserver interface, and JPanel 
        // extends from Component. So "this" Board instance, as a Component, can 
        // react to imageUpdate() events triggered by g.drawImage()

        // draw our graphics.
        drawBackground(g);
        switch (mState)
        {
            case TITLE_SCREEN:
            {
                for (BasicSprite sprite : mTitleScreenSpriteArray)
                {
                    sprite.draw(g, this);
                }
                break;
            }

            case GAMEPLAY:
            {
                for (BasicSprite sprite : mGameScreenSpriteArray)
                {
                    sprite.draw(g, this);
                }
                break;
            }
        }


        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        // react to key down events
        switch (mState)
        {
            case TITLE_SCREEN:
            {
                for (BasicSprite sprite : mTitleScreenSpriteArray)
                {
                    sprite.keyPressed(e);
                }
            }
            case GAMEPLAY:
            {
                for (BasicSprite sprite : mGameScreenSpriteArray)
                {
                    sprite.keyPressed(e);
                }
            }
        }
        
        
    }

    public void exitingState(GameStates.States state)
    {
        System.out.println(mState);
        switch(mState)
        {
            case TITLE_SCREEN:
            {
                for (BasicSprite sprite : mTitleScreenSpriteArray)
                {
                    sprite.onDelete();
                }
                break;
            }
            case GAMEPLAY:
            {
                for (BasicSprite sprite : mGameScreenSpriteArray)
                {
                    sprite.onDelete();
                }
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
        mPlayer.keyReleased(e);
    }

    private void drawBackground(Graphics g) {
        // draw a checkered background
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                // only color every other tile
                if ((row + col) % 2 == 1) {
                    // draw a square tile at the current row/column position
                    g.fillRect(
                        col * TILE_SIZE, 
                        row * TILE_SIZE, 
                        TILE_SIZE, 
                        TILE_SIZE
                    );
                }
            }    
        }
    }

    /*   MISC GETTER METHODS    */
    public Point getPlayerPosition()
    {
        return mPlayer.getPos();
    }
















    private void drawScore(Graphics g) {
        // set the text to be displayed
        String text = "$" + mPlayer.getScore();
        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
            RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
        // draw the score in the bottom center of the screen
        // https://stackoverflow.com/a/27740330/4655368
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        // the text will be contained within this rectangle.
        // here I've sized it to be the entire bottom row of board tiles
        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        // determine the x coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // determine the y coordinate for the text
        // (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // draw the string
        g2d.drawString(text, x, y);
    }
    
    

 

}