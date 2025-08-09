package com.google.android.gms.games.internal.events;

import java.util.concurrent.atomic.AtomicReference;

public abstract class EventIncrementManager {
    private final AtomicReference<EventIncrementCache> Rp = new AtomicReference<>();

    public void flush() {
        EventIncrementCache eventIncrementCache = this.Rp.get();
        if (eventIncrementCache != null) {
            eventIncrementCache.flush();
        }
    }

    /* access modifiers changed from: protected */
    public abstract EventIncrementCache hs();

    public void l(String str, int i) {
        EventIncrementCache eventIncrementCache = this.Rp.get();
        if (eventIncrementCache == null) {
            eventIncrementCache = hs();
            if (!this.Rp.compareAndSet((Object) null, eventIncrementCache)) {
                eventIncrementCache = this.Rp.get();
            }
        }
        eventIncrementCache.u(str, i);
    }
}
