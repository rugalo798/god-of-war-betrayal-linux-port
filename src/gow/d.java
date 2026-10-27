package gow;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: gow.jar:d.class */
public final class d {
    private Image i;
    public short a;
    public short b;
    public short c;
    public short d;
    public short e;
    public short f;
    public short g;
    public short h;
    private short j;
    private short k;

    public d(Image image) {
        this.i = image;
    }

    public final void a(short[] sArr) {
        this.a = sArr[0];
        this.b = sArr[1];
        this.j = sArr[2];
        this.k = sArr[3];
        this.c = sArr[4];
        this.d = sArr[5];
        this.e = sArr[6];
        this.f = sArr[7];
        this.g = sArr[8];
        this.h = sArr[9];
    }

    public final void a(Graphics graphics, int i, int i2, int i3) {
        int i4 = i3 & 3;
        int i5 = i + ((i4 & 2) == 0 ? this.a : -(this.a + this.c));
        int i6 = i2 + ((i4 & 1) == 0 ? this.b : -(this.b + this.d));
        int i7 = this.c;
        int i8 = this.d;
        short s = this.j;
        short s2 = this.k;
        if (s < 0) {
            i7 += s;
            i5 += s;
            s = 0;
        }
        if (s2 < 0) {
            i8 += s2;
            i6 += s2;
            s2 = 0;
        }
        int width = (s + i7) - this.i.getWidth();
        if (width > 0) {
            i7 -= width;
        }
        int height = (s2 + i8) - this.i.getHeight();
        if (height > 0) {
            i8 -= height;
        }
        if (i7 <= 0 || i8 <= 0) {
            return;
        }
        graphics.drawRegion(this.i, s, s2, i7, i8, i4, i5, i6, 20);
    }

    public final int a(int i) {
        return (i & 2) == 0 ? this.a : -(this.a + this.c);
    }

    public final int b(int i) {
        return (i & 1) == 0 ? this.b : -(this.b + this.d);
    }
}
