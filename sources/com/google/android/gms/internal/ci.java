package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;

public final class ci implements SafeParcelable {
    public static final ch CREATOR = new ch();
    public final ew kO;
    public final String nZ;
    public final String oA;
    public final cm oB;
    public final int oC;
    public final be oD;
    public final String oE;
    public final w oF;
    public final int orientation;
    public final cf ot;
    public final u ou;
    public final cj ov;
    public final ey ow;
    public final bb ox;
    public final String oy;
    public final boolean oz;
    public final int versionCode;

    ci(int i, cf cfVar, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, ew ewVar, IBinder iBinder6, String str4, w wVar) {
        this.versionCode = i;
        this.ot = cfVar;
        this.ou = (u) e.e(d.a.ag(iBinder));
        this.ov = (cj) e.e(d.a.ag(iBinder2));
        this.ow = (ey) e.e(d.a.ag(iBinder3));
        this.ox = (bb) e.e(d.a.ag(iBinder4));
        this.oy = str;
        this.oz = z;
        this.oA = str2;
        this.oB = (cm) e.e(d.a.ag(iBinder5));
        this.orientation = i2;
        this.oC = i3;
        this.nZ = str3;
        this.kO = ewVar;
        this.oD = (be) e.e(d.a.ag(iBinder6));
        this.oE = str4;
        this.oF = wVar;
    }

    public ci(cf cfVar, u uVar, cj cjVar, cm cmVar, ew ewVar) {
        this.versionCode = 4;
        this.ot = cfVar;
        this.ou = uVar;
        this.ov = cjVar;
        this.ow = null;
        this.ox = null;
        this.oy = null;
        this.oz = false;
        this.oA = null;
        this.oB = cmVar;
        this.orientation = -1;
        this.oC = 4;
        this.nZ = null;
        this.kO = ewVar;
        this.oD = null;
        this.oE = null;
        this.oF = null;
    }

    public ci(u uVar, cj cjVar, bb bbVar, cm cmVar, ey eyVar, boolean z, int i, String str, ew ewVar, be beVar) {
        this.versionCode = 4;
        this.ot = null;
        this.ou = uVar;
        this.ov = cjVar;
        this.ow = eyVar;
        this.ox = bbVar;
        this.oy = null;
        this.oz = z;
        this.oA = null;
        this.oB = cmVar;
        this.orientation = i;
        this.oC = 3;
        this.nZ = str;
        this.kO = ewVar;
        this.oD = beVar;
        this.oE = null;
        this.oF = null;
    }

    public ci(u uVar, cj cjVar, bb bbVar, cm cmVar, ey eyVar, boolean z, int i, String str, String str2, ew ewVar, be beVar) {
        this.versionCode = 4;
        this.ot = null;
        this.ou = uVar;
        this.ov = cjVar;
        this.ow = eyVar;
        this.ox = bbVar;
        this.oy = str2;
        this.oz = z;
        this.oA = str;
        this.oB = cmVar;
        this.orientation = i;
        this.oC = 3;
        this.nZ = null;
        this.kO = ewVar;
        this.oD = beVar;
        this.oE = null;
        this.oF = null;
    }

    public ci(u uVar, cj cjVar, cm cmVar, ey eyVar, int i, ew ewVar, String str, w wVar) {
        this.versionCode = 4;
        this.ot = null;
        this.ou = uVar;
        this.ov = cjVar;
        this.ow = eyVar;
        this.ox = null;
        this.oy = null;
        this.oz = false;
        this.oA = null;
        this.oB = cmVar;
        this.orientation = i;
        this.oC = 1;
        this.nZ = null;
        this.kO = ewVar;
        this.oD = null;
        this.oE = str;
        this.oF = wVar;
    }

    public ci(u uVar, cj cjVar, cm cmVar, ey eyVar, boolean z, int i, ew ewVar) {
        this.versionCode = 4;
        this.ot = null;
        this.ou = uVar;
        this.ov = cjVar;
        this.ow = eyVar;
        this.ox = null;
        this.oy = null;
        this.oz = z;
        this.oA = null;
        this.oB = cmVar;
        this.orientation = i;
        this.oC = 2;
        this.nZ = null;
        this.kO = ewVar;
        this.oD = null;
        this.oE = null;
        this.oF = null;
    }

    public static ci a(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(ci.class.getClassLoader());
            return (ci) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public static void a(Intent intent, ci ciVar) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", ciVar);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    /* access modifiers changed from: package-private */
    public IBinder aP() {
        return e.h(this.ou).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder aQ() {
        return e.h(this.ov).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder aR() {
        return e.h(this.ow).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder aS() {
        return e.h(this.ox).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder aT() {
        return e.h(this.oD).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder aU() {
        return e.h(this.oB).asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ch.a(this, out, flags);
    }
}
