package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.CalledByNative;
import org.chromium.base.CommandLine;
import org.chromium.base.JNINamespace;
import org.chromium.base.ObserverList;
import org.chromium.base.TraceEvent;
import org.chromium.base.VisibleForTesting;
import org.chromium.content.R;
import org.chromium.content.browser.PopupZoomer;
import org.chromium.content.browser.ScreenOrientationListener;
import org.chromium.content.browser.SelectActionModeCallback;
import org.chromium.content.browser.accessibility.AccessibilityInjector;
import org.chromium.content.browser.accessibility.BrowserAccessibilityManager;
import org.chromium.content.browser.input.AdapterInputConnection;
import org.chromium.content.browser.input.GamepadList;
import org.chromium.content.browser.input.ImeAdapter;
import org.chromium.content.browser.input.InputMethodManagerWrapper;
import org.chromium.content.browser.input.PastePopupMenu;
import org.chromium.content.browser.input.PopupTouchHandleDrawable;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.content.browser.input.SelectPopupDialog;
import org.chromium.content.browser.input.SelectPopupDropdown;
import org.chromium.content.browser.input.SelectPopupItem;
import org.chromium.content.common.ContentSwitches;
import org.chromium.content_public.browser.GestureStateListener;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.PageTransition;
import org.chromium.ui.base.ViewAndroid;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.gfx.DeviceDisplayInfo;

@JNINamespace("content")
public class ContentViewCore implements AccessibilityManager.AccessibilityStateChangeListener, ScreenOrientationListener.ScreenOrientationObserver {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int INVALID_RENDER_PROCESS_PID = 0;
    private static final int IS_LONG_PRESS = 1;
    private static final int IS_LONG_TAP = 2;
    private static final ZoomControlsDelegate NO_OP_ZOOM_CONTROLS_DELEGATE = new ZoomControlsDelegate() {
        public void invokeZoomPicker() {
        }

        public void dismissZoomPicker() {
        }

        public void updateZoomControls() {
        }
    };
    private static final String TAG = "ContentViewCore";
    private static final float ZOOM_CONTROLS_EPSILON = 0.007f;
    private AccessibilityInjector mAccessibilityInjector;
    /* access modifiers changed from: private */
    public final AccessibilityManager mAccessibilityManager;
    private ContentObserver mAccessibilityScriptInjectionObserver;
    private SelectActionModeCallback.ActionHandler mActionHandler;
    /* access modifiers changed from: private */
    public ActionMode mActionMode;
    private ImeAdapter.AdapterInputConnectionFactory mAdapterInputConnectionFactory;
    private BrowserAccessibilityManager mBrowserAccessibilityManager;
    /* access modifiers changed from: private */
    public ViewGroup mContainerView;
    private InternalAccessDelegate mContainerViewInternals;
    private ContentSettings mContentSettings;
    private ContentViewClient mContentViewClient;
    /* access modifiers changed from: private */
    public final Context mContext;
    private float mCurrentTouchOffsetX;
    private float mCurrentTouchOffsetY;
    private ContentViewDownloadDelegate mDownloadDelegate;
    private final Editable mEditable;
    private Runnable mFakeMouseMoveRunnable = null;
    /* access modifiers changed from: private */
    public final Rect mFocusPreOSKViewportRect = new Rect();
    /* access modifiers changed from: private */
    public boolean mFocusedNodeEditable;
    private boolean mFullscreenRequiredForOrientationLock = true;
    private final ObserverList<GestureStateListener> mGestureStateListeners;
    private final ObserverList.RewindableIterator<GestureStateListener> mGestureStateListenersIterator;
    private boolean mHasInsertion;
    private boolean mHasSelection;
    /* access modifiers changed from: private */
    public ImeAdapter mImeAdapter;
    private AdapterInputConnection mInputConnection;
    private InputMethodManagerWrapper mInputMethodManagerWrapper;
    private final Map<String, Pair<Object, Class>> mJavaScriptInterfaces = new HashMap();
    private String mLastSelectedText;
    private int mLastTapX;
    private int mLastTapY;
    private boolean mNativeAccessibilityAllowed;
    private boolean mNativeAccessibilityEnabled;
    /* access modifiers changed from: private */
    public long mNativeContentViewCore = 0;
    private long mNativeSelectPopupSourceFrame = 0;
    private PastePopupMenu mPastePopupMenu;
    private int mPhysicalBackingHeightPix;
    private int mPhysicalBackingWidthPix;
    /* access modifiers changed from: private */
    public PopupZoomer mPopupZoomer;
    /* access modifiers changed from: private */
    public PositionObserver mPositionObserver;
    private int mPotentiallyActiveFlingCount;
    private boolean mPreserveSelectionOnNextLossOfFocus;
    /* access modifiers changed from: private */
    public final RenderCoordinates mRenderCoordinates;
    private final HashSet<Object> mRetainedJavaScriptObjects = new HashSet<>();
    private SelectPopup mSelectPopup;
    private boolean mShouldSetAccessibilityFocusOnPageLoad;
    private SmartClipDataListener mSmartClipDataListener = null;
    private int mSmartClipOffsetX;
    private int mSmartClipOffsetY;
    private int mTopControlsLayoutHeightPix;
    private boolean mTouchExplorationEnabled;
    private PopupTouchHandleDrawable.PopupTouchHandleDrawableDelegate mTouchHandleDelegate;
    private boolean mTouchScrollInProgress;
    /* access modifiers changed from: private */
    public boolean mUnselectAllOnActionModeDismiss;
    private ViewAndroid mViewAndroid;
    private ContentViewAndroidDelegate mViewAndroidDelegate;
    private int mViewportHeightPix;
    private int mViewportWidthPix;
    private boolean mWasPastePopupShowingOnInsertionDragStart;
    /* access modifiers changed from: private */
    public WebContents mWebContents;
    private WebContentsObserver mWebContentsObserver;
    private ZoomControlsDelegate mZoomControlsDelegate;

    public interface InternalAccessDelegate {
        boolean awakenScrollBars();

        boolean drawChild(Canvas canvas, View view, long j);

        void onScrollChanged(int i, int i2, int i3, int i4);

        boolean super_awakenScrollBars(int i, boolean z);

        boolean super_dispatchKeyEvent(KeyEvent keyEvent);

        boolean super_dispatchKeyEventPreIme(KeyEvent keyEvent);

        void super_onConfigurationChanged(Configuration configuration);

        boolean super_onGenericMotionEvent(MotionEvent motionEvent);

        boolean super_onKeyUp(int i, KeyEvent keyEvent);
    }

    public interface SmartClipDataListener {
        void onSmartClipDataExtracted(String str, String str2, Rect rect);
    }

    public interface ZoomControlsDelegate {
        void dismissZoomPicker();

        void invokeZoomPicker();

        void updateZoomControls();
    }

    private native void nativeAddJavascriptInterface(long j, Object obj, String str, Class cls);

    private native void nativeDismissTextHandles(long j);

    private native void nativeDoubleTap(long j, long j2, float f, float f2);

    private native void nativeExtractSmartClipData(long j, int i, int i2, int i3, int i4);

    private native void nativeFlingCancel(long j, long j2);

    private native void nativeFlingStart(long j, long j2, float f, float f2, float f3, float f4);

    private native int nativeGetCurrentRenderProcessId(long j);

    private native long nativeGetNativeImeAdapter(long j);

    private native WebContents nativeGetWebContentsAndroid(long j);

    private native long nativeInit(long j, long j2, long j3, HashSet<Object> hashSet);

    /* access modifiers changed from: private */
    public native void nativeLongPress(long j, long j2, float f, float f2);

    private native void nativeMoveCaret(long j, float f, float f2);

    private native void nativeOnJavaContentViewCoreDestroyed(long j);

    private native boolean nativeOnTouchEvent(long j, MotionEvent motionEvent, long j2, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, int i5, int i6, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, int i7, int i8, int i9, int i10, boolean z);

    private native void nativePinchBegin(long j, long j2, float f, float f2);

    private native void nativePinchBy(long j, long j2, float f, float f2, float f3);

    private native void nativePinchEnd(long j, long j2);

    private native void nativeRemoveJavascriptInterface(long j, String str);

    private native void nativeResetGestureDetection(long j);

    private native void nativeScrollBegin(long j, long j2, float f, float f2, float f3, float f4);

    private native void nativeScrollBy(long j, long j2, float f, float f2, float f3, float f4);

    private native void nativeScrollEnd(long j, long j2);

    private native void nativeSelectBetweenCoordinates(long j, float f, float f2, float f3, float f4);

    private native void nativeSelectPopupMenuItems(long j, long j2, int[] iArr);

    private native int nativeSendMouseMoveEvent(long j, long j2, float f, float f2);

    private native int nativeSendMouseWheelEvent(long j, long j2, float f, float f2, float f3);

    private native void nativeSendOrientationChangeEvent(long j, int i);

    private native void nativeSetAccessibilityEnabled(long j, boolean z);

    private native void nativeSetAllowJavascriptInterfacesInspection(long j, boolean z);

    private native void nativeSetBackgroundOpaque(long j, boolean z);

    private native void nativeSetDoubleTapSupportEnabled(long j, boolean z);

    private native void nativeSetFocus(long j, boolean z);

    private native void nativeSetMultiTouchZoomSupportEnabled(long j, boolean z);

    private native void nativeSetTextHandlesTemporarilyHidden(long j, boolean z);

    /* access modifiers changed from: private */
    public native void nativeSingleTap(long j, long j2, float f, float f2);

    private native void nativeWasResized(long j);

    static {
        boolean z;
        if (!ContentViewCore.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    private class ContentViewAndroidDelegate implements ViewAndroidDelegate {
        static final /* synthetic */ boolean $assertionsDisabled = (!ContentViewCore.class.desiredAssertionStatus());
        private Map<View, Position> mAnchorViews;
        private ViewGroup mCurrentContainerView;

        private ContentViewAndroidDelegate() {
            this.mAnchorViews = new LinkedHashMap();
        }

        @VisibleForTesting
        private class Position {
            /* access modifiers changed from: private */
            public final float mHeight;
            /* access modifiers changed from: private */
            public final float mWidth;
            /* access modifiers changed from: private */
            public final float mX;
            /* access modifiers changed from: private */
            public final float mY;

            public Position(float x, float y, float width, float height) {
                this.mX = x;
                this.mY = y;
                this.mWidth = width;
                this.mHeight = height;
            }
        }

        public View acquireAnchorView() {
            View anchorView = new View(ContentViewCore.this.mContext);
            this.mAnchorViews.put(anchorView, (Object) null);
            this.mCurrentContainerView.addView(anchorView);
            return anchorView;
        }

        public void setAnchorViewPosition(View view, float x, float y, float width, float height) {
            this.mAnchorViews.put(view, new Position(x, y, width, height));
            doSetAnchorViewPosition(view, x, y, width, height);
        }

        private void doSetAnchorViewPosition(View view, float x, float y, float width, float height) {
            int startMargin;
            if (view.getParent() != null) {
                if ($assertionsDisabled || view.getParent() == this.mCurrentContainerView) {
                    float scale = (float) DeviceDisplayInfo.create(ContentViewCore.this.mContext).getDIPScale();
                    int leftMargin = Math.round(x * scale);
                    int topMargin = Math.round(ContentViewCore.this.mRenderCoordinates.getContentOffsetYPix() + (y * scale));
                    int scaledWidth = Math.round(width * scale);
                    if (this.mCurrentContainerView instanceof FrameLayout) {
                        if (ApiCompatibilityUtils.isLayoutRtl(this.mCurrentContainerView)) {
                            startMargin = this.mCurrentContainerView.getMeasuredWidth() - Math.round((width + x) * scale);
                        } else {
                            startMargin = leftMargin;
                        }
                        if (scaledWidth + startMargin > this.mCurrentContainerView.getWidth()) {
                            scaledWidth = this.mCurrentContainerView.getWidth() - startMargin;
                        }
                        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(scaledWidth, Math.round(height * scale));
                        ApiCompatibilityUtils.setMarginStart(lp, startMargin);
                        lp.topMargin = topMargin;
                        view.setLayoutParams(lp);
                    } else if (this.mCurrentContainerView instanceof AbsoluteLayout) {
                        view.setLayoutParams(new AbsoluteLayout.LayoutParams(scaledWidth, (int) (height * scale), leftMargin + ContentViewCore.this.mRenderCoordinates.getScrollXPixInt(), topMargin + ContentViewCore.this.mRenderCoordinates.getScrollYPixInt()));
                    } else {
                        Log.e(ContentViewCore.TAG, "Unknown layout " + this.mCurrentContainerView.getClass().getName());
                    }
                } else {
                    throw new AssertionError();
                }
            }
        }

        public void releaseAnchorView(View anchorView) {
            this.mAnchorViews.remove(anchorView);
            this.mCurrentContainerView.removeView(anchorView);
        }

        /* access modifiers changed from: package-private */
        public void updateCurrentContainerView() {
            ViewGroup oldContainerView = this.mCurrentContainerView;
            this.mCurrentContainerView = ContentViewCore.this.mContainerView;
            for (Map.Entry<View, Position> entry : this.mAnchorViews.entrySet()) {
                View anchorView = entry.getKey();
                Position position = entry.getValue();
                oldContainerView.removeView(anchorView);
                this.mCurrentContainerView.addView(anchorView);
                if (position != null) {
                    doSetAnchorViewPosition(anchorView, position.mX, position.mY, position.mWidth, position.mHeight);
                }
            }
        }
    }

    public ContentViewCore(Context context) {
        this.mContext = context;
        this.mAdapterInputConnectionFactory = new ImeAdapter.AdapterInputConnectionFactory();
        this.mInputMethodManagerWrapper = new InputMethodManagerWrapper(this.mContext);
        this.mRenderCoordinates = new RenderCoordinates();
        float deviceScaleFactor = getContext().getResources().getDisplayMetrics().density;
        String forceScaleFactor = CommandLine.getInstance().getSwitchValue(ContentSwitches.FORCE_DEVICE_SCALE_FACTOR);
        this.mRenderCoordinates.setDeviceScaleFactor(forceScaleFactor != null ? Float.valueOf(forceScaleFactor).floatValue() : deviceScaleFactor);
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.mGestureStateListeners = new ObserverList<>();
        this.mGestureStateListenersIterator = this.mGestureStateListeners.rewindableIterator();
        this.mEditable = Editable.Factory.getInstance().newEditable("");
        Selection.setSelection(this.mEditable, 0);
    }

    @CalledByNative
    public Context getContext() {
        return this.mContext;
    }

    public ViewGroup getContainerView() {
        return this.mContainerView;
    }

    public WebContents getWebContents() {
        return this.mWebContents;
    }

    public void setViewportSizeOffset(int offsetXPix, int offsetYPix) {
        setTopControlsLayoutHeight(offsetYPix);
    }

    public void setTopControlsLayoutHeight(int topControlsLayoutHeightPix) {
        if (topControlsLayoutHeightPix != this.mTopControlsLayoutHeightPix) {
            this.mTopControlsLayoutHeightPix = topControlsLayoutHeightPix;
            if (this.mNativeContentViewCore != 0) {
                nativeWasResized(this.mNativeContentViewCore);
            }
        }
    }

    public ViewAndroidDelegate getViewAndroidDelegate() {
        return this.mViewAndroidDelegate;
    }

    @VisibleForTesting
    public void setImeAdapterForTest(ImeAdapter imeAdapter) {
        this.mImeAdapter = imeAdapter;
    }

    @VisibleForTesting
    public ImeAdapter getImeAdapterForTest() {
        return this.mImeAdapter;
    }

    @VisibleForTesting
    public void setAdapterInputConnectionFactory(ImeAdapter.AdapterInputConnectionFactory factory) {
        this.mAdapterInputConnectionFactory = factory;
    }

    @VisibleForTesting
    public void setInputMethodManagerWrapperForTest(InputMethodManagerWrapper immw) {
        this.mInputMethodManagerWrapper = immw;
    }

    @VisibleForTesting
    public AdapterInputConnection getInputConnectionForTest() {
        return this.mInputConnection;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ViewAndroid getViewAndroid() {
        return this.mViewAndroid;
    }

    private ImeAdapter createImeAdapter(Context context) {
        return new ImeAdapter(this.mInputMethodManagerWrapper, new ImeAdapter.ImeAdapterDelegate() {
            public void onImeEvent() {
                ContentViewCore.this.mPopupZoomer.hide(true);
                ContentViewCore.this.getContentViewClient().onImeEvent();
                if (ContentViewCore.this.mFocusedNodeEditable) {
                    ContentViewCore.this.dismissTextHandles();
                }
            }

            public void onDismissInput() {
                ContentViewCore.this.getContentViewClient().onImeStateChangeRequested(false);
            }

            public View getAttachedView() {
                return ContentViewCore.this.mContainerView;
            }

            public ResultReceiver getNewShowKeyboardReceiver() {
                return new ResultReceiver(new Handler()) {
                    static final /* synthetic */ boolean $assertionsDisabled = (!ContentViewCore.class.desiredAssertionStatus());

                    public void onReceiveResult(int resultCode, Bundle resultData) {
                        ContentViewCore.this.getContentViewClient().onImeStateChangeRequested(resultCode == 2 || resultCode == 0);
                        if (resultCode == 2) {
                            ContentViewCore.this.getContainerView().getWindowVisibleDisplayFrame(ContentViewCore.this.mFocusPreOSKViewportRect);
                        } else if (ContentViewCore.this.hasFocus() && resultCode == 0) {
                            if ($assertionsDisabled || ContentViewCore.this.mWebContents != null) {
                                ContentViewCore.this.mWebContents.scrollFocusedEditableNodeIntoView();
                                return;
                            }
                            throw new AssertionError();
                        }
                    }
                };
            }
        });
    }

    public void initialize(ViewGroup containerView, InternalAccessDelegate internalDispatcher, long nativeWebContents, WindowAndroid windowAndroid) {
        createContentViewAndroidDelegate();
        setContainerView(containerView);
        long windowNativePointer = windowAndroid.getNativePointer();
        if ($assertionsDisabled || windowNativePointer != 0) {
            createViewAndroid(windowAndroid);
            long viewAndroidNativePointer = this.mViewAndroid.getNativePointer();
            if ($assertionsDisabled || viewAndroidNativePointer != 0) {
                this.mZoomControlsDelegate = NO_OP_ZOOM_CONTROLS_DELEGATE;
                this.mNativeContentViewCore = nativeInit(nativeWebContents, viewAndroidNativePointer, windowNativePointer, this.mRetainedJavaScriptObjects);
                this.mWebContents = nativeGetWebContentsAndroid(this.mNativeContentViewCore);
                this.mContentSettings = new ContentSettings(this, this.mNativeContentViewCore);
                setContainerViewInternals(internalDispatcher);
                this.mRenderCoordinates.reset();
                initPopupZoomer(this.mContext);
                this.mImeAdapter = createImeAdapter(this.mContext);
                attachImeAdapter();
                this.mAccessibilityInjector = AccessibilityInjector.newInstance(this);
                this.mWebContentsObserver = new WebContentsObserver(this.mWebContents) {
                    public void didFailLoad(boolean isProvisionalLoad, boolean isMainFrame, int errorCode, String description, String failingUrl) {
                        if (isProvisionalLoad) {
                            determinedProcessVisibility();
                        }
                    }

                    public void didNavigateMainFrame(String url, String baseUrl, boolean isNavigationToDifferentPage, boolean isFragmentNavigation) {
                        if (isNavigationToDifferentPage) {
                            ContentViewCore.this.hidePopupsAndClearSelection();
                            ContentViewCore.this.resetScrollInProgress();
                            ContentViewCore.this.resetGestureDetection();
                        }
                    }

                    public void renderProcessGone(boolean wasOomProtected) {
                        ContentViewCore.this.hidePopupsAndClearSelection();
                        ContentViewCore.this.resetScrollInProgress();
                    }

                    public void navigationEntryCommitted() {
                        determinedProcessVisibility();
                    }

                    private void determinedProcessVisibility() {
                        ChildProcessLauncher.determinedVisibility(ContentViewCore.this.getCurrentRenderProcessId());
                    }
                };
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void createContentViewAndroidDelegate() {
        this.mViewAndroidDelegate = new ContentViewAndroidDelegate();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void createViewAndroid(WindowAndroid windowAndroid) {
        this.mViewAndroid = new ViewAndroid(windowAndroid, this.mViewAndroidDelegate);
    }

    public void setContainerView(ViewGroup containerView) {
        TraceEvent.begin();
        if (this.mContainerView != null) {
            this.mPastePopupMenu = null;
            this.mInputConnection = null;
            hidePopupsAndClearSelection();
        }
        this.mContainerView = containerView;
        this.mPositionObserver = new ViewPositionObserver(this.mContainerView);
        this.mContainerView.setClickable(true);
        this.mViewAndroidDelegate.updateCurrentContainerView();
        TraceEvent.end();
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void onNativeContentViewCoreDestroyed(long nativeContentViewCore) {
        if ($assertionsDisabled || nativeContentViewCore == this.mNativeContentViewCore) {
            this.mNativeContentViewCore = 0;
            return;
        }
        throw new AssertionError();
    }

    public void setContainerViewInternals(InternalAccessDelegate internalDispatcher) {
        this.mContainerViewInternals = internalDispatcher;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void initPopupZoomer(Context context) {
        this.mPopupZoomer = new PopupZoomer(context);
        this.mPopupZoomer.setOnVisibilityChangedListener(new PopupZoomer.OnVisibilityChangedListener() {
            /* access modifiers changed from: private */
            public final ViewGroup mContainerViewAtCreation = ContentViewCore.this.mContainerView;

            public void onPopupZoomerShown(final PopupZoomer zoomer) {
                this.mContainerViewAtCreation.post(new Runnable() {
                    public void run() {
                        if (AnonymousClass4.this.mContainerViewAtCreation.indexOfChild(zoomer) == -1) {
                            AnonymousClass4.this.mContainerViewAtCreation.addView(zoomer);
                        }
                    }
                });
            }

            public void onPopupZoomerHidden(final PopupZoomer zoomer) {
                this.mContainerViewAtCreation.post(new Runnable() {
                    public void run() {
                        if (AnonymousClass4.this.mContainerViewAtCreation.indexOfChild(zoomer) != -1) {
                            AnonymousClass4.this.mContainerViewAtCreation.removeView(zoomer);
                            AnonymousClass4.this.mContainerViewAtCreation.invalidate();
                        }
                    }
                });
            }
        });
        this.mPopupZoomer.setOnTapListener(new PopupZoomer.OnTapListener() {
            private final ViewGroup mContainerViewAtCreation = ContentViewCore.this.mContainerView;

            public boolean onSingleTap(View v, MotionEvent e) {
                this.mContainerViewAtCreation.requestFocus();
                if (ContentViewCore.this.mNativeContentViewCore == 0) {
                    return true;
                }
                ContentViewCore.this.nativeSingleTap(ContentViewCore.this.mNativeContentViewCore, e.getEventTime(), e.getX(), e.getY());
                return true;
            }

            public boolean onLongPress(View v, MotionEvent e) {
                if (ContentViewCore.this.mNativeContentViewCore == 0) {
                    return true;
                }
                ContentViewCore.this.nativeLongPress(ContentViewCore.this.mNativeContentViewCore, e.getEventTime(), e.getX(), e.getY());
                return true;
            }
        });
    }

    @VisibleForTesting
    public void setPopupZoomerForTest(PopupZoomer popupZoomer) {
        this.mPopupZoomer = popupZoomer;
    }

    public void destroy() {
        if (this.mNativeContentViewCore != 0) {
            nativeOnJavaContentViewCoreDestroyed(this.mNativeContentViewCore);
        }
        this.mWebContentsObserver.detachFromWebContents();
        this.mWebContentsObserver = null;
        setSmartClipDataListener((SmartClipDataListener) null);
        setZoomControlsDelegate((ZoomControlsDelegate) null);
        this.mContentViewClient = new ContentViewClient();
        this.mWebContents = null;
        if (this.mViewAndroid != null) {
            this.mViewAndroid.destroy();
        }
        this.mNativeContentViewCore = 0;
        this.mContentSettings = null;
        this.mJavaScriptInterfaces.clear();
        this.mRetainedJavaScriptObjects.clear();
        unregisterAccessibilityContentObserver();
        this.mGestureStateListeners.clear();
        ScreenOrientationListener.getInstance().removeObserver(this);
        this.mPositionObserver.clearListener();
    }

    private void unregisterAccessibilityContentObserver() {
        if (this.mAccessibilityScriptInjectionObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mAccessibilityScriptInjectionObserver);
            this.mAccessibilityScriptInjectionObserver = null;
        }
    }

    public boolean isAlive() {
        return this.mNativeContentViewCore != 0;
    }

    @CalledByNative
    public long getNativeContentViewCore() {
        return this.mNativeContentViewCore;
    }

    public void setContentViewClient(ContentViewClient client) {
        if (client == null) {
            throw new IllegalArgumentException("The client can't be null.");
        }
        this.mContentViewClient = client;
    }

    @VisibleForTesting
    public ContentViewClient getContentViewClient() {
        if (this.mContentViewClient == null) {
            this.mContentViewClient = new ContentViewClient();
        }
        return this.mContentViewClient;
    }

    @CalledByNative
    private void onBackgroundColorChanged(int color) {
        getContentViewClient().onBackgroundColorChanged(color);
    }

    @CalledByNative
    public int getViewportWidthPix() {
        return this.mViewportWidthPix;
    }

    @CalledByNative
    public int getViewportHeightPix() {
        return this.mViewportHeightPix;
    }

    @CalledByNative
    public int getPhysicalBackingWidthPix() {
        return this.mPhysicalBackingWidthPix;
    }

    @CalledByNative
    public int getPhysicalBackingHeightPix() {
        return this.mPhysicalBackingHeightPix;
    }

    @VisibleForTesting
    public int getViewportSizeOffsetWidthPix() {
        return 0;
    }

    @VisibleForTesting
    public int getViewportSizeOffsetHeightPix() {
        return getTopControlsLayoutHeightPix();
    }

    @CalledByNative
    public int getTopControlsLayoutHeightPix() {
        return this.mTopControlsLayoutHeightPix;
    }

    public float getContentHeightCss() {
        return this.mRenderCoordinates.getContentHeightCss();
    }

    public float getContentWidthCss() {
        return this.mRenderCoordinates.getContentWidthCss();
    }

    public String getSelectedText() {
        return this.mHasSelection ? this.mLastSelectedText : "";
    }

    public boolean isSelectionEditable() {
        if (this.mHasSelection) {
            return this.mFocusedNodeEditable;
        }
        return false;
    }

    public boolean isFocusedNodeEditable() {
        return this.mFocusedNodeEditable;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return onTouchEventImpl(event, false);
    }

    /* access modifiers changed from: private */
    public boolean onTouchEventImpl(MotionEvent event, boolean isTouchHandleEvent) {
        String str;
        float f;
        float f2;
        int i;
        float f3;
        float f4;
        float f5;
        int i2;
        TraceEvent.begin("onTouchEvent");
        try {
            int eventAction = event.getActionMasked();
            if (eventAction == 0) {
                cancelRequestToScrollFocusedEditableNodeIntoView();
            }
            if (SPenSupport.isSPenSupported(this.mContext)) {
                eventAction = SPenSupport.convertSPenEventAction(eventAction);
            }
            if (!isValidTouchEventActionForNative(eventAction)) {
                return false;
            } else if (this.mNativeContentViewCore == 0) {
                TraceEvent.end("onTouchEvent");
                return false;
            } else {
                MotionEvent offset = null;
                if (!(this.mCurrentTouchOffsetX == 0.0f && this.mCurrentTouchOffsetY == 0.0f)) {
                    offset = createOffsetMotionEvent(event);
                    event = offset;
                }
                int pointerCount = event.getPointerCount();
                long j = this.mNativeContentViewCore;
                long eventTime = event.getEventTime();
                int historySize = event.getHistorySize();
                int actionIndex = event.getActionIndex();
                float x = event.getX();
                float y = event.getY();
                if (pointerCount > 1) {
                    f = event.getX(1);
                } else {
                    f = 0.0f;
                }
                if (pointerCount > 1) {
                    f2 = event.getY(1);
                } else {
                    f2 = 0.0f;
                }
                int pointerId = event.getPointerId(0);
                if (pointerCount > 1) {
                    i = event.getPointerId(1);
                } else {
                    i = -1;
                }
                float touchMajor = event.getTouchMajor();
                if (pointerCount > 1) {
                    f3 = event.getTouchMajor(1);
                } else {
                    f3 = 0.0f;
                }
                float touchMinor = event.getTouchMinor();
                if (pointerCount > 1) {
                    f4 = event.getTouchMinor(1);
                } else {
                    f4 = 0.0f;
                }
                float orientation = event.getOrientation();
                if (pointerCount > 1) {
                    f5 = event.getOrientation(1);
                } else {
                    f5 = 0.0f;
                }
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                int toolType = event.getToolType(0);
                if (pointerCount > 1) {
                    i2 = event.getToolType(1);
                } else {
                    i2 = 0;
                }
                boolean nativeOnTouchEvent = nativeOnTouchEvent(j, event, eventTime, eventAction, pointerCount, historySize, actionIndex, x, y, f, f2, pointerId, i, touchMajor, f3, touchMinor, f4, orientation, f5, rawX, rawY, toolType, i2, event.getButtonState(), event.getMetaState(), isTouchHandleEvent);
                if (offset != null) {
                    offset.recycle();
                }
                TraceEvent.end("onTouchEvent");
                return nativeOnTouchEvent;
            }
        } finally {
            str = "onTouchEvent";
            TraceEvent.end(str);
        }
    }

    private static boolean isValidTouchEventActionForNative(int eventAction) {
        return eventAction == 0 || eventAction == 1 || eventAction == 3 || eventAction == 2 || eventAction == 5 || eventAction == 6;
    }

    public void setIgnoreRemainingTouchEvents() {
        resetGestureDetection();
    }

    public boolean isScrollInProgress() {
        return this.mTouchScrollInProgress || this.mPotentiallyActiveFlingCount > 0;
    }

    @CalledByNative
    private void onFlingStartEventConsumed(int vx, int vy) {
        this.mTouchScrollInProgress = false;
        this.mPotentiallyActiveFlingCount++;
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            ((GestureStateListener) this.mGestureStateListenersIterator.next()).onFlingStartGesture(vx, vy, computeVerticalScrollOffset(), computeVerticalScrollExtent());
        }
    }

    @CalledByNative
    private void onFlingStartEventHadNoConsumer(int vx, int vy) {
        this.mTouchScrollInProgress = false;
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            ((GestureStateListener) this.mGestureStateListenersIterator.next()).onUnhandledFlingStartEvent(vx, vy);
        }
    }

    @CalledByNative
    private void onFlingCancelEventAck() {
        updateGestureStateListener(10);
    }

    @CalledByNative
    private void onScrollBeginEventAck() {
        this.mTouchScrollInProgress = true;
        hidePastePopup();
        this.mZoomControlsDelegate.invokeZoomPicker();
        updateGestureStateListener(6);
    }

    @CalledByNative
    private void onScrollUpdateGestureConsumed() {
        this.mZoomControlsDelegate.invokeZoomPicker();
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            ((GestureStateListener) this.mGestureStateListenersIterator.next()).onScrollUpdateGestureConsumed();
        }
    }

    @CalledByNative
    private void onScrollEndEventAck() {
        if (this.mTouchScrollInProgress) {
            this.mTouchScrollInProgress = false;
            updateGestureStateListener(8);
        }
    }

    @CalledByNative
    private void onPinchBeginEventAck() {
        updateGestureStateListener(12);
    }

    @CalledByNative
    private void onPinchEndEventAck() {
        updateGestureStateListener(14);
    }

    @CalledByNative
    private void onSingleTapEventAck(boolean consumed, int x, int y) {
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            ((GestureStateListener) this.mGestureStateListenersIterator.next()).onSingleTap(consumed, x, y);
        }
    }

    @CalledByNative
    private boolean filterTapOrPressEvent(int type, int x, int y) {
        if (type == 5 && offerLongPressToEmbedder()) {
            return true;
        }
        updateForTapOrPress(type, (float) x, (float) y);
        return false;
    }

    @VisibleForTesting
    public void sendDoubleTapForTest(long timeMs, int x, int y) {
        if (this.mNativeContentViewCore != 0) {
            nativeDoubleTap(this.mNativeContentViewCore, timeMs, (float) x, (float) y);
        }
    }

    @VisibleForTesting
    public void flingForTest(long timeMs, int x, int y, int velocityX, int velocityY) {
        if (this.mNativeContentViewCore != 0) {
            nativeFlingCancel(this.mNativeContentViewCore, timeMs);
            nativeScrollBegin(this.mNativeContentViewCore, timeMs, (float) x, (float) y, (float) velocityX, (float) velocityY);
            nativeFlingStart(this.mNativeContentViewCore, timeMs, (float) x, (float) y, (float) velocityX, (float) velocityY);
        }
    }

    public void cancelFling(long timeMs) {
        if (this.mNativeContentViewCore != 0) {
            nativeFlingCancel(this.mNativeContentViewCore, timeMs);
        }
    }

    public void addGestureStateListener(GestureStateListener listener) {
        this.mGestureStateListeners.addObserver(listener);
    }

    public void removeGestureStateListener(GestureStateListener listener) {
        this.mGestureStateListeners.removeObserver(listener);
    }

    /* access modifiers changed from: package-private */
    public void updateGestureStateListener(int gestureType) {
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            GestureStateListener listener = (GestureStateListener) this.mGestureStateListenersIterator.next();
            switch (gestureType) {
                case 6:
                    listener.onScrollStarted(computeVerticalScrollOffset(), computeVerticalScrollExtent());
                    break;
                case 8:
                    listener.onScrollEnded(computeVerticalScrollOffset(), computeVerticalScrollExtent());
                    break;
                case 10:
                    listener.onFlingCancelGesture();
                    break;
                case 11:
                    listener.onFlingEndGesture(computeVerticalScrollOffset(), computeVerticalScrollExtent());
                    break;
                case 12:
                    listener.onPinchStarted();
                    break;
                case 14:
                    listener.onPinchEnded();
                    break;
            }
        }
    }

    public void onShow() {
        if ($assertionsDisabled || this.mWebContents != null) {
            this.mWebContents.onShow();
            setAccessibilityState(this.mAccessibilityManager.isEnabled());
            restoreSelectionPopupsIfNecessary();
            return;
        }
        throw new AssertionError();
    }

    public int getCurrentRenderProcessId() {
        return nativeGetCurrentRenderProcessId(this.mNativeContentViewCore);
    }

    public void onHide() {
        if ($assertionsDisabled || this.mWebContents != null) {
            hidePopupsAndPreserveSelection();
            setInjectedAccessibility(false);
            this.mWebContents.onHide();
            return;
        }
        throw new AssertionError();
    }

    public ContentSettings getContentSettings() {
        return this.mContentSettings;
    }

    /* access modifiers changed from: private */
    public void hidePopupsAndClearSelection() {
        this.mUnselectAllOnActionModeDismiss = true;
        hidePopups();
        clearUserSelection();
    }

    private void hidePopupsAndPreserveSelection() {
        this.mUnselectAllOnActionModeDismiss = false;
        hidePopups();
    }

    /* access modifiers changed from: private */
    public void clearUserSelection() {
        if (this.mFocusedNodeEditable) {
            if (this.mInputConnection != null) {
                int selectionEnd = Selection.getSelectionEnd(this.mEditable);
                this.mInputConnection.setSelection(selectionEnd, selectionEnd);
            }
        } else if (this.mImeAdapter != null) {
            this.mImeAdapter.unselect();
        }
    }

    private void hidePopups() {
        hideSelectActionBar();
        hidePastePopup();
        hideSelectPopup();
        this.mPopupZoomer.hide(false);
        if (this.mUnselectAllOnActionModeDismiss) {
            dismissTextHandles();
        }
    }

    private void restoreSelectionPopupsIfNecessary() {
        if (this.mHasSelection && this.mActionMode == null) {
            showSelectActionBar();
        }
    }

    public void hideSelectActionBar() {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
            this.mActionMode = null;
        }
    }

    public boolean isSelectActionBarShowing() {
        return this.mActionMode != null;
    }

    /* access modifiers changed from: private */
    public void resetGestureDetection() {
        if (this.mNativeContentViewCore != 0) {
            nativeResetGestureDetection(this.mNativeContentViewCore);
        }
    }

    public void onAttachedToWindow() {
        setAccessibilityState(this.mAccessibilityManager.isEnabled());
        setTextHandlesTemporarilyHidden(false);
        restoreSelectionPopupsIfNecessary();
        ScreenOrientationListener.getInstance().addObserver(this, this.mContext);
        GamepadList.onAttachedToWindow(this.mContext);
    }

    @SuppressLint({"MissingSuperCall"})
    public void onDetachedFromWindow() {
        setInjectedAccessibility(false);
        this.mZoomControlsDelegate.dismissZoomPicker();
        unregisterAccessibilityContentObserver();
        ScreenOrientationListener.getInstance().removeObserver(this);
        GamepadList.onDetachedFromWindow();
        setTextHandlesTemporarilyHidden(true);
        hidePopupsAndPreserveSelection();
    }

    public void onVisibilityChanged(View changedView, int visibility) {
        if (visibility != 0) {
            this.mZoomControlsDelegate.dismissZoomPicker();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        if (!this.mImeAdapter.hasTextInputType()) {
            outAttrs.imeOptions = PageTransition.FROM_ADDRESS_BAR;
        }
        this.mInputConnection = this.mAdapterInputConnectionFactory.get(this.mContainerView, this.mImeAdapter, this.mEditable, outAttrs);
        return this.mInputConnection;
    }

    @VisibleForTesting
    public AdapterInputConnection getAdapterInputConnectionForTest() {
        return this.mInputConnection;
    }

    @VisibleForTesting
    public Editable getEditableForTest() {
        return this.mEditable;
    }

    public boolean onCheckIsTextEditor() {
        return this.mImeAdapter.hasTextInputType();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        TraceEvent.begin();
        if (newConfig.keyboard != 1) {
            if (this.mNativeContentViewCore != 0) {
                this.mImeAdapter.attach(nativeGetNativeImeAdapter(this.mNativeContentViewCore), ImeAdapter.getTextInputTypeNone(), 0);
            }
            this.mInputMethodManagerWrapper.restartInput(this.mContainerView);
        }
        this.mContainerViewInternals.super_onConfigurationChanged(newConfig);
        this.mContainerView.requestLayout();
        TraceEvent.end();
    }

    public void onSizeChanged(int wPix, int hPix, int owPix, int ohPix) {
        if (getViewportWidthPix() != wPix || getViewportHeightPix() != hPix) {
            this.mViewportWidthPix = wPix;
            this.mViewportHeightPix = hPix;
            if (this.mNativeContentViewCore != 0) {
                nativeWasResized(this.mNativeContentViewCore);
            }
            updateAfterSizeChanged();
        }
    }

    public void onPhysicalBackingSizeChanged(int wPix, int hPix) {
        if (this.mPhysicalBackingWidthPix != wPix || this.mPhysicalBackingHeightPix != hPix) {
            this.mPhysicalBackingWidthPix = wPix;
            this.mPhysicalBackingHeightPix = hPix;
            if (this.mNativeContentViewCore != 0) {
                nativeWasResized(this.mNativeContentViewCore);
            }
        }
    }

    public void onOverdrawBottomHeightChanged(int overdrawHeightPix) {
    }

    private void updateAfterSizeChanged() {
        this.mPopupZoomer.hide(false);
        if (!this.mFocusPreOSKViewportRect.isEmpty()) {
            Rect rect = new Rect();
            getContainerView().getWindowVisibleDisplayFrame(rect);
            if (!rect.equals(this.mFocusPreOSKViewportRect)) {
                if (rect.width() == this.mFocusPreOSKViewportRect.width()) {
                    if ($assertionsDisabled || this.mWebContents != null) {
                        this.mWebContents.scrollFocusedEditableNodeIntoView();
                    } else {
                        throw new AssertionError();
                    }
                }
                cancelRequestToScrollFocusedEditableNodeIntoView();
            }
        }
    }

    private void cancelRequestToScrollFocusedEditableNodeIntoView() {
        this.mFocusPreOSKViewportRect.setEmpty();
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) {
            resetGestureDetection();
        }
    }

    public void onFocusChanged(boolean gainFocus) {
        if (gainFocus) {
            restoreSelectionPopupsIfNecessary();
        } else {
            hideImeIfNeeded();
            cancelRequestToScrollFocusedEditableNodeIntoView();
            if (this.mPreserveSelectionOnNextLossOfFocus) {
                this.mPreserveSelectionOnNextLossOfFocus = false;
                hidePopupsAndPreserveSelection();
            } else {
                hidePopupsAndClearSelection();
            }
        }
        if (this.mNativeContentViewCore != 0) {
            nativeSetFocus(this.mNativeContentViewCore, gainFocus);
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!this.mPopupZoomer.isShowing() || keyCode != 4) {
            return this.mContainerViewInternals.super_onKeyUp(keyCode, event);
        }
        this.mPopupZoomer.hide(true);
        return true;
    }

    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        try {
            TraceEvent.begin();
            return this.mContainerViewInternals.super_dispatchKeyEventPreIme(event);
        } finally {
            TraceEvent.end();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (GamepadList.dispatchKeyEvent(event)) {
            return true;
        }
        if (getContentViewClient().shouldOverrideKeyEvent(event)) {
            return this.mContainerViewInternals.super_dispatchKeyEvent(event);
        }
        if (!this.mImeAdapter.dispatchKeyEvent(event)) {
            return this.mContainerViewInternals.super_dispatchKeyEvent(event);
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent event) {
        String str;
        TraceEvent.begin("onHoverEvent");
        MotionEvent offset = createOffsetMotionEvent(event);
        try {
            if (this.mBrowserAccessibilityManager != null) {
                return this.mBrowserAccessibilityManager.onHoverEvent(offset);
            }
            if (!this.mTouchExplorationEnabled || offset.getAction() != 10) {
                this.mContainerView.removeCallbacks(this.mFakeMouseMoveRunnable);
                if (this.mNativeContentViewCore != 0) {
                    nativeSendMouseMoveEvent(this.mNativeContentViewCore, offset.getEventTime(), offset.getX(), offset.getY());
                }
                offset.recycle();
                TraceEvent.end("onHoverEvent");
                return true;
            }
            offset.recycle();
            TraceEvent.end("onHoverEvent");
            return true;
        } finally {
            offset.recycle();
            str = "onHoverEvent";
            TraceEvent.end(str);
        }
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        if (GamepadList.onGenericMotionEvent(event)) {
            return true;
        }
        if ((event.getSource() & 2) != 0) {
            switch (event.getAction()) {
                case 8:
                    if (this.mNativeContentViewCore == 0) {
                        return false;
                    }
                    nativeSendMouseWheelEvent(this.mNativeContentViewCore, event.getEventTime(), event.getX(), event.getY(), event.getAxisValue(9));
                    this.mContainerView.removeCallbacks(this.mFakeMouseMoveRunnable);
                    final MotionEvent eventFakeMouseMove = MotionEvent.obtain(event);
                    this.mFakeMouseMoveRunnable = new Runnable() {
                        public void run() {
                            ContentViewCore.this.onHoverEvent(eventFakeMouseMove);
                            eventFakeMouseMove.recycle();
                        }
                    };
                    this.mContainerView.postDelayed(this.mFakeMouseMoveRunnable, 250);
                    return true;
            }
        }
        return this.mContainerViewInternals.super_onGenericMotionEvent(event);
    }

    public void setCurrentMotionEventOffsets(float dx, float dy) {
        this.mCurrentTouchOffsetX = dx;
        this.mCurrentTouchOffsetY = dy;
    }

    private MotionEvent createOffsetMotionEvent(MotionEvent src) {
        MotionEvent dst = MotionEvent.obtain(src);
        dst.offsetLocation(this.mCurrentTouchOffsetX, this.mCurrentTouchOffsetY);
        return dst;
    }

    public void scrollBy(int xPix, int yPix) {
        if (this.mNativeContentViewCore != 0) {
            nativeScrollBy(this.mNativeContentViewCore, SystemClock.uptimeMillis(), 0.0f, 0.0f, (float) xPix, (float) yPix);
        }
    }

    public void scrollTo(int xPix, int yPix) {
        if (this.mNativeContentViewCore != 0) {
            float xCurrentPix = this.mRenderCoordinates.getScrollXPix();
            float yCurrentPix = this.mRenderCoordinates.getScrollYPix();
            float dxPix = ((float) xPix) - xCurrentPix;
            float dyPix = ((float) yPix) - yCurrentPix;
            if (dxPix != 0.0f || dyPix != 0.0f) {
                long time = SystemClock.uptimeMillis();
                nativeScrollBegin(this.mNativeContentViewCore, time, xCurrentPix, yCurrentPix, -dxPix, -dyPix);
                nativeScrollBy(this.mNativeContentViewCore, time, xCurrentPix, yCurrentPix, dxPix, dyPix);
                nativeScrollEnd(this.mNativeContentViewCore, time);
            }
        }
    }

    public int getNativeScrollXForTest() {
        return this.mRenderCoordinates.getScrollXPixInt();
    }

    public int getNativeScrollYForTest() {
        return this.mRenderCoordinates.getScrollYPixInt();
    }

    public int computeHorizontalScrollExtent() {
        return this.mRenderCoordinates.getLastFrameViewportWidthPixInt();
    }

    public int computeHorizontalScrollOffset() {
        return this.mRenderCoordinates.getScrollXPixInt();
    }

    public int computeHorizontalScrollRange() {
        return this.mRenderCoordinates.getContentWidthPixInt();
    }

    public int computeVerticalScrollExtent() {
        return this.mRenderCoordinates.getLastFrameViewportHeightPixInt();
    }

    public int computeVerticalScrollOffset() {
        return this.mRenderCoordinates.getScrollYPixInt();
    }

    public int computeVerticalScrollRange() {
        return this.mRenderCoordinates.getContentHeightPixInt();
    }

    public boolean awakenScrollBars(int startDelay, boolean invalidate) {
        if (this.mContainerView.getScrollBarStyle() == 0) {
            return false;
        }
        return this.mContainerViewInternals.super_awakenScrollBars(startDelay, invalidate);
    }

    private void updateForTapOrPress(int type, float xPix, float yPix) {
        if (type == 3 || type == 2 || type == 5 || type == 16) {
            if (this.mContainerView.isFocusable() && this.mContainerView.isFocusableInTouchMode() && !this.mContainerView.isFocused()) {
                this.mContainerView.requestFocus();
            }
            if (!this.mPopupZoomer.isShowing()) {
                this.mPopupZoomer.setLastTouch(xPix, yPix);
            }
            this.mLastTapX = (int) xPix;
            this.mLastTapY = (int) yPix;
        }
    }

    public int getLastTapX() {
        return this.mLastTapX;
    }

    public int getLastTapY() {
        return this.mLastTapY;
    }

    public void setZoomControlsDelegate(ZoomControlsDelegate zoomControlsDelegate) {
        if (zoomControlsDelegate == null) {
            this.mZoomControlsDelegate = NO_OP_ZOOM_CONTROLS_DELEGATE;
        } else {
            this.mZoomControlsDelegate = zoomControlsDelegate;
        }
    }

    public void updateMultiTouchZoomSupport(boolean supportsMultiTouchZoom) {
        if (this.mNativeContentViewCore != 0) {
            nativeSetMultiTouchZoomSupportEnabled(this.mNativeContentViewCore, supportsMultiTouchZoom);
        }
    }

    public void updateDoubleTapSupport(boolean supportsDoubleTap) {
        if (this.mNativeContentViewCore != 0) {
            nativeSetDoubleTapSupportEnabled(this.mNativeContentViewCore, supportsDoubleTap);
        }
    }

    public void selectPopupMenuItems(int[] indices) {
        if (this.mNativeContentViewCore != 0) {
            nativeSelectPopupMenuItems(this.mNativeContentViewCore, this.mNativeSelectPopupSourceFrame, indices);
        }
        this.mNativeSelectPopupSourceFrame = 0;
        this.mSelectPopup = null;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void sendOrientationChangeEvent(int orientation) {
        if (this.mNativeContentViewCore != 0) {
            nativeSendOrientationChangeEvent(this.mNativeContentViewCore, orientation);
        }
    }

    public void setDownloadDelegate(ContentViewDownloadDelegate delegate) {
        this.mDownloadDelegate = delegate;
    }

    /* access modifiers changed from: package-private */
    public ContentViewDownloadDelegate getDownloadDelegate() {
        return this.mDownloadDelegate;
    }

    @VisibleForTesting
    public SelectActionModeCallback.ActionHandler getSelectActionHandler() {
        return this.mActionHandler;
    }

    private void showSelectActionBar() {
        if (this.mActionMode != null) {
            this.mActionMode.invalidate();
            return;
        }
        if (this.mActionHandler == null) {
            this.mActionHandler = new SelectActionModeCallback.ActionHandler() {
                public void selectAll() {
                    ContentViewCore.this.mImeAdapter.selectAll();
                }

                public void cut() {
                    ContentViewCore.this.mImeAdapter.cut();
                }

                public void copy() {
                    ContentViewCore.this.mImeAdapter.copy();
                }

                public void paste() {
                    ContentViewCore.this.mImeAdapter.paste();
                }

                public void share() {
                    String query = ContentViewCore.this.getSelectedText();
                    if (!TextUtils.isEmpty(query)) {
                        Intent send = new Intent("android.intent.action.SEND");
                        send.setType("text/plain");
                        send.putExtra("android.intent.extra.TEXT", query);
                        try {
                            Intent i = Intent.createChooser(send, ContentViewCore.this.getContext().getString(R.string.actionbar_share));
                            i.setFlags(268435456);
                            ContentViewCore.this.getContext().startActivity(i);
                        } catch (ActivityNotFoundException e) {
                        }
                    }
                }

                public void search() {
                    String query = ContentViewCore.this.getSelectedText();
                    if (!TextUtils.isEmpty(query)) {
                        if (ContentViewCore.this.getContentViewClient().doesPerformWebSearch()) {
                            ContentViewCore.this.getContentViewClient().performWebSearch(query);
                            return;
                        }
                        Intent i = new Intent("android.intent.action.WEB_SEARCH");
                        i.putExtra("new_search", true);
                        i.putExtra("query", query);
                        i.putExtra("com.android.browser.application_id", ContentViewCore.this.getContext().getPackageName());
                        if (!(ContentViewCore.this.getContext() instanceof Activity)) {
                            i.addFlags(268435456);
                        }
                        try {
                            ContentViewCore.this.getContext().startActivity(i);
                        } catch (ActivityNotFoundException e) {
                        }
                    }
                }

                public boolean isSelectionPassword() {
                    return ContentViewCore.this.mImeAdapter.isSelectionPassword();
                }

                public boolean isSelectionEditable() {
                    return ContentViewCore.this.mFocusedNodeEditable;
                }

                public void onDestroyActionMode() {
                    ActionMode unused = ContentViewCore.this.mActionMode = null;
                    if (ContentViewCore.this.mUnselectAllOnActionModeDismiss) {
                        ContentViewCore.this.dismissTextHandles();
                        ContentViewCore.this.clearUserSelection();
                    }
                    ContentViewCore.this.getContentViewClient().onContextualActionBarHidden();
                }

                public boolean isShareAvailable() {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    return ContentViewCore.this.getContext().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
                }

                public boolean isWebSearchAvailable() {
                    if (ContentViewCore.this.getContentViewClient().doesPerformWebSearch()) {
                        return true;
                    }
                    Intent intent = new Intent("android.intent.action.WEB_SEARCH");
                    intent.putExtra("new_search", true);
                    if (ContentViewCore.this.getContext().getPackageManager().queryIntentActivities(intent, 65536).size() <= 0) {
                        return false;
                    }
                    return true;
                }
            };
        }
        this.mActionMode = null;
        if (this.mContainerView.getParent() != null) {
            if ($assertionsDisabled || this.mWebContents != null) {
                this.mActionMode = this.mContainerView.startActionMode(getContentViewClient().getSelectActionModeCallback(getContext(), this.mActionHandler, this.mWebContents.isIncognito()));
            } else {
                throw new AssertionError();
            }
        }
        this.mUnselectAllOnActionModeDismiss = true;
        if (this.mActionMode == null) {
            this.mImeAdapter.unselect();
        } else {
            getContentViewClient().onContextualActionBarShown();
        }
    }

    public void clearSelection() {
        this.mImeAdapter.unselect();
    }

    public void preserveSelectionOnNextLossOfFocus() {
        this.mPreserveSelectionOnNextLossOfFocus = true;
    }

    @VisibleForTesting
    public boolean hasSelection() {
        return this.mHasSelection;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public boolean hasInsertion() {
        return this.mHasInsertion;
    }

    private void hidePastePopup() {
        if (this.mPastePopupMenu != null) {
            this.mPastePopupMenu.hide();
        }
    }

    @CalledByNative
    private void onSelectionEvent(int eventType, float posXDip, float posYDip) {
        boolean z = true;
        switch (eventType) {
            case 0:
                this.mHasSelection = true;
                this.mUnselectAllOnActionModeDismiss = true;
                this.mContainerView.performHapticFeedback(0);
                showSelectActionBar();
                break;
            case 1:
                this.mHasSelection = false;
                this.mUnselectAllOnActionModeDismiss = false;
                hideSelectActionBar();
                break;
            case 2:
            case 3:
                break;
            case 4:
                this.mHasInsertion = true;
                break;
            case 5:
                if (this.mPastePopupMenu != null) {
                    if (!isScrollInProgress() && this.mPastePopupMenu.isShowing()) {
                        showPastePopup((int) posXDip, (int) posYDip);
                        break;
                    } else {
                        hidePastePopup();
                        break;
                    }
                }
                break;
            case 6:
                if (!this.mWasPastePopupShowingOnInsertionDragStart) {
                    showPastePopup((int) posXDip, (int) posYDip);
                    break;
                } else {
                    hidePastePopup();
                    break;
                }
            case 7:
                this.mHasInsertion = false;
                hidePastePopup();
                break;
            case 8:
                if (this.mPastePopupMenu == null || !this.mPastePopupMenu.isShowing()) {
                    z = false;
                }
                this.mWasPastePopupShowingOnInsertionDragStart = z;
                hidePastePopup();
                break;
            default:
                if (!$assertionsDisabled) {
                    throw new AssertionError("Invalid selection event type.");
                }
                break;
        }
        float scale = this.mRenderCoordinates.getDeviceScaleFactor();
        getContentViewClient().onSelectionEvent(eventType, posXDip * scale, posYDip * scale);
    }

    /* access modifiers changed from: private */
    public void dismissTextHandles() {
        this.mHasSelection = false;
        this.mHasInsertion = false;
        if (this.mNativeContentViewCore != 0) {
            nativeDismissTextHandles(this.mNativeContentViewCore);
        }
    }

    private void setTextHandlesTemporarilyHidden(boolean hide) {
        if (this.mNativeContentViewCore != 0) {
            nativeSetTextHandlesTemporarilyHidden(this.mNativeContentViewCore, hide);
        }
    }

    public void hideImeIfNeeded() {
        if (this.mInputMethodManagerWrapper.isActive(this.mContainerView)) {
            this.mInputMethodManagerWrapper.hideSoftInputFromWindow(this.mContainerView.getWindowToken(), 0, (ResultReceiver) null);
        }
        getContentViewClient().onImeStateChangeRequested(false);
    }

    @CalledByNative
    private void updateFrameInfo(float scrollOffsetX, float scrollOffsetY, float pageScaleFactor, float minPageScaleFactor, float maxPageScaleFactor, float contentWidth, float contentHeight, float viewportWidth, float viewportHeight, float controlsOffsetYCss, float contentOffsetYCss) {
        TraceEvent.begin("ContentViewCore:updateFrameInfo");
        float deviceScale = this.mRenderCoordinates.getDeviceScaleFactor();
        float contentWidth2 = Math.max(contentWidth, ((float) this.mViewportWidthPix) / (deviceScale * pageScaleFactor));
        float contentHeight2 = Math.max(contentHeight, ((float) this.mViewportHeightPix) / (deviceScale * pageScaleFactor));
        float contentOffsetYPix = this.mRenderCoordinates.fromDipToPix(contentOffsetYCss);
        boolean contentSizeChanged = (contentWidth2 == this.mRenderCoordinates.getContentWidthCss() && contentHeight2 == this.mRenderCoordinates.getContentHeightCss()) ? false : true;
        boolean scaleLimitsChanged = (minPageScaleFactor == this.mRenderCoordinates.getMinPageScaleFactor() && maxPageScaleFactor == this.mRenderCoordinates.getMaxPageScaleFactor()) ? false : true;
        boolean scrollChanged = (!((pageScaleFactor > this.mRenderCoordinates.getPageScaleFactor() ? 1 : (pageScaleFactor == this.mRenderCoordinates.getPageScaleFactor() ? 0 : -1)) != 0) && scrollOffsetX == this.mRenderCoordinates.getScrollX() && scrollOffsetY == this.mRenderCoordinates.getScrollY()) ? false : true;
        boolean contentOffsetChanged = contentOffsetYPix != this.mRenderCoordinates.getContentOffsetYPix();
        boolean needHidePopupZoomer = contentSizeChanged || scrollChanged;
        boolean needUpdateZoomControls = scaleLimitsChanged || scrollChanged;
        if (needHidePopupZoomer) {
            this.mPopupZoomer.hide(true);
        }
        if (scrollChanged) {
            this.mContainerViewInternals.onScrollChanged((int) this.mRenderCoordinates.fromLocalCssToPix(scrollOffsetX), (int) this.mRenderCoordinates.fromLocalCssToPix(scrollOffsetY), (int) this.mRenderCoordinates.getScrollXPix(), (int) this.mRenderCoordinates.getScrollYPix());
        }
        this.mRenderCoordinates.updateFrameInfo(scrollOffsetX, scrollOffsetY, contentWidth2, contentHeight2, viewportWidth, viewportHeight, pageScaleFactor, minPageScaleFactor, maxPageScaleFactor, contentOffsetYPix);
        if (scrollChanged || contentOffsetChanged) {
            this.mGestureStateListenersIterator.rewind();
            while (this.mGestureStateListenersIterator.hasNext()) {
                ((GestureStateListener) this.mGestureStateListenersIterator.next()).onScrollOffsetOrExtentChanged(computeVerticalScrollOffset(), computeVerticalScrollExtent());
            }
        }
        if (needUpdateZoomControls) {
            this.mZoomControlsDelegate.updateZoomControls();
        }
        getContentViewClient().onOffsetsForFullscreenChanged(controlsOffsetYCss * deviceScale, contentOffsetYPix, 0.0f);
        if (this.mBrowserAccessibilityManager != null) {
            this.mBrowserAccessibilityManager.notifyFrameInfoInitialized();
        }
        TraceEvent.end("ContentViewCore:updateFrameInfo");
    }

    @CalledByNative
    private void updateImeAdapter(long nativeImeAdapterAndroid, int textInputType, int textInputFlags, String text, int selectionStart, int selectionEnd, int compositionStart, int compositionEnd, boolean showImeIfNeeded, boolean isNonImeChange) {
        TraceEvent.begin();
        this.mFocusedNodeEditable = textInputType != ImeAdapter.getTextInputTypeNone();
        if (!this.mFocusedNodeEditable) {
            hidePastePopup();
        }
        this.mImeAdapter.updateKeyboardVisibility(nativeImeAdapterAndroid, textInputType, textInputFlags, showImeIfNeeded);
        if (this.mInputConnection != null) {
            this.mInputConnection.updateState(text, selectionStart, selectionEnd, compositionStart, compositionEnd, isNonImeChange);
        }
        if (this.mActionMode != null) {
            this.mActionMode.invalidate();
        }
        TraceEvent.end();
    }

    @CalledByNative
    private void setTitle(String title) {
        getContentViewClient().onUpdateTitle(title);
    }

    @CalledByNative
    private void showSelectPopup(long nativeSelectPopupSourceFrame, Rect bounds, String[] items, int[] enabled, boolean multiple, int[] selectedIndices) {
        if (this.mContainerView.getParent() == null || this.mContainerView.getVisibility() != 0) {
            this.mNativeSelectPopupSourceFrame = nativeSelectPopupSourceFrame;
            selectPopupMenuItems((int[]) null);
            return;
        }
        hidePopupsAndClearSelection();
        if (!$assertionsDisabled && this.mNativeSelectPopupSourceFrame != 0) {
            throw new AssertionError("Zombie popup did not clear the frame source");
        } else if ($assertionsDisabled || items.length == enabled.length) {
            List<SelectPopupItem> popupItems = new ArrayList<>();
            for (int i = 0; i < items.length; i++) {
                popupItems.add(new SelectPopupItem(items[i], enabled[i]));
            }
            if (!DeviceFormFactor.isTablet(this.mContext) || multiple || isTouchExplorationEnabled()) {
                this.mSelectPopup = new SelectPopupDialog(this, popupItems, multiple, selectedIndices);
            } else {
                this.mSelectPopup = new SelectPopupDropdown(this, popupItems, bounds, selectedIndices);
            }
            this.mNativeSelectPopupSourceFrame = nativeSelectPopupSourceFrame;
            this.mSelectPopup.show();
        } else {
            throw new AssertionError();
        }
    }

    @CalledByNative
    private void hideSelectPopup() {
        if (this.mSelectPopup != null) {
            this.mSelectPopup.hide();
        }
    }

    public SelectPopup getSelectPopupForTest() {
        return this.mSelectPopup;
    }

    @CalledByNative
    private void showDisambiguationPopup(Rect targetRect, Bitmap zoomedBitmap) {
        this.mPopupZoomer.setBitmap(zoomedBitmap);
        this.mPopupZoomer.show(targetRect);
    }

    @CalledByNative
    private TouchEventSynthesizer createTouchEventSynthesizer() {
        return new TouchEventSynthesizer(this);
    }

    @CalledByNative
    private PopupTouchHandleDrawable createPopupTouchHandleDrawable() {
        if (this.mTouchHandleDelegate == null) {
            this.mTouchHandleDelegate = new PopupTouchHandleDrawable.PopupTouchHandleDrawableDelegate() {
                public View getParent() {
                    return ContentViewCore.this.getContainerView();
                }

                public PositionObserver getParentPositionObserver() {
                    return ContentViewCore.this.mPositionObserver;
                }

                public boolean onTouchHandleEvent(MotionEvent event) {
                    return ContentViewCore.this.onTouchEventImpl(event, true);
                }

                public boolean isScrollInProgress() {
                    return ContentViewCore.this.isScrollInProgress();
                }
            };
        }
        return new PopupTouchHandleDrawable(this.mTouchHandleDelegate);
    }

    @CalledByNative
    private void onSelectionChanged(String text) {
        this.mLastSelectedText = text;
        getContentViewClient().onSelectionChanged(text);
    }

    @CalledByNative
    private void showPastePopupWithFeedback(int xDip, int yDip) {
        if (showPastePopup(xDip, yDip)) {
            this.mContainerView.performHapticFeedback(0);
        }
    }

    private boolean showPastePopup(int xDip, int yDip) {
        if (!this.mHasInsertion || !canPaste()) {
            return false;
        }
        getPastePopup().showAt((int) this.mRenderCoordinates.fromDipToPix((float) xDip), (int) (this.mRenderCoordinates.fromDipToPix((float) yDip) + this.mRenderCoordinates.getContentOffsetYPix()));
        return true;
    }

    private PastePopupMenu getPastePopup() {
        if (this.mPastePopupMenu == null) {
            this.mPastePopupMenu = new PastePopupMenu(getContainerView(), new PastePopupMenu.PastePopupMenuDelegate() {
                public void paste() {
                    ContentViewCore.this.mImeAdapter.paste();
                    ContentViewCore.this.dismissTextHandles();
                }
            });
        }
        return this.mPastePopupMenu;
    }

    @VisibleForTesting
    public PastePopupMenu getPastePopupForTest() {
        return getPastePopup();
    }

    private boolean canPaste() {
        if (!this.mFocusedNodeEditable) {
            return false;
        }
        return ((ClipboardManager) this.mContext.getSystemService("clipboard")).hasPrimaryClip();
    }

    @CalledByNative
    private void onRenderProcessChange() {
        attachImeAdapter();
    }

    public void attachImeAdapter() {
        if (this.mImeAdapter != null && this.mNativeContentViewCore != 0) {
            this.mImeAdapter.attach(nativeGetNativeImeAdapter(this.mNativeContentViewCore));
        }
    }

    /* access modifiers changed from: private */
    @CalledByNative
    public boolean hasFocus() {
        if (!this.mContainerView.isFocusable()) {
            return true;
        }
        return this.mContainerView.hasFocus();
    }

    public boolean canZoomIn() {
        return this.mRenderCoordinates.getMaxPageScaleFactor() - this.mRenderCoordinates.getPageScaleFactor() > ZOOM_CONTROLS_EPSILON;
    }

    public boolean canZoomOut() {
        return this.mRenderCoordinates.getPageScaleFactor() - this.mRenderCoordinates.getMinPageScaleFactor() > ZOOM_CONTROLS_EPSILON;
    }

    public boolean zoomIn() {
        if (!canZoomIn()) {
            return false;
        }
        return pinchByDelta(1.25f);
    }

    public boolean zoomOut() {
        if (!canZoomOut()) {
            return false;
        }
        return pinchByDelta(0.8f);
    }

    public boolean zoomReset() {
        if (!canZoomOut()) {
            return false;
        }
        return pinchByDelta(this.mRenderCoordinates.getMinPageScaleFactor() / this.mRenderCoordinates.getPageScaleFactor());
    }

    public boolean pinchByDelta(float delta) {
        if (this.mNativeContentViewCore == 0) {
            return false;
        }
        long timeMs = SystemClock.uptimeMillis();
        int xPix = getViewportWidthPix() / 2;
        int yPix = getViewportHeightPix() / 2;
        nativePinchBegin(this.mNativeContentViewCore, timeMs, (float) xPix, (float) yPix);
        nativePinchBy(this.mNativeContentViewCore, timeMs, (float) xPix, (float) yPix, delta);
        nativePinchEnd(this.mNativeContentViewCore, timeMs);
        return true;
    }

    public void invokeZoomPicker() {
        this.mZoomControlsDelegate.invokeZoomPicker();
    }

    public void setAllowJavascriptInterfacesInspection(boolean allow) {
        nativeSetAllowJavascriptInterfacesInspection(this.mNativeContentViewCore, allow);
    }

    public Map<String, Pair<Object, Class>> getJavascriptInterfaces() {
        return this.mJavaScriptInterfaces;
    }

    public void addJavascriptInterface(Object object, String name) {
        addPossiblyUnsafeJavascriptInterface(object, name, JavascriptInterface.class);
    }

    public void addPossiblyUnsafeJavascriptInterface(Object object, String name, Class<? extends Annotation> requiredAnnotation) {
        if (this.mNativeContentViewCore != 0 && object != null) {
            this.mJavaScriptInterfaces.put(name, new Pair(object, requiredAnnotation));
            nativeAddJavascriptInterface(this.mNativeContentViewCore, object, name, requiredAnnotation);
        }
    }

    public void removeJavascriptInterface(String name) {
        this.mJavaScriptInterfaces.remove(name);
        if (this.mNativeContentViewCore != 0) {
            nativeRemoveJavascriptInterface(this.mNativeContentViewCore, name);
        }
    }

    @VisibleForTesting
    public float getScale() {
        return this.mRenderCoordinates.getPageScaleFactor();
    }

    @CalledByNative
    private void startContentIntent(String contentUrl) {
        getContentViewClient().onStartContentIntent(getContext(), contentUrl);
    }

    public void onAccessibilityStateChanged(boolean enabled) {
        setAccessibilityState(enabled);
    }

    public boolean supportsAccessibilityAction(int action) {
        return this.mAccessibilityInjector.supportsAccessibilityAction(action);
    }

    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (this.mAccessibilityInjector.supportsAccessibilityAction(action)) {
            return this.mAccessibilityInjector.performAccessibilityAction(action, arguments);
        }
        return false;
    }

    public void setBrowserAccessibilityManager(BrowserAccessibilityManager manager) {
        this.mBrowserAccessibilityManager = manager;
    }

    public BrowserAccessibilityManager getBrowserAccessibilityManager() {
        return this.mBrowserAccessibilityManager;
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (this.mBrowserAccessibilityManager != null) {
            return this.mBrowserAccessibilityManager.getAccessibilityNodeProvider();
        }
        if (this.mNativeAccessibilityAllowed && !this.mNativeAccessibilityEnabled && this.mNativeContentViewCore != 0 && Build.VERSION.SDK_INT >= 16) {
            this.mNativeAccessibilityEnabled = true;
            nativeSetAccessibilityEnabled(this.mNativeContentViewCore, true);
        }
        return null;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        this.mAccessibilityInjector.onInitializeAccessibilityNodeInfo(info);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        boolean z = false;
        event.setClassName(getClass().getName());
        event.setScrollX(this.mRenderCoordinates.getScrollXPixInt());
        event.setScrollY(this.mRenderCoordinates.getScrollYPixInt());
        int maxScrollXPix = Math.max(0, this.mRenderCoordinates.getMaxHorizontalScrollPixInt());
        int maxScrollYPix = Math.max(0, this.mRenderCoordinates.getMaxVerticalScrollPixInt());
        if (maxScrollXPix > 0 || maxScrollYPix > 0) {
            z = true;
        }
        event.setScrollable(z);
        if (Build.VERSION.SDK_INT >= 15) {
            event.setMaxScrollX(maxScrollXPix);
            event.setMaxScrollY(maxScrollYPix);
        }
    }

    public boolean isDeviceAccessibilityScriptInjectionEnabled() {
        boolean z = true;
        try {
            if ((Build.VERSION.SDK_INT >= 16 && !CommandLine.getInstance().hasSwitch(ContentSwitches.ENABLE_ACCESSIBILITY_SCRIPT_INJECTION)) || !this.mContentSettings.getJavaScriptEnabled() || getContext().checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
                return false;
            }
            Field field = Settings.Secure.class.getField("ACCESSIBILITY_SCRIPT_INJECTION");
            field.setAccessible(true);
            String accessibilityScriptInjection = (String) field.get((Object) null);
            ContentResolver contentResolver = getContext().getContentResolver();
            if (this.mAccessibilityScriptInjectionObserver == null) {
                ContentObserver contentObserver = new ContentObserver(new Handler()) {
                    public void onChange(boolean selfChange, Uri uri) {
                        ContentViewCore.this.setAccessibilityState(ContentViewCore.this.mAccessibilityManager.isEnabled());
                    }
                };
                contentResolver.registerContentObserver(Settings.Secure.getUriFor(accessibilityScriptInjection), false, contentObserver);
                this.mAccessibilityScriptInjectionObserver = contentObserver;
            }
            if (Settings.Secure.getInt(contentResolver, accessibilityScriptInjection, 0) != 1) {
                z = false;
            }
            return z;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return false;
        }
    }

    public boolean isInjectingAccessibilityScript() {
        return this.mAccessibilityInjector.accessibilityIsAvailable();
    }

    public boolean isTouchExplorationEnabled() {
        return this.mTouchExplorationEnabled;
    }

    public void setAccessibilityState(boolean state) {
        boolean z = false;
        if (!state) {
            setInjectedAccessibility(false);
            this.mNativeAccessibilityAllowed = false;
            this.mTouchExplorationEnabled = false;
            return;
        }
        boolean useScriptInjection = isDeviceAccessibilityScriptInjectionEnabled();
        setInjectedAccessibility(useScriptInjection);
        if (!useScriptInjection) {
            z = true;
        }
        this.mNativeAccessibilityAllowed = z;
        this.mTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
    }

    public void setInjectedAccessibility(boolean enabled) {
        this.mAccessibilityInjector.addOrRemoveAccessibilityApisIfNecessary();
        this.mAccessibilityInjector.setScriptEnabled(enabled);
    }

    public void stopCurrentAccessibilityNotifications() {
        this.mAccessibilityInjector.onPageLostFocus();
    }

    public boolean shouldSetAccessibilityFocusOnPageLoad() {
        return this.mShouldSetAccessibilityFocusOnPageLoad;
    }

    public void setShouldSetAccessibilityFocusOnPageLoad(boolean on) {
        this.mShouldSetAccessibilityFocusOnPageLoad = on;
    }

    public RenderCoordinates getRenderCoordinates() {
        return this.mRenderCoordinates;
    }

    @CalledByNative
    private static Rect createRect(int x, int y, int right, int bottom) {
        return new Rect(x, y, right, bottom);
    }

    public void extractSmartClipData(int x, int y, int width, int height) {
        if (this.mNativeContentViewCore != 0) {
            nativeExtractSmartClipData(this.mNativeContentViewCore, x + this.mSmartClipOffsetX, y + this.mSmartClipOffsetY, width, height);
        }
    }

    public void setSmartClipOffsets(int offsetX, int offsetY) {
        this.mSmartClipOffsetX = offsetX;
        this.mSmartClipOffsetY = offsetY;
    }

    @CalledByNative
    private void onSmartClipDataExtracted(String text, String html, Rect clipRect) {
        float deviceScale = this.mRenderCoordinates.getDeviceScaleFactor();
        clipRect.offset(-((int) (((float) this.mSmartClipOffsetX) / deviceScale)), -((int) (((float) this.mSmartClipOffsetY) / deviceScale)));
        if (this.mSmartClipDataListener != null) {
            this.mSmartClipDataListener.onSmartClipDataExtracted(text, html, clipRect);
        }
    }

    public void setSmartClipDataListener(SmartClipDataListener listener) {
        this.mSmartClipDataListener = listener;
    }

    public void setBackgroundOpaque(boolean opaque) {
        if (this.mNativeContentViewCore != 0) {
            nativeSetBackgroundOpaque(this.mNativeContentViewCore, opaque);
        }
    }

    private boolean offerLongPressToEmbedder() {
        return this.mContainerView.performLongClick();
    }

    /* access modifiers changed from: private */
    public void resetScrollInProgress() {
        if (isScrollInProgress()) {
            boolean touchScrollInProgress = this.mTouchScrollInProgress;
            int potentiallyActiveFlingCount = this.mPotentiallyActiveFlingCount;
            this.mTouchScrollInProgress = false;
            this.mPotentiallyActiveFlingCount = 0;
            if (touchScrollInProgress) {
                updateGestureStateListener(8);
            }
            if (potentiallyActiveFlingCount > 0) {
                updateGestureStateListener(11);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ContentVideoViewClient getContentVideoViewClient() {
        return getContentViewClient().getContentVideoViewClient();
    }

    @CalledByNative
    private boolean shouldBlockMediaRequest(String url) {
        return getContentViewClient().shouldBlockMediaRequest(url);
    }

    @CalledByNative
    private void onNativeFlingStopped() {
        this.mTouchScrollInProgress = false;
        if (this.mPotentiallyActiveFlingCount > 0) {
            this.mPotentiallyActiveFlingCount--;
            updateGestureStateListener(11);
        }
    }

    public void onScreenOrientationChanged(int orientation) {
        sendOrientationChangeEvent(orientation);
    }

    public void setFullscreenRequiredForOrientationLock(boolean value) {
        this.mFullscreenRequiredForOrientationLock = value;
    }

    @CalledByNative
    private boolean isFullscreenRequiredForOrientationLock() {
        return this.mFullscreenRequiredForOrientationLock;
    }
}
