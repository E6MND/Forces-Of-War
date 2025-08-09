package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class MetadataBundle implements SafeParcelable {
    public static final Parcelable.Creator<MetadataBundle> CREATOR = new h();
    final Bundle JP;
    final int xJ;

    MetadataBundle(int versionCode, Bundle valueBundle) {
        this.xJ = versionCode;
        this.JP = (Bundle) hn.f(valueBundle);
        this.JP.setClassLoader(getClass().getClassLoader());
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : this.JP.keySet()) {
            if (e.aN(str) == null) {
                arrayList.add(str);
                Log.w("MetadataBundle", "Ignored unknown metadata field in bundle: " + str);
            }
        }
        for (String remove : arrayList) {
            this.JP.remove(remove);
        }
    }

    private MetadataBundle(Bundle valueBundle) {
        this(1, valueBundle);
    }

    public static <T> MetadataBundle a(MetadataField<T> metadataField, T t) {
        MetadataBundle gA = gA();
        gA.b(metadataField, t);
        return gA;
    }

    public static MetadataBundle a(MetadataBundle metadataBundle) {
        return new MetadataBundle(new Bundle(metadataBundle.JP));
    }

    public static MetadataBundle gA() {
        return new MetadataBundle(new Bundle());
    }

    public <T> T a(MetadataField<T> metadataField) {
        return metadataField.e(this.JP);
    }

    public <T> void b(MetadataField<T> metadataField, T t) {
        if (e.aN(metadataField.getName()) == null) {
            throw new IllegalArgumentException("Unregistered field: " + metadataField.getName());
        }
        metadataField.a(t, this.JP);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetadataBundle)) {
            return false;
        }
        MetadataBundle metadataBundle = (MetadataBundle) obj;
        Set<String> keySet = this.JP.keySet();
        if (!keySet.equals(metadataBundle.JP.keySet())) {
            return false;
        }
        for (String str : keySet) {
            if (!hl.equal(this.JP.get(str), metadataBundle.JP.get(str))) {
                return false;
            }
        }
        return true;
    }

    public Set<MetadataField<?>> gB() {
        HashSet hashSet = new HashSet();
        for (String aN : this.JP.keySet()) {
            hashSet.add(e.aN(aN));
        }
        return hashSet;
    }

    public int hashCode() {
        int i = 1;
        Iterator it = this.JP.keySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = this.JP.get((String) it.next()).hashCode() + (i2 * 31);
        }
    }

    public String toString() {
        return "MetadataBundle [values=" + this.JP + "]";
    }

    public void writeToParcel(Parcel dest, int flags) {
        h.a(this, dest, flags);
    }
}
