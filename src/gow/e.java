package gow;

import com.nokia.mid.sound.Sound;
import java.io.ByteArrayInputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

/* JADX INFO: loaded from: gow.jar:e.class */
public final class e implements Runnable {
    private c a;
    private int b;
    private volatile boolean c;
    private int d = -1;
    private int e = -1;
    private int[] f;
    private int[] g;
    private Sound[] h;
    private Sound i;
    private Player[] j;
    private Player k;

    public final synchronized void a() {
        if (this.c) {
            return;
        }
        this.c = true;
        b(-1);
    }

    public final boolean b() {
        this.a.a(false);
        return !this.c && this.a.iy;
    }

    public final void a(boolean z) {
        if (b() != z) {
            c();
            this.a.iy = z;
            this.a.a(true);
        }
    }

    private static String a(byte b) {
        if (b == 1) {
            return "audio/x-wav";
        }
        if (b == 2) {
            return "audio/mpeg";
        }
        if (b == 3) {
            return "audio/amr";
        }
        if (b == 4) {
            return "audio/aac";
        }
        if (b == 7) {
            return "application/vnd.nokia.ringing-tone";
        }
        if (b == 8) {
            return "audio/x-tone-seq";
        }
        return b == 9 ? "audio/imelody" : "audio/midi";
    }

    @Override // java.lang.Runnable
    public final synchronized void run() {
        int i;
        if (!b() || (i = this.d) < 0) {
            return;
        }
        if (this.e >= 0) {
            c();
        }
        this.d = -1;
        Player player = this.j[i];
        if (player != null) {
            try {
                player.prefetch();
                try {
                    player.setMediaTime(0L);
                } catch (Throwable unused) {
                }
                try {
                    player.setLoopCount(this.f[i]);
                } catch (Throwable unused2) {
                }
                try {
                    player.start();
                    this.k = player;
                } catch (Throwable unused3) {
                    return;
                }
            } catch (Throwable unused4) {
                return;
            }
        } else {
            Sound sound = this.h[i];
            if (sound != null) {
                int i2 = this.f[i];
                sound.play(i2 == -1 ? 0 : i2);
            }
            this.i = sound;
        }
        this.e = i;
    }

    public final synchronized void a(int i, int i2, int i3, int i4) {
        if (this.c) {
            return;
        }
        if (this.h[i] != null || this.j[i] != null) {
            b(i);
        }
        this.f[i] = i3;
        this.g[i] = i4;
        try {
            byte[] bArrE = this.a.e(i2);
            byte b = this.a.iz[i2 & 1023];
            if (b == 7) {
                this.h[i] = new Sound(bArrE, 1);
                return;
            }
            this.j[i] = Manager.createPlayer(new ByteArrayInputStream(bArrE), a(b));
            this.j[i].realize();
            this.j[i].prefetch();
        } catch (Throwable unused) {
        }
    }

    private synchronized void b(int i) {
        if (i == -1) {
            for (int i2 = 0; i2 < this.b; i2++) {
                b(i2);
            }
            return;
        }
        if (this.e == i || this.d == i) {
            c();
        }
        if (this.j[i] != null) {
            this.j[i].close();
            this.j[i] = null;
        }
        if (this.h[i] != null) {
            this.h[i].release();
            this.h[i] = null;
        }
    }

    public final void a(int i) {
        if (b()) {
            if (this.h[i] == null && this.j[i] == null) {
                return;
            }
            if (this.d < 0 || this.g[this.d] <= this.g[i]) {
                int i2 = this.e;
                if (i2 >= 0) {
                    Player player = this.k;
                    if (player != null && player.getState() == 400 && this.g[i2] > this.g[i]) {
                        return;
                    }
                    Sound sound = this.i;
                    if (sound != null && sound.getState() == 0 && this.g[i2] > this.g[i]) {
                        return;
                    }
                }
                this.d = i;
            }
        }
    }

    public final synchronized void c() {
        if (this.k != null) {
            try {
                if (this.k.getState() == 400) {
                    this.k.stop();
                }
            } catch (Throwable unused) {
            }
            this.k = null;
        }
        if (this.i != null) {
            try {
                if (this.i.getState() == 0) {
                    this.i.stop();
                }
            } catch (Throwable unused2) {
            }
            this.i = null;
        }
        this.d = -1;
        this.e = -1;
    }

    public e(c cVar, int i) {
        this.a = cVar;
        this.b = i;
        this.f = new int[i];
        this.g = new int[i];
        this.h = new Sound[i];
        this.j = new Player[i];
        cVar.ix = this;
    }
}
