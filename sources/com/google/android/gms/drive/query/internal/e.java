package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Set;

class e {
    static MetadataField<?> b(MetadataBundle metadataBundle) {
        Set<MetadataField<?>> gB = metadataBundle.gB();
        if (gB.size() == 1) {
            return gB.iterator().next();
        }
        throw new IllegalArgumentException("bundle should have exactly 1 populated field");
    }
}
