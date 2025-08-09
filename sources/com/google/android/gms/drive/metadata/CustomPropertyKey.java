package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hn;

public class CustomPropertyKey implements SafeParcelable {
    public static final Parcelable.Creator<CustomPropertyKey> CREATOR = new c();
    final String JI;
    final int JJ;
    final int xJ;

    CustomPropertyKey(int versionCode, String key, int visibility) {
        boolean z = true;
        this.xJ = versionCode;
        hn.b(key, (Object) "key");
        if (!(visibility == 0 || visibility == 1)) {
            z = false;
        }
        hn.a(z, "visibility must be either PUBLIC or PRIVATE");
        this.JI = key;
        this.JJ = visibility;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomPropertyKey)) {
            return false;
        }
        CustomPropertyKey customPropertyKey = (CustomPropertyKey) obj;
        if (!customPropertyKey.getKey().equals(this.JI) || customPropertyKey.getVisibility() != this.JJ) {
            z = false;
        }
        return z;
    }

    public String getKey() {
        return this.JI;
    }

    public int getVisibility() {
        return this.JJ;
    }

    public int hashCode() {
        return (this.JI + this.JJ).hashCode();
    }

    public String toString() {
        return "CustomPropertyKey(" + this.JI + "," + this.JJ + ")";
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
