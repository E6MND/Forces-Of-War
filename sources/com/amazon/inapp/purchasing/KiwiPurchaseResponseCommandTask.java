package com.amazon.inapp.purchasing;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.inapp.purchasing.PurchaseResponse;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class KiwiPurchaseResponseCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "purchase_response";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiPurchaseResponseCommandTask";

    KiwiPurchaseResponseCommandTask(String str) {
        super(COMMAND_NAME, COMMAND_VERSION, str);
    }

    /* access modifiers changed from: protected */
    public void onSuccess(SuccessResult successResult) throws RemoteException, KiwiException {
        PurchaseResponse.PurchaseRequestStatus purchaseRequestStatus;
        String str;
        Receipt receipt;
        Receipt receipt2 = null;
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess");
        }
        Map data = successResult.getData();
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "data: " + data);
        }
        String str2 = (String) data.get("errorMessage");
        String str3 = (String) data.get("userId");
        String str4 = (String) data.get("receipt");
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess: errorMessage: \"" + str2 + "\" receipt: \"" + str4 + "\"");
        }
        PurchaseResponse.PurchaseRequestStatus purchaseRequestStatus2 = PurchaseResponse.PurchaseRequestStatus.FAILED;
        if (!isNullOrEmpty(str2) || isNullOrEmpty(str4)) {
            purchaseRequestStatus = purchaseRequestStatus2;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str4);
                try {
                    purchaseRequestStatus = PurchaseResponse.PurchaseRequestStatus.valueOf(jSONObject.getString("orderStatus"));
                } catch (Exception e) {
                    if (Logger.isErrorOn()) {
                        Logger.error(TAG, "Invalid order status " + str);
                    }
                    purchaseRequestStatus = PurchaseResponse.PurchaseRequestStatus.FAILED;
                }
                if (PurchaseResponse.PurchaseRequestStatus.SUCCESSFUL == purchaseRequestStatus) {
                    receipt = getReceiptFromReceiptJson(jSONObject);
                    if (!verifyReceipt(str3, receipt, jSONObject)) {
                        purchaseRequestStatus = PurchaseResponse.PurchaseRequestStatus.FAILED;
                        receipt = null;
                    }
                } else {
                    receipt = null;
                }
                receipt2 = receipt;
            } catch (JSONException e2) {
                if (Logger.isErrorOn()) {
                    Logger.error(TAG, "Error parsing receipt JSON: " + e2.getMessage());
                }
                purchaseRequestStatus = PurchaseResponse.PurchaseRequestStatus.FAILED;
            }
        }
        final PurchaseResponse purchaseResponse = new PurchaseResponse(getRequestId(), str3, receipt2, purchaseRequestStatus);
        postRunnableToMainLooper(new Runnable() {
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (Logger.isTraceOn()) {
                    Logger.trace(KiwiPurchaseResponseCommandTask.TAG, "About to invoke onPurchaseResponse with PurchasingObserver: " + purchasingObserver);
                }
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiPurchaseResponseCommandTask.TAG, "Invoking onPurchaseResponse with " + purchaseResponse);
                    }
                    purchasingObserver.onPurchaseResponse(purchaseResponse);
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiPurchaseResponseCommandTask.TAG, "No exceptions were thrown when invoking onPurchaseResponse");
                    }
                    ImplementationFactory.getRequestHandler().sendPurchaseResponseReceivedRequest(KiwiPurchaseResponseCommandTask.this.getRequestId());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void sendFailedResponse() {
        postRunnableToMainLooper(new Runnable() {
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                PurchaseResponse purchaseResponse = new PurchaseResponse(KiwiPurchaseResponseCommandTask.this.getRequestId(), (String) null, (Receipt) null, PurchaseResponse.PurchaseRequestStatus.FAILED);
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiPurchaseResponseCommandTask.TAG, "Invoking onPurchaseResponse with " + purchaseResponse);
                    }
                    purchasingObserver.onPurchaseResponse(purchaseResponse);
                }
            }
        });
    }
}
