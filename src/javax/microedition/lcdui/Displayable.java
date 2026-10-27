package javax.microedition.lcdui;

public abstract class Displayable {
    protected int width = 240;
    protected int height = 320;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isShown() {
        return true;
    }
}
