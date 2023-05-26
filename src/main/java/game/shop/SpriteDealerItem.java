package game.shop;
import java.awt.Graphics2D;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;

import game.Game;
import game.generalSprites.BasicSpriteWithImage;
import game.shop.SpriteDealer;

public class SpriteDealerItem extends BasicSpriteWithImage{

    private float mBrightnessFactor;
    private SpriteDealer.ItemSelected itemType;
    public SpriteDealerItem(SpriteDealer.ItemSelected pItemType, String pFileName, Rectangle pHitbox) {
        super(pFileName, pHitbox);
        itemType = pItemType;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        SpriteDealer dealer = Game.getBoard().getSpriteDealer();
        if (dealer.getItemSelected() == itemType)
        {
            mBrightnessFactor = (float) Math.sin((System.currentTimeMillis() - dealer.getTimeStartedSelected() + (300 * Math.PI))/300.0) + 1.2f;
        }
        else
        {
            mBrightnessFactor = 1;
        }
    }
    @Override
    public void draw(Graphics g, ImageObserver observer) 
    {
        RescaleOp rescaleOp = new RescaleOp(mBrightnessFactor, 0, null);   
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(img, rescaleOp, mHitbox.x, mHitbox.y);
    }

}
