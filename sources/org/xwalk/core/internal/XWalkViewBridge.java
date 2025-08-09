package org.xwalk.core.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import java.lang.reflect.Method;

public class XWalkViewBridge extends XWalkViewInternal {
    private static final String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Method addJavascriptInterfaceObjectStringMethod;
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
    private Object wrapper;

    public Object getWrapper() {
        return this.wrapper;
    }

    public XWalkViewBridge(Context context, AttributeSet attrs, Object wrapper2) {
        super(context, attrs);
        this.wrapper = wrapper2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public XWalkViewBridge(Context context, Activity activity, Object wrapper2) {
        super(context, activity);
        this.wrapper = wrapper2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public void load(String url, String content) {
        ReflectionHelper.invokeMethod(this.loadStringStringMethod, this.wrapper, url, content);
    }

    public void loadSuper(String url, String content) {
        super.load(url, content);
    }

    public void loadAppFromManifest(String url, String content) {
        ReflectionHelper.invokeMethod(this.loadAppFromManifestStringStringMethod, this.wrapper, url, content);
    }

    public void loadAppFromManifestSuper(String url, String content) {
        super.loadAppFromManifest(url, content);
    }

    public void reload(int mode) {
        ReflectionHelper.invokeMethod(this.reloadintMethod, this.wrapper, Integer.valueOf(mode));
    }

    public void reloadSuper(int mode) {
        super.reload(mode);
    }

    public void stopLoading() {
        ReflectionHelper.invokeMethod(this.stopLoadingMethod, this.wrapper, new Object[0]);
    }

    public void stopLoadingSuper() {
        super.stopLoading();
    }

    public String getUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getUrlMethod, this.wrapper, new Object[0]);
    }

    public String getUrlSuper() {
        String ret = super.getUrl();
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public String getTitle() {
        return (String) ReflectionHelper.invokeMethod(this.getTitleMethod, this.wrapper, new Object[0]);
    }

    public String getTitleSuper() {
        String ret = super.getTitle();
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public String getOriginalUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getOriginalUrlMethod, this.wrapper, new Object[0]);
    }

    public String getOriginalUrlSuper() {
        String ret = super.getOriginalUrl();
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public XWalkNavigationHistoryInternal getNavigationHistory() {
        return (XWalkNavigationHistoryBridge) ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(this.getNavigationHistoryMethod, this.wrapper, new Object[0]));
    }

    public XWalkNavigationHistoryBridge getNavigationHistorySuper() {
        XWalkNavigationHistoryInternal ret = super.getNavigationHistory();
        if (ret == null) {
            return null;
        }
        return ret instanceof XWalkNavigationHistoryBridge ? (XWalkNavigationHistoryBridge) ret : new XWalkNavigationHistoryBridge(ret);
    }

    public void addJavascriptInterface(Object object, String name) {
        ReflectionHelper.invokeMethod(this.addJavascriptInterfaceObjectStringMethod, this.wrapper, object, name);
    }

    public void addJavascriptInterfaceSuper(Object object, String name) {
        super.addJavascriptInterface(object, name);
    }

    public void evaluateJavascript(String script, ValueCallback<String> callback) {
        ReflectionHelper.invokeMethod(this.evaluateJavascriptStringValueCallbackMethod, this.wrapper, script, callback);
    }

    public void evaluateJavascriptSuper(String script, ValueCallback<String> callback) {
        super.evaluateJavascript(script, callback);
    }

    public void clearCache(boolean includeDiskFiles) {
        ReflectionHelper.invokeMethod(this.clearCachebooleanMethod, this.wrapper, Boolean.valueOf(includeDiskFiles));
    }

    public void clearCacheSuper(boolean includeDiskFiles) {
        super.clearCache(includeDiskFiles);
    }

    public boolean hasEnteredFullscreen() {
        return ((Boolean) ReflectionHelper.invokeMethod(this.hasEnteredFullscreenMethod, this.wrapper, new Object[0])).booleanValue();
    }

    public boolean hasEnteredFullscreenSuper() {
        return super.hasEnteredFullscreen();
    }

    public void leaveFullscreen() {
        ReflectionHelper.invokeMethod(this.leaveFullscreenMethod, this.wrapper, new Object[0]);
    }

    public void leaveFullscreenSuper() {
        super.leaveFullscreen();
    }

    public void pauseTimers() {
        ReflectionHelper.invokeMethod(this.pauseTimersMethod, this.wrapper, new Object[0]);
    }

    public void pauseTimersSuper() {
        super.pauseTimers();
    }

    public void resumeTimers() {
        ReflectionHelper.invokeMethod(this.resumeTimersMethod, this.wrapper, new Object[0]);
    }

    public void resumeTimersSuper() {
        super.resumeTimers();
    }

    public void onHide() {
        ReflectionHelper.invokeMethod(this.onHideMethod, this.wrapper, new Object[0]);
    }

    public void onHideSuper() {
        super.onHide();
    }

    public void onShow() {
        ReflectionHelper.invokeMethod(this.onShowMethod, this.wrapper, new Object[0]);
    }

    public void onShowSuper() {
        super.onShow();
    }

    public void onDestroy() {
        ReflectionHelper.invokeMethod(this.onDestroyMethod, this.wrapper, new Object[0]);
    }

    public void onDestroySuper() {
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ReflectionHelper.invokeMethod(this.onActivityResultintintIntentMethod, this.wrapper, Integer.valueOf(requestCode), Integer.valueOf(resultCode), data);
    }

    public void onActivityResultSuper(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean onNewIntent(Intent intent) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.onNewIntentIntentMethod, this.wrapper, intent)).booleanValue();
    }

    public boolean onNewIntentSuper(Intent intent) {
        return super.onNewIntent(intent);
    }

    public boolean saveState(Bundle outState) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.saveStateBundleMethod, this.wrapper, outState)).booleanValue();
    }

    public boolean saveStateSuper(Bundle outState) {
        return super.saveState(outState);
    }

    public boolean restoreState(Bundle inState) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.restoreStateBundleMethod, this.wrapper, inState)).booleanValue();
    }

    public boolean restoreStateSuper(Bundle inState) {
        return super.restoreState(inState);
    }

    public String getAPIVersion() {
        return (String) ReflectionHelper.invokeMethod(this.getAPIVersionMethod, this.wrapper, new Object[0]);
    }

    public String getAPIVersionSuper() {
        String ret = super.getAPIVersion();
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public String getXWalkVersion() {
        return (String) ReflectionHelper.invokeMethod(this.getXWalkVersionMethod, this.wrapper, new Object[0]);
    }

    public String getXWalkVersionSuper() {
        String ret = super.getXWalkVersion();
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public void setUIClient(XWalkUIClientInternal client) {
        if (client instanceof XWalkUIClientBridge) {
            setUIClient((XWalkUIClientBridge) client);
        } else {
            super.setUIClient(client);
        }
    }

    public void setUIClient(XWalkUIClientBridge client) {
        ReflectionHelper.invokeMethod(this.setUIClientXWalkUIClientInternalMethod, this.wrapper, client.getWrapper());
    }

    public void setUIClientSuper(XWalkUIClientBridge client) {
        super.setUIClient(client);
    }

    public void setResourceClient(XWalkResourceClientInternal client) {
        if (client instanceof XWalkResourceClientBridge) {
            setResourceClient((XWalkResourceClientBridge) client);
        } else {
            super.setResourceClient(client);
        }
    }

    public void setResourceClient(XWalkResourceClientBridge client) {
        ReflectionHelper.invokeMethod(this.setResourceClientXWalkResourceClientInternalMethod, this.wrapper, client.getWrapper());
    }

    public void setResourceClientSuper(XWalkResourceClientBridge client) {
        super.setResourceClient(client);
    }

    public void setBackgroundColor(int color) {
        ReflectionHelper.invokeMethod(this.setBackgroundColorintMethod, this.wrapper, Integer.valueOf(color));
    }

    public void setBackgroundColorSuper(int color) {
        super.setBackgroundColor(color);
    }

    public void setLayerType(int layerType, Paint paint) {
        ReflectionHelper.invokeMethod(this.setLayerTypeintPaintMethod, this.wrapper, Integer.valueOf(layerType), paint);
    }

    public void setLayerTypeSuper(int layerType, Paint paint) {
        super.setLayerType(layerType, paint);
    }

    public void setNetworkAvailable(boolean networkUp) {
        ReflectionHelper.invokeMethod(this.setNetworkAvailablebooleanMethod, this.wrapper, Boolean.valueOf(networkUp));
    }

    public void setNetworkAvailableSuper(boolean networkUp) {
        super.setNetworkAvailable(networkUp);
    }

    public Uri getRemoteDebuggingUrl() {
        return (Uri) ReflectionHelper.invokeMethod(this.getRemoteDebuggingUrlMethod, this.wrapper, new Object[0]);
    }

    public Uri getRemoteDebuggingUrlSuper() {
        Uri ret = super.getRemoteDebuggingUrl();
        if (ret == null) {
            return null;
        }
        return ret;
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.wrapper.getClass();
        this.loadStringStringMethod = ReflectionHelper.loadMethod(clazz, "load", String.class, String.class);
        this.loadAppFromManifestStringStringMethod = ReflectionHelper.loadMethod(clazz, "loadAppFromManifest", String.class, String.class);
        this.reloadintMethod = ReflectionHelper.loadMethod(clazz, "reload", Integer.TYPE);
        this.stopLoadingMethod = ReflectionHelper.loadMethod(clazz, "stopLoading", new Object[0]);
        this.getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrl", new Object[0]);
        this.getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitle", new Object[0]);
        this.getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrl", new Object[0]);
        this.getNavigationHistoryMethod = ReflectionHelper.loadMethod(clazz, "getNavigationHistory", new Object[0]);
        this.addJavascriptInterfaceObjectStringMethod = ReflectionHelper.loadMethod(clazz, "addJavascriptInterface", Object.class, String.class);
        this.evaluateJavascriptStringValueCallbackMethod = ReflectionHelper.loadMethod(clazz, "evaluateJavascript", String.class, ValueCallback.class);
        this.clearCachebooleanMethod = ReflectionHelper.loadMethod(clazz, "clearCache", Boolean.TYPE);
        this.hasEnteredFullscreenMethod = ReflectionHelper.loadMethod(clazz, "hasEnteredFullscreen", new Object[0]);
        this.leaveFullscreenMethod = ReflectionHelper.loadMethod(clazz, "leaveFullscreen", new Object[0]);
        this.pauseTimersMethod = ReflectionHelper.loadMethod(clazz, "pauseTimers", new Object[0]);
        this.resumeTimersMethod = ReflectionHelper.loadMethod(clazz, "resumeTimers", new Object[0]);
        this.onHideMethod = ReflectionHelper.loadMethod(clazz, "onHide", new Object[0]);
        this.onShowMethod = ReflectionHelper.loadMethod(clazz, "onShow", new Object[0]);
        this.onDestroyMethod = ReflectionHelper.loadMethod(clazz, "onDestroy", new Object[0]);
        this.onActivityResultintintIntentMethod = ReflectionHelper.loadMethod(clazz, "onActivityResult", Integer.TYPE, Integer.TYPE, Intent.class);
        this.onNewIntentIntentMethod = ReflectionHelper.loadMethod(clazz, "onNewIntent", Intent.class);
        this.saveStateBundleMethod = ReflectionHelper.loadMethod(clazz, "saveState", Bundle.class);
        this.restoreStateBundleMethod = ReflectionHelper.loadMethod(clazz, "restoreState", Bundle.class);
        this.getAPIVersionMethod = ReflectionHelper.loadMethod(clazz, "getAPIVersion", new Object[0]);
        this.getXWalkVersionMethod = ReflectionHelper.loadMethod(clazz, "getXWalkVersion", new Object[0]);
        this.setUIClientXWalkUIClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setUIClient", "org.xwalk.core.XWalkUIClient");
        this.setResourceClientXWalkResourceClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setResourceClient", "org.xwalk.core.XWalkResourceClient");
        this.setBackgroundColorintMethod = ReflectionHelper.loadMethod(clazz, "setBackgroundColor", Integer.TYPE);
        this.setLayerTypeintPaintMethod = ReflectionHelper.loadMethod(clazz, "setLayerType", Integer.TYPE, Paint.class);
        this.setNetworkAvailablebooleanMethod = ReflectionHelper.loadMethod(clazz, "setNetworkAvailable", Boolean.TYPE);
        this.getRemoteDebuggingUrlMethod = ReflectionHelper.loadMethod(clazz, "getRemoteDebuggingUrl", new Object[0]);
    }
}
