package com.madis;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.util.Scanner;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] paths = new String[2];

        for (int i = 0; i < 2; i++) {
            System.out.print("Enter path to image file " + (i + 1) + ": ");
            paths[i] = scanner.nextLine();
        }

        System.out.println("Choose operation:");
        System.out.println("1) Grayscale");
        System.out.println("2) Rotate 90 degrees");
        System.out.println("3) Crop half");
        System.out.println("4) Resize (maintain aspect ratio)");
        int choice = scanner.nextInt();

        for (int i = 0; i < 2; i++) {
            try {
                BufferedImage img = ImageLoader.loadImage(paths[i]);
                BufferedImage result = null;

                switch (choice) {
                    case 1:
                        result = ImageFilters.applyGrayscale(img);
                        break;
                    case 2:
                        result = ImageRotator.rotate90(img);
                        break;
                    case 3:
                        result = ImageCropper.cropHalf(img);
                        break;
                    case 4:
                        System.out.print("Enter new width for image " + (i + 1) + ": ");
                        int newWidth = scanner.nextInt();
                        result = ImageResizer.resize(img, newWidth);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        System.exit(1);
                }

                String outPath = "output" + (i + 1) + ".png";
                ImageSaver.saveImage(result, "png", outPath);
                System.out.println("Processed image saved as " + outPath);

            } catch (Exception e) {
                System.out.println("Error processing image " + (i + 1) + ": " + e.getMessage());
            }
        }

        scanner.close();
    }
}
