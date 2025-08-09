package com.uken.android.common;

import android.app.Activity;
import android.os.Bundle;
import com.uken.android.forcesofwar.R;

public class OfferWallActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        setContentView(R.layout.offerwall);
    }
}
