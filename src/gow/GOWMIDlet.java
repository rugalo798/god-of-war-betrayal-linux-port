package gow;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/* JADX INFO: loaded from: gow.jar:GOWMIDlet.class */
public class GOWMIDlet extends MIDlet {
    public static a a;
    public static int _D_D = 0;

    public void startApp() throws InterruptedException {
        if (_D_D != 0) {
            _d_d();
        } else {
            new _B_B(this)._e();
            _D_D++;
        }
    }

    public void destroyApp(boolean z) throws InterruptedException {
        if (a.p != 0 && a.p != 79) {
            a.b(1);
        }
        a.j(3);
    }

    public void pauseApp() throws InterruptedException {
        a.hideNotify();
    }

    public void _c_c() {
    }

    public void _d_d() throws InterruptedException {
        System.out.println("GOWMIDlet: initializing main game canvas 'a'...");
        try {
            if (a != null) {
                a.showNotify();
            } else {
                a = new a(this);
                System.out.println("GOWMIDlet: canvas 'a' instantiated successfully, setting display...");
                Display.getDisplay(this).setCurrent(a);
            }
        } catch (Throwable t) {
            System.err.println("GOWMIDlet error during game canvas init:");
            t.printStackTrace();
        }
    }

    public void _a_a() {
        _c_c();
    }

    public void _b_b() throws InterruptedException {
        _d_d();
    }
}
