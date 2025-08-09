package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class al {
    public static final al mb = new al();

    private al() {
    }

    public static al aA() {
        return mb;
    }

    public aj a(Context context, au auVar) {
        Date birthday = auVar.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = auVar.getContentUrl();
        int gender = auVar.getGender();
        Set<String> keywords = auVar.getKeywords();
        List unmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean isTestDevice = auVar.isTestDevice(context);
        int aF = auVar.aF();
        Location location = auVar.getLocation();
        Bundle networkExtrasBundle = auVar.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = auVar.getManualImpressionsEnabled();
        String publisherProvidedId = auVar.getPublisherProvidedId();
        SearchAdRequest aC = auVar.aC();
        return new aj(4, time, networkExtrasBundle, gender, unmodifiableList, isTestDevice, aF, manualImpressionsEnabled, publisherProvidedId, aC != null ? new ax(aC) : null, location, contentUrl, auVar.aE());
    }
}
