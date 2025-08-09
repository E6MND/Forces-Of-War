package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.k;

class ai extends k<aj> {

    private static class a implements k.a<aj> {
        private final aj xt = new aj();

        public void a(String str, int i) {
            if ("ga_sessionTimeout".equals(str)) {
                this.xt.xw = i;
            } else {
                aa.D("int configuration name not recognized:  " + str);
            }
        }

        public void c(String str, String str2) {
            this.xt.xA.put(str, str2);
        }

        public void c(String str, boolean z) {
            int i = 1;
            if ("ga_autoActivityTracking".equals(str)) {
                aj ajVar = this.xt;
                if (!z) {
                    i = 0;
                }
                ajVar.xx = i;
            } else if ("ga_anonymizeIp".equals(str)) {
                aj ajVar2 = this.xt;
                if (!z) {
                    i = 0;
                }
                ajVar2.xy = i;
            } else if ("ga_reportUncaughtExceptions".equals(str)) {
                aj ajVar3 = this.xt;
                if (!z) {
                    i = 0;
                }
                ajVar3.xz = i;
            } else {
                aa.D("bool configuration name not recognized:  " + str);
            }
        }

        public void d(String str, String str2) {
            if ("ga_trackingId".equals(str)) {
                this.xt.xu = str2;
            } else if ("ga_sampleFrequency".equals(str)) {
                try {
                    this.xt.xv = Double.parseDouble(str2);
                } catch (NumberFormatException e) {
                    aa.A("Error parsing ga_sampleFrequency value: " + str2);
                }
            } else {
                aa.D("string configuration name not recognized:  " + str);
            }
        }

        /* renamed from: dy */
        public aj cw() {
            return this.xt;
        }
    }

    public ai(Context context) {
        super(context, new a());
    }
}
