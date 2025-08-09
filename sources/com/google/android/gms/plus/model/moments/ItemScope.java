package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.ko;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ItemScope extends Freezable<ItemScope> {

    public static class Builder {
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
        private final Set<Integer> acp = new HashSet();
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

        public ItemScope build() {
            return new ko(this.acp, this.acq, this.acr, this.acs, this.act, this.acu, this.acv, this.acw, this.acx, this.acy, this.acz, this.acA, this.acB, this.acC, this.acD, this.acE, this.acF, this.mi, this.acG, this.acH, this.acI, this.acJ, this.Mm, this.acK, this.acL, this.acM, this.acN, this.acO, this.acP, this.acQ, this.acR, this.xD, this.acS, this.acT, this.Va, this.acU, this.Vb, this.mName, this.acV, this.acW, this.acX, this.acY, this.acZ, this.ada, this.adb, this.adc, this.add, this.ade, this.adf, this.adg, this.adh, this.qU, this.qV, this.adi, this.adj);
        }

        public Builder setAbout(ItemScope about) {
            this.acq = (ko) about;
            this.acp.add(2);
            return this;
        }

        public Builder setAdditionalName(List<String> additionalName) {
            this.acr = additionalName;
            this.acp.add(3);
            return this;
        }

        public Builder setAddress(ItemScope address) {
            this.acs = (ko) address;
            this.acp.add(4);
            return this;
        }

        public Builder setAddressCountry(String addressCountry) {
            this.act = addressCountry;
            this.acp.add(5);
            return this;
        }

        public Builder setAddressLocality(String addressLocality) {
            this.acu = addressLocality;
            this.acp.add(6);
            return this;
        }

        public Builder setAddressRegion(String addressRegion) {
            this.acv = addressRegion;
            this.acp.add(7);
            return this;
        }

        public Builder setAssociated_media(List<ItemScope> associated_media) {
            this.acw = associated_media;
            this.acp.add(8);
            return this;
        }

        public Builder setAttendeeCount(int attendeeCount) {
            this.acx = attendeeCount;
            this.acp.add(9);
            return this;
        }

        public Builder setAttendees(List<ItemScope> attendees) {
            this.acy = attendees;
            this.acp.add(10);
            return this;
        }

        public Builder setAudio(ItemScope audio) {
            this.acz = (ko) audio;
            this.acp.add(11);
            return this;
        }

        public Builder setAuthor(List<ItemScope> author) {
            this.acA = author;
            this.acp.add(12);
            return this;
        }

        public Builder setBestRating(String bestRating) {
            this.acB = bestRating;
            this.acp.add(13);
            return this;
        }

        public Builder setBirthDate(String birthDate) {
            this.acC = birthDate;
            this.acp.add(14);
            return this;
        }

        public Builder setByArtist(ItemScope byArtist) {
            this.acD = (ko) byArtist;
            this.acp.add(15);
            return this;
        }

        public Builder setCaption(String caption) {
            this.acE = caption;
            this.acp.add(16);
            return this;
        }

        public Builder setContentSize(String contentSize) {
            this.acF = contentSize;
            this.acp.add(17);
            return this;
        }

        public Builder setContentUrl(String contentUrl) {
            this.mi = contentUrl;
            this.acp.add(18);
            return this;
        }

        public Builder setContributor(List<ItemScope> contributor) {
            this.acG = contributor;
            this.acp.add(19);
            return this;
        }

        public Builder setDateCreated(String dateCreated) {
            this.acH = dateCreated;
            this.acp.add(20);
            return this;
        }

        public Builder setDateModified(String dateModified) {
            this.acI = dateModified;
            this.acp.add(21);
            return this;
        }

        public Builder setDatePublished(String datePublished) {
            this.acJ = datePublished;
            this.acp.add(22);
            return this;
        }

        public Builder setDescription(String description) {
            this.Mm = description;
            this.acp.add(23);
            return this;
        }

        public Builder setDuration(String duration) {
            this.acK = duration;
            this.acp.add(24);
            return this;
        }

        public Builder setEmbedUrl(String embedUrl) {
            this.acL = embedUrl;
            this.acp.add(25);
            return this;
        }

        public Builder setEndDate(String endDate) {
            this.acM = endDate;
            this.acp.add(26);
            return this;
        }

        public Builder setFamilyName(String familyName) {
            this.acN = familyName;
            this.acp.add(27);
            return this;
        }

        public Builder setGender(String gender) {
            this.acO = gender;
            this.acp.add(28);
            return this;
        }

        public Builder setGeo(ItemScope geo) {
            this.acP = (ko) geo;
            this.acp.add(29);
            return this;
        }

        public Builder setGivenName(String givenName) {
            this.acQ = givenName;
            this.acp.add(30);
            return this;
        }

        public Builder setHeight(String height) {
            this.acR = height;
            this.acp.add(31);
            return this;
        }

        public Builder setId(String id) {
            this.xD = id;
            this.acp.add(32);
            return this;
        }

        public Builder setImage(String image) {
            this.acS = image;
            this.acp.add(33);
            return this;
        }

        public Builder setInAlbum(ItemScope inAlbum) {
            this.acT = (ko) inAlbum;
            this.acp.add(34);
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.Va = latitude;
            this.acp.add(36);
            return this;
        }

        public Builder setLocation(ItemScope location) {
            this.acU = (ko) location;
            this.acp.add(37);
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.Vb = longitude;
            this.acp.add(38);
            return this;
        }

        public Builder setName(String name) {
            this.mName = name;
            this.acp.add(39);
            return this;
        }

        public Builder setPartOfTVSeries(ItemScope partOfTVSeries) {
            this.acV = (ko) partOfTVSeries;
            this.acp.add(40);
            return this;
        }

        public Builder setPerformers(List<ItemScope> performers) {
            this.acW = performers;
            this.acp.add(41);
            return this;
        }

        public Builder setPlayerType(String playerType) {
            this.acX = playerType;
            this.acp.add(42);
            return this;
        }

        public Builder setPostOfficeBoxNumber(String postOfficeBoxNumber) {
            this.acY = postOfficeBoxNumber;
            this.acp.add(43);
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.acZ = postalCode;
            this.acp.add(44);
            return this;
        }

        public Builder setRatingValue(String ratingValue) {
            this.ada = ratingValue;
            this.acp.add(45);
            return this;
        }

        public Builder setReviewRating(ItemScope reviewRating) {
            this.adb = (ko) reviewRating;
            this.acp.add(46);
            return this;
        }

        public Builder setStartDate(String startDate) {
            this.adc = startDate;
            this.acp.add(47);
            return this;
        }

        public Builder setStreetAddress(String streetAddress) {
            this.add = streetAddress;
            this.acp.add(48);
            return this;
        }

        public Builder setText(String text) {
            this.ade = text;
            this.acp.add(49);
            return this;
        }

        public Builder setThumbnail(ItemScope thumbnail) {
            this.adf = (ko) thumbnail;
            this.acp.add(50);
            return this;
        }

        public Builder setThumbnailUrl(String thumbnailUrl) {
            this.adg = thumbnailUrl;
            this.acp.add(51);
            return this;
        }

        public Builder setTickerSymbol(String tickerSymbol) {
            this.adh = tickerSymbol;
            this.acp.add(52);
            return this;
        }

        public Builder setType(String type) {
            this.qU = type;
            this.acp.add(53);
            return this;
        }

        public Builder setUrl(String url) {
            this.qV = url;
            this.acp.add(54);
            return this;
        }

        public Builder setWidth(String width) {
            this.adi = width;
            this.acp.add(55);
            return this;
        }

        public Builder setWorstRating(String worstRating) {
            this.adj = worstRating;
            this.acp.add(56);
            return this;
        }
    }

    ItemScope getAbout();

    List<String> getAdditionalName();

    ItemScope getAddress();

    String getAddressCountry();

    String getAddressLocality();

    String getAddressRegion();

    List<ItemScope> getAssociated_media();

    int getAttendeeCount();

    List<ItemScope> getAttendees();

    ItemScope getAudio();

    List<ItemScope> getAuthor();

    String getBestRating();

    String getBirthDate();

    ItemScope getByArtist();

    String getCaption();

    String getContentSize();

    String getContentUrl();

    List<ItemScope> getContributor();

    String getDateCreated();

    String getDateModified();

    String getDatePublished();

    String getDescription();

    String getDuration();

    String getEmbedUrl();

    String getEndDate();

    String getFamilyName();

    String getGender();

    ItemScope getGeo();

    String getGivenName();

    String getHeight();

    String getId();

    String getImage();

    ItemScope getInAlbum();

    double getLatitude();

    ItemScope getLocation();

    double getLongitude();

    String getName();

    ItemScope getPartOfTVSeries();

    List<ItemScope> getPerformers();

    String getPlayerType();

    String getPostOfficeBoxNumber();

    String getPostalCode();

    String getRatingValue();

    ItemScope getReviewRating();

    String getStartDate();

    String getStreetAddress();

    String getText();

    ItemScope getThumbnail();

    String getThumbnailUrl();

    String getTickerSymbol();

    String getType();

    String getUrl();

    String getWidth();

    String getWorstRating();

    boolean hasAbout();

    boolean hasAdditionalName();

    boolean hasAddress();

    boolean hasAddressCountry();

    boolean hasAddressLocality();

    boolean hasAddressRegion();

    boolean hasAssociated_media();

    boolean hasAttendeeCount();

    boolean hasAttendees();

    boolean hasAudio();

    boolean hasAuthor();

    boolean hasBestRating();

    boolean hasBirthDate();

    boolean hasByArtist();

    boolean hasCaption();

    boolean hasContentSize();

    boolean hasContentUrl();

    boolean hasContributor();

    boolean hasDateCreated();

    boolean hasDateModified();

    boolean hasDatePublished();

    boolean hasDescription();

    boolean hasDuration();

    boolean hasEmbedUrl();

    boolean hasEndDate();

    boolean hasFamilyName();

    boolean hasGender();

    boolean hasGeo();

    boolean hasGivenName();

    boolean hasHeight();

    boolean hasId();

    boolean hasImage();

    boolean hasInAlbum();

    boolean hasLatitude();

    boolean hasLocation();

    boolean hasLongitude();

    boolean hasName();

    boolean hasPartOfTVSeries();

    boolean hasPerformers();

    boolean hasPlayerType();

    boolean hasPostOfficeBoxNumber();

    boolean hasPostalCode();

    boolean hasRatingValue();

    boolean hasReviewRating();

    boolean hasStartDate();

    boolean hasStreetAddress();

    boolean hasText();

    boolean hasThumbnail();

    boolean hasThumbnailUrl();

    boolean hasTickerSymbol();

    boolean hasType();

    boolean hasUrl();

    boolean hasWidth();

    boolean hasWorstRating();
}
