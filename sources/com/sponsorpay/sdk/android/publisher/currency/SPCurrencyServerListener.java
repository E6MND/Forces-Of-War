package com.sponsorpay.sdk.android.publisher.currency;

public interface SPCurrencyServerListener {
    void onSPCurrencyDeltaReceived(CurrencyServerDeltaOfCoinsResponse currencyServerDeltaOfCoinsResponse);

    void onSPCurrencyServerError(CurrencyServerAbstractResponse currencyServerAbstractResponse);
}
