package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class l extends Metadata {
    private final MetadataBundle IF;

    public l(MetadataBundle metadataBundle) {
        this.IF = metadataBundle;
    }

    /* access modifiers changed from: protected */
    public <T> T a(MetadataField<T> metadataField) {
        return this.IF.a(metadataField);
    }

    /* renamed from: gg */
    public Metadata freeze() {
        return new l(MetadataBundle.a(this.IF));
    }

    public boolean isDataValid() {
        return this.IF != null;
    }

    public String toString() {
        return "Metadata [mImpl=" + this.IF + "]";
    }
}
