package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.j;
import java.util.Arrays;
import java.util.Collections;

public class is extends j<AppVisibleCustomProperties> {
    public is(int i) {
        super("customFileProperties", Collections.emptyList(), Arrays.asList(new String[]{"customPropertiesExtra"}), i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public AppVisibleCustomProperties b(DataHolder dataHolder, int i, int i2) {
        return (AppVisibleCustomProperties) dataHolder.eP().getSparseParcelableArray("customPropertiesExtra").get(i, AppVisibleCustomProperties.JK);
    }
}
