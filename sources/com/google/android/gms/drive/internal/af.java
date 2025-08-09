package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.lz;
import com.google.android.gms.internal.ma;
import com.google.android.gms.internal.mb;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.mf;
import java.io.IOException;

public final class af extends mb<af> {
    public String Jq;
    public long Jr;
    public long Js;
    public int versionCode;

    public af() {
        gn();
    }

    public static af g(byte[] bArr) throws me {
        return (af) mf.a(new af(), bArr);
    }

    public void a(ma maVar) throws IOException {
        maVar.p(1, this.versionCode);
        maVar.b(2, this.Jq);
        maVar.c(3, this.Jr);
        maVar.c(4, this.Js);
        super.a(maVar);
    }

    /* access modifiers changed from: protected */
    public int c() {
        return super.c() + ma.r(1, this.versionCode) + ma.h(2, this.Jq) + ma.e(3, this.Jr) + ma.e(4, this.Js);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof af)) {
            return false;
        }
        af afVar = (af) o;
        if (this.versionCode != afVar.versionCode) {
            return false;
        }
        if (this.Jq == null) {
            if (afVar.Jq != null) {
                return false;
            }
        } else if (!this.Jq.equals(afVar.Jq)) {
            return false;
        }
        if (this.Jr != afVar.Jr || this.Js != afVar.Js) {
            return false;
        }
        if (this.amU == null || this.amU.isEmpty()) {
            return afVar.amU == null || afVar.amU.isEmpty();
        }
        return this.amU.equals(afVar.amU);
    }

    public af gn() {
        this.versionCode = 1;
        this.Jq = "";
        this.Jr = -1;
        this.Js = -1;
        this.amU = null;
        this.amY = -1;
        return this;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.Jq == null ? 0 : this.Jq.hashCode()) + ((this.versionCode + 527) * 31)) * 31) + ((int) (this.Jr ^ (this.Jr >>> 32)))) * 31) + ((int) (this.Js ^ (this.Js >>> 32)))) * 31;
        if (this.amU != null && !this.amU.isEmpty()) {
            i = this.amU.hashCode();
        }
        return hashCode + i;
    }

    /* renamed from: m */
    public af b(lz lzVar) throws IOException {
        while (true) {
            int nw = lzVar.nw();
            switch (nw) {
                case 0:
                    break;
                case 8:
                    this.versionCode = lzVar.nz();
                    continue;
                case 18:
                    this.Jq = lzVar.readString();
                    continue;
                case 24:
                    this.Jr = lzVar.nC();
                    continue;
                case 32:
                    this.Js = lzVar.nC();
                    continue;
                default:
                    if (!a(lzVar, nw)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }
}
