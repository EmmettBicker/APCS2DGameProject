package game;

import java.util.Hashtable;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import game.interfaces.BasicSprite;
import game.npcs.MessageFactory;
import game.npcs.NPC;
import game.npcs.TextBox;
import game.npcs.NPC.PresetNPC;
import game.GameStates.GameplayStates;
import game.PlayerAttributes.HealthBar;
import game.PlayerAttributes.Inventory;
import game.PlayerAttributes.InventoryScreen;
import game.endgame.EndgameDoor;
import game.enemies.Enemy;
import game.enemies.EnemyDrop;
import game.enemies.EnemyDropsFactory;
import game.generalSprites.BasicSpriteWithImage;
import game.generalSprites.GeneralDoor;
import game.generalSprites.GeneralImage;
import game.generalSprites.GeneralMusic;
import game.interfaces.BasicRoomSprite;
import game.titleScreen.*;
import game.wallFactory.FakeWall;
import game.wallFactory.Wall;
import game.wallFactory.WallFactory;
import game.scrollingText.*;
import game.shop.ShopDoor;
import game.shop.SpriteCostSelector;
import game.shop.SpriteDealer;
import game.shop.SpriteDealerItem;
import game.enemies.EnemyFactory;

public class Board extends JPanel implements ActionListener, KeyListener {

    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // controls the size of the board
    public static final int TILE_SIZE = 10;
    public static final int ROWS = 720 / TILE_SIZE;
    public static final int COLUMNS = 1280 / TILE_SIZE;

    private GameStates.States[] mAllStatesArray;
    private GameStates.GameplayStates[] mAllRoomStatesArray;
    private Hashtable<GameStates.States, ArrayList<BasicSprite>> mStatesToRespectiveArray;
    private Hashtable<GameStates.GameplayStates, ArrayList<BasicRoomSprite>> mGameplayStatesToRespectiveArray;

    private Timer mTimer;
    // objects that appear on the game board
    private TitleScreen mTitleScreen;
    private SpaceText mSpaceText;
    private GeneralMusic mTitleMusic;

    private Player mPlayer;
    private Weapon mWeapon;
    private Projectile mProjectile;
    private HealthBar mHealthBar;
    private TextBox mTextBox;

    private InventoryScreen mInventoryScreen;
    private SpriteDealer mSpriteDealer;
    // ROOM 1
    private GenericBackground mScreenOneBg;
    private GeneralDoor mRoom1toRoom2Door;
    private GeneralDoor mRoom1toRoom3Door;

    // ROOM 2
    private GeneralDoor mRoom2toRoom1Door;

    private BeginningText mBeginningText;
    private TextBackground mTextBackground;
    // ROOM 3
    private GeneralDoor mRoom3toRoom1Door;

    private ArrayList<BasicSprite> mTitleScreenSpriteArray;
    private ArrayList<BasicSprite> mGameScreenSpriteArray;
    private ArrayList<BasicSprite> mBeginningTextArray;

    private ArrayList<BasicRoomSprite> mNotPlayingSpriteArray; // has nothing
    private ArrayList<BasicRoomSprite> mRoomOneSpriteArray;
    private ArrayList<BasicRoomSprite> mRoomTwoSpriteArray;
    private ArrayList<BasicRoomSprite> mRoomThreeSpriteArray;

    private GameStates.States mState;
    private GameStates.GameplayStates mGameplayState;

    public Board() {
        // set the game board size
        mState = GameStates.getState();
        setPreferredSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
        // set the game board background color
        setBackground(new Color(232, 232, 232));

        mAllStatesArray = GameStates.States.values();
        mAllRoomStatesArray = GameStates.GameplayStates.values();

        mStatesToRespectiveArray = new Hashtable<GameStates.States, ArrayList<BasicSprite>>();
        mGameplayStatesToRespectiveArray = new Hashtable<GameStates.GameplayStates, ArrayList<BasicRoomSprite>>();

        // initialize the game state
        mTitleScreen = new TitleScreen();
        mSpaceText = new SpaceText();
        mTitleMusic = new GeneralMusic("src/main/resources/music/titleScreenChopin.wav");

        // ROOM 1
        mPlayer = new Player();
        mWeapon = new Weapon();
        mProjectile = new Projectile();
        mTextBox = new TextBox();

        mHealthBar = new HealthBar();

        mInventoryScreen = new InventoryScreen();
        

        Point room1toRoom2DoorPos = new Point(0, 200);
        Point room2toRoom1DoorPos = new Point(Constants.CANVAS_WIDTH - Constants.DOOR_WIDTH, 200);
        Point room1toRoom3DoorPos = new Point(Constants.CANVAS_WIDTH - Constants.DOOR_WIDTH, 200);
        Point room3toRoom1DoorPos = new Point(0, 200);
        Point room3toRoom4DoorPos = new Point(Constants.CANVAS_WIDTH - Constants.DOOR_WIDTH, 200);
        Point room4toRoom3DoorPos = new Point(0, 300);
        mRoom1toRoom2Door = new GeneralDoor(GameStates.GameplayStates.ROOM_2, room2toRoom1DoorPos,
                new Rectangle(room1toRoom2DoorPos.x, room1toRoom2DoorPos.y, Constants.DOOR_WIDTH,
                        Constants.DOOR_HEIGHT));
        mRoom1toRoom3Door = new GeneralDoor(GameStates.GameplayStates.ROOM_3, room3toRoom1DoorPos,
                new Rectangle(room2toRoom1DoorPos.x, room2toRoom1DoorPos.y, Constants.DOOR_WIDTH,
                        Constants.DOOR_HEIGHT));

        // ROOM 2

        mRoom2toRoom1Door = new GeneralDoor(GameStates.GameplayStates.ROOM_1, room1toRoom2DoorPos,
                new Rectangle(room2toRoom1DoorPos.x, room2toRoom1DoorPos.y, Constants.DOOR_WIDTH,
                        Constants.DOOR_HEIGHT));

        mBeginningText = new BeginningText();
        mTextBackground = new TextBackground();

        // ROOM 3

        mRoom3toRoom1Door = new GeneralDoor(GameStates.GameplayStates.ROOM_1, room1toRoom3DoorPos,
                new Rectangle(room3toRoom1DoorPos.x, room3toRoom1DoorPos.y, Constants.DOOR_WIDTH,
                        Constants.DOOR_HEIGHT));
        GeneralDoor room3toRoom4Door = new GeneralDoor(GameStates.GameplayStates.ROOM_4, room4toRoom3DoorPos,
                new Rectangle(room3toRoom4DoorPos.x, room3toRoom4DoorPos.y, Constants.DOOR_WIDTH,
                        Constants.DOOR_HEIGHT));
        GeneralDoor room4toRoom3Door = new GeneralDoor(GameStates.GameplayStates.ROOM_3, room3toRoom4DoorPos,
                new Rectangle(room4toRoom3DoorPos.x, room4toRoom3DoorPos.y, Constants.DOOR_WIDTH,
                        Constants.DOOR_HEIGHT));

        // this timer will call the actionPerformed() method every DELAY ms
        mTimer = new Timer(DELAY, this);
        mTimer.start();

        mTitleScreenSpriteArray = new ArrayList<BasicSprite>();
        
        mTitleScreenSpriteArray.add(mTitleScreen);
        mTitleScreenSpriteArray.add(mSpaceText);
        // mTitleScreenSpriteArray.add(mTitleMusic);

        mBeginningTextArray = new ArrayList<BasicSprite>();

        mBeginningTextArray.add(mTextBackground);
        mBeginningTextArray.add(mBeginningText);

        mGameScreenSpriteArray = new ArrayList<BasicSprite>();

        // ROOM SPRITES
        // ROOM 1
        mNotPlayingSpriteArray = new ArrayList<BasicRoomSprite>();
        mRoomOneSpriteArray = new ArrayList<BasicRoomSprite>();

        mRoomOneSpriteArray.add(new GenericBackground("screen1"));
        mRoomOneSpriteArray.add(mRoom1toRoom3Door);
        mRoomOneSpriteArray.add(mRoom1toRoom2Door);
        mGameScreenSpriteArray.add(mPlayer);
        mGameScreenSpriteArray.add(mWeapon);
        mGameScreenSpriteArray.add(mProjectile);
        mGameScreenSpriteArray.add(mHealthBar);
        mGameScreenSpriteArray.add(mTextBox);
        mGameScreenSpriteArray.add(new Inventory());
        mGameScreenSpriteArray.add(mInventoryScreen);
        
        mRoomOneSpriteArray.add(new NPC(
                new Rectangle(Constants.CANVAS_WIDTH / 2 - 150, Constants.CANVAS_HEIGHT / 2,
                        Constants.NPCS.ADAM_NPC_WIDTH, Constants.NPCS.ADAM_NPC_HEIGHT),
                PresetNPC.Adam, MessageFactory.getRoomOneAdamMessage()));
        
        
        // ROOM 2
        mRoomTwoSpriteArray = new ArrayList<BasicRoomSprite>(); 
        mRoomTwoSpriteArray.add(new GenericBackground("screen2"));
        mRoomTwoSpriteArray.add(mRoom2toRoom1Door);
        mRoomTwoSpriteArray.add(new GeneralImage("generalSprites/chopSaw.png", new Rectangle(450,100,350,500)));
        Point shopDoorLocation = new Point(0,Constants.CANVAS_HEIGHT-100-Constants.DOOR_HEIGHT);
        mRoomTwoSpriteArray.add(new ShopDoor(new Rectangle(shopDoorLocation.x, shopDoorLocation.y, Constants.DOOR_WIDTH,
                Constants.DOOR_HEIGHT)));
        mRoomTwoSpriteArray.add(new NPC(
            new Rectangle(Constants.CANVAS_WIDTH / 2 - 150, Constants.CANVAS_HEIGHT / 2,
                    Constants.NPCS.ADAM_NPC_WIDTH, Constants.NPCS.ADAM_NPC_HEIGHT),
                    PresetNPC.Nile, MessageFactory.getRoomTwoNileMessage()));
    
    
        // ROOM 3
        mRoomThreeSpriteArray = new ArrayList<BasicRoomSprite>();
        mRoomThreeSpriteArray.add(new GenericBackground("screen3"));
        mRoomThreeSpriteArray.add(mRoom3toRoom1Door);
        mRoomThreeSpriteArray.add(room3toRoom4Door);
        mRoomThreeSpriteArray.add(new NPC(
            new Rectangle(Constants.CANVAS_WIDTH / 2 +150, Constants.CANVAS_HEIGHT / 2-200,
                    Constants.NPCS.ADAM_NPC_WIDTH, Constants.NPCS.ADAM_NPC_HEIGHT),
                    PresetNPC.Caroline, MessageFactory.getRoomThreeCarolineMessage()));

        // ROOM 4
        ArrayList<BasicRoomSprite> mRoomFourSpriteArray = new ArrayList<BasicRoomSprite>();
        mRoomFourSpriteArray.add(new GenericBackground("screen4"));
        mRoomFourSpriteArray.add(room4toRoom3Door);
        mRoomFourSpriteArray.add(new NPC(
            new Rectangle(Constants.CANVAS_WIDTH / 2 +150, Constants.CANVAS_HEIGHT / 2-100,
                    Constants.NPCS.ADAM_NPC_WIDTH, Constants.NPCS.ADAM_NPC_HEIGHT),
                    PresetNPC.Alice, MessageFactory.getRoomFourAliceMessage()));

        mRoomFourSpriteArray.add(new EndgameDoor( new Rectangle(room3toRoom4DoorPos.x, room3toRoom4DoorPos.y+100, Constants.DOOR_WIDTH,
        Constants.DOOR_HEIGHT)));

        // ROOM 5
        ArrayList<BasicRoomSprite> roomFiveSpriteArray = new ArrayList<BasicRoomSprite>();
        
        // SHOP
        
        ArrayList<BasicSprite> mShopScreenSpriteArray = new ArrayList<BasicSprite>();
        
        mShopScreenSpriteArray.add(new BasicSpriteWithImage("shop/spriteDealerGraphics.png", 
                                   new Rectangle(0,0,Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT)));
        mShopScreenSpriteArray.add(new BasicSpriteWithImage("shop/spriteDealerBackground.png", 
                                   new Rectangle(0,0,Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT)));
        mSpriteDealer = new SpriteDealer("shop/spriteDealer.png", 
                                                     new Rectangle(0,0,Constants.CANVAS_WIDTH,
                                                                           Constants.CANVAS_HEIGHT));
        mShopScreenSpriteArray.add(new SpriteDealerItem(SpriteDealer.ItemSelected.BOLT, 
                                   "shop/boltItem.png",new Rectangle(100,125,100,100)));

        mShopScreenSpriteArray.add(new SpriteDealerItem(SpriteDealer.ItemSelected.THWACKER, 
                                   "shop/thwackerItem.png",new Rectangle(200,325,100,100)));

        mShopScreenSpriteArray.add(new SpriteDealerItem(SpriteDealer.ItemSelected.GEAR, 
                                   "shop/gearItem.png",new Rectangle(300,525,100,100)));

                                   
        mShopScreenSpriteArray.add(new SpriteCostSelector());

        mShopScreenSpriteArray.add(mSpriteDealer);


        // DEATH
        ArrayList<BasicSprite> mDeathScreenSpriteArray = new ArrayList<BasicSprite>();
        mDeathScreenSpriteArray.add(new BasicSpriteWithImage("special/deathScreen.png", 
            new Rectangle(0,0,Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT)));


        ArrayList<BasicSprite> mVictoryScreenSpriteArray = new ArrayList<BasicSprite>();
        mVictoryScreenSpriteArray.add(new BasicSpriteWithImage("special/victory.png", 
            new Rectangle(0,0,Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT)));
        
        mStatesToRespectiveArray.put(GameStates.States.TITLE_SCREEN, mTitleScreenSpriteArray);
        mStatesToRespectiveArray.put(GameStates.States.SCROLLING_TEXT, mBeginningTextArray);
        mStatesToRespectiveArray.put(GameStates.States.GAMEPLAY, mGameScreenSpriteArray);
        mStatesToRespectiveArray.put(GameStates.States.SHOP, mShopScreenSpriteArray);
        mStatesToRespectiveArray.put(GameStates.States.DEATH, mDeathScreenSpriteArray);
        mStatesToRespectiveArray.put(GameStates.States.VICTORY, mVictoryScreenSpriteArray);


        mGameplayStatesToRespectiveArray.put(GameStates.GameplayStates.NOT_IN_GAME, mNotPlayingSpriteArray);
        mGameplayStatesToRespectiveArray.put(GameStates.GameplayStates.ROOM_1, mRoomOneSpriteArray);
        mGameplayStatesToRespectiveArray.put(GameStates.GameplayStates.ROOM_2, mRoomTwoSpriteArray);
        mGameplayStatesToRespectiveArray.put(GameStates.GameplayStates.ROOM_3, mRoomThreeSpriteArray);
        mGameplayStatesToRespectiveArray.put(GameStates.GameplayStates.ROOM_4, mRoomFourSpriteArray);
        mGameplayStatesToRespectiveArray.put(GameStates.GameplayStates.ROOM_5, roomFiveSpriteArray);

        // ROOM 1

        GameStates.GameplayStates wallState = GameStates.GameplayStates.ROOM_1;
        WallFactory.addWall(GameStates.GameplayStates.ROOM_1, new Rectangle(0, 100, 1000, 100));
        WallFactory.addWall(GameStates.GameplayStates.ROOM_1,
                new Rectangle(0, 300, Constants.CANVAS_WIDTH / 2 - 200, 500));
        WallFactory.addWall(GameStates.GameplayStates.ROOM_1,
                new Rectangle(Constants.CANVAS_WIDTH / 2 + 200, 300, Constants.CANVAS_WIDTH / 2 - 200, 500));
        
        // ROOM 2
        WallFactory.addWall(GameStates.GameplayStates.ROOM_2,
                new Rectangle(1000, 100, Constants.CANVAS_WIDTH - 1000, 100));
        WallFactory.addWall(GameStates.GameplayStates.ROOM_2,
                new Rectangle(1000, 300, Constants.CANVAS_WIDTH - 1000, 900));
        WallFactory.addWall(GameStates.GameplayStates.ROOM_2, new Rectangle(0, 0, Constants.CANVAS_WIDTH, 100));
        WallFactory.addWall(GameStates.GameplayStates.ROOM_2, new Rectangle(0, Constants.CANVAS_HEIGHT-100, Constants.CANVAS_WIDTH, 100));
        WallFactory.addWall(GameStates.GameplayStates.ROOM_2, new Rectangle(0,0,100,Constants.CANVAS_HEIGHT-200));

        // ROOM 3
        wallState = GameStates.GameplayStates.ROOM_3;
        WallFactory.addHallway(wallState, 0, 200);
        mRoomThreeSpriteArray.add(new FakeWall(new Rectangle(Constants.CANVAS_WIDTH-500, 400, Constants.CANVAS_WIDTH-600, Constants.CANVAS_HEIGHT-300)));

        // ROOM 4
        wallState = GameStates.GameplayStates.ROOM_4;
        WallFactory.addHallway(wallState, 0, room4toRoom3DoorPos.y);
        WallFactory.addWall(GameplayStates.ROOM_4, new Rectangle(Constants.CANVAS_WIDTH-500, 500, Constants.CANVAS_WIDTH-600, Constants.CANVAS_HEIGHT-300));
        
        // ROOM 5
        WallFactory.addWall(GameStates.GameplayStates.ROOM_5,
                new Rectangle(0, 300, Constants.CANVAS_WIDTH / 2 - 200, 500));
        WallFactory.addWall(GameStates.GameplayStates.ROOM_5,
                new Rectangle(Constants.CANVAS_WIDTH / 2 + 200, 300, Constants.CANVAS_WIDTH / 2 - 200, 500));
        


        EnemyFactory.addEnemy(GameStates.GameplayStates.ROOM_2, new Point(0, Constants.CANVAS_HEIGHT/4));
        EnemyFactory.addEnemy(GameStates.GameplayStates.ROOM_2, new Point(0, (Constants.CANVAS_HEIGHT * 3)/4));
        EnemyFactory.addEnemy(GameStates.GameplayStates.ROOM_3,
                new Point(Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT / 2));

        EnemyFactory.addEnemy(GameStates.GameplayStates.ROOM_4,
                new Point(Constants.CANVAS_WIDTH / 2+300, (Constants.CANVAS_HEIGHT-170) / 2));
        EnemyFactory.addEnemy(GameStates.GameplayStates.ROOM_4,
                new Point(Constants.CANVAS_WIDTH / 2+300, (Constants.CANVAS_HEIGHT-20) / 2));


        EnemyFactory.addFinalBossEnemy(GameStates.GameplayStates.ROOM_5,
                new Point(Constants.CANVAS_WIDTH / 2-100, 0));

        // Add all wall sprites to room array

        for (GameStates.GameplayStates gameplayState : mAllRoomStatesArray) {
            ArrayList<Wall> tempWallArray = WallFactory.getRoomWallArray(gameplayState);
            for (Wall wall : tempWallArray) {
                mGameplayStatesToRespectiveArray.get(gameplayState).add(wall);
            }
        }

        for (GameStates.GameplayStates gameplayState : mAllRoomStatesArray) {
            ArrayList<Enemy> tempEnemyArray = EnemyFactory.getRoomEnemyArray(gameplayState);
            for (Enemy enemy : tempEnemyArray) {
                mGameplayStatesToRespectiveArray.get(gameplayState).add(enemy);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        mState = GameStates.getState();
        mGameplayState = GameStates.getGameplayState();
        // System.out.println(mGameplayState);
        if (mState == GameStates.States.GAMEPLAY) {

            for (BasicRoomSprite roomSprite : mGameplayStatesToRespectiveArray.get(mGameplayState)) {
                // System.out.println(roomSprite);
                roomSprite.tick();
            }
            for (BasicSprite sprite : EnemyDropsFactory.getAllRoomDrops(mGameplayState)) {
                sprite.tick();
            }
        }
        
        for (BasicSprite sprite : mStatesToRespectiveArray.get(mState)) {
            sprite.tick();
        }

        


        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        mState = GameStates.getState();
        super.paintComponent(g);
        // when calling g.drawImage() we can use "this" for the ImageObserver
        // because Component implements the ImageObserver interface, and JPanel
        // extends from Component. So "this" Board instance, as a Component, can
        // react to imageUpdate() events triggered by g.drawImage()

        // draw our graphics.
        drawBackground(g);

        if (mState == GameStates.States.GAMEPLAY) {
            for (BasicRoomSprite roomSprite : mGameplayStatesToRespectiveArray.get(mGameplayState)) {
                roomSprite.draw(g, this);
            }
            // System.out.println(EnemyDropsFactory.getAllRoomDrops(mGameplayState).size());
            
            for (EnemyDrop sprite : EnemyDropsFactory.getAllRoomDrops(mGameplayState)) {
               
                sprite.draw(g, this);
                
            }
        }
        for (BasicSprite sprite : mStatesToRespectiveArray.get(mState)) {
            sprite.draw(g, this);
        }

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public Weapon getWeapon() {
        return mWeapon;
    }

    public TextBox getTextBox() {
        return mTextBox;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    private boolean mHasChangedRoomAlready;

    @Override
    public void keyPressed(KeyEvent e) {
        mHasChangedRoomAlready = false;
        mState = GameStates.getState();
        // react to key down events
        if (mState == GameStates.States.GAMEPLAY) {
            for (BasicRoomSprite roomSprite : mGameplayStatesToRespectiveArray.get(mGameplayState)) {
                roomSprite.keyPressed(e);
            }
        }
        for (BasicSprite sprite : mStatesToRespectiveArray.get(mState)) {
            sprite.keyPressed(e);
        }

    }

    public void exitingState(GameStates.States state) {
        if (mState == GameStates.States.GAMEPLAY) {
            for (BasicRoomSprite roomSprite : mGameplayStatesToRespectiveArray.get(mGameplayState)) {
                roomSprite.onDelete();
            }
        }
        for (BasicSprite sprite : mStatesToRespectiveArray.get(mState)) {
            sprite.onDelete();
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
                            TILE_SIZE);
                }
            }
        }
    }

    /* MISC GETTER METHODS */
    public Point getPlayerPosition() {
        return mPlayer.getPlayerPos();
    }

    public Rectangle getPlayerHitbox() {
        return mPlayer.getPlayerHitboxRectangle();
    }

    public void setPlayerPosition(Point pPos) {
        mPlayer.setPosition(pPos);
    }

    public boolean getHasChangedRoomAlready() {
        return mHasChangedRoomAlready;
    }

    public void setHasChangedRoomAlready(boolean b) {
        mHasChangedRoomAlready = b;
    }

    public void lowerPlayerHealth() {
        mPlayer.lowerPlayerHealth();
    }

    public InventoryScreen getInventoryScreen() {
        return mInventoryScreen;
    }

    public SpriteDealer getSpriteDealer() {
        return mSpriteDealer;
    }

}