package org.xwalk.core.internal.extension.api.device_capabilities;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

class DeviceCapabilitiesMemory {
    private static final String MEM_INFO_FILE = "/proc/meminfo";
    private static final String TAG = "DeviceCapabilitiesMemory";
    private long mAvailableCapacity = 0;
    private long mCapacity = 0;
    private Context mContext;
    private DeviceCapabilities mDeviceCapabilities;

    public DeviceCapabilitiesMemory(DeviceCapabilities instance, Context context) {
        this.mDeviceCapabilities = instance;
        this.mContext = context;
    }

    public JSONObject getInfo() {
        readMemoryInfo();
        JSONObject out = new JSONObject();
        try {
            out.put("capacity", this.mCapacity);
            out.put("availCapacity", this.mAvailableCapacity);
            return out;
        } catch (JSONException e) {
            return this.mDeviceCapabilities.setErrorMessage(e.toString());
        }
    }

    private void readMemoryInfo() {
        ActivityManager.MemoryInfo mem_info = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.mContext.getSystemService("activity")).getMemoryInfo(mem_info);
        if (Build.VERSION.SDK_INT >= 16) {
            this.mCapacity = mem_info.totalMem;
        } else {
            this.mCapacity = getTotalMemFromFile();
        }
        this.mAvailableCapacity = mem_info.availMem;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0081 A[SYNTHETIC, Splitter:B:35:0x0081] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long getTotalMemFromFile() {
        /*
            r12 = this;
            r2 = 0
            r4 = 0
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0061 }
            java.lang.String r8 = "/proc/meminfo"
            java.lang.String r9 = "r"
            r5.<init>(r8, r9)     // Catch:{ IOException -> 0x0061 }
            java.lang.String r6 = r5.readLine()     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            java.lang.String r8 = ":"
            java.lang.String[] r0 = r6.split(r8)     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            r8 = 0
            r8 = r0[r8]     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            java.lang.String r9 = "MemTotal"
            boolean r8 = r8.equals(r9)     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            if (r8 != 0) goto L_0x0035
            r8 = 0
            if (r5 == 0) goto L_0x0028
            r5.close()     // Catch:{ IOException -> 0x002a }
        L_0x0028:
            r4 = r5
        L_0x0029:
            return r8
        L_0x002a:
            r1 = move-exception
            java.lang.String r10 = "DeviceCapabilitiesMemory"
            java.lang.String r11 = r1.toString()
            android.util.Log.e(r10, r11)
            goto L_0x0028
        L_0x0035:
            r8 = 1
            r8 = r0[r8]     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            java.lang.String r8 = r8.trim()     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            java.lang.String r9 = "\\s+"
            java.lang.String[] r7 = r8.split(r9)     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            r8 = 0
            r8 = r7[r8]     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            long r8 = java.lang.Long.parseLong(r8)     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            r10 = 1024(0x400, double:5.06E-321)
            long r2 = r8 * r10
            if (r5 == 0) goto L_0x0052
            r5.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0052:
            r4 = r5
        L_0x0053:
            r8 = r2
            goto L_0x0029
        L_0x0055:
            r1 = move-exception
            java.lang.String r8 = "DeviceCapabilitiesMemory"
            java.lang.String r9 = r1.toString()
            android.util.Log.e(r8, r9)
            r4 = r5
            goto L_0x0053
        L_0x0061:
            r1 = move-exception
        L_0x0062:
            r2 = 0
            java.lang.String r8 = "DeviceCapabilitiesMemory"
            java.lang.String r9 = r1.toString()     // Catch:{ all -> 0x007e }
            android.util.Log.e(r8, r9)     // Catch:{ all -> 0x007e }
            if (r4 == 0) goto L_0x0053
            r4.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0053
        L_0x0073:
            r1 = move-exception
            java.lang.String r8 = "DeviceCapabilitiesMemory"
            java.lang.String r9 = r1.toString()
            android.util.Log.e(r8, r9)
            goto L_0x0053
        L_0x007e:
            r8 = move-exception
        L_0x007f:
            if (r4 == 0) goto L_0x0084
            r4.close()     // Catch:{ IOException -> 0x0085 }
        L_0x0084:
            throw r8
        L_0x0085:
            r1 = move-exception
            java.lang.String r9 = "DeviceCapabilitiesMemory"
            java.lang.String r10 = r1.toString()
            android.util.Log.e(r9, r10)
            goto L_0x0084
        L_0x0090:
            r8 = move-exception
            r4 = r5
            goto L_0x007f
        L_0x0093:
            r1 = move-exception
            r4 = r5
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.extension.api.device_capabilities.DeviceCapabilitiesMemory.getTotalMemFromFile():long");
    }
}
