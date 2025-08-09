package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class h<T> extends c<T> {
    private T ET;

    public h(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.Ey);
        }
        this.Ey++;
        if (this.Ey == 0) {
            this.ET = this.Ex.get(0);
            if (!(this.ET instanceof d)) {
                throw new IllegalStateException("DataBuffer reference of type " + this.ET.getClass() + " is not movable");
            }
        } else {
            ((d) this.ET).ac(this.Ey);
        }
        return this.ET;
    }
}
