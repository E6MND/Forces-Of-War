package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

public class d extends Fragment implements DialogInterface.OnCancelListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient.OnConnectionFailedListener DK;
    private GoogleApiClient Eq;
    private ConnectionResult Er;
    private boolean Es;

    public void a(GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.Eq = googleApiClient;
        this.Eq.registerConnectionCallbacks(this);
        this.DK = onConnectionFailedListener;
        this.Eq.registerConnectionCallbacks(this);
        this.Eq.registerConnectionFailedListener(this);
    }

    public boolean isInitialized() {
        return this.Eq != null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0018, code lost:
        if (com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == 0) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r5 == -1) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            switch(r4) {
                case 1: goto L_0x001b;
                case 2: goto L_0x0010;
                default: goto L_0x0005;
            }
        L_0x0005:
            r0 = r1
        L_0x0006:
            if (r0 == 0) goto L_0x001f
            com.google.android.gms.common.api.GoogleApiClient r0 = r3.Eq
            r0.connect()
            r3.Es = r1
        L_0x000f:
            return
        L_0x0010:
            android.support.v4.app.FragmentActivity r2 = r3.getActivity()
            int r2 = com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(r2)
            if (r2 != 0) goto L_0x0005
            goto L_0x0006
        L_0x001b:
            r2 = -1
            if (r5 != r2) goto L_0x0005
            goto L_0x0006
        L_0x001f:
            com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r0 = r3.DK
            com.google.android.gms.common.ConnectionResult r1 = r3.Er
            r0.onConnectionFailed(r1)
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.d.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.Es = false;
        this.DK.onConnectionFailed(this.Er);
    }

    public void onConnected(Bundle connectionHint) {
        this.Es = false;
    }

    public void onConnectionFailed(ConnectionResult result) {
        this.Er = result;
        if (!this.Es) {
            int indexOf = getActivity().getSupportFragmentManager().getFragments().indexOf(this);
            if (result.hasResolution()) {
                try {
                    result.startResolutionForResult(getActivity(), ((indexOf + 1) << 16) + 1);
                } catch (IntentSender.SendIntentException e) {
                    this.Eq.connect();
                }
            } else if (GooglePlayServicesUtil.isUserRecoverableError(result.getErrorCode())) {
                GooglePlayServicesUtil.b(result.getErrorCode(), getActivity(), this, 2, this);
            } else {
                this.DK.onConnectionFailed(this.Er);
            }
        }
    }

    public void onConnectionSuspended(int cause) {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.Es = savedInstanceState.getBoolean("resolving_error", false);
            int i = savedInstanceState.getInt("connection_result_status");
            if (i != 0) {
                this.Er = new ConnectionResult(i, (PendingIntent) savedInstanceState.getParcelable("resolution_pending_intent"));
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("resolving_error", this.Es);
        if (this.Er != null) {
            outState.putInt("connection_result_status", this.Er.getErrorCode());
            outState.putParcelable("resolution_pending_intent", this.Er.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        if (!this.Es && this.Eq != null) {
            this.Eq.connect();
        }
    }

    public void onStop() {
        super.onStop();
        if (this.Eq != null) {
            this.Eq.disconnect();
        }
    }
}
