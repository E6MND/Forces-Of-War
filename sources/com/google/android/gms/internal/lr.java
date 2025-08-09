package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Payments;
import com.google.android.gms.wallet.Wallet;

public class lr implements Payments {
    public void changeMaskedWallet(GoogleApiClient googleApiClient, final String googleTransactionId, final String merchantTransactionId, final int requestCode) {
        googleApiClient.a(new Wallet.b() {
            /* access modifiers changed from: protected */
            public void a(ls lsVar) {
                lsVar.d(googleTransactionId, merchantTransactionId, requestCode);
                a(Status.Ek);
            }
        });
    }

    public void checkForPreAuthorization(GoogleApiClient googleApiClient, final int requestCode) {
        googleApiClient.a(new Wallet.b() {
            /* access modifiers changed from: protected */
            public void a(ls lsVar) {
                lsVar.dQ(requestCode);
                a(Status.Ek);
            }
        });
    }

    public void loadFullWallet(GoogleApiClient googleApiClient, final FullWalletRequest request, final int requestCode) {
        googleApiClient.a(new Wallet.b() {
            /* access modifiers changed from: protected */
            public void a(ls lsVar) {
                lsVar.a(request, requestCode);
                a(Status.Ek);
            }
        });
    }

    public void loadMaskedWallet(GoogleApiClient googleApiClient, final MaskedWalletRequest request, final int requestCode) {
        googleApiClient.a(new Wallet.b() {
            /* access modifiers changed from: protected */
            public void a(ls lsVar) {
                lsVar.a(request, requestCode);
                a(Status.Ek);
            }
        });
    }

    public void notifyTransactionStatus(GoogleApiClient googleApiClient, final NotifyTransactionStatusRequest request) {
        googleApiClient.a(new Wallet.b() {
            /* access modifiers changed from: protected */
            public void a(ls lsVar) {
                lsVar.a(request);
                a(Status.Ek);
            }
        });
    }
}
