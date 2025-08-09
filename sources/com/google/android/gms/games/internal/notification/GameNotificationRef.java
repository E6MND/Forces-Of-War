package com.google.android.gms.games.internal.notification;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.internal.hl;
import com.google.android.gms.plus.PlusShare;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class GameNotificationRef extends d implements GameNotification {
    GameNotificationRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public long getId() {
        return getLong("_id");
    }

    public String getText() {
        return getString("text");
    }

    public String getTitle() {
        return getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
    }

    public int getType() {
        return getInteger(MessagingSmsConsts.TYPE);
    }

    /* renamed from: if  reason: not valid java name */
    public String m1if() {
        return getString("notification_id");
    }

    public String ig() {
        return getString("ticker");
    }

    public String ih() {
        return getString("coalesced_text");
    }

    public boolean ii() {
        return getInteger("acknowledged") > 0;
    }

    public boolean ij() {
        return getInteger("alert_level") == 0;
    }

    public String toString() {
        return hl.e(this).a("Id", Long.valueOf(getId())).a("NotificationId", m1if()).a("Type", Integer.valueOf(getType())).a("Title", getTitle()).a("Ticker", ig()).a("Text", getText()).a("CoalescedText", ih()).a("isAcknowledged", Boolean.valueOf(ii())).a("isSilent", Boolean.valueOf(ij())).toString();
    }
}
