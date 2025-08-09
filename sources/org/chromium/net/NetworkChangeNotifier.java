package org.chromium.net;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.NativeClassQualifiedName;
import org.chromium.base.ObserverList;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

@JNINamespace("net")
public class NetworkChangeNotifier {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkChangeNotifier.class.desiredAssertionStatus());
    public static final int CONNECTION_2G = 3;
    public static final int CONNECTION_3G = 4;
    public static final int CONNECTION_4G = 5;
    public static final int CONNECTION_BLUETOOTH = 7;
    public static final int CONNECTION_ETHERNET = 1;
    public static final int CONNECTION_NONE = 6;
    public static final int CONNECTION_UNKNOWN = 0;
    public static final int CONNECTION_WIFI = 2;
    private static NetworkChangeNotifier sInstance;
    private NetworkChangeNotifierAutoDetect mAutoDetector;
    private final ObserverList<ConnectionTypeObserver> mConnectionTypeObservers;
    private final Context mContext;
    private int mCurrentConnectionType = 0;
    private final ArrayList<Long> mNativeChangeNotifiers;

    public interface ConnectionTypeObserver {
        void onConnectionTypeChanged(int i);
    }

    @NativeClassQualifiedName("NetworkChangeNotifierDelegateAndroid")
    private native void nativeNotifyConnectionTypeChanged(long j, int i);

    private NetworkChangeNotifier(Context context) {
        this.mContext = context.getApplicationContext();
        this.mNativeChangeNotifiers = new ArrayList<>();
        this.mConnectionTypeObservers = new ObserverList<>();
    }

    @CalledByNative
    public static NetworkChangeNotifier init(Context context) {
        if (sInstance == null) {
            sInstance = new NetworkChangeNotifier(context);
        }
        return sInstance;
    }

    public static boolean isInitialized() {
        return sInstance != null;
    }

    static void resetInstanceForTests(Context context) {
        sInstance = new NetworkChangeNotifier(context);
    }

    @CalledByNative
    public int getCurrentConnectionType() {
        return this.mCurrentConnectionType;
    }

    @CalledByNative
    public void addNativeObserver(long nativeChangeNotifier) {
        this.mNativeChangeNotifiers.add(Long.valueOf(nativeChangeNotifier));
    }

    @CalledByNative
    public void removeNativeObserver(long nativeChangeNotifier) {
        this.mNativeChangeNotifiers.remove(Long.valueOf(nativeChangeNotifier));
    }

    public static NetworkChangeNotifier getInstance() {
        if ($assertionsDisabled || sInstance != null) {
            return sInstance;
        }
        throw new AssertionError();
    }

    public static void setAutoDetectConnectivityState(boolean shouldAutoDetect) {
        getInstance().setAutoDetectConnectivityStateInternal(shouldAutoDetect, false);
    }

    private void destroyAutoDetector() {
        if (this.mAutoDetector != null) {
            this.mAutoDetector.destroy();
            this.mAutoDetector = null;
        }
    }

    public static void registerToReceiveNotificationsAlways() {
        getInstance().setAutoDetectConnectivityStateInternal(true, true);
    }

    private void setAutoDetectConnectivityStateInternal(boolean shouldAutoDetect, boolean alwaysWatchForChanges) {
        if (!shouldAutoDetect) {
            destroyAutoDetector();
        } else if (this.mAutoDetector == null) {
            this.mAutoDetector = new NetworkChangeNotifierAutoDetect(new NetworkChangeNotifierAutoDetect.Observer() {
                public void onConnectionTypeChanged(int newConnectionType) {
                    NetworkChangeNotifier.this.updateCurrentConnectionType(newConnectionType);
                }
            }, this.mContext, alwaysWatchForChanges);
            updateCurrentConnectionType(this.mAutoDetector.getCurrentConnectionType());
        }
    }

    @CalledByNative
    public static void forceConnectivityState(boolean networkAvailable) {
        setAutoDetectConnectivityState(false);
        getInstance().forceConnectivityStateInternal(networkAvailable);
    }

    private void forceConnectivityStateInternal(boolean forceOnline) {
        boolean connectionCurrentlyExists;
        int i = 0;
        if (this.mCurrentConnectionType != 6) {
            connectionCurrentlyExists = true;
        } else {
            connectionCurrentlyExists = false;
        }
        if (connectionCurrentlyExists != forceOnline) {
            if (!forceOnline) {
                i = 6;
            }
            updateCurrentConnectionType(i);
        }
    }

    /* access modifiers changed from: private */
    public void updateCurrentConnectionType(int newConnectionType) {
        this.mCurrentConnectionType = newConnectionType;
        notifyObserversOfConnectionTypeChange(newConnectionType);
    }

    /* access modifiers changed from: package-private */
    public void notifyObserversOfConnectionTypeChange(int newConnectionType) {
        Iterator i$ = this.mNativeChangeNotifiers.iterator();
        while (i$.hasNext()) {
            nativeNotifyConnectionTypeChanged(i$.next().longValue(), newConnectionType);
        }
        Iterator i$2 = this.mConnectionTypeObservers.iterator();
        while (i$2.hasNext()) {
            i$2.next().onConnectionTypeChanged(newConnectionType);
        }
    }

    public static void addConnectionTypeObserver(ConnectionTypeObserver observer) {
        getInstance().addConnectionTypeObserverInternal(observer);
    }

    private void addConnectionTypeObserverInternal(ConnectionTypeObserver observer) {
        this.mConnectionTypeObservers.addObserver(observer);
    }

    public static void removeConnectionTypeObserver(ConnectionTypeObserver observer) {
        getInstance().removeConnectionTypeObserverInternal(observer);
    }

    private void removeConnectionTypeObserverInternal(ConnectionTypeObserver observer) {
        this.mConnectionTypeObservers.removeObserver(observer);
    }

    public static NetworkChangeNotifierAutoDetect getAutoDetectorForTest() {
        return getInstance().mAutoDetector;
    }

    public static boolean isOnline() {
        int connectionType = getInstance().getCurrentConnectionType();
        return (connectionType == 0 || connectionType == 6) ? false : true;
    }
}
