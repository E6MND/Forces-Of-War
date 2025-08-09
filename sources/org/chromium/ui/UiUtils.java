package org.chromium.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicInteger;

public class UiUtils {
    private static final float KEYBOARD_DETECT_BOTTOM_THRESHOLD_DP = 100.0f;
    private static final int KEYBOARD_RETRY_ATTEMPTS = 10;
    private static final long KEYBOARD_RETRY_DELAY_MS = 100;
    private static final String TAG = "UiUtils";
    private static KeyboardShowingDelegate sKeyboardShowingDelegate;

    public interface KeyboardShowingDelegate {
        boolean disableKeyboardCheck(Context context, View view);
    }

    private UiUtils() {
    }

    public static void setKeyboardShowingDelegate(KeyboardShowingDelegate delegate) {
        sKeyboardShowingDelegate = delegate;
    }

    public static void showKeyboard(final View view) {
        final Handler handler = new Handler();
        final AtomicInteger attempt = new AtomicInteger();
        new Runnable() {
            public void run() {
                try {
                    ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
                } catch (IllegalArgumentException e) {
                    if (attempt.incrementAndGet() <= 10) {
                        handler.postDelayed(this, UiUtils.KEYBOARD_RETRY_DELAY_MS);
                    } else {
                        Log.e(UiUtils.TAG, "Unable to open keyboard.  Giving up.", e);
                    }
                }
            }
        }.run();
    }

    public static boolean hideKeyboard(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isKeyboardShowing(Context context, View view) {
        View rootView;
        if ((sKeyboardShowingDelegate != null && sKeyboardShowingDelegate.disableKeyboardCheck(context, view)) || (rootView = view.getRootView()) == null) {
            return false;
        }
        Rect appRect = new Rect();
        rootView.getWindowVisibleDisplayFrame(appRect);
        if (((float) Math.abs(rootView.getHeight() - appRect.height())) / context.getResources().getDisplayMetrics().density > KEYBOARD_DETECT_BOTTOM_THRESHOLD_DP) {
            return true;
        }
        return false;
    }

    public static int insertBefore(ViewGroup container, View newView, View existingView) {
        return insertView(container, newView, existingView, false);
    }

    public static int insertAfter(ViewGroup container, View newView, View existingView) {
        return insertView(container, newView, existingView, true);
    }

    private static int insertView(ViewGroup container, View newView, View existingView, boolean after) {
        int index = container.indexOfChild(newView);
        if (index >= 0) {
            return index;
        }
        int index2 = container.indexOfChild(existingView);
        if (index2 < 0) {
            return -1;
        }
        if (after) {
            index2++;
        }
        container.addView(newView, index2);
        return index2;
    }

    public static Bitmap generateScaledScreenshot(View currentView, int maximumDimension, Bitmap.Config bitmapConfig) {
        Bitmap screenshot = null;
        boolean drawingCacheEnabled = currentView.isDrawingCacheEnabled();
        try {
            prepareViewHierarchyForScreenshot(currentView, true);
            if (!drawingCacheEnabled) {
                currentView.setDrawingCacheEnabled(true);
            }
            Bitmap originalBitmap = currentView.getDrawingCache();
            if (originalBitmap != null) {
                double originalHeight = (double) originalBitmap.getHeight();
                double originalWidth = (double) originalBitmap.getWidth();
                int newWidth = (int) originalWidth;
                int newHeight = (int) originalHeight;
                if (maximumDimension > 0) {
                    double scale = ((double) maximumDimension) / Math.max(originalWidth, originalHeight);
                    newWidth = (int) Math.round(originalWidth * scale);
                    newHeight = (int) Math.round(originalHeight * scale);
                }
                Bitmap scaledScreenshot = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                if (scaledScreenshot.getConfig() != bitmapConfig) {
                    screenshot = scaledScreenshot.copy(bitmapConfig, false);
                    scaledScreenshot.recycle();
                } else {
                    screenshot = scaledScreenshot;
                }
            } else if (currentView.getMeasuredHeight() > 0 && currentView.getMeasuredWidth() > 0) {
                double originalHeight2 = (double) currentView.getMeasuredHeight();
                double originalWidth2 = (double) currentView.getMeasuredWidth();
                int newWidth2 = (int) originalWidth2;
                int newHeight2 = (int) originalHeight2;
                if (maximumDimension > 0) {
                    double scale2 = ((double) maximumDimension) / Math.max(originalWidth2, originalHeight2);
                    newWidth2 = (int) Math.round(originalWidth2 * scale2);
                    newHeight2 = (int) Math.round(originalHeight2 * scale2);
                }
                Bitmap bitmap = Bitmap.createBitmap(newWidth2, newHeight2, bitmapConfig);
                Canvas canvas = new Canvas(bitmap);
                canvas.scale((float) (((double) newWidth2) / originalWidth2), (float) (((double) newHeight2) / originalHeight2));
                currentView.draw(canvas);
                screenshot = bitmap;
            }
            if (!drawingCacheEnabled) {
                currentView.setDrawingCacheEnabled(false);
            }
            prepareViewHierarchyForScreenshot(currentView, false);
        } catch (OutOfMemoryError e) {
            Log.d(TAG, "Unable to capture screenshot and scale it down." + e.getMessage());
            if (!drawingCacheEnabled) {
                currentView.setDrawingCacheEnabled(false);
            }
            prepareViewHierarchyForScreenshot(currentView, false);
        } catch (Throwable th) {
            if (!drawingCacheEnabled) {
                currentView.setDrawingCacheEnabled(false);
            }
            prepareViewHierarchyForScreenshot(currentView, false);
            throw th;
        }
        return screenshot;
    }

    private static void prepareViewHierarchyForScreenshot(View view, boolean takingScreenshot) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                prepareViewHierarchyForScreenshot(viewGroup.getChildAt(i), takingScreenshot);
            }
        } else if (view instanceof SurfaceView) {
            view.setWillNotDraw(!takingScreenshot);
        }
    }
}
