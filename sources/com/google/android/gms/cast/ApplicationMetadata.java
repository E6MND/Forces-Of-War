package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.gj;
import com.google.android.gms.internal.hl;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationMetadata implements SafeParcelable {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new a();
    String mName;
    private final int xJ;
    String zM;
    List<WebImage> zN;
    List<String> zO;
    String zP;
    Uri zQ;

    private ApplicationMetadata() {
        this.xJ = 1;
        this.zN = new ArrayList();
        this.zO = new ArrayList();
    }

    ApplicationMetadata(int versionCode, String applicationId, String name, List<WebImage> images, List<String> namespaces, String senderAppIdentifier, Uri senderAppLaunchUrl) {
        this.xJ = versionCode;
        this.zM = applicationId;
        this.mName = name;
        this.zN = images;
        this.zO = namespaces;
        this.zP = senderAppIdentifier;
        this.zQ = senderAppLaunchUrl;
    }

    public boolean areNamespacesSupported(List<String> namespaces) {
        return this.zO != null && this.zO.containsAll(namespaces);
    }

    public Uri dS() {
        return this.zQ;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return gj.a(this.zM, applicationMetadata.zM) && gj.a(this.zN, applicationMetadata.zN) && gj.a(this.mName, applicationMetadata.mName) && gj.a(this.zO, applicationMetadata.zO) && gj.a(this.zP, applicationMetadata.zP) && gj.a(this.zQ, applicationMetadata.zQ);
    }

    public String getApplicationId() {
        return this.zM;
    }

    public List<WebImage> getImages() {
        return this.zN;
    }

    public String getName() {
        return this.mName;
    }

    public String getSenderAppIdentifier() {
        return this.zP;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return hl.hashCode(Integer.valueOf(this.xJ), this.zM, this.mName, this.zN, this.zO, this.zP, this.zQ);
    }

    public boolean isNamespaceSupported(String namespace) {
        return this.zO != null && this.zO.contains(namespace);
    }

    public String toString() {
        return this.mName;
    }

    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
