package org.chromium.ui.gfx;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.ui.R;

@JNINamespace("gfx")
public class ViewConfigurationHelper {
    static final /* synthetic */ boolean $assertionsDisabled = (!ViewConfigurationHelper.class.desiredAssertionStatus());
    private static final float MIN_SCALING_SPAN_MM = 27.0f;
    private static final float MIN_SCALING_TOUCH_MAJOR_DIP = 48.0f;
    private final Context mAppContext;
    private ViewConfiguration mViewConfiguration = ViewConfiguration.get(this.mAppContext);

    private native void nativeUpdateSharedViewConfiguration(int i, int i2, int i3, int i4, int i5, int i6);

    private ViewConfigurationHelper(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    private void registerListener() {
        this.mAppContext.registerComponentCallbacks(new ComponentCallbacks() {
            public void onConfigurationChanged(Configuration configuration) {
                ViewConfigurationHelper.this.updateNativeViewConfigurationIfNecessary();
            }

            public void onLowMemory() {
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateNativeViewConfigurationIfNecessary() {
        ViewConfiguration configuration = ViewConfiguration.get(this.mAppContext);
        if (this.mViewConfiguration != configuration) {
            this.mViewConfiguration = configuration;
            nativeUpdateSharedViewConfiguration(getScaledMaximumFlingVelocity(), getScaledMinimumFlingVelocity(), getScaledTouchSlop(), getScaledDoubleTapSlop(), getScaledMinScalingSpan(), getScaledMinScalingTouchMajor());
        }
    }

    @CalledByNative
    private static int getDoubleTapTimeout() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    @CalledByNative
    private static int getLongPressTimeout() {
        return ViewConfiguration.getLongPressTimeout();
    }

    @CalledByNative
    private static int getTapTimeout() {
        return ViewConfiguration.getTapTimeout();
    }

    @CalledByNative
    private static float getScrollFriction() {
        return ViewConfiguration.getScrollFriction();
    }

    @CalledByNative
    private int getScaledMaximumFlingVelocity() {
        return this.mViewConfiguration.getScaledMaximumFlingVelocity();
    }

    @CalledByNative
    private int getScaledMinimumFlingVelocity() {
        return this.mViewConfiguration.getScaledMinimumFlingVelocity();
    }

    @CalledByNative
    private int getScaledTouchSlop() {
        return this.mViewConfiguration.getScaledTouchSlop();
    }

    @CalledByNative
    private int getScaledDoubleTapSlop() {
        return this.mViewConfiguration.getScaledDoubleTapSlop();
    }

    @CalledByNative
    private int getScaledMinScalingSpan() {
        Resources res = this.mAppContext.getResources();
        int id = res.getIdentifier("config_minScalingSpan", "dimen", "android");
        if (id == 0) {
            id = R.dimen.config_min_scaling_span;
        }
        try {
            return res.getDimensionPixelSize(id);
        } catch (Resources.NotFoundException e) {
            if ($assertionsDisabled) {
                return (int) TypedValue.applyDimension(5, MIN_SCALING_SPAN_MM, res.getDisplayMetrics());
            }
            throw new AssertionError("MinScalingSpan resource lookup failed.");
        }
    }

    @CalledByNative
    private int getScaledMinScalingTouchMajor() {
        Resources res = this.mAppContext.getResources();
        int id = res.getIdentifier("config_minScalingTouchMajor", "dimen", "android");
        if (id == 0) {
            id = R.dimen.config_min_scaling_touch_major;
        }
        try {
            return res.getDimensionPixelSize(id);
        } catch (Resources.NotFoundException e) {
            if ($assertionsDisabled) {
                return (int) TypedValue.applyDimension(1, MIN_SCALING_TOUCH_MAJOR_DIP, res.getDisplayMetrics());
            }
            throw new AssertionError("MinScalingTouchMajor resource lookup failed.");
        }
    }

    @CalledByNative
    private static ViewConfigurationHelper createWithListener(Context context) {
        ViewConfigurationHelper viewConfigurationHelper = new ViewConfigurationHelper(context);
        viewConfigurationHelper.registerListener();
        return viewConfigurationHelper;
    }
}
