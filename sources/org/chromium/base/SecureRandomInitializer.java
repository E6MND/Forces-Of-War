package org.chromium.base;

public class SecureRandomInitializer {
    private static final int NUM_RANDOM_BYTES = 16;
    private static byte[] sSeedBytes = new byte[16];

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[SYNTHETIC, Splitter:B:11:0x001f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initialize(java.security.SecureRandom r4) throws java.io.IOException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "/dev/urandom"
            r1.<init>(r2)     // Catch:{ all -> 0x0032 }
            byte[] r2 = sSeedBytes     // Catch:{ all -> 0x001b }
            int r2 = r1.read(r2)     // Catch:{ all -> 0x001b }
            byte[] r3 = sSeedBytes     // Catch:{ all -> 0x001b }
            int r3 = r3.length     // Catch:{ all -> 0x001b }
            if (r2 == r3) goto L_0x0023
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x001b }
            java.lang.String r3 = "Failed to get enough random data."
            r2.<init>(r3)     // Catch:{ all -> 0x001b }
            throw r2     // Catch:{ all -> 0x001b }
        L_0x001b:
            r2 = move-exception
            r0 = r1
        L_0x001d:
            if (r0 == 0) goto L_0x0022
            r0.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0022:
            throw r2
        L_0x0023:
            byte[] r2 = sSeedBytes     // Catch:{ all -> 0x001b }
            r4.setSeed(r2)     // Catch:{ all -> 0x001b }
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch:{ IOException -> 0x002e }
        L_0x002d:
            return
        L_0x002e:
            r2 = move-exception
            goto L_0x002d
        L_0x0030:
            r3 = move-exception
            goto L_0x0022
        L_0x0032:
            r2 = move-exception
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.SecureRandomInitializer.initialize(java.security.SecureRandom):void");
    }
}
