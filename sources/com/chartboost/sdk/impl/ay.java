package com.chartboost.sdk.impl;

import com.facebook.AppEventsConstants;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ay implements Serializable, Comparable<ay> {
    static final Logger a = Logger.getLogger("org.bson.ObjectId");
    private static AtomicInteger f = new AtomicInteger(new Random().nextInt());
    private static final int g;
    final int b;
    final int c;
    final int d;
    boolean e;

    static {
        int i;
        int i2;
        int i3;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                sb.append(networkInterfaces.nextElement().toString());
            }
            i = sb.toString().hashCode() << 16;
        } catch (Throwable th) {
            try {
                a.log(Level.WARNING, th.getMessage(), th);
                i = new Random().nextInt() << 16;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
        a.fine("machine piece post: " + Integer.toHexString(i));
        int nextInt = new Random().nextInt();
        try {
            i3 = ManagementFactory.getRuntimeMXBean().getName().hashCode();
        } catch (Throwable th2) {
            i3 = i2;
        }
        ClassLoader classLoader = ay.class.getClassLoader();
        int identityHashCode = classLoader != null ? System.identityHashCode(classLoader) : 0;
        int hashCode = (Integer.toHexString(i3) + Integer.toHexString(identityHashCode)).hashCode() & 65535;
        a.fine(new StringBuilder().append("process piece: ").append(Integer.toHexString(hashCode)).toString());
        g = hashCode | i;
        a.fine(new StringBuilder().append("machine : ").append(Integer.toHexString(g)).toString());
    }

    public static boolean a(String str) {
        int length;
        if (str == null || (length = str.length()) != 24) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'f') && (charAt < 'A' || charAt > 'F'))) {
                return false;
            }
        }
        return true;
    }

    public static ay a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ay) {
            return (ay) obj;
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (a(obj2)) {
                return new ay(obj2);
            }
        }
        return null;
    }

    public ay(String str) {
        this(str, false);
    }

    public ay(String str, boolean z) {
        if (!a(str)) {
            throw new IllegalArgumentException("invalid ObjectId [" + str + "]");
        }
        str = z ? b(str) : str;
        byte[] bArr = new byte[12];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16);
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.b = wrap.getInt();
        this.c = wrap.getInt();
        this.d = wrap.getInt();
        this.e = false;
    }

    public ay(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = false;
    }

    public ay() {
        this.b = (int) (System.currentTimeMillis() / 1000);
        this.c = g;
        this.d = f.getAndIncrement();
        this.e = true;
    }

    public int hashCode() {
        return this.b + (this.c * 111) + (this.d * 17);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        ay a2 = a(o);
        if (a2 == null) {
            return false;
        }
        if (this.b == a2.b && this.c == a2.c && this.d == a2.d) {
            return true;
        }
        return false;
    }

    public String a() {
        byte[] b2 = b();
        StringBuilder sb = new StringBuilder(24);
        for (byte b3 : b2) {
            String hexString = Integer.toHexString(b3 & 255);
            if (hexString.length() == 1) {
                sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public byte[] b() {
        byte[] bArr = new byte[12];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putInt(this.b);
        wrap.putInt(this.c);
        wrap.putInt(this.d);
        return bArr;
    }

    static String a(String str, int i) {
        return str.substring(i * 2, (i * 2) + 2);
    }

    public static String b(String str) {
        if (!a(str)) {
            throw new IllegalArgumentException("invalid object id: " + str);
        }
        StringBuilder sb = new StringBuilder(24);
        for (int i = 7; i >= 0; i--) {
            sb.append(a(str, i));
        }
        for (int i2 = 11; i2 >= 8; i2--) {
            sb.append(a(str, i2));
        }
        return sb.toString();
    }

    public String toString() {
        return a();
    }

    /* access modifiers changed from: package-private */
    public int a(int i, int i2) {
        long j = (((long) i) & 4294967295L) - (((long) i2) & 4294967295L);
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    /* renamed from: a */
    public int compareTo(ay ayVar) {
        if (ayVar == null) {
            return -1;
        }
        int a2 = a(this.b, ayVar.b);
        if (a2 != 0) {
            return a2;
        }
        int a3 = a(this.c, ayVar.c);
        return a3 == 0 ? a(this.d, ayVar.d) : a3;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }
}
