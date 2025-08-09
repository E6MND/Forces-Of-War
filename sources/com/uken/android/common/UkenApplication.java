package com.uken.android.common;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.uken.android.cache.ImageCacheManager;
import com.uken.android.cache.RequestManager;
import com.uken.android.forcesofwar.R;
import java.util.HashMap;

public class UkenApplication extends Application {
    public static String GA_PROPERTY_ID;
    private static int IMAGECACHE_SIZE = 10485760;
    private static Context mContext;
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<>();

    public enum TrackerName {
        APP_TRACKER,
        GLOBAL_TRACKER,
        ECOMMERCE_TRACKER
    }

    public void onCreate() {
        super.onCreate();
        RequestManager.init(this);
        ImageCacheManager.getInstance().init(this, getPackageCodePath(), IMAGECACHE_SIZE, ImageCacheManager.IMAGECACHE_QUALITY);
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    /* access modifiers changed from: package-private */
    public synchronized Tracker getTracker(TrackerName trackerId) {
        Tracker t;
        if (!this.mTrackers.containsKey(trackerId)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            if (trackerId == TrackerName.APP_TRACKER) {
                t = analytics.newTracker((int) R.xml.app_tracker);
            } else if (trackerId == TrackerName.GLOBAL_TRACKER) {
                t = analytics.newTracker(GA_PROPERTY_ID);
            } else {
                t = analytics.newTracker((int) R.xml.ecommerce_tracker);
            }
            this.mTrackers.put(trackerId, t);
        }
        return this.mTrackers.get(trackerId);
    }
}
