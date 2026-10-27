package javax.microedition.lcdui.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import gow.GameHost;

public abstract class GameCanvas extends Canvas {
    public static final int UP_PRESSED = 1 << 1;
    public static final int DOWN_PRESSED = 1 << 6;
    public static final int LEFT_PRESSED = 1 << 2;
    public static final int RIGHT_PRESSED = 1 << 5;
    public static final int FIRE_PRESSED = 1 << 8;
    public static final int GAME_A_PRESSED = 1 << 9;
    public static final int GAME_B_PRESSED = 1 << 10;
    public static final int GAME_C_PRESSED = 1 << 11;
    public static final int GAME_D_PRESSED = 1 << 12;

    private BufferedImage buffer;
    private Graphics2D bufferG2D;
    private Graphics graphics;
    private int keyStates;

    protected GameCanvas(boolean suppressKeyEvents) {
        initBuffer(240, 320);
    }

    public synchronized void initBuffer(int w, int h) {
        this.width = w;
        this.height = h;
        this.buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        this.bufferG2D = buffer.createGraphics();
        this.graphics = new Graphics(bufferG2D);
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void flushGraphics() {
        flushGraphics(0, 0, width, height);
    }

    public void flushGraphics(int x, int y, int width, int height) {
        if (GameHost.getInstance() != null) {
            GameHost.getInstance().onFrameReady(buffer);
        }
    }

    public int getKeyStates() {
        return keyStates;
    }

    public void setKeyStates(int states) {
        this.keyStates = states;
    }
}
