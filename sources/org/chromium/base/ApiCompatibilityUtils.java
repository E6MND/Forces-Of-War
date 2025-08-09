package org.chromium.base;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

public class ApiCompatibilityUtils {
    private static final String TAG = "ApiCompatibilityUtils";

    private ApiCompatibilityUtils() {
    }

    public static boolean isLayoutRtl(View view) {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (view.getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public static int getLayoutDirection(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 17) {
            return configuration.getLayoutDirection();
        }
        return 0;
    }

    public static boolean isPrintingSupported() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean isHTMLClipboardSupported() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static void setLayoutDirection(View view, int layoutDirection) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayoutDirection(layoutDirection);
        }
    }

    public static void setTextAlignment(View view, int textAlignment) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setTextAlignment(textAlignment);
        }
    }

    public static void setTextDirection(View view, int textDirection) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setTextDirection(textDirection);
        }
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams layoutParams, int end) {
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginEnd(end);
        } else {
            layoutParams.rightMargin = end;
        }
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams layoutParams) {
        if (Build.VERSION.SDK_INT >= 17) {
            return layoutParams.getMarginEnd();
        }
        return layoutParams.rightMargin;
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams layoutParams, int start) {
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(start);
        } else {
            layoutParams.leftMargin = start;
        }
    }

    public static int getMarginStart(ViewGroup.MarginLayoutParams layoutParams) {
        if (Build.VERSION.SDK_INT >= 17) {
            return layoutParams.getMarginStart();
        }
        return layoutParams.leftMargin;
    }

    public static void setPaddingRelative(View view, int start, int top, int end, int bottom) {
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(start, top, end, bottom);
        } else {
            view.setPadding(start, top, end, bottom);
        }
    }

    public static int getPaddingStart(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getPaddingStart();
        }
        return view.getPaddingLeft();
    }

    public static int getPaddingEnd(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getPaddingEnd();
        }
        return view.getPaddingRight();
    }

    public static void setCompoundDrawablesRelative(TextView textView, Drawable start, Drawable top, Drawable end, Drawable bottom) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT == 17) {
            boolean isRtl = isLayoutRtl(textView);
            if (isRtl) {
                drawable = end;
            } else {
                drawable = start;
            }
            if (!isRtl) {
                start = end;
            }
            textView.setCompoundDrawables(drawable, top, start, bottom);
        } else if (Build.VERSION.SDK_INT > 17) {
            textView.setCompoundDrawablesRelative(start, top, end, bottom);
        } else {
            textView.setCompoundDrawables(start, top, end, bottom);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, Drawable start, Drawable top, Drawable end, Drawable bottom) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT == 17) {
            boolean isRtl = isLayoutRtl(textView);
            if (isRtl) {
                drawable = end;
            } else {
                drawable = start;
            }
            if (!isRtl) {
                start = end;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, top, start, bottom);
        } else if (Build.VERSION.SDK_INT > 17) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, int start, int top, int end, int bottom) {
        int i;
        if (Build.VERSION.SDK_INT == 17) {
            boolean isRtl = isLayoutRtl(textView);
            if (isRtl) {
                i = end;
            } else {
                i = start;
            }
            if (!isRtl) {
                start = end;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(i, top, start, bottom);
        } else if (Build.VERSION.SDK_INT > 17) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    public static void postInvalidateOnAnimation(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void postOnAnimation(View view, Runnable action) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(action);
        } else {
            view.postDelayed(action, getFrameTime());
        }
    }

    public static void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(action, delayMillis);
        } else {
            view.postDelayed(action, getFrameTime() + delayMillis);
        }
    }

    private static long getFrameTime() {
        if (Build.VERSION.SDK_INT >= 11) {
            return ValueAnimator.getFrameDelay();
        }
        return 10;
    }

    public static void setContentDescriptionForRemoteView(RemoteViews remoteViews, int viewId, CharSequence contentDescription) {
        if (Build.VERSION.SDK_INT >= 15) {
            remoteViews.setContentDescription(viewId, contentDescription);
        }
    }

    public static void startActivity(Context context, Intent intent, Bundle options) {
        if (Build.VERSION.SDK_INT >= 16) {
            context.startActivity(intent, options);
        } else {
            context.startActivity(intent);
        }
    }

    public static Bundle toBundle(ActivityOptions options) {
        if (Build.VERSION.SDK_INT >= 16) {
            return options.toBundle();
        }
        return null;
    }

    public static void setBackgroundForView(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } else {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }

    public static void setImageAlpha(ImageView iv, int alpha) {
        if (Build.VERSION.SDK_INT >= 16) {
            iv.setImageAlpha(alpha);
        } else {
            iv.setAlpha(alpha);
        }
    }

    public static String getCreatorPackage(PendingIntent intent) {
        if (Build.VERSION.SDK_INT >= 17) {
            return intent.getCreatorPackage();
        }
        return intent.getTargetPackage();
    }

    public static Notification build(Notification.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16) {
            return builder.build();
        }
        return builder.getNotification();
    }

    @TargetApi(17)
    public static boolean isDeviceProvisioned(Context context) {
        if (Build.VERSION.SDK_INT >= 17 && context != null && context.getContentResolver() != null && Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 0) {
            return false;
        }
        return true;
    }
}
