package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class as extends Thread implements ar {
    private static as afF;
    private final LinkedBlockingQueue<Runnable> afE = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public volatile at afG;
    private volatile boolean mClosed = false;
    /* access modifiers changed from: private */
    public final Context mContext;
    private volatile boolean uI = false;

    private as(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    static as M(Context context) {
        if (afF == null) {
            afF = new as(context);
        }
        return afF;
    }

    private String a(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void a(Runnable runnable) {
        this.afE.add(runnable);
    }

    /* access modifiers changed from: package-private */
    public void b(String str, long j) {
        final long j2 = j;
        final String str2 = str;
        a((Runnable) new Runnable() {
            public void run() {
                if (as.this.afG == null) {
                    cx mL = cx.mL();
                    mL.a(as.this.mContext, this);
                    at unused = as.this.afG = mL.mM();
                }
                as.this.afG.f(j2, str2);
            }
        });
    }

    public void bU(String str) {
        b(str, System.currentTimeMillis());
    }

    public void run() {
        while (!this.mClosed) {
            try {
                Runnable take = this.afE.take();
                if (!this.uI) {
                    take.run();
                }
            } catch (InterruptedException e) {
                bh.B(e.toString());
            } catch (Throwable th) {
                bh.A("Error on GAThread: " + a(th));
                bh.A("Google Analytics is shutting down.");
                this.uI = true;
            }
        }
    }
}
