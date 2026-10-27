package com.nokia.mid.sound;

public class Sound {
    public static final int SOUND_PLAYING = 0;
    public static final int SOUND_STOPPED = 1;
    public static final int SOUND_UNINITIALIZED = 2;

    private int state = SOUND_STOPPED;

    public Sound(byte[] data, int type) {
    }

    public void play(int loop) {
        state = SOUND_PLAYING;
    }

    public void stop() {
        state = SOUND_STOPPED;
    }

    public void release() {
        state = SOUND_STOPPED;
    }

    public int getState() {
        return state;
    }
}
