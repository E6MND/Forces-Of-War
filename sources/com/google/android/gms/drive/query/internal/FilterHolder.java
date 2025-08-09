package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder implements SafeParcelable {
    public static final Parcelable.Creator<FilterHolder> CREATOR = new d();
    final ComparisonFilter<?> KM;
    final FieldOnlyFilter KN;
    final LogicalFilter KO;
    final NotFilter KP;
    final InFilter<?> KQ;
    final MatchAllFilter KR;
    final HasFilter KS;
    private final Filter KT;
    final int xJ;

    FilterHolder(int versionCode, ComparisonFilter<?> comparisonField, FieldOnlyFilter fieldOnlyFilter, LogicalFilter logicalFilter, NotFilter notFilter, InFilter<?> containsFilter, MatchAllFilter matchAllFilter, HasFilter<?> hasFilter) {
        this.xJ = versionCode;
        this.KM = comparisonField;
        this.KN = fieldOnlyFilter;
        this.KO = logicalFilter;
        this.KP = notFilter;
        this.KQ = containsFilter;
        this.KR = matchAllFilter;
        this.KS = hasFilter;
        if (this.KM != null) {
            this.KT = this.KM;
        } else if (this.KN != null) {
            this.KT = this.KN;
        } else if (this.KO != null) {
            this.KT = this.KO;
        } else if (this.KP != null) {
            this.KT = this.KP;
        } else if (this.KQ != null) {
            this.KT = this.KQ;
        } else if (this.KR != null) {
            this.KT = this.KR;
        } else if (this.KS != null) {
            this.KT = this.KS;
        } else {
            throw new IllegalArgumentException("At least one filter must be set.");
        }
    }

    public FilterHolder(Filter filter) {
        this.xJ = 2;
        this.KM = filter instanceof ComparisonFilter ? (ComparisonFilter) filter : null;
        this.KN = filter instanceof FieldOnlyFilter ? (FieldOnlyFilter) filter : null;
        this.KO = filter instanceof LogicalFilter ? (LogicalFilter) filter : null;
        this.KP = filter instanceof NotFilter ? (NotFilter) filter : null;
        this.KQ = filter instanceof InFilter ? (InFilter) filter : null;
        this.KR = filter instanceof MatchAllFilter ? (MatchAllFilter) filter : null;
        this.KS = filter instanceof HasFilter ? (HasFilter) filter : null;
        if (this.KM == null && this.KN == null && this.KO == null && this.KP == null && this.KQ == null && this.KR == null && this.KS == null) {
            throw new IllegalArgumentException("Invalid filter type or null filter.");
        }
        this.KT = filter;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        d.a(this, out, flags);
    }
}
