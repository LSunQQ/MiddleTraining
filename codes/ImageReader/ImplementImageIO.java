import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import javax.imageio.ImageIO;
import java.util.*;

public class ImplementImageIO implements IImageIO {
    /*
     * These are the const variablities
     * fileHead is the length of the file head
     * fileInfo is the length of the file info
     * imageWidthSize is the length of the image width
     * imageHeightSize is the length of the image heigth
     * imageBitCountSize is the length of the image bit count
     * imageColorSize is the length of the image Color bit
     */
    private static int fileHead = 14;
    private static int fileInfo = 40;
    private static int imageWidthSize = 4;
    private static int imageHeightSize = 4;
    private static int imageBitCountSize = 2;
    private static int imageSizeSize = 4;
    private static int imageColorSize = 1;

    private static int tempOffset = 0;

    public int getInfo(byte[] info, int size) {
        /*
         * According to the different info
         * return the Interger of the info
         */
        int result, byte0, byte1, byte2, byte3;
        if (size == 1) {
            result = (int)(info[this.tempOffset] & 0xff);
        } else if (size == 2) {
            byte0 = (int)((info[this.tempOffset + 0] & 0xff) << 0);
            byte1 = (int)((info[this.tempOffset + 1] & 0xff) << 8);
            result = (int)(byte1 | byte0);
        } else if (size == 3) {
            byte0 = (int)((info[this.tempOffset + 0] & 0xff) << 0);
            byte1 = (int)((info[this.tempOffset + 1] & 0xff) << 8);
            byte2 = (int)((info[this.tempOffset + 2] & 0xff) << 16);
            result = (int)(byte2 | byte1 | byte0);
        } else {
            byte0 = (int)((info[this.tempOffset + 0] & 0xff) << 0);
            byte1 = (int)((info[this.tempOffset + 1] & 0xff) << 8);
            byte2 = (int)((info[this.tempOffset + 2] & 0xff) << 16);
            byte3 = (int)((info[this.tempOffset + 3] & 0xff) << 24);
            result = (int)(byte3 | byte2 | byte1 | byte0);            
        }
        if (tempOffset != 0) {
            this.tempOffset += size;
        }
        return result;
    }

    public Image myRead(String filePath) throws IOException {
        Image image;

        try {
            /*
             * open the file
             */
            FileInputStream inputStream = new FileInputStream(filePath);
            
            /*
             * count image head
             */
            byte imageHead[] = new byte[fileHead];
            inputStream.read(imageHead, 0, fileHead);

            /*
             * count image info
             */
            byte imageInfo[] = new byte[fileInfo];
            inputStream.read(imageInfo, 0, fileInfo);
            this.tempOffset = 4;

            /*
             * count image width and heigth
             */
            int imageWidth = getInfo(imageInfo, imageWidthSize);
            int imageHeight = getInfo(imageInfo, imageHeightSize);
            this.tempOffset += 2;
            
            /*
             * count image bit count
             */
            int imageBitCount = getInfo(imageInfo, imageBitCountSize);

            int colors[] = new int [imageHeight * imageWidth];

            /*
             * judge whether the size of every row to save color
             * can be divided by 4. If couldn't, it means that there
             * are two empty bytes at the end of every row.
             */
            int judge = (imageBitCount * imageWidth / 8) % 4;
            boolean hasEmptyBytes = (judge != 0) ? true : false;
            this.tempOffset = 0;

            for (int i = imageHeight - 1; i >= 0; --i) {
                for (int j = 0; j < imageWidth; ++j) {
                    byte blueByte[] = new byte[1];
                    byte greenByte[] = new byte[1];
                    byte redByte[] = new byte[1];
                    inputStream.read(blueByte, 0, 1);
                    inputStream.read(greenByte, 0, 1);
                    inputStream.read(redByte, 0, 1);
                    /*
                     * get the pix of the red,
                     * blue and green color.
                     */
                    Color newColors = new Color(getInfo(redByte, imageColorSize),
                                                getInfo(greenByte, imageColorSize),
                                                getInfo(blueByte, imageColorSize));
                    colors[i * imageWidth + j] = newColors.getRGB();
                }
                if (hasEmptyBytes) {
                    inputStream.skip(4 - judge);
                }
            }

            image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(imageWidth, imageHeight, colors, 0, imageWidth));
            inputStream.close();
            return image;
        } catch (Exception ex) {

        }
        return null;
    }

    public Image myWrite(Image image, String filePath) throws IOException {
        try {
            /*
             * use the buffered image to output 
             * the image.
             */
            BufferedImage outputImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(image, 0, 0, null);
            
            /*
             * set the output file path
             */
            File imageFile = new File(filePath + ".bmp");
            ImageIO.write(outputImage, "bmp", imageFile);
            return image; 
        } catch (Exception ex) {

        }
        return null;
    }
}