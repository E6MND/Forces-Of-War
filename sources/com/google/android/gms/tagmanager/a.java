package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.im;
import java.io.IOException;

class a {
    private static a aee;
    private static Object tn = new Object();
    private volatile long adZ;
    private volatile long aea;
    private volatile long aeb;
    private final ik aec;
    private C0128a aed;
    private volatile boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Thread sc;
    private volatile AdvertisingIdClient.Info tp;

    /* renamed from: com.google.android.gms.tagmanager.a$a  reason: collision with other inner class name */
    public interface C0128a {
        AdvertisingIdClient.Info lb();
    }

    private a(Context context) {
        this(context, (C0128a) null, im.fW());
    }

    a(Context context, C0128a aVar, ik ikVar) {
        this.adZ = 900000;
        this.aea = 30000;
        this.mClosed = false;
        this.aed = new C0128a() {
            public AdvertisingIdClient.Info lb() {
                try {
                    return AdvertisingIdClient.getAdvertisingIdInfo(a.this.mContext);
                } catch (IllegalStateException e) {
                    bh.D("IllegalStateException getting Advertising Id Info");
                    return null;
                } catch (GooglePlayServicesRepairableException e2) {
                    bh.D("GooglePlayServicesRepairableException getting Advertising Id Info");
                    return null;
                } catch (IOException e3) {
                    bh.D("IOException getting Ad Id Info");
                    return null;
                } catch (GooglePlayServicesNotAvailableException e4) {
                    bh.D("GooglePlayServicesNotAvailableException getting Advertising Id Info");
                    return null;
                } catch (Exception e5) {
                    bh.D("Unknown exception. Could not get the Advertising Id Info.");
                    return null;
                }
            }
        };
        this.aec = ikVar;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        if (aVar != null) {
            this.aed = aVar;
        }
        this.sc = new Thread(new Runnable() {
            public void run() {
                a.this.kZ();
            }
        });
    }

    static a J(Context context) {
        if (aee == null) {
            synchronized (tn) {
                if (aee == null) {
                    aee = new a(context);
                    aee.start();
                }
            }
        }
        return aee;
    }

    /* access modifiers changed from: private */
    public void kZ() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            try {
                this.tp = this.aed.lb();
                Thread.sleep(this.adZ);
            } catch (InterruptedException e) {
                bh.B("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    private void la() {
        if (this.aec.currentTimeMillis() - this.aeb >= this.aea) {
            interrupt();
            this.aeb = this.aec.currentTimeMillis();
        }
    }

    /* access modifiers changed from: package-private */
    public void interrupt() {
        this.sc.interrupt();
    }

    public boolean isLimitAdTrackingEnabled() {
        la();
        if (this.tp == null) {
            return true;
        }
        return this.tp.isLimitAdTrackingEnabled();
    }

    public String kY() {
        la();
        if (this.tp == null) {
            return null;
        }
        return this.tp.getId();
    }

    /* access modifiers changed from: package-private */
    public void start() {
        this.sc.start();
    }
}
