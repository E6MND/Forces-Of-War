package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.internal.ac;

public class av extends ac.a {
    private final DataApi.DataListener ami;
    private final MessageApi.MessageListener amj;
    private final NodeApi.NodeListener amk;
    private final IntentFilter[] aml;

    public av(DataApi.DataListener dataListener, MessageApi.MessageListener messageListener, NodeApi.NodeListener nodeListener, IntentFilter[] intentFilterArr) {
        this.ami = dataListener;
        this.amj = messageListener;
        this.amk = nodeListener;
        this.aml = intentFilterArr;
    }

    public static av a(DataApi.DataListener dataListener, IntentFilter[] intentFilterArr) {
        return new av(dataListener, (MessageApi.MessageListener) null, (NodeApi.NodeListener) null, intentFilterArr);
    }

    public static av a(MessageApi.MessageListener messageListener, IntentFilter[] intentFilterArr) {
        return new av((DataApi.DataListener) null, messageListener, (NodeApi.NodeListener) null, intentFilterArr);
    }

    public static av a(NodeApi.NodeListener nodeListener) {
        return new av((DataApi.DataListener) null, (MessageApi.MessageListener) null, nodeListener, (IntentFilter[]) null);
    }

    public void Y(DataHolder dataHolder) {
        if (this.ami != null) {
            try {
                this.ami.onDataChanged(new DataEventBuffer(dataHolder));
            } finally {
                dataHolder.close();
            }
        } else {
            dataHolder.close();
        }
    }

    public void a(af afVar) {
        if (this.amj != null) {
            this.amj.onMessageReceived(afVar);
        }
    }

    public void a(ai aiVar) {
        if (this.amk != null) {
            this.amk.onPeerConnected(aiVar);
        }
    }

    public void b(ai aiVar) {
        if (this.amk != null) {
            this.amk.onPeerDisconnected(aiVar);
        }
    }

    public IntentFilter[] np() {
        return this.aml;
    }
}
