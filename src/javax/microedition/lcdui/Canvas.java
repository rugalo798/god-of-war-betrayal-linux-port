package javax.microedition.lcdui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import gow.GameHost;

public abstract class Canvas extends Displayable {
    public static final int UP = 1;
    public static final int DOWN = 6;
    public static final int LEFT = 2;
    public static final int RIGHT = 5;
    public static final int FIRE = 8;
    public static final int GAME_A = 9;
    public static final int GAME_B = 10;
    public static final int GAME_C = 11;
    public static final int GAME_D = 12;
    public static final int KEY_NUM0 = 48;
    public static final int KEY_NUM1 = 49;
    public static final int KEY_NUM2 = 50;
    public static final int KEY_NUM3 = 51;
    public static final int KEY_NUM4 = 52;
    public static final int KEY_NUM5 = 53;
    public static final int KEY_NUM6 = 54;
    public static final int KEY_NUM7 = 55;
    public static final int KEY_NUM8 = 56;
    public static final int KEY_NUM9 = 57;
    public static final int KEY_STAR = 42;
    public static final int KEY_POUND = 35;

    protected BufferedImage canvasBuffer;
    protected Graphics2D canvasG2D;
    protected Graphics canvasGraphics;

    public Canvas() {
        this.canvasBuffer = new BufferedImage(240, 320, BufferedImage.TYPE_INT_RGB);
        this.canvasG2D = canvasBuffer.createGraphics();
        this.canvasGraphics = new Graphics(canvasG2D);
    }

    public void setFullScreenMode(boolean mode) {}

    public void repaint() {
        if (canvasG2D != null) {
            paint(canvasGraphics);
            if (GameHost.getInstance() != null) {
                GameHost.getInstance().onFrameReady(canvasBuffer);
            }
        }
    }

    public void repaint(int x, int y, int width, int height) {
        repaint();
    }

    public void serviceRepaints() {
        repaint();
    }

    public void showNotify() throws InterruptedException {}
    public void hideNotify() throws InterruptedException {}
    public void keyPressed(int keyCode) {}
    public void keyReleased(int keyCode) {}
    public void keyRepeated(int keyCode) {}
    protected void paint(Graphics g) {}
}
