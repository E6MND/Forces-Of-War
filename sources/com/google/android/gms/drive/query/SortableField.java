package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.it;
import java.util.Date;

public class SortableField {
    public static final SortableMetadataField<Date> CREATED_DATE = it.Kt;
    public static final SortableMetadataField<Date> LAST_VIEWED_BY_ME = it.Ku;
    public static final SortableMetadataField<Date> MODIFIED_BY_ME_DATE = it.Kw;
    public static final SortableMetadataField<Date> MODIFIED_DATE = it.Kv;
    public static final SortableMetadataField<Long> QUOTA_USED = ir.Kl;
    public static final SortableMetadataField<Date> SHARED_WITH_ME_DATE = it.Kx;
    public static final SortableMetadataField<String> TITLE = ir.Ko;
}
