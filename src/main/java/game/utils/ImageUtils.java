package game.utils;

import java.awt.image.BufferedImage;

public class ImageUtils {
    private ImageUtils() {
    }

    public static BufferedImage flipImageHoriziontally(BufferedImage b) {
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
