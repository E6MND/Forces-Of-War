package com.amazon.inapp.purchasing;

import android.os.RemoteException;
import com.amazon.android.Kiwi;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.framework.prompt.PromptManager;
import com.amazon.android.framework.task.command.AbstractCommandTask;
import com.amazon.android.licensing.LicenseFailurePromptContentMapper;
import com.amazon.inapp.purchasing.Item;
import com.amazon.venezia.command.FailureResult;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

abstract class KiwiBaseCommandTask extends AbstractCommandTask {
    private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    protected static final String FALSE = "false";
    protected static final String KEY_CURSOR = "cursor";
    protected static final String KEY_DESCRIPTION = "description";
    protected static final String KEY_ERROR_MESSAGE = "errorMessage";
    protected static final String KEY_HAS_CONTENT = "hasContent";
    protected static final String KEY_HAS_MORE = "hasMore";
    protected static final String KEY_ICON_URL = "iconUrl";
    protected static final String KEY_ITEM_TYPE = "itemType";
    protected static final String KEY_ORDER_STATUS = "orderStatus";
    protected static final String KEY_PERIOD_DATE_END = "endDate";
    protected static final String KEY_PERIOD_DATE_START = "startDate";
    protected static final String KEY_PRICE = "price";
    protected static final String KEY_PURCHASE_ITEM_INTENT = "purchaseItemIntent";
    protected static final String KEY_RECEIPT = "receipt";
    protected static final String KEY_RECEIPTS = "receipts";
    protected static final String KEY_REQUEST_ID = "requestId";
    protected static final String KEY_REVOCATIONS = "revocations";
    protected static final String KEY_SDK_VERSION = "sdkVersion";
    protected static final String KEY_SIGNATURE = "signature";
    protected static final String KEY_SKU = "sku";
    protected static final String KEY_SKUS = "skus";
    protected static final String KEY_TITLE = "title";
    protected static final String KEY_TOKEN = "token";
    protected static final String KEY_USER_ID = "userId";
    protected static final String SDK_VERSION = "1.0";
    private static final String TAG = "KiwiBaseCommandTask";
    protected static final String TRUE = "true";
    private final Map<String, Object> _commandData;
    private final String _commandName;
    private final String _commandVersion;
    private final String _requestId;
    private LicenseFailurePromptContentMapper mapper = new LicenseFailurePromptContentMapper();

    KiwiBaseCommandTask(String str, String str2, String str3) {
        this._requestId = str3;
        this._commandName = str;
        this._commandVersion = str2;
        this._commandData = new HashMap();
        this._commandData.put(KEY_REQUEST_ID, this._requestId);
        this._commandData.put(KEY_SDK_VERSION, SDK_VERSION);
    }

    /* access modifiers changed from: protected */
    public void addCommandData(String str, Object obj) {
        this._commandData.put(str, obj);
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getCommandData() {
        return this._commandData;
    }

    /* access modifiers changed from: protected */
    public String getCommandName() {
        return this._commandName;
    }

    /* access modifiers changed from: protected */
    public String getCommandVersion() {
        return this._commandVersion;
    }

    /* access modifiers changed from: protected */
    public Date getDateFromString(String str) throws JSONException {
        try {
            Date parse = new SimpleDateFormat(DATE_FORMAT).parse(str);
            if (0 == parse.getTime()) {
                return null;
            }
            return parse;
        } catch (ParseException e) {
            throw new JSONException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public Receipt getReceiptFromReceiptJson(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString(KEY_SKU);
        Item.ItemType valueOf = Item.ItemType.valueOf(jSONObject.getString(KEY_ITEM_TYPE));
        return new Receipt(string, valueOf, false, Item.ItemType.SUBSCRIPTION == valueOf ? getSubscriptionPeriodFromReceiptJson(jSONObject) : null, jSONObject.getString("token"));
    }

    /* access modifiers changed from: protected */
    public String getRequestId() {
        return this._requestId;
    }

    /* access modifiers changed from: protected */
    public SubscriptionPeriod getSubscriptionPeriodFromReceiptJson(JSONObject jSONObject) throws JSONException {
        Date date = null;
        if (!jSONObject.has(KEY_PERIOD_DATE_START)) {
            return null;
        }
        Date dateFromString = getDateFromString(jSONObject.getString(KEY_PERIOD_DATE_START));
        String optString = jSONObject.optString(KEY_PERIOD_DATE_END);
        if (!isNullOrEmpty(optString)) {
            date = getDateFromString(optString);
        }
        return new SubscriptionPeriod(dateFromString, date);
    }

    /* access modifiers changed from: protected */
    public boolean isExecutionNeeded() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /* access modifiers changed from: protected */
    public void onException(KiwiException kiwiException) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onException, result: " + kiwiException.getMessage());
        }
        PromptManager promptManager = Kiwi.getPromptManager();
        PromptContent map = this.mapper.map(kiwiException);
        if (map != null) {
            promptManager.present(new FailurePrompt(map));
        }
        sendFailedResponse();
    }

    /* access modifiers changed from: protected */
    public void onFailure(FailureResult failureResult) throws RemoteException, KiwiException {
        if (failureResult != null) {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "onFailure: result message: " + failureResult.getDisplayableMessage());
            }
            Kiwi.getPromptManager().present(new FailurePrompt(new PromptContent(failureResult.getDisplayableName(), failureResult.getDisplayableMessage(), failureResult.getButtonLabel(), failureResult.show())));
            sendFailedResponse();
        } else if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onFailure: null result");
        }
    }

    /* access modifiers changed from: protected */
    public void postRunnableToMainLooper(Runnable runnable) {
        HandlerManager.getMainHandlerAdapter().post(runnable);
    }

    /* access modifiers changed from: protected */
    public abstract void sendFailedResponse();

    /* access modifiers changed from: protected */
    public boolean verifyReceipt(String str, Receipt receipt, JSONObject jSONObject) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "Validating receipt: " + receipt);
        }
        String optString = jSONObject.optString(KEY_SIGNATURE);
        if (!isNullOrEmpty(optString)) {
            boolean verifySignature = verifySignature(str, receipt.getPurchaseToken(), optString);
            if (!Logger.isTraceOn()) {
                return verifySignature;
            }
            Logger.error(TAG, "signature verification " + (verifySignature ? "succeeded" : "failed") + " for request ID " + getRequestId());
            return verifySignature;
        } else if (!Logger.isTraceOn()) {
            return false;
        } else {
            Logger.error(TAG, "a signature was not found in the receipt for request ID " + getRequestId());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifySignature(String str, String str2, String str3) {
        return Kiwi.isSignedByKiwi(str + "-" + str2, str3);
    }
}
