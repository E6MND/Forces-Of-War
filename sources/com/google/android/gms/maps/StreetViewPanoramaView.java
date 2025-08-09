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
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class StreetViewPanoramaView extends FrameLayout {
    private StreetViewPanorama ZQ;
    private final a ZZ;

    static class a extends com.google.android.gms.dynamic.a<b> {
        protected f<b> ZC;
        private final ViewGroup ZH;
        private final StreetViewPanoramaOptions aaa;
        private final Context mContext;

        a(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.ZH = viewGroup;
            this.mContext = context;
            this.aaa = streetViewPanoramaOptions;
        }

        /* access modifiers changed from: protected */
        public void a(f<b> fVar) {
            this.ZC = fVar;
            ju();
        }

        public void ju() {
            if (this.ZC != null && gC() == null) {
                try {
                    this.ZC.a(new b(this.ZH, u.E(this.mContext).a(e.h(this.mContext), this.aaa)));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    static class b implements LifecycleDelegate {
        private final ViewGroup ZE;
        private final IStreetViewPanoramaViewDelegate aab;
        private View aac;

        public b(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.aab = (IStreetViewPanoramaViewDelegate) hn.f(iStreetViewPanoramaViewDelegate);
            this.ZE = (ViewGroup) hn.f(viewGroup);
        }

        public IStreetViewPanoramaViewDelegate jB() {
            return this.aab;
        }

        public void onCreate(Bundle savedInstanceState) {
            try {
                this.aab.onCreate(savedInstanceState);
                this.aac = (View) e.e(this.aab.getView());
                this.ZE.removeAllViews();
                this.ZE.addView(this.aac);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onDestroy() {
            try {
                this.aab.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onInflate(Activity activity, Bundle attrs, Bundle savedInstanceState) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.aab.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.aab.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.aab.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle outState) {
            try {
                this.aab.onSaveInstanceState(outState);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public void onStop() {
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.ZZ = new a(this, context, (StreetViewPanoramaOptions) null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ZZ = new a(this, context, (StreetViewPanoramaOptions) null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ZZ = new a(this, context, (StreetViewPanoramaOptions) null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions options) {
        super(context);
        this.ZZ = new a(this, context, options);
    }

    public final StreetViewPanorama getStreetViewPanorama() {
        if (this.ZQ != null) {
            return this.ZQ;
        }
        this.ZZ.ju();
        if (this.ZZ.gC() == null) {
            return null;
        }
        try {
            this.ZQ = new StreetViewPanorama(((b) this.ZZ.gC()).jB().getStreetViewPanorama());
            return this.ZQ;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void onCreate(Bundle savedInstanceState) {
        this.ZZ.onCreate(savedInstanceState);
        if (this.ZZ.gC() == null) {
            a aVar = this.ZZ;
            a.b((FrameLayout) this);
        }
    }

    public final void onDestroy() {
        this.ZZ.onDestroy();
    }

    public final void onLowMemory() {
        this.ZZ.onLowMemory();
    }

    public final void onPause() {
        this.ZZ.onPause();
    }

    public final void onResume() {
        this.ZZ.onResume();
    }

    public final void onSaveInstanceState(Bundle outState) {
        this.ZZ.onSaveInstanceState(outState);
    }
}
