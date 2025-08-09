package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hz;
import java.util.ArrayList;
import java.util.HashMap;

public class ic implements SafeParcelable {
    public static final id CREATOR = new id();
    private final HashMap<String, HashMap<String, hz.a<?, ?>>> Hi;
    private final ArrayList<a> Hj;
    private final String Hk;
    private final int xJ;

    public static class a implements SafeParcelable {
        public static final ie CREATOR = new ie();
        final ArrayList<b> Hl;
        final String className;
        final int versionCode;

        a(int i, String str, ArrayList<b> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.Hl = arrayList;
        }

        a(String str, HashMap<String, hz.a<?, ?>> hashMap) {
            this.versionCode = 1;
            this.className = str;
            this.Hl = a(hashMap);
        }

        private static ArrayList<b> a(HashMap<String, hz.a<?, ?>> hashMap) {
            if (hashMap == null) {
                return null;
            }
            ArrayList<b> arrayList = new ArrayList<>();
            for (String next : hashMap.keySet()) {
                arrayList.add(new b(next, hashMap.get(next)));
            }
            return arrayList;
        }

        public int describeContents() {
            ie ieVar = CREATOR;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public HashMap<String, hz.a<?, ?>> fS() {
            HashMap<String, hz.a<?, ?>> hashMap = new HashMap<>();
            int size = this.Hl.size();
            for (int i = 0; i < size; i++) {
                b bVar = this.Hl.get(i);
                hashMap.put(bVar.eM, bVar.Hm);
            }
            return hashMap;
        }

        public void writeToParcel(Parcel out, int flags) {
            ie ieVar = CREATOR;
            ie.a(this, out, flags);
        }
    }

    public static class b implements SafeParcelable {
        public static final ib CREATOR = new ib();
        final hz.a<?, ?> Hm;
        final String eM;
        final int versionCode;

        b(int i, String str, hz.a<?, ?> aVar) {
            this.versionCode = i;
            this.eM = str;
            this.Hm = aVar;
        }

        b(String str, hz.a<?, ?> aVar) {
            this.versionCode = 1;
            this.eM = str;
            this.Hm = aVar;
        }

        public int describeContents() {
            ib ibVar = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            ib ibVar = CREATOR;
            ib.a(this, out, flags);
        }
    }

    ic(int i, ArrayList<a> arrayList, String str) {
        this.xJ = i;
        this.Hj = null;
        this.Hi = b(arrayList);
        this.Hk = (String) hn.f(str);
        fO();
    }

    public ic(Class<? extends hz> cls) {
        this.xJ = 1;
        this.Hj = null;
        this.Hi = new HashMap<>();
        this.Hk = cls.getCanonicalName();
    }

    private static HashMap<String, HashMap<String, hz.a<?, ?>>> b(ArrayList<a> arrayList) {
        HashMap<String, HashMap<String, hz.a<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            a aVar = arrayList.get(i);
            hashMap.put(aVar.className, aVar.fS());
        }
        return hashMap;
    }

    public void a(Class<? extends hz> cls, HashMap<String, hz.a<?, ?>> hashMap) {
        this.Hi.put(cls.getCanonicalName(), hashMap);
    }

    public HashMap<String, hz.a<?, ?>> aJ(String str) {
        return this.Hi.get(str);
    }

    public boolean b(Class<? extends hz> cls) {
        return this.Hi.containsKey(cls.getCanonicalName());
    }

    public int describeContents() {
        id idVar = CREATOR;
        return 0;
    }

    public void fO() {
        for (String str : this.Hi.keySet()) {
            HashMap hashMap = this.Hi.get(str);
            for (String str2 : hashMap.keySet()) {
                ((hz.a) hashMap.get(str2)).a(this);
            }
        }
    }

    public void fP() {
        for (String next : this.Hi.keySet()) {
            HashMap hashMap = this.Hi.get(next);
            HashMap hashMap2 = new HashMap();
            for (String str : hashMap.keySet()) {
                hashMap2.put(str, ((hz.a) hashMap.get(str)).fE());
            }
            this.Hi.put(next, hashMap2);
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<a> fQ() {
        ArrayList<a> arrayList = new ArrayList<>();
        for (String next : this.Hi.keySet()) {
            arrayList.add(new a(next, this.Hi.get(next)));
        }
        return arrayList;
    }

    public String fR() {
        return this.Hk;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String next : this.Hi.keySet()) {
            sb.append(next).append(":\n");
            HashMap hashMap = this.Hi.get(next);
            for (String str : hashMap.keySet()) {
                sb.append("  ").append(str).append(": ");
                sb.append(hashMap.get(str));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        id idVar = CREATOR;
        id.a(this, out, flags);
    }
}
