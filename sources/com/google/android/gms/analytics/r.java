package com.google.android.gms.analytics;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.analytics.u;

class r extends af {
    /* access modifiers changed from: private */
    public static final Object tQ = new Object();
    private static r uc;
    private Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private d tR;
    private volatile f tS;
    /* access modifiers changed from: private */
    public int tT = 1800;
    private boolean tU = true;
    private boolean tV;
    private String tW;
    /* access modifiers changed from: private */
    public boolean tX = true;
    private boolean tY = true;
    private e tZ = new e() {
        public void s(boolean z) {
            r.this.a(z, r.this.tX);
        }
    };
    private q ua;
    /* access modifiers changed from: private */
    public boolean ub = false;

    private r() {
    }

    private void cA() {
        this.ua = new q(this);
        this.ua.s(this.mContext);
    }

    private void cB() {
        this.mHandler = new Handler(this.mContext.getMainLooper(), new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                if (1 == msg.what && r.tQ.equals(msg.obj)) {
                    u.cP().u(true);
                    r.this.dispatchLocalHits();
                    u.cP().u(false);
                    if (r.this.tT > 0 && !r.this.ub) {
                        r.this.mHandler.sendMessageDelayed(r.this.mHandler.obtainMessage(1, r.tQ), (long) (r.this.tT * 1000));
                    }
                }
                return true;
            }
        });
        if (this.tT > 0) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, tQ), (long) (this.tT * 1000));
        }
    }

    public static r cz() {
        if (uc == null) {
            uc = new r();
        }
        return uc;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Context context, f fVar) {
        if (this.mContext == null) {
            this.mContext = context.getApplicationContext();
            if (this.tS == null) {
                this.tS = fVar;
                if (this.tU) {
                    dispatchLocalHits();
                    this.tU = false;
                }
                if (this.tV) {
                    cn();
                    this.tV = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(boolean z, boolean z2) {
        if (!(this.ub == z && this.tX == z2)) {
            if (z || !z2) {
                if (this.tT > 0) {
                    this.mHandler.removeMessages(1, tQ);
                }
            }
            if (!z && z2 && this.tT > 0) {
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, tQ), (long) (this.tT * 1000));
            }
            aa.C("PowerSaveMode " + ((z || !z2) ? "initiated." : "terminated."));
            this.ub = z;
            this.tX = z2;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized d cC() {
        if (this.tR == null) {
            if (this.mContext == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.tR = new ac(this.tZ, this.mContext);
            if (this.tW != null) {
                this.tR.cm().M(this.tW);
                this.tW = null;
            }
        }
        if (this.mHandler == null) {
            cB();
        }
        if (this.ua == null && this.tY) {
            cA();
        }
        return this.tR;
    }

    /* access modifiers changed from: package-private */
    public synchronized void cD() {
        if (!this.ub && this.tX && this.tT > 0) {
            this.mHandler.removeMessages(1, tQ);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, tQ));
        }
    }

    /* access modifiers changed from: package-private */
    public void cn() {
        if (this.tS == null) {
            aa.C("setForceLocalDispatch() queued. It will be called once initialization is complete.");
            this.tV = true;
            return;
        }
        u.cP().a(u.a.SET_FORCE_LOCAL_DISPATCH);
        this.tS.cn();
    }

    /* access modifiers changed from: package-private */
    public synchronized void dispatchLocalHits() {
        if (this.tS == null) {
            aa.C("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.tU = true;
        } else {
            u.cP().a(u.a.DISPATCH);
            this.tS.cl();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void setLocalDispatchPeriod(int dispatchPeriodInSeconds) {
        if (this.mHandler == null) {
            aa.C("Dispatch period set with null handler. Dispatch will run once initialization is complete.");
            this.tT = dispatchPeriodInSeconds;
        } else {
            u.cP().a(u.a.SET_DISPATCH_PERIOD);
            if (!this.ub && this.tX && this.tT > 0) {
                this.mHandler.removeMessages(1, tQ);
            }
            this.tT = dispatchPeriodInSeconds;
            if (dispatchPeriodInSeconds > 0 && !this.ub && this.tX) {
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, tQ), (long) (dispatchPeriodInSeconds * 1000));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void t(boolean z) {
        a(this.ub, z);
    }
}
