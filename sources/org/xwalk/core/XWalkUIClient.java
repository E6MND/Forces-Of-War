package org.xwalk.core;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import java.lang.reflect.Method;

public class XWalkUIClient {
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkUIClientBridge";
    private Object bridge;
    private Class<?> enumInitiateByClass;
    private Method enumInitiateByClassValueOfMethod;
    private Class<?> enumJavascriptMessageTypeClass;
    private Method enumJavascriptMessageTypeClassValueOfMethod;
    private Class<?> enumLoadStatusClass;
    private Method enumLoadStatusClassValueOfMethod;
    private Method onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod;
    private Method onFullscreenToggledXWalkViewInternalbooleanMethod;
    private Method onIconAvailableXWalkViewInternalStringMessageMethod;
    private Method onJavascriptCloseWindowXWalkViewInternalMethod;
    private Method onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod;
    private Method onPageLoadStartedXWalkViewInternalStringMethod;
    private Method onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod;
    private Method onReceivedIconXWalkViewInternalStringBitmapMethod;
    private Method onReceivedTitleXWalkViewInternalStringMethod;
    private Method onRequestFocusXWalkViewInternalMethod;
    private Method onScaleChangedXWalkViewInternalfloatfloatMethod;
    private Method onUnhandledKeyEventXWalkViewInternalKeyEventMethod;
    private Method openFileChooserXWalkViewInternalValueCallbackStringStringMethod;
    private Method shouldOverrideKeyEventXWalkViewInternalKeyEventMethod;

    public enum InitiateBy {
        BY_USER_GESTURE,
        BY_JAVASCRIPT
    }

    public enum JavascriptMessageType {
        JAVASCRIPT_ALERT,
        JAVASCRIPT_CONFIRM,
        JAVASCRIPT_PROMPT,
        JAVASCRIPT_BEFOREUNLOAD
    }

    public enum LoadStatus {
        FINISHED,
        FAILED,
        CANCELLED
    }

    private Object ConvertJavascriptMessageType(JavascriptMessageType type) {
        return ReflectionHelper.invokeMethod(this.enumJavascriptMessageTypeClassValueOfMethod, (Object) null, type.toString());
    }

    private Object ConvertInitiateBy(InitiateBy type) {
        return ReflectionHelper.invokeMethod(this.enumInitiateByClassValueOfMethod, (Object) null, type.toString());
    }

    private Object ConvertLoadStatus(LoadStatus type) {
        return ReflectionHelper.invokeMethod(this.enumLoadStatusClassValueOfMethod, (Object) null, type.toString());
    }

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public XWalkUIClient(XWalkView view) {
        this.bridge = ReflectionHelper.createInstance("XWalkUIClientInternalXWalkViewInternalConstructor", view.getBridge(), this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public boolean onCreateWindowRequested(XWalkView view, InitiateBy initiator, ValueCallback<XWalkView> callback) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod, this.bridge, view.getBridge(), ConvertInitiateBy(initiator), callback)).booleanValue();
    }

    public void onIconAvailable(XWalkView view, String url, Message startDownload) {
        ReflectionHelper.invokeMethod(this.onIconAvailableXWalkViewInternalStringMessageMethod, this.bridge, view.getBridge(), url, startDownload);
    }

    public void onReceivedIcon(XWalkView view, String url, Bitmap icon) {
        ReflectionHelper.invokeMethod(this.onReceivedIconXWalkViewInternalStringBitmapMethod, this.bridge, view.getBridge(), url, icon);
    }

    public void onRequestFocus(XWalkView view) {
        ReflectionHelper.invokeMethod(this.onRequestFocusXWalkViewInternalMethod, this.bridge, view.getBridge());
    }

    public void onJavascriptCloseWindow(XWalkView view) {
        ReflectionHelper.invokeMethod(this.onJavascriptCloseWindowXWalkViewInternalMethod, this.bridge, view.getBridge());
    }

    public boolean onJavascriptModalDialog(XWalkView view, JavascriptMessageType type, String url, String message, String defaultValue, XWalkJavascriptResult result) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod, this.bridge, view.getBridge(), ConvertJavascriptMessageType(type), url, message, defaultValue, ((XWalkJavascriptResultHandler) result).getBridge())).booleanValue();
    }

    public void onFullscreenToggled(XWalkView view, boolean enterFullscreen) {
        ReflectionHelper.invokeMethod(this.onFullscreenToggledXWalkViewInternalbooleanMethod, this.bridge, view.getBridge(), Boolean.valueOf(enterFullscreen));
    }

    public void openFileChooser(XWalkView view, ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        ReflectionHelper.invokeMethod(this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod, this.bridge, view.getBridge(), uploadFile, acceptType, capture);
    }

    public void onScaleChanged(XWalkView view, float oldScale, float newScale) {
        ReflectionHelper.invokeMethod(this.onScaleChangedXWalkViewInternalfloatfloatMethod, this.bridge, view.getBridge(), Float.valueOf(oldScale), Float.valueOf(newScale));
    }

    public boolean shouldOverrideKeyEvent(XWalkView view, KeyEvent event) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod, this.bridge, view.getBridge(), event)).booleanValue();
    }

    public void onUnhandledKeyEvent(XWalkView view, KeyEvent event) {
        ReflectionHelper.invokeMethod(this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod, this.bridge, view.getBridge(), event);
    }

    public void onReceivedTitle(XWalkView view, String title) {
        ReflectionHelper.invokeMethod(this.onReceivedTitleXWalkViewInternalStringMethod, this.bridge, view.getBridge(), title);
    }

    public void onPageLoadStarted(XWalkView view, String url) {
        ReflectionHelper.invokeMethod(this.onPageLoadStartedXWalkViewInternalStringMethod, this.bridge, view.getBridge(), url);
    }

    public void onPageLoadStopped(XWalkView view, String url, LoadStatus status) {
        ReflectionHelper.invokeMethod(this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod, this.bridge, view.getBridge(), url, ConvertLoadStatus(status));
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.bridge.getClass();
        this.enumJavascriptMessageTypeClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkUIClientInternal$JavascriptMessageTypeInternal");
        this.enumJavascriptMessageTypeClassValueOfMethod = this.enumJavascriptMessageTypeClass.getMethod("valueOf", new Class[]{String.class});
        this.enumInitiateByClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkUIClientInternal$InitiateByInternal");
        this.enumInitiateByClassValueOfMethod = this.enumInitiateByClass.getMethod("valueOf", new Class[]{String.class});
        this.enumLoadStatusClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkUIClientInternal$LoadStatusInternal");
        this.enumLoadStatusClassValueOfMethod = this.enumLoadStatusClass.getMethod("valueOf", new Class[]{String.class});
        this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod = ReflectionHelper.loadMethod(clazz, "onCreateWindowRequestedSuper", "org.xwalk.core.internal.XWalkViewBridge", this.enumInitiateByClass, ValueCallback.class);
        this.onIconAvailableXWalkViewInternalStringMessageMethod = ReflectionHelper.loadMethod(clazz, "onIconAvailableSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class, Message.class);
        this.onReceivedIconXWalkViewInternalStringBitmapMethod = ReflectionHelper.loadMethod(clazz, "onReceivedIconSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class, Bitmap.class);
        this.onRequestFocusXWalkViewInternalMethod = ReflectionHelper.loadMethod(clazz, "onRequestFocusSuper", "org.xwalk.core.internal.XWalkViewBridge");
        this.onJavascriptCloseWindowXWalkViewInternalMethod = ReflectionHelper.loadMethod(clazz, "onJavascriptCloseWindowSuper", "org.xwalk.core.internal.XWalkViewBridge");
        this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod = ReflectionHelper.loadMethod(clazz, "onJavascriptModalDialogSuper", "org.xwalk.core.internal.XWalkViewBridge", this.enumJavascriptMessageTypeClass, String.class, String.class, String.class, "org.xwalk.core.internal.XWalkJavascriptResultHandlerBridge");
        this.onFullscreenToggledXWalkViewInternalbooleanMethod = ReflectionHelper.loadMethod(clazz, "onFullscreenToggledSuper", "org.xwalk.core.internal.XWalkViewBridge", Boolean.TYPE);
        this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod = ReflectionHelper.loadMethod(clazz, "openFileChooserSuper", "org.xwalk.core.internal.XWalkViewBridge", ValueCallback.class, String.class, String.class);
        this.onScaleChangedXWalkViewInternalfloatfloatMethod = ReflectionHelper.loadMethod(clazz, "onScaleChangedSuper", "org.xwalk.core.internal.XWalkViewBridge", Float.TYPE, Float.TYPE);
        this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod = ReflectionHelper.loadMethod(clazz, "shouldOverrideKeyEventSuper", "org.xwalk.core.internal.XWalkViewBridge", KeyEvent.class);
        this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod = ReflectionHelper.loadMethod(clazz, "onUnhandledKeyEventSuper", "org.xwalk.core.internal.XWalkViewBridge", KeyEvent.class);
        this.onReceivedTitleXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onReceivedTitleSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        this.onPageLoadStartedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onPageLoadStartedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod = ReflectionHelper.loadMethod(clazz, "onPageLoadStoppedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class, this.enumLoadStatusClass);
    }

    static {
        ReflectionHelper.registerConstructor("XWalkUIClientInternalXWalkViewInternalConstructor", BRIDGE_CLASS, "org.xwalk.core.internal.XWalkViewBridge", Object.class);
    }
}
