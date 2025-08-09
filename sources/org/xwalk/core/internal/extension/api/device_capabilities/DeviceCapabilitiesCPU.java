package org.xwalk.core.internal.extension.api.device_capabilities;

import com.facebook.internal.AnalyticsEvents;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.json.JSONException;
import org.json.JSONObject;

class DeviceCapabilitiesCPU {
    private static final String SYSTEM_INFO_STAT_FILE = "/proc/stat";
    private static final String TAG = "DeviceCapabilitiesCPU";
    private String mCPUArch = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
    private double mCPULoad = 0.0d;
    private int mCoreNum = 0;
    private DeviceCapabilities mDeviceCapabilities;

    public DeviceCapabilitiesCPU(DeviceCapabilities instance) {
        this.mDeviceCapabilities = instance;
        this.mCoreNum = Runtime.getRuntime().availableProcessors();
        this.mCPUArch = System.getProperty("os.arch");
    }

    public JSONObject getInfo() {
        getCPULoad();
        JSONObject out = new JSONObject();
        try {
            out.put("numOfProcessors", this.mCoreNum);
            out.put("archName", this.mCPUArch);
            out.put("load", this.mCPULoad);
            return out;
        } catch (JSONException e) {
            return this.mDeviceCapabilities.setErrorMessage(e.toString());
        }
    }

    private boolean getCPULoad() {
        try {
            RandomAccessFile file = new RandomAccessFile(SYSTEM_INFO_STAT_FILE, "r");
            String[] arrs = file.readLine().split("\\s+");
            long total1 = 0;
            for (int i = 1; i < arrs.length; i++) {
                total1 += Long.parseLong(arrs[i]);
            }
            long used1 = total1 - Long.parseLong(arrs[4]);
            try {
                Thread.sleep(1000);
                file.seek(0);
                String line = file.readLine();
                file.close();
                String[] arrs2 = line.split("\\s+");
                long total2 = 0;
                for (int i2 = 1; i2 < arrs2.length; i2++) {
                    total2 += Long.parseLong(arrs2[i2]);
                }
                long used2 = total2 - Long.parseLong(arrs2[4]);
                if (total2 == total1) {
                    this.mCPULoad = 0.0d;
                } else {
                    this.mCPULoad = ((double) (used2 - used1)) / ((double) (total2 - total1));
                }
                return true;
            } catch (Exception e) {
                this.mCPULoad = 0.0d;
                return false;
            }
        } catch (IOException e2) {
            this.mCPULoad = 0.0d;
            return false;
        }
    }
}
