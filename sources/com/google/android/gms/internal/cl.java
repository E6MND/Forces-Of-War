package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public final class cl extends FrameLayout implements View.OnClickListener {
    private final ImageButton oP;
    private final Activity oe;

    public cl(Activity activity, int i) {
        super(activity);
        this.oe = activity;
        setOnClickListener(this);
        this.oP = new ImageButton(activity);
        this.oP.setImageResource(17301527);
        this.oP.setBackgroundColor(0);
        this.oP.setOnClickListener(this);
        this.oP.setPadding(0, 0, 0, 0);
        int a = eu.a((Context) activity, i);
        addView(this.oP, new FrameLayout.LayoutParams(a, a, 17));
    }

    public void j(boolean z) {
        this.oP.setVisibility(z ? 4 : 0);
    }

    public void onClick(View view) {
        this.oe.finish();
    }
}
