import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageSaver {
    public static void saveImage(BufferedImage img, String format, String path) throws Exception {
        ImageIO.write(img, format, new File(path));
    }
}
