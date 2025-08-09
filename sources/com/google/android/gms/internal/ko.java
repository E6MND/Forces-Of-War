package com.google.android.gms.internal;

import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hz;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public final class ko extends hz implements SafeParcelable, ItemScope {
    public static final kp CREATOR = new kp();
    private static final HashMap<String, hz.a<?, ?>> aco = new HashMap<>();
    private String Mm;
    private double Va;
    private double Vb;
    private List<ko> acA;
    private String acB;
    private String acC;
    private ko acD;
    private String acE;
    private String acF;
    private List<ko> acG;
    private String acH;
    private String acI;
    private String acJ;
    private String acK;
    private String acL;
    private String acM;
    private String acN;
    private String acO;
    private ko acP;
    private String acQ;
    private String acR;
    private String acS;
    private ko acT;
    private ko acU;
    private ko acV;
    private List<ko> acW;
    private String acX;
    private String acY;
    private String acZ;
    private final Set<Integer> acp;
    private ko acq;
    private List<String> acr;
    private ko acs;
    private String act;
    private String acu;
    private String acv;
    private List<ko> acw;
    private int acx;
    private List<ko> acy;
    private ko acz;
    private String ada;
    private ko adb;
    private String adc;
    private String add;
    private String ade;
    private ko adf;
    private String adg;
    private String adh;
    private String adi;
    private String adj;
    private String mName;
    private String mi;
    private String qU;
    private String qV;
    private String xD;
    private final int xJ;

    static {
        aco.put("about", hz.a.a("about", 2, ko.class));
        aco.put("additionalName", hz.a.k("additionalName", 3));
        aco.put(MessagingSmsConsts.ADDRESS, hz.a.a(MessagingSmsConsts.ADDRESS, 4, ko.class));
        aco.put("addressCountry", hz.a.j("addressCountry", 5));
        aco.put("addressLocality", hz.a.j("addressLocality", 6));
        aco.put("addressRegion", hz.a.j("addressRegion", 7));
        aco.put("associated_media", hz.a.b("associated_media", 8, ko.class));
        aco.put("attendeeCount", hz.a.g("attendeeCount", 9));
        aco.put("attendees", hz.a.b("attendees", 10, ko.class));
        aco.put("audio", hz.a.a("audio", 11, ko.class));
        aco.put("author", hz.a.b("author", 12, ko.class));
        aco.put("bestRating", hz.a.j("bestRating", 13));
        aco.put("birthDate", hz.a.j("birthDate", 14));
        aco.put("byArtist", hz.a.a("byArtist", 15, ko.class));
        aco.put("caption", hz.a.j("caption", 16));
        aco.put("contentSize", hz.a.j("contentSize", 17));
        aco.put("contentUrl", hz.a.j("contentUrl", 18));
        aco.put("contributor", hz.a.b("contributor", 19, ko.class));
        aco.put("dateCreated", hz.a.j("dateCreated", 20));
        aco.put("dateModified", hz.a.j("dateModified", 21));
        aco.put("datePublished", hz.a.j("datePublished", 22));
        aco.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, hz.a.j(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, 23));
        aco.put("duration", hz.a.j("duration", 24));
        aco.put("embedUrl", hz.a.j("embedUrl", 25));
        aco.put("endDate", hz.a.j("endDate", 26));
        aco.put("familyName", hz.a.j("familyName", 27));
        aco.put("gender", hz.a.j("gender", 28));
        aco.put("geo", hz.a.a("geo", 29, ko.class));
        aco.put("givenName", hz.a.j("givenName", 30));
        aco.put("height", hz.a.j("height", 31));
        aco.put("id", hz.a.j("id", 32));
        aco.put("image", hz.a.j("image", 33));
        aco.put("inAlbum", hz.a.a("inAlbum", 34, ko.class));
        aco.put("latitude", hz.a.h("latitude", 36));
        aco.put("location", hz.a.a("location", 37, ko.class));
        aco.put("longitude", hz.a.h("longitude", 38));
        aco.put("name", hz.a.j("name", 39));
        aco.put("partOfTVSeries", hz.a.a("partOfTVSeries", 40, ko.class));
        aco.put("performers", hz.a.b("performers", 41, ko.class));
        aco.put("playerType", hz.a.j("playerType", 42));
        aco.put("postOfficeBoxNumber", hz.a.j("postOfficeBoxNumber", 43));
        aco.put("postalCode", hz.a.j("postalCode", 44));
        aco.put("ratingValue", hz.a.j("ratingValue", 45));
        aco.put("reviewRating", hz.a.a("reviewRating", 46, ko.class));
        aco.put("startDate", hz.a.j("startDate", 47));
        aco.put("streetAddress", hz.a.j("streetAddress", 48));
        aco.put("text", hz.a.j("text", 49));
        aco.put("thumbnail", hz.a.a("thumbnail", 50, ko.class));
        aco.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL, hz.a.j(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL, 51));
        aco.put("tickerSymbol", hz.a.j("tickerSymbol", 52));
        aco.put(MessagingSmsConsts.TYPE, hz.a.j(MessagingSmsConsts.TYPE, 53));
        aco.put("url", hz.a.j("url", 54));
        aco.put("width", hz.a.j("width", 55));
        aco.put("worstRating", hz.a.j("worstRating", 56));
    }

    public ko() {
        this.xJ = 1;
        this.acp = new HashSet();
    }

    ko(Set<Integer> set, int i, ko koVar, List<String> list, ko koVar2, String str, String str2, String str3, List<ko> list2, int i2, List<ko> list3, ko koVar3, List<ko> list4, String str4, String str5, ko koVar4, String str6, String str7, String str8, List<ko> list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, ko koVar5, String str18, String str19, String str20, String str21, ko koVar6, double d, ko koVar7, double d2, String str22, ko koVar8, List<ko> list6, String str23, String str24, String str25, String str26, ko koVar9, String str27, String str28, String str29, ko koVar10, String str30, String str31, String str32, String str33, String str34, String str35) {
        this.acp = set;
        this.xJ = i;
        this.acq = koVar;
        this.acr = list;
        this.acs = koVar2;
        this.act = str;
        this.acu = str2;
        this.acv = str3;
        this.acw = list2;
        this.acx = i2;
        this.acy = list3;
        this.acz = koVar3;
        this.acA = list4;
        this.acB = str4;
        this.acC = str5;
        this.acD = koVar4;
        this.acE = str6;
        this.acF = str7;
        this.mi = str8;
        this.acG = list5;
        this.acH = str9;
        this.acI = str10;
        this.acJ = str11;
        this.Mm = str12;
        this.acK = str13;
        this.acL = str14;
        this.acM = str15;
        this.acN = str16;
        this.acO = str17;
        this.acP = koVar5;
        this.acQ = str18;
        this.acR = str19;
        this.xD = str20;
        this.acS = str21;
        this.acT = koVar6;
        this.Va = d;
        this.acU = koVar7;
        this.Vb = d2;
        this.mName = str22;
        this.acV = koVar8;
        this.acW = list6;
        this.acX = str23;
        this.acY = str24;
        this.acZ = str25;
        this.ada = str26;
        this.adb = koVar9;
        this.adc = str27;
        this.add = str28;
        this.ade = str29;
        this.adf = koVar10;
        this.adg = str30;
        this.adh = str31;
        this.qU = str32;
        this.qV = str33;
        this.adi = str34;
        this.adj = str35;
    }

    public ko(Set<Integer> set, ko koVar, List<String> list, ko koVar2, String str, String str2, String str3, List<ko> list2, int i, List<ko> list3, ko koVar3, List<ko> list4, String str4, String str5, ko koVar4, String str6, String str7, String str8, List<ko> list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, ko koVar5, String str18, String str19, String str20, String str21, ko koVar6, double d, ko koVar7, double d2, String str22, ko koVar8, List<ko> list6, String str23, String str24, String str25, String str26, ko koVar9, String str27, String str28, String str29, ko koVar10, String str30, String str31, String str32, String str33, String str34, String str35) {
        this.acp = set;
        this.xJ = 1;
        this.acq = koVar;
        this.acr = list;
        this.acs = koVar2;
        this.act = str;
        this.acu = str2;
        this.acv = str3;
        this.acw = list2;
        this.acx = i;
        this.acy = list3;
        this.acz = koVar3;
        this.acA = list4;
        this.acB = str4;
        this.acC = str5;
        this.acD = koVar4;
        this.acE = str6;
        this.acF = str7;
        this.mi = str8;
        this.acG = list5;
        this.acH = str9;
        this.acI = str10;
        this.acJ = str11;
        this.Mm = str12;
        this.acK = str13;
        this.acL = str14;
        this.acM = str15;
        this.acN = str16;
        this.acO = str17;
        this.acP = koVar5;
        this.acQ = str18;
        this.acR = str19;
        this.xD = str20;
        this.acS = str21;
        this.acT = koVar6;
        this.Va = d;
        this.acU = koVar7;
        this.Vb = d2;
        this.mName = str22;
        this.acV = koVar8;
        this.acW = list6;
        this.acX = str23;
        this.acY = str24;
        this.acZ = str25;
        this.ada = str26;
        this.adb = koVar9;
        this.adc = str27;
        this.add = str28;
        this.ade = str29;
        this.adf = koVar10;
        this.adg = str30;
        this.adh = str31;
        this.qU = str32;
        this.qV = str33;
        this.adi = str34;
        this.adj = str35;
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
                return this.acq;
            case 3:
                return this.acr;
            case 4:
                return this.acs;
            case 5:
                return this.act;
            case 6:
                return this.acu;
            case 7:
                return this.acv;
            case 8:
                return this.acw;
            case 9:
                return Integer.valueOf(this.acx);
            case 10:
                return this.acy;
            case 11:
                return this.acz;
            case 12:
                return this.acA;
            case 13:
                return this.acB;
            case 14:
                return this.acC;
            case 15:
                return this.acD;
            case 16:
                return this.acE;
            case 17:
                return this.acF;
            case 18:
                return this.mi;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN:
                return this.acG;
            case BrailleInputEvent.CMD_ACTIVATE_CURRENT /*20*/:
                return this.acH;
            case 21:
                return this.acI;
            case 22:
                return this.acJ;
            case 23:
                return this.Mm;
            case 24:
                return this.acK;
            case 25:
                return this.acL;
            case 26:
                return this.acM;
            case 27:
                return this.acN;
            case 28:
                return this.acO;
            case 29:
                return this.acP;
            case BrailleInputEvent.CMD_SCROLL_BACKWARD /*30*/:
                return this.acQ;
            case 31:
                return this.acR;
            case 32:
                return this.xD;
            case 33:
                return this.acS;
            case 34:
                return this.acT;
            case 36:
                return Double.valueOf(this.Va);
            case 37:
                return this.acU;
            case 38:
                return Double.valueOf(this.Vb);
            case 39:
                return this.mName;
            case BrailleInputEvent.CMD_SELECTION_START /*40*/:
                return this.acV;
            case BrailleInputEvent.CMD_SELECTION_END /*41*/:
                return this.acW;
            case BrailleInputEvent.CMD_SELECTION_SELECT_ALL /*42*/:
                return this.acX;
            case BrailleInputEvent.CMD_SELECTION_CUT /*43*/:
                return this.acY;
            case BrailleInputEvent.CMD_SELECTION_COPY /*44*/:
                return this.acZ;
            case BrailleInputEvent.CMD_SELECTION_PASTE /*45*/:
                return this.ada;
            case 46:
                return this.adb;
            case 47:
                return this.adc;
            case 48:
                return this.add;
            case 49:
                return this.ade;
            case 50:
                return this.adf;
            case 51:
                return this.adg;
            case 52:
                return this.adh;
            case 53:
                return this.qU;
            case 54:
                return this.qV;
            case 55:
                return this.adi;
            case 56:
                return this.adj;
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.fI());
        }
    }

    public int describeContents() {
        kp kpVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ko)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ko koVar = (ko) obj;
        for (hz.a next : aco.values()) {
            if (a(next)) {
                if (!koVar.a(next)) {
                    return false;
                }
                if (!b(next).equals(koVar.b(next))) {
                    return false;
                }
            } else if (koVar.a(next)) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, hz.a<?, ?>> fB() {
        return aco;
    }

    public ItemScope getAbout() {
        return this.acq;
    }

    public List<String> getAdditionalName() {
        return this.acr;
    }

    public ItemScope getAddress() {
        return this.acs;
    }

    public String getAddressCountry() {
        return this.act;
    }

    public String getAddressLocality() {
        return this.acu;
    }

    public String getAddressRegion() {
        return this.acv;
    }

    public List<ItemScope> getAssociated_media() {
        return (ArrayList) this.acw;
    }

    public int getAttendeeCount() {
        return this.acx;
    }

    public List<ItemScope> getAttendees() {
        return (ArrayList) this.acy;
    }

    public ItemScope getAudio() {
        return this.acz;
    }

    public List<ItemScope> getAuthor() {
        return (ArrayList) this.acA;
    }

    public String getBestRating() {
        return this.acB;
    }

    public String getBirthDate() {
        return this.acC;
    }

    public ItemScope getByArtist() {
        return this.acD;
    }

    public String getCaption() {
        return this.acE;
    }

    public String getContentSize() {
        return this.acF;
    }

    public String getContentUrl() {
        return this.mi;
    }

    public List<ItemScope> getContributor() {
        return (ArrayList) this.acG;
    }

    public String getDateCreated() {
        return this.acH;
    }

    public String getDateModified() {
        return this.acI;
    }

    public String getDatePublished() {
        return this.acJ;
    }

    public String getDescription() {
        return this.Mm;
    }

    public String getDuration() {
        return this.acK;
    }

    public String getEmbedUrl() {
        return this.acL;
    }

    public String getEndDate() {
        return this.acM;
    }

    public String getFamilyName() {
        return this.acN;
    }

    public String getGender() {
        return this.acO;
    }

    public ItemScope getGeo() {
        return this.acP;
    }

    public String getGivenName() {
        return this.acQ;
    }

    public String getHeight() {
        return this.acR;
    }

    public String getId() {
        return this.xD;
    }

    public String getImage() {
        return this.acS;
    }

    public ItemScope getInAlbum() {
        return this.acT;
    }

    public double getLatitude() {
        return this.Va;
    }

    public ItemScope getLocation() {
        return this.acU;
    }

    public double getLongitude() {
        return this.Vb;
    }

    public String getName() {
        return this.mName;
    }

    public ItemScope getPartOfTVSeries() {
        return this.acV;
    }

    public List<ItemScope> getPerformers() {
        return (ArrayList) this.acW;
    }

    public String getPlayerType() {
        return this.acX;
    }

    public String getPostOfficeBoxNumber() {
        return this.acY;
    }

    public String getPostalCode() {
        return this.acZ;
    }

    public String getRatingValue() {
        return this.ada;
    }

    public ItemScope getReviewRating() {
        return this.adb;
    }

    public String getStartDate() {
        return this.adc;
    }

    public String getStreetAddress() {
        return this.add;
    }

    public String getText() {
        return this.ade;
    }

    public ItemScope getThumbnail() {
        return this.adf;
    }

    public String getThumbnailUrl() {
        return this.adg;
    }

    public String getTickerSymbol() {
        return this.adh;
    }

    public String getType() {
        return this.qU;
    }

    public String getUrl() {
        return this.qV;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public String getWidth() {
        return this.adi;
    }

    public String getWorstRating() {
        return this.adj;
    }

    public boolean hasAbout() {
        return this.acp.contains(2);
    }

    public boolean hasAdditionalName() {
        return this.acp.contains(3);
    }

    public boolean hasAddress() {
        return this.acp.contains(4);
    }

    public boolean hasAddressCountry() {
        return this.acp.contains(5);
    }

    public boolean hasAddressLocality() {
        return this.acp.contains(6);
    }

    public boolean hasAddressRegion() {
        return this.acp.contains(7);
    }

    public boolean hasAssociated_media() {
        return this.acp.contains(8);
    }

    public boolean hasAttendeeCount() {
        return this.acp.contains(9);
    }

    public boolean hasAttendees() {
        return this.acp.contains(10);
    }

    public boolean hasAudio() {
        return this.acp.contains(11);
    }

    public boolean hasAuthor() {
        return this.acp.contains(12);
    }

    public boolean hasBestRating() {
        return this.acp.contains(13);
    }

    public boolean hasBirthDate() {
        return this.acp.contains(14);
    }

    public boolean hasByArtist() {
        return this.acp.contains(15);
    }

    public boolean hasCaption() {
        return this.acp.contains(16);
    }

    public boolean hasContentSize() {
        return this.acp.contains(17);
    }

    public boolean hasContentUrl() {
        return this.acp.contains(18);
    }

    public boolean hasContributor() {
        return this.acp.contains(19);
    }

    public boolean hasDateCreated() {
        return this.acp.contains(20);
    }

    public boolean hasDateModified() {
        return this.acp.contains(21);
    }

    public boolean hasDatePublished() {
        return this.acp.contains(22);
    }

    public boolean hasDescription() {
        return this.acp.contains(23);
    }

    public boolean hasDuration() {
        return this.acp.contains(24);
    }

    public boolean hasEmbedUrl() {
        return this.acp.contains(25);
    }

    public boolean hasEndDate() {
        return this.acp.contains(26);
    }

    public boolean hasFamilyName() {
        return this.acp.contains(27);
    }

    public boolean hasGender() {
        return this.acp.contains(28);
    }

    public boolean hasGeo() {
        return this.acp.contains(29);
    }

    public boolean hasGivenName() {
        return this.acp.contains(30);
    }

    public boolean hasHeight() {
        return this.acp.contains(31);
    }

    public boolean hasId() {
        return this.acp.contains(32);
    }

    public boolean hasImage() {
        return this.acp.contains(33);
    }

    public boolean hasInAlbum() {
        return this.acp.contains(34);
    }

    public boolean hasLatitude() {
        return this.acp.contains(36);
    }

    public boolean hasLocation() {
        return this.acp.contains(37);
    }

    public boolean hasLongitude() {
        return this.acp.contains(38);
    }

    public boolean hasName() {
        return this.acp.contains(39);
    }

    public boolean hasPartOfTVSeries() {
        return this.acp.contains(40);
    }

    public boolean hasPerformers() {
        return this.acp.contains(41);
    }

    public boolean hasPlayerType() {
        return this.acp.contains(42);
    }

    public boolean hasPostOfficeBoxNumber() {
        return this.acp.contains(43);
    }

    public boolean hasPostalCode() {
        return this.acp.contains(44);
    }

    public boolean hasRatingValue() {
        return this.acp.contains(45);
    }

    public boolean hasReviewRating() {
        return this.acp.contains(46);
    }

    public boolean hasStartDate() {
        return this.acp.contains(47);
    }

    public boolean hasStreetAddress() {
        return this.acp.contains(48);
    }

    public boolean hasText() {
        return this.acp.contains(49);
    }

    public boolean hasThumbnail() {
        return this.acp.contains(50);
    }

    public boolean hasThumbnailUrl() {
        return this.acp.contains(51);
    }

    public boolean hasTickerSymbol() {
        return this.acp.contains(52);
    }

    public boolean hasType() {
        return this.acp.contains(53);
    }

    public boolean hasUrl() {
        return this.acp.contains(54);
    }

    public boolean hasWidth() {
        return this.acp.contains(55);
    }

    public boolean hasWorstRating() {
        return this.acp.contains(56);
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
    public Set<Integer> kf() {
        return this.acp;
    }

    /* access modifiers changed from: package-private */
    public ko kg() {
        return this.acq;
    }

    /* access modifiers changed from: package-private */
    public ko kh() {
        return this.acs;
    }

    /* access modifiers changed from: package-private */
    public List<ko> ki() {
        return this.acw;
    }

    /* access modifiers changed from: package-private */
    public List<ko> kj() {
        return this.acy;
    }

    /* access modifiers changed from: package-private */
    public ko kk() {
        return this.acz;
    }

    /* access modifiers changed from: package-private */
    public List<ko> kl() {
        return this.acA;
    }

    /* access modifiers changed from: package-private */
    public ko km() {
        return this.acD;
    }

    /* access modifiers changed from: package-private */
    public List<ko> kn() {
        return this.acG;
    }

    /* access modifiers changed from: package-private */
    public ko ko() {
        return this.acP;
    }

    /* access modifiers changed from: package-private */
    public ko kp() {
        return this.acT;
    }

    /* access modifiers changed from: package-private */
    public ko kq() {
        return this.acU;
    }

    /* access modifiers changed from: package-private */
    public ko kr() {
        return this.acV;
    }

    /* access modifiers changed from: package-private */
    public List<ko> ks() {
        return this.acW;
    }

    /* access modifiers changed from: package-private */
    public ko kt() {
        return this.adb;
    }

    /* access modifiers changed from: package-private */
    public ko ku() {
        return this.adf;
    }

    /* renamed from: kv */
    public ko freeze() {
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        kp kpVar = CREATOR;
        kp.a(this, out, flags);
    }
}
