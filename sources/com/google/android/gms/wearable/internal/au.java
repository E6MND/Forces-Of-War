package com.google.android.gms.wearable.internal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hj;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.internal.ad;
import com.google.android.gms.wearable.internal.ae;
import com.google.android.gms.wearable.internal.ah;
import com.google.android.gms.wearable.internal.f;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class au extends hc<ad> {
    private final ExecutorService agR = Executors.newCachedThreadPool();
    /* access modifiers changed from: private */
    public final HashMap<DataApi.DataListener, av> amb = new HashMap<>();
    /* access modifiers changed from: private */
    public final HashMap<MessageApi.MessageListener, av> amc = new HashMap<>();
    /* access modifiers changed from: private */
    public final HashMap<NodeApi.NodeListener, av> amd = new HashMap<>();

    private static class a extends a {
        private final List<FutureTask<Boolean>> amh;
        private final a.d<DataApi.DataItemResult> yO;

        a(a.d<DataApi.DataItemResult> dVar, List<FutureTask<Boolean>> list) {
            this.yO = dVar;
            this.amh = list;
        }

        public void a(am amVar) {
            this.yO.a(new f.a(new Status(amVar.statusCode), amVar.alL));
            if (amVar.statusCode != 0) {
                for (FutureTask<Boolean> cancel : this.amh) {
                    cancel.cancel(true);
                }
            }
        }
    }

    public au(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
    }

    private FutureTask<Boolean> a(final ParcelFileDescriptor parcelFileDescriptor, final byte[] bArr) {
        return new FutureTask<>(new Callable<Boolean>() {
            /* renamed from: no */
            public Boolean call() {
                if (Log.isLoggable("WearableClient", 3)) {
                    Log.d("WearableClient", "processAssets: writing data to FD : " + parcelFileDescriptor);
                }
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
                try {
                    autoCloseOutputStream.write(bArr);
                    autoCloseOutputStream.flush();
                    if (Log.isLoggable("WearableClient", 3)) {
                        Log.d("WearableClient", "processAssets: wrote data: " + parcelFileDescriptor);
                    }
                    try {
                        if (Log.isLoggable("WearableClient", 3)) {
                            Log.d("WearableClient", "processAssets: closing: " + parcelFileDescriptor);
                        }
                        autoCloseOutputStream.close();
                        return true;
                    } catch (IOException e) {
                        return true;
                    }
                } catch (IOException e2) {
                    Log.w("WearableClient", "processAssets: writing data failed: " + parcelFileDescriptor);
                    try {
                        if (Log.isLoggable("WearableClient", 3)) {
                            Log.d("WearableClient", "processAssets: closing: " + parcelFileDescriptor);
                        }
                        autoCloseOutputStream.close();
                    } catch (IOException e3) {
                    }
                    return false;
                } catch (Throwable th) {
                    try {
                        if (Log.isLoggable("WearableClient", 3)) {
                            Log.d("WearableClient", "processAssets: closing: " + parcelFileDescriptor);
                        }
                        autoCloseOutputStream.close();
                    } catch (IOException e4) {
                    }
                    throw th;
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(int i, IBinder iBinder, Bundle bundle) {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.d("WearableClient", "onPostInitHandler: statusCode " + i);
        }
        if (i == 0) {
            try {
                AnonymousClass1 r1 = new a() {
                    public void a(Status status) {
                    }
                };
                if (Log.isLoggable("WearableClient", 2)) {
                    Log.d("WearableClient", "onPostInitHandler: service " + iBinder);
                }
                ad by = ad.a.by(iBinder);
                for (Map.Entry next : this.amb.entrySet()) {
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: adding Data listener " + next.getValue());
                    }
                    by.a((ab) r1, new b((av) next.getValue()));
                }
                for (Map.Entry next2 : this.amc.entrySet()) {
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: adding Message listener " + next2.getValue());
                    }
                    by.a((ab) r1, new b((av) next2.getValue()));
                }
                for (Map.Entry next3 : this.amd.entrySet()) {
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: adding Node listener " + next3.getValue());
                    }
                    by.a((ab) r1, new b((av) next3.getValue()));
                }
            } catch (RemoteException e) {
                Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: error while adding listener", e);
            }
        }
        Log.d("WearableClient", "WearableClientImpl.onPostInitHandler: done");
        super.a(i, iBinder, bundle);
    }

    public void a(final a.d<DataApi.DataItemResult> dVar, Uri uri) throws RemoteException {
        ((ad) fo()).a((ab) new a() {
            public void a(v vVar) {
                dVar.a(new f.a(new Status(vVar.statusCode), vVar.alL));
            }
        }, uri);
    }

    public void a(final a.d<DataApi.GetFdForAssetResult> dVar, Asset asset) throws RemoteException {
        ((ad) fo()).a((ab) new a() {
            public void a(x xVar) {
                dVar.a(new f.c(new Status(xVar.statusCode), xVar.alM));
            }
        }, asset);
    }

    public void a(a.d<Status> dVar, DataApi.DataListener dataListener) throws RemoteException {
        ac remove;
        synchronized (this.amb) {
            remove = this.amb.remove(dataListener);
        }
        if (remove == null) {
            dVar.a(new Status(4002));
        } else {
            a(dVar, remove);
        }
    }

    public void a(final a.d<Status> dVar, final DataApi.DataListener dataListener, IntentFilter[] intentFilterArr) throws RemoteException {
        av a2 = av.a(dataListener, intentFilterArr);
        synchronized (this.amb) {
            if (this.amb.get(dataListener) != null) {
                dVar.a(new Status(4001));
                return;
            }
            this.amb.put(dataListener, a2);
            ((ad) fo()).a((ab) new a() {
                public void a(Status status) {
                    if (!status.isSuccess()) {
                        synchronized (au.this.amb) {
                            au.this.amb.remove(dataListener);
                        }
                    }
                    dVar.a(status);
                }
            }, new b(a2));
        }
    }

    public void a(a.d<DataApi.GetFdForAssetResult> dVar, DataItemAsset dataItemAsset) throws RemoteException {
        a(dVar, Asset.createFromRef(dataItemAsset.getId()));
    }

    public void a(a.d<Status> dVar, MessageApi.MessageListener messageListener) throws RemoteException {
        synchronized (this.amc) {
            ac remove = this.amc.remove(messageListener);
            if (remove == null) {
                dVar.a(new Status(4002));
            } else {
                a(dVar, remove);
            }
        }
    }

    public void a(final a.d<Status> dVar, final MessageApi.MessageListener messageListener, IntentFilter[] intentFilterArr) throws RemoteException {
        av a2 = av.a(messageListener, intentFilterArr);
        synchronized (this.amc) {
            if (this.amc.get(messageListener) != null) {
                dVar.a(new Status(4001));
                return;
            }
            this.amc.put(messageListener, a2);
            ((ad) fo()).a((ab) new a() {
                public void a(Status status) {
                    if (!status.isSuccess()) {
                        synchronized (au.this.amc) {
                            au.this.amc.remove(messageListener);
                        }
                    }
                    dVar.a(status);
                }
            }, new b(a2));
        }
    }

    public void a(final a.d<Status> dVar, final NodeApi.NodeListener nodeListener) throws RemoteException, RemoteException {
        av a2 = av.a(nodeListener);
        synchronized (this.amd) {
            if (this.amd.get(nodeListener) != null) {
                dVar.a(new Status(4001));
                return;
            }
            this.amd.put(nodeListener, a2);
            ((ad) fo()).a((ab) new a() {
                public void a(Status status) {
                    if (!status.isSuccess()) {
                        synchronized (au.this.amd) {
                            au.this.amd.remove(nodeListener);
                        }
                    }
                    dVar.a(status);
                }
            }, new b(a2));
        }
    }

    public void a(a.d<DataApi.DataItemResult> dVar, PutDataRequest putDataRequest) throws RemoteException {
        for (Map.Entry<String, Asset> value : putDataRequest.getAssets().entrySet()) {
            Asset asset = (Asset) value.getValue();
            if (asset.getData() == null && asset.getDigest() == null && asset.getFd() == null && asset.getUri() == null) {
                throw new IllegalArgumentException("Put for " + putDataRequest.getUri() + " contains invalid asset: " + asset);
            }
        }
        PutDataRequest j = PutDataRequest.j(putDataRequest.getUri());
        j.setData(putDataRequest.getData());
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : putDataRequest.getAssets().entrySet()) {
            Asset asset2 = (Asset) next.getValue();
            if (asset2.getData() == null) {
                j.putAsset((String) next.getKey(), (Asset) next.getValue());
            } else {
                try {
                    ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
                    if (Log.isLoggable("WearableClient", 3)) {
                        Log.d("WearableClient", "processAssets: replacing data with FD in asset: " + asset2 + " read:" + createPipe[0] + " write:" + createPipe[1]);
                    }
                    j.putAsset((String) next.getKey(), Asset.createFromFd(createPipe[0]));
                    FutureTask<Boolean> a2 = a(createPipe[1], asset2.getData());
                    arrayList.add(a2);
                    this.agR.submit(a2);
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to create ParcelFileDescriptor for asset in request: " + putDataRequest, e);
                }
            }
        }
        try {
            ((ad) fo()).a((ab) new a(dVar, arrayList), j);
        } catch (NullPointerException e2) {
            throw new IllegalStateException("Unable to putDataItem: " + putDataRequest, e2);
        }
    }

    public void a(final a.d<Status> dVar, ac acVar) throws RemoteException {
        ((ad) fo()).a((ab) new a() {
            public void a(Status status) {
                dVar.a(status);
            }
        }, new ao(acVar));
    }

    public void a(final a.d<MessageApi.SendMessageResult> dVar, String str, String str2, byte[] bArr) throws RemoteException {
        ((ad) fo()).a(new a() {
            public void a(aq aqVar) {
                dVar.a(new ae.a(new Status(aqVar.statusCode), aqVar.alZ));
            }
        }, str, str2, bArr);
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        hjVar.e(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName());
    }

    public void b(final a.d<DataItemBuffer> dVar, Uri uri) throws RemoteException {
        ((ad) fo()).b(new a() {
            public void Z(DataHolder dataHolder) {
                dVar.a(new DataItemBuffer(dataHolder));
            }
        }, uri);
    }

    public void b(a.d<Status> dVar, NodeApi.NodeListener nodeListener) throws RemoteException {
        synchronized (this.amd) {
            ac remove = this.amd.remove(nodeListener);
            if (remove == null) {
                dVar.a(new Status(4002));
            } else {
                a(dVar, remove);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.wearable.BIND";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.wearable.internal.IWearableService";
    }

    /* access modifiers changed from: protected */
    /* renamed from: bz */
    public ad x(IBinder iBinder) {
        return ad.a.by(iBinder);
    }

    public void c(final a.d<DataApi.DeleteDataItemsResult> dVar, Uri uri) throws RemoteException {
        ((ad) fo()).c(new a() {
            public void a(p pVar) {
                dVar.a(new f.b(new Status(pVar.statusCode), pVar.alI));
            }
        }, uri);
    }

    public void disconnect() {
        super.disconnect();
        this.amb.clear();
        this.amc.clear();
        this.amd.clear();
    }

    public void o(final a.d<DataItemBuffer> dVar) throws RemoteException {
        ((ad) fo()).d(new a() {
            public void Z(DataHolder dataHolder) {
                dVar.a(new DataItemBuffer(dataHolder));
            }
        });
    }

    public void p(final a.d<NodeApi.GetLocalNodeResult> dVar) throws RemoteException {
        ((ad) fo()).e(new a() {
            public void a(z zVar) {
                dVar.a(new ah.b(new Status(zVar.statusCode), zVar.alN));
            }
        });
    }

    public void q(final a.d<NodeApi.GetConnectedNodesResult> dVar) throws RemoteException {
        ((ad) fo()).f(new a() {
            public void a(t tVar) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(tVar.alK);
                dVar.a(new ah.a(new Status(tVar.statusCode), arrayList));
            }
        });
    }
}
