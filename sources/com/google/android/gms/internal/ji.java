package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class ji implements SafeParcelable, Geofence {
    public static final jj CREATOR = new jj();
    private final String Oy;
    private final int UX;
    private final short UZ;
    private final long VW;
    private final double Va;
    private final double Vb;
    private final float Vc;
    private final int Vd;
    private final int Ve;
    private final int xJ;

    public ji(int i, String str, int i2, short s, double d, double d2, float f, long j, int i3, int i4) {
        bq(str);
        b(f);
        a(d, d2);
        int cM = cM(i2);
        this.xJ = i;
        this.UZ = s;
        this.Oy = str;
        this.Va = d;
        this.Vb = d2;
        this.Vc = f;
        this.VW = j;
        this.UX = cM;
        this.Vd = i3;
        this.Ve = i4;
    }

    public ji(String str, int i, short s, double d, double d2, float f, long j, int i2, int i3) {
        this(1, str, i, s, d, d2, f, j, i2, i3);
    }

    private static void a(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    private static void b(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static void bq(String str) {
        if (str == null || str.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + str);
        }
    }

    private static int cM(int i) {
        int i2 = i & 7;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    private static String cN(int i) {
        switch (i) {
            case 1:
                return "CIRCLE";
            default:
                return null;
        }
    }

    public static ji h(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        ji bt = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return bt;
    }

    public int describeContents() {
        jj jjVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ji)) {
            return false;
        }
        ji jiVar = (ji) obj;
        if (this.Vc != jiVar.Vc) {
            return false;
        }
        if (this.Va != jiVar.Va) {
            return false;
        }
        if (this.Vb != jiVar.Vb) {
            return false;
        }
        return this.UZ == jiVar.UZ;
    }

    public long getExpirationTime() {
        return this.VW;
    }

    public double getLatitude() {
        return this.Va;
    }

    public double getLongitude() {
        return this.Vb;
    }

    public int getNotificationResponsiveness() {
        return this.Vd;
    }

    public String getRequestId() {
        return this.Oy;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.Va);
        long doubleToLongBits2 = Double.doubleToLongBits(this.Vb);
        return ((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.Vc)) * 31) + this.UZ) * 31) + this.UX;
    }

    public short iV() {
        return this.UZ;
    }

    public float iW() {
        return this.Vc;
    }

    public int iX() {
        return this.UX;
    }

    public int iY() {
        return this.Ve;
    }

    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{cN(this.UZ), this.Oy, Integer.valueOf(this.UX), Double.valueOf(this.Va), Double.valueOf(this.Vb), Float.valueOf(this.Vc), Integer.valueOf(this.Vd / 1000), Integer.valueOf(this.Ve), Long.valueOf(this.VW)});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        jj jjVar = CREATOR;
        jj.a(this, parcel, flags);
    }
}
