package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.MatchAllFilter;
import com.google.android.gms.drive.query.internal.Operator;
import java.util.ArrayList;
import java.util.List;

public class Query implements SafeParcelable {
    public static final Parcelable.Creator<Query> CREATOR = new a();
    final LogicalFilter KB;
    final String KC;
    final SortOrder KD;
    final int xJ;

    public static class Builder {
        private String KC;
        private SortOrder KD;
        private final List<Filter> KE = new ArrayList();

        public Builder addFilter(Filter filter) {
            if (!(filter instanceof MatchAllFilter)) {
                this.KE.add(filter);
            }
            return this;
        }

        public Query build() {
            return new Query(new LogicalFilter(Operator.Lc, this.KE), this.KC, this.KD);
        }

        public Builder setPageToken(String token) {
            this.KC = token;
            return this;
        }

        public Builder setSortOrder(SortOrder sortOrder) {
            this.KD = sortOrder;
            return this;
        }
    }

    Query(int versionCode, LogicalFilter clause, String pageToken, SortOrder sortOrder) {
        this.xJ = versionCode;
        this.KB = clause;
        this.KC = pageToken;
        this.KD = sortOrder;
    }

    Query(LogicalFilter clause, String pageToken, SortOrder sortOrder) {
        this(1, clause, pageToken, sortOrder);
    }

    public int describeContents() {
        return 0;
    }

    public Filter getFilter() {
        return this.KB;
    }

    public String getPageToken() {
        return this.KC;
    }

    public SortOrder getSortOrder() {
        return this.KD;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
