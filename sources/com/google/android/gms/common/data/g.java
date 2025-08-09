package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class g<T> extends DataBuffer<T> {
    private boolean ER = false;
    private ArrayList<Integer> ES;

    protected g(DataHolder dataHolder) {
        super(dataHolder);
    }

    private void eV() {
        synchronized (this) {
            if (!this.ER) {
                int count = this.DD.getCount();
                this.ES = new ArrayList<>();
                if (count > 0) {
                    this.ES.add(0);
                    String eU = eU();
                    String c = this.DD.c(eU, 0, this.DD.ae(0));
                    int i = 1;
                    while (i < count) {
                        String c2 = this.DD.c(eU, i, this.DD.ae(i));
                        if (!c2.equals(c)) {
                            this.ES.add(Integer.valueOf(i));
                        } else {
                            c2 = c;
                        }
                        i++;
                        c = c2;
                    }
                }
                this.ER = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int ah(int i) {
        if (i >= 0 && i < this.ES.size()) {
            return this.ES.get(i).intValue();
        }
        throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
    }

    /* access modifiers changed from: protected */
    public int ai(int i) {
        if (i < 0 || i == this.ES.size()) {
            return 0;
        }
        int count = i == this.ES.size() + -1 ? this.DD.getCount() - this.ES.get(i).intValue() : this.ES.get(i + 1).intValue() - this.ES.get(i).intValue();
        if (count != 1) {
            return count;
        }
        int ah = ah(i);
        int ae = this.DD.ae(ah);
        String eW = eW();
        if (eW == null || this.DD.c(eW, ah, ae) != null) {
            return count;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract T c(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract String eU();

    /* access modifiers changed from: protected */
    public String eW() {
        return null;
    }

    public final T get(int position) {
        eV();
        return c(ah(position), ai(position));
    }

    public int getCount() {
        eV();
        return this.ES.size();
    }
}
