package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.a;
import java.util.HashMap;

public class jg {
    private final jk<jf> VG;
    private ContentProviderClient VH = null;
    private boolean VI = false;
    private HashMap<LocationListener, b> VJ = new HashMap<>();
    private final Context mContext;

    private static class a extends Handler {
        private final LocationListener VK;

        public a(LocationListener locationListener) {
            this.VK = locationListener;
        }

        public a(LocationListener locationListener, Looper looper) {
            super(looper);
            this.VK = locationListener;
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    this.VK.onLocationChanged(new Location((Location) msg.obj));
                    return;
                default:
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
                    return;
            }
        }
    }

    private static class b extends a.C0083a {
        private Handler VL;

        b(LocationListener locationListener, Looper looper) {
            this.VL = looper == null ? new a(locationListener) : new a(locationListener, looper);
        }

        public void onLocationChanged(Location location) {
            if (this.VL == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = location;
            this.VL.sendMessage(obtain);
        }

        public void release() {
            this.VL = null;
        }
    }

    public jg(Context context, jk<jf> jkVar) {
        this.mContext = context;
        this.VG = jkVar;
    }

    public Location getLastLocation() {
        this.VG.ci();
        try {
            return this.VG.fo().bo(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void iT() {
        if (this.VI) {
            try {
                setMockMode(false);
            } catch (RemoteException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public void removeAllListeners() {
        try {
            synchronized (this.VJ) {
                for (b next : this.VJ.values()) {
                    if (next != null) {
                        this.VG.fo().a((com.google.android.gms.location.a) next);
                    }
                }
                this.VJ.clear();
            }
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void removeLocationUpdates(PendingIntent callbackIntent) throws RemoteException {
        this.VG.ci();
        this.VG.fo().a(callbackIntent);
    }

    public void removeLocationUpdates(LocationListener listener) throws RemoteException {
        this.VG.ci();
        hn.b(listener, (Object) "Invalid null listener");
        synchronized (this.VJ) {
            b remove = this.VJ.remove(listener);
            if (this.VH != null && this.VJ.isEmpty()) {
                this.VH.release();
                this.VH = null;
            }
            if (remove != null) {
                remove.release();
                this.VG.fo().a((com.google.android.gms.location.a) remove);
            }
        }
    }

    public void requestLocationUpdates(LocationRequest request, PendingIntent callbackIntent) throws RemoteException {
        this.VG.ci();
        this.VG.fo().a(request, callbackIntent);
    }

    public void requestLocationUpdates(LocationRequest request, LocationListener listener, Looper looper) throws RemoteException {
        this.VG.ci();
        if (looper == null) {
            hn.b(Looper.myLooper(), (Object) "Can't create handler inside thread that has not called Looper.prepare()");
        }
        synchronized (this.VJ) {
            b bVar = this.VJ.get(listener);
            b bVar2 = bVar == null ? new b(listener, looper) : bVar;
            this.VJ.put(listener, bVar2);
            this.VG.fo().a(request, (com.google.android.gms.location.a) bVar2, this.mContext.getPackageName());
        }
    }

    public void setMockLocation(Location mockLocation) throws RemoteException {
        this.VG.ci();
        this.VG.fo().setMockLocation(mockLocation);
    }

    public void setMockMode(boolean isMockMode) throws RemoteException {
        this.VG.ci();
        this.VG.fo().setMockMode(isMockMode);
        this.VI = isMockMode;
    }
}
