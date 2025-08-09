package org.xwalk.core.internal.extension.api.device_capabilities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.SparseArray;
import java.io.File;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

class DeviceCapabilitiesStorage {
    private static final String TAG = "DeviceCapabilitiesStorage";
    private static int mStorageCount = 0;
    private WeakReference<Activity> mActivity;
    /* access modifiers changed from: private */
    public DeviceCapabilities mDeviceCapabilities;
    private IntentFilter mIntentFilter = new IntentFilter();
    private boolean mIsListening = false;
    private final SparseArray<StorageUnit> mStorageList = new SparseArray<>();
    private final BroadcastReceiver mStorageListener = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                DeviceCapabilitiesStorage.this.notifyAndSaveAttachedStorage();
            }
            if ("android.intent.action.MEDIA_UNMOUNTED".equals(action) || "android.intent.action.MEDIA_REMOVED".equals(action) || "android.intent.action.MEDIA_BAD_REMOVAL".equals(action)) {
                DeviceCapabilitiesStorage.this.notifyAndRemoveDetachedStorage();
            }
        }
    };

    class StorageUnit {
        private long mAvailCapacity = 0;
        private long mCapacity = 0;
        private int mId;
        private String mName;
        private String mPath = "";
        private String mType;

        public StorageUnit(int id, String name, String type) {
            this.mId = id;
            this.mName = name;
            this.mType = type;
        }

        public int getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public String getType() {
            return this.mType;
        }

        public String getPath() {
            return this.mPath;
        }

        public long getCapacity() {
            return this.mCapacity;
        }

        public long getAvailCapacity() {
            return this.mAvailCapacity;
        }

        public void setType(String type) {
            this.mType = type;
        }

        public void setPath(String path) {
            this.mPath = path;
            updateCapacity();
        }

        public boolean isSame(StorageUnit unit) {
            return this.mPath == unit.getPath();
        }

        public boolean isValid() {
            if (this.mPath != null && !this.mPath.isEmpty()) {
                return new File(this.mPath).canRead();
            }
            this.mCapacity = 0;
            this.mAvailCapacity = 0;
            return false;
        }

        public void updateCapacity() {
            if (isValid()) {
                StatFs stat = new StatFs(this.mPath);
                if (Build.VERSION.SDK_INT >= 18) {
                    long blockSize = stat.getBlockSizeLong();
                    this.mCapacity = stat.getBlockCountLong() * blockSize;
                    this.mAvailCapacity = stat.getAvailableBlocksLong() * blockSize;
                    return;
                }
                long blockSize2 = (long) stat.getBlockSize();
                this.mCapacity = ((long) stat.getBlockCount()) * blockSize2;
                this.mAvailCapacity = ((long) stat.getAvailableBlocks()) * blockSize2;
            }
        }

        public JSONObject convertToJSON() {
            JSONObject out = new JSONObject();
            try {
                out.put("id", Integer.toString(this.mId + 1));
                out.put("name", this.mName);
                out.put(MessagingSmsConsts.TYPE, this.mType);
                out.put("capacity", this.mCapacity);
                out.put("availCapacity", this.mAvailCapacity);
                return out;
            } catch (JSONException e) {
                return DeviceCapabilitiesStorage.this.mDeviceCapabilities.setErrorMessage(e.toString());
            }
        }
    }

    public DeviceCapabilitiesStorage(DeviceCapabilities instance, Activity activity) {
        this.mDeviceCapabilities = instance;
        this.mActivity = new WeakReference<>(activity);
        registerIntentFilter();
        initStorageList();
    }

    public JSONObject getInfo() {
        JSONObject out = new JSONObject();
        JSONArray arr = new JSONArray();
        int i = 0;
        while (i < this.mStorageList.size()) {
            try {
                arr.put(this.mStorageList.valueAt(i).convertToJSON());
                i++;
            } catch (JSONException e) {
                return this.mDeviceCapabilities.setErrorMessage(e.toString());
            }
        }
        out.put("storages", arr);
        return out;
    }

    private void initStorageList() {
        this.mStorageList.clear();
        mStorageCount = 0;
        StorageUnit unit = new StorageUnit(mStorageCount, "Internal", "fixed");
        unit.setPath(Environment.getRootDirectory().getAbsolutePath());
        this.mStorageList.put(mStorageCount, unit);
        mStorageCount++;
        StorageUnit unit2 = new StorageUnit(mStorageCount, new String("sdcard" + Integer.toString(mStorageCount - 1)), "fixed");
        if (Environment.isExternalStorageRemovable()) {
            unit2.setType("removable");
        }
        unit2.setPath(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (unit2.isValid()) {
            this.mStorageList.put(mStorageCount, unit2);
            mStorageCount++;
        }
        attemptAddExternalStorage();
    }

    private void registerIntentFilter() {
        this.mIntentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        this.mIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        this.mIntentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        this.mIntentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        this.mIntentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        this.mIntentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        this.mIntentFilter.addDataScheme(AndroidProtocolHandler.FILE_SCHEME);
    }

    private boolean attemptAddExternalStorage() {
        int sdcardNum = mStorageCount - 1;
        StorageUnit unit = new StorageUnit(mStorageCount, new String("sdcard" + Integer.toString(sdcardNum)), "removable");
        unit.setPath("/storage/sdcard" + Integer.toString(sdcardNum));
        if (!unit.isValid()) {
            return false;
        }
        for (int i = 0; i < this.mStorageList.size(); i++) {
            if (unit.isSame(this.mStorageList.valueAt(i))) {
                return false;
            }
        }
        this.mStorageList.put(mStorageCount, unit);
        mStorageCount++;
        return true;
    }

    public void registerListener() {
        if (!this.mIsListening) {
            this.mIsListening = true;
            Activity activity = (Activity) this.mActivity.get();
            if (activity != null) {
                activity.registerReceiver(this.mStorageListener, this.mIntentFilter);
            }
        }
    }

    public void unregisterListener() {
        if (this.mIsListening) {
            this.mIsListening = false;
            Activity activity = (Activity) this.mActivity.get();
            if (activity != null) {
                activity.unregisterReceiver(this.mStorageListener);
            }
        }
    }

    /* access modifiers changed from: private */
    public void notifyAndSaveAttachedStorage() {
        if (attemptAddExternalStorage()) {
            StorageUnit unit = this.mStorageList.valueAt(this.mStorageList.size() - 1);
            JSONObject out = new JSONObject();
            try {
                out.put("reply", "attachStorage");
                out.put("eventName", "storageattach");
                out.put("data", unit.convertToJSON());
                this.mDeviceCapabilities.broadcastMessage(out.toString());
            } catch (JSONException e) {
                this.mDeviceCapabilities.printErrorMessage(e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void notifyAndRemoveDetachedStorage() {
        StorageUnit unit = this.mStorageList.valueAt(this.mStorageList.size() - 1);
        if (unit.getType() == "removable") {
            JSONObject out = new JSONObject();
            try {
                out.put("reply", "detachStorage");
                out.put("eventName", "storagedetach");
                out.put("data", unit.convertToJSON());
                this.mDeviceCapabilities.broadcastMessage(out.toString());
                this.mStorageList.remove(unit.getId());
                mStorageCount--;
            } catch (JSONException e) {
                this.mDeviceCapabilities.printErrorMessage(e);
            }
        }
    }

    public void onResume() {
        if (!this.mStorageList.valueAt(this.mStorageList.size() - 1).isValid()) {
            notifyAndRemoveDetachedStorage();
        }
        notifyAndSaveAttachedStorage();
        registerListener();
    }

    public void onPause() {
        unregisterListener();
    }

    public void onDestroy() {
    }
}
