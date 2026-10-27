package gow;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: gow.jar:b.class */
public final class b {
    private final boolean a;
    private final int b;
    private final int c;
    private int d;
    private int e;
    private int f;
    private short[] g;
    private final boolean h;
    private Image i;
    private char[] j;
    private char[] k;
    private int l;
    private int m;
    private int n;
    private d[] o;
    private int[] p;
    private int[] q;
    private int r;
    private boolean s;
    private boolean t;
    private int[] u;
    private d[] v;
    private int[] w;
    private short[] x;
    private short[] y;
    private int z;
    private int A;
    private int B;
    private boolean C;

    public static b a(int i, int i2) {
        return new b(false, false, i, i2);
    }

    private b(boolean z, boolean z2, int i, int i2) {
        this.a = z;
        this.b = i;
        this.c = i2;
        this.h = z2;
        a(0);
    }

    public final void b(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public final void a(Graphics graphics) {
        if (this.a) {
            return;
        }
        b(graphics);
    }

    private void b(Graphics graphics) {
        int i = this.d;
        int i2 = this.e;
        int i3 = 0;
        int i4 = 0;
        int i5 = this.b;
        int i6 = this.c;
        int clipX = graphics.getClipX();
        if (clipX > 0) {
            i += clipX;
            i5 -= clipX;
            i3 = clipX;
        }
        int clipWidth = (i3 + i5) - (clipX + graphics.getClipWidth());
        if (clipWidth > 0) {
            i5 -= clipWidth;
        }
        int clipY = graphics.getClipY();
        if (clipY > 0) {
            i2 += clipY;
            i6 -= clipY;
            i4 = clipY;
        }
        int clipHeight = (i4 + i6) - (clipY + graphics.getClipHeight());
        if (clipHeight > 0) {
            i6 -= clipHeight;
        }
        if (i5 <= 0 || i6 <= 0) {
            return;
        }
        a(graphics, i, i2, i3, i4, i5, i6);
    }

    private void a(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6) {
        if (!this.C) {
            if (this.g != null) {
                b(graphics, i, i2, i3, i4, i5, i6);
            }
            if (this.n > 0) {
                a(i, i2, i3, i4, i5, i6, graphics);
            }
        }
        if (this.z >= 0) {
            a(i, i2, i3, i4, i5, i6, this.C, graphics);
        }
    }

    private void b(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = ((i + i5) - 1) / this.l;
        int i8 = i7 - (i / this.l);
        int i9 = ((i2 + i6) - 1) / this.m;
        int i10 = i7 + (i9 * this.f);
        int i11 = i9 - (i2 / this.m);
        int i12 = i % this.l;
        int i13 = i2 % this.m;
        int i14 = (-(i12 + i5)) % this.l;
        int i15 = i14;
        if (i14 < 0) {
            i15 += this.l;
        }
        int i16 = (-(i13 + i6)) % this.m;
        int i17 = i16;
        if (i16 < 0) {
            i17 += this.m;
        }
        int i18 = (i3 - i12) + (this.l * i8);
        int i19 = (i4 - i13) + (this.m * i11);
        for (int i20 = i11; i20 >= 0; i20--) {
            for (int i21 = i8; i21 >= 0; i21--) {
                int i22 = i10;
                i10 = i22 - 1;
                int i23 = this.g[i22] & 4095;
                if (i23 > 0) {
                    int i24 = i23 - 1;
                    int i25 = i21 == 0 ? i12 : 0;
                    int i26 = i21 == i8 ? i15 : 0;
                    int i27 = i20 == 0 ? i13 : 0;
                    int i28 = i20 == i11 ? i17 : 0;
                    if (!this.h) {
                        graphics.drawRegion(this.i, this.j[i24] + i25, this.k[i24] + i27, (this.l - i25) - i26, (this.m - i27) - i28, 0, i18 + i25, i19 + i27, 20);
                    }
                }
                i18 -= this.l;
            }
            i10 += (1 + i8) - this.f;
            i18 += this.l * (i8 + 1);
            i19 -= this.m;
        }
    }

    public final void a(int i, int i2, Image image, int i3, int i4) {
        a();
        this.f = i;
        this.g = new short[i * i2];
        this.l = i3;
        this.m = i4;
        int width = image.getWidth();
        int height = image.getHeight();
        int i5 = (width / this.l) * (height / this.m);
        int i6 = width - this.l;
        int i7 = height - this.m;
        if (this.h) {
            return;
        }
        this.i = image;
        this.j = new char[i5];
        this.k = new char[i5];
        int i8 = 0;
        for (int i10 = 0; i10 <= i7; i10 += this.m) {
            for (int i12 = 0; i12 <= i6; i12 += this.l) {
                this.j[i8] = (char) i12;
                this.k[i8] = (char) i10;
                i8++;
            }
        }
    }

    private void a() {
        this.g = null;
        if (this.h) {
            return;
        }
        this.i = null;
        this.j = null;
        this.k = null;
    }

    public final void a(int i, int i2, int i3) {
        this.g[(i2 * this.f) + i] = (short) i3;
    }

    public final void a(int i, boolean z) {
        this.n = 0;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = 0;
        this.s = true;
        this.u = null;
        if (i > 0) {
            this.t = z;
            this.o = new d[i];
            this.p = new int[i];
            this.q = new int[i];
            this.u = new int[i];
        }
    }

    public final void a(d dVar, int i, int i2, int i3) {
        this.o[this.n] = dVar;
        int iA = i + dVar.a(i3);
        int iB = i2 + dVar.b(i3);
        this.p[this.n] = (iA << 17) | ((iB << 17) >>> 15) | i3;
        this.q[this.n] = ((this.t ? iB : iA) << 16) | this.n;
        this.r = Math.max(this.r, (int) (this.t ? dVar.d : dVar.c));
        this.n++;
    }

    private void a(int i, int i2, int i3, int i4, int i5, int i6, Graphics graphics) {
        int clipX = graphics.getClipX();
        int clipY = graphics.getClipY();
        int clipWidth = graphics.getClipWidth();
        int clipHeight = graphics.getClipHeight();
        graphics.setClip(i3, i4, i5, i6);
        if (this.s) {
            gow.c.a(this.q, this.n);
            this.s = false;
        }
        int i7 = i5 + i;
        int i8 = i6 + i2;
        int i9 = this.t ? i8 : i7;
        int i10 = 0;
        for (int iA = gow.c.a(this.q, this.n - 1, ((this.t ? i2 : i) - this.r) << 16); iA < this.n; iA++) {
            int i11 = this.q[iA];
            if ((i11 >> 16) >= i9) {
                break;
            }
            int i12 = i11 & 65535;
            int i13 = this.p[i12];
            int i14 = i13 >> 17;
            int i15 = ((short) (i13 >> 1)) >> 1;
            if (i14 < i7 && i15 < i8) {
                d dVar = this.o[i12];
                if (i < i14 + dVar.c && i2 < i15 + dVar.d) {
                    int i16 = i10;
                    i10++;
                    this.u[i16] = i12;
                }
            }
        }
        gow.c.a(this.u, i10);
        int i17 = i - i3;
        int i18 = i2 - i4;
        for (int i19 = 0; i19 < i10; i19++) {
            int i20 = this.u[i19];
            d dVar2 = this.o[i20];
            int i21 = this.p[i20];
            int i22 = i21 & 3;
            dVar2.a(graphics, ((i21 >> 17) - dVar2.a(i22)) - i17, ((((short) (i21 >> 1)) >> 1) - dVar2.b(i22)) - i18, i22);
        }
        graphics.setClip(clipX, clipY, clipWidth, clipHeight);
    }

    public final void a(int i) {
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = -1;
        this.A = -1;
        this.B = -1;
        if (i > 0) {
            this.v = new d[i];
            this.w = new int[i];
            this.x = new short[i];
            int i2 = 0;
            while (i2 < i - 1) {
                int i3 = i2;
                i2++;
                this.x[i3] = (short) i2;
            }
            this.x[i - 1] = -1;
            this.y = new short[i];
            this.B = 0;
        }
    }

    public final int b(d dVar, int i, int i2, int i3) {
        int i4 = this.B;
        this.B = this.x[i4];
        if (this.z < 0) {
            this.z = i4;
        } else {
            this.x[this.A] = (short) i4;
        }
        this.x[i4] = -1;
        this.y[i4] = (short) this.A;
        this.A = i4;
        this.v[i4] = dVar;
        this.w[i4] = ((i + dVar.a(i3)) << 17) | (((i2 + dVar.b(i3)) << 17) >>> 15) | i3;
        return i4;
    }

    private void a(int i, int i2, int i3, int i4, int i5, int i6, boolean z, Graphics graphics) {
        int clipX = graphics.getClipX();
        int clipY = graphics.getClipY();
        int clipWidth = graphics.getClipWidth();
        int clipHeight = graphics.getClipHeight();
        graphics.setClip(i3, i4, i5, i6);
        int i7 = 0;
        if (!z && !this.a) {
            i7 = this.z;
        }
        int i8 = i5 + i;
        int i9 = i6 + i2;
        int i10 = i - i3;
        int i11 = i2 - i4;
        while (i7 >= 0) {
            int i12 = i7;
            i7 = this.x[i7];
            int i13 = this.w[i12];
            int i14 = i13 >> 17;
            int i15 = ((short) (i13 >> 1)) >> 1;
            if (i14 < i8 && i15 < i9) {
                d dVar = this.v[i12];
                if (i < i14 + dVar.c && i2 < i15 + dVar.d) {
                    int i16 = i13 & 3;
                    dVar.a(graphics, (i14 - dVar.a(i16)) - i10, (i15 - dVar.b(i16)) - i11, i16);
                }
            }
        }
        graphics.setClip(clipX, clipY, clipWidth, clipHeight);
    }
}
