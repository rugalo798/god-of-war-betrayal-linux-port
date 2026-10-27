package javax.microedition.lcdui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Graphics {
    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    public static final int BASELINE = 64;

    private Graphics2D g2d;
    private Font currentFont = Font.getDefaultFont();
    private int currentColor = 0;
    private int transX = 0;
    private int transY = 0;

    public Graphics() {
    }

    public Graphics(Graphics2D g) {
        setGraphics2D(g);
    }

    public void setGraphics2D(Graphics2D g) {
        this.g2d = g;
        if (g != null && currentFont != null) {
            g.setFont(currentFont.getAwtFont());
        }
    }

    public Graphics2D getGraphics2D() {
        return g2d;
    }

    public void setColor(int rgb) {
        this.currentColor = rgb;
        if (g2d != null) {
            g2d.setColor(new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF));
        }
    }

    public void setColor(int r, int g, int b) {
        this.currentColor = ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
        if (g2d != null) {
            g2d.setColor(new Color(r & 0xFF, g & 0xFF, b & 0xFF));
        }
    }

    public int getColor() {
        return currentColor;
    }

    public void fillRect(int x, int y, int width, int height) {
        if (g2d != null && width > 0 && height > 0) {
            g2d.fillRect(x, y, width, height);
        }
    }

    public void drawRect(int x, int y, int width, int height) {
        if (g2d != null && width >= 0 && height >= 0) {
            g2d.drawRect(x, y, width, height);
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        if (g2d != null) {
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        if (g2d != null) {
            g2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
        }
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        if (g2d != null) {
            g2d.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
        }
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (g2d != null) {
            g2d.drawArc(x, y, width, height, startAngle, arcAngle);
        }
    }

    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (g2d != null) {
            g2d.fillArc(x, y, width, height, startAngle, arcAngle);
        }
    }

    public void translate(int x, int y) {
        this.transX += x;
        this.transY += y;
        if (g2d != null) {
            g2d.translate(x, y);
        }
    }

    public int getTranslateX() {
        return transX;
    }

    public int getTranslateY() {
        return transY;
    }

    public void setClip(int x, int y, int width, int height) {
        if (g2d != null) {
            g2d.setClip(x, y, width, height);
        }
    }

    public void clipRect(int x, int y, int width, int height) {
        if (g2d != null) {
            g2d.clipRect(x, y, width, height);
        }
    }

    public int getClipX() {
        Shape clip = g2d != null ? g2d.getClip() : null;
        return clip != null ? clip.getBounds().x : 0;
    }

    public int getClipY() {
        Shape clip = g2d != null ? g2d.getClip() : null;
        return clip != null ? clip.getBounds().y : 0;
    }

    public int getClipWidth() {
        Shape clip = g2d != null ? g2d.getClip() : null;
        return clip != null ? clip.getBounds().width : 240;
    }

    public int getClipHeight() {
        Shape clip = g2d != null ? g2d.getClip() : null;
        return clip != null ? clip.getBounds().height : 320;
    }

    public void setFont(Font f) {
        this.currentFont = (f != null) ? f : Font.getDefaultFont();
        if (g2d != null) {
            g2d.setFont(this.currentFont.getAwtFont());
        }
    }

    public Font getFont() {
        return currentFont;
    }

    public void drawString(String str, int x, int y, int anchor) {
        if (str == null || g2d == null) return;
        int drawX = x;
        int drawY = y;
        int strWidth = currentFont.stringWidth(str);

        if ((anchor & RIGHT) != 0) drawX -= strWidth;
        else if ((anchor & HCENTER) != 0) drawX -= strWidth / 2;

        if ((anchor & TOP) != 0) drawY += currentFont.getBaselinePosition();
        else if ((anchor & BOTTOM) != 0) drawY -= (currentFont.getHeight() - currentFont.getBaselinePosition());
        else if ((anchor & VCENTER) != 0) drawY += (currentFont.getBaselinePosition() - currentFont.getHeight() / 2);

        g2d.drawString(str, drawX, drawY);
    }

    public void drawChars(char[] data, int offset, int length, int x, int y, int anchor) {
        if (data == null || length <= 0 || g2d == null) return;
        drawString(new String(data, offset, length), x, y, anchor);
    }

    public void drawSubstring(String str, int offset, int len, int x, int y, int anchor) {
        if (str == null || len <= 0 || g2d == null) return;
        drawString(str.substring(offset, offset + len), x, y, anchor);
    }

    public void drawImage(Image img, int x, int y, int anchor) {
        if (img == null || img.getBufferedImage() == null || g2d == null) return;
        BufferedImage bi = img.getBufferedImage();
        int drawX = x;
        int drawY = y;
        int w = bi.getWidth();
        int h = bi.getHeight();

        if ((anchor & RIGHT) != 0) drawX -= w;
        else if ((anchor & HCENTER) != 0) drawX -= w / 2;

        if ((anchor & BOTTOM) != 0) drawY -= h;
        else if ((anchor & VCENTER) != 0) drawY -= h / 2;

        g2d.drawImage(bi, drawX, drawY, null);
    }

    /**
     * Standard J2ME MIDP 2.0 drawRegion with full AffineTransform support for all 8 orientations
     */
    public void drawRegion(Image src, int x_src, int y_src, int width, int height,
                           int transform, int x_dest, int y_dest, int anchor) {
        if (src == null || src.getBufferedImage() == null || g2d == null || width <= 0 || height <= 0) return;

        // Destination dimensions after rotation
        int destW = (transform >= 4) ? height : width;
        int destH = (transform >= 4) ? width : height;

        int drawX = x_dest;
        int drawY = y_dest;

        if ((anchor & RIGHT) != 0) drawX -= destW;
        else if ((anchor & HCENTER) != 0) drawX -= destW / 2;

        if ((anchor & BOTTOM) != 0) drawY -= destH;
        else if ((anchor & VCENTER) != 0) drawY -= destH / 2;

        AffineTransform oldTx = g2d.getTransform();
        g2d.translate(drawX, drawY);

        switch (transform) {
            case 0: // TRANS_NONE
                break;
            case 1: // TRANS_MIRROR_ROT180 (Vertical Flip)
                g2d.translate(0, height);
                g2d.scale(1.0, -1.0);
                break;
            case 2: // TRANS_MIRROR (Horizontal Flip)
                g2d.translate(width, 0);
                g2d.scale(-1.0, 1.0);
                break;
            case 3: // TRANS_ROT180 (180 deg rotation)
                g2d.translate(width, height);
                g2d.scale(-1.0, -1.0);
                break;
            case 4: // TRANS_MIRROR_ROT270
                g2d.translate(height, 0);
                g2d.rotate(Math.PI / 2.0);
                g2d.scale(-1.0, 1.0);
                break;
            case 5: // TRANS_ROT90 (90 deg clockwise)
                g2d.translate(height, 0);
                g2d.rotate(Math.PI / 2.0);
                break;
            case 6: // TRANS_ROT270 (270 deg clockwise)
                g2d.translate(0, width);
                g2d.rotate(-Math.PI / 2.0);
                break;
            case 7: // TRANS_MIRROR_ROT90
                g2d.rotate(-Math.PI / 2.0);
                g2d.scale(-1.0, 1.0);
                break;
        }

        g2d.drawImage(src.getBufferedImage(),
                      0, 0, width, height,
                      x_src, y_src, x_src + width, y_src + height,
                      null);

        g2d.setTransform(oldTx);
    }

    public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
        if (g2d == null || rgbData == null || width <= 0 || height <= 0) return;
        BufferedImage img = new BufferedImage(width, height, processAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, width, height, rgbData, offset, scanlength);
        g2d.drawImage(img, x, y, null);
    }
}
