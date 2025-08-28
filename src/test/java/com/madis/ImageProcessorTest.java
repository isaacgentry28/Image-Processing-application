package com.madis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageProcessorTest {

    private static final String TEST_IMAGE_PATH = "src/test/resources/cat.jpg";
    private static final String OUTPUT_IMAGE_PATH = "src/test/resources/output_test.png";

    private static final int RUNS = 10; 

    @Test
    void testLoadImage() throws Exception {
        for (int i = 0; i < RUNS; i++) {
            BufferedImage img = ImageLoader.loadImage(TEST_IMAGE_PATH);
            assertNotNull(img, "Loaded image should not be null");
            assertTrue(img.getWidth() > 0 && img.getHeight() > 0, "Image dimensions should be positive");
        }
    }

    @Test
    void testSaveImage() throws Exception {
        BufferedImage img = ImageLoader.loadImage(TEST_IMAGE_PATH);
        for (int i = 0; i < RUNS; i++) {
            ImageSaver.saveImage(img, "png", OUTPUT_IMAGE_PATH);
            File f = new File(OUTPUT_IMAGE_PATH);
            assertTrue(f.exists(), "Saved image file should exist");
            assertTrue(f.length() > 0, "Saved image file should not be empty");
        }
        new File(OUTPUT_IMAGE_PATH).delete(); // clean up
    }

    @Test
    void testResizeImage() throws Exception {
        BufferedImage img = ImageLoader.loadImage(TEST_IMAGE_PATH);
        int newWidth = img.getWidth() / 2;

        for (int i = 0; i < RUNS; i++) {
            BufferedImage resized = ImageResizer.resize(img, newWidth);
            assertEquals(newWidth, resized.getWidth(), "Resized width should match requested width");
            double originalRatio = (double) img.getWidth() / img.getHeight();
            double newRatio = (double) resized.getWidth() / resized.getHeight();
            assertEquals(originalRatio, newRatio, 0.01, "Aspect ratio should be maintained");
        }
    }

    @Test
    void testGrayscale() throws Exception {
        BufferedImage img = ImageLoader.loadImage(TEST_IMAGE_PATH);

        for (int i = 0; i < RUNS; i++) {
            BufferedImage gray = ImageFilters.applyGrayscale(img);
            assertEquals(img.getWidth(), gray.getWidth(), "Width should stay the same after grayscale");
            assertEquals(img.getHeight(), gray.getHeight(), "Height should stay the same after grayscale");

            int pixel = gray.getRGB(0, 0);
            int r = (pixel >> 16) & 0xff;
            int g = (pixel >> 8) & 0xff;
            int b = pixel & 0xff;
            assertTrue(r == g && g == b, "Grayscale pixel should have equal RGB values");
        }
    }

    @Test
    void testRotate90() throws Exception {
        BufferedImage img = ImageLoader.loadImage(TEST_IMAGE_PATH);

        for (int i = 0; i < RUNS; i++) {
            BufferedImage rotated = ImageRotator.rotate90(img);
            assertEquals(img.getWidth(), rotated.getHeight(), "Rotated width should equal original height");
            assertEquals(img.getHeight(), rotated.getWidth(), "Rotated height should equal original width");
        }
    }

    @Test
    void testCropHalf() throws Exception {
        BufferedImage img = ImageLoader.loadImage(TEST_IMAGE_PATH);

        for (int i = 0; i < RUNS; i++) {
            BufferedImage cropped = ImageCropper.cropHalf(img);
            assertEquals(img.getWidth() / 2, cropped.getWidth(), "Cropped width should be half of original");
            assertEquals(img.getHeight(), cropped.getHeight(), "Cropped height should stay the same");
        }
    }

    @Test
    void testBatchProcessing() throws Exception {
        List<String> paths = new ArrayList<>();
        paths.add(TEST_IMAGE_PATH);
        paths.add(TEST_IMAGE_PATH); // duplicate for example batch

        for (int i = 0; i < RUNS; i++) {
            for (String path : paths) {
                BufferedImage img = ImageLoader.loadImage(path);
                BufferedImage gray = ImageFilters.applyGrayscale(img);
                BufferedImage resized = ImageResizer.resize(gray, img.getWidth() / 2);
                BufferedImage rotated = ImageRotator.rotate90(resized);
                BufferedImage cropped = ImageCropper.cropHalf(rotated);
                ImageSaver.saveImage(cropped, "png", OUTPUT_IMAGE_PATH);
                File f = new File(OUTPUT_IMAGE_PATH);
                assertTrue(f.exists(), "Batch processed image should exist");
                f.delete();
            }
        }
    }
}
