package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class k extends j {

    class a {
        private String kb;
        private boolean kc;

        public a(String str, boolean z) {
            this.kb = str;
            this.kc = z;
        }

        public String getId() {
            return this.kb;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.kc;
        }
    }

    private k(Context context, n nVar, o oVar) {
        super(context, nVar, oVar);
    }

    public static k a(String str, Context context) {
        e eVar = new e();
        a(str, context, eVar);
        return new k(context, eVar, new q(239));
    }

    /* access modifiers changed from: protected */
    public void b(Context context) {
        long j = 1;
        super.b(context);
        try {
            a f = f(context);
            try {
                if (!f.isLimitAdTrackingEnabled()) {
                    j = 0;
                }
                a(28, j);
                String id = f.getId();
                if (id != null) {
                    a(30, id);
                }
            } catch (IOException e) {
            }
        } catch (GooglePlayServicesNotAvailableException e2) {
        } catch (IOException e3) {
            a(28, 1);
        }
    }

    /* access modifiers changed from: package-private */
    public a f(Context context) throws IOException, GooglePlayServicesNotAvailableException {
        String str;
        int i = 0;
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            String id = advertisingIdInfo.getId();
            if (id == null || !id.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")) {
                str = id;
            } else {
                byte[] bArr = new byte[16];
                int i2 = 0;
                while (i < id.length()) {
                    if (i == 8 || i == 13 || i == 18 || i == 23) {
                        i++;
                    }
                    bArr[i2] = (byte) ((Character.digit(id.charAt(i), 16) << 4) + Character.digit(id.charAt(i + 1), 16));
                    i2++;
                    i += 2;
                }
                str = this.jQ.a(bArr, true);
            }
            return new a(str, advertisingIdInfo.isLimitAdTrackingEnabled());
        } catch (GooglePlayServicesRepairableException e) {
            throw new IOException(e);
        }
    }
}
