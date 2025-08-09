package org.xwalk.core.internal.extension.api.device_capabilities;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.XWalkDisplayManager;

class DeviceCapabilitiesDisplay {
    private static final String TAG = "DeviceCapabilitiesDisplay";
    private DeviceCapabilities mDeviceCapabilities;
    /* access modifiers changed from: private */
    public final SparseArray<Display> mDisplayList = new SparseArray<>();
    private final XWalkDisplayManager.DisplayListener mDisplayListener = new XWalkDisplayManager.DisplayListener() {
        public void onDisplayAdded(int displayId) {
            DeviceCapabilitiesDisplay.this.notifyAndSaveConnectedDisplay(DeviceCapabilitiesDisplay.this.mDisplayManager.getDisplay(displayId));
        }

        public void onDisplayRemoved(int displayId) {
            Display disp = (Display) DeviceCapabilitiesDisplay.this.mDisplayList.get(displayId);
            if (disp != null) {
                DeviceCapabilitiesDisplay.this.notifyAndRemoveDisconnectedDisplay(disp);
            }
        }

        public void onDisplayChanged(int displayId) {
        }
    };
    /* access modifiers changed from: private */
    public XWalkDisplayManager mDisplayManager;

    public DeviceCapabilitiesDisplay(DeviceCapabilities instance, Context context) {
        this.mDeviceCapabilities = instance;
        this.mDisplayManager = XWalkDisplayManager.getInstance(context);
        initDisplayList();
    }

    public JSONObject getInfo() {
        JSONObject out = new JSONObject();
        JSONArray arr = new JSONArray();
        int i = 0;
        while (i < this.mDisplayList.size()) {
            try {
                arr.put(convertDisplayToJSON(this.mDisplayList.valueAt(i)));
                i++;
            } catch (JSONException e) {
                return this.mDeviceCapabilities.setErrorMessage(e.toString());
            }
        }
        out.put("displays", arr);
        return out;
    }

    public JSONObject convertDisplayToJSON(Display disp) {
        boolean z;
        boolean z2 = true;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        disp.getRealMetrics(displayMetrics);
        Point realSize = new Point();
        disp.getRealSize(realSize);
        Point availSize = new Point();
        disp.getSize(availSize);
        JSONObject out = new JSONObject();
        try {
            out.put("id", Integer.toString(disp.getDisplayId()));
            out.put("name", disp.getName());
            if (disp.getDisplayId() == 0) {
                z = true;
            } else {
                z = false;
            }
            out.put("primary", z);
            if (disp.getDisplayId() == 0) {
                z2 = false;
            }
            out.put("external", z2);
            out.put("deviceXDPI", (int) displayMetrics.xdpi);
            out.put("deviceYDPI", (int) displayMetrics.ydpi);
            out.put("width", realSize.x);
            out.put("height", realSize.y);
            out.put("availWidth", availSize.x);
            out.put("availHeight", availSize.y);
            out.put("colorDepth", 24);
            out.put("pixelDepth", 24);
            return out;
        } catch (JSONException e) {
            return this.mDeviceCapabilities.setErrorMessage(e.toString());
        }
    }

    private void initDisplayList() {
        for (Display disp : this.mDisplayManager.getDisplays()) {
            this.mDisplayList.put(disp.getDisplayId(), disp);
        }
    }

    /* access modifiers changed from: private */
    public void notifyAndSaveConnectedDisplay(Display disp) {
        if (disp != null) {
            JSONObject out = new JSONObject();
            try {
                out.put("reply", "connectDisplay");
                out.put("eventName", "displayconnect");
                out.put("data", convertDisplayToJSON(disp));
                this.mDeviceCapabilities.broadcastMessage(out.toString());
                this.mDisplayList.put(disp.getDisplayId(), disp);
            } catch (JSONException e) {
                this.mDeviceCapabilities.printErrorMessage(e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void notifyAndRemoveDisconnectedDisplay(Display disp) {
        JSONObject out = new JSONObject();
        try {
            out.put("reply", "disconnectDisplay");
            out.put("eventName", "displaydisconnect");
            out.put("data", convertDisplayToJSON(disp));
            this.mDeviceCapabilities.broadcastMessage(out.toString());
            this.mDisplayList.remove(disp.getDisplayId());
        } catch (JSONException e) {
            this.mDeviceCapabilities.printErrorMessage(e);
        }
    }

    public void onResume() {
        Display[] displays = this.mDisplayManager.getDisplays();
        for (Display disp : displays) {
            if (this.mDisplayList.get(disp.getDisplayId()) == null) {
                notifyAndSaveConnectedDisplay(disp);
            } else {
                this.mDisplayList.put(disp.getDisplayId(), disp);
            }
        }
        for (int i = 0; i < this.mDisplayList.size(); i++) {
            boolean found = false;
            Display[] arr$ = displays;
            int len$ = arr$.length;
            int i$ = 0;
            while (true) {
                if (i$ >= len$) {
                    break;
                }
                if (this.mDisplayList.valueAt(i).getDisplayId() == arr$[i$].getDisplayId()) {
                    found = true;
                    break;
                }
                i$++;
            }
            if (!found) {
                notifyAndRemoveDisconnectedDisplay(this.mDisplayList.valueAt(i));
            }
        }
        this.mDisplayManager.registerDisplayListener(this.mDisplayListener);
    }

    public void onPause() {
        this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
    }

    public void onDestroy() {
    }
}
