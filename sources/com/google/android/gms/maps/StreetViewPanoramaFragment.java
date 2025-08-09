package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.internal.hn;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.t;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class StreetViewPanoramaFragment extends Fragment {
    private final b ZP = new b(this);
    private StreetViewPanorama ZQ;

    static class a implements LifecycleDelegate {
        private final Fragment Mg;
        private final IStreetViewPanoramaFragmentDelegate ZR;

        public a(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.ZR = (IStreetViewPanoramaFragmentDelegate) hn.f(iStreetViewPanoramaFragmentDelegate);
            this.Mg = (Fragment) hn.f(fragment);
        }

        public IStreetViewPanoramaFragmentDelegate jx() {
            return this.ZR;
        }

        public void onCreate(Bundle savedInstanceState) {
            if (savedInstanceState == null) {
                try {
                    savedInstanceState = new Bundle();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            Bundle arguments = this.Mg.getArguments();
            if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                t.a(savedInstanceState, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
            }
            this.ZR.onCreate(savedInstanceState);
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            try {
                return (View) e.e(this.ZR.onCreateView(e.h(inflater), e.h(container), savedInstanceState));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroy() {
            try {
                this.ZR.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            try {
                this.ZR.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle attrs, Bundle savedInstanceState) {
            try {
                this.ZR.onInflate(e.h(activity), (StreetViewPanoramaOptions) null, savedInstanceState);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onLowMemory() {
            try {
                this.ZR.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.ZR.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.ZR.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle outState) {
            try {
                this.ZR.onSaveInstanceState(outState);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public void onStop() {
        }
    }

    static class b extends com.google.android.gms.dynamic.a<a> {
        private final Fragment Mg;
        protected f<a> ZC;
        private Activity oe;

        b(Fragment fragment) {
            this.Mg = fragment;
        }

        /* access modifiers changed from: private */
        public void setActivity(Activity activity) {
            this.oe = activity;
            ju();
        }

        /* access modifiers changed from: protected */
        public void a(f<a> fVar) {
            this.ZC = fVar;
            ju();
        }

        public void ju() {
            if (this.oe != null && this.ZC != null && gC() == null) {
                try {
                    MapsInitializer.initialize(this.oe);
                    this.ZC.a(new a(this.Mg, u.E(this.oe).j(e.h(this.oe))));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    public static StreetViewPanoramaFragment newInstance() {
        return new StreetViewPanoramaFragment();
    }

    public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions options) {
        StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", options);
        streetViewPanoramaFragment.setArguments(bundle);
        return streetViewPanoramaFragment;
    }

    public final StreetViewPanorama getStreetViewPanorama() {
        IStreetViewPanoramaFragmentDelegate jx = jx();
        if (jx == null) {
            return null;
        }
        try {
            IStreetViewPanoramaDelegate streetViewPanorama = jx.getStreetViewPanorama();
            if (streetViewPanorama == null) {
                return null;
            }
            if (this.ZQ == null || this.ZQ.jw().asBinder() != streetViewPanorama.asBinder()) {
                this.ZQ = new StreetViewPanorama(streetViewPanorama);
            }
            return this.ZQ;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    /* access modifiers changed from: protected */
    public IStreetViewPanoramaFragmentDelegate jx() {
        this.ZP.ju();
        if (this.ZP.gC() == null) {
            return null;
        }
        return ((a) this.ZP.gC()).jx();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.ZP.setActivity(activity);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ZP.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.ZP.onCreateView(inflater, container, savedInstanceState);
    }

    public void onDestroy() {
        this.ZP.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.ZP.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        this.ZP.setActivity(activity);
        this.ZP.onInflate(activity, new Bundle(), savedInstanceState);
    }

    public void onLowMemory() {
        this.ZP.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.ZP.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.ZP.onResume();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(outState);
        this.ZP.onSaveInstanceState(outState);
    }

    public void setArguments(Bundle args) {
        super.setArguments(args);
    }
}
