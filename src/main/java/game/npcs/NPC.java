package game.npcs;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import game.Game;
import game.npcs.TextBox.TextState;
import game.utils.ImageUtils;

public class NPC implements NPCInterface {

    public enum PresetNPC {
        Adam, Nile
    }

    private BufferedImage npc;
    private BufferedImage npcFlipped;
    private BufferedImage npcHead;

    private ArrayList<String> mMessage;
    private PresetNPC mPresetNPC;
    private boolean mIsTalking;
    private Rectangle mHitBox;

    public NPC(Rectangle pHitbox, PresetNPC pPresetNPC, ArrayList<String> pMessage) {
        // load the assets
        mPresetNPC = pPresetNPC;
        mMessage = pMessage;
        loadImage();
        mHitBox = pHitbox;

    }

    private void loadImage() {
        try {
            switch (mPresetNPC) {
                case Adam: {
                    npc = ImageIO.read(new File("src/main/resources/images/npcs/adamFigure.png"));
                    npcFlipped = ImageUtils.flipImageHoriziontally(npc);
                    npcHead = ImageIO.read(new File("src/main/resources/images/npcs/adamHead.png"));
                    break;
                }
                case Nile:
                {
                    npc = ImageIO.read(new File("src/main/resources/images/npcs/nileFigure.png"));
                    npcFlipped = ImageUtils.flipImageHoriziontally(npc);
                    npcHead = ImageIO.read(new File("src/main/resources/images/npcs/nileHead.png"));
                    break;
                }
            }

        } catch (IOException exc) {
            System.out.println("Error opening " + mPresetNPC + " NPC image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        BufferedImage currentImage = Math.sin(System.currentTimeMillis() / 500.0) > 0 ? npc : npcFlipped;
        g.drawImage(
                currentImage,
                mHitBox.x,
                mHitBox.y,
                mHitBox.width,
                mHitBox.height,
                observer);

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && mHitBox.intersects(Game.getPlayer().getPlayerHitboxRectangle())) {
            mIsTalking = Game.getTextBox().getTextState() != TextState.INVISIBLE;
            if (!mIsTalking) {
                Game.getTextBox().setState(TextBox.TextState.ENTERING);
                Game.getTextBox().setText(mMessage);
                Game.getTextBox().setHeadImage(npcHead);
                mIsTalking = true;
            }

        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void onDelete() {
        npc = null;
    }

}