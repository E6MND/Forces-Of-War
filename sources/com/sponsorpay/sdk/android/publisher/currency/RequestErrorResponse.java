package com.sponsorpay.sdk.android.publisher.currency;

import com.sponsorpay.sdk.android.publisher.AbstractResponse;

public class RequestErrorResponse extends CurrencyServerAbstractResponse {
    public RequestErrorResponse() {
        this.mErrorType = AbstractResponse.RequestErrorType.ERROR_NO_INTERNET_CONNECTION;
    }

    public void parseSuccessfulResponse() {
    }

    public void invokeOnSuccessCallback() {
    }
}
