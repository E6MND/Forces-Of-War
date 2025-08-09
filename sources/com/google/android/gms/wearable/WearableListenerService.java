package com.google.android.gms.wearable;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.internal.ac;
import com.google.android.gms.wearable.internal.af;
import com.google.android.gms.wearable.internal.ai;

public abstract class WearableListenerService extends Service implements DataApi.DataListener, MessageApi.MessageListener, NodeApi.NodeListener {
    public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
    private IBinder Gz;
    private volatile int aln = -1;
    /* access modifiers changed from: private */
    public Handler alo;
    /* access modifiers changed from: private */
    public Object alp = new Object();
    /* access modifiers changed from: private */
    public boolean alq;
    /* access modifiers changed from: private */
    public String xN;

    private class a extends ac.a {
        private a() {
        }

        public void Y(final DataHolder dataHolder) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onDataItemChanged: " + WearableListenerService.this.xN + ": " + dataHolder);
            }
            WearableListenerService.this.ni();
            synchronized (WearableListenerService.this.alp) {
                if (WearableListenerService.this.alq) {
                    dataHolder.close();
                } else {
                    WearableListenerService.this.alo.post(new Runnable() {
                        public void run() {
                            DataEventBuffer dataEventBuffer = new DataEventBuffer(dataHolder);
                            try {
                                WearableListenerService.this.onDataChanged(dataEventBuffer);
                            } finally {
                                dataEventBuffer.release();
                            }
                        }
                    });
                }
            }
        }

        public void a(final af afVar) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onMessageReceived: " + afVar);
            }
            WearableListenerService.this.ni();
            synchronized (WearableListenerService.this.alp) {
                if (!WearableListenerService.this.alq) {
                    WearableListenerService.this.alo.post(new Runnable() {
                        public void run() {
                            WearableListenerService.this.onMessageReceived(afVar);
                        }
                    });
                }
            }
        }

        public void a(final ai aiVar) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerConnected: " + WearableListenerService.this.xN + ": " + aiVar);
            }
            WearableListenerService.this.ni();
            synchronized (WearableListenerService.this.alp) {
                if (!WearableListenerService.this.alq) {
                    WearableListenerService.this.alo.post(new Runnable() {
                        public void run() {
                            WearableListenerService.this.onPeerConnected(aiVar);
                        }
                    });
                }
            }
        }

        public void b(final ai aiVar) {
            if (Log.isLoggable("WearableLS", 3)) {
                Log.d("WearableLS", "onPeerDisconnected: " + WearableListenerService.this.xN + ": " + aiVar);
            }
            WearableListenerService.this.ni();
            synchronized (WearableListenerService.this.alp) {
                if (!WearableListenerService.this.alq) {
                    WearableListenerService.this.alo.post(new Runnable() {
                        public void run() {
                            WearableListenerService.this.onPeerDisconnected(aiVar);
                        }
                    });
                }
            }
        }
    }

    private boolean ed(int i) {
        String[] packagesForUid = getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null) {
            return false;
        }
        for (String equals : packagesForUid) {
            if (GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void ni() throws SecurityException {
        int callingUid = Binder.getCallingUid();
        if (callingUid != this.aln) {
            if (!GooglePlayServicesUtil.b(getPackageManager(), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE) || !ed(callingUid)) {
                throw new SecurityException("Caller is not GooglePlayServices");
            }
            this.aln = callingUid;
        }
    }

    public final IBinder onBind(Intent intent) {
        if (BIND_LISTENER_INTENT_ACTION.equals(intent.getAction())) {
            return this.Gz;
        }
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", "onCreate: " + getPackageName());
        }
        this.xN = getPackageName();
        HandlerThread handlerThread = new HandlerThread("WearableListenerService");
        handlerThread.start();
        this.alo = new Handler(handlerThread.getLooper());
        this.Gz = new a();
    }

    public void onDataChanged(DataEventBuffer dataEvents) {
    }

    public void onDestroy() {
        synchronized (this.alp) {
            this.alq = true;
            this.alo.getLooper().quit();
        }
        super.onDestroy();
    }

    public void onMessageReceived(MessageEvent messageEvent) {
    }

    public void onPeerConnected(Node peer) {
    }

    public void onPeerDisconnected(Node peer) {
    }
}
