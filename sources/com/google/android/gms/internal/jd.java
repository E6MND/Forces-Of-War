package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.ArrayList;
import java.util.List;

public class jd implements GeofencingApi {

    private static abstract class a extends LocationServices.a<Status> {
        private a() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    public PendingResult<Status> addGeofences(GoogleApiClient client, List<Geofence> geofences, final PendingIntent pendingIntent) {
        final ArrayList arrayList;
        if (geofences != null) {
            ArrayList arrayList2 = new ArrayList(geofences.size());
            for (Geofence next : geofences) {
                hn.b(next instanceof ji, (Object) "Geofence must be created using Geofence.Builder.");
                arrayList2.add((ji) next);
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.addGeofences(arrayList, pendingIntent, new LocationClient.OnAddGeofencesResultListener() {
                    public void onAddGeofencesResult(int statusCode, String[] geofenceRequestIds) {
                        AnonymousClass1.this.a(LocationStatusCodes.cK(statusCode));
                    }
                });
            }
        });
    }

    public PendingResult<Status> removeGeofences(GoogleApiClient client, final PendingIntent pendingIntent) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.removeGeofences(pendingIntent, (LocationClient.OnRemoveGeofencesResultListener) new LocationClient.OnRemoveGeofencesResultListener() {
                    public void onRemoveGeofencesByPendingIntentResult(int statusCode, PendingIntent pendingIntent) {
                        AnonymousClass2.this.a(LocationStatusCodes.cK(statusCode));
                    }

                    public void onRemoveGeofencesByRequestIdsResult(int statusCode, String[] geofenceRequestIds) {
                        Log.wtf("GeofencingImpl", "Request ID callback shouldn't have been called");
                    }
                });
            }
        });
    }

    public PendingResult<Status> removeGeofences(GoogleApiClient client, final List<String> geofenceRequestIds) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.removeGeofences((List<String>) geofenceRequestIds, (LocationClient.OnRemoveGeofencesResultListener) new LocationClient.OnRemoveGeofencesResultListener() {
                    public void onRemoveGeofencesByPendingIntentResult(int statusCode, PendingIntent pendingIntent) {
                        Log.wtf("GeofencingImpl", "PendingIntent callback shouldn't have been called");
                    }

                    public void onRemoveGeofencesByRequestIdsResult(int statusCode, String[] geofenceRequestIds) {
                        AnonymousClass3.this.a(LocationStatusCodes.cK(statusCode));
                    }
                });
            }
        });
    }
}
