package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.gj;
import com.google.android.gms.internal.hl;
import java.util.Locale;

public class LaunchOptions implements SafeParcelable {
    public static final Parcelable.Creator<LaunchOptions> CREATOR = new c();
    private boolean An;
    private String Ao;
    private final int xJ;

    public static final class Builder {
        private LaunchOptions Ap = new LaunchOptions();

        public LaunchOptions build() {
            return this.Ap;
        }

        public Builder setLocale(Locale locale) {
            this.Ap.setLanguage(gj.b(locale));
            return this;
        }

        public Builder setRelaunchIfRunning(boolean relaunchIfRunning) {
            this.Ap.setRelaunchIfRunning(relaunchIfRunning);
            return this;
        }
    }

    public LaunchOptions() {
        this(1, false, gj.b(Locale.getDefault()));
    }

    LaunchOptions(int versionCode, boolean relaunchIfRunning, String language) {
        this.xJ = versionCode;
        this.An = relaunchIfRunning;
        this.Ao = language;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.An == launchOptions.An && gj.a(this.Ao, launchOptions.Ao);
    }

    public String getLanguage() {
        return this.Ao;
    }

    public boolean getRelaunchIfRunning() {
        return this.An;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Boolean.valueOf(this.An), this.Ao);
    }

    public void setLanguage(String language) {
        this.Ao = language;
    }

    public void setRelaunchIfRunning(boolean relaunchIfRunning) {
        this.An = relaunchIfRunning;
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", new Object[]{Boolean.valueOf(this.An), this.Ao});
    }

    public void writeToParcel(Parcel out, int flags) {
        c.a(this, out, flags);
    }
}
