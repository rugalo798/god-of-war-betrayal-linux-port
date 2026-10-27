package javax.microedition.midlet;

public abstract class MIDlet {
    public abstract void startApp() throws InterruptedException;
    public abstract void pauseApp() throws InterruptedException;
    public abstract void destroyApp(boolean unconditional) throws InterruptedException;
    public void notifyDestroyed() {
        System.exit(0);
    }
}
