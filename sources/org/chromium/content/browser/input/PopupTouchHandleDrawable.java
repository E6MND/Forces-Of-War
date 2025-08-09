package org.chromium.content.browser.input;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.content.browser.PositionObserver;

@JNINamespace("content")
public class PopupTouchHandleDrawable extends View {
    static final /* synthetic */ boolean $assertionsDisabled = (!PopupTouchHandleDrawable.class.desiredAssertionStatus());
    static final int CENTER = 1;
    private static final int FADE_IN_DELAY_MS = 300;
    private static final int FADE_IN_DURATION_MS = 200;
    @SuppressLint({"RtlHardcoded"})
    static final int LEFT = 0;
    @SuppressLint({"RtlHardcoded"})
    static final int RIGHT = 2;
    private float mAlpha;
    private final PopupWindow mContainer;
    private final Context mContext;
    private Runnable mDeferredHandleFadeInRunnable;
    private final WeakReference<PopupTouchHandleDrawableDelegate> mDelegate;
    private Drawable mDrawable;
    private long mFadeStartTime;
    /* access modifiers changed from: private */
    public boolean mHasPendingInvalidate;
    private float mHotspotX;
    private float mHotspotY;
    private Runnable mInvalidationRunnable;
    private int mOrientation = -1;
    private final PositionObserver.Listener mParentPositionListener;
    private PositionObserver mParentPositionObserver;
    private int mParentPositionX;
    private int mParentPositionY;
    private int mPositionX;
    private int mPositionY;
    private final int[] mTempScreenCoords = new int[2];
    /* access modifiers changed from: private */
    public boolean mTemporarilyHidden;
    private boolean mVisible;

    public interface PopupTouchHandleDrawableDelegate {
        View getParent();

        PositionObserver getParentPositionObserver();

        boolean isScrollInProgress();

        boolean onTouchHandleEvent(MotionEvent motionEvent);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PopupTouchHandleDrawable(PopupTouchHandleDrawableDelegate delegate) {
        super(delegate.getParent().getContext());
        boolean z = true;
        this.mContext = delegate.getParent().getContext();
        this.mDelegate = new WeakReference<>(delegate);
        this.mContainer = new PopupWindow(this.mContext, (AttributeSet) null, 16843464);
        this.mContainer.setSplitTouchEnabled(true);
        this.mContainer.setClippingEnabled(false);
        this.mContainer.setAnimationStyle(0);
        this.mAlpha = 1.0f;
        this.mVisible = getVisibility() != 0 ? false : z;
        this.mParentPositionListener = new PositionObserver.Listener() {
            public void onPositionChanged(int x, int y) {
                PopupTouchHandleDrawable.this.updateParentPosition(x, y);
            }
        };
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent event) {
        PopupTouchHandleDrawableDelegate delegate = (PopupTouchHandleDrawableDelegate) this.mDelegate.get();
        if (delegate == null) {
            hide();
            return false;
        }
        delegate.getParent().getLocationOnScreen(this.mTempScreenCoords);
        MotionEvent offsetEvent = MotionEvent.obtainNoHistory(event);
        offsetEvent.offsetLocation((event.getRawX() - event.getX()) - ((float) this.mTempScreenCoords[0]), (event.getRawY() - event.getY()) - ((float) this.mTempScreenCoords[1]));
        boolean onTouchHandleEvent = delegate.onTouchHandleEvent(offsetEvent);
        offsetEvent.recycle();
        return onTouchHandleEvent;
    }

    private void setOrientation(int orientation) {
        if (!$assertionsDisabled && (orientation < 0 || orientation > 2)) {
            throw new AssertionError();
        } else if (this.mOrientation != orientation) {
            boolean hadValidOrientation = this.mOrientation != -1;
            this.mOrientation = orientation;
            int oldAdjustedPositionX = getAdjustedPositionX();
            int oldAdjustedPositionY = getAdjustedPositionY();
            switch (orientation) {
                case 0:
                    this.mDrawable = HandleViewResources.getLeftHandleDrawable(this.mContext);
                    this.mHotspotX = ((float) (this.mDrawable.getIntrinsicWidth() * 3)) / 4.0f;
                    break;
                case 2:
                    this.mDrawable = HandleViewResources.getRightHandleDrawable(this.mContext);
                    this.mHotspotX = ((float) this.mDrawable.getIntrinsicWidth()) / 4.0f;
                    break;
                default:
                    this.mDrawable = HandleViewResources.getCenterHandleDrawable(this.mContext);
                    this.mHotspotX = ((float) this.mDrawable.getIntrinsicWidth()) / 2.0f;
                    break;
            }
            this.mHotspotY = 0.0f;
            if (hadValidOrientation) {
                setFocus((float) oldAdjustedPositionX, (float) oldAdjustedPositionY);
            }
            this.mDrawable.setAlpha((int) (255.0f * this.mAlpha));
            scheduleInvalidate();
        }
    }

    /* access modifiers changed from: private */
    public void updateParentPosition(int parentPositionX, int parentPositionY) {
        if (this.mParentPositionX != parentPositionX || this.mParentPositionY != parentPositionY) {
            this.mParentPositionX = parentPositionX;
            this.mParentPositionY = parentPositionY;
            temporarilyHide();
        }
    }

    private int getContainerPositionX() {
        return this.mParentPositionX + this.mPositionX;
    }

    private int getContainerPositionY() {
        return this.mParentPositionY + this.mPositionY;
    }

    private void updatePosition() {
        this.mContainer.update(getContainerPositionX(), getContainerPositionY(), getRight() - getLeft(), getBottom() - getTop());
    }

    private void updateVisibility() {
        boolean visible;
        int i = 0;
        if (!this.mVisible || this.mTemporarilyHidden) {
            visible = false;
        } else {
            visible = true;
        }
        if (!visible) {
            i = 4;
        }
        setVisibility(i);
    }

    private void updateAlpha() {
        if (this.mAlpha != 1.0f) {
            this.mAlpha = Math.min(1.0f, ((float) (AnimationUtils.currentAnimationTimeMillis() - this.mFadeStartTime)) / 200.0f);
            this.mDrawable.setAlpha((int) (255.0f * this.mAlpha));
            scheduleInvalidate();
        }
    }

    private void temporarilyHide() {
        this.mTemporarilyHidden = true;
        updateVisibility();
        rescheduleFadeIn();
    }

    /* access modifiers changed from: private */
    public void doInvalidate() {
        if (this.mContainer.isShowing()) {
            updatePosition();
            updateVisibility();
            invalidate();
        }
    }

    private void scheduleInvalidate() {
        if (this.mInvalidationRunnable == null) {
            this.mInvalidationRunnable = new Runnable() {
                public void run() {
                    boolean unused = PopupTouchHandleDrawable.this.mHasPendingInvalidate = false;
                    PopupTouchHandleDrawable.this.doInvalidate();
                }
            };
        }
        if (!this.mHasPendingInvalidate) {
            this.mHasPendingInvalidate = true;
            ApiCompatibilityUtils.postOnAnimation(this, this.mInvalidationRunnable);
        }
    }

    /* access modifiers changed from: private */
    public void rescheduleFadeIn() {
        if (this.mDeferredHandleFadeInRunnable == null) {
            this.mDeferredHandleFadeInRunnable = new Runnable() {
                public void run() {
                    if (PopupTouchHandleDrawable.this.isScrollInProgress()) {
                        PopupTouchHandleDrawable.this.rescheduleFadeIn();
                        return;
                    }
                    boolean unused = PopupTouchHandleDrawable.this.mTemporarilyHidden = false;
                    PopupTouchHandleDrawable.this.beginFadeIn();
                }
            };
        }
        removeCallbacks(this.mDeferredHandleFadeInRunnable);
        ApiCompatibilityUtils.postOnAnimationDelayed(this, this.mDeferredHandleFadeInRunnable, 300);
    }

    /* access modifiers changed from: private */
    public void beginFadeIn() {
        if (getVisibility() != 0) {
            this.mAlpha = 0.0f;
            this.mFadeStartTime = AnimationUtils.currentAnimationTimeMillis();
            doInvalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mDrawable == null) {
            setMeasuredDimension(0, 0);
        } else {
            setMeasuredDimension(this.mDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas c) {
        if (this.mDrawable != null) {
            updateAlpha();
            this.mDrawable.setBounds(0, 0, getRight() - getLeft(), getBottom() - getTop());
            this.mDrawable.draw(c);
        }
    }

    private int getAdjustedPositionX() {
        return this.mPositionX + Math.round(this.mHotspotX);
    }

    private int getAdjustedPositionY() {
        return this.mPositionY + Math.round(this.mHotspotY);
    }

    /* access modifiers changed from: private */
    public boolean isScrollInProgress() {
        PopupTouchHandleDrawableDelegate delegate = (PopupTouchHandleDrawableDelegate) this.mDelegate.get();
        if (delegate != null) {
            return delegate.isScrollInProgress();
        }
        hide();
        return false;
    }

    @CalledByNative
    private void show() {
        if (!this.mContainer.isShowing()) {
            PopupTouchHandleDrawableDelegate delegate = (PopupTouchHandleDrawableDelegate) this.mDelegate.get();
            if (delegate == null) {
                hide();
                return;
            }
            this.mParentPositionObserver = delegate.getParentPositionObserver();
            if ($assertionsDisabled || this.mParentPositionObserver != null) {
                updateParentPosition(this.mParentPositionObserver.getPositionX(), this.mParentPositionObserver.getPositionY());
                this.mParentPositionObserver.addListener(this.mParentPositionListener);
                this.mContainer.setContentView(this);
                this.mContainer.showAtLocation(delegate.getParent(), 0, getContainerPositionX(), getContainerPositionY());
                return;
            }
            throw new AssertionError();
        }
    }

    @CalledByNative
    private void hide() {
        this.mTemporarilyHidden = false;
        this.mContainer.dismiss();
        if (this.mParentPositionObserver != null) {
            this.mParentPositionObserver.removeListener(this.mParentPositionListener);
            this.mParentPositionObserver = null;
        }
    }

    @CalledByNative
    private void setRightOrientation() {
        setOrientation(2);
    }

    @CalledByNative
    private void setLeftOrientation() {
        setOrientation(0);
    }

    @CalledByNative
    private void setCenterOrientation() {
        setOrientation(1);
    }

    @CalledByNative
    private void setOpacity(float alpha) {
    }

    @CalledByNative
    private void setFocus(float focusX, float focusY) {
        int x = ((int) focusX) - Math.round(this.mHotspotX);
        int y = ((int) focusY) - Math.round(this.mHotspotY);
        if (this.mPositionX != x || this.mPositionY != y) {
            this.mPositionX = x;
            this.mPositionY = y;
            if (isScrollInProgress()) {
                temporarilyHide();
            } else {
                scheduleInvalidate();
            }
        }
    }

    @CalledByNative
    private void setVisible(boolean visible) {
        this.mVisible = visible;
        if (getVisibility() != (visible ? 0 : 4)) {
            scheduleInvalidate();
        }
    }

    @CalledByNative
    private boolean intersectsWith(float x, float y, float width, float height) {
        if (this.mDrawable == null) {
            return false;
        }
        int drawableWidth = this.mDrawable.getIntrinsicWidth();
        int drawableHeight = this.mDrawable.getIntrinsicHeight();
        if (x >= ((float) (this.mPositionX + drawableWidth)) || y >= ((float) (this.mPositionY + drawableHeight)) || x + width <= ((float) this.mPositionX) || y + height <= ((float) this.mPositionY)) {
            return false;
        }
        return true;
    }
}
