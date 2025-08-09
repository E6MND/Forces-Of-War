package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.internal.OpenFileIntentSenderRequest;
import com.google.android.gms.drive.internal.r;
import com.google.android.gms.internal.hn;

public class OpenFileActivityBuilder {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private String HV;
    private String[] HW;
    private DriveId HX;

    public IntentSender build(GoogleApiClient apiClient) {
        hn.a(apiClient.isConnected(), "Client must be connected");
        if (this.HW == null) {
            this.HW = new String[0];
        }
        try {
            return ((r) apiClient.a(Drive.yE)).gk().a(new OpenFileIntentSenderRequest(this.HV, this.HW, this.HX));
        } catch (RemoteException e) {
            throw new RuntimeException("Unable to connect Drive Play Service", e);
        }
    }

    public OpenFileActivityBuilder setActivityStartFolder(DriveId folder) {
        this.HX = (DriveId) hn.f(folder);
        return this;
    }

    public OpenFileActivityBuilder setActivityTitle(String title) {
        this.HV = (String) hn.f(title);
        return this;
    }

    public OpenFileActivityBuilder setMimeType(String[] mimeTypes) {
        hn.b(mimeTypes != null, (Object) "mimeTypes may not be null");
        this.HW = mimeTypes;
        return this;
    }
}
