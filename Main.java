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
            BufferedImage result = ImageFilters.applyGrayscale(img);
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
