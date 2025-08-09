package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionApi;

public class jb implements ActivityRecognitionApi {

    private static abstract class a extends ActivityRecognition.a<Status> {
        private a() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    public PendingResult<Status> removeActivityUpdates(GoogleApiClient client, final PendingIntent callbackIntent) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.removeActivityUpdates(callbackIntent);
                a(Status.Ek);
            }
        });
    }

    public PendingResult<Status> requestActivityUpdates(GoogleApiClient client, final long detectionIntervalMillis, final PendingIntent callbackIntent) {
        return client.b(new a() {
            /* access modifiers changed from: protected */
            public void a(jh jhVar) throws RemoteException {
                jhVar.requestActivityUpdates(detectionIntervalMillis, callbackIntent);
                a(Status.Ek);
            }
        });
    }
}
