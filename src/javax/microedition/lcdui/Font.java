package javax.microedition.lcdui;

import java.awt.FontMetrics;
import java.awt.Toolkit;

public class Font {
    public static final int STYLE_PLAIN = 0;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_ITALIC = 2;
    public static final int STYLE_UNDERLINED = 4;
    public static final int SIZE_SMALL = 8;
    public static final int SIZE_MEDIUM = 0;
    public static final int SIZE_LARGE = 16;
    public static final int FACE_SYSTEM = 0;
    public static final int FACE_MONOSPACE = 32;
    public static final int FACE_PROPORTIONAL = 64;

    private static final Font DEFAULT_FONT = new Font(FACE_SYSTEM, STYLE_PLAIN, SIZE_MEDIUM);

    private final java.awt.Font awtFont;
    private final FontMetrics metrics;

    public Font(int face, int style, int size) {
        String name = java.awt.Font.SANS_SERIF;
        if ((face & FACE_MONOSPACE) != 0) {
            name = java.awt.Font.MONOSPACED;
        } else if ((face & FACE_PROPORTIONAL) != 0) {
            name = java.awt.Font.SERIF;
        }

        int awtStyle = java.awt.Font.PLAIN;
        if ((style & STYLE_BOLD) != 0) awtStyle |= java.awt.Font.BOLD;
        if ((style & STYLE_ITALIC) != 0) awtStyle |= java.awt.Font.ITALIC;

        int ptSize = 12;
        if ((size & SIZE_SMALL) != 0) ptSize = 10;
        else if ((size & SIZE_LARGE) != 0) ptSize = 15;

        this.awtFont = new java.awt.Font(name, awtStyle, ptSize);
        this.metrics = Toolkit.getDefaultToolkit().getFontMetrics(this.awtFont);
    }

    public java.awt.Font getAwtFont() {
        return awtFont;
    }

    public static Font getFont(int face, int style, int size) {
        return DEFAULT_FONT;
    }

    public static Font getDefaultFont() {
        return DEFAULT_FONT;
    }

    public int getHeight() {
        return metrics.getHeight();
    }

    public int getBaselinePosition() {
        return metrics.getAscent();
    }

    public int stringWidth(String str) {
        return str != null ? metrics.stringWidth(str) : 0;
    }

    public int charWidth(char ch) {
        return metrics.charWidth(ch);
    }

    public int charsWidth(char[] ch, int offset, int length) {
        return (ch != null && length > 0) ? metrics.charsWidth(ch, offset, length) : 0;
    }

    public int substringWidth(String str, int offset, int len) {
        return (str != null && len > 0) ? metrics.stringWidth(str.substring(offset, offset + len)) : 0;
    }
}
