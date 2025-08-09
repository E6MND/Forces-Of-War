package com.amazon.inapp.purchasing;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.inapp.purchasing.Item;
import com.amazon.inapp.purchasing.ItemDataResponse;
import com.amazon.venezia.command.SuccessResult;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

final class KiwiGetItemDataRequestCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "getItem_data";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiGetItemDataRequestCommandTask";
    private final Set<String> _skus;

    KiwiGetItemDataRequestCommandTask(Set<String> set, String str) {
        super(COMMAND_NAME, COMMAND_VERSION, str);
        this._skus = set;
        addCommandData("skus", this._skus);
    }

    private ItemDataResponse getFailedResponse() {
        return new ItemDataResponse(getRequestId(), (Set<String>) null, ItemDataResponse.ItemDataRequestStatus.FAILED, (Map<String, Item>) null);
    }

    private void notifyObserver(final ItemDataResponse itemDataResponse) {
        postRunnableToMainLooper(new Runnable() {
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiGetItemDataRequestCommandTask.TAG, "Invoking onItemDataResponse with " + itemDataResponse);
                    }
                    purchasingObserver.onItemDataResponse(itemDataResponse);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onSuccess(SuccessResult successResult) throws RemoteException, KiwiException {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess");
        }
        Map data = successResult.getData();
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "data: " + data);
        }
        String str = (String) data.get("errorMessage");
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess: errorMessage: \"" + str + "\"");
        }
        if (isNullOrEmpty(str)) {
            HashSet hashSet = new HashSet();
            HashMap hashMap = new HashMap();
            for (String next : this._skus) {
                if (!data.containsKey(next)) {
                    hashSet.add(next);
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject((String) data.get(next));
                        hashMap.put(next, new Item(next, jSONObject.optString("price"), Item.ItemType.valueOf(jSONObject.getString("itemType")), jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE), jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION), jSONObject.getString("iconUrl")));
                    } catch (JSONException e) {
                        hashSet.add(next);
                        if (Logger.isErrorOn()) {
                            Logger.error(TAG, "Error parsing JSON for SKU " + next + ": " + e.getMessage());
                        }
                    }
                }
            }
            notifyObserver(new ItemDataResponse(getRequestId(), hashSet, hashSet.isEmpty() ? ItemDataResponse.ItemDataRequestStatus.SUCCESSFUL : ItemDataResponse.ItemDataRequestStatus.SUCCESSFUL_WITH_UNAVAILABLE_SKUS, hashMap));
            return;
        }
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "found error message: " + str);
        }
        sendFailedResponse();
    }

    /* access modifiers changed from: protected */
    public void sendFailedResponse() {
        notifyObserver(getFailedResponse());
    }
}
