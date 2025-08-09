package com.amazon.inapp.purchasing;

import com.google.android.gms.plus.PlusShare;

public final class Item {
    private static final String TO_STRING_FORMAT = "(%s, sku: \"%s\", price: \"%s\", itemType: \"%s\", title: \"%s\", description: \"%s\", smallIconUrl: \"%s\")";
    private final String _description;
    private final ItemType _itemType;
    private final String _price;
    private final String _sku;
    private final String _smallIconUrl;
    private final String _title;

    public enum ItemType {
        CONSUMABLE,
        ENTITLED,
        SUBSCRIPTION
    }

    Item(String str, String str2, ItemType itemType, String str3, String str4, String str5) {
        Validator.validateNotNull(str, "sku");
        Validator.validateNotNull(itemType, "itemType");
        Validator.validateNotNull(str3, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
        Validator.validateNotNull(str4, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
        Validator.validateNotNull(str5, "smallIconUrl");
        if (ItemType.SUBSCRIPTION != itemType) {
            Validator.validateNotNull(str2, "price");
        }
        this._sku = str;
        this._itemType = itemType;
        this._price = str2;
        this._title = str3;
        this._description = str4;
        this._smallIconUrl = str5;
    }

    public String getDescription() {
        return this._description;
    }

    public ItemType getItemType() {
        return this._itemType;
    }

    public String getPrice() {
        return this._price;
    }

    public String getSku() {
        return this._sku;
    }

    public String getSmallIconUrl() {
        return this._smallIconUrl;
    }

    public String getTitle() {
        return this._title;
    }

    public String toString() {
        return String.format(TO_STRING_FORMAT, new Object[]{super.toString(), this._sku, this._price, this._itemType, this._title, this._description, this._smallIconUrl});
    }
}
