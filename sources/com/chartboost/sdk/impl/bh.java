package com.chartboost.sdk.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class bh<T> {
    final int a;
    private Queue<T> b = new ConcurrentLinkedQueue();

    /* access modifiers changed from: protected */
    public abstract T b();

    public bh(int i) {
        this.a = i;
    }

    /* access modifiers changed from: protected */
    public boolean a(T t) {
        return true;
    }

    public T c() {
        T poll = this.b.poll();
        return poll != null ? poll : b();
    }

    public void b(T t) {
        if (a(t) && this.b.size() <= this.a) {
            this.b.add(t);
        }
    }
}
