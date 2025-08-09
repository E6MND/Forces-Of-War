package org.chromium.content.browser;

import android.view.MotionEvent;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("content")
public class TouchEventSynthesizer {
    static final /* synthetic */ boolean $assertionsDisabled = (!TouchEventSynthesizer.class.desiredAssertionStatus());
    private static final int ACTION_CANCEL = 2;
    private static final int ACTION_END = 3;
    private static final int ACTION_MOVE = 1;
    private static final int ACTION_START = 0;
    private static final int MAX_NUM_POINTERS = 16;
    private final ContentViewCore mContentViewCore;
    private long mDownTimeInMs;
    private final MotionEvent.PointerCoords[] mPointerCoords = new MotionEvent.PointerCoords[16];
    private final MotionEvent.PointerProperties[] mPointerProperties = new MotionEvent.PointerProperties[16];

    TouchEventSynthesizer(ContentViewCore contentViewCore) {
        this.mContentViewCore = contentViewCore;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void setPointer(int index, int x, int y, int id) {
        if ($assertionsDisabled || (index >= 0 && index < 16)) {
            float scaleFactor = this.mContentViewCore.getRenderCoordinates().getDeviceScaleFactor();
            MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
            coords.x = ((float) x) * scaleFactor;
            coords.y = ((float) y) * scaleFactor;
            coords.pressure = 1.0f;
            this.mPointerCoords[index] = coords;
            MotionEvent.PointerProperties properties = new MotionEvent.PointerProperties();
            properties.id = id;
            this.mPointerProperties[index] = properties;
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void inject(int action, int pointerCount, long timeInMs) {
        switch (action) {
            case 0:
                this.mDownTimeInMs = timeInMs;
                MotionEvent event = MotionEvent.obtain(this.mDownTimeInMs, timeInMs, 0, 1, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.mContentViewCore.onTouchEvent(event);
                event.recycle();
                if (pointerCount > 1) {
                    MotionEvent event2 = MotionEvent.obtain(this.mDownTimeInMs, timeInMs, 5, pointerCount, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    this.mContentViewCore.onTouchEvent(event2);
                    event2.recycle();
                    return;
                }
                return;
            case 1:
                MotionEvent event3 = MotionEvent.obtain(this.mDownTimeInMs, timeInMs, 2, pointerCount, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.mContentViewCore.onTouchEvent(event3);
                event3.recycle();
                return;
            case 2:
                MotionEvent event4 = MotionEvent.obtain(this.mDownTimeInMs, timeInMs, 3, 1, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.mContentViewCore.onTouchEvent(event4);
                event4.recycle();
                return;
            case 3:
                if (pointerCount > 1) {
                    MotionEvent event5 = MotionEvent.obtain(this.mDownTimeInMs, timeInMs, 6, pointerCount, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    this.mContentViewCore.onTouchEvent(event5);
                    event5.recycle();
                }
                MotionEvent event6 = MotionEvent.obtain(this.mDownTimeInMs, timeInMs, 1, 1, this.mPointerProperties, this.mPointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.mContentViewCore.onTouchEvent(event6);
                event6.recycle();
                return;
            default:
                return;
        }
    }
}
