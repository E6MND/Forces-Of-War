package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class hw implements SafeParcelable, hz.b<String, Integer> {
    public static final hx CREATOR = new hx();
    private final HashMap<String, Integer> GT;
    private final HashMap<Integer, String> GU;
    private final ArrayList<a> GV;
    private final int xJ;

    public static final class a implements SafeParcelable {
        public static final hy CREATOR = new hy();
        final String GW;
        final int GX;
        final int versionCode;

        a(int i, String str, int i2) {
            this.versionCode = i;
            this.GW = str;
            this.GX = i2;
        }

        a(String str, int i) {
            this.versionCode = 1;
            this.GW = str;
            this.GX = i;
        }

        public int describeContents() {
            hy hyVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            hy hyVar = CREATOR;
            hy.a(this, out, flags);
        }
    }

    public hw() {
        this.xJ = 1;
        this.GT = new HashMap<>();
        this.GU = new HashMap<>();
        this.GV = null;
    }

    hw(int i, ArrayList<a> arrayList) {
        this.xJ = i;
        this.GT = new HashMap<>();
        this.GU = new HashMap<>();
        this.GV = null;
        a(arrayList);
    }

    private void a(ArrayList<a> arrayList) {
        Iterator<a> it = arrayList.iterator();
        while (it.hasNext()) {
            a next = it.next();
            f(next.GW, next.GX);
        }
    }

    /* renamed from: a */
    public String g(Integer num) {
        String str = this.GU.get(num);
        return (str != null || !this.GT.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public int describeContents() {
        hx hxVar = CREATOR;
        return 0;
    }

    public hw f(String str, int i) {
        this.GT.put(str, Integer.valueOf(i));
        this.GU.put(Integer.valueOf(i), str);
        return this;
    }

    public int fA() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<a> fy() {
        ArrayList<a> arrayList = new ArrayList<>();
        for (String next : this.GT.keySet()) {
            arrayList.add(new a(next, this.GT.get(next).intValue()));
        }
        return arrayList;
    }

    public int fz() {
        return 7;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel out, int flags) {
        hx hxVar = CREATOR;
        hx.a(this, out, flags);
    }
}
