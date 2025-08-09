package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;

public final class EventEntity implements SafeParcelable, Event {
    public static final EventEntityCreator CREATOR = new EventEntityCreator();
    private final String Mm;
    private final Uri Mo;
    private final String Mz;
    private final String Nf;
    private final PlayerEntity Ng;
    private final long Nh;
    private final String Ni;
    private final boolean Nj;
    private final String mName;
    private final int xJ;

    EventEntity(int versionCode, String eventId, String name, String description, Uri iconImageUri, String iconImageUrl, Player player, long value, String formattedValue, boolean isVisible) {
        this.xJ = versionCode;
        this.Nf = eventId;
        this.mName = name;
        this.Mm = description;
        this.Mo = iconImageUri;
        this.Mz = iconImageUrl;
        this.Ng = new PlayerEntity(player);
        this.Nh = value;
        this.Ni = formattedValue;
        this.Nj = isVisible;
    }

    public EventEntity(Event event) {
        this.xJ = 1;
        this.Nf = event.getEventId();
        this.mName = event.getName();
        this.Mm = event.getDescription();
        this.Mo = event.getIconImageUri();
        this.Mz = event.getIconImageUrl();
        this.Ng = (PlayerEntity) event.getPlayer().freeze();
        this.Nh = event.getValue();
        this.Ni = event.getFormattedValue();
        this.Nj = event.isVisible();
    }

    static int a(Event event) {
        return hl.hashCode(event.getEventId(), event.getName(), event.getDescription(), event.getIconImageUri(), event.getIconImageUrl(), event.getPlayer(), Long.valueOf(event.getValue()), event.getFormattedValue(), Boolean.valueOf(event.isVisible()));
    }

    static boolean a(Event event, Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        if (event == obj) {
            return true;
        }
        Event event2 = (Event) obj;
        return hl.equal(event2.getEventId(), event.getEventId()) && hl.equal(event2.getName(), event.getName()) && hl.equal(event2.getDescription(), event.getDescription()) && hl.equal(event2.getIconImageUri(), event.getIconImageUri()) && hl.equal(event2.getIconImageUrl(), event.getIconImageUrl()) && hl.equal(event2.getPlayer(), event.getPlayer()) && hl.equal(Long.valueOf(event2.getValue()), Long.valueOf(event.getValue())) && hl.equal(event2.getFormattedValue(), event.getFormattedValue()) && hl.equal(Boolean.valueOf(event2.isVisible()), Boolean.valueOf(event.isVisible()));
    }

    static String b(Event event) {
        return hl.e(event).a("Id", event.getEventId()).a("Name", event.getName()).a("Description", event.getDescription()).a("IconImageUri", event.getIconImageUri()).a("IconImageUrl", event.getIconImageUrl()).a("Player", event.getPlayer()).a("Value", Long.valueOf(event.getValue())).a("FormattedValue", event.getFormattedValue()).a("isVisible", Boolean.valueOf(event.isVisible())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Event freeze() {
        return this;
    }

    public String getDescription() {
        return this.Mm;
    }

    public void getDescription(CharArrayBuffer dataOut) {
        il.b(this.Mm, dataOut);
    }

    public String getEventId() {
        return this.Nf;
    }

    public String getFormattedValue() {
        return this.Ni;
    }

    public void getFormattedValue(CharArrayBuffer dataOut) {
        il.b(this.Ni, dataOut);
    }

    public Uri getIconImageUri() {
        return this.Mo;
    }

    public String getIconImageUrl() {
        return this.Mz;
    }

    public String getName() {
        return this.mName;
    }

    public void getName(CharArrayBuffer dataOut) {
        il.b(this.mName, dataOut);
    }

    public Player getPlayer() {
        return this.Ng;
    }

    public long getValue() {
        return this.Nh;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    public boolean isVisible() {
        return this.Nj;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        EventEntityCreator.a(this, out, flags);
    }
}
