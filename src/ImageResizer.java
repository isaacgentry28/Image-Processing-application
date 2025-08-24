import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ImageResizer {
    public static BufferedImage resize(BufferedImage img, int newWidth) {
        int originalWidth = img.getWidth();
        int originalHeight = img.getHeight();

        // maintain aspect ratio
        int newHeight = (originalHeight * newWidth) / originalWidth;

        BufferedImage resized = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g = resized.createGraphics();
        g.drawImage(img, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return resized;
    }
}
