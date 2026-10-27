package gow;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

/* JADX INFO: loaded from: gow.jar:_B_B.class */
public class _B_B extends Canvas implements Runnable {
    private int a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    private long f0a;
    private long b;
    public static MIDlet _d;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    private Image f1a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    private byte f2a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    private final byte[] f3a;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    private int f4b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    /* JADX INFO: renamed from: c, reason: collision with other field name */
    private long f5c;
    private int i;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    private byte[] f6b;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    private int[] f7a;
    public static int[] _BB = new int[20];
    private int _CC;

    private final void a(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.f3a[i] = (byte) i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            i2 = (i2 + this.f3a[i3] + bArr[i3 % bArr.length]) & 255;
            byte b = this.f3a[i3];
            this.f3a[i3] = this.f3a[i2];
            this.f3a[i2] = b;
        }
        this.f4b = 0;
        this.c = 0;
    }

    private final void a(byte[] bArr, int i, int i2) {
        a(bArr, i, bArr, i, i2);
    }

    private final void a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4 = this.f4b;
        int i5 = this.c;
        for (int i6 = 0; i6 < i3; i6++) {
            i4 = (i4 + 1) & 255;
            i5 = (i5 + this.f3a[i4]) & 255;
            byte b = this.f3a[i4];
            this.f3a[i4] = this.f3a[i5];
            this.f3a[i5] = b;
            bArr2[i2 + i6] = (byte) (bArr[i + i6] ^ this.f3a[(this.f3a[i4] + this.f3a[i5]) & 255]);
        }
        this.f4b = i4;
        this.c = i5;
    }

    private final void a() {
        this.d = 1732584193;
        this.e = -271733879;
        this.f = -1732584194;
        this.g = 271733878;
        this.h = 0;
        this.f5c = 0L;
        this.i = 0;
        this.h = -1009589776;
        this.f6b = new byte[64];
        this.f7a = new int[80];
    }

    private final void b(byte[] bArr, int i, int i2) {
        int i3;
        if (this.i > 0 && i2 >= (i3 = 64 - this.i)) {
            System.arraycopy(bArr, i, this.f6b, this.i, i3);
            b(this.f6b, 0);
            this.i = 0;
            i += i3;
            i2 -= i3;
        }
        while (i2 >= 64) {
            b(bArr, i);
            i += 64;
            i2 -= 64;
        }
        System.arraycopy(bArr, i, this.f6b, this.i, i2);
        this.i += i2;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    private final byte[] m2a() {
        byte[] bArr = new byte[20];
        a(bArr, 0);
        return bArr;
    }

    private final void a(byte[] bArr, int i) {
        byte[] bArr2 = this.f6b;
        if (bArr2 != null) {
            int i2 = this.i;
            long j = this.f5c + ((long) (i2 << 3));
            int i3 = i2 + 1;
            bArr2[i2] = -128;
            if (i3 > 56) {
                while (i3 < 64) {
                    int i4 = i3;
                    i3++;
                    bArr2[i4] = 0;
                }
                b(bArr2, 0);
                i3 = 0;
            }
            while (i3 < 56) {
                int i5 = i3;
                i3++;
                bArr2[i5] = 0;
            }
            c(bArr2, 56, (int) (j >> 32));
            c(bArr2, 60, (int) j);
            b(bArr2, 0);
            this.f6b = null;
            this.f7a = null;
        }
        c(bArr, i, this.d);
        c(bArr, i + 4, this.e);
        c(bArr, i + 8, this.f);
        c(bArr, i + 12, this.g);
        c(bArr, i + 16, this.h);
    }

    private final void b(byte[] bArr, int i) {
        int[] iArr = this.f7a;
        int i2 = 0;
        while (i2 < 16) {
            iArr[i2] = (bArr[i] << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
            i2++;
            i += 4;
        }
        for (int i3 = 16; i3 < 80; i3++) {
            int i4 = ((iArr[i3 - 3] ^ iArr[i3 - 8]) ^ iArr[i3 - 14]) ^ iArr[i3 - 16];
            iArr[i3] = (i4 << 1) | (i4 >>> 31);
        }
        int i5 = this.d;
        int i6 = this.e;
        int i7 = this.f;
        int i8 = this.g;
        int i9 = this.h;
        int i10 = 0;
        while (i10 < 20) {
            int i11 = ((i5 << 5) | (i5 >>> 27)) + ((i6 & i7) | ((i6 ^ (-1)) & i8)) + i9 + iArr[i10] + 1518500249;
            i9 = i8;
            i8 = i7;
            i7 = (i6 << 30) | (i6 >>> 2);
            i6 = i5;
            i5 = i11;
            i10++;
        }
        while (i10 < 40) {
            int i12 = ((i5 << 5) | (i5 >>> 27)) + ((i6 ^ i7) ^ i8) + i9 + iArr[i10] + 1859775393;
            i9 = i8;
            i8 = i7;
            i7 = (i6 << 30) | (i6 >>> 2);
            i6 = i5;
            i5 = i12;
            i10++;
        }
        while (i10 < 60) {
            int i13 = (((((i5 << 5) | (i5 >>> 27)) + (((i6 & i7) | (i6 & i8)) | (i7 & i8))) + i9) + iArr[i10]) - 1894007588;
            i9 = i8;
            i8 = i7;
            i7 = (i6 << 30) | (i6 >>> 2);
            i6 = i5;
            i5 = i13;
            i10++;
        }
        while (i10 < 80) {
            int i14 = (((((i5 << 5) | (i5 >>> 27)) + ((i6 ^ i7) ^ i8)) + i9) + iArr[i10]) - 899497514;
            i9 = i8;
            i8 = i7;
            i7 = (i6 << 30) | (i6 >>> 2);
            i6 = i5;
            i5 = i14;
            i10++;
        }
        this.d += i5;
        this.e += i6;
        this.f += i7;
        this.g += i8;
        this.h += i9;
        this.f5c += 512;
    }

    private static void c(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >>> 24);
        bArr[i + 1] = (byte) (i2 >>> 16);
        bArr[i + 2] = (byte) (i2 >>> 8);
        bArr[i + 3] = (byte) i2;
    }

    public _B_B() {
        this._CC = 0;
        this.f2a = (byte) 2;
        this.f3a = new byte[256];
    }

    public _B_B(MIDlet mIDlet) {
        _BB[0] = 1560558374;
        _BB[1] = -220668932;
        _BB[2] = -700635326;
        _BB[3] = -891009371;
        _BB[4] = 1774759117;
        _BB[5] = -1130459190;
        _BB[6] = 1076924777;
        _BB[7] = -62631775;
        _BB[8] = -1634743580;
        _BB[9] = 2043124061;
        _BB[10] = 92744046;
        _BB[11] = 1765352294;
        _BB[12] = 1560558374;
        _BB[13] = 92744046;
        _BB[14] = 2066612387;
        _BB[15] = 2016300847;
        _BB[16] = 1836592719;
        _BB[17] = 1723637391;
        _BB[18] = -1634743580;
        _BB[19] = -167745080;
        this._CC = 0;
        this.f2a = (byte) 2;
        this.f3a = new byte[256];
        this.a = 50;
        this.f0a = 2000L;
        this.b = 0L;
        _d = mIDlet;
        c();
        setFullScreenMode(true);
        Display.getDisplay(_d).setCurrent(this);
    }

    public static Displayable _b() {
        Displayable current = (Display.getDisplay(_d).getCurrent() == null || (Display.getDisplay(_d).getCurrent() instanceof _B_B)) ? null : Display.getDisplay(_d).getCurrent();
        return current;
    }

    public final void _e() {
        new Thread(this).start();
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (this.b <= this.f0a) {
            if (isShown()) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                d();
                repaint();
                serviceRepaints();
                int iCurrentTimeMillis = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                if (iCurrentTimeMillis < this.a) {
                    try {
                        Thread.sleep(this.a - iCurrentTimeMillis);
                    } catch (Exception e) {
                    }
                    this.b += (long) (this.a - iCurrentTimeMillis);
                } else {
                    this.b += (long) this.a;
                }
            }
        }
        b();
        try {
            _i();
        } catch (Throwable e2) {
            System.err.println("_B_B: error in _i():");
            e2.printStackTrace();
        }
    }

    private final void b() {
    }

    private final void c() {
        try {
            String resPath = A_B_C_D("0mlnnwj\u007fh({kh");
            InputStream is = getClass().getResourceAsStream(resPath);
            if (is == null) {
                is = _B_B.class.getResourceAsStream(resPath);
            }
            if (is == null) {
                String alt = resPath.startsWith("/") ? resPath.substring(1) : "/" + resPath;
                is = _B_B.class.getResourceAsStream(alt);
            }
            if (is != null) {
                byte[] bArr_w = _w(is);
                if (bArr_w != null) {
                    this.f1a = Image.createImage(bArr_w, 0, bArr_w.length);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final byte[] _w(InputStream inputStream) {
        byte[] bArr = new byte[1024];
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    byte[] bArr2 = new byte[16];
                    System.arraycopy(A_B_C_D("nimkxk\u0080i").getBytes(), 0, bArr2, 0, 8);
                    System.arraycopy(A_B_C_D("iaqh7bpc").getBytes(), 0, bArr2, 8, 8);
                    return a(bArr2, byteArrayOutputStream.toByteArray());
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Exception e) {
            return null;
        }
    }

    private final void d() {
        if (this._CC < 20) {
            _BB[this._CC] = A_B_C_D(new StringBuffer().append("").append((char) _BB[this._CC]).toString()).charAt(0);
        }
        this._CC++;
    }

    public final void _i() throws Exception {
        ((GOWMIDlet) _d)._a_a();
        ((GOWMIDlet) _d)._b_b();
    }

    protected final void paint(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(16777215);
        if (this.f1a != null) {
            graphics.drawImage(this.f1a, getWidth() / 2, getHeight() / 2, 3);
        }
    }

    private final byte[] a(byte[] bArr, byte[] bArr2) {
        int length = bArr2.length;
        if (length < 36 || bArr2[0] != this.f2a) {
            throw new IllegalArgumentException();
        }
        a();
        b(bArr2, 1, 7);
        b(bArr, 0, bArr.length);
        a(m2a());
        byte[] bArr3 = new byte[length - 8];
        a(bArr2, 8, bArr3, 0, length - 8);
        a();
        b(bArr2, 0, 8);
        b(bArr3, 0, length - 28);
        byte[] bArrM2a = m2a();
        for (int i = 0; i < 20; i++) {
            if (bArrM2a[i] != bArr3[(length - 28) + i]) {
                throw new IllegalArgumentException();
            }
        }
        int iM3a = m3a(bArr3, 0);
        int iM3a2 = m3a(bArr3, 4);
        byte[] bArr4 = new byte[iM3a];
        if (iM3a2 != -1) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(bArr3, 8, bArr4, 0, length - 36);
        return bArr4;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    private static int m3a(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
    }

    public static String A_B_C_D(String str) {
        char[] cArr = new char[str.length()];
        str.getChars(0, str.length(), cArr, 0);
        char c = 0;
        for (int i = 0; i < cArr.length; i++) {
            char c2 = c;
            c = (char) (c + 1);
            cArr[i] = (char) ((cArr[i] - 1) ^ c2);
        }
        return new String(cArr);
    }
}
