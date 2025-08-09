package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class au {
    public static final String DEVICE_ID_EMULATOR = eu.y("emulator");
    private final Date d;
    private final Set<String> f;
    private final Location h;
    private final String mi;
    private final int mj;
    private final boolean mk;
    private final Bundle ml;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> mm;
    private final String mn;
    private final SearchAdRequest mo;
    private final int mp;
    private final Set<String> mq;

    public static final class a {
        /* access modifiers changed from: private */
        public Date d;
        /* access modifiers changed from: private */
        public Location h;
        /* access modifiers changed from: private */
        public String mi;
        /* access modifiers changed from: private */
        public int mj = -1;
        /* access modifiers changed from: private */
        public boolean mk = false;
        /* access modifiers changed from: private */
        public final Bundle ml = new Bundle();
        /* access modifiers changed from: private */
        public String mn;
        /* access modifiers changed from: private */
        public int mp = -1;
        /* access modifiers changed from: private */
        public final HashSet<String> mr = new HashSet<>();
        /* access modifiers changed from: private */
        public final HashMap<Class<? extends NetworkExtras>, NetworkExtras> ms = new HashMap<>();
        /* access modifiers changed from: private */
        public final HashSet<String> mt = new HashSet<>();

        public void a(Location location) {
            this.h = location;
        }

        @Deprecated
        public void a(NetworkExtras networkExtras) {
            if (networkExtras instanceof AdMobExtras) {
                a(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
            } else {
                this.ms.put(networkExtras.getClass(), networkExtras);
            }
        }

        public void a(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.ml.putBundle(cls.getName(), bundle);
        }

        public void a(Date date) {
            this.d = date;
        }

        public void b(Class<? extends CustomEvent> cls, Bundle bundle) {
            if (this.ml.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
                this.ml.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
            }
            this.ml.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
        }

        public void e(int i) {
            this.mj = i;
        }

        public void g(String str) {
            this.mr.add(str);
        }

        public void g(boolean z) {
            this.mk = z;
        }

        public void h(String str) {
            this.mt.add(str);
        }

        public void h(boolean z) {
            this.mp = z ? 1 : 0;
        }

        public void i(String str) {
            this.mi = str;
        }

        public void j(String str) {
            this.mn = str;
        }
    }

    public au(a aVar) {
        this(aVar, (SearchAdRequest) null);
    }

    public au(a aVar, SearchAdRequest searchAdRequest) {
        this.d = aVar.d;
        this.mi = aVar.mi;
        this.mj = aVar.mj;
        this.f = Collections.unmodifiableSet(aVar.mr);
        this.h = aVar.h;
        this.mk = aVar.mk;
        this.ml = aVar.ml;
        this.mm = Collections.unmodifiableMap(aVar.ms);
        this.mn = aVar.mn;
        this.mo = searchAdRequest;
        this.mp = aVar.mp;
        this.mq = Collections.unmodifiableSet(aVar.mt);
    }

    public SearchAdRequest aC() {
        return this.mo;
    }

    public Map<Class<? extends NetworkExtras>, NetworkExtras> aD() {
        return this.mm;
    }

    public Bundle aE() {
        return this.ml;
    }

    public int aF() {
        return this.mp;
    }

    public Date getBirthday() {
        return this.d;
    }

    public String getContentUrl() {
        return this.mi;
    }

    public Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> adapterClass) {
        Bundle bundle = this.ml.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(adapterClass.getClass().getName());
        }
        return null;
    }

    public int getGender() {
        return this.mj;
    }

    public Set<String> getKeywords() {
        return this.f;
    }

    public Location getLocation() {
        return this.h;
    }

    public boolean getManualImpressionsEnabled() {
        return this.mk;
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> networkExtrasClass) {
        return (NetworkExtras) this.mm.get(networkExtrasClass);
    }

    public Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> adapterClass) {
        return this.ml.getBundle(adapterClass.getName());
    }

    public String getPublisherProvidedId() {
        return this.mn;
    }

    public boolean isTestDevice(Context context) {
        return this.mq.contains(eu.o(context));
    }
}
