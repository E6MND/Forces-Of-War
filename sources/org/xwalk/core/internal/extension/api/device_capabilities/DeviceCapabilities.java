package org.xwalk.core.internal.extension.api.device_capabilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.XWalkExtensionWithActivityStateListener;

public class DeviceCapabilities extends XWalkExtensionWithActivityStateListener {
    public static final String JS_API_PATH = "jsapi/device_capabilities_api.js";
    private static final String NAME = "xwalk.experimental.system";
    private static final String TAG = "DeviceCapabilities";
    private DeviceCapabilitiesCPU mCPU = new DeviceCapabilitiesCPU(this);
    private DeviceCapabilitiesCodecs mCodecs = new DeviceCapabilitiesCodecs(this);
    private DeviceCapabilitiesDisplay mDisplay;
    private DeviceCapabilitiesMemory mMemory;
    private DeviceCapabilitiesStorage mStorage;

    public DeviceCapabilities(String jsApiContent, Activity activity) {
        super(NAME, jsApiContent, activity);
        Context context = activity.getApplicationContext();
        this.mDisplay = new DeviceCapabilitiesDisplay(this, context);
        this.mMemory = new DeviceCapabilitiesMemory(this, context);
        this.mStorage = new DeviceCapabilitiesStorage(this, activity);
    }

    private void handleMessage(int instanceID, String message) {
        try {
            JSONObject jsonInput = new JSONObject(message);
            String cmd = jsonInput.getString("cmd");
            if (cmd.equals("addEventListener")) {
                handleAddEventListener(jsonInput.getString("eventName"));
            } else {
                handleGetDeviceInfo(instanceID, jsonInput.getString("asyncCallId"), cmd);
            }
        } catch (JSONException e) {
            printErrorMessage(e);
        }
    }

    private void handleGetDeviceInfo(int instanceID, String asyncCallId, String cmd) {
        try {
            JSONObject jsonOutput = new JSONObject();
            if (cmd.equals("getCPUInfo")) {
                jsonOutput.put("data", this.mCPU.getInfo());
            } else if (cmd.equals("getCodecsInfo")) {
                jsonOutput.put("data", this.mCodecs.getInfo());
            } else if (cmd.equals("getDisplayInfo")) {
                jsonOutput.put("data", this.mDisplay.getInfo());
            } else if (cmd.equals("getMemoryInfo")) {
                jsonOutput.put("data", this.mMemory.getInfo());
            } else if (cmd.equals("getStorageInfo")) {
                jsonOutput.put("data", this.mStorage.getInfo());
            }
            jsonOutput.put("asyncCallId", asyncCallId);
            postMessage(instanceID, jsonOutput.toString());
        } catch (JSONException e) {
            printErrorMessage(e);
        }
    }

    private void handleAddEventListener(String eventName) {
        if (eventName.equals("storageattach") || eventName.equals("storagedetach")) {
            this.mStorage.registerListener();
        }
    }

    /* access modifiers changed from: protected */
    public void printErrorMessage(JSONException e) {
        Log.e(TAG, e.toString());
    }

    /* access modifiers changed from: protected */
    public JSONObject setErrorMessage(String error) {
        JSONObject out = new JSONObject();
        JSONObject errorMessage = new JSONObject();
        try {
            errorMessage.put("message", error);
            out.put("error", errorMessage);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        return out;
    }

    public void onMessage(int instanceID, String message) {
        if (!message.isEmpty()) {
            handleMessage(instanceID, message);
        }
    }

    public void onActivityStateChange(Activity activity, int newState) {
        switch (newState) {
            case 3:
                this.mDisplay.onResume();
                this.mStorage.onResume();
                return;
            case 4:
                this.mDisplay.onPause();
                this.mStorage.onPause();
                return;
            case 6:
                this.mDisplay.onDestroy();
                this.mStorage.onDestroy();
                return;
            default:
                return;
        }
    }

    public String onSyncMessage(int instanceID, String message) {
        return null;
    }
}
