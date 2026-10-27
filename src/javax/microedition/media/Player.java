package javax.microedition.media;

public interface Player {
    int UNREALIZED = 100;
    int REALIZED = 200;
    int PREFETCHED = 300;
    int STARTED = 400;
    int CLOSED = 0;

    void realize();
    void prefetch();
    void start();
    void stop();
    void close();
    void setLoopCount(int count);
    void setMediaTime(long now);
    int getState();
}
