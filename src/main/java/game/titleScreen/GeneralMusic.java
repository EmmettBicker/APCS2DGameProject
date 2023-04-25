package game.titleScreen;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.interfaces.BasicSprite;


public class GeneralMusic implements BasicSprite {

    // image that represents the player's position on the board
    private BufferedImage background;
    private Point pos;
    private Clip music;
    private boolean hasStartedMusic;
    private String path;

    public GeneralMusic(String pPath) {
        // load the assets
        loadImage();
        path = pPath;
        hasStartedMusic = false;
        
        pos = new Point(0, 0);

    }

    private void loadImage() {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            
            background = ImageIO.read(new File("src/main/resources/images/special/emptyImage.png"));
    
        } catch (IOException exc) {
            System.out.println("Error opening title screen image file: " + exc.getMessage());
        }
    }

    public void playMusic() 
    {
        try 
        {
            if (!hasStartedMusic)
            {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(path).getAbsoluteFile());
                music = AudioSystem.getClip();
                
                music.open(audioInputStream);
                music.start();
            }
            hasStartedMusic = true;
            
        } 
        catch(Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but 
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
   
            g.drawImage
            (
                background, 
                pos.x,
                pos.y,
                observer
            );
    
    
    }

    public void keyPressed(KeyEvent e) {
        
   
    }

    @Override
    public void tick() {
        // no special behavior
        playMusic();
    }

    @Override
    public void onDelete() {
        
        hasStartedMusic = false;
        background = null;
        music.stop();
        music = null;
    }
 

}