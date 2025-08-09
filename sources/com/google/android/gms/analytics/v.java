package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.k;

class v extends k<w> {

    private static class a implements k.a<w> {
        private final w wf = new w();

        public void a(String str, int i) {
            if ("ga_dispatchPeriod".equals(str)) {
                this.wf.wh = i;
            } else {
                aa.D("int configuration name not recognized:  " + str);
            }
        }

        public void c(String str, String str2) {
        }

        public void c(String str, boolean z) {
            if ("ga_dryRun".equals(str)) {
                this.wf.wi = z ? 1 : 0;
                return;
            }
            aa.D("bool configuration name not recognized:  " + str);
        }

        /* renamed from: cS */
        public w cw() {
            return this.wf;
        }

        public void d(String str, String str2) {
            if ("ga_appName".equals(str)) {
                this.wf.tz = str2;
            } else if ("ga_appVersion".equals(str)) {
                this.wf.tA = str2;
            } else if ("ga_logLevel".equals(str)) {
                this.wf.wg = str2;
            } else {
                aa.D("string configuration name not recognized:  " + str);
            }
        }
    }

    public v(Context context) {
        super(context, new a());
    }
}
