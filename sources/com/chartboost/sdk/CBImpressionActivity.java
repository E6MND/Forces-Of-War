package com.chartboost.sdk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public final class CBImpressionActivity extends Activity {
    public static final String PARAM_FULLSCREEN = "paramFullscreen";
    protected Chartboost a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra(PARAM_FULLSCREEN, false)) {
            getWindow().addFlags(1024);
        }
        getWindow().setWindowAnimations(0);
        setContentView(new RelativeLayout(this));
        this.a = Chartboost.sharedChartboost();
        this.a.a(this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.a.a((Activity) this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.a.b((Activity) this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        if (!this.a.a()) {
            super.onBackPressed();
        }
    }
}
