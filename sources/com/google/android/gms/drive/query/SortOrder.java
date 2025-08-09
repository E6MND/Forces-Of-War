package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.ArrayList;
import java.util.List;

public class SortOrder implements SafeParcelable {
    public static final Parcelable.Creator<SortOrder> CREATOR = new b();
    final List<FieldWithSortOrder> KH;
    final int xJ;

    public static class Builder {
        private final List<FieldWithSortOrder> KH = new ArrayList();

        public Builder addSortAscending(SortableMetadataField sortField) {
            this.KH.add(new FieldWithSortOrder(sortField.getName(), true));
            return this;
        }

        public Builder addSortDescending(SortableMetadataField sortField) {
            this.KH.add(new FieldWithSortOrder(sortField.getName(), false));
            return this;
        }

        public SortOrder build() {
            return new SortOrder((List) this.KH);
        }
    }

    SortOrder(int versionCode, List<FieldWithSortOrder> sortingFields) {
        this.xJ = versionCode;
        this.KH = sortingFields;
    }

    private SortOrder(List<FieldWithSortOrder> sortingFields) {
        this(1, sortingFields);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
