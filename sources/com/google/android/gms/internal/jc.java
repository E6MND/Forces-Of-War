package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class jc implements FusedLocationProviderApi {

    private static abstract class a extends LocationServices.a<Status> {
        private a() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    public Location getLastLocation(GoogleApiClient client) {
        try {
            return LocationServices.e(client).getLastLocation();
        } catch (Exception e) {
            return null;
        }
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient client, final PendingIntent callbackIntent) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.removeLocationUpdates(callbackIntent);
                a(Status.Ek);
            }
        });
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient client, final LocationListener listener) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.removeLocationUpdates(listener);
                a(Status.Ek);
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, final LocationRequest request, final PendingIntent callbackIntent) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.requestLocationUpdates(request, callbackIntent);
                a(Status.Ek);
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, final LocationRequest request, final LocationListener listener) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.requestLocationUpdates(request, listener);
                a(Status.Ek);
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, final LocationRequest request, final LocationListener listener, final Looper looper) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.requestLocationUpdates(request, listener, looper);
                a(Status.Ek);
            }
        });
    }

    public PendingResult<Status> setMockLocation(GoogleApiClient client, final Location mockLocation) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.setMockLocation(mockLocation);
                a(Status.Ek);
            }
        });
    }

    public PendingResult<Status> setMockMode(GoogleApiClient client, final boolean isMockMode) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.setMockMode(isMockMode);
                a(Status.Ek);
            }
        });
    }
}
