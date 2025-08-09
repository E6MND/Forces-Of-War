package org.chromium.base;

import android.os.Build;

public class SysUtils {
    static final /* synthetic */ boolean $assertionsDisabled = (!SysUtils.class.desiredAssertionStatus());
    private static final int ANDROID_LOW_MEMORY_ANDROID_SDK_THRESHOLD = 18;
    private static final long ANDROID_LOW_MEMORY_DEVICE_THRESHOLD_MB = 512;
    private static final String TAG = "SysUtils";
    private static Boolean sLowEndDevice;

    private SysUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        r7 = java.lang.Integer.parseInt(r3.group(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r7 > 1024) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        android.util.Log.w(TAG, "Invalid /proc/meminfo total size in kB: " + r3.group(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007f, code lost:
        android.os.StrictMode.setThreadPolicy(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int amountOfPhysicalMemoryKB() {
        /*
            java.lang.String r8 = "^MemTotal:\\s+([0-9]+) kB$"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r8)
            android.os.StrictMode$ThreadPolicy r4 = android.os.StrictMode.allowThreadDiskReads()
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Exception -> 0x006d }
            java.lang.String r8 = "/proc/meminfo"
            r1.<init>(r8)     // Catch:{ Exception -> 0x006d }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ all -> 0x0068 }
            r6.<init>(r1)     // Catch:{ all -> 0x0068 }
        L_0x0016:
            java.lang.String r2 = r6.readLine()     // Catch:{ all -> 0x0063 }
            if (r2 != 0) goto L_0x002e
            java.lang.String r8 = "SysUtils"
            java.lang.String r9 = "/proc/meminfo lacks a MemTotal entry?"
            android.util.Log.w(r8, r9)     // Catch:{ all -> 0x0063 }
        L_0x0023:
            r6.close()     // Catch:{ all -> 0x0068 }
            r1.close()     // Catch:{ Exception -> 0x006d }
            android.os.StrictMode.setThreadPolicy(r4)
        L_0x002c:
            r7 = 0
        L_0x002d:
            return r7
        L_0x002e:
            java.util.regex.Matcher r3 = r5.matcher(r2)     // Catch:{ all -> 0x0063 }
            boolean r8 = r3.find()     // Catch:{ all -> 0x0063 }
            if (r8 == 0) goto L_0x0016
            r8 = 1
            java.lang.String r8 = r3.group(r8)     // Catch:{ all -> 0x0063 }
            int r7 = java.lang.Integer.parseInt(r8)     // Catch:{ all -> 0x0063 }
            r8 = 1024(0x400, float:1.435E-42)
            if (r7 > r8) goto L_0x0079
            java.lang.String r8 = "SysUtils"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0063 }
            r9.<init>()     // Catch:{ all -> 0x0063 }
            java.lang.String r10 = "Invalid /proc/meminfo total size in kB: "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x0063 }
            r10 = 1
            java.lang.String r10 = r3.group(r10)     // Catch:{ all -> 0x0063 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x0063 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0063 }
            android.util.Log.w(r8, r9)     // Catch:{ all -> 0x0063 }
            goto L_0x0023
        L_0x0063:
            r8 = move-exception
            r6.close()     // Catch:{ all -> 0x0068 }
            throw r8     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r8 = move-exception
            r1.close()     // Catch:{ Exception -> 0x006d }
            throw r8     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            r0 = move-exception
            java.lang.String r8 = "SysUtils"
            java.lang.String r9 = "Cannot get total physical size from /proc/meminfo"
            android.util.Log.w(r8, r9, r0)     // Catch:{ all -> 0x0083 }
            android.os.StrictMode.setThreadPolicy(r4)
            goto L_0x002c
        L_0x0079:
            r6.close()     // Catch:{ all -> 0x0068 }
            r1.close()     // Catch:{ Exception -> 0x006d }
            android.os.StrictMode.setThreadPolicy(r4)
            goto L_0x002d
        L_0x0083:
            r8 = move-exception
            android.os.StrictMode.setThreadPolicy(r4)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.SysUtils.amountOfPhysicalMemoryKB():int");
    }

    @CalledByNative
    public static boolean isLowEndDevice() {
        if (sLowEndDevice == null) {
            sLowEndDevice = Boolean.valueOf(detectLowEndDevice());
        }
        return sLowEndDevice.booleanValue();
    }

    private static boolean detectLowEndDevice() {
        if ($assertionsDisabled || CommandLine.isInitialized()) {
            if (CommandLine.getInstance().hasSwitch(BaseSwitches.LOW_END_DEVICE_MODE)) {
                int mode = Integer.parseInt(CommandLine.getInstance().getSwitchValue(BaseSwitches.LOW_END_DEVICE_MODE));
                if (mode == 1) {
                    return true;
                }
                if (mode == 0) {
                    return false;
                }
            }
            if (Build.VERSION.SDK_INT <= ANDROID_LOW_MEMORY_ANDROID_SDK_THRESHOLD) {
                return false;
            }
            int ramSizeKB = amountOfPhysicalMemoryKB();
            if (ramSizeKB <= 0 || ((long) (ramSizeKB / 1024)) >= ANDROID_LOW_MEMORY_DEVICE_THRESHOLD_MB) {
                return false;
            }
            return true;
        }
        throw new AssertionError();
    }
}
