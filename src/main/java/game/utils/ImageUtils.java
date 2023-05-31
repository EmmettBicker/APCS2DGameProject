package game.utils;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * The ImageUtils class provides utility methods for image manipulation.
 * It contains a method for flipping an image horizontally.
 */
public class ImageUtils {
    private ImageUtils() {
    }

    /**
     * Flips an image horizontally.
     *
     * @param b the image to be flipped
     * @return the flipped image
     */
    public static BufferedImage flipImageHorizontally(BufferedImage b) {
        int width = b.getWidth();
        int height = b.getHeight();
        BufferedImage ans = new BufferedImage(width, height, b.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ans.setRGB(width - i - 1, j, b.getRGB(i, j));
            }
        }
        return ans;
    }
}
