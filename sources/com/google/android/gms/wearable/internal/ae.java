package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.wearable.MessageApi;

public final class ae implements MessageApi {

    public static class a implements MessageApi.SendMessageResult {
        private final int qX;
        private final Status yw;

        public a(Status status, int i) {
            this.yw = status;
            this.qX = i;
        }

        public int getRequestId() {
            return this.qX;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private PendingResult<Status> a(GoogleApiClient googleApiClient, final MessageApi.MessageListener messageListener, final IntentFilter[] intentFilterArr) {
        return googleApiClient.a(new d<Status>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a((a.d<Status>) this, messageListener, intentFilterArr);
            }

            /* renamed from: d */
            public Status c(Status status) {
                return new Status(13);
            }
        });
    }

    public PendingResult<Status> addListener(GoogleApiClient client, MessageApi.MessageListener listener) {
        return a(client, listener, (IntentFilter[]) null);
    }

    public PendingResult<Status> removeListener(GoogleApiClient client, final MessageApi.MessageListener listener) {
        return client.a(new d<Status>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a((a.d<Status>) this, listener);
            }

            /* renamed from: d */
            public Status c(Status status) {
                return new Status(13);
            }
        });
    }

    public PendingResult<MessageApi.SendMessageResult> sendMessage(GoogleApiClient client, final String nodeId, final String action, final byte[] data) {
        return client.a(new d<MessageApi.SendMessageResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a(this, nodeId, action, data);
            }

            /* access modifiers changed from: protected */
            /* renamed from: au */
            public MessageApi.SendMessageResult c(Status status) {
                return new a(status, -1);
            }
        });
    }
}
