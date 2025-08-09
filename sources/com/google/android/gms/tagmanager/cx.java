package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

class cx extends cw {
    private static cx ahR;
    /* access modifiers changed from: private */
    public static final Object tQ = new Object();
    private Context ahH;
    /* access modifiers changed from: private */
    public at ahI;
    private volatile ar ahJ;
    /* access modifiers changed from: private */
    public int ahK = 1800000;
    private boolean ahL = true;
    private boolean ahM = false;
    private boolean ahN = true;
    private au ahO = new au() {
        public void s(boolean z) {
            cx.this.a(z, cx.this.connected);
        }
    };
    private bn ahP;
    /* access modifiers changed from: private */
    public boolean ahQ = false;
    /* access modifiers changed from: private */
    public boolean connected = true;
    /* access modifiers changed from: private */
    public Handler handler;

    private cx() {
    }

    private void cA() {
        this.ahP = new bn(this);
        this.ahP.s(this.ahH);
    }

    private void cB() {
        this.handler = new Handler(this.ahH.getMainLooper(), new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                if (1 == msg.what && cx.tQ.equals(msg.obj)) {
                    cx.this.cl();
                    if (cx.this.ahK > 0 && !cx.this.ahQ) {
                        cx.this.handler.sendMessageDelayed(cx.this.handler.obtainMessage(1, cx.tQ), (long) cx.this.ahK);
                    }
                }
                return true;
            }
        });
        if (this.ahK > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1, tQ), (long) this.ahK);
        }
    }

    public static cx mL() {
        if (ahR == null) {
            ahR = new cx();
        }
        return ahR;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Context context, ar arVar) {
        if (this.ahH == null) {
            this.ahH = context.getApplicationContext();
            if (this.ahJ == null) {
                this.ahJ = arVar;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(boolean z, boolean z2) {
        if (!(this.ahQ == z && this.connected == z2)) {
            if (z || !z2) {
                if (this.ahK > 0) {
                    this.handler.removeMessages(1, tQ);
                }
            }
            if (!z && z2 && this.ahK > 0) {
                this.handler.sendMessageDelayed(this.handler.obtainMessage(1, tQ), (long) this.ahK);
            }
            bh.C("PowerSaveMode " + ((z || !z2) ? "initiated." : "terminated."));
            this.ahQ = z;
            this.connected = z2;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void cD() {
        if (!this.ahQ && this.connected && this.ahK > 0) {
            this.handler.removeMessages(1, tQ);
            this.handler.sendMessage(this.handler.obtainMessage(1, tQ));
        }
    }

    public synchronized void cl() {
        if (!this.ahM) {
            bh.C("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.ahL = true;
        } else {
            this.ahJ.a(new Runnable() {
                public void run() {
                    cx.this.ahI.cl();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized at mM() {
        if (this.ahI == null) {
            if (this.ahH == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.ahI = new ca(this.ahO, this.ahH);
        }
        if (this.handler == null) {
            cB();
        }
        this.ahM = true;
        if (this.ahL) {
            cl();
            this.ahL = false;
        }
        if (this.ahP == null && this.ahN) {
            cA();
        }
        return this.ahI;
    }

    /* access modifiers changed from: package-private */
    public synchronized void t(boolean z) {
        a(this.ahQ, z);
    }
}
