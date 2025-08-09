package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.IOException;
import java.io.InputStream;

public final class f implements DataApi {

    public static class a implements DataApi.DataItemResult {
        private final DataItem alE;
        private final Status yw;

        public a(Status status, DataItem dataItem) {
            this.yw = status;
            this.alE = dataItem;
        }

        public DataItem getDataItem() {
            return this.alE;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    public static class b implements DataApi.DeleteDataItemsResult {
        private final int alF;
        private final Status yw;

        public b(Status status, int i) {
            this.yw = status;
            this.alF = i;
        }

        public int getNumDeleted() {
            return this.alF;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    public static class c implements DataApi.GetFdForAssetResult {
        private final ParcelFileDescriptor alG;
        private final Status yw;

        public c(Status status, ParcelFileDescriptor parcelFileDescriptor) {
            this.yw = status;
            this.alG = parcelFileDescriptor;
        }

        public ParcelFileDescriptor getFd() {
            return this.alG;
        }

        public InputStream getInputStream() {
            return new ParcelFileDescriptor.AutoCloseInputStream(this.alG);
        }

        public Status getStatus() {
            return this.yw;
        }

        public void release() {
            try {
                this.alG.close();
            } catch (IOException e) {
            }
        }
    }

    private PendingResult<Status> a(GoogleApiClient googleApiClient, final DataApi.DataListener dataListener, final IntentFilter[] intentFilterArr) {
        return googleApiClient.a(new d<Status>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a((a.d<Status>) this, dataListener, intentFilterArr);
            }

            /* renamed from: d */
            public Status c(Status status) {
                return new Status(13);
            }
        });
    }

    private void a(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("asset is null");
        } else if (asset.getDigest() == null) {
            throw new IllegalArgumentException("invalid asset");
        } else if (asset.getData() != null) {
            throw new IllegalArgumentException("invalid asset");
        }
    }

    public PendingResult<Status> addListener(GoogleApiClient client, DataApi.DataListener listener) {
        return a(client, listener, (IntentFilter[]) null);
    }

    public PendingResult<DataApi.DeleteDataItemsResult> deleteDataItems(GoogleApiClient client, final Uri uri) {
        return client.a(new d<DataApi.DeleteDataItemsResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.c(this, uri);
            }

            /* access modifiers changed from: protected */
            /* renamed from: as */
            public DataApi.DeleteDataItemsResult c(Status status) {
                return new b(status, 0);
            }
        });
    }

    public PendingResult<DataApi.DataItemResult> getDataItem(GoogleApiClient client, final Uri uri) {
        return client.a(new d<DataApi.DataItemResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a((a.d<DataApi.DataItemResult>) this, uri);
            }

            /* access modifiers changed from: protected */
            /* renamed from: aq */
            public DataApi.DataItemResult c(Status status) {
                return new a(status, (DataItem) null);
            }
        });
    }

    public PendingResult<DataItemBuffer> getDataItems(GoogleApiClient client) {
        return client.a(new d<DataItemBuffer>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.o(this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: ar */
            public DataItemBuffer c(Status status) {
                return new DataItemBuffer(DataHolder.af(status.getStatusCode()));
            }
        });
    }

    public PendingResult<DataItemBuffer> getDataItems(GoogleApiClient client, final Uri uri) {
        return client.a(new d<DataItemBuffer>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.b((a.d<DataItemBuffer>) this, uri);
            }

            /* access modifiers changed from: protected */
            /* renamed from: ar */
            public DataItemBuffer c(Status status) {
                return new DataItemBuffer(DataHolder.af(status.getStatusCode()));
            }
        });
    }

    public PendingResult<DataApi.GetFdForAssetResult> getFdForAsset(GoogleApiClient client, final Asset asset) {
        a(asset);
        return client.a(new d<DataApi.GetFdForAssetResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a((a.d<DataApi.GetFdForAssetResult>) this, asset);
            }

            /* access modifiers changed from: protected */
            /* renamed from: at */
            public DataApi.GetFdForAssetResult c(Status status) {
                return new c(status, (ParcelFileDescriptor) null);
            }
        });
    }

    public PendingResult<DataApi.GetFdForAssetResult> getFdForAsset(GoogleApiClient client, final DataItemAsset asset) {
        return client.a(new d<DataApi.GetFdForAssetResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a((a.d<DataApi.GetFdForAssetResult>) this, asset);
            }

            /* access modifiers changed from: protected */
            /* renamed from: at */
            public DataApi.GetFdForAssetResult c(Status status) {
                return new c(status, (ParcelFileDescriptor) null);
            }
        });
    }

    public PendingResult<DataApi.DataItemResult> putDataItem(GoogleApiClient client, final PutDataRequest request) {
        return client.a(new d<DataApi.DataItemResult>() {
            /* access modifiers changed from: protected */
            public void a(au auVar) throws RemoteException {
                auVar.a((a.d<DataApi.DataItemResult>) this, request);
            }

            /* renamed from: aq */
            public DataApi.DataItemResult c(Status status) {
                return new a(status, (DataItem) null);
            }
        });
    }

    public PendingResult<Status> removeListener(GoogleApiClient client, final DataApi.DataListener listener) {
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
}
