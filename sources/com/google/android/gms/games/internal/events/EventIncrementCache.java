package com.google.android.gms.games.internal.events;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EventIncrementCache {
    final Object Rj = new Object();
    private Handler Rk;
    private boolean Rl;
    private HashMap<String, AtomicInteger> Rm;
    private int Rn;

    public EventIncrementCache(Looper looper, int flushIntervalMillis) {
        this.Rk = new Handler(looper);
        this.Rm = new HashMap<>();
        this.Rn = flushIntervalMillis;
    }

    /* access modifiers changed from: private */
    public void hK() {
        synchronized (this.Rj) {
            this.Rl = false;
            flush();
        }
    }

    public void flush() {
        synchronized (this.Rj) {
            for (Map.Entry next : this.Rm.entrySet()) {
                o((String) next.getKey(), ((AtomicInteger) next.getValue()).get());
            }
            this.Rm.clear();
        }
    }

    /* access modifiers changed from: protected */
    public abstract void o(String str, int i);

    public void u(String str, int i) {
        synchronized (this.Rj) {
            if (!this.Rl) {
                this.Rl = true;
                this.Rk.postDelayed(new Runnable() {
                    public void run() {
                        EventIncrementCache.this.hK();
                    }
                }, (long) this.Rn);
            }
            AtomicInteger atomicInteger = this.Rm.get(str);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                this.Rm.put(str, atomicInteger);
            }
            atomicInteger.addAndGet(i);
        }
    }
}
