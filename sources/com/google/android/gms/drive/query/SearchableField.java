package com.google.android.gms.drive.query;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.it;
import java.util.Date;

public class SearchableField {
    public static final SearchableMetadataField<Boolean> IS_PINNED = ir.Kc;
    public static final SearchableOrderedMetadataField<Date> KF = it.Kx;
    public static final SearchableMetadataField<AppVisibleCustomProperties> KG = ir.JS;
    public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME = it.Ku;
    public static final SearchableMetadataField<String> MIME_TYPE = ir.Kh;
    public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE = it.Kv;
    public static final SearchableCollectionMetadataField<DriveId> PARENTS = ir.Kk;
    public static final SearchableMetadataField<Boolean> STARRED = ir.Km;
    public static final SearchableMetadataField<String> TITLE = ir.Ko;
    public static final SearchableMetadataField<Boolean> TRASHED = ir.Kp;
}
