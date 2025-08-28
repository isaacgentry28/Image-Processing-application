package com.madis;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageLoader {
    public static BufferedImage loadImage(String path) throws Exception {
        return ImageIO.read(new File(path));
    }
}
