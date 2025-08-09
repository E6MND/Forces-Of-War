package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class jn implements SafeParcelable {
    public static final jo CREATOR = new jo();
    final List<jt> VZ;
    private final String Wa;
    private final boolean Wb;
    final List<jx> Wc;
    private final String Wd;
    final List<String> We;
    private final Set<jt> Wf;
    private final Set<jx> Wg;
    private final Set<String> Wh;
    final int xJ;

    jn(int i, List<jt> list, String str, boolean z, List<jx> list2, String str2, List<String> list3) {
        this.xJ = i;
        this.VZ = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.Wa = str == null ? "" : str;
        this.Wb = z;
        this.Wc = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.Wd = str2 == null ? "" : str2;
        this.We = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.Wf = c(this.VZ);
        this.Wg = c(this.Wc);
        this.Wh = c(this.We);
    }

    private static <E> Set<E> c(List<E> list) {
        return list.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(list));
    }

    public int describeContents() {
        jo joVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof jn)) {
            return false;
        }
        jn jnVar = (jn) object;
        return this.Wf.equals(jnVar.Wf) && this.Wb == jnVar.Wb && this.Wd.equals(jnVar.Wd) && this.Wg.equals(jnVar.Wg) && this.Wh.equals(jnVar.Wh);
    }

    public int hashCode() {
        return hl.hashCode(this.Wf, Boolean.valueOf(this.Wb), this.Wg, this.Wd, this.Wh);
    }

    @Deprecated
    public String jb() {
        return this.Wa;
    }

    public boolean jc() {
        return this.Wb;
    }

    public String jd() {
        return this.Wd;
    }

    public String toString() {
        return hl.e(this).a("types", this.Wf).a("placeIds", this.Wh).a("requireOpenNow", Boolean.valueOf(this.Wb)).a("userAccountName", this.Wd).a("requestedUserDataTypes", this.Wg).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        jo joVar = CREATOR;
        jo.a(this, parcel, flags);
    }
}
