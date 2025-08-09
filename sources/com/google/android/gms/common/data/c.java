package com.google.android.gms.common.data;

import com.google.android.gms.internal.hn;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class c<T> implements Iterator<T> {
    protected final DataBuffer<T> Ex;
    protected int Ey = -1;

    public c(DataBuffer<T> dataBuffer) {
        this.Ex = (DataBuffer) hn.f(dataBuffer);
    }

    public boolean hasNext() {
        return this.Ey < this.Ex.getCount() + -1;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.Ey);
        }
        DataBuffer<T> dataBuffer = this.Ex;
        int i = this.Ey + 1;
        this.Ey = i;
        return dataBuffer.get(i);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
