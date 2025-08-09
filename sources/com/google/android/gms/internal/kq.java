package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hz;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class kq extends hz implements SafeParcelable, Moment {
    public static final kr CREATOR = new kr();
    private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
    private final Set<Integer> acp;
    private String adc;
    private ko adk;
    private ko adl;
    private String qU;
    private String xD;
    private final int xJ;

    static {
        aco.put("id", hz.a.j("id", 2));
        aco.put("result", hz.a.a("result", 4, ko.class));
        aco.put("startDate", hz.a.j("startDate", 5));
        aco.put("target", hz.a.a("target", 6, ko.class));
        aco.put(MessagingSmsConsts.TYPE, hz.a.j(MessagingSmsConsts.TYPE, 7));
    }

    public kq() {
        this.xJ = 1;
        this.acp = new HashSet();
    }

    kq(Set<Integer> set, int i, String str, ko koVar, String str2, ko koVar2, String str3) {
        this.acp = set;
        this.xJ = i;
        this.xD = str;
        this.adk = koVar;
        this.adc = str2;
        this.adl = koVar2;
        this.qU = str3;
    }

    public kq(Set<Integer> set, String str, ko koVar, String str2, ko koVar2, String str3) {
        this.acp = set;
        this.xJ = 1;
        this.xD = str;
        this.adk = koVar;
        this.adc = str2;
        this.adl = koVar2;
        this.qU = str3;
    }

    /* access modifiers changed from: protected */
    public boolean a(hz.a aVar) {
        return this.acp.contains(Integer.valueOf(aVar.fI()));
    }

    /* access modifiers changed from: protected */
    public Object aF(String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean aG(String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public Object b(hz.a aVar) {
        switch (aVar.fI()) {
            case 2:
                return this.xD;
            case 4:
                return this.adk;
            case 5:
                return this.adc;
            case 6:
                return this.adl;
            case 7:
                return this.qU;
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
        }
    }

    public int describeContents() {
        kr krVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof kq)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        kq kqVar = (kq) obj;
        for (hz.a next : aco.values()) {
            if (a(next)) {
                if (!kqVar.a(next)) {
                    return false;
                }
                if (!b(next).equals(kqVar.b(next))) {
                    return false;
                }
            } else if (kqVar.a(next)) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, hz.a<?, ?>> fB() {
        return aco;
    }

    public String getId() {
        return this.xD;
    }

    public ItemScope getResult() {
        return this.adk;
    }

    public String getStartDate() {
        return this.adc;
    }

    public ItemScope getTarget() {
        return this.adl;
    }

    public String getType() {
        return this.qU;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public boolean hasId() {
        return this.acp.contains(2);
    }

    public boolean hasResult() {
        return this.acp.contains(4);
    }

    public boolean hasStartDate() {
        return this.acp.contains(5);
    }

    public boolean hasTarget() {
        return this.acp.contains(6);
    }

    public boolean hasType() {
        return this.acp.contains(7);
    }

    public int hashCode() {
        int i = 0;
        Iterator<hz.a<?, ?>> it = aco.values().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            hz.a next = it.next();
            if (a(next)) {
                i = b(next).hashCode() + i2 + next.fI();
            } else {
                i = i2;
            }
        }
    }

    public boolean isDataValid() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public Set<Integer> kf() {
        return this.acp;
    }

    /* access modifiers changed from: package-private */
    public ko kw() {
        return this.adk;
    }

    /* access modifiers changed from: package-private */
    public ko kx() {
        return this.adl;
    }

    /* renamed from: ky */
    public kq freeze() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        kr krVar = CREATOR;
        kr.a(this, out, flags);
    }
}
