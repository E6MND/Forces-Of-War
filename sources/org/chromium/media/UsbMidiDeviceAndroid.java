package org.chromium.media;

import android.annotation.TargetApi;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Handler;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
class UsbMidiDeviceAndroid {
    static final int MIDI_SUBCLASS = 3;
    /* access modifiers changed from: private */
    public final UsbDeviceConnection mConnection;
    private final SparseArray<UsbEndpoint> mEndpointMap = new SparseArray<>();
    private final Handler mHandler = new Handler();
    private boolean mHasInputThread = false;
    /* access modifiers changed from: private */
    public boolean mIsClosed = false;
    /* access modifiers changed from: private */
    public long mNativePointer = 0;
    private final Map<UsbEndpoint, UsbRequest> mRequestMap = new HashMap();

    /* access modifiers changed from: private */
    public static native void nativeOnData(long j, int i, byte[] bArr);

    UsbMidiDeviceAndroid(UsbManager manager, UsbDevice device) {
        this.mConnection = manager.openDevice(device);
        for (int i = 0; i < device.getInterfaceCount(); i++) {
            UsbInterface iface = device.getInterface(i);
            if (iface.getInterfaceClass() == 1 && iface.getInterfaceSubclass() == 3) {
                this.mConnection.claimInterface(iface, true);
                for (int j = 0; j < iface.getEndpointCount(); j++) {
                    UsbEndpoint endpoint = iface.getEndpoint(j);
                    if (endpoint.getDirection() == 0) {
                        this.mEndpointMap.put(endpoint.getEndpointNumber(), endpoint);
                    }
                }
            }
        }
        startListen(device);
    }

    private void startListen(UsbDevice device) {
        final Map<UsbEndpoint, ByteBuffer> bufferForEndpoints = new HashMap<>();
        for (int i = 0; i < device.getInterfaceCount(); i++) {
            UsbInterface iface = device.getInterface(i);
            if (iface.getInterfaceClass() == 1 && iface.getInterfaceSubclass() == 3) {
                for (int j = 0; j < iface.getEndpointCount(); j++) {
                    UsbEndpoint endpoint = iface.getEndpoint(j);
                    if (endpoint.getDirection() == 128) {
                        ByteBuffer buffer = ByteBuffer.allocate(endpoint.getMaxPacketSize());
                        UsbRequest request = new UsbRequest();
                        request.initialize(this.mConnection, endpoint);
                        request.queue(buffer, buffer.remaining());
                        bufferForEndpoints.put(endpoint, buffer);
                    }
                }
            }
        }
        if (!bufferForEndpoints.isEmpty()) {
            this.mHasInputThread = true;
            new Thread() {
                public void run() {
                    while (true) {
                        UsbRequest request = UsbMidiDeviceAndroid.this.mConnection.requestWait();
                        if (request != null) {
                            UsbEndpoint endpoint = request.getEndpoint();
                            if (endpoint.getDirection() == 128) {
                                ByteBuffer buffer = (ByteBuffer) bufferForEndpoints.get(endpoint);
                                int length = UsbMidiDeviceAndroid.getInputDataLength(buffer);
                                if (length > 0) {
                                    buffer.rewind();
                                    byte[] bs = new byte[length];
                                    buffer.get(bs, 0, length);
                                    UsbMidiDeviceAndroid.this.postOnDataEvent(endpoint.getEndpointNumber(), bs);
                                }
                                buffer.rewind();
                                request.queue(buffer, buffer.capacity());
                            }
                        } else {
                            return;
                        }
                    }
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public void postOnDataEvent(final int endpointNumber, final byte[] bs) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (!UsbMidiDeviceAndroid.this.mIsClosed) {
                    UsbMidiDeviceAndroid.nativeOnData(UsbMidiDeviceAndroid.this.mNativePointer, endpointNumber, bs);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void registerSelf(long nativePointer) {
        this.mNativePointer = nativePointer;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(18)
    @CalledByNative
    public void send(int endpointNumber, byte[] bs) {
        UsbEndpoint endpoint;
        if (this.mIsClosed || (endpoint = this.mEndpointMap.get(endpointNumber)) == null) {
            return;
        }
        if (shouldUseBulkTransfer()) {
            this.mConnection.bulkTransfer(endpoint, bs, bs.length, 100);
            return;
        }
        UsbRequest request = this.mRequestMap.get(endpoint);
        if (request == null) {
            request = new UsbRequest();
            request.initialize(this.mConnection, endpoint);
            this.mRequestMap.put(endpoint, request);
        }
        request.queue(ByteBuffer.wrap(bs), bs.length);
    }

    private boolean shouldUseBulkTransfer() {
        return this.mHasInputThread;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public byte[] getDescriptors() {
        if (this.mConnection == null) {
            return new byte[0];
        }
        return this.mConnection.getRawDescriptors();
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void close() {
        this.mEndpointMap.clear();
        for (UsbRequest request : this.mRequestMap.values()) {
            request.close();
        }
        this.mRequestMap.clear();
        this.mConnection.close();
        this.mNativePointer = 0;
        this.mIsClosed = true;
    }

    /* access modifiers changed from: private */
    public static int getInputDataLength(ByteBuffer buffer) {
        int position = buffer.position();
        for (int i = 0; i < position; i += 4) {
            if (buffer.get(i) == 0) {
                return i;
            }
        }
        return position;
    }
}
