package gow;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

/* JADX INFO: loaded from: gow.jar:c.class */
public abstract class c extends GameCanvas implements Runnable {
    public int iu;
    public int iv;
    private long a;
    public MIDlet iw;
    public e ix;
    private boolean b;
    public boolean iy;
    private boolean c;
    private boolean d;
    private int e;
    private int[] f;
    private byte[] g;
    public byte[] iz;
    private int h;
    private byte[] i;
    private short[] j;
    private d[] k;
    private char[] l;
    private Font m;
    private short[] n;
    private int[][] o;
    private byte[][] p;
    private int[][] q;
    private char[][] r;
    private int[][] s;
    private int[][] t;
    private int[][] u;
    private char[][] v;
    private char[][] w;
    private DataInputStream x;
    private short[] y;
    private int z;
    private static final byte[] A;
    public volatile int iA;
    public volatile int iB;
    public volatile int iC;
    private volatile int B;
    private volatile int C;
    private volatile int D;
    public int iD;
    public int iE;
    public int iF;
    private boolean E;
    private int F;
    private int G;
    private int H;
    private int I;
    public boolean iG;
    private boolean J;
    private Thread K;
    private volatile boolean L;
    private volatile boolean M;
    private volatile long N;
    private volatile int O;
    private int P;

    public abstract void a(Graphics graphics);

    public abstract void a() throws InterruptedException;

    public static int a(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static void a(int[] iArr, int i) {
        int i2 = i;
        int i3 = i - 1;
        while (true) {
            if (i2 != 1) {
                i2 = (i2 * 197) >> 8;
            }
            boolean z = false;
            int i4 = i3 - i2;
            int i5 = i3;
            while (i4 >= 0) {
                if (iArr[i4] > iArr[i5]) {
                    int i6 = iArr[i4];
                    iArr[i4] = iArr[i5];
                    iArr[i5] = i6;
                    z = true;
                }
                i4--;
                i5--;
            }
            if (!z && i2 <= 1) {
                return;
            }
        }
    }

    public static byte[] c(int i) {
        byte[] bArr;
        RecordStore recordStore = null;
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore(String.valueOf(i), false);
            recordStore = recordStoreOpenRecordStore;
            int numRecords = recordStoreOpenRecordStore.getNumRecords();
            int recordSize = 0;
            for (int i2 = 1; i2 <= numRecords; i2++) {
                recordSize += recordStore.getRecordSize(i2);
            }
            bArr = new byte[recordSize];
            int record = 0;
            for (int i3 = 1; i3 <= numRecords; i3++) {
                record += recordStore.getRecord(i3, bArr, record);
            }
        } catch (Throwable unused) {
            bArr = null;
        }
        if (recordStore != null) {
            try {
                recordStore.closeRecordStore();
            } catch (Throwable unused2) {
            }
        }
        return bArr;
    }

    public static boolean a(int i, byte[] bArr) {
        String strValueOf = String.valueOf(i);
        RecordStore recordStoreOpenRecordStore = null;
        boolean z = true;
        try {
            String[] strArrListRecordStores = RecordStore.listRecordStores();
            if (strArrListRecordStores != null) {
                for (String str : strArrListRecordStores) {
                    if (strValueOf.equals(str)) {
                        RecordStore.deleteRecordStore(strValueOf);
                        break;
                    }
                }
            }
            if (bArr != null) {
                recordStoreOpenRecordStore = RecordStore.openRecordStore(strValueOf, true);
                int length = ((bArr.length + 786432) - 1) / 786432;
                int i2 = 0;
                for (int i3 = 1; i3 <= length; i3++) {
                    int length2 = bArr.length - i2;
                    int i4 = length2;
                    if (length2 > 786432) {
                        i4 = 786432;
                    }
                    recordStoreOpenRecordStore.addRecord(bArr, i2, i4);
                    i2 += i4;
                }
            }
        } catch (Throwable unused) {
            z = false;
        }
        if (recordStoreOpenRecordStore != null) {
            try {
                recordStoreOpenRecordStore.closeRecordStore();
            } catch (Throwable unused2) {
            }
        }
        return z;
    }

    public final synchronized void a(boolean z) {
        if (z || !this.b) {
            if (z) {
                byte[] bArr = new byte[3];
                bArr[0] = this.iy ? (byte) 1 : (byte) 0;
                bArr[1] = this.c ? (byte) 1 : (byte) 0;
                bArr[2] = this.d ? (byte) 1 : (byte) 0;
                a(-9, bArr);
                return;
            }
            this.b = true;
            byte[] bArrC = c(-9);
            byte[] bArr2 = bArrC;
            if (bArrC == null || bArr2.length != 3) {
                bArr2 = new byte[]{-1, -1, -1};
                a(-9, bArr2);
            }
            this.iy = bArr2[0] != 0;
            this.c = bArr2[1] != 0;
            this.d = bArr2[2] != 0;
        }
    }

    public final d[] d(int i) {
        return b(i, (byte[]) null);
    }

    public final d[] b(int i, byte[] bArr) {
        int i2;
        try {
            DataInputStream dataInputStreamF = f(i);
            char c = dataInputStreamF.readChar();
            dataInputStreamF.readByte();
            byte[] bArr2 = new byte[dataInputStreamF.readShort() - 4];
            char c2 = dataInputStreamF.readChar();
            int i3 = dataInputStreamF.readChar();
            dataInputStreamF.readFully(bArr2);
            boolean[] zArr = new boolean[16];
            for (int i4 = 0; i4 < 16; i4++) {
                zArr[i4] = 0 == ((c2 >> i4) & 1);
            }
            int i5 = 5;
            int i6 = 0;
            int[] iArr = new int[10];
            int i7 = 0;
            while (i7 < 3) {
                int limit = (i7 == 0 ? 2 : 4);
                for (int i8 = 0; i8 < limit; i8++) {
                    int i9 = i6;
                    i6++;
                    if (zArr[i7]) {
                        int i10 = i5;
                        i5++;
                        i2 = zArr[i10] ? 2 : 1;
                    } else {
                        i2 = 0;
                    }
                    iArr[i9] = i2;
                }
                i7++;
            }
            Image imageC = c(c, bArr);
            short[] sArr = new short[10];
            d[] dVarArr = new d[i3];
            for (int i11 = 0; i11 < i3; i11++) {
                dVarArr[i11] = new d(imageC);
                int i12 = 0;
                for (int i13 = 0; i13 < sArr.length; i13++) {
                    int i14 = 0;
                    int i15 = iArr[i13] - 1;
                    if (i15 >= 0) {
                        int i16 = i12 + (i11 << i15);
                        if (i15 == 0) {
                            i14 = bArr2[i16];
                            if (i13 > 1 && i13 != 6 && i13 != 7) {
                                i14 &= 255;
                            }
                        } else {
                            i14 = ((bArr2[i16] & 255) << 8) | (bArr2[i16 + 1] & 255);
                        }
                        i12 = i16 + ((i3 - i11) << i15);
                    }
                    sArr[i13] = (short) i14;
                }
                dVarArr[i11].a(sArr);
            }
            return dVarArr;
        } catch (Exception unused) {
            return null;
        }
    }

    public final Image c(int i, byte[] bArr) {
        k(i);
        int i2 = i & 1023;
        int iB = b(i2);
        a(this.g, iB, bArr);
        Image imageCreateImage = Image.createImage(this.g, iB, this.f[i2]);
        a(this.g, iB, bArr);
        return imageCreateImage;
    }

    private void a(byte[] bArr, int i, byte[] bArr2) {
        if (bArr2 != null) {
            try {
                b(new DataInputStream(new ByteArrayInputStream(bArr, i, bArr.length - i)));
                int i2 = 0;
                int i3 = i + this.h;
                while (i2 < bArr2.length) {
                    byte b = bArr[i3];
                    int i4 = i3;
                    i3++;
                    bArr[i4] = bArr2[i2];
                    int i5 = i2;
                    i2++;
                    bArr2[i5] = b;
                }
            } catch (IOException unused) {
            }
        }
    }

    private int b(DataInputStream dataInputStream) throws IOException {
        dataInputStream.skip(8L);
        this.h = 8;
        while (true) {
            int i = dataInputStream.readInt();
            int i2 = dataInputStream.readInt();
            this.h += 8;
            if (1347179589 == i2) {
                return i;
            }
            if (1229278788 == i2) {
                return -1;
            }
            dataInputStream.skip(i + 4);
            this.h += i + 4;
        }
    }

    public final byte[] e(int i) {
        k(i);
        int i2 = i & 1023;
        byte[] bArr = new byte[this.f[i2]];
        System.arraycopy(this.g, b(i2), bArr, 0, bArr.length);
        return bArr;
    }

    public final DataInputStream f(int i) {
        k(i);
        int i2 = i & 1023;
        return new DataInputStream(new ByteArrayInputStream(this.g, b(i2), this.f[i2]));
    }

    private int b(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            i--;
            if (i < 0) {
                return i3;
            }
            i2 = i3 + this.f[i];
        }
    }

    private void k(int arg0) {
        int arg1 = arg0 >> 10;
        if (arg1 == 0 || arg1 == this.e) {
            return;
        }
        if (arg1 > 0) {
            try {
                getClass();
                DataInputStream dataInputStream = new DataInputStream(c.class.getResourceAsStream(new StringBuffer().append("/RP").append(arg1).toString()));
                a(dataInputStream);
                dataInputStream.close();
            } catch (Exception unused) {
            }
        } else {
            a((DataInputStream) null);
        }
        this.e = arg1;
    }

    public final boolean a(DataInputStream dataInputStream) {
        this.f = null;
        this.iz = null;
        this.g = null;
        if (dataInputStream == null) {
            this.e = -1;
            return false;
        }
        this.e = 0;
        try {
            int i = (1 + (dataInputStream.read() & 1)) << 1;
            int i2 = dataInputStream.readChar();
            byte[] bArr = new byte[i * i2];
            dataInputStream.readFully(bArr);
            this.f = new int[i2];
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                for (int i5 = i - 1; i5 >= 0; i5--) {
                    int[] iArr = this.f;
                    int i6 = i4;
                    int i7 = i3;
                    i3++;
                    iArr[i6] = iArr[i6] | ((255 & bArr[i7]) << (i5 << 3));
                }
            }
            this.iz = new byte[i2];
            dataInputStream.readFully(this.iz);
            this.g = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(this.g);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String a(byte[] bArr, int i, int i2) {
        int i3;
        char[] cArr = new char[i2];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2 && (i3 = bArr[i + i4] & 255) <= 127) {
            i4++;
            int i6 = i5;
            i5++;
            cArr[i6] = (char) i3;
        }
        while (i4 < i2) {
            int i7 = bArr[i + i4] & 255;
            switch (i7 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    i4++;
                    int i8 = i5;
                    i5++;
                    cArr[i8] = (char) i7;
                    break;
                case 12:
                case 13:
                    i4 += 2;
                    int i9 = i5;
                    i5++;
                    cArr[i9] = (char) (((i7 & 31) << 6) | (bArr[(i + i4) - 1] & 63));
                    break;
                case 14:
                    i4 += 3;
                    int i10 = i5;
                    i5++;
                    cArr[i10] = (char) (((i7 & 15) << 12) | ((bArr[(i + i4) - 2] & 63) << 6) | ((bArr[(i + i4) - 1] & 63) << 0));
                    break;
            }
        }
        return new String(cArr, 0, i5);
    }

    public final String g(int i) {
        short s = this.j[i];
        try {
            return a(this.i, (int) s, this.j[i + 1] - s);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void h(int i) {
        if (i == -1) {
            this.i = null;
            this.j = null;
            return;
        }
        try {
            DataInputStream dataInputStreamF = f(i);
            this.j = new short[dataInputStreamF.readShort() + 1];
            this.i = new byte[this.f[i & 1023] - (this.j.length << 1)];
            short s = 0;
            int i2 = 0;
            while (i2 < this.j.length - 1) {
                this.j[i2] = s;
                short s2 = dataInputStreamF.readShort();
                if (s2 > 0) {
                    dataInputStreamF.readFully(this.i, s, s2);
                }
                s = (short) (s + s2);
                i2++;
            }
            this.j[i2] = s;
        } catch (Exception unused) {
        }
    }

    public static void a(Graphics graphics, int[] iArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        b(graphics, iArr, i, i2, i3, i4, i5, i6, z);
    }

    public static void b(Graphics graphics, int[] iArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        graphics.drawRGB(iArr, i, i2, i3, i4, i5, i6, z);
    }

    public final void a(d[] dVarArr) {
        this.k = dVarArr;
        if (this.k == null || dVarArr[3].h != 255) {
            return;
        }
        dVarArr[3].h = (short) 0;
        short s = 0;
        for (d dVar : dVarArr) {
            s = (short) (s + (dVar.a << 8) + (dVar.b & 255));
            dVar.a = dVar.e;
            dVar.b = dVar.f;
            dVar.e = s;
        }
    }

    private void l(int i) {
        if (this.l == null || this.l.length < i) {
            this.l = new char[i];
        }
    }

    public final int a(String str) {
        return a(str, 0, str.length());
    }

    public final int a(String str, int i, int i2) {
        l(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            this.l[i3] = str.charAt(i + i3);
        }
        return a(this.l, 0, i2);
    }

    public final int a(char c) {
        l(1);
        this.l[0] = c;
        return a(this.l, 0, 1);
    }

    public final int a(char[] cArr, int i, int i2) {
        if (this.k == null) {
            if (i2 == 0) {
                return 0;
            }
            return this.m.charsWidth(cArr, i, i2);
        }
        int i3 = i2 + i;
        int i4 = 0;
        while (i < i3) {
            d dVarB = b(cArr[i]);
            i4 += dVarB != null ? dVarB.g : this.k[2].h;
            i++;
        }
        return i4;
    }

    private d b(char c) {
        int i = 0;
        int length = this.k.length - 1;
        while (i <= length) {
            int i2 = (i + length) >> 1;
            char c2 = (char) this.k[i2].e;
            if (c2 < c) {
                i = i2 + 1;
            } else {
                if (c2 <= c) {
                    return this.k[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public final int b() {
        return this.k != null ? this.k[1].h : this.m.getBaselinePosition();
    }

    public final int c() {
        return this.k != null ? this.k[0].h : this.m.getHeight();
    }

    public final void a(Graphics graphics, String str, int i, int i2, int i3) {
        a(graphics, str, 0, str.length(), i, i2, i3);
    }

    public final void a(Graphics graphics, String str, int i, int i2, int i3, int i4, int i5) {
        l(i2);
        for (int i6 = 0; i6 < i2; i6++) {
            this.l[i6] = str.charAt(i + i6);
        }
        a(graphics, this.l, 0, i2, i3, i4, i5);
    }

    public final void a(Graphics graphics, char[] cArr, int i, int i2, int i3, int i4, int i5) {
        short s;
        if ((i5 & 64) != 0) {
            i4 -= b();
        } else if ((i5 & 32) != 0) {
            i4 -= c();
        }
        if ((i5 & 9) != 0) {
            i3 -= a(cArr, i, i2) >> (i5 & 1);
        }
        if (this.k == null) {
            if (i2 != 0) {
                if (graphics.getFont() != this.m) {
                    graphics.setFont(this.m);
                }
                graphics.drawChars(cArr, i, i2, i3, i4, 20);
                return;
            }
            return;
        }
        int i6 = i2 + i;
        while (i < i6) {
            d dVarB = b(cArr[i]);
            if (dVarB != null) {
                dVarB.a(graphics, i3, i4, 0);
                s = dVarB.g;
            } else {
                s = this.k[2].h;
            }
            i3 += s;
            i++;
        }
    }

    public static int a(int[] iArr, int i, int i2) {
        int i3 = 0;
        while (i3 <= i) {
            int i4 = (i3 + i) >> 1;
            int i5 = iArr[i4];
            if (i5 < i2) {
                i3 = i4 + 1;
            } else {
                if (i5 <= i2) {
                    return i4;
                }
                i = i4 - 1;
            }
        }
        return i + 1;
    }

    public final void i(int i) {
        if (this.n != null) {
            for (int i2 = 0; i2 < this.n.length; i2++) {
                if (i == (this.n[i2] & Short.MAX_VALUE)) {
                    return;
                }
            }
        }
        this.n = a(this.n, (short) i);
        int length = this.n.length - 1;
        try {
            this.x = f(i);
            this.x.read();
            int iG = this.x.readChar();
            this.o = a(this.o, iG);
            int[] iArr = this.o[length];
            int iA = a(iArr);
            for (int i3 = 0; i3 < iG; i3++) {
                int i4 = i3;
                iArr[i4] = iArr[i4] | ((63 & h()) << 10);
            }
            this.p = a(this.p, iA);
            byte[] bArr = this.p[length];
            this.q = a(this.q, iA);
            int[] iArr2 = this.q[length];
            for (int i5 = 0; i5 < iA; i5++) {
                iG = g();
                bArr[i5] = (byte) iG;
                iArr2[i5] = iG >> 8;
            }
            b(iArr2);
            char c = this.x.readChar();
            this.r = a(this.r, (int) c);
            this.s = a(this.s, c);
            int iA2 = a(this.s[length]);
            this.t = a(this.t, iA2);
            int[] iArr3 = this.t[length];
            int i6 = 0;
            for (int i7 = 0; i7 < iA2; i7++) {
                if (i6 == 0) {
                    iG = g();
                    i6 = 8;
                }
                i6 -= 2;
                iArr3[i7] = 3 & (iG >> i6);
            }
            b(iArr3);
            int i8 = 0;
            for (int i9 = 0; i9 < c; i9++) {
                int iG2 = g();
                int[] iArr4 = this.s[length];
                int i10 = i9;
                iArr4[i10] = iArr4[i10] | (iG2 << 8);
                this.r[length][i9] = (char) i8;
                i8 += iG2;
            }
            this.u = a(this.u, i8);
            this.v = a(this.v, i8);
            int[] iArr5 = this.u[length];
            char[] cArr = this.v[length];
            int i11 = 0;
            while (i11 < i8) {
                int i12 = i11;
                i11++;
                iArr5[i12] = h() << 21;
            }
            int i13 = 0;
            while (i13 < i8) {
                int i14 = i13;
                i13++;
                iArr5[i14] = iArr5[i14] | ((h() << 21) >>> 11);
            }
            int i15 = 0;
            while (i15 < i8) {
                int i16 = i15;
                i15++;
                iArr5[i16] = iArr5[i16] | g();
            }
            int i17 = 0;
            while (i17 < i8) {
                int i18 = i17;
                i17++;
                cArr[i18] = (char) (g() << 6);
            }
            int i19 = 0;
            while (i19 < i8) {
                int i20 = i19;
                i19++;
                cArr[i20] = (char) (cArr[i20] | ((char) g()));
            }
            char c2 = this.x.readChar();
            this.w = a(this.w, (int) c2);
            for (int i21 = 0; i21 < c2; i21++) {
                this.w[length][i21] = (char) g();
            }
        } catch (Exception unused) {
        }
        this.x = null;
    }

    private int a(int[] iArr) throws Exception {
        int i = 0;
        int i2 = 0;
        while (i2 < iArr.length) {
            int iG = g();
            int i3 = i2;
            i2++;
            iArr[i3] = (i << 16) | iG;
            i += iG;
        }
        return i;
    }

    private void b(int[] iArr) throws Exception {
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i;
            i++;
            iArr[i2] = iArr[i2] | (g() << 22);
        }
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3;
            i3++;
            iArr[i4] = iArr[i4] | ((h() << 22) >>> 10);
        }
        int i5 = 0;
        while (i5 < length) {
            int i6 = i5;
            i5++;
            iArr[i6] = iArr[i6] | ((h() << 22) >>> 20);
        }
    }

    private int g() throws Exception {
        int i = this.x.read();
        return i == 255 ? this.x.readChar() : i;
    }

    private int h() throws Exception {
        byte b = this.x.readByte();
        return b == -128 ? this.x.readShort() : b;
    }

    private static short[] a(short[] sArr, short s) {
        if (sArr == null) {
            sArr = new short[0];
        }
        short[] sArr2 = new short[sArr.length + 1];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        sArr2[sArr.length] = s;
        return sArr2;
    }

    private static int[][] a(int[][] iArr, int i) {
        if (iArr == null) {
            iArr = new int[0][];
        }
        int[][] iArr2 = new int[iArr.length + 1][];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        iArr2[iArr.length] = new int[i];
        return iArr2;
    }

    private static byte[][] a(byte[][] bArr, int i) {
        if (bArr == null) {
            bArr = new byte[0][];
        }
        byte[][] bArr2 = new byte[bArr.length + 1][];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = new byte[i];
        return bArr2;
    }

    private static char[][] a(char[][] cArr, int i) {
        if (cArr == null) {
            cArr = new char[0][];
        }
        char[][] cArr2 = new char[cArr.length + 1][];
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        cArr2[cArr.length] = new char[i];
        return cArr2;
    }

    private static short[] a(short[] sArr, int i) {
        if (sArr == null) {
            sArr = new short[0];
        }
        if (i > sArr.length) {
            short[] sArr2 = new short[i];
            System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
            sArr = sArr2;
        }
        return sArr;
    }

    public final void a(int i, int i2, int i3) {
        int i4 = -1;
        do {
            i4++;
        } while (i2 != (this.n[i4] & Short.MAX_VALUE));
        int i5 = i * 8;
        this.y = a(this.y, i5 + 8);
        this.y[i5 + 0] = (short) i4;
        this.y[i5 + 1] = (short) i3;
        this.y[i5 + 2] = 0;
        this.y[i5 + 3] = (short) ((this.o[i4][i3] << 16) >> 26);
        this.y[i5 + 4] = 0;
        this.y[i5 + 5] = 0;
        this.y[i5 + 6] = 0;
        this.y[i5 + 7] = 0;
    }

    public final void a(int i, int i2) {
        int i3 = i2 * 8;
        this.y = a(this.y, i3 + 8);
        System.arraycopy(this.y, i * 8, this.y, i3, 8);
    }

    public final boolean b(int i, int i2) {
        char c;
        int i3;
        int i4;
        int i5;
        int i6 = i * 8;
        short s = this.y[i6 + 3];
        int i7 = s;
        if (s == 0) {
            this.iu = 0;
            this.iv = 0;
            return false;
        }
        short s2 = this.y[i6 + 0];
        int i8 = this.y[i6 + 2];
        int i9 = i2 + (this.y[i6 + 4] & 65535);
        int i10 = this.y[i6 + 5];
        int i11 = this.y[i6 + 6];
        int i12 = this.o[s2][this.y[i6 + 1]];
        int i13 = i12 >>> 16;
        int i14 = i12 & 1023;
        int i15 = i8;
        int i16 = i8 == this.y[i6 + 7] ? 0 : (this.p[s2][i13 + i15] >> 6) & 3;
        while (true) {
            int i17 = i9;
            c = this.w[s2][this.p[s2][i13 + i8] & 63];
            if (i17 < c) {
                break;
            }
            i9 -= c;
            i8++;
            if (i8 == i14) {
                if (i7 > 0) {
                    i7--;
                    if (i7 == 0) {
                        i8--;
                        i9 = c;
                        break;
                    }
                }
                i8 = 0;
                int i18 = this.q[s2][i13 + (i14 - 1)];
                i10 -= (i18 << 10) >> 22;
                i11 -= (i18 << 20) >> 22;
            }
            int i19 = (this.p[s2][i13 + i8] >> 6) & 3;
            if (i19 >= i16) {
                i16 = i19;
                i15 = i8;
            }
        }
        int i20 = this.q[s2][i13 + i8];
        int i21 = (i20 << 10) >> 22;
        int i22 = (i20 << 20) >> 22;
        if ((i20 & 3) > 0 && i7 != 0 && (i8 + 1 != i14 || i7 != 1)) {
            if (i8 + 1 == i14) {
                int i23 = this.q[s2][i13 + i8];
                i3 = (i23 << 10) >> 22;
                i4 = (i23 << 20) >> 22;
                i5 = 0;
            } else {
                i3 = 0;
                i4 = 0;
                i5 = i8 + 1;
            }
            int i24 = this.q[s2][i13 + i5];
            int i25 = i3 + ((i24 << 10) >> 22);
            int i26 = i4 + ((i24 << 20) >> 22);
            int i27 = (i9 << 12) / c;
            i21 += ((i25 - i21) * i27) >> 12;
            i22 += ((i26 - i22) * i27) >> 12;
        }
        this.iu = i21 - i10;
        this.iv = i22 - i11;
        this.y[i6 + 2] = (short) i8;
        this.y[i6 + 3] = (short) i7;
        this.y[i6 + 4] = (short) i9;
        this.y[i6 + 5] = (short) i21;
        this.y[i6 + 6] = (short) i22;
        this.y[i6 + 7] = (short) i15;
        return i7 != 0;
    }

    public final void a(d[] dVarArr, int i, int i2, int i3, int i4, int[] iArr) {
        // m(i) updates this.z, so resolve it before selecting the resource arrays.
        int indice = m(i);
        int i5 = this.s[this.z][indice];
        int i6 = i5 >>> 16;
        int[] iArr2 = this.t[this.z];
        int i7 = i6 + (i5 & 255);
        int iMin = Integer.MAX_VALUE;
        int iMin2 = Integer.MAX_VALUE;
        int iMax = Integer.MIN_VALUE;
        int iMax2 = Integer.MIN_VALUE;
        while (true) {
            int i8 = iMax2;
            if (i6 >= i7) {
                iArr[0] = i2 + iMin;
                iArr[1] = i3 + iMin2;
                iArr[2] = iMax - iMin;
                iArr[3] = i8 - iMin2;
                return;
            }
            int i9 = i6;
            i6++;
            int i10 = iArr2[i9];
            int i11 = (i10 & 3) ^ i4;
            d dVar = dVarArr[i10 >>> 22];
            int i12 = dVar.a + ((i10 << 10) >> 22);
            if ((i11 & 2) != 0) {
                i12 = -(i12 + dVar.c);
            }
            int i13 = dVar.b + ((i10 << 20) >> 22);
            if ((i11 & 1) != 0) {
                i13 = -(i13 + dVar.d);
            }
            iMin = Math.min(iMin, i12);
            iMin2 = Math.min(iMin2, i13);
            iMax = Math.max(iMax, i12 + dVar.c);
            iMax2 = Math.max(i8, i13 + dVar.d);
        }
    }

    public final int a(short[] sArr, int i, int i2) {
        // m(i) updates this.z, so resolve it before selecting the resource arrays.
        int indice = m(i);
        int i3 = this.s[this.z][indice];
        int i4 = i3 >>> 16;
        int[] iArr = this.t[this.z];
        int i5 = i3 & 255;
        int i6 = i4 + i5;
        int i7 = 0;
        while (i4 < i6 && i7 + 3 < sArr.length) {
            int i8 = i4;
            i4++;
            int i9 = iArr[i8];
            int i10 = i7;
            int i11 = i7 + 1;
            sArr[i10] = (short) (i9 >>> 22);
            int i12 = (i9 & 3) ^ i2;
            int i13 = i11 + 1;
            sArr[i11] = (short) (((i12 & 2) == 0 ? 1 : -1) * ((i9 << 10) >> 22));
            int i14 = i13 + 1;
            sArr[i13] = (short) (((i12 & 1) == 0 ? 1 : -1) * ((i9 << 20) >> 22));
            i7 = i14 + 1;
            sArr[i14] = (short) i12;
        }
        return i5;
    }

    public final void a(Graphics graphics, d[] dVarArr, int i, int i2, int i3, int i4) {
        // m(i) updates this.z, so resolve it before selecting the resource arrays.
        int indice = m(i);
        int i5 = this.s[this.z][indice];
        int i6 = i5 >>> 16;
        int[] iArr = this.t[this.z];
        int i7 = i6 + (i5 & 255);
        while (i6 < i7) {
            int i8 = i6;
            i6++;
            int i9 = iArr[i8];
            int i10 = (i9 << 10) >> 22;
            int i11 = (i9 << 20) >> 22;
            d dVar = dVarArr[i9 >>> 22];
            dVar.a = (short) (dVar.a + i10);
            dVar.b = (short) (dVar.b + i11);
            dVar.a(graphics, i2, i3, (i9 & 3) ^ i4);
            dVar.a = (short) (dVar.a - i10);
            dVar.b = (short) (dVar.b - i11);
        }
    }

    public final int b(short[] sArr, int i, int i2) {
        int iM = m(i);
        int i3 = this.r[this.z][iM];
        int i4 = (this.s[this.z][iM] >> 8) & 255;
        int i5 = i3 + i4;
        int i6 = 0;
        while (i3 < i5 && i6 + 4 < sArr.length) {
            int i7 = this.u[this.z][i3];
            int i8 = i7 >> 21;
            int i9 = (i7 << 11) >> 21;
            int i10 = i7 & 1023;
            int i11 = i3;
            i3++;
            char c = this.v[this.z][i11];
            int i12 = c >> 6;
            int i13 = c & '?';
            if ((i2 & 2) != 0) {
                i8 = -(i8 + i10);
            }
            if ((i2 & 1) != 0) {
                i9 = -(i9 + i12);
            }
            int i14 = i6;
            int i15 = i6 + 1;
            sArr[i14] = (short) i8;
            int i16 = i15 + 1;
            sArr[i15] = (short) i9;
            int i17 = i16 + 1;
            sArr[i16] = (short) i10;
            int i18 = i17 + 1;
            sArr[i17] = (short) i12;
            i6 = i18 + 1;
            sArr[i18] = (short) i13;
        }
        return i4;
    }

    private int m(int i) {
        int i2 = i * 8;
        this.z = this.y[i2 + 0];
        return this.q[this.z][(this.o[this.z][this.y[i2 + 1]] >>> 16) + this.y[i2 + 7]] >>> 22;
    }

    public final synchronized void d() {
        this.B = 0;
        this.C = 0;
        this.D |= this.iA;
        this.iB = 0;
        this.iA = 0;
        this.iC = 0;
    }

    public final synchronized void e() {
        this.iB = this.B;
        this.B = 0;
        this.D &= this.C;
        this.C &= this.D ^ (-1);
        this.iA = this.iB | this.C;
        this.iC = this.D;
        this.D = 0;
    }

    private void a(int i, boolean z) {
        if (i != 0) {
            synchronized (this) {
                for (int i2 = 1; i2 < A.length; i2++) {
                    if (A[i2] == i) {
                        int i3 = 1 << i2;
                        if (z) {
                            this.B |= i3;
                            this.C |= i3;
                            this.D &= i3 ^ (-1);
                        } else {
                            this.D |= i3;
                        }
                    }
                }
            }
        }
    }

    public void keyPressed(int i) {
        a(i, true);
        super/*javax.microedition.lcdui.Canvas*/.keyPressed(i);
    }

    public void keyReleased(int i) {
        a(i, false);
        super/*javax.microedition.lcdui.Canvas*/.keyReleased(i);
    }

    public void keyRepeated(int i) {
        super/*javax.microedition.lcdui.Canvas*/.keyRepeated(i);
    }

    private void c(Graphics graphics) {
        if (graphics == null) {
            return;
        }
        this.iG = this.J;
        this.J = false;
        if ((240 == this.F && 320 == this.G) ? false : true) {
            if (this.iG) {
                int i = ((320 - this.G) + 1) >>> 1;
                graphics.setColor(-16777216);
                if (i > 0) {
                    if (this.I > 0) {
                        graphics.fillRect(0, 0, 240, this.I);
                    }
                    graphics.fillRect(0, 320 - i, 240, i);
                }
                int i2 = ((240 - this.F) + 1) >>> 1;
                if (i2 > 0) {
                    if (this.H > 0) {
                        graphics.fillRect(0, this.I, this.H, this.G);
                    }
                    graphics.fillRect(240 - i2, this.I, i2, this.G);
                }
            }
            graphics.translate(this.H, this.I);
        }
        b(graphics);
        a(graphics);
    }

    public final void c(int i, int i2) {
        this.J = true;
        if (i <= 0) {
            i = 240;
        }
        if (i2 <= 0) {
            i2 = 320;
        }
        this.F = i;
        this.G = i2;
        this.H = 240 - i;
        this.I = 320 - i2;
        if (this.H < 0) {
            this.H++;
        }
        if (this.I < 0) {
            this.I++;
        }
        this.H >>= 1;
        this.I >>= 1;
    }

    public final void b(Graphics graphics) {
        graphics.setClip(0, 0, this.F, this.G);
    }

    public final void a(Graphics graphics, int i, int i2, int i3, int i4) {
        if ((this.H | this.I) != 0) {
            if (i < 0) {
                i3 += i;
                i = 0;
            }
            int i5 = (i + i3) - this.F;
            if (i5 > 0) {
                i3 -= i5;
            }
            if (i2 < 0) {
                i4 += i2;
                i2 = 0;
            }
            int i6 = (i2 + i4) - this.G;
            if (i6 > 0) {
                i4 -= i6;
            }
        }
        graphics.setClip(i, i2, i3, i4);
    }

    public final void f() {
        this.J = true;
    }

    public void a(int i) throws InterruptedException {
        if (i == 3) {
            if (this.ix != null) {
                this.ix.a();
            }
            this.M = true;
            this.iw.notifyDestroyed();
        } else if (i == 2) {
            try {
                Thread.sleep(1000L);
            } catch (Exception unused) {
            }
        }
        if (i == 0 || i == 1 || i == 2) {
            this.J = true;
            this.E = true;
            d();
        }
    }

    public final void hideNotify() throws InterruptedException {
        j(1);
    }

    public final void showNotify() throws InterruptedException {
        j(2);
        if (this.K == null) {
            this.K = new Thread(this);
            this.K.start();
        }
    }

    public final synchronized void j(int i) throws InterruptedException {
        if (i == 3) {
            a(i);
            return;
        }
        if (this.L) {
            if (i == 1 || i == 2) {
                synchronized (this.K) {
                    this.O = i;
                    this.N = System.currentTimeMillis();
                }
            }
        }
    }

    private void i() throws InterruptedException {
        while (this.O != -1) {
            if (this.P != 1) {
                if (this.ix != null) {
                    this.ix.c();
                }
                a(1);
                this.P = 1;
            }
            synchronized (this.K) {
                if (this.O == 1) {
                    this.O = -1;
                }
            }
            while (this.O == 2 && isShown()) {
                if (((int) (System.currentTimeMillis() - this.N)) < 750) {
                    try {
                        Thread.sleep(250L);
                        Thread.yield();
                    } catch (Exception unused) {
                    }
                } else {
                    synchronized (this.K) {
                        if (this.O == 2) {
                            this.O = -1;
                            if (this.ix != null) {
                                this.ix.c();
                            }
                            a(2);
                            this.P = 2;
                            a(5);
                        }
                    }
                }
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.L = true;
            a(0);
            while (!this.M) {
                i();
                if (isShown()) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (this.E) {
                        this.E = false;
                        this.iD = this.iE;
                    } else {
                        int i = (int) (jCurrentTimeMillis - this.a);
                        int i2 = i;
                        if (i > this.iF || i2 < 0) {
                            i2 = this.iF;
                        }
                        this.iD = i2;
                        if (this.iD < this.iE) {
                            int i3 = this.iE - this.iD;
                            int i4 = i3;
                            if (i3 < 10) {
                                i4 = 10;
                            }
                            this.iD = this.iE;
                            try {
                                Thread.sleep(i4);
                            } catch (Exception unused) {
                            }
                        }
                    }
                    this.a = jCurrentTimeMillis;
                    a();
                    if (this.ix != null) {
                        this.ix.run();
                    }
                    if (this.M) {
                        return;
                    }
                    if (isShown()) {
                        c(getGraphics());
                        flushGraphics();
                    }
                } else {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception unused2) {
                    }
                    this.E = true;
                }
                Thread.yield();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public c(MIDlet mIDlet) {
        super(false);
        this.e = -1;
        this.m = Font.getFont(0, 0, 0);
        this.iD = 10;
        this.iE = 10;
        this.iF = 100;
        this.O = -1;
        this.P = 2;
        setFullScreenMode(true);
        this.iw = mIDlet;
        c(240, 320);
    }

    static {
        System.currentTimeMillis();
        A = new byte[32];
        A[1] = -1;
        A[2] = -3;
        A[3] = 35;
        A[4] = -8;
        A[5] = -4;
        A[6] = -2;
        A[8] = -5;
        A[10] = 42;
        A[13] = -36;
        A[14] = -37;
        A[15] = -11;
        for (int i = 0; i < 10; i++) {
            A[16 + i] = (byte) (48 + i);
        }
        A[27] = -6;
        A[29] = -7;
    }
}
