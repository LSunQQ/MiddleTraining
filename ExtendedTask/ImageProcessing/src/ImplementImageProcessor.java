import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;

public class ImplementImageProcessor implements IImageProcessor {
    /*
     * show the red chanel of the photo
     */
    public Image showChanelR(Image sourceImage) {
        BufferedImage outputImage = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(sourceImage, 0, 0, null);
        for (int i = 0; i < outputImage.getHeight(); ++i) {
            for (int j = 0; j < outputImage.getWidth(); ++j) {
                Color newcolors = new Color(outputImage.getRGB(j, i));
                int red = newcolors.getRed();
                outputImage.setRGB(j, i, new Color(red, 0, 0).getRGB());
            }
        }
        return outputImage;
    }


    /*
     * show the green chanel of the photo
     */
    public Image showChanelG(Image sourceImage) {
        BufferedImage outputImage = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(sourceImage, 0, 0, null);
        for (int i = 0; i < outputImage.getHeight(); ++i) {
            for (int j = 0; j < outputImage.getWidth(); ++j) {
                Color newcolors = new Color(outputImage.getRGB(j, i));
                int green = newcolors.getGreen();
                outputImage.setRGB(j, i, new Color(0, green, 0).getRGB());
            }
        }
        return outputImage;
    }

    /*
     * show the blue chanel of the photo
     */
    public Image showChanelB(Image sourceImage) {
        BufferedImage outputImage = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(sourceImage, 0, 0, null);
        for (int i = 0; i < outputImage.getHeight(); ++i) {
            for (int j = 0; j < outputImage.getWidth(); ++j) {
                Color newcolors = new Color(outputImage.getRGB(j, i));
                int blue = newcolors.getBlue();
                outputImage.setRGB(j, i, new Color(0, 0, blue).getRGB());
            }
        }
        return outputImage;
    }

    /*
     * show the gray chanel of the photo
     */
    public Image showGray(Image sourceImage) {
        BufferedImage outputImage = new BufferedImage(sourceImage.getWidth(null), sourceImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(sourceImage, 0, 0, null);
        for (int i = 0; i < outputImage.getHeight(); ++i) {
            for (int j = 0; j < outputImage.getWidth(); ++j) {
                Color newcolors = new Color(outputImage.getRGB(j, i));
                int gray = (int)(newcolors.getRed() * 0.299 + newcolors.getGreen() * 0.587 + newcolors.getBlue() * 0.114);
                outputImage.setRGB(j, i, new Color(gray, gray, gray).getRGB());
            }
        }
        return outputImage;
    }
}