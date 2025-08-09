package com.google.android.gms.drive;

import android.content.Context;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.it;
import java.util.Date;

public final class MetadataChangeSet {
    public static final MetadataChangeSet HS = new MetadataChangeSet(MetadataBundle.gA());
    private final MetadataBundle HT;

    public static class Builder {
        private final MetadataBundle HT;
        private AppVisibleCustomProperties.a HU;
        private final Context mContext;

        public Builder() {
            this((Context) null);
        }

        public Builder(Context context) {
            this.HT = MetadataBundle.gA();
            this.mContext = context;
        }

        public MetadataChangeSet build() {
            if (this.HU != null) {
                this.HT.b(ir.JS, this.HU.gy());
            }
            return new MetadataChangeSet(this.HT);
        }

        public Builder setDescription(String description) {
            this.HT.b(ir.JT, description);
            return this;
        }

        public Builder setIndexableText(String text) {
            this.HT.b(ir.JY, text);
            return this;
        }

        public Builder setLastViewedByMeDate(Date date) {
            this.HT.b(it.Ku, date);
            return this;
        }

        public Builder setMimeType(String mimeType) {
            this.HT.b(ir.Kh, mimeType);
            return this;
        }

        public Builder setPinned(boolean pinned) {
            this.HT.b(ir.Kc, Boolean.valueOf(pinned));
            return this;
        }

        public Builder setStarred(boolean starred) {
            this.HT.b(ir.Km, Boolean.valueOf(starred));
            return this;
        }

        public Builder setTitle(String title) {
            this.HT.b(ir.Ko, title);
            return this;
        }

        public Builder setViewed(boolean viewed) {
            this.HT.b(ir.Kg, Boolean.valueOf(viewed));
            return this;
        }
    }

    public MetadataChangeSet(MetadataBundle bag) {
        this.HT = MetadataBundle.a(bag);
    }

    public String getDescription() {
        return (String) this.HT.a(ir.JT);
    }

    public String getIndexableText() {
        return (String) this.HT.a(ir.JY);
    }

    public Date getLastViewedByMeDate() {
        return (Date) this.HT.a(it.Ku);
    }

    public String getMimeType() {
        return (String) this.HT.a(ir.Kh);
    }

    public String getTitle() {
        return (String) this.HT.a(ir.Ko);
    }

    public MetadataBundle gh() {
        return this.HT;
    }

    public Boolean isPinned() {
        return (Boolean) this.HT.a(ir.Kc);
    }

    public Boolean isStarred() {
        return (Boolean) this.HT.a(ir.Km);
    }

    public Boolean isViewed() {
        return (Boolean) this.HT.a(ir.Kg);
    }
}
