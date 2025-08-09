package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.av;

public final class AdView extends ViewGroup {
    private final av kv;

    public AdView(Context context) {
        super(context);
        this.kv = new av(this);
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.kv = new av(this, attrs, false);
    }

    public AdView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.kv = new av(this, attrs, false);
    }

    public void destroy() {
        this.kv.destroy();
    }

    public AdListener getAdListener() {
        return this.kv.getAdListener();
    }

    public AdSize getAdSize() {
        return this.kv.getAdSize();
    }

    public String getAdUnitId() {
        return this.kv.getAdUnitId();
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.kv.getInAppPurchaseListener();
    }

    public void loadAd(AdRequest adRequest) {
        this.kv.a(adRequest.O());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i = ((right - left) - measuredWidth) / 2;
            int i2 = ((bottom - top) - measuredHeight) / 2;
            childAt.layout(i, i2, measuredWidth + i, measuredHeight + i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int i2 = 0;
        View childAt = getChildAt(0);
        AdSize adSize = getAdSize();
        if (childAt != null && childAt.getVisibility() != 8) {
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            i = childAt.getMeasuredWidth();
            i2 = childAt.getMeasuredHeight();
        } else if (adSize != null) {
            Context context = getContext();
            i = adSize.getWidthInPixels(context);
            i2 = adSize.getHeightInPixels(context);
        } else {
            i = 0;
        }
        setMeasuredDimension(View.resolveSize(Math.max(i, getSuggestedMinimumWidth()), widthMeasureSpec), View.resolveSize(Math.max(i2, getSuggestedMinimumHeight()), heightMeasureSpec));
    }

    public void pause() {
        this.kv.pause();
    }

    public void resume() {
        this.kv.resume();
    }

    public void setAdListener(AdListener adListener) {
        this.kv.setAdListener(adListener);
    }

    public void setAdSize(AdSize adSize) {
        this.kv.setAdSizes(adSize);
    }

    public void setAdUnitId(String adUnitId) {
        this.kv.setAdUnitId(adUnitId);
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        this.kv.setInAppPurchaseListener(inAppPurchaseListener);
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String publicKey) {
        this.kv.setPlayStorePurchaseParams(playStorePurchaseListener, publicKey);
    }
}
