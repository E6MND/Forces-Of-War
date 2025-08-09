package com.amazon.inapp.purchasing;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.inapp.purchasing.PurchaseUpdatesResponse;
import com.amazon.venezia.command.SuccessResult;
import com.facebook.internal.ServerProtocol;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class KiwiPurchaseUpdatesCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "purchase_updates";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiPurchaseUpdatesCommandTask";
    private final Offset _offset;

    KiwiPurchaseUpdatesCommandTask(Offset offset, String str) {
        super(COMMAND_NAME, COMMAND_VERSION, str);
        this._offset = offset;
        addCommandData("cursor", Offset.BEGINNING.equals(this._offset) ? null : this._offset.toString());
    }

    private PurchaseUpdatesResponse getFailedResponse() {
        return new PurchaseUpdatesResponse(getRequestId(), (String) null, PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.FAILED, (Set<Receipt>) null, (Set<String>) null, this._offset, false);
    }

    private void notifyObserver(final PurchaseUpdatesResponse purchaseUpdatesResponse) {
        postRunnableToMainLooper(new Runnable() {
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiPurchaseUpdatesCommandTask.TAG, "Invoking onPurchaseUpdatesResponse with " + purchaseUpdatesResponse);
                    }
                    purchasingObserver.onPurchaseUpdatesResponse(purchaseUpdatesResponse);
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
        String str2 = (String) data.get("userId");
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess: errorMessage: \"" + str + "\"");
        }
        if (isNullOrEmpty(str)) {
            try {
                HashSet hashSet = new HashSet();
                HashSet hashSet2 = new HashSet();
                JSONArray jSONArray = new JSONArray((String) data.get("receipts"));
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    Receipt receiptFromReceiptJson = getReceiptFromReceiptJson(jSONObject);
                    if (verifyReceipt(str2, receiptFromReceiptJson, jSONObject)) {
                        hashSet.add(receiptFromReceiptJson);
                    }
                }
                JSONArray jSONArray2 = new JSONArray((String) data.get("revocations"));
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    hashSet2.add(jSONArray2.getString(i2));
                }
                String str3 = (String) data.get("cursor");
                notifyObserver(new PurchaseUpdatesResponse(getRequestId(), str2, PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.SUCCESSFUL, hashSet, hashSet2, isNullOrEmpty(str3) ? Offset.BEGINNING : Offset.fromString(str3), ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase((String) data.get("hasMore"))));
            } catch (JSONException e) {
                if (Logger.isErrorOn()) {
                    Logger.error(TAG, "Error parsing purchase updates JSON: " + e.getMessage());
                }
                sendFailedResponse();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void sendFailedResponse() {
        notifyObserver(getFailedResponse());
    }
}
