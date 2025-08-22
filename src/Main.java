import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to image file: ");
        String path = scanner.nextLine();
        try {
            BufferedImage img = ImageIO.read(new File(path));
            System.out.println("Choose operation: 1) Grayscale 2) Rotate 90 degrees 3) Crop half");
            int choice = scanner.nextInt();
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
            String outPath = "output.png";
            
            ImageIO.write(result, "png", new File(outPath));
            System.out.println("Grayscale image saved as " + outPath);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
