package org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;
import org.chromium.base.ApplicationStatus;

public class NetworkChangeNotifierAutoDetect extends BroadcastReceiver implements ApplicationStatus.ApplicationStateListener {
    private static final String TAG = "NetworkChangeNotifierAutoDetect";
    private int mConnectionType;
    private ConnectivityManagerDelegate mConnectivityManagerDelegate;
    private final Context mContext;
    private final NetworkConnectivityIntentFilter mIntentFilter = new NetworkConnectivityIntentFilter();
    private final Observer mObserver;
    private boolean mRegistered;
    private WifiManagerDelegate mWifiManagerDelegate;
    private String mWifiSSID;

    public interface Observer {
        void onConnectionTypeChanged(int i);
    }

    static class ConnectivityManagerDelegate {
        private final ConnectivityManager mConnectivityManager;

        ConnectivityManagerDelegate(Context context) {
            this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        ConnectivityManagerDelegate() {
            this.mConnectivityManager = null;
        }

        /* access modifiers changed from: package-private */
        public boolean activeNetworkExists() {
            return this.mConnectivityManager.getActiveNetworkInfo() != null;
        }

        /* access modifiers changed from: package-private */
        public boolean isConnected() {
            return this.mConnectivityManager.getActiveNetworkInfo().isConnected();
        }

        /* access modifiers changed from: package-private */
        public int getNetworkType() {
            return this.mConnectivityManager.getActiveNetworkInfo().getType();
        }

        /* access modifiers changed from: package-private */
        public int getNetworkSubtype() {
            return this.mConnectivityManager.getActiveNetworkInfo().getSubtype();
        }
    }

    static class WifiManagerDelegate {
        private final WifiManager mWifiManager;

        WifiManagerDelegate(Context context) {
            this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        }

        WifiManagerDelegate() {
            this.mWifiManager = null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
            r0 = r1.getSSID();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String getWifiSSID() {
            /*
                r3 = this;
                android.net.wifi.WifiManager r2 = r3.mWifiManager
                android.net.wifi.WifiInfo r1 = r2.getConnectionInfo()
                if (r1 != 0) goto L_0x000b
                java.lang.String r0 = ""
            L_0x000a:
                return r0
            L_0x000b:
                java.lang.String r0 = r1.getSSID()
                if (r0 != 0) goto L_0x000a
                java.lang.String r0 = ""
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.NetworkChangeNotifierAutoDetect.WifiManagerDelegate.getWifiSSID():java.lang.String");
        }
    }

    public NetworkChangeNotifierAutoDetect(Observer observer, Context context, boolean alwaysWatchForChanges) {
        this.mObserver = observer;
        this.mContext = context.getApplicationContext();
        this.mConnectivityManagerDelegate = new ConnectivityManagerDelegate(context);
        this.mWifiManagerDelegate = new WifiManagerDelegate(context);
        this.mConnectionType = getCurrentConnectionType();
        this.mWifiSSID = getCurrentWifiSSID();
        if (alwaysWatchForChanges) {
            registerReceiver();
        } else {
            ApplicationStatus.registerApplicationStateListener(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate delegate) {
        this.mConnectivityManagerDelegate = delegate;
    }

    /* access modifiers changed from: package-private */
    public void setWifiManagerDelegateForTests(WifiManagerDelegate delegate) {
        this.mWifiManagerDelegate = delegate;
    }

    public void destroy() {
        unregisterReceiver();
    }

    private void registerReceiver() {
        if (!this.mRegistered) {
            this.mRegistered = true;
            this.mContext.registerReceiver(this, this.mIntentFilter);
        }
    }

    private void unregisterReceiver() {
        if (this.mRegistered) {
            this.mRegistered = false;
            this.mContext.unregisterReceiver(this);
        }
    }

    public int getCurrentConnectionType() {
        if (!this.mConnectivityManagerDelegate.activeNetworkExists() || !this.mConnectivityManagerDelegate.isConnected()) {
            return 6;
        }
        switch (this.mConnectivityManagerDelegate.getNetworkType()) {
            case 0:
                switch (this.mConnectivityManagerDelegate.getNetworkSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 3;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 4;
                    case 13:
                        return 5;
                    default:
                        return 0;
                }
            case 1:
                return 2;
            case 6:
                return 5;
            case 7:
                return 7;
            case 9:
                return 1;
            default:
                return 0;
        }
    }

    private String getCurrentWifiSSID() {
        if (getCurrentConnectionType() != 2) {
            return "";
        }
        return this.mWifiManagerDelegate.getWifiSSID();
    }

    public void onReceive(Context context, Intent intent) {
        connectionTypeChanged();
    }

    public void onApplicationStateChange(int newState) {
        if (newState == 1) {
            connectionTypeChanged();
            registerReceiver();
        } else if (newState == 2) {
            unregisterReceiver();
        }
    }

    private void connectionTypeChanged() {
        int newConnectionType = getCurrentConnectionType();
        String newWifiSSID = getCurrentWifiSSID();
        if (newConnectionType != this.mConnectionType || !newWifiSSID.equals(this.mWifiSSID)) {
            this.mConnectionType = newConnectionType;
            this.mWifiSSID = newWifiSSID;
            Log.d(TAG, "Network connectivity changed, type is: " + this.mConnectionType);
            this.mObserver.onConnectionTypeChanged(newConnectionType);
        }
    }

    private static class NetworkConnectivityIntentFilter extends IntentFilter {
        NetworkConnectivityIntentFilter() {
            addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
    }
}
