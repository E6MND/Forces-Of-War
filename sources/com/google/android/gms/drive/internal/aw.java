package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;

public class aw extends c {
    private final a.d<Status> yO;

    public aw(a.d<Status> dVar) {
        this.yO = dVar;
    }

    public void o(Status status) throws RemoteException {
        this.yO.a(status);
    }

    public void onSuccess() throws RemoteException {
        this.yO.a(Status.Ek);
    }
}
