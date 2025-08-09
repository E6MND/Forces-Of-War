package com.google.android.gms.internal;

import java.util.Map;

public final class ea {
    private ey lL;
    /* access modifiers changed from: private */
    public final Object lq = new Object();
    /* access modifiers changed from: private */
    public int pH = -2;
    public final bd qA = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            synchronized (ea.this.lq) {
                ec ecVar = new ec(map);
                ev.D("Invalid " + ecVar.getType() + " request error: " + ecVar.bt());
                int unused = ea.this.pH = 1;
                ea.this.lq.notify();
            }
        }
    };
    public final bd qB = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            synchronized (ea.this.lq) {
                ec ecVar = new ec(map);
                String url = ecVar.getUrl();
                if (url == null) {
                    ev.D("URL missing in loadAdUrl GMSG.");
                    return;
                }
                if (url.contains("%40mediation_adapters%40")) {
                    ev.C("Ad request URL modified to " + url.replaceAll("%40mediation_adapters%40", em.a(eyVar.getContext(), map.get("check_adapters"), ea.this.qy)));
                }
                ec unused = ea.this.qz = ecVar;
                ea.this.lq.notify();
            }
        }
    };
    /* access modifiers changed from: private */
    public String qy;
    /* access modifiers changed from: private */
    public ec qz;

    public ea(String str) {
        this.qy = str;
    }

    public void b(ey eyVar) {
        synchronized (this.lq) {
            this.lL = eyVar;
        }
    }

    public ec bs() {
        ec ecVar;
        synchronized (this.lq) {
            while (this.qz == null && this.pH == -2) {
                try {
                    this.lq.wait();
                } catch (InterruptedException e) {
                    ev.D("Ad request service was interrupted.");
                    ecVar = null;
                }
            }
            ecVar = this.qz;
        }
        return ecVar;
    }

    public int getErrorCode() {
        int i;
        synchronized (this.lq) {
            i = this.pH;
        }
        return i;
    }
}
