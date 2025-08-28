package com.madis;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImageFilters {
    public static BufferedImage applyGrayscale(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color c = new Color(img.getRGB(x, y));
                int gray = (int)(0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                Color g = new Color(gray, gray, gray);
                result.setRGB(x, y, g.getRGB());
            }
        }
        return result;
    }
}
