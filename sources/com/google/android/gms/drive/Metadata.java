package com.google.android.gms.drive;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.it;
import com.google.android.gms.internal.iv;
import java.util.Date;

public abstract class Metadata implements Freezable<Metadata> {
    public static final int CONTENT_AVAILABLE_LOCALLY = 1;
    public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;

    /* access modifiers changed from: protected */
    public abstract <T> T a(MetadataField<T> metadataField);

    public String getAlternateLink() {
        return (String) a(ir.JR);
    }

    public int getContentAvailability() {
        Integer num = (Integer) a(iv.Kz);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public Date getCreatedDate() {
        return (Date) a(it.Kt);
    }

    public String getDescription() {
        return (String) a(ir.JT);
    }

    public DriveId getDriveId() {
        return (DriveId) a(ir.JQ);
    }

    public String getEmbedLink() {
        return (String) a(ir.JU);
    }

    public String getFileExtension() {
        return (String) a(ir.JV);
    }

    public long getFileSize() {
        return ((Long) a(ir.JW)).longValue();
    }

    public Date getLastViewedByMeDate() {
        return (Date) a(it.Ku);
    }

    public String getMimeType() {
        return (String) a(ir.Kh);
    }

    public Date getModifiedByMeDate() {
        return (Date) a(it.Kw);
    }

    public Date getModifiedDate() {
        return (Date) a(it.Kv);
    }

    public String getOriginalFilename() {
        return (String) a(ir.Ki);
    }

    public long getQuotaBytesUsed() {
        return ((Long) a(ir.Kl)).longValue();
    }

    public Date getSharedWithMeDate() {
        return (Date) a(it.Kx);
    }

    public String getTitle() {
        return (String) a(ir.Ko);
    }

    public String getWebContentLink() {
        return (String) a(ir.Kq);
    }

    public String getWebViewLink() {
        return (String) a(ir.Kr);
    }

    public boolean isEditable() {
        Boolean bool = (Boolean) a(ir.Kb);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isFolder() {
        return DriveFolder.MIME_TYPE.equals(getMimeType());
    }

    public boolean isInAppFolder() {
        Boolean bool = (Boolean) a(ir.JZ);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isPinnable() {
        Boolean bool = (Boolean) a(iv.KA);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isPinned() {
        Boolean bool = (Boolean) a(ir.Kc);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isRestricted() {
        Boolean bool = (Boolean) a(ir.Kd);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isShared() {
        Boolean bool = (Boolean) a(ir.Ke);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isStarred() {
        Boolean bool = (Boolean) a(ir.Km);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isTrashed() {
        Boolean bool = (Boolean) a(ir.Kp);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean isViewed() {
        Boolean bool = (Boolean) a(ir.Kg);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }
}
