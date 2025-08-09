package com.google.android.gms.internal;

import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hz;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class kt extends hz implements SafeParcelable, Person {
    public static final ku CREATOR = new ku();
    private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
    private String Ao;
    private String Lk;
    private final Set<Integer> acp;
    private List<g> adA;
    private int adB;
    private int adC;
    private String adD;
    private List<h> adE;
    private boolean adF;
    private String adn;
    private a ado;
    private String adp;
    private String adq;
    private int adr;
    private b ads;
    private String adt;
    private c adu;
    private boolean adv;
    private d adw;
    private String adx;
    private int ady;
    private List<f> adz;
    private int mj;
    private String qV;
    private String xD;
    private final int xJ;

    public static final class a extends hz implements SafeParcelable, Person.AgeRange {
        public static final kv CREATOR = new kv();
        private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
        private final Set<Integer> acp;
        private int adG;
        private int adH;
        private final int xJ;

        static {
            aco.put("max", hz.a.g("max", 2));
            aco.put("min", hz.a.g("min", 3));
        }

        public a() {
            this.xJ = 1;
            this.acp = new HashSet();
        }

        a(Set<Integer> set, int i, int i2, int i3) {
            this.acp = set;
            this.xJ = i;
            this.adG = i2;
            this.adH = i3;
        }

        /* access modifiers changed from: protected */
        public boolean a(hz.a aVar) {
            return this.acp.contains(Integer.valueOf(aVar.fI()));
        }

        /* access modifiers changed from: protected */
        public Object aF(String str) {
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean aG(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public Object b(hz.a aVar) {
            switch (aVar.fI()) {
                case 2:
                    return Integer.valueOf(this.adG);
                case 3:
                    return Integer.valueOf(this.adH);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
            }
        }

        public int describeContents() {
            kv kvVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            for (hz.a next : aco.values()) {
                if (a(next)) {
                    if (!aVar.a(next)) {
                        return false;
                    }
                    if (!b(next).equals(aVar.b(next))) {
                        return false;
                    }
                } else if (aVar.a(next)) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<String, hz.a<?, ?>> fB() {
            return aco;
        }

        public int getMax() {
            return this.adG;
        }

        public int getMin() {
            return this.adH;
        }

        /* access modifiers changed from: package-private */
        public int getVersionCode() {
            return this.xJ;
        }

        public boolean hasMax() {
            return this.acp.contains(2);
        }

        public boolean hasMin() {
            return this.acp.contains(3);
        }

        public int hashCode() {
            int i = 0;
            Iterator<hz.a<?, ?>> it = aco.values().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                hz.a next = it.next();
                if (a(next)) {
                    i = b(next).hashCode() + i2 + next.fI();
                } else {
                    i = i2;
                }
            }
        }

        public boolean isDataValid() {
            return true;
        }

        /* renamed from: kI */
        public a freeze() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public Set<Integer> kf() {
            return this.acp;
        }

        public void writeToParcel(Parcel out, int flags) {
            kv kvVar = CREATOR;
            kv.a(this, out, flags);
        }
    }

    public static final class b extends hz implements SafeParcelable, Person.Cover {
        public static final kw CREATOR = new kw();
        private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
        private final Set<Integer> acp;
        private a adI;
        private C0072b adJ;
        private int adK;
        private final int xJ;

        public static final class a extends hz implements SafeParcelable, Person.Cover.CoverInfo {
            public static final kx CREATOR = new kx();
            private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
            private final Set<Integer> acp;
            private int adL;
            private int adM;
            private final int xJ;

            static {
                aco.put("leftImageOffset", hz.a.g("leftImageOffset", 2));
                aco.put("topImageOffset", hz.a.g("topImageOffset", 3));
            }

            public a() {
                this.xJ = 1;
                this.acp = new HashSet();
            }

            a(Set<Integer> set, int i, int i2, int i3) {
                this.acp = set;
                this.xJ = i;
                this.adL = i2;
                this.adM = i3;
            }

            /* access modifiers changed from: protected */
            public boolean a(hz.a aVar) {
                return this.acp.contains(Integer.valueOf(aVar.fI()));
            }

            /* access modifiers changed from: protected */
            public Object aF(String str) {
                return null;
            }

            /* access modifiers changed from: protected */
            public boolean aG(String str) {
                return false;
            }

            /* access modifiers changed from: protected */
            public Object b(hz.a aVar) {
                switch (aVar.fI()) {
                    case 2:
                        return Integer.valueOf(this.adL);
                    case 3:
                        return Integer.valueOf(this.adM);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
                }
            }

            public int describeContents() {
                kx kxVar = CREATOR;
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof a)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                a aVar = (a) obj;
                for (hz.a next : aco.values()) {
                    if (a(next)) {
                        if (!aVar.a(next)) {
                            return false;
                        }
                        if (!b(next).equals(aVar.b(next))) {
                            return false;
                        }
                    } else if (aVar.a(next)) {
                        return false;
                    }
                }
                return true;
            }

            public HashMap<String, hz.a<?, ?>> fB() {
                return aco;
            }

            public int getLeftImageOffset() {
                return this.adL;
            }

            public int getTopImageOffset() {
                return this.adM;
            }

            /* access modifiers changed from: package-private */
            public int getVersionCode() {
                return this.xJ;
            }

            public boolean hasLeftImageOffset() {
                return this.acp.contains(2);
            }

            public boolean hasTopImageOffset() {
                return this.acp.contains(3);
            }

            public int hashCode() {
                int i = 0;
                Iterator<hz.a<?, ?>> it = aco.values().iterator();
                while (true) {
                    int i2 = i;
                    if (!it.hasNext()) {
                        return i2;
                    }
                    hz.a next = it.next();
                    if (a(next)) {
                        i = b(next).hashCode() + i2 + next.fI();
                    } else {
                        i = i2;
                    }
                }
            }

            public boolean isDataValid() {
                return true;
            }

            /* renamed from: kM */
            public a freeze() {
                return this;
            }

            /* access modifiers changed from: package-private */
            public Set<Integer> kf() {
                return this.acp;
            }

            public void writeToParcel(Parcel out, int flags) {
                kx kxVar = CREATOR;
                kx.a(this, out, flags);
            }
        }

        /* renamed from: com.google.android.gms.internal.kt$b$b  reason: collision with other inner class name */
        public static final class C0072b extends hz implements SafeParcelable, Person.Cover.CoverPhoto {
            public static final ky CREATOR = new ky();
            private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
            private final Set<Integer> acp;
            private int ks;
            private int kt;
            private String qV;
            private final int xJ;

            static {
                aco.put("height", hz.a.g("height", 2));
                aco.put("url", hz.a.j("url", 3));
                aco.put("width", hz.a.g("width", 4));
            }

            public C0072b() {
                this.xJ = 1;
                this.acp = new HashSet();
            }

            C0072b(Set<Integer> set, int i, int i2, String str, int i3) {
                this.acp = set;
                this.xJ = i;
                this.kt = i2;
                this.qV = str;
                this.ks = i3;
            }

            /* access modifiers changed from: protected */
            public boolean a(hz.a aVar) {
                return this.acp.contains(Integer.valueOf(aVar.fI()));
            }

            /* access modifiers changed from: protected */
            public Object aF(String str) {
                return null;
            }

            /* access modifiers changed from: protected */
            public boolean aG(String str) {
                return false;
            }

            /* access modifiers changed from: protected */
            public Object b(hz.a aVar) {
                switch (aVar.fI()) {
                    case 2:
                        return Integer.valueOf(this.kt);
                    case 3:
                        return this.qV;
                    case 4:
                        return Integer.valueOf(this.ks);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
                }
            }

            public int describeContents() {
                ky kyVar = CREATOR;
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof C0072b)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                C0072b bVar = (C0072b) obj;
                for (hz.a next : aco.values()) {
                    if (a(next)) {
                        if (!bVar.a(next)) {
                            return false;
                        }
                        if (!b(next).equals(bVar.b(next))) {
                            return false;
                        }
                    } else if (bVar.a(next)) {
                        return false;
                    }
                }
                return true;
            }

            public HashMap<String, hz.a<?, ?>> fB() {
                return aco;
            }

            public int getHeight() {
                return this.kt;
            }

            public String getUrl() {
                return this.qV;
            }

            /* access modifiers changed from: package-private */
            public int getVersionCode() {
                return this.xJ;
            }

            public int getWidth() {
                return this.ks;
            }

            public boolean hasHeight() {
                return this.acp.contains(2);
            }

            public boolean hasUrl() {
                return this.acp.contains(3);
            }

            public boolean hasWidth() {
                return this.acp.contains(4);
            }

            public int hashCode() {
                int i = 0;
                Iterator<hz.a<?, ?>> it = aco.values().iterator();
                while (true) {
                    int i2 = i;
                    if (!it.hasNext()) {
                        return i2;
                    }
                    hz.a next = it.next();
                    if (a(next)) {
                        i = b(next).hashCode() + i2 + next.fI();
                    } else {
                        i = i2;
                    }
                }
            }

            public boolean isDataValid() {
                return true;
            }

            /* renamed from: kN */
            public C0072b freeze() {
                return this;
            }

            /* access modifiers changed from: package-private */
            public Set<Integer> kf() {
                return this.acp;
            }

            public void writeToParcel(Parcel out, int flags) {
                ky kyVar = CREATOR;
                ky.a(this, out, flags);
            }
        }

        static {
            aco.put("coverInfo", hz.a.a("coverInfo", 2, a.class));
            aco.put("coverPhoto", hz.a.a("coverPhoto", 3, C0072b.class));
            aco.put("layout", hz.a.a("layout", 4, new hw().f("banner", 0), false));
        }

        public b() {
            this.xJ = 1;
            this.acp = new HashSet();
        }

        b(Set<Integer> set, int i, a aVar, C0072b bVar, int i2) {
            this.acp = set;
            this.xJ = i;
            this.adI = aVar;
            this.adJ = bVar;
            this.adK = i2;
        }

        /* access modifiers changed from: protected */
        public boolean a(hz.a aVar) {
            return this.acp.contains(Integer.valueOf(aVar.fI()));
        }

        /* access modifiers changed from: protected */
        public Object aF(String str) {
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean aG(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public Object b(hz.a aVar) {
            switch (aVar.fI()) {
                case 2:
                    return this.adI;
                case 3:
                    return this.adJ;
                case 4:
                    return Integer.valueOf(this.adK);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
            }
        }

        public int describeContents() {
            kw kwVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            b bVar = (b) obj;
            for (hz.a next : aco.values()) {
                if (a(next)) {
                    if (!bVar.a(next)) {
                        return false;
                    }
                    if (!b(next).equals(bVar.b(next))) {
                        return false;
                    }
                } else if (bVar.a(next)) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<String, hz.a<?, ?>> fB() {
            return aco;
        }

        public Person.Cover.CoverInfo getCoverInfo() {
            return this.adI;
        }

        public Person.Cover.CoverPhoto getCoverPhoto() {
            return this.adJ;
        }

        public int getLayout() {
            return this.adK;
        }

        /* access modifiers changed from: package-private */
        public int getVersionCode() {
            return this.xJ;
        }

        public boolean hasCoverInfo() {
            return this.acp.contains(2);
        }

        public boolean hasCoverPhoto() {
            return this.acp.contains(3);
        }

        public boolean hasLayout() {
            return this.acp.contains(4);
        }

        public int hashCode() {
            int i = 0;
            Iterator<hz.a<?, ?>> it = aco.values().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                hz.a next = it.next();
                if (a(next)) {
                    i = b(next).hashCode() + i2 + next.fI();
                } else {
                    i = i2;
                }
            }
        }

        public boolean isDataValid() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public a kJ() {
            return this.adI;
        }

        /* access modifiers changed from: package-private */
        public C0072b kK() {
            return this.adJ;
        }

        /* renamed from: kL */
        public b freeze() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public Set<Integer> kf() {
            return this.acp;
        }

        public void writeToParcel(Parcel out, int flags) {
            kw kwVar = CREATOR;
            kw.a(this, out, flags);
        }
    }

    public static final class c extends hz implements SafeParcelable, Person.Image {
        public static final kz CREATOR = new kz();
        private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
        private final Set<Integer> acp;
        private String qV;
        private final int xJ;

        static {
            aco.put("url", hz.a.j("url", 2));
        }

        public c() {
            this.xJ = 1;
            this.acp = new HashSet();
        }

        public c(String str) {
            this.acp = new HashSet();
            this.xJ = 1;
            this.qV = str;
            this.acp.add(2);
        }

        c(Set<Integer> set, int i, String str) {
            this.acp = set;
            this.xJ = i;
            this.qV = str;
        }

        /* access modifiers changed from: protected */
        public boolean a(hz.a aVar) {
            return this.acp.contains(Integer.valueOf(aVar.fI()));
        }

        /* access modifiers changed from: protected */
        public Object aF(String str) {
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean aG(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public Object b(hz.a aVar) {
            switch (aVar.fI()) {
                case 2:
                    return this.qV;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
            }
        }

        public int describeContents() {
            kz kzVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            c cVar = (c) obj;
            for (hz.a next : aco.values()) {
                if (a(next)) {
                    if (!cVar.a(next)) {
                        return false;
                    }
                    if (!b(next).equals(cVar.b(next))) {
                        return false;
                    }
                } else if (cVar.a(next)) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<String, hz.a<?, ?>> fB() {
            return aco;
        }

        public String getUrl() {
            return this.qV;
        }

        /* access modifiers changed from: package-private */
        public int getVersionCode() {
            return this.xJ;
        }

        public boolean hasUrl() {
            return this.acp.contains(2);
        }

        public int hashCode() {
            int i = 0;
            Iterator<hz.a<?, ?>> it = aco.values().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                hz.a next = it.next();
                if (a(next)) {
                    i = b(next).hashCode() + i2 + next.fI();
                } else {
                    i = i2;
                }
            }
        }

        public boolean isDataValid() {
            return true;
        }

        /* renamed from: kO */
        public c freeze() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public Set<Integer> kf() {
            return this.acp;
        }

        public void writeToParcel(Parcel out, int flags) {
            kz kzVar = CREATOR;
            kz.a(this, out, flags);
        }
    }

    public static final class d extends hz implements SafeParcelable, Person.Name {
        public static final la CREATOR = new la();
        private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
        private String acN;
        private String acQ;
        private final Set<Integer> acp;
        private String adN;
        private String adO;
        private String adP;
        private String adQ;
        private final int xJ;

        static {
            aco.put("familyName", hz.a.j("familyName", 2));
            aco.put("formatted", hz.a.j("formatted", 3));
            aco.put("givenName", hz.a.j("givenName", 4));
            aco.put("honorificPrefix", hz.a.j("honorificPrefix", 5));
            aco.put("honorificSuffix", hz.a.j("honorificSuffix", 6));
            aco.put("middleName", hz.a.j("middleName", 7));
        }

        public d() {
            this.xJ = 1;
            this.acp = new HashSet();
        }

        d(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, String str6) {
            this.acp = set;
            this.xJ = i;
            this.acN = str;
            this.adN = str2;
            this.acQ = str3;
            this.adO = str4;
            this.adP = str5;
            this.adQ = str6;
        }

        /* access modifiers changed from: protected */
        public boolean a(hz.a aVar) {
            return this.acp.contains(Integer.valueOf(aVar.fI()));
        }

        /* access modifiers changed from: protected */
        public Object aF(String str) {
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean aG(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public Object b(hz.a aVar) {
            switch (aVar.fI()) {
                case 2:
                    return this.acN;
                case 3:
                    return this.adN;
                case 4:
                    return this.acQ;
                case 5:
                    return this.adO;
                case 6:
                    return this.adP;
                case 7:
                    return this.adQ;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
            }
        }

        public int describeContents() {
            la laVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            d dVar = (d) obj;
            for (hz.a next : aco.values()) {
                if (a(next)) {
                    if (!dVar.a(next)) {
                        return false;
                    }
                    if (!b(next).equals(dVar.b(next))) {
                        return false;
                    }
                } else if (dVar.a(next)) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<String, hz.a<?, ?>> fB() {
            return aco;
        }

        public String getFamilyName() {
            return this.acN;
        }

        public String getFormatted() {
            return this.adN;
        }

        public String getGivenName() {
            return this.acQ;
        }

        public String getHonorificPrefix() {
            return this.adO;
        }

        public String getHonorificSuffix() {
            return this.adP;
        }

        public String getMiddleName() {
            return this.adQ;
        }

        /* access modifiers changed from: package-private */
        public int getVersionCode() {
            return this.xJ;
        }

        public boolean hasFamilyName() {
            return this.acp.contains(2);
        }

        public boolean hasFormatted() {
            return this.acp.contains(3);
        }

        public boolean hasGivenName() {
            return this.acp.contains(4);
        }

        public boolean hasHonorificPrefix() {
            return this.acp.contains(5);
        }

        public boolean hasHonorificSuffix() {
            return this.acp.contains(6);
        }

        public boolean hasMiddleName() {
            return this.acp.contains(7);
        }

        public int hashCode() {
            int i = 0;
            Iterator<hz.a<?, ?>> it = aco.values().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                hz.a next = it.next();
                if (a(next)) {
                    i = b(next).hashCode() + i2 + next.fI();
                } else {
                    i = i2;
                }
            }
        }

        public boolean isDataValid() {
            return true;
        }

        /* renamed from: kP */
        public d freeze() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public Set<Integer> kf() {
            return this.acp;
        }

        public void writeToParcel(Parcel out, int flags) {
            la laVar = CREATOR;
            la.a(this, out, flags);
        }
    }

    public static class e {
        public static int bA(String str) {
            if (str.equals(MessagingSmsConsts.PERSON)) {
                return 0;
            }
            if (str.equals("page")) {
                return 1;
            }
            throw new IllegalArgumentException("Unknown objectType string: " + str);
        }
    }

    public static final class f extends hz implements SafeParcelable, Person.Organizations {
        public static final lb CREATOR = new lb();
        private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
        private int AQ;
        private String HV;
        private String Mm;
        private String acM;
        private final Set<Integer> acp;
        private String adR;
        private String adS;
        private boolean adT;
        private String adc;
        private String mName;
        private final int xJ;

        static {
            aco.put("department", hz.a.j("department", 2));
            aco.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, hz.a.j(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, 3));
            aco.put("endDate", hz.a.j("endDate", 4));
            aco.put("location", hz.a.j("location", 5));
            aco.put("name", hz.a.j("name", 6));
            aco.put("primary", hz.a.i("primary", 7));
            aco.put("startDate", hz.a.j("startDate", 8));
            aco.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, hz.a.j(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, 9));
            aco.put(MessagingSmsConsts.TYPE, hz.a.a(MessagingSmsConsts.TYPE, 10, new hw().f("work", 0).f("school", 1), false));
        }

        public f() {
            this.xJ = 1;
            this.acp = new HashSet();
        }

        f(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.acp = set;
            this.xJ = i;
            this.adR = str;
            this.Mm = str2;
            this.acM = str3;
            this.adS = str4;
            this.mName = str5;
            this.adT = z;
            this.adc = str6;
            this.HV = str7;
            this.AQ = i2;
        }

        /* access modifiers changed from: protected */
        public boolean a(hz.a aVar) {
            return this.acp.contains(Integer.valueOf(aVar.fI()));
        }

        /* access modifiers changed from: protected */
        public Object aF(String str) {
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean aG(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public Object b(hz.a aVar) {
            switch (aVar.fI()) {
                case 2:
                    return this.adR;
                case 3:
                    return this.Mm;
                case 4:
                    return this.acM;
                case 5:
                    return this.adS;
                case 6:
                    return this.mName;
                case 7:
                    return Boolean.valueOf(this.adT);
                case 8:
                    return this.adc;
                case 9:
                    return this.HV;
                case 10:
                    return Integer.valueOf(this.AQ);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
            }
        }

        public int describeContents() {
            lb lbVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            f fVar = (f) obj;
            for (hz.a next : aco.values()) {
                if (a(next)) {
                    if (!fVar.a(next)) {
                        return false;
                    }
                    if (!b(next).equals(fVar.b(next))) {
                        return false;
                    }
                } else if (fVar.a(next)) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<String, hz.a<?, ?>> fB() {
            return aco;
        }

        public String getDepartment() {
            return this.adR;
        }

        public String getDescription() {
            return this.Mm;
        }

        public String getEndDate() {
            return this.acM;
        }

        public String getLocation() {
            return this.adS;
        }

        public String getName() {
            return this.mName;
        }

        public String getStartDate() {
            return this.adc;
        }

        public String getTitle() {
            return this.HV;
        }

        public int getType() {
            return this.AQ;
        }

        /* access modifiers changed from: package-private */
        public int getVersionCode() {
            return this.xJ;
        }

        public boolean hasDepartment() {
            return this.acp.contains(2);
        }

        public boolean hasDescription() {
            return this.acp.contains(3);
        }

        public boolean hasEndDate() {
            return this.acp.contains(4);
        }

        public boolean hasLocation() {
            return this.acp.contains(5);
        }

        public boolean hasName() {
            return this.acp.contains(6);
        }

        public boolean hasPrimary() {
            return this.acp.contains(7);
        }

        public boolean hasStartDate() {
            return this.acp.contains(8);
        }

        public boolean hasTitle() {
            return this.acp.contains(9);
        }

        public boolean hasType() {
            return this.acp.contains(10);
        }

        public int hashCode() {
            int i = 0;
            Iterator<hz.a<?, ?>> it = aco.values().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                hz.a next = it.next();
                if (a(next)) {
                    i = b(next).hashCode() + i2 + next.fI();
                } else {
                    i = i2;
                }
            }
        }

        public boolean isDataValid() {
            return true;
        }

        public boolean isPrimary() {
            return this.adT;
        }

        /* renamed from: kQ */
        public f freeze() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public Set<Integer> kf() {
            return this.acp;
        }

        public void writeToParcel(Parcel out, int flags) {
            lb lbVar = CREATOR;
            lb.a(this, out, flags);
        }
    }

    public static final class g extends hz implements SafeParcelable, Person.PlacesLived {
        public static final lc CREATOR = new lc();
        private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
        private final Set<Integer> acp;
        private boolean adT;
        private String mValue;
        private final int xJ;

        static {
            aco.put("primary", hz.a.i("primary", 2));
            aco.put("value", hz.a.j("value", 3));
        }

        public g() {
            this.xJ = 1;
            this.acp = new HashSet();
        }

        g(Set<Integer> set, int i, boolean z, String str) {
            this.acp = set;
            this.xJ = i;
            this.adT = z;
            this.mValue = str;
        }

        /* access modifiers changed from: protected */
        public boolean a(hz.a aVar) {
            return this.acp.contains(Integer.valueOf(aVar.fI()));
        }

        /* access modifiers changed from: protected */
        public Object aF(String str) {
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean aG(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public Object b(hz.a aVar) {
            switch (aVar.fI()) {
                case 2:
                    return Boolean.valueOf(this.adT);
                case 3:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
            }
        }

        public int describeContents() {
            lc lcVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof g)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            g gVar = (g) obj;
            for (hz.a next : aco.values()) {
                if (a(next)) {
                    if (!gVar.a(next)) {
                        return false;
                    }
                    if (!b(next).equals(gVar.b(next))) {
                        return false;
                    }
                } else if (gVar.a(next)) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<String, hz.a<?, ?>> fB() {
            return aco;
        }

        public String getValue() {
            return this.mValue;
        }

        /* access modifiers changed from: package-private */
        public int getVersionCode() {
            return this.xJ;
        }

        public boolean hasPrimary() {
            return this.acp.contains(2);
        }

        public boolean hasValue() {
            return this.acp.contains(3);
        }

        public int hashCode() {
            int i = 0;
            Iterator<hz.a<?, ?>> it = aco.values().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                hz.a next = it.next();
                if (a(next)) {
                    i = b(next).hashCode() + i2 + next.fI();
                } else {
                    i = i2;
                }
            }
        }

        public boolean isDataValid() {
            return true;
        }

        public boolean isPrimary() {
            return this.adT;
        }

        /* renamed from: kR */
        public g freeze() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public Set<Integer> kf() {
            return this.acp;
        }

        public void writeToParcel(Parcel out, int flags) {
            lc lcVar = CREATOR;
            lc.a(this, out, flags);
        }
    }

    public static final class h extends hz implements SafeParcelable, Person.Urls {
        public static final ld CREATOR = new ld();
        private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
        private int AQ;
        private final Set<Integer> acp;
        private String adU;
        private final int adV;
        private String mValue;
        private final int xJ;

        static {
            aco.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, hz.a.j(PlusShare.KEY_CALL_TO_ACTION_LABEL, 5));
            aco.put(MessagingSmsConsts.TYPE, hz.a.a(MessagingSmsConsts.TYPE, 6, new hw().f("home", 0).f("work", 1).f("blog", 2).f(Scopes.PROFILE, 3).f("other", 4).f("otherProfile", 5).f("contributor", 6).f("website", 7), false));
            aco.put("value", hz.a.j("value", 4));
        }

        public h() {
            this.adV = 4;
            this.xJ = 2;
            this.acp = new HashSet();
        }

        h(Set<Integer> set, int i, String str, int i2, String str2, int i3) {
            this.adV = 4;
            this.acp = set;
            this.xJ = i;
            this.adU = str;
            this.AQ = i2;
            this.mValue = str2;
        }

        /* access modifiers changed from: protected */
        public boolean a(hz.a aVar) {
            return this.acp.contains(Integer.valueOf(aVar.fI()));
        }

        /* access modifiers changed from: protected */
        public Object aF(String str) {
            return null;
        }

        /* access modifiers changed from: protected */
        public boolean aG(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public Object b(hz.a aVar) {
            switch (aVar.fI()) {
                case 4:
                    return this.mValue;
                case 5:
                    return this.adU;
                case 6:
                    return Integer.valueOf(this.AQ);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
            }
        }

        public int describeContents() {
            ld ldVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof h)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            h hVar = (h) obj;
            for (hz.a next : aco.values()) {
                if (a(next)) {
                    if (!hVar.a(next)) {
                        return false;
                    }
                    if (!b(next).equals(hVar.b(next))) {
                        return false;
                    }
                } else if (hVar.a(next)) {
                    return false;
                }
            }
            return true;
        }

        public HashMap<String, hz.a<?, ?>> fB() {
            return aco;
        }

        public String getLabel() {
            return this.adU;
        }

        public int getType() {
            return this.AQ;
        }

        public String getValue() {
            return this.mValue;
        }

        /* access modifiers changed from: package-private */
        public int getVersionCode() {
            return this.xJ;
        }

        public boolean hasLabel() {
            return this.acp.contains(5);
        }

        public boolean hasType() {
            return this.acp.contains(6);
        }

        public boolean hasValue() {
            return this.acp.contains(4);
        }

        public int hashCode() {
            int i = 0;
            Iterator<hz.a<?, ?>> it = aco.values().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                hz.a next = it.next();
                if (a(next)) {
                    i = b(next).hashCode() + i2 + next.fI();
                } else {
                    i = i2;
                }
            }
        }

        public boolean isDataValid() {
            return true;
        }

        @Deprecated
        public int kS() {
            return 4;
        }

        /* renamed from: kT */
        public h freeze() {
            return this;
        }

        /* access modifiers changed from: package-private */
        public Set<Integer> kf() {
            return this.acp;
        }

        public void writeToParcel(Parcel out, int flags) {
            ld ldVar = CREATOR;
            ld.a(this, out, flags);
        }
    }

    static {
        aco.put("aboutMe", hz.a.j("aboutMe", 2));
        aco.put("ageRange", hz.a.a("ageRange", 3, a.class));
        aco.put("birthday", hz.a.j("birthday", 4));
        aco.put("braggingRights", hz.a.j("braggingRights", 5));
        aco.put("circledByCount", hz.a.g("circledByCount", 6));
        aco.put("cover", hz.a.a("cover", 7, b.class));
        aco.put("currentLocation", hz.a.j("currentLocation", 8));
        aco.put("displayName", hz.a.j("displayName", 9));
        aco.put("gender", hz.a.a("gender", 12, new hw().f("male", 0).f("female", 1).f("other", 2), false));
        aco.put("id", hz.a.j("id", 14));
        aco.put("image", hz.a.a("image", 15, c.class));
        aco.put("isPlusUser", hz.a.i("isPlusUser", 16));
        aco.put("language", hz.a.j("language", 18));
        aco.put("name", hz.a.a("name", 19, d.class));
        aco.put("nickname", hz.a.j("nickname", 20));
        aco.put("objectType", hz.a.a("objectType", 21, new hw().f(MessagingSmsConsts.PERSON, 0).f("page", 1), false));
        aco.put("organizations", hz.a.b("organizations", 22, f.class));
        aco.put("placesLived", hz.a.b("placesLived", 23, g.class));
        aco.put("plusOneCount", hz.a.g("plusOneCount", 24));
        aco.put("relationshipStatus", hz.a.a("relationshipStatus", 25, new hw().f("single", 0).f("in_a_relationship", 1).f("engaged", 2).f("married", 3).f("its_complicated", 4).f("open_relationship", 5).f("widowed", 6).f("in_domestic_partnership", 7).f("in_civil_union", 8), false));
        aco.put("tagline", hz.a.j("tagline", 26));
        aco.put("url", hz.a.j("url", 27));
        aco.put("urls", hz.a.b("urls", 28, h.class));
        aco.put("verified", hz.a.i("verified", 29));
    }

    public kt() {
        this.xJ = 2;
        this.acp = new HashSet();
    }

    public kt(String str, String str2, c cVar, int i, String str3) {
        this.xJ = 2;
        this.acp = new HashSet();
        this.Lk = str;
        this.acp.add(9);
        this.xD = str2;
        this.acp.add(14);
        this.adu = cVar;
        this.acp.add(15);
        this.ady = i;
        this.acp.add(21);
        this.qV = str3;
        this.acp.add(27);
    }

    kt(Set<Integer> set, int i, String str, a aVar, String str2, String str3, int i2, b bVar, String str4, String str5, int i3, String str6, c cVar, boolean z, String str7, d dVar, String str8, int i4, List<f> list, List<g> list2, int i5, int i6, String str9, String str10, List<h> list3, boolean z2) {
        this.acp = set;
        this.xJ = i;
        this.adn = str;
        this.ado = aVar;
        this.adp = str2;
        this.adq = str3;
        this.adr = i2;
        this.ads = bVar;
        this.adt = str4;
        this.Lk = str5;
        this.mj = i3;
        this.xD = str6;
        this.adu = cVar;
        this.adv = z;
        this.Ao = str7;
        this.adw = dVar;
        this.adx = str8;
        this.ady = i4;
        this.adz = list;
        this.adA = list2;
        this.adB = i5;
        this.adC = i6;
        this.adD = str9;
        this.qV = str10;
        this.adE = list3;
        this.adF = z2;
    }

    public static kt i(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        kt bG = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return bG;
    }

    /* access modifiers changed from: protected */
    public boolean a(hz.a aVar) {
        return this.acp.contains(Integer.valueOf(aVar.fI()));
    }

    /* access modifiers changed from: protected */
    public Object aF(String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean aG(String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public Object b(hz.a aVar) {
        switch (aVar.fI()) {
            case 2:
                return this.adn;
            case 3:
                return this.ado;
            case 4:
                return this.adp;
            case 5:
                return this.adq;
            case 6:
                return Integer.valueOf(this.adr);
            case 7:
                return this.ads;
            case 8:
                return this.adt;
            case 9:
                return this.Lk;
            case 12:
                return Integer.valueOf(this.mj);
            case 14:
                return this.xD;
            case 15:
                return this.adu;
            case 16:
                return Boolean.valueOf(this.adv);
            case 18:
                return this.Ao;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN:
                return this.adw;
            case BrailleInputEvent.CMD_ACTIVATE_CURRENT /*20*/:
                return this.adx;
            case 21:
                return Integer.valueOf(this.ady);
            case 22:
                return this.adz;
            case 23:
                return this.adA;
            case 24:
                return Integer.valueOf(this.adB);
            case 25:
                return Integer.valueOf(this.adC);
            case 26:
                return this.adD;
            case 27:
                return this.qV;
            case 28:
                return this.adE;
            case 29:
                return Boolean.valueOf(this.adF);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
        }
    }

    public int describeContents() {
        ku kuVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof kt)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        kt ktVar = (kt) obj;
        for (hz.a next : aco.values()) {
            if (a(next)) {
                if (!ktVar.a(next)) {
                    return false;
                }
                if (!b(next).equals(ktVar.b(next))) {
                    return false;
                }
            } else if (ktVar.a(next)) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, hz.a<?, ?>> fB() {
        return aco;
    }

    public String getAboutMe() {
        return this.adn;
    }

    public Person.AgeRange getAgeRange() {
        return this.ado;
    }

    public String getBirthday() {
        return this.adp;
    }

    public String getBraggingRights() {
        return this.adq;
    }

    public int getCircledByCount() {
        return this.adr;
    }

    public Person.Cover getCover() {
        return this.ads;
    }

    public String getCurrentLocation() {
        return this.adt;
    }

    public String getDisplayName() {
        return this.Lk;
    }

    public int getGender() {
        return this.mj;
    }

    public String getId() {
        return this.xD;
    }

    public Person.Image getImage() {
        return this.adu;
    }

    public String getLanguage() {
        return this.Ao;
    }

    public Person.Name getName() {
        return this.adw;
    }

    public String getNickname() {
        return this.adx;
    }

    public int getObjectType() {
        return this.ady;
    }

    public List<Person.Organizations> getOrganizations() {
        return (ArrayList) this.adz;
    }

    public List<Person.PlacesLived> getPlacesLived() {
        return (ArrayList) this.adA;
    }

    public int getPlusOneCount() {
        return this.adB;
    }

    public int getRelationshipStatus() {
        return this.adC;
    }

    public String getTagline() {
        return this.adD;
    }

    public String getUrl() {
        return this.qV;
    }

    public List<Person.Urls> getUrls() {
        return (ArrayList) this.adE;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public boolean hasAboutMe() {
        return this.acp.contains(2);
    }

    public boolean hasAgeRange() {
        return this.acp.contains(3);
    }

    public boolean hasBirthday() {
        return this.acp.contains(4);
    }

    public boolean hasBraggingRights() {
        return this.acp.contains(5);
    }

    public boolean hasCircledByCount() {
        return this.acp.contains(6);
    }

    public boolean hasCover() {
        return this.acp.contains(7);
    }

    public boolean hasCurrentLocation() {
        return this.acp.contains(8);
    }

    public boolean hasDisplayName() {
        return this.acp.contains(9);
    }

    public boolean hasGender() {
        return this.acp.contains(12);
    }

    public boolean hasId() {
        return this.acp.contains(14);
    }

    public boolean hasImage() {
        return this.acp.contains(15);
    }

    public boolean hasIsPlusUser() {
        return this.acp.contains(16);
    }

    public boolean hasLanguage() {
        return this.acp.contains(18);
    }

    public boolean hasName() {
        return this.acp.contains(19);
    }

    public boolean hasNickname() {
        return this.acp.contains(20);
    }

    public boolean hasObjectType() {
        return this.acp.contains(21);
    }

    public boolean hasOrganizations() {
        return this.acp.contains(22);
    }

    public boolean hasPlacesLived() {
        return this.acp.contains(23);
    }

    public boolean hasPlusOneCount() {
        return this.acp.contains(24);
    }

    public boolean hasRelationshipStatus() {
        return this.acp.contains(25);
    }

    public boolean hasTagline() {
        return this.acp.contains(26);
    }

    public boolean hasUrl() {
        return this.acp.contains(27);
    }

    public boolean hasUrls() {
        return this.acp.contains(28);
    }

    public boolean hasVerified() {
        return this.acp.contains(29);
    }

    public int hashCode() {
        int i = 0;
        Iterator<hz.a<?, ?>> it = aco.values().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            hz.a next = it.next();
            if (a(next)) {
                i = b(next).hashCode() + i2 + next.fI();
            } else {
                i = i2;
            }
        }
    }

    public boolean isDataValid() {
        return true;
    }

    public boolean isPlusUser() {
        return this.adv;
    }

    public boolean isVerified() {
        return this.adF;
    }

    /* access modifiers changed from: package-private */
    public a kA() {
        return this.ado;
    }

    /* access modifiers changed from: package-private */
    public b kB() {
        return this.ads;
    }

    /* access modifiers changed from: package-private */
    public c kC() {
        return this.adu;
    }

    /* access modifiers changed from: package-private */
    public d kD() {
        return this.adw;
    }

    /* access modifiers changed from: package-private */
    public List<f> kE() {
        return this.adz;
    }

    /* access modifiers changed from: package-private */
    public List<g> kF() {
        return this.adA;
    }

    /* access modifiers changed from: package-private */
    public List<h> kG() {
        return this.adE;
    }

    /* renamed from: kH */
    public kt freeze() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public Set<Integer> kf() {
        return this.acp;
    }

    public void writeToParcel(Parcel out, int flags) {
        ku kuVar = CREATOR;
        ku.a(this, out, flags);
    }
}
