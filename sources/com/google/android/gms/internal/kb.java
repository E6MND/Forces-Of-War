package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class kb implements SafeParcelable {
    public static final kc CREATOR = new kc();
    public final String YS;
    public final String YT;
    public final int versionCode;

    public kb(int i, String str, String str2) {
        this.versionCode = i;
        this.YS = str;
        this.YT = str2;
    }

    public kb(String str, Locale locale) {
        this.versionCode = 0;
        this.YS = str;
        this.YT = locale.toString();
    }

    public int describeContents() {
        kc kcVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof kb)) {
            return false;
        }
        kb kbVar = (kb) object;
        return this.YT.equals(kbVar.YT) && this.YS.equals(kbVar.YS);
    }

    public int hashCode() {
        return hl.hashCode(this.YS, this.YT);
    }

    public String toString() {
        return hl.e(this).a("clientPackageName", this.YS).a("locale", this.YT).toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        kc kcVar = CREATOR;
        kc.a(this, out, flags);
    }
}
