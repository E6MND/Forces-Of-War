package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.AddressConstants;
import com.google.android.gms.identity.intents.UserAddressRequest;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.iy;
import com.google.android.gms.internal.iz;
import org.chromium.ui.base.PageTransition;

public class ix extends hc<iz> {
    private a UD;
    private final int mTheme;
    private Activity oe;
    private final String yN;

    public static final class a extends iy.a {
        private final int FT;
        private Activity oe;

        public a(int i, Activity activity) {
            this.FT = i;
            this.oe = activity;
        }

        /* access modifiers changed from: private */
        public void setActivity(Activity activity) {
            this.oe = activity;
        }

        public void g(int i, Bundle bundle) {
            if (i == 1) {
                Intent intent = new Intent();
                intent.putExtras(bundle);
                PendingIntent createPendingResult = this.oe.createPendingResult(this.FT, intent, PageTransition.CLIENT_REDIRECT);
                if (createPendingResult != null) {
                    try {
                        createPendingResult.send(1);
                    } catch (PendingIntent.CanceledException e) {
                        Log.w("AddressClientImpl", "Exception settng pending result", e);
                    }
                }
            } else {
                PendingIntent pendingIntent = null;
                if (bundle != null) {
                    pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
                }
                ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
                if (connectionResult.hasResolution()) {
                    try {
                        connectionResult.startResolutionForResult(this.oe, this.FT);
                    } catch (IntentSender.SendIntentException e2) {
                        Log.w("AddressClientImpl", "Exception starting pending intent", e2);
                    }
                } else {
                    try {
                        PendingIntent createPendingResult2 = this.oe.createPendingResult(this.FT, new Intent(), PageTransition.CLIENT_REDIRECT);
                        if (createPendingResult2 != null) {
                            createPendingResult2.send(1);
                        }
                    } catch (PendingIntent.CanceledException e3) {
                        Log.w("AddressClientImpl", "Exception setting pending result", e3);
                    }
                }
            }
        }
    }

    public ix(Activity activity, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, int i) {
        super(activity, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.yN = str;
        this.oe = activity;
        this.mTheme = i;
    }

    public void a(UserAddressRequest userAddressRequest, int i) {
        iP();
        this.UD = new a(i, this.oe);
        try {
            Bundle bundle = new Bundle();
            bundle.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", getContext().getPackageName());
            if (!TextUtils.isEmpty(this.yN)) {
                bundle.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", new Account(this.yN, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE));
            }
            bundle.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
            iO().a(this.UD, userAddressRequest, bundle);
        } catch (RemoteException e) {
            Log.e("AddressClientImpl", "Exception requesting user address", e);
            Bundle bundle2 = new Bundle();
            bundle2.putInt(AddressConstants.Extras.EXTRA_ERROR_CODE, AddressConstants.ErrorCodes.ERROR_CODE_NO_APPLICABLE_ADDRESSES);
            this.UD.g(1, bundle2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        hjVar.d(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName());
    }

    /* access modifiers changed from: protected */
    /* renamed from: an */
    public iz x(IBinder iBinder) {
        return iz.a.ap(iBinder);
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.identity.service.BIND";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.identity.intents.internal.IAddressService";
    }

    public void disconnect() {
        super.disconnect();
        if (this.UD != null) {
            this.UD.setActivity((Activity) null);
            this.UD = null;
        }
    }

    /* access modifiers changed from: protected */
    public iz iO() {
        return (iz) super.fo();
    }

    /* access modifiers changed from: protected */
    public void iP() {
        super.ci();
    }
}
