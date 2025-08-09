package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
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
import com.google.android.gms.internal.ll;
import com.google.android.gms.internal.lm;
import com.google.android.gms.internal.lt;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragment extends Fragment {
    /* access modifiers changed from: private */
    public final Fragment Mg = this;
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
    public b akq;
    /* access modifiers changed from: private */
    public final com.google.android.gms.dynamic.b akr = com.google.android.gms.dynamic.b.a(this);
    private final c aks = new c();
    /* access modifiers changed from: private */
    public a akt = new a(this);
    /* access modifiers changed from: private */
    public boolean mCreated = false;

    public interface OnStateChangedListener {
        void onStateChanged(WalletFragment walletFragment, int i, int i2, Bundle bundle);
    }

    static class a extends lm.a {
        private OnStateChangedListener aku;
        private final WalletFragment akv;

        a(WalletFragment walletFragment) {
            this.akv = walletFragment;
        }

        public void a(int i, int i2, Bundle bundle) {
            if (this.aku != null) {
                this.aku.onStateChanged(this.akv, i, i2, bundle);
            }
        }

        public void a(OnStateChangedListener onStateChangedListener) {
            this.aku = onStateChangedListener;
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
            Button button = new Button(WalletFragment.this.Mg.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int i = -1;
            int i2 = -2;
            if (!(WalletFragment.this.akh == null || (fragmentStyle = WalletFragment.this.akh.getFragmentStyle()) == null)) {
                DisplayMetrics displayMetrics = WalletFragment.this.Mg.getResources().getDisplayMetrics();
                i = fragmentStyle.a("buyButtonWidth", displayMetrics, -1);
                i2 = fragmentStyle.a("buyButtonHeight", displayMetrics, -2);
            }
            button.setLayoutParams(new ViewGroup.LayoutParams(i, i2));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        /* access modifiers changed from: protected */
        public void a(f<b> fVar) {
            Activity activity = WalletFragment.this.Mg.getActivity();
            if (WalletFragment.this.akq == null && WalletFragment.this.mCreated && activity != null) {
                try {
                    b unused = WalletFragment.this.akq = new b(lt.a(activity, WalletFragment.this.akr, WalletFragment.this.akh, WalletFragment.this.akt));
                    WalletFragmentOptions unused2 = WalletFragment.this.akh = null;
                    fVar.a(WalletFragment.this.akq);
                    if (WalletFragment.this.aki != null) {
                        WalletFragment.this.akq.initialize(WalletFragment.this.aki);
                        WalletFragmentInitParams unused3 = WalletFragment.this.aki = null;
                    }
                    if (WalletFragment.this.akj != null) {
                        WalletFragment.this.akq.updateMaskedWalletRequest(WalletFragment.this.akj);
                        MaskedWalletRequest unused4 = WalletFragment.this.akj = null;
                    }
                    if (WalletFragment.this.akk != null) {
                        WalletFragment.this.akq.updateMaskedWallet(WalletFragment.this.akk);
                        MaskedWallet unused5 = WalletFragment.this.akk = null;
                    }
                    if (WalletFragment.this.akl != null) {
                        WalletFragment.this.akq.setEnabled(WalletFragment.this.akl.booleanValue());
                        Boolean unused6 = WalletFragment.this.akl = null;
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        }

        public void onClick(View view) {
            Activity activity = WalletFragment.this.Mg.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
        }
    }

    public static WalletFragment newInstance(WalletFragmentOptions options) {
        WalletFragment walletFragment = new WalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", options);
        walletFragment.Mg.setArguments(bundle);
        return walletFragment;
    }

    public int getState() {
        if (this.akq != null) {
            return this.akq.getState();
        }
        return 0;
    }

    public void initialize(WalletFragmentInitParams initParams) {
        if (this.akq != null) {
            this.akq.initialize(initParams);
            this.aki = null;
        } else if (this.aki == null) {
            this.aki = initParams;
            if (this.akj != null) {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.akk != null) {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.akq != null) {
            this.akq.onActivityResult(requestCode, resultCode, data);
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
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
        } else if (!(this.Mg.getArguments() == null || (walletFragmentOptions = (WalletFragmentOptions) this.Mg.getArguments().getParcelable("extraWalletFragmentOptions")) == null)) {
            walletFragmentOptions.N(this.Mg.getActivity());
            this.akh = walletFragmentOptions;
        }
        this.mCreated = true;
        this.aks.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.aks.onCreateView(inflater, container, savedInstanceState);
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
        this.aks.onInflate(activity, bundle, savedInstanceState);
    }

    public void onPause() {
        super.onPause();
        this.aks.onPause();
    }

    public void onResume() {
        super.onResume();
        this.aks.onResume();
        FragmentManager fragmentManager = this.Mg.getActivity().getFragmentManager();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            fragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.Mg.getActivity()), this.Mg.getActivity(), -1);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.aks.onSaveInstanceState(outState);
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
        this.aks.onStart();
    }

    public void onStop() {
        super.onStop();
        this.aks.onStop();
    }

    public void setEnabled(boolean enabled) {
        if (this.akq != null) {
            this.akq.setEnabled(enabled);
            this.akl = null;
            return;
        }
        this.akl = Boolean.valueOf(enabled);
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.akt.a(listener);
    }

    public void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.akq != null) {
            this.akq.updateMaskedWallet(maskedWallet);
            this.akk = null;
            return;
        }
        this.akk = maskedWallet;
    }

    public void updateMaskedWalletRequest(MaskedWalletRequest request) {
        if (this.akq != null) {
            this.akq.updateMaskedWalletRequest(request);
            this.akj = null;
            return;
        }
        this.akj = request;
    }
}
