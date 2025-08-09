package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.internal.hn;

public class h {
    private String HV;
    private DriveId HX;
    protected MetadataChangeSet Ix;
    private Integer Iy;
    private final int Iz;

    public h(int i) {
        this.Iz = i;
    }

    public void a(DriveId driveId) {
        this.HX = (DriveId) hn.f(driveId);
    }

    public void a(MetadataChangeSet metadataChangeSet) {
        this.Ix = (MetadataChangeSet) hn.f(metadataChangeSet);
    }

    public void aM(String str) {
        this.HV = (String) hn.f(str);
    }

    public void aS(int i) {
        this.Iy = Integer.valueOf(i);
    }

    public IntentSender build(GoogleApiClient apiClient) {
        hn.b(this.Ix, (Object) "Must provide initial metadata to CreateFileActivityBuilder.");
        hn.a(apiClient.isConnected(), "Client must be connected");
        try {
            return ((r) apiClient.a(Drive.yE)).gk().a(new CreateFileIntentSenderRequest(this.Ix.gh(), this.Iy == null ? -1 : this.Iy.intValue(), this.HV, this.HX, this.Iz));
        } catch (RemoteException e) {
            throw new RuntimeException("Unable to connect Drive Play Service", e);
        }
    }
}
