package gow;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class GameHost extends JFrame {
    private static GameHost instance;

    public static final int NATIVE_WIDTH = 240;
    public static final int NATIVE_HEIGHT = 320;

    private int scale = 3; // Default 3x (720x960)
    private BufferedImage currentFrame;
    private final JPanel canvasPanel;
    private Displayable currentDisplayable;
    private MIDlet currentMIDlet;
    private boolean isFullscreen = false;
    private Dimension windowedSize;
    private Point windowedLocation;

    public static synchronized GameHost getInstance() {
        return instance;
    }

    public static synchronized GameHost createInstance(MIDlet midlet) {
        if (instance == null) {
            instance = new GameHost(midlet);
        }
        return instance;
    }

    public GameHost(MIDlet midlet) {
        super("God of War: Betrayal (Linux Port)");
        this.currentMIDlet = midlet;
        instance = this;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);

        // Load window icon
        try (InputStream is = getClass().getResourceAsStream("/i.png")) {
            if (is != null) {
                BufferedImage icon = ImageIO.read(is);
                setIconImage(icon);
            }
        } catch (Exception e) {
            // Non-critical if icon fails to load
        }

        canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                if (currentFrame != null) {
                    // Maintain aspect ratio (3:4)
                    int panelW = getWidth();
                    int panelH = getHeight();
                    double scaleW = (double) panelW / NATIVE_WIDTH;
                    double scaleH = (double) panelH / NATIVE_HEIGHT;
                    double chosenScale = Math.min(scaleW, scaleH);

                    int targetW = (int) (NATIVE_WIDTH * chosenScale);
                    int targetH = (int) (NATIVE_HEIGHT * chosenScale);
                    int targetX = (panelW - targetW) / 2;
                    int targetY = (panelH - targetH) / 2;

                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                    g2d.drawImage(currentFrame, targetX, targetY, targetW, targetH, null);
                }
            }
        };

        canvasPanel.setPreferredSize(new Dimension(NATIVE_WIDTH * scale, NATIVE_HEIGHT * scale));
        canvasPanel.setBackground(Color.BLACK);
        canvasPanel.setFocusable(true);
        canvasPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyEvent(e, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyEvent(e, false);
            }
        });

        add(canvasPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public synchronized void setCurrentDisplayable(Displayable d) {
        this.currentDisplayable = d;
        if (d instanceof Canvas) {
            Canvas c = (Canvas) d;
            try {
                c.showNotify();
            } catch (Exception e) {
            }
            c.repaint();
        }
    }

    public void onFrameReady(BufferedImage frame) {
        this.currentFrame = frame;
        canvasPanel.repaint();
    }

    private void toggleFullscreen() {
        isFullscreen = !isFullscreen;
        dispose();
        if (isFullscreen) {
            windowedSize = getSize();
            windowedLocation = getLocation();
            setUndecorated(true);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setUndecorated(false);
            setExtendedState(JFrame.NORMAL);
            if (windowedSize != null) {
                setSize(windowedSize);
            }
            if (windowedLocation != null) {
                setLocation(windowedLocation);
            }
        }
        setVisible(true);
        canvasPanel.requestFocusInWindow();
    }

    public void setScale(int s) {
        if (isFullscreen) return;
        this.scale = Math.max(1, Math.min(5, s));
        canvasPanel.setPreferredSize(new Dimension(NATIVE_WIDTH * scale, NATIVE_HEIGHT * scale));
        pack();
        setLocationRelativeTo(null);
    }

    private void handleKeyEvent(KeyEvent e, boolean pressed) {
        int code = e.getKeyCode();

        // Hotkeys
        if (pressed) {
            if (code == KeyEvent.VK_F11) {
                toggleFullscreen();
                return;
            }
            if (e.isControlDown()) {
                if (code == KeyEvent.VK_1) { setScale(1); return; }
                if (code == KeyEvent.VK_2) { setScale(2); return; }
                if (code == KeyEvent.VK_3) { setScale(3); return; }
                if (code == KeyEvent.VK_4) { setScale(4); return; }
            }
        }

        if (currentDisplayable instanceof Canvas) {
            Canvas canvas = (Canvas) currentDisplayable;
            int j2meKey = mapToJ2MEKey(code);
            if (j2meKey != 0) {
                if (pressed) {
                    canvas.keyPressed(j2meKey);
                } else {
                    canvas.keyReleased(j2meKey);
                }
            }
        }
    }

    /**
     * Map PC Keyboard to J2ME Key codes:
     * UP = -1, LEFT = -3, RIGHT = -4, DOWN = -2
     * FIRE / ACTION = -5 (or '5')
     * LEFT_SOFTKEY = -6
     * RIGHT_SOFTKEY = -7
     * '0'..'9' = 48..57
     * '*' = 42, '#' = 35
     */
    private int mapToJ2MEKey(int keyCode) {
        switch (keyCode) {
            // Movement (Arrow Keys or WASD)
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                return -1; // UP
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                return -2; // DOWN
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                return -3; // LEFT
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                return -4; // RIGHT

            // Action / Light Attack / Select (Space, Enter, Z, J)
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_Z:
            case KeyEvent.VK_J:
                return -5; // FIRE

            // Heavy Attack (X, K) -> Key '7' (55)
            case KeyEvent.VK_X:
            case KeyEvent.VK_K:
                return 55; // '7'

            // Jump / Interact (C, L, Up) -> Key '1' (49) or '2'
            case KeyEvent.VK_C:
            case KeyEvent.VK_L:
                return 49; // '1'

            // Magic (V, U) -> Key '3' (51)
            case KeyEvent.VK_V:
            case KeyEvent.VK_U:
                return 51; // '3'

            // Dodge / Roll (B, I) -> Key '0' (48)
            case KeyEvent.VK_B:
            case KeyEvent.VK_I:
                return 48; // '0'

            // Left Softkey (Menu / Pause / Options)
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_Q:
            case KeyEvent.VK_F1:
            case KeyEvent.VK_SHIFT:
                return -6;

            // Right Softkey (Back / Skip Dialog)
            case KeyEvent.VK_BACK_SPACE:
            case KeyEvent.VK_E:
            case KeyEvent.VK_F2:
            case KeyEvent.VK_TAB:
                return -7;

            // Numpad & Numeric keys
            case KeyEvent.VK_NUMPAD0:
            case KeyEvent.VK_0:
                return 48;
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_1:
                return 49;
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_2:
                return 50;
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_3:
                return 51;
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_4:
                return 52;
            case KeyEvent.VK_NUMPAD5:
            case KeyEvent.VK_5:
                return 53;
            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_6:
                return 54;
            case KeyEvent.VK_NUMPAD7:
            case KeyEvent.VK_7:
                return 55;
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_8:
                return 56;
            case KeyEvent.VK_NUMPAD9:
            case KeyEvent.VK_9:
                return 57;
            case KeyEvent.VK_MULTIPLY:
                return 42; // '*'
            case KeyEvent.VK_DIVIDE:
                return 35; // '#'

            default:
                return 0;
        }
    }
}
