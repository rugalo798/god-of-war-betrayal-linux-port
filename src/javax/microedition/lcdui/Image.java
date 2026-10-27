package javax.microedition.lcdui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Image {
    private final BufferedImage bufferedImage;

    public Image(BufferedImage bi) {
        this.bufferedImage = bi;
    }

    public static Image createImage(byte[] imageData, int imageOffset, int imageLength) {
        if (imageData == null || imageLength <= 0) return null;
        try {
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(imageData, imageOffset, imageLength));
            if (bi == null) {
                System.err.println("Warning: ImageIO.read returned null for image (offset=" + imageOffset + ", len=" + imageLength + ")");
                return null;
            }
            return new Image(bi);
        } catch (Exception e) {
            System.err.println("Error decoding image: " + e.getMessage());
            return null;
        }
    }

    public static Image createImage(String name) {
        try (InputStream in = Image.class.getResourceAsStream(name)) {
            if (in == null) {
                System.err.println("Warning: Image resource not found: " + name);
                return null;
            }
            BufferedImage bi = ImageIO.read(in);
            return (bi != null) ? new Image(bi) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Image createImage(int width, int height) {
        return new Image(new BufferedImage(Math.max(1, width), Math.max(1, height), BufferedImage.TYPE_INT_ARGB));
    }

    public static Image createImage(Image image, int x, int y, int width, int height, int transform) {
        if (image == null || width <= 0 || height <= 0) return null;
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = new Graphics(result.createGraphics());
        g.drawRegion(image, x, y, width, height, transform, 0, 0, 0);
        return new Image(result);
    }

    public int getWidth() {
        return bufferedImage != null ? bufferedImage.getWidth() : 0;
    }

    public int getHeight() {
        return bufferedImage != null ? bufferedImage.getHeight() : 0;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public Graphics getGraphics() {
        if (bufferedImage != null) {
            return new Graphics(bufferedImage.createGraphics());
        }
        return null;
    }

    public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
        if (bufferedImage != null && rgbData != null) {
            bufferedImage.getRGB(x, y, width, height, rgbData, offset, scanlength);
        }
    }
}
