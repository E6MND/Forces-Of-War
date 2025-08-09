package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hn;

public final class SnapshotMetadataChange implements SafeParcelable {
    public static final SnapshotMetadataChangeCreator CREATOR = new SnapshotMetadataChangeCreator();
    public static final SnapshotMetadataChange EMPTY_CHANGE = new SnapshotMetadataChange();
    private final String Mm;
    private final Long Ud;
    private final Uri Ue;
    private a Uf;
    private final int xJ;

    public static final class Builder {
        private String Mm;
        private Uri Ue;
        private Long Ug;
        private a Uh;

        public SnapshotMetadataChange build() {
            return new SnapshotMetadataChange(this.Mm, this.Ug, this.Uh, this.Ue);
        }

        public Builder fromMetadata(SnapshotMetadata metadata) {
            this.Mm = metadata.getDescription();
            this.Ug = Long.valueOf(metadata.getPlayedTime());
            if (this.Ug.longValue() == -1) {
                this.Ug = null;
            }
            this.Ue = metadata.getCoverImageUri();
            if (this.Ue != null) {
                this.Uh = null;
            }
            return this;
        }

        public Builder setCoverImage(Bitmap coverImage) {
            this.Uh = new a(coverImage);
            this.Ue = null;
            return this;
        }

        public Builder setDescription(String description) {
            this.Mm = description;
            return this;
        }

        public Builder setPlayedTimeMillis(long playedTimeMillis) {
            this.Ug = Long.valueOf(playedTimeMillis);
            return this;
        }
    }

    SnapshotMetadataChange() {
        this(4, (String) null, (Long) null, (a) null, (Uri) null);
    }

    SnapshotMetadataChange(int versionCode, String description, Long playedTimeMillis, a coverImage, Uri coverImageUri) {
        boolean z = true;
        this.xJ = versionCode;
        this.Mm = description;
        this.Ud = playedTimeMillis;
        this.Uf = coverImage;
        this.Ue = coverImageUri;
        if (this.Uf != null) {
            hn.a(this.Ue != null ? false : z, "Cannot set both a URI and an image");
        } else if (this.Ue != null) {
            hn.a(this.Uf != null ? false : z, "Cannot set both a URI and an image");
        }
    }

    SnapshotMetadataChange(String description, Long playedTimeMillis, a coverImage, Uri coverImageUri) {
        this(4, description, playedTimeMillis, coverImage, coverImageUri);
    }

    public int describeContents() {
        return 0;
    }

    public Bitmap getCoverImage() {
        if (this.Uf == null) {
            return null;
        }
        return this.Uf.eN();
    }

    public Uri getCoverImageUri() {
        return this.Ue;
    }

    public String getDescription() {
        return this.Mm;
    }

    public Long getPlayedTimeMillis() {
        return this.Ud;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public a iI() {
        return this.Uf;
    }

    public void writeToParcel(Parcel out, int flags) {
        SnapshotMetadataChangeCreator.a(this, out, flags);
    }
}
