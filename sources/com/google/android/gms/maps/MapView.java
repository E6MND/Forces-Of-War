package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.internal.hn;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class MapView extends FrameLayout {
    private GoogleMap ZA;
    private final b ZD;

    static class a implements LifecycleDelegate {
        private final ViewGroup ZE;
        private final IMapViewDelegate ZF;
        private View ZG;

        public a(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.ZF = (IMapViewDelegate) hn.f(iMapViewDelegate);
            this.ZE = (ViewGroup) hn.f(viewGroup);
        }

        public IMapViewDelegate jv() {
            return this.ZF;
        }

        public void onCreate(Bundle savedInstanceState) {
            try {
                this.ZF.onCreate(savedInstanceState);
                this.ZG = (View) e.e(this.ZF.getView());
                this.ZE.removeAllViews();
                this.ZE.addView(this.ZG);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public void onDestroy() {
            try {
                this.ZF.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        public void onInflate(Activity activity, Bundle attrs, Bundle savedInstanceState) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.ZF.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.ZF.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.ZF.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle outState) {
            try {
                this.ZF.onSaveInstanceState(outState);
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
        protected f<a> ZC;
        private final ViewGroup ZH;
        private final GoogleMapOptions ZI;
        private final Context mContext;

        b(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.ZH = viewGroup;
            this.mContext = context;
            this.ZI = googleMapOptions;
        }

        /* access modifiers changed from: protected */
        public void a(f<a> fVar) {
            this.ZC = fVar;
            ju();
        }

        public void ju() {
            if (this.ZC != null && gC() == null) {
                try {
                    this.ZC.a(new a(this.ZH, u.E(this.mContext).a(e.h(this.mContext), this.ZI)));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    public MapView(Context context) {
        super(context);
        this.ZD = new b(this, context, (GoogleMapOptions) null);
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ZD = new b(this, context, GoogleMapOptions.createFromAttributes(context, attrs));
    }

    public MapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ZD = new b(this, context, GoogleMapOptions.createFromAttributes(context, attrs));
    }

    public MapView(Context context, GoogleMapOptions options) {
        super(context);
        this.ZD = new b(this, context, options);
    }

    public final GoogleMap getMap() {
        if (this.ZA != null) {
            return this.ZA;
        }
        this.ZD.ju();
        if (this.ZD.gC() == null) {
            return null;
        }
        try {
            this.ZA = new GoogleMap(((a) this.ZD.gC()).jv().getMap());
            return this.ZA;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void onCreate(Bundle savedInstanceState) {
        this.ZD.onCreate(savedInstanceState);
        if (this.ZD.gC() == null) {
            b bVar = this.ZD;
            b.b((FrameLayout) this);
        }
    }

    public final void onDestroy() {
        this.ZD.onDestroy();
    }

    public final void onLowMemory() {
        this.ZD.onLowMemory();
    }

    public final void onPause() {
        this.ZD.onPause();
    }

    public final void onResume() {
        this.ZD.onResume();
    }

    public final void onSaveInstanceState(Bundle outState) {
        this.ZD.onSaveInstanceState(outState);
    }
}
