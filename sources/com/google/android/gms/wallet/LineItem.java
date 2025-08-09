package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LineItem implements SafeParcelable {
    public static final Parcelable.Creator<LineItem> CREATOR = new i();
    String aiH;
    String aiI;
    String ajb;
    String ajc;
    int ajd;
    String description;
    private final int xJ;

    public final class Builder {
        private Builder() {
        }

        public LineItem build() {
            return LineItem.this;
        }

        public Builder setCurrencyCode(String currencyCode) {
            LineItem.this.aiI = currencyCode;
            return this;
        }

        public Builder setDescription(String description) {
            LineItem.this.description = description;
            return this;
        }

        public Builder setQuantity(String quantity) {
            LineItem.this.ajb = quantity;
            return this;
        }

        public Builder setRole(int role) {
            LineItem.this.ajd = role;
            return this;
        }

        public Builder setTotalPrice(String totalPrice) {
            LineItem.this.aiH = totalPrice;
            return this;
        }

        public Builder setUnitPrice(String unitPrice) {
            LineItem.this.ajc = unitPrice;
            return this;
        }
    }

    public interface Role {
        public static final int REGULAR = 0;
        public static final int SHIPPING = 2;
        public static final int TAX = 1;
    }

    LineItem() {
        this.xJ = 1;
        this.ajd = 0;
    }

    LineItem(int versionCode, String description2, String quantity, String unitPrice, String totalPrice, int role, String currencyCode) {
        this.xJ = versionCode;
        this.description = description2;
        this.ajb = quantity;
        this.ajc = unitPrice;
        this.aiH = totalPrice;
        this.ajd = role;
        this.aiI = currencyCode;
    }

    public static Builder newBuilder() {
        LineItem lineItem = new LineItem();
        lineItem.getClass();
        return new Builder();
    }

    public int describeContents() {
        return 0;
    }

    public String getCurrencyCode() {
        return this.aiI;
    }

    public String getDescription() {
        return this.description;
    }

    public String getQuantity() {
        return this.ajb;
    }

    public int getRole() {
        return this.ajd;
    }

    public String getTotalPrice() {
        return this.aiH;
    }

    public String getUnitPrice() {
        return this.ajc;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
