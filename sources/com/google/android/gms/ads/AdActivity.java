package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.internal.cn;
import com.google.android.gms.internal.co;
import com.google.android.gms.internal.ev;

public final class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private co kp;

    private void N() {
        if (this.kp != null) {
            try {
                this.kp.N();
            } catch (RemoteException e) {
                ev.c("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.kp = cn.a(this);
        if (this.kp == null) {
            ev.D("Could not create ad overlay.");
            finish();
            return;
        }
        try {
            this.kp.onCreate(savedInstanceState);
        } catch (RemoteException e) {
            ev.c("Could not forward onCreate to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            if (this.kp != null) {
                this.kp.onDestroy();
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        try {
            if (this.kp != null) {
                this.kp.onPause();
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        try {
            if (this.kp != null) {
                this.kp.onRestart();
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            if (this.kp != null) {
                this.kp.onResume();
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        try {
            if (this.kp != null) {
                this.kp.onSaveInstanceState(outState);
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            if (this.kp != null) {
                this.kp.onStart();
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        try {
            if (this.kp != null) {
                this.kp.onStop();
            }
        } catch (RemoteException e) {
            ev.c("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        N();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        N();
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        N();
    }
}
