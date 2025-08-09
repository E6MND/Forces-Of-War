package com.crashlytics.android.internal;

final class aO extends Z implements ba {
    public aO(String str, String str2, C0163av avVar) {
        this(str, str2, avVar, C0165ax.GET);
    }

    private aO(String str, String str2, C0163av avVar, C0165ax axVar) {
        super(str, str2, avVar, axVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0146  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject a(com.crashlytics.android.internal.aZ r8) {
        /*
            r7 = this;
            r1 = 0
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            r0.<init>()     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            java.lang.String r2 = "build_version"
            java.lang.String r3 = r8.e     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            java.lang.String r2 = "display_version"
            java.lang.String r3 = r8.d     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            java.lang.String r2 = "source"
            int r3 = r8.f     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            java.lang.String r2 = r8.g     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            if (r2 == 0) goto L_0x002a
            java.lang.String r2 = "icon_hash"
            java.lang.String r3 = r8.g     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            r0.put(r2, r3)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
        L_0x002a:
            java.lang.String r2 = r8.c     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            boolean r3 = com.crashlytics.android.internal.C0143ab.e((java.lang.String) r2)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            if (r3 != 0) goto L_0x0037
            java.lang.String r3 = "instance"
            r0.put(r3, r2)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
        L_0x0037:
            com.crashlytics.android.internal.ay r2 = r7.a(r0)     // Catch:{ Exception -> 0x00d3, all -> 0x011b }
            java.lang.String r3 = "X-CRASHLYTICS-API-KEY"
            java.lang.String r4 = r8.a     // Catch:{ Exception -> 0x0144 }
            com.crashlytics.android.internal.ay r3 = r2.a((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ Exception -> 0x0144 }
            java.lang.String r4 = "X-CRASHLYTICS-API-CLIENT-TYPE"
            java.lang.String r5 = "android"
            com.crashlytics.android.internal.ay r3 = r3.a((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0144 }
            java.lang.String r4 = "X-CRASHLYTICS-D"
            java.lang.String r5 = r8.b     // Catch:{ Exception -> 0x0144 }
            com.crashlytics.android.internal.ay r3 = r3.a((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0144 }
            java.lang.String r4 = "X-CRASHLYTICS-API-CLIENT-VERSION"
            com.crashlytics.android.internal.v r5 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x0144 }
            java.lang.String r5 = r5.getVersion()     // Catch:{ Exception -> 0x0144 }
            com.crashlytics.android.internal.ay r3 = r3.a((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0144 }
            java.lang.String r4 = "Accept"
            java.lang.String r5 = "application/json"
            com.crashlytics.android.internal.ay r2 = r3.a((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0144 }
            com.crashlytics.android.internal.v r3 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x0144 }
            com.crashlytics.android.internal.q r3 = r3.b()     // Catch:{ Exception -> 0x0144 }
            java.lang.String r4 = "Crashlytics"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0144 }
            java.lang.String r6 = "Requesting settings from "
            r5.<init>(r6)     // Catch:{ Exception -> 0x0144 }
            java.lang.String r6 = r7.a()     // Catch:{ Exception -> 0x0144 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x0144 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0144 }
            r3.a(r4, r5)     // Catch:{ Exception -> 0x0144 }
            com.crashlytics.android.internal.v r3 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x0144 }
            com.crashlytics.android.internal.q r3 = r3.b()     // Catch:{ Exception -> 0x0144 }
            java.lang.String r4 = "Crashlytics"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0144 }
            java.lang.String r6 = "Settings query params were: "
            r5.<init>(r6)     // Catch:{ Exception -> 0x0144 }
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ Exception -> 0x0144 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0144 }
            r3.a(r4, r0)     // Catch:{ Exception -> 0x0144 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0144 }
            java.lang.String r3 = r2.c()     // Catch:{ Exception -> 0x0144 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x0144 }
            if (r2 == 0) goto L_0x00d2
            com.crashlytics.android.internal.v r1 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r1 = r1.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Settings request ID: "
            r4.<init>(r5)
            java.lang.String r5 = "X-REQUEST-ID"
            java.lang.String r2 = r2.a((java.lang.String) r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            r1.a(r3, r2)
        L_0x00d2:
            return r0
        L_0x00d3:
            r0 = move-exception
            r2 = r1
        L_0x00d5:
            com.crashlytics.android.internal.v r3 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ all -> 0x0142 }
            com.crashlytics.android.internal.q r3 = r3.b()     // Catch:{ all -> 0x0142 }
            java.lang.String r4 = "Crashlytics"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            java.lang.String r6 = "Failed to retrieve settings from "
            r5.<init>(r6)     // Catch:{ all -> 0x0142 }
            java.lang.String r6 = r7.a()     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0142 }
            r3.a((java.lang.String) r4, (java.lang.String) r5, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0142 }
            if (r2 == 0) goto L_0x0146
            com.crashlytics.android.internal.v r0 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r0 = r0.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Settings request ID: "
            r4.<init>(r5)
            java.lang.String r5 = "X-REQUEST-ID"
            java.lang.String r2 = r2.a((java.lang.String) r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            r0.a(r3, r2)
            r0 = r1
            goto L_0x00d2
        L_0x011b:
            r0 = move-exception
            r2 = r1
        L_0x011d:
            if (r2 == 0) goto L_0x0141
            com.crashlytics.android.internal.v r1 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r1 = r1.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Settings request ID: "
            r4.<init>(r5)
            java.lang.String r5 = "X-REQUEST-ID"
            java.lang.String r2 = r2.a((java.lang.String) r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            r1.a(r3, r2)
        L_0x0141:
            throw r0
        L_0x0142:
            r0 = move-exception
            goto L_0x011d
        L_0x0144:
            r0 = move-exception
            goto L_0x00d5
        L_0x0146:
            r0 = r1
            goto L_0x00d2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.aO.a(com.crashlytics.android.internal.aZ):org.json.JSONObject");
    }
}
