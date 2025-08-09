package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class dt implements SafeParcelable {
    public static final du CREATOR = new du();
    public final ApplicationInfo applicationInfo;
    public final String kL;
    public final ew kO;
    public final am kR;
    public final Bundle pU;
    public final aj pV;
    public final PackageInfo pW;
    public final String pX;
    public final String pY;
    public final String pZ;
    public final Bundle qa;
    public final int versionCode;

    public static final class a {
        public final ApplicationInfo applicationInfo;
        public final String kL;
        public final ew kO;
        public final am kR;
        public final Bundle pU;
        public final aj pV;
        public final PackageInfo pW;
        public final String pY;
        public final String pZ;
        public final Bundle qa;

        public a(Bundle bundle, aj ajVar, am amVar, String str, ApplicationInfo applicationInfo2, PackageInfo packageInfo, String str2, String str3, ew ewVar, Bundle bundle2) {
            this.pU = bundle;
            this.pV = ajVar;
            this.kR = amVar;
            this.kL = str;
            this.applicationInfo = applicationInfo2;
            this.pW = packageInfo;
            this.pY = str2;
            this.pZ = str3;
            this.kO = ewVar;
            this.qa = bundle2;
        }
    }

    dt(int i, Bundle bundle, aj ajVar, am amVar, String str, ApplicationInfo applicationInfo2, PackageInfo packageInfo, String str2, String str3, String str4, ew ewVar, Bundle bundle2) {
        this.versionCode = i;
        this.pU = bundle;
        this.pV = ajVar;
        this.kR = amVar;
        this.kL = str;
        this.applicationInfo = applicationInfo2;
        this.pW = packageInfo;
        this.pX = str2;
        this.pY = str3;
        this.pZ = str4;
        this.kO = ewVar;
        this.qa = bundle2;
    }

    public dt(Bundle bundle, aj ajVar, am amVar, String str, ApplicationInfo applicationInfo2, PackageInfo packageInfo, String str2, String str3, String str4, ew ewVar, Bundle bundle2) {
        this(2, bundle, ajVar, amVar, str, applicationInfo2, packageInfo, str2, str3, str4, ewVar, bundle2);
    }

    public dt(a aVar, String str) {
        this(aVar.pU, aVar.pV, aVar.kR, aVar.kL, aVar.applicationInfo, aVar.pW, str, aVar.pY, aVar.pZ, aVar.kO, aVar.qa);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        du.a(this, out, flags);
    }
}
