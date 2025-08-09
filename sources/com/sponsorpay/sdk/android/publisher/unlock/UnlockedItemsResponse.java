package com.sponsorpay.sdk.android.publisher.unlock;

import com.facebook.internal.ServerProtocol;
import com.sponsorpay.sdk.android.publisher.AbstractResponse;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UnlockedItemsResponse extends AbstractResponse {
    private static final String ITEM_ID_KEY = "itemID";
    private static final String ITEM_NAME_KEY = "itemName";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String UNLOCKED_KEY = "unlocked";
    protected SPUnlockResponseListener mListener;
    private Map<String, Item> mReturnedItems;

    public void setResponseListener(SPUnlockResponseListener listener) {
        this.mListener = listener;
    }

    public Map<String, Item> getItems() {
        return this.mReturnedItems;
    }

    public void parseSuccessfulResponse() {
        try {
            JSONArray responseBodyAsJsonArray = new JSONArray(this.mResponseBody);
            int responseItemsCount = responseBodyAsJsonArray.length();
            this.mReturnedItems = new HashMap(responseItemsCount);
            for (int i = 0; i < responseItemsCount; i++) {
                JSONObject itemAsJsonObject = responseBodyAsJsonArray.getJSONObject(i);
                Item parsedItem = new Item();
                parsedItem.id = itemAsJsonObject.getString(ITEM_ID_KEY);
                parsedItem.name = itemAsJsonObject.getString(ITEM_NAME_KEY);
                parsedItem.unlocked = itemAsJsonObject.getBoolean(UNLOCKED_KEY);
                parsedItem.timestamp = itemAsJsonObject.getLong(TIMESTAMP_KEY);
                this.mReturnedItems.put(parsedItem.name, parsedItem);
            }
            this.mErrorType = AbstractResponse.RequestErrorType.NO_ERROR;
        } catch (JSONException e) {
            this.mErrorType = AbstractResponse.RequestErrorType.ERROR_INVALID_RESPONSE;
        }
    }

    public void invokeOnSuccessCallback() {
        if (this.mListener != null) {
            this.mListener.onSPUnlockItemsStatusResponseReceived(this);
        }
    }

    public void invokeOnErrorCallback() {
        if (this.mListener != null) {
            this.mListener.onSPUnlockRequestError(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void setErrorType(AbstractResponse.RequestErrorType errorType) {
        this.mErrorType = errorType;
    }

    public static class Item {
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public long timestamp;
        /* access modifiers changed from: private */
        public boolean unlocked;

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public boolean isUnlocked() {
            return this.unlocked;
        }

        public String toString() {
            Object[] objArr = new Object[4];
            objArr[0] = this.id;
            objArr[1] = this.name;
            objArr[2] = this.unlocked ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
            objArr[3] = Long.valueOf(this.timestamp);
            return String.format("Item ID: %s, name: %s, unlocked: %s, timestamp:%d", objArr);
        }
    }
}
