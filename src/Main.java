import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] paths = new String[2];
        for(int i = 0; i < 2; i++) {
            System.out.print("Enter path to image file " + (i + 1) + ": ");
            paths[i] = scanner.nextLine();
        }
        System.out.print("Choose operation: 1) Grayscale 2) Rotate 90 degrees 3) Crop half");
        int choice = scanner.nextInt();
        for(int i = 0; i < 2; i++) {
            try {
                BufferedImage img = ImageIO.read(new File(paths[i]));
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
                    default:
                        System.out.println("Invalid choice.");
                        System.exit(1);
                }
                String outPath = "output" + (i + 1) + ".png";
                ImageIO.write(result, "png", new File(outPath));
                System.out.println("Processed image saved as " + outPath);
            } catch (Exception e) {
                System.out.println("Error processing image " + (i + 1) + ": " + e.getMessage());
            }
        }
        
            scanner.close();
        }
    }

