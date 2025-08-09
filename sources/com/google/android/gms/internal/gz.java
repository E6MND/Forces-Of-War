package com.google.android.gms.internal;

import android.os.Parcel;
import android.view.View;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class gz {
    private final View DG;
    private final a FR;

    public static final class a implements SafeParcelable {
        public static final hm CREATOR = new hm();
        private final int DF;
        private final String DH;
        private final List<String> Ec;
        private final int xJ;
        private final String yN;

        a(int i, String str, List<String> list, int i2, String str2) {
            this.Ec = new ArrayList();
            this.xJ = i;
            this.yN = str;
            this.Ec.addAll(list);
            this.DF = i2;
            this.DH = str2;
        }

        public a(String str, Collection<String> collection, int i, String str2) {
            this(3, str, new ArrayList(collection), i, str2);
        }

        public int describeContents() {
            return 0;
        }

        public String fe() {
            return this.yN != null ? this.yN : "<<default account>>";
        }

        public int ff() {
            return this.DF;
        }

        public List<String> fg() {
            return new ArrayList(this.Ec);
        }

        public String fi() {
            return this.DH;
        }

        public String getAccountName() {
            return this.yN;
        }

        public int getVersionCode() {
            return this.xJ;
        }

        public void writeToParcel(Parcel out, int flags) {
            hm.a(this, out, flags);
        }
    }

    public gz(String str, Collection<String> collection, int i, View view, String str2) {
        this.FR = new a(str, collection, i, str2);
        this.DG = view;
    }

    public String fe() {
        return this.FR.fe();
    }

    public int ff() {
        return this.FR.ff();
    }

    public List<String> fg() {
        return this.FR.fg();
    }

    public String[] fh() {
        return (String[]) this.FR.fg().toArray(new String[0]);
    }

    public String fi() {
        return this.FR.fi();
    }

    public View fj() {
        return this.DG;
    }

    public String getAccountName() {
        return this.FR.getAccountName();
    }
}
