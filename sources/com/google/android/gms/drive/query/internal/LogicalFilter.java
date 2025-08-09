package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.List;

public class LogicalFilter implements SafeParcelable, Filter {
    public static final Parcelable.Creator<LogicalFilter> CREATOR = new h();
    private List<Filter> KE;
    final Operator KI;
    final List<FilterHolder> KV;
    final int xJ;

    LogicalFilter(int versionCode, Operator operator, List<FilterHolder> filterHolders) {
        this.xJ = versionCode;
        this.KI = operator;
        this.KV = filterHolders;
    }

    public LogicalFilter(Operator operator, Filter filter, Filter... additionalFilters) {
        this.xJ = 1;
        this.KI = operator;
        this.KV = new ArrayList(additionalFilters.length + 1);
        this.KV.add(new FilterHolder(filter));
        this.KE = new ArrayList(additionalFilters.length + 1);
        this.KE.add(filter);
        for (Filter filter2 : additionalFilters) {
            this.KV.add(new FilterHolder(filter2));
            this.KE.add(filter2);
        }
    }

    public LogicalFilter(Operator operator, Iterable<Filter> filters) {
        this.xJ = 1;
        this.KI = operator;
        this.KE = new ArrayList();
        this.KV = new ArrayList();
        for (Filter next : filters) {
            this.KE.add(next);
            this.KV.add(new FilterHolder(next));
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        h.a(this, out, flags);
    }
}
