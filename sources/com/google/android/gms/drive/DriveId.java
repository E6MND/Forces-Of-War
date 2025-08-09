package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.af;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.mf;

public class DriveId implements SafeParcelable {
    public static final Parcelable.Creator<DriveId> CREATOR = new c();
    final String HK;
    final long HL;
    final long HM;
    private volatile String HN;
    final int xJ;

    DriveId(int versionCode, String resourceId, long sqlId, long databaseInstanceId) {
        boolean z = false;
        this.HN = null;
        this.xJ = versionCode;
        this.HK = resourceId;
        hn.C(!"".equals(resourceId));
        hn.C((resourceId == null && sqlId == -1) ? z : true);
        this.HL = sqlId;
        this.HM = databaseInstanceId;
    }

    public DriveId(String resourceId, long sqlId, long databaseInstanceId) {
        this(1, resourceId, sqlId, databaseInstanceId);
    }

    public static DriveId aL(String str) {
        hn.f(str);
        return new DriveId(str, -1, -1);
    }

    public static DriveId decodeFromString(String s) {
        hn.b(s.startsWith("DriveId:"), (Object) "Invalid DriveId: " + s);
        return f(Base64.decode(s.substring("DriveId:".length()), 10));
    }

    static DriveId f(byte[] bArr) {
        try {
            af g = af.g(bArr);
            return new DriveId(g.versionCode, "".equals(g.Jq) ? null : g.Jq, g.Jr, g.Js);
        } catch (me e) {
            throw new IllegalArgumentException();
        }
    }

    public int describeContents() {
        return 0;
    }

    public final String encodeToString() {
        if (this.HN == null) {
            this.HN = "DriveId:" + Base64.encodeToString(gf(), 10);
        }
        return this.HN;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DriveId)) {
            return false;
        }
        DriveId driveId = (DriveId) obj;
        if (driveId.HM != this.HM) {
            Log.w("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
            return false;
        } else if (driveId.HL == -1 && this.HL == -1) {
            return driveId.HK.equals(this.HK);
        } else {
            return driveId.HL == this.HL;
        }
    }

    public String getResourceId() {
        return this.HK;
    }

    /* access modifiers changed from: package-private */
    public final byte[] gf() {
        af afVar = new af();
        afVar.versionCode = this.xJ;
        afVar.Jq = this.HK == null ? "" : this.HK;
        afVar.Jr = this.HL;
        afVar.Js = this.HM;
        return mf.d(afVar);
    }

    public int hashCode() {
        return this.HL == -1 ? this.HK.hashCode() : (String.valueOf(this.HM) + String.valueOf(this.HL)).hashCode();
    }

    public String toString() {
        return encodeToString();
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
