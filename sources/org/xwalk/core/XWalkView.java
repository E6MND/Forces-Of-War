package org.xwalk.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

public class XWalkView extends FrameLayout {
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkViewBridge";
    public static final int RELOAD_IGNORE_CACHE = 1;
    public static final int RELOAD_NORMAL = 0;
    private Method addJavascriptInterfaceObjectStringMethod;
    private Object bridge;
    private Method clearCachebooleanMethod;
    private Method evaluateJavascriptStringValueCallbackMethod;
    private Method getAPIVersionMethod;
    private Method getNavigationHistoryMethod;
    private Method getOriginalUrlMethod;
    private Method getRemoteDebuggingUrlMethod;
    private Method getTitleMethod;
    private Method getUrlMethod;
    private Method getXWalkVersionMethod;
    private Method hasEnteredFullscreenMethod;
    private Method leaveFullscreenMethod;
    private Method loadAppFromManifestStringStringMethod;
    private Method loadStringStringMethod;
    private Method onActivityResultintintIntentMethod;
    private Method onDestroyMethod;
    private Method onHideMethod;
    private Method onNewIntentIntentMethod;
    private Method onShowMethod;
    private Method pauseTimersMethod;
    private Method reloadintMethod;
    private Method restoreStateBundleMethod;
    private Method resumeTimersMethod;
    private Method saveStateBundleMethod;
    private Method setBackgroundColorintMethod;
    private Method setLayerTypeintPaintMethod;
    private Method setNetworkAvailablebooleanMethod;
    private Method setResourceClientXWalkResourceClientInternalMethod;
    private Method setUIClientXWalkUIClientInternalMethod;
    private Method stopLoadingMethod;

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public XWalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.bridge = ReflectionHelper.createInstance("XWalkViewInternalContextAttributeSetConstructor", context, attrs, this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
        if (this.bridge != null) {
            addView((FrameLayout) this.bridge, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public XWalkView(Context context, Activity activity) {
        super(context, (AttributeSet) null);
        this.bridge = ReflectionHelper.createInstance("XWalkViewInternalContextActivityConstructor", context, activity, this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
        if (this.bridge != null) {
            addView((FrameLayout) this.bridge, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public void load(String url, String content) {
        ReflectionHelper.invokeMethod(this.loadStringStringMethod, this.bridge, url, content);
    }

    public void loadAppFromManifest(String url, String content) {
        ReflectionHelper.invokeMethod(this.loadAppFromManifestStringStringMethod, this.bridge, url, content);
    }

    public void reload(int mode) {
        ReflectionHelper.invokeMethod(this.reloadintMethod, this.bridge, Integer.valueOf(mode));
    }

    public void stopLoading() {
        ReflectionHelper.invokeMethod(this.stopLoadingMethod, this.bridge, new Object[0]);
    }

    public String getUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getUrlMethod, this.bridge, new Object[0]);
    }

    public String getTitle() {
        return (String) ReflectionHelper.invokeMethod(this.getTitleMethod, this.bridge, new Object[0]);
    }

    public String getOriginalUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getOriginalUrlMethod, this.bridge, new Object[0]);
    }

    public XWalkNavigationHistory getNavigationHistory() {
        return (XWalkNavigationHistory) ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(this.getNavigationHistoryMethod, this.bridge, new Object[0]));
    }

    public void addJavascriptInterface(Object object, String name) {
        ReflectionHelper.invokeMethod(this.addJavascriptInterfaceObjectStringMethod, this.bridge, object, name);
    }

    public void evaluateJavascript(String script, ValueCallback<String> callback) {
        ReflectionHelper.invokeMethod(this.evaluateJavascriptStringValueCallbackMethod, this.bridge, script, callback);
    }

    public void clearCache(boolean includeDiskFiles) {
        ReflectionHelper.invokeMethod(this.clearCachebooleanMethod, this.bridge, Boolean.valueOf(includeDiskFiles));
    }

    public boolean hasEnteredFullscreen() {
        return ((Boolean) ReflectionHelper.invokeMethod(this.hasEnteredFullscreenMethod, this.bridge, new Object[0])).booleanValue();
    }

    public void leaveFullscreen() {
        ReflectionHelper.invokeMethod(this.leaveFullscreenMethod, this.bridge, new Object[0]);
    }

    public void pauseTimers() {
        ReflectionHelper.invokeMethod(this.pauseTimersMethod, this.bridge, new Object[0]);
    }

    public void resumeTimers() {
        ReflectionHelper.invokeMethod(this.resumeTimersMethod, this.bridge, new Object[0]);
    }

    public void onHide() {
        ReflectionHelper.invokeMethod(this.onHideMethod, this.bridge, new Object[0]);
    }

    public void onShow() {
        ReflectionHelper.invokeMethod(this.onShowMethod, this.bridge, new Object[0]);
    }

    public void onDestroy() {
        ReflectionHelper.invokeMethod(this.onDestroyMethod, this.bridge, new Object[0]);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ReflectionHelper.invokeMethod(this.onActivityResultintintIntentMethod, this.bridge, Integer.valueOf(requestCode), Integer.valueOf(resultCode), data);
    }

    public boolean onNewIntent(Intent intent) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.onNewIntentIntentMethod, this.bridge, intent)).booleanValue();
    }

    public boolean saveState(Bundle outState) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.saveStateBundleMethod, this.bridge, outState)).booleanValue();
    }

    public boolean restoreState(Bundle inState) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.restoreStateBundleMethod, this.bridge, inState)).booleanValue();
    }

    public String getAPIVersion() {
        return (String) ReflectionHelper.invokeMethod(this.getAPIVersionMethod, this.bridge, new Object[0]);
    }

    public String getXWalkVersion() {
        return (String) ReflectionHelper.invokeMethod(this.getXWalkVersionMethod, this.bridge, new Object[0]);
    }

    public void setUIClient(XWalkUIClient client) {
        ReflectionHelper.invokeMethod(this.setUIClientXWalkUIClientInternalMethod, this.bridge, client.getBridge());
    }

    public void setResourceClient(XWalkResourceClient client) {
        ReflectionHelper.invokeMethod(this.setResourceClientXWalkResourceClientInternalMethod, this.bridge, client.getBridge());
    }

    public void setBackgroundColor(int color) {
        ReflectionHelper.invokeMethod(this.setBackgroundColorintMethod, this.bridge, Integer.valueOf(color));
    }

    public void setLayerType(int layerType, Paint paint) {
        ReflectionHelper.invokeMethod(this.setLayerTypeintPaintMethod, this.bridge, Integer.valueOf(layerType), paint);
    }

    public void setNetworkAvailable(boolean networkUp) {
        ReflectionHelper.invokeMethod(this.setNetworkAvailablebooleanMethod, this.bridge, Boolean.valueOf(networkUp));
    }

    public Uri getRemoteDebuggingUrl() {
        return (Uri) ReflectionHelper.invokeMethod(this.getRemoteDebuggingUrlMethod, this.bridge, new Object[0]);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.bridge.getClass();
        this.loadStringStringMethod = ReflectionHelper.loadMethod(clazz, "loadSuper", String.class, String.class);
        this.loadAppFromManifestStringStringMethod = ReflectionHelper.loadMethod(clazz, "loadAppFromManifestSuper", String.class, String.class);
        this.reloadintMethod = ReflectionHelper.loadMethod(clazz, "reloadSuper", Integer.TYPE);
        this.stopLoadingMethod = ReflectionHelper.loadMethod(clazz, "stopLoadingSuper", new Object[0]);
        this.getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrlSuper", new Object[0]);
        this.getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitleSuper", new Object[0]);
        this.getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrlSuper", new Object[0]);
        this.getNavigationHistoryMethod = ReflectionHelper.loadMethod(clazz, "getNavigationHistorySuper", new Object[0]);
        this.addJavascriptInterfaceObjectStringMethod = ReflectionHelper.loadMethod(clazz, "addJavascriptInterfaceSuper", Object.class, String.class);
        this.evaluateJavascriptStringValueCallbackMethod = ReflectionHelper.loadMethod(clazz, "evaluateJavascriptSuper", String.class, ValueCallback.class);
        this.clearCachebooleanMethod = ReflectionHelper.loadMethod(clazz, "clearCacheSuper", Boolean.TYPE);
        this.hasEnteredFullscreenMethod = ReflectionHelper.loadMethod(clazz, "hasEnteredFullscreenSuper", new Object[0]);
        this.leaveFullscreenMethod = ReflectionHelper.loadMethod(clazz, "leaveFullscreenSuper", new Object[0]);
        this.pauseTimersMethod = ReflectionHelper.loadMethod(clazz, "pauseTimersSuper", new Object[0]);
        this.resumeTimersMethod = ReflectionHelper.loadMethod(clazz, "resumeTimersSuper", new Object[0]);
        this.onHideMethod = ReflectionHelper.loadMethod(clazz, "onHideSuper", new Object[0]);
        this.onShowMethod = ReflectionHelper.loadMethod(clazz, "onShowSuper", new Object[0]);
        this.onDestroyMethod = ReflectionHelper.loadMethod(clazz, "onDestroySuper", new Object[0]);
        this.onActivityResultintintIntentMethod = ReflectionHelper.loadMethod(clazz, "onActivityResultSuper", Integer.TYPE, Integer.TYPE, Intent.class);
        this.onNewIntentIntentMethod = ReflectionHelper.loadMethod(clazz, "onNewIntentSuper", Intent.class);
        this.saveStateBundleMethod = ReflectionHelper.loadMethod(clazz, "saveStateSuper", Bundle.class);
        this.restoreStateBundleMethod = ReflectionHelper.loadMethod(clazz, "restoreStateSuper", Bundle.class);
        this.getAPIVersionMethod = ReflectionHelper.loadMethod(clazz, "getAPIVersionSuper", new Object[0]);
        this.getXWalkVersionMethod = ReflectionHelper.loadMethod(clazz, "getXWalkVersionSuper", new Object[0]);
        this.setUIClientXWalkUIClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setUIClientSuper", "org.xwalk.core.internal.XWalkUIClientBridge");
        this.setResourceClientXWalkResourceClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setResourceClientSuper", "org.xwalk.core.internal.XWalkResourceClientBridge");
        this.setBackgroundColorintMethod = ReflectionHelper.loadMethod(clazz, "setBackgroundColorSuper", Integer.TYPE);
        this.setLayerTypeintPaintMethod = ReflectionHelper.loadMethod(clazz, "setLayerTypeSuper", Integer.TYPE, Paint.class);
        this.setNetworkAvailablebooleanMethod = ReflectionHelper.loadMethod(clazz, "setNetworkAvailableSuper", Boolean.TYPE);
        this.getRemoteDebuggingUrlMethod = ReflectionHelper.loadMethod(clazz, "getRemoteDebuggingUrlSuper", new Object[0]);
    }

    static {
        ReflectionHelper.registerConstructor("XWalkViewInternalContextAttributeSetConstructor", BRIDGE_CLASS, Context.class, AttributeSet.class, Object.class);
        ReflectionHelper.registerConstructor("XWalkViewInternalContextActivityConstructor", BRIDGE_CLASS, Context.class, Activity.class, Object.class);
    }
}
