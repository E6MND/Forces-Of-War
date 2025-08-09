package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.internal.ji;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingEvent {
    private final int Vf;
    private final List<Geofence> Vg;
    private final Location Vh;
    private final int pH;

    private GeofencingEvent(int errorCode, int transitionType, List<Geofence> triggeringGeofences, Location triggeringLocaton) {
        this.pH = errorCode;
        this.Vf = transitionType;
        this.Vg = triggeringGeofences;
        this.Vh = triggeringLocaton;
    }

    public static GeofencingEvent fromIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        return new GeofencingEvent(intent.getIntExtra("gms_error_code", -1), getGeofenceTransition(intent), getTriggeringGeofences(intent), (Location) intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
    }

    private static int getGeofenceTransition(Intent intent) {
        int intExtra = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        if (intExtra == -1) {
            return -1;
        }
        if (intExtra == 1 || intExtra == 2 || intExtra == 4) {
            return intExtra;
        }
        return -1;
    }

    private static List<Geofence> getTriggeringGeofences(Intent intent) {
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if (arrayList == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(ji.h((byte[]) it.next()));
        }
        return arrayList2;
    }

    public int getErrorCode() {
        return this.pH;
    }

    public int getGeofenceTransition() {
        return this.Vf;
    }

    public List<Geofence> getTriggeringGeofences() {
        return this.Vg;
    }

    public Location getTriggeringLocation() {
        return this.Vh;
    }

    public boolean hasError() {
        return this.pH != -1;
    }
}
