import java.awt.image.BufferedImage;
public class ImageRotator {
    public static BufferedImage rotate90(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage rotated = new BufferedImage(height, width, img.getType());

        for(int y = 0; y < height; y++) {
            for(int x = 0; x <width; x++) {
                rotated.setRGB(y, width - 1 - x, img.getRGB(x, y));
            }
        }

        return rotated;
    }
}
