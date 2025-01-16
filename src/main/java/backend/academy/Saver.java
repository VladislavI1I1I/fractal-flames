package backend.academy;

import backend.academy.Primitives.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("RegexpSinglelineJava")

@Slf4j
public class Saver {
    public void savePicture(ArrayList<ArrayList<Pixel>> picture, Path fullPath) {
        int width = picture.size() - 1;
        int height = picture.getFirst().size() - 1;

        BufferedImage savableFractal = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                savableFractal.setRGB(x, y, picture.get(x).get(y).getColor());
            }
        }

        File outputFile = fullPath.toFile();

        try {
            boolean success = ImageIO.write(savableFractal, "png", outputFile);
            if (!success) {
                System.out.println("Error: Failed to save the image.");
            } else {
                System.out.println("Image successfully saved to " + fullPath);
            }
        } catch (IOException e) {
            System.err.println("Error saving the image: " + e.getMessage());
            log.warn("Error saving the image:", e);
        }
    }
}
