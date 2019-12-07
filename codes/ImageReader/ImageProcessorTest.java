import static org.junit.Assert.*;
import org.junit.Test;
import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import javax.imageio.ImageIO;
import java.util.*;

public class ImageProcessorTest {
    /*
     * the test files:
     * the red chanel of the photo1
     * the green chanel of the photo1
     * the blue chanel of the photo1
     * the gray chanel of the photo1
     * the red chanel of the photo2
     * the green chanel of the photo2
     * the blue chanel of the photo2
     * the gray chanel of the photo2
     */
    private static String filePath = "/home/lxz/桌面/shixun/ExtendedTask/ImageProcessing/bmptest/goal";
    private static String file1 = filePath + "/../1.bmp";
    private static String file2 = filePath + "/../2.bmp";
    private static String red1 = filePath + "1_red_goal.bmp";
    private static String green1 = filePath + "1_green_goal.bmp";
    private static String blue1 = filePath + "1_blue_goal.bmp";
    private static String gray1 = filePath + "1_gray_goal.bmp";
    private static String red2 = filePath + "2_red_goal.bmp";
    private static String green2 = filePath + "2_green_goal.bmp";
    private static String blue2 = filePath + "2_blue_goal.bmp";
    private static String gray2 = filePath + "2_gray_goal.bmp";

    @Test
    public void testIO() {
        /*
         * test whether the height, width
         * and the pix after using the function
         * myRead to read the original photo
         */
        checkSizeAndPix(file1, file1, 0);
        checkSizeAndPix(file2, file2, 0);
    }

    @Test
    public void testChanels() {
        /*
         * test whether the photo that
         * after processing is right
         */
        checkSizeAndPix(file1, red1, 1);
        checkSizeAndPix(file2, red2, 1);

        checkSizeAndPix(file1, green1, 2);
        checkSizeAndPix(file2, green2, 2);

        checkSizeAndPix(file1, blue1, 3);
        checkSizeAndPix(file2, blue2, 3);

        checkSizeAndPix(file1, gray1, 4);
        checkSizeAndPix(file2, gray2, 4);
    }

    public void checkSizeAndPix(String testFile, String standartFile, int mode) {
        try {
            FileInputStream standardInput = new FileInputStream(standartFile);
            BufferedImage standardImage = ImageIO.read(standardInput);

            ImplementImageIO testInput = new ImplementImageIO();
            Image testImage = testInput.myRead(testFile);
            ImplementImageProcessor testProcessor = new ImplementImageProcessor();

            /*
             * get the chanel of the photo
             */
            if (mode == 1) {
                testImage = testProcessor.showChanelR(testImage);
            } else if (mode == 2) {
                testImage = testProcessor.showChanelG(testImage);
            } else if (mode == 3) {
                testImage = testProcessor.showChanelB(testImage);
            } else if (mode == 4) {
                testImage = testProcessor.showGray(testImage);
            }

            /*
             * judge whether the height and the width
             * is the same
             */
            assertEquals(testImage.getHeight(null), standardImage.getHeight(null));
            assertEquals(testImage.getHeight(null), standardImage.getHeight(null));

            BufferedImage testBufferedImage = new BufferedImage(testImage.getWidth(null), testImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            testBufferedImage.getGraphics().drawImage(testImage, 0, 0, null);
            
            /*
             * judge whether each pix is the same
             * as the file read with the java api
             */
            for (int i = 0; i < testBufferedImage.getHeight(); ++i) {
                for (int j = 0; j < testBufferedImage.getWidth(); ++j) {
                    assertEquals(testBufferedImage.getRGB(j, i), standardImage.getRGB(j, i));
                }
            }
        } catch (Exception ex) {

        }
        
    }

}
