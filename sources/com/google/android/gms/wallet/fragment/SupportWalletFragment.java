package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.h;
import com.google.android.gms.internal.ll;
import com.google.android.gms.internal.lm;
import com.google.android.gms.internal.lt;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class SupportWalletFragment extends Fragment {
    /* access modifiers changed from: private */
    public final Fragment FS = this;
    /* access modifiers changed from: private */
    public b akd;
    /* access modifiers changed from: private */
    public final h ake = h.a(this);
    private final c akf = new c();
    /* access modifiers changed from: private */
    public a akg = new a(this);
    /* access modifiers changed from: private */
    public WalletFragmentOptions akh;
    /* access modifiers changed from: private */
    public WalletFragmentInitParams aki;
    /* access modifiers changed from: private */
    public MaskedWalletRequest akj;
    /* access modifiers changed from: private */
    public MaskedWallet akk;
    /* access modifiers changed from: private */
    public Boolean akl;
    /* access modifiers changed from: private */
    public boolean mCreated = false;

    public interface OnStateChangedListener {
        void onStateChanged(SupportWalletFragment supportWalletFragment, int i, int i2, Bundle bundle);
    }

    static class a extends lm.a {
        private OnStateChangedListener akm;
        private final SupportWalletFragment akn;

        a(SupportWalletFragment supportWalletFragment) {
            this.akn = supportWalletFragment;
        }

        public void a(int i, int i2, Bundle bundle) {
            if (this.akm != null) {
                this.akm.onStateChanged(this.akn, i, i2, bundle);
            }
        }

        public void a(OnStateChangedListener onStateChangedListener) {
            this.akm = onStateChangedListener;
        }
    }

    private static class b implements LifecycleDelegate {
        private final ll ako;

        private b(ll llVar) {
            this.ako = llVar;
        }

        /* access modifiers changed from: private */
        public int getState() {
            try {
                return this.ako.getState();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public void initialize(WalletFragmentInitParams startParams) {
            try {
                this.ako.initialize(startParams);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            try {
                this.ako.onActivityResult(requestCode, resultCode, data);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public void setEnabled(boolean enabled) {
            try {
                this.ako.setEnabled(enabled);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public void updateMaskedWallet(MaskedWallet maskedWallet) {
            try {
                this.ako.updateMaskedWallet(maskedWallet);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public void updateMaskedWalletRequest(MaskedWalletRequest request) {
            try {
                this.ako.updateMaskedWalletRequest(request);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public void onCreate(Bundle savedInstanceState) {
            try {
                this.ako.onCreate(savedInstanceState);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            try {
                return (View) e.e(this.ako.onCreateView(e.h(inflater), e.h(container), savedInstanceState));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public void onDestroy() {
        }

        public void onDestroyView() {
        }

        public void onInflate(Activity activity, Bundle attrs, Bundle savedInstanceState) {
            try {
                this.ako.a(e.h(activity), (WalletFragmentOptions) attrs.getParcelable("extraWalletFragmentOptions"), savedInstanceState);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public void onLowMemory() {
        }

        public void onPause() {
            try {
                this.ako.onPause();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public void onResume() {
            try {
                this.ako.onResume();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public void onSaveInstanceState(Bundle outState) {
            try {
                this.ako.onSaveInstanceState(outState);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public void onStart() {
            try {
                this.ako.onStart();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public void onStop() {
            try {
                this.ako.onStop();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class c extends com.google.android.gms.dynamic.a<b> implements View.OnClickListener {
        private c() {
        }

        /* access modifiers changed from: protected */
        public void a(FrameLayout frameLayout) {
            WalletFragmentStyle fragmentStyle;
            Button button = new Button(SupportWalletFragment.this.FS.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int i = -1;
            int i2 = -2;
            if (!(SupportWalletFragment.this.akh == null || (fragmentStyle = SupportWalletFragment.this.akh.getFragmentStyle()) == null)) {
                DisplayMetrics displayMetrics = SupportWalletFragment.this.FS.getResources().getDisplayMetrics();
                i = fragmentStyle.a("buyButtonWidth", displayMetrics, -1);
                i2 = fragmentStyle.a("buyButtonHeight", displayMetrics, -2);
            }
            button.setLayoutParams(new ViewGroup.LayoutParams(i, i2));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        /* access modifiers changed from: protected */
        public void a(f<b> fVar) {
            FragmentActivity activity = SupportWalletFragment.this.FS.getActivity();
            if (SupportWalletFragment.this.akd == null && SupportWalletFragment.this.mCreated && activity != null) {
                try {
                    b unused = SupportWalletFragment.this.akd = new b(lt.a(activity, SupportWalletFragment.this.ake, SupportWalletFragment.this.akh, SupportWalletFragment.this.akg));
                    WalletFragmentOptions unused2 = SupportWalletFragment.this.akh = null;
                    fVar.a(SupportWalletFragment.this.akd);
                    if (SupportWalletFragment.this.aki != null) {
                        SupportWalletFragment.this.akd.initialize(SupportWalletFragment.this.aki);
                        WalletFragmentInitParams unused3 = SupportWalletFragment.this.aki = null;
                    }
                    if (SupportWalletFragment.this.akj != null) {
                        SupportWalletFragment.this.akd.updateMaskedWalletRequest(SupportWalletFragment.this.akj);
                        MaskedWalletRequest unused4 = SupportWalletFragment.this.akj = null;
                    }
                    if (SupportWalletFragment.this.akk != null) {
                        SupportWalletFragment.this.akd.updateMaskedWallet(SupportWalletFragment.this.akk);
                        MaskedWallet unused5 = SupportWalletFragment.this.akk = null;
                    }
                    if (SupportWalletFragment.this.akl != null) {
                        SupportWalletFragment.this.akd.setEnabled(SupportWalletFragment.this.akl.booleanValue());
                        Boolean unused6 = SupportWalletFragment.this.akl = null;
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        }

        public void onClick(View view) {
            FragmentActivity activity = SupportWalletFragment.this.FS.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
        }
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions options) {
        SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", options);
        supportWalletFragment.FS.setArguments(bundle);
        return supportWalletFragment;
    }

    public int getState() {
        if (this.akd != null) {
            return this.akd.getState();
        }
        return 0;
    }

    public void initialize(WalletFragmentInitParams initParams) {
        if (this.akd != null) {
            this.akd.initialize(initParams);
            this.aki = null;
        } else if (this.aki == null) {
            this.aki = initParams;
            if (this.akj != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.akk != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.akd != null) {
            this.akd.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        WalletFragmentOptions walletFragmentOptions;
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) savedInstanceState.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.aki != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.aki = walletFragmentInitParams;
            }
            if (this.akj == null) {
                this.akj = (MaskedWalletRequest) savedInstanceState.getParcelable("maskedWalletRequest");
            }
            if (this.akk == null) {
                this.akk = (MaskedWallet) savedInstanceState.getParcelable("maskedWallet");
            }
            if (savedInstanceState.containsKey("walletFragmentOptions")) {
                this.akh = (WalletFragmentOptions) savedInstanceState.getParcelable("walletFragmentOptions");
            }
            if (savedInstanceState.containsKey("enabled")) {
                this.akl = Boolean.valueOf(savedInstanceState.getBoolean("enabled"));
            }
        } else if (!(this.FS.getArguments() == null || (walletFragmentOptions = (WalletFragmentOptions) this.FS.getArguments().getParcelable("extraWalletFragmentOptions")) == null)) {
            walletFragmentOptions.N(this.FS.getActivity());
            this.akh = walletFragmentOptions;
        }
        this.mCreated = true;
        this.akf.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.akf.onCreateView(inflater, container, savedInstanceState);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }

    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        if (this.akh == null) {
            this.akh = WalletFragmentOptions.a((Context) activity, attrs);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("attrKeyWalletFragmentOptions", this.akh);
        this.akf.onInflate(activity, bundle, savedInstanceState);
    }

    public void onPause() {
        super.onPause();
        this.akf.onPause();
    }

    public void onResume() {
        super.onResume();
        this.akf.onResume();
        FragmentManager supportFragmentManager = this.FS.getActivity().getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.FS.getActivity()), this.FS.getActivity(), -1);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.akf.onSaveInstanceState(outState);
        if (this.aki != null) {
            outState.putParcelable("walletFragmentInitParams", this.aki);
            this.aki = null;
        }
        if (this.akj != null) {
            outState.putParcelable("maskedWalletRequest", this.akj);
            this.akj = null;
        }
        if (this.akk != null) {
            outState.putParcelable("maskedWallet", this.akk);
            this.akk = null;
        }
        if (this.akh != null) {
            outState.putParcelable("walletFragmentOptions", this.akh);
            this.akh = null;
        }
        if (this.akl != null) {
            outState.putBoolean("enabled", this.akl.booleanValue());
            this.akl = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.akf.onStart();
    }

    public void onStop() {
        super.onStop();
        this.akf.onStop();
    }

    public void setEnabled(boolean enabled) {
        if (this.akd != null) {
            this.akd.setEnabled(enabled);
            this.akl = null;
            return;
        }
        this.akl = Boolean.valueOf(enabled);
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.akg.a(listener);
    }

    public void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.akd != null) {
            this.akd.updateMaskedWallet(maskedWallet);
            this.akk = null;
            return;
        }
        this.akk = maskedWallet;
    }

    public void updateMaskedWalletRequest(MaskedWalletRequest request) {
        if (this.akd != null) {
            this.akd.updateMaskedWalletRequest(request);
            this.akj = null;
            return;
        }
        this.akj = request;
    }
}
