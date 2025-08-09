package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c;
import com.google.android.gms.tagmanager.o;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class co implements o.e {
    private String aeM;
    /* access modifiers changed from: private */
    public final String aeq;
    private bg<c.j> agK;
    private r agL;
    private final ScheduledExecutorService agN;
    private final a agO;
    private ScheduledFuture<?> agP;
    private boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;

    interface a {
        cn a(r rVar);
    }

    interface b {
        ScheduledExecutorService mf();
    }

    public co(Context context, String str, r rVar) {
        this(context, str, rVar, (b) null, (a) null);
    }

    co(Context context, String str, r rVar, b bVar, a aVar) {
        this.agL = rVar;
        this.mContext = context;
        this.aeq = str;
        this.agN = (bVar == null ? new b() {
            public ScheduledExecutorService mf() {
                return Executors.newSingleThreadScheduledExecutor();
            }
        } : bVar).mf();
        if (aVar == null) {
            this.agO = new a() {
                public cn a(r rVar) {
                    return new cn(co.this.mContext, co.this.aeq, rVar);
                }
            };
        } else {
            this.agO = aVar;
        }
    }

    private cn cc(String str) {
        cn a2 = this.agO.a(this.agL);
        a2.a(this.agK);
        a2.bM(this.aeM);
        a2.cb(str);
        return a2;
    }

    private synchronized void me() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    public synchronized void a(bg<c.j> bgVar) {
        me();
        this.agK = bgVar;
    }

    public synchronized void bM(String str) {
        me();
        this.aeM = str;
    }

    public synchronized void e(long j, String str) {
        bh.C("loadAfterDelay: containerId=" + this.aeq + " delay=" + j);
        me();
        if (this.agK == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.agP != null) {
            this.agP.cancel(false);
        }
        this.agP = this.agN.schedule(cc(str), j, TimeUnit.MILLISECONDS);
    }

    public synchronized void release() {
        me();
        if (this.agP != null) {
            this.agP.cancel(false);
        }
        this.agN.shutdown();
        this.mClosed = true;
    }
}
