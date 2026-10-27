package javax.microedition.media;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.sound.sampled.*;

public class Manager {
    public static Player createPlayer(InputStream stream, String type) {
        return new SimplePlayer(stream, type);
    }

    private static class SimplePlayer implements Player {
        private byte[] audioData;
        private String type;
        private Clip clip;
        private int loopCount = 1;
        private int state = UNREALIZED;

        public SimplePlayer(InputStream stream, String type) {
            this.type = type;
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                int r;
                while ((r = stream.read(buf)) != -1) {
                    baos.write(buf, 0, r);
                }
                this.audioData = baos.toByteArray();
                this.state = REALIZED;
            } catch (Exception e) {
                this.state = CLOSED;
            }
        }

        public void realize() {
            if (state == UNREALIZED) state = REALIZED;
        }

        public void prefetch() {
            if (state < PREFETCHED && audioData != null) {
                try {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(new java.io.ByteArrayInputStream(audioData));
                    clip = AudioSystem.getClip();
                    clip.open(ais);
                    state = PREFETCHED;
                } catch (Exception e) {
                    state = PREFETCHED; // Soft fail, keep going
                }
            }
        }

        public void start() {
            if (clip != null) {
                try {
                    clip.setFramePosition(0);
                    if (loopCount == -1) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } else if (loopCount > 1) {
                        clip.loop(loopCount - 1);
                    } else {
                        clip.start();
                    }
                } catch (Exception e) {
                }
            }
            state = STARTED;
        }

        public void stop() {
            if (clip != null) {
                try {
                    clip.stop();
                } catch (Exception e) {
                }
            }
            state = PREFETCHED;
        }

        public void close() {
            stop();
            if (clip != null) {
                try {
                    clip.close();
                } catch (Exception e) {
                }
                clip = null;
            }
            state = CLOSED;
        }

        public void setLoopCount(int count) {
            this.loopCount = count;
        }

        public void setMediaTime(long now) {
            if (clip != null) {
                clip.setMicrosecondPosition(now);
            }
        }

        public int getState() {
            if (clip != null && !clip.isRunning() && state == STARTED) {
                state = PREFETCHED;
            }
            return state;
        }
    }
}
