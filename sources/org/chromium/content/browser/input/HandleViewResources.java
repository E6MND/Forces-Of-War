package org.chromium.content.browser.input;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("content")
public class HandleViewResources {
    static final /* synthetic */ boolean $assertionsDisabled = (!HandleViewResources.class.desiredAssertionStatus());
    private static final int[] CENTER_HANDLE_ATTRS = {16843463};
    private static final int[] LEFT_HANDLE_ATTRS = {16843461};
    private static final int[] RIGHT_HANDLE_ATTRS = {16843462};

    public static Drawable getLeftHandleDrawable(Context context) {
        return getHandleDrawable(context, LEFT_HANDLE_ATTRS);
    }

    public static Drawable getCenterHandleDrawable(Context context) {
        return getHandleDrawable(context, CENTER_HANDLE_ATTRS);
    }

    public static Drawable getRightHandleDrawable(Context context) {
        return getHandleDrawable(context, RIGHT_HANDLE_ATTRS);
    }

    private static Drawable getHandleDrawable(Context context, int[] attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs);
        Drawable drawable = a.getDrawable(0);
        if (drawable == null) {
            try {
                drawable = context.getResources().getDrawable(a.getResourceId(0, 0));
            } catch (Resources.NotFoundException e) {
            }
        }
        a.recycle();
        return drawable;
    }

    private static Bitmap getHandleBitmap(Context context, int[] attrs) {
        Bitmap bitmap;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs);
        int resId = a.getResourceId(a.getIndex(0), 0);
        Resources res = a.getResources();
        a.recycle();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = config;
        Bitmap bitmap2 = BitmapFactory.decodeResource(res, resId, options);
        if (bitmap2 != null) {
            return bitmap2;
        }
        if (res != context.getResources() && (bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options)) != null) {
            return bitmap;
        }
        Drawable drawable = getHandleDrawable(context, attrs);
        if ($assertionsDisabled || drawable != null) {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            Bitmap canvasBitmap = Bitmap.createBitmap(width, height, config);
            Canvas canvas = new Canvas(canvasBitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
            return canvasBitmap;
        }
        throw new AssertionError();
    }

    @CalledByNative
    private static Bitmap getLeftHandleBitmap(Context context) {
        return getHandleBitmap(context, LEFT_HANDLE_ATTRS);
    }

    @CalledByNative
    private static Bitmap getCenterHandleBitmap(Context context) {
        return getHandleBitmap(context, CENTER_HANDLE_ATTRS);
    }

    @CalledByNative
    private static Bitmap getRightHandleBitmap(Context context) {
        return getHandleBitmap(context, RIGHT_HANDLE_ATTRS);
    }
}
