package javax.microedition.lcdui;

import javax.microedition.midlet.MIDlet;
import gow.GameHost;

public class Display {
    private static final Display instance = new Display();
    private Displayable current;

    public static Display getDisplay(MIDlet m) {
        return instance;
    }

    public void setCurrent(Displayable d) {
        this.current = d;
        if (GameHost.getInstance() != null) {
            GameHost.getInstance().setCurrentDisplayable(d);
        }
    }

    public Displayable getCurrent() {
        return current;
    }
}
