package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import java.util.List;

public final class ah implements NodeApi {

    public static class a implements NodeApi.GetConnectedNodesResult {
        private final List<Node> alW;
        private final Status yw;

        public a(Status status, List<Node> list) {
            this.yw = status;
            this.alW = list;
        }

        public List<Node> getNodes() {
            return this.alW;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    public static class b implements NodeApi.GetLocalNodeResult {
        private final Node alX;
        private final Status yw;

        public b(Status status, Node node) {
            this.yw = status;
            this.alX = node;
        }

        public Node getNode() {
            return this.alX;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    public PendingResult<Status> addListener(GoogleApiClient client, final NodeApi.NodeListener listener) {
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

    public PendingResult<NodeApi.GetConnectedNodesResult> getConnectedNodes(GoogleApiClient client) {
        return client.a(new d<NodeApi.GetConnectedNodesResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.q(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: aw */
            public NodeApi.GetConnectedNodesResult c(Status status) {
                return new a(status, (List<Node>) null);
            }
        });
    }

    public PendingResult<NodeApi.GetLocalNodeResult> getLocalNode(GoogleApiClient client) {
        return client.a(new d<NodeApi.GetLocalNodeResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.p(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: av */
            public NodeApi.GetLocalNodeResult c(Status status) {
                return new b(status, (Node) null);
            }
        });
    }

    public PendingResult<Status> removeListener(GoogleApiClient client, final NodeApi.NodeListener listener) {
        return client.a(new d<Status>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.b((a.d<Status>) this, listener);
            }

            /* renamed from: d */
            public Status c(Status status) {
                return new Status(13);
            }
        });
    }
}
