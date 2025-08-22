import java.awt.image.BufferedImage;
public class ImageCropper {
    public static BufferedImage cropHalf(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage cropped = new BufferedImage(width / 2, height, img.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                cropped.setRGB(x, y, img.getRGB(x + width / 2, y));
            }
        }

        return cropped;
    }
}
