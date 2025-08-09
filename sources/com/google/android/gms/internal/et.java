package com.google.android.gms.internal;

import android.content.Context;

public final class et extends en {
    private final String lp;
    private final Context mContext;
    private final String qV;

    public et(Context context, String str, String str2) {
        this.mContext = context;
        this.lp = str;
        this.qV = str2;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bc() {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            r0.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.lang.String r1 = "Pinging URL: "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.lang.String r1 = r4.qV     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.lang.String r0 = r0.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            com.google.android.gms.internal.ev.C(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.net.URL r0 = new java.net.URL     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.lang.String r1 = r4.qV     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            r0.<init>(r1)     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            android.content.Context r1 = r4.mContext     // Catch:{ all -> 0x005f }
            java.lang.String r2 = r4.lp     // Catch:{ all -> 0x005f }
            r3 = 1
            com.google.android.gms.internal.ep.a(r1, r2, r3, r0)     // Catch:{ all -> 0x005f }
            int r1 = r0.getResponseCode()     // Catch:{ all -> 0x005f }
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 < r2) goto L_0x0039
            r2 = 300(0x12c, float:4.2E-43)
            if (r1 < r2) goto L_0x005b
        L_0x0039:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r2.<init>()     // Catch:{ all -> 0x005f }
            java.lang.String r3 = "Received non-success response code "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x005f }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ all -> 0x005f }
            java.lang.String r2 = " from pinging URL: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x005f }
            java.lang.String r2 = r4.qV     // Catch:{ all -> 0x005f }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x005f }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x005f }
            com.google.android.gms.internal.ev.D(r1)     // Catch:{ all -> 0x005f }
        L_0x005b:
            r0.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
        L_0x005e:
            return
        L_0x005f:
            r1 = move-exception
            r0.disconnect()     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
            throw r1     // Catch:{ IndexOutOfBoundsException -> 0x0064, IOException -> 0x008c }
        L_0x0064:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Error while parsing ping URL: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r4.qV
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ". "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ev.D(r0)
            goto L_0x005e
        L_0x008c:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Error while pinging URL: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r4.qV
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ". "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ev.D(r0)
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.et.bc():void");
    }

    public void onStop() {
    }
}
