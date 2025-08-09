package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;

public final class cr implements SafeParcelable {
    public static final cq CREATOR = new cq();
    public final dh kV;
    public final da kX;
    public final dc oR;
    public final Context oS;
    public final int versionCode;

    cr(int i, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4) {
        this.versionCode = i;
        this.kV = (dh) e.e(d.a.ag(iBinder));
        this.kX = (da) e.e(d.a.ag(iBinder2));
        this.oR = (dc) e.e(d.a.ag(iBinder3));
        this.oS = (Context) e.e(d.a.ag(iBinder4));
    }

    public cr(dc dcVar, dh dhVar, da daVar, Context context) {
        this.versionCode = 1;
        this.oR = dcVar;
        this.kV = dhVar;
        this.kX = daVar;
        this.oS = context;
    }

    public static void a(Intent intent, cr crVar) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", crVar);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", bundle);
    }

    public static cr b(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(cr.class.getClassLoader());
            return (cr) bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        } catch (Exception e) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public IBinder aY() {
        return e.h(this.kV).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder aZ() {
        return e.h(this.kX).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder ba() {
        return e.h(this.oR).asBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder bb() {
        return e.h(this.oS).asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        cq.a(this, out, flags);
    }
}
