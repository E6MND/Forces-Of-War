package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;

public abstract class d {
    protected final DataHolder DD;
    private int EA;
    protected int Ez;

    public d(DataHolder dataHolder, int i) {
        this.DD = (DataHolder) hn.f(dataHolder);
        ac(i);
    }

    /* access modifiers changed from: protected */
    public void a(String str, CharArrayBuffer charArrayBuffer) {
        this.DD.a(str, this.Ez, this.EA, charArrayBuffer);
    }

    /* access modifiers changed from: protected */
    public void ac(int i) {
        hn.A(i >= 0 && i < this.DD.getCount());
        this.Ez = i;
        this.EA = this.DD.ae(this.Ez);
    }

    public boolean av(String str) {
        return this.DD.av(str);
    }

    /* access modifiers changed from: protected */
    public Uri aw(String str) {
        return this.DD.g(str, this.Ez, this.EA);
    }

    /* access modifiers changed from: protected */
    public boolean ax(String str) {
        return this.DD.h(str, this.Ez, this.EA);
    }

    /* access modifiers changed from: protected */
    public int eQ() {
        return this.Ez;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return hl.equal(Integer.valueOf(dVar.Ez), Integer.valueOf(this.Ez)) && hl.equal(Integer.valueOf(dVar.EA), Integer.valueOf(this.EA)) && dVar.DD == this.DD;
    }

    /* access modifiers changed from: protected */
    public boolean getBoolean(String column) {
        return this.DD.d(column, this.Ez, this.EA);
    }

    /* access modifiers changed from: protected */
    public byte[] getByteArray(String column) {
        return this.DD.f(column, this.Ez, this.EA);
    }

    /* access modifiers changed from: protected */
    public float getFloat(String column) {
        return this.DD.e(column, this.Ez, this.EA);
    }

    /* access modifiers changed from: protected */
    public int getInteger(String column) {
        return this.DD.b(column, this.Ez, this.EA);
    }

    /* access modifiers changed from: protected */
    public long getLong(String column) {
        return this.DD.a(column, this.Ez, this.EA);
    }

    /* access modifiers changed from: protected */
    public String getString(String column) {
        return this.DD.c(column, this.Ez, this.EA);
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.Ez), Integer.valueOf(this.EA), this.DD);
    }

    public boolean isDataValid() {
        return !this.DD.isClosed();
    }
}
