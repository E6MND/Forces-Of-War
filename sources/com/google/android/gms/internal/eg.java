package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

public final class eg {
    public final int errorCode;
    public final bm nK;
    public final bv nL;
    public final String nM;
    public final bp nN;
    public final List<String> nr;
    public final List<String> ns;
    public final long nv;
    public final int orientation;
    public final ey ow;
    public final aj pV;
    public final String pY;
    public final long qc;
    public final boolean qd;
    public final long qe;
    public final List<String> qf;
    public final String qi;
    public final JSONObject rv;
    public final bn rw;
    public final am rx;
    public final long ry;
    public final long rz;

    public eg(aj ajVar, ey eyVar, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, bm bmVar, bv bvVar, String str2, bn bnVar, bp bpVar, long j2, am amVar, long j3, long j4, long j5, String str3, JSONObject jSONObject) {
        this.pV = ajVar;
        this.ow = eyVar;
        this.nr = list != null ? Collections.unmodifiableList(list) : null;
        this.errorCode = i;
        this.ns = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.qf = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.orientation = i2;
        this.nv = j;
        this.pY = str;
        this.qd = z;
        this.nK = bmVar;
        this.nL = bvVar;
        this.nM = str2;
        this.rw = bnVar;
        this.nN = bpVar;
        this.qe = j2;
        this.rx = amVar;
        this.qc = j3;
        this.ry = j4;
        this.rz = j5;
        this.qi = str3;
        this.rv = jSONObject;
    }
}
