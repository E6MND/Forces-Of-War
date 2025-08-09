package com.sponsorpay.sdk.android.publisher.currency;

import com.sponsorpay.sdk.android.publisher.AbstractResponse;
import com.sponsorpay.sdk.android.publisher.currency.VirtualCurrencyConnector;

public abstract class CurrencyServerAbstractResponse extends AbstractResponse {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$sponsorpay$sdk$android$publisher$currency$VirtualCurrencyConnector$RequestType;
    protected SPCurrencyServerListener mListener;

    static /* synthetic */ int[] $SWITCH_TABLE$com$sponsorpay$sdk$android$publisher$currency$VirtualCurrencyConnector$RequestType() {
        int[] iArr = $SWITCH_TABLE$com$sponsorpay$sdk$android$publisher$currency$VirtualCurrencyConnector$RequestType;
        if (iArr == null) {
            iArr = new int[VirtualCurrencyConnector.RequestType.values().length];
            try {
                iArr[VirtualCurrencyConnector.RequestType.DELTA_COINS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $SWITCH_TABLE$com$sponsorpay$sdk$android$publisher$currency$VirtualCurrencyConnector$RequestType = iArr;
        }
        return iArr;
    }

    public void setResponseListener(SPCurrencyServerListener listener) {
        this.mListener = listener;
    }

    public void invokeOnErrorCallback() {
        if (this.mListener != null) {
            this.mListener.onSPCurrencyServerError(this);
        }
    }

    public static CurrencyServerAbstractResponse getParsingInstance(VirtualCurrencyConnector.RequestType requestType) {
        switch ($SWITCH_TABLE$com$sponsorpay$sdk$android$publisher$currency$VirtualCurrencyConnector$RequestType()[requestType.ordinal()]) {
            case 1:
                return new CurrencyServerDeltaOfCoinsResponse();
            default:
                return null;
        }
    }
}
