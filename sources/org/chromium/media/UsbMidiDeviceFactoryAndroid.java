package org.chromium.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
class UsbMidiDeviceFactoryAndroid {
    private static final String ACTION_USB_PERMISSION = "org.chromium.media.USB_PERMISSION";
    private final List<UsbMidiDeviceAndroid> mDevices = new ArrayList();
    private long mNativePointer;
    private BroadcastReceiver mReceiver;
    private Set<UsbDevice> mRequestedDevices;
    private UsbManager mUsbManager;

    private static native void nativeOnUsbMidiDeviceRequestDone(long j, Object[] objArr);

    UsbMidiDeviceFactoryAndroid(long nativePointer) {
        this.mNativePointer = nativePointer;
    }

    @CalledByNative
    static UsbMidiDeviceFactoryAndroid create(long nativePointer) {
        return new UsbMidiDeviceFactoryAndroid(nativePointer);
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public boolean enumerateDevices(Context context) {
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
        Map<String, UsbDevice> devices = this.mUsbManager.getDeviceList();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        this.mRequestedDevices = new HashSet();
        for (UsbDevice device : devices.values()) {
            boolean found = false;
            for (int i = 0; i < device.getInterfaceCount() && !found; i++) {
                UsbInterface iface = device.getInterface(i);
                if (iface.getInterfaceClass() == 1 && iface.getInterfaceSubclass() == 3) {
                    found = true;
                }
            }
            if (found) {
                this.mUsbManager.requestPermission(device, pendingIntent);
                this.mRequestedDevices.add(device);
            }
        }
        if (this.mRequestedDevices.isEmpty()) {
            return false;
        }
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (UsbMidiDeviceFactoryAndroid.ACTION_USB_PERMISSION.equals(intent.getAction())) {
                    UsbMidiDeviceFactoryAndroid.this.onRequestDone(context, intent);
                }
            }
        };
        context.registerReceiver(this.mReceiver, filter);
        return true;
    }

    /* access modifiers changed from: private */
    public void onRequestDone(Context context, Intent intent) {
        UsbDevice device = (UsbDevice) intent.getParcelableExtra("device");
        if (this.mRequestedDevices.contains(device)) {
            this.mRequestedDevices.remove(device);
            if (!intent.getBooleanExtra("permission", false)) {
                device = null;
            }
            if (device != null) {
                this.mDevices.add(new UsbMidiDeviceAndroid(this.mUsbManager, device));
            }
            if (this.mRequestedDevices.isEmpty()) {
                context.unregisterReceiver(this.mReceiver);
                if (this.mNativePointer != 0) {
                    nativeOnUsbMidiDeviceRequestDone(this.mNativePointer, this.mDevices.toArray());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void close() {
        this.mNativePointer = 0;
    }
}
