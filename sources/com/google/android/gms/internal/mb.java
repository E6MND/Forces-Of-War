package com.google.android.gms.internal;

import com.google.android.gms.internal.mb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class mb<M extends mb<M>> extends mf {
    protected List<mh> amU;

    public final <T> T a(mc<M, T> mcVar) {
        return mcVar.i(this.amU);
    }

    public void a(ma maVar) throws IOException {
        int size = this.amU == null ? 0 : this.amU.size();
        for (int i = 0; i < size; i++) {
            mh mhVar = this.amU.get(i);
            maVar.eI(mhVar.tag);
            maVar.t(mhVar.amZ);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(lz lzVar, int i) throws IOException {
        int position = lzVar.getPosition();
        if (!lzVar.ev(i)) {
            return false;
        }
        if (this.amU == null) {
            this.amU = new ArrayList();
        }
        this.amU.add(new mh(i, lzVar.o(position, lzVar.getPosition() - position)));
        return true;
    }

    /* access modifiers changed from: protected */
    public int c() {
        int size = this.amU == null ? 0 : this.amU.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            mh mhVar = this.amU.get(i2);
            i = i + ma.eJ(mhVar.tag) + mhVar.amZ.length;
        }
        return i;
    }
}
