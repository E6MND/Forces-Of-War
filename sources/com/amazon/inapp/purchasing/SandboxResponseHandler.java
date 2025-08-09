package com.amazon.inapp.purchasing;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.inapp.purchasing.GetUserIdResponse;
import com.amazon.inapp.purchasing.Item;
import com.amazon.inapp.purchasing.ItemDataResponse;
import com.amazon.inapp.purchasing.PurchaseResponse;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.plus.PlusShare;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

final class SandboxResponseHandler implements ResponseHandler {
    private static final String TAG = "SandboxResponseHandler";
    private final HandlerAdapter _handler = HandlerManager.getMainHandlerAdapter();

    SandboxResponseHandler() {
    }

    private Item getItem(String str, JSONObject jSONObject) {
        return new Item(str, jSONObject.optString("price"), Item.ItemType.valueOf(jSONObject.optString("itemType")), jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE), jSONObject.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION), jSONObject.optString("smallIconUrl"));
    }

    private ItemDataResponse getItemDataResponse(Intent intent) {
        Exception exc;
        HashSet hashSet;
        ItemDataResponse.ItemDataRequestStatus itemDataRequestStatus;
        String str;
        HashSet hashSet2;
        HashMap hashMap;
        HashMap hashMap2 = null;
        ItemDataResponse.ItemDataRequestStatus itemDataRequestStatus2 = ItemDataResponse.ItemDataRequestStatus.FAILED;
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("itemDataOutput"));
            String optString = jSONObject.optString("requestId");
            try {
                ItemDataResponse.ItemDataRequestStatus valueOf = ItemDataResponse.ItemDataRequestStatus.valueOf(jSONObject.optString(MessagingSmsConsts.STATUS));
                if (valueOf != ItemDataResponse.ItemDataRequestStatus.FAILED) {
                    hashSet = new HashSet();
                    try {
                        hashMap = new HashMap();
                    } catch (Exception e) {
                        Exception exc2 = e;
                        itemDataRequestStatus = valueOf;
                        str = optString;
                        exc = exc2;
                        Log.e(TAG, "Error parsing item data output", exc);
                        return new ItemDataResponse(str, hashSet, itemDataRequestStatus, hashMap2);
                    }
                    try {
                        JSONArray optJSONArray = jSONObject.optJSONArray("unavailableSkus");
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                hashSet.add(optJSONArray.getString(i));
                            }
                        }
                        JSONObject optJSONObject = jSONObject.optJSONObject("items");
                        if (optJSONObject != null) {
                            Iterator<String> keys = optJSONObject.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                hashMap.put(next, getItem(next, optJSONObject.optJSONObject(next)));
                            }
                        }
                        hashMap2 = hashMap;
                        hashSet2 = hashSet;
                    } catch (Exception e2) {
                        Exception exc3 = e2;
                        hashMap2 = hashMap;
                        itemDataRequestStatus = valueOf;
                        str = optString;
                        exc = exc3;
                        Log.e(TAG, "Error parsing item data output", exc);
                        return new ItemDataResponse(str, hashSet, itemDataRequestStatus, hashMap2);
                    }
                } else {
                    hashSet2 = null;
                }
                hashSet = hashSet2;
                itemDataRequestStatus = valueOf;
                str = optString;
            } catch (Exception e3) {
                hashSet = null;
                ItemDataResponse.ItemDataRequestStatus itemDataRequestStatus3 = itemDataRequestStatus2;
                str = optString;
                exc = e3;
                itemDataRequestStatus = itemDataRequestStatus3;
                Log.e(TAG, "Error parsing item data output", exc);
                return new ItemDataResponse(str, hashSet, itemDataRequestStatus, hashMap2);
            }
        } catch (Exception e4) {
            exc = e4;
            hashSet = null;
            itemDataRequestStatus = itemDataRequestStatus2;
            str = null;
            Log.e(TAG, "Error parsing item data output", exc);
            return new ItemDataResponse(str, hashSet, itemDataRequestStatus, hashMap2);
        }
        return new ItemDataResponse(str, hashSet, itemDataRequestStatus, hashMap2);
    }

    private PurchaseResponse getPurchaseResponse(Intent intent) {
        Exception e;
        String str;
        String str2;
        PurchaseResponse.PurchaseRequestStatus purchaseRequestStatus;
        Receipt receipt = null;
        PurchaseResponse.PurchaseRequestStatus purchaseRequestStatus2 = PurchaseResponse.PurchaseRequestStatus.FAILED;
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("purchaseOutput"));
            str = jSONObject.optString("requestId");
            try {
                str2 = jSONObject.optString("userId");
            } catch (Exception e2) {
                str2 = null;
                PurchaseResponse.PurchaseRequestStatus purchaseRequestStatus3 = purchaseRequestStatus2;
                e = e2;
                purchaseRequestStatus = purchaseRequestStatus3;
                Log.e(TAG, "Error parsing purchase output", e);
                return new PurchaseResponse(str, str2, receipt, purchaseRequestStatus);
            }
            try {
                purchaseRequestStatus = PurchaseResponse.PurchaseRequestStatus.valueOf(jSONObject.optString("purchaseStatus"));
                try {
                    JSONObject optJSONObject = jSONObject.optJSONObject("receipt");
                    if (optJSONObject != null) {
                        receipt = getReceipt(optJSONObject);
                    }
                } catch (Exception e3) {
                    e = e3;
                    Log.e(TAG, "Error parsing purchase output", e);
                    return new PurchaseResponse(str, str2, receipt, purchaseRequestStatus);
                }
            } catch (Exception e4) {
                Exception exc = e4;
                purchaseRequestStatus = purchaseRequestStatus2;
                e = exc;
                Log.e(TAG, "Error parsing purchase output", e);
                return new PurchaseResponse(str, str2, receipt, purchaseRequestStatus);
            }
        } catch (Exception e5) {
            str2 = null;
            str = null;
            Exception exc2 = e5;
            purchaseRequestStatus = purchaseRequestStatus2;
            e = exc2;
            Log.e(TAG, "Error parsing purchase output", e);
            return new PurchaseResponse(str, str2, receipt, purchaseRequestStatus);
        }
        return new PurchaseResponse(str, str2, receipt, purchaseRequestStatus);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: java.util.HashSet} */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amazon.inapp.purchasing.PurchaseUpdatesResponse getPurchaseUpdatesResponse(android.content.Intent r15) {
        /*
            r14 = this;
            r8 = 0
            r1 = 0
            com.amazon.inapp.purchasing.PurchaseUpdatesResponse$PurchaseUpdatesRequestStatus r3 = com.amazon.inapp.purchasing.PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.FAILED
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = "purchaseUpdatesOutput"
            java.lang.String r0 = r15.getStringExtra(r0)     // Catch:{ Exception -> 0x0085 }
            r9.<init>(r0)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = "requestId"
            java.lang.String r6 = r9.optString(r0)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = "status"
            java.lang.String r0 = r9.optString(r0)     // Catch:{ Exception -> 0x0095 }
            com.amazon.inapp.purchasing.PurchaseUpdatesResponse$PurchaseUpdatesRequestStatus r3 = com.amazon.inapp.purchasing.PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.valueOf(r0)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r0 = "offset"
            java.lang.String r4 = r9.optString(r0)     // Catch:{ Exception -> 0x0095 }
            java.lang.String r0 = "isMore"
            boolean r7 = r9.optBoolean(r0)     // Catch:{ Exception -> 0x009f }
            java.lang.String r0 = "userId"
            java.lang.String r5 = r9.optString(r0)     // Catch:{ Exception -> 0x00a9 }
            com.amazon.inapp.purchasing.PurchaseUpdatesResponse$PurchaseUpdatesRequestStatus r0 = com.amazon.inapp.purchasing.PurchaseUpdatesResponse.PurchaseUpdatesRequestStatus.SUCCESSFUL     // Catch:{ Exception -> 0x00b2 }
            if (r3 != r0) goto L_0x00ce
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ Exception -> 0x00b2 }
            r2.<init>()     // Catch:{ Exception -> 0x00b2 }
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ Exception -> 0x00bb }
            r0.<init>()     // Catch:{ Exception -> 0x00bb }
            java.lang.String r1 = "receipts"
            org.json.JSONArray r10 = r9.optJSONArray(r1)     // Catch:{ Exception -> 0x00c4 }
            if (r10 == 0) goto L_0x005c
            r1 = r8
        L_0x0048:
            int r11 = r10.length()     // Catch:{ Exception -> 0x00c4 }
            if (r1 >= r11) goto L_0x005c
            org.json.JSONObject r11 = r10.optJSONObject(r1)     // Catch:{ Exception -> 0x00c4 }
            com.amazon.inapp.purchasing.Receipt r11 = r14.getReceipt(r11)     // Catch:{ Exception -> 0x00c4 }
            r2.add(r11)     // Catch:{ Exception -> 0x00c4 }
            int r1 = r1 + 1
            goto L_0x0048
        L_0x005c:
            java.lang.String r1 = "revokedSkus"
            org.json.JSONArray r1 = r9.optJSONArray(r1)     // Catch:{ Exception -> 0x00c4 }
            if (r1 == 0) goto L_0x0074
        L_0x0064:
            int r9 = r1.length()     // Catch:{ Exception -> 0x00c4 }
            if (r8 >= r9) goto L_0x0074
            java.lang.String r9 = r1.getString(r8)     // Catch:{ Exception -> 0x00c4 }
            r0.add(r9)     // Catch:{ Exception -> 0x00c4 }
            int r8 = r8 + 1
            goto L_0x0064
        L_0x0074:
            r1 = r2
        L_0x0075:
            r2 = r5
            r5 = r0
            r12 = r4
            r4 = r1
            r1 = r6
            r6 = r12
        L_0x007b:
            com.amazon.inapp.purchasing.PurchaseUpdatesResponse r0 = new com.amazon.inapp.purchasing.PurchaseUpdatesResponse
            com.amazon.inapp.purchasing.Offset r6 = com.amazon.inapp.purchasing.Offset.fromString(r6)
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r0
        L_0x0085:
            r0 = move-exception
            r6 = r0
            r5 = r1
            r4 = r1
            r7 = r8
            r2 = r1
            r0 = r1
        L_0x008c:
            java.lang.String r8 = "SandboxResponseHandler"
            java.lang.String r9 = "Error parsing purchase updates output"
            android.util.Log.e(r8, r9, r6)
            r6 = r0
            goto L_0x007b
        L_0x0095:
            r0 = move-exception
            r5 = r1
            r4 = r1
            r7 = r8
            r2 = r1
            r12 = r1
            r1 = r6
            r6 = r0
            r0 = r12
            goto L_0x008c
        L_0x009f:
            r0 = move-exception
            r5 = r1
            r7 = r8
            r2 = r1
            r12 = r0
            r0 = r4
            r4 = r1
            r1 = r6
            r6 = r12
            goto L_0x008c
        L_0x00a9:
            r0 = move-exception
            r5 = r1
            r2 = r1
            r12 = r1
            r1 = r6
            r6 = r0
            r0 = r4
            r4 = r12
            goto L_0x008c
        L_0x00b2:
            r0 = move-exception
            r2 = r5
            r5 = r1
            r12 = r1
            r1 = r6
            r6 = r0
            r0 = r4
            r4 = r12
            goto L_0x008c
        L_0x00bb:
            r0 = move-exception
            r12 = r0
            r0 = r4
            r4 = r2
            r2 = r5
            r5 = r1
            r1 = r6
            r6 = r12
            goto L_0x008c
        L_0x00c4:
            r1 = move-exception
            r12 = r1
            r1 = r6
            r6 = r12
            r13 = r2
            r2 = r5
            r5 = r0
            r0 = r4
            r4 = r13
            goto L_0x008c
        L_0x00ce:
            r0 = r1
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.inapp.purchasing.SandboxResponseHandler.getPurchaseUpdatesResponse(android.content.Intent):com.amazon.inapp.purchasing.PurchaseUpdatesResponse");
    }

    private Receipt getReceipt(JSONObject jSONObject) throws ParseException {
        SubscriptionPeriod subscriptionPeriod;
        Date date = null;
        String optString = jSONObject.optString("sku");
        Item.ItemType valueOf = Item.ItemType.valueOf(jSONObject.optString("itemType"));
        JSONObject optJSONObject = jSONObject.optJSONObject("subscripionPeriod");
        if (valueOf == Item.ItemType.SUBSCRIPTION) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date parse = simpleDateFormat.parse(optJSONObject.optString("startTime"));
            String optString2 = optJSONObject.optString("endTime");
            if (!(optString2 == null || optString2.length() == 0)) {
                date = simpleDateFormat.parse(optString2);
            }
            subscriptionPeriod = new SubscriptionPeriod(parse, date);
        } else {
            subscriptionPeriod = null;
        }
        return new Receipt(optString, valueOf, false, subscriptionPeriod, jSONObject.optString(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN));
    }

    private GetUserIdResponse getUserIdResponse(Intent intent) {
        Exception e;
        String str;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus;
        String str2;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus2;
        String str3 = null;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus3 = GetUserIdResponse.GetUserIdRequestStatus.FAILED;
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("userOutput"));
            str = jSONObject.optString("requestId");
            try {
                getUserIdRequestStatus = GetUserIdResponse.GetUserIdRequestStatus.valueOf(jSONObject.optString(MessagingSmsConsts.STATUS));
                try {
                    if (getUserIdRequestStatus == GetUserIdResponse.GetUserIdRequestStatus.SUCCESSFUL) {
                        str3 = jSONObject.optString("userId");
                    }
                    GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus4 = getUserIdRequestStatus;
                    str2 = str3;
                    getUserIdRequestStatus2 = getUserIdRequestStatus4;
                } catch (Exception e2) {
                    e = e2;
                    Log.e(TAG, "Error parsing userid output", e);
                    GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus5 = getUserIdRequestStatus;
                    str2 = null;
                    getUserIdRequestStatus2 = getUserIdRequestStatus5;
                    return new GetUserIdResponse(str, getUserIdRequestStatus2, str2);
                }
            } catch (Exception e3) {
                Exception exc = e3;
                getUserIdRequestStatus = getUserIdRequestStatus3;
                e = exc;
            }
        } catch (Exception e4) {
            str = null;
            GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus6 = getUserIdRequestStatus3;
            e = e4;
            getUserIdRequestStatus = getUserIdRequestStatus6;
            Log.e(TAG, "Error parsing userid output", e);
            GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus52 = getUserIdRequestStatus;
            str2 = null;
            getUserIdRequestStatus2 = getUserIdRequestStatus52;
            return new GetUserIdResponse(str, getUserIdRequestStatus2, str2);
        }
        return new GetUserIdResponse(str, getUserIdRequestStatus2, str2);
    }

    private void handleItemDataResponse(Intent intent) {
        final ItemDataResponse itemDataResponse = getItemDataResponse(intent);
        this._handler.post(new Runnable() {
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for itemDataResponse with requestId: " + itemDataResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onItemDataResponse(itemDataResponse);
                }
            }
        });
    }

    private void handlePurchaseResponse(Intent intent) {
        final PurchaseResponse purchaseResponse = getPurchaseResponse(intent);
        this._handler.post(new Runnable() {
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for purchaseResponse with requestId: " + purchaseResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onPurchaseResponse(purchaseResponse);
                }
            }
        });
    }

    private void handlePurchaseUpdatesResponse(Intent intent) {
        final PurchaseUpdatesResponse purchaseUpdatesResponse = getPurchaseUpdatesResponse(intent);
        this._handler.post(new Runnable() {
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for purchaseUpdatesResponse with requestId: " + purchaseUpdatesResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onPurchaseUpdatesResponse(purchaseUpdatesResponse);
                }
            }
        });
    }

    private void handleUserIdResponse(Intent intent) {
        final GetUserIdResponse userIdResponse = getUserIdResponse(intent);
        this._handler.post(new Runnable() {
            public void run() {
                if (Logger.isTraceOn()) {
                    Logger.trace(SandboxResponseHandler.TAG, "Running Runnable for userIdResponse with requestId: " + userIdResponse.getRequestId());
                }
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    purchasingObserver.onGetUserIdResponse(userIdResponse);
                }
            }
        });
    }

    public void handleResponse(Context context, Intent intent) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "handleResponse");
        }
        try {
            String string = intent.getExtras().getString("responseType");
            if (string.equalsIgnoreCase("com.amazon.testclient.iap.purchase")) {
                handlePurchaseResponse(intent);
            } else if (string.equalsIgnoreCase("com.amazon.testclient.iap.appUserId")) {
                handleUserIdResponse(intent);
            } else if (string.equalsIgnoreCase("com.amazon.testclient.iap.itemData")) {
                handleItemDataResponse(intent);
            } else if (string.equalsIgnoreCase("com.amazon.testclient.iap.purchaseUpdates")) {
                handlePurchaseUpdatesResponse(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error handling response.", e);
        }
    }
}
