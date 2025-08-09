package com.google.android.gms.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.internal.h;
import com.google.android.gms.internal.hn;
import java.io.IOException;

public class CreateFileActivityBuilder {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private final h HC = new h(0);
    private Contents HD;

    public IntentSender build(GoogleApiClient apiClient) {
        hn.b(this.HD, (Object) "Must provide initial contents to CreateFileActivityBuilder.");
        try {
            this.HD.getParcelFileDescriptor().close();
        } catch (IOException e) {
        }
        this.HD.close();
        return this.HC.build(apiClient);
    }

    public CreateFileActivityBuilder setActivityStartFolder(DriveId folder) {
        this.HC.a(folder);
        return this;
    }

    public CreateFileActivityBuilder setActivityTitle(String title) {
        this.HC.aM(title);
        return this;
    }

    public CreateFileActivityBuilder setInitialContents(Contents contents) {
        this.HD = (Contents) hn.f(contents);
        this.HC.aS(this.HD.getRequestId());
        return this;
    }

    public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet metadataChangeSet) {
        this.HC.a(metadataChangeSet);
        return this;
    }
}
