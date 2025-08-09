package com.uken.android.util;

import com.google.android.gms.plus.PlusShare;
import com.uken.android.common.Utils;
import java.text.NumberFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class SkuDetails {
    String mDescription;
    String mItemType;
    String mJson;
    String mPrice;
    double mPriceAmount;
    int mPriceAmountMicros;
    String mPriceCurrencyCode;
    String mSku;
    double mTaxAmount;
    String mTitle;
    double mTotalPrice;
    String mType;

    public SkuDetails(String jsonSkuDetails) throws JSONException {
        this(IabHelper.ITEM_TYPE_INAPP, jsonSkuDetails);
    }

    public SkuDetails(String itemType, String jsonSkuDetails) throws JSONException {
        this.mItemType = itemType;
        this.mJson = jsonSkuDetails;
        JSONObject o = new JSONObject(this.mJson);
        this.mSku = o.optString("productId");
        this.mType = o.optString(MessagingSmsConsts.TYPE);
        this.mPrice = o.optString("price");
        this.mTitle = o.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
        this.mDescription = o.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
        this.mPriceCurrencyCode = o.optString("price_currency_code");
        this.mPriceAmountMicros = o.optInt("price_amount_micros");
        this.mPriceAmount = ((double) this.mPriceAmountMicros) / 1000000.0d;
        try {
            this.mTotalPrice = NumberFormat.getCurrencyInstance().parse(this.mPrice).doubleValue();
            this.mTaxAmount = this.mTotalPrice - this.mPriceAmount;
        } catch (Exception e) {
            Utils.logCrashlytics(e);
        }
    }

    public String getSku() {
        return this.mSku;
    }

    public String getType() {
        return this.mType;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getPriceCurrencyCode() {
        return this.mPriceCurrencyCode;
    }

    public int getPriceAmountMicros() {
        return this.mPriceAmountMicros;
    }

    public double getPriceAmount() {
        return this.mPriceAmount;
    }

    public double getTotalPrice() {
        return this.mTotalPrice;
    }

    public double getTaxAmount() {
        return this.mTaxAmount;
    }

    public String toString() {
        return "SkuDetails:" + this.mJson;
    }
}
