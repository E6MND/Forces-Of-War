package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
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

public class SupportStreetViewPanoramaFragment extends Fragment {
    private StreetViewPanorama ZQ;
    private final b aae = new b(this);

    static class a implements LifecycleDelegate {
        private final Fragment FS;
        private final IStreetViewPanoramaFragmentDelegate ZR;

        public a(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.ZR = (IStreetViewPanoramaFragmentDelegate) hn.f(iStreetViewPanoramaFragmentDelegate);
            this.FS = (Fragment) hn.f(fragment);
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
            Bundle arguments = this.FS.getArguments();
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
        private final Fragment FS;
        protected f<a> ZC;
        private Activity oe;

        b(Fragment fragment) {
            this.FS = fragment;
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
                    this.ZC.a(new a(this.FS, u.E(this.oe).j(e.h(this.oe))));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions options) {
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", options);
        supportStreetViewPanoramaFragment.setArguments(bundle);
        return supportStreetViewPanoramaFragment;
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
        this.aae.ju();
        if (this.aae.gC() == null) {
            return null;
        }
        return ((a) this.aae.gC()).jx();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.aae.setActivity(activity);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.aae.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.aae.onCreateView(inflater, container, savedInstanceState);
    }

    public void onDestroy() {
        this.aae.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.aae.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        this.aae.setActivity(activity);
        this.aae.onInflate(activity, new Bundle(), savedInstanceState);
    }

    public void onLowMemory() {
        this.aae.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.aae.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.aae.onResume();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(outState);
        this.aae.onSaveInstanceState(outState);
    }

    public void setArguments(Bundle args) {
        super.setArguments(args);
    }
}
