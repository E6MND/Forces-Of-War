package org.chromium.content.browser.crypto;

public class ByteArrayGenerator {
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getBytes(int r6) throws java.io.IOException, java.security.GeneralSecurityException {
        /*
            r5 = this;
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0027 }
            java.lang.String r3 = "/dev/urandom"
            r2.<init>(r3)     // Catch:{ all -> 0x0027 }
            byte[] r0 = new byte[r6]     // Catch:{ all -> 0x0019 }
            int r3 = r0.length     // Catch:{ all -> 0x0019 }
            int r4 = r2.read(r0)     // Catch:{ all -> 0x0019 }
            if (r3 == r4) goto L_0x0021
            java.security.GeneralSecurityException r3 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x0019 }
            java.lang.String r4 = "Not enough random data available"
            r3.<init>(r4)     // Catch:{ all -> 0x0019 }
            throw r3     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r3 = move-exception
            r1 = r2
        L_0x001b:
            if (r1 == 0) goto L_0x0020
            r1.close()
        L_0x0020:
            throw r3
        L_0x0021:
            if (r2 == 0) goto L_0x0026
            r2.close()
        L_0x0026:
            return r0
        L_0x0027:
            r3 = move-exception
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.crypto.ByteArrayGenerator.getBytes(int):byte[]");
    }
}
