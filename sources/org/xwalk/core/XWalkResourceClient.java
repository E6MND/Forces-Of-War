package org.xwalk.core;

import android.net.http.SslError;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import java.lang.reflect.Method;

public class XWalkResourceClient {
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkResourceClientBridge";
    public static final int ERROR_AUTHENTICATION = -4;
    public static final int ERROR_BAD_URL = -12;
    public static final int ERROR_CONNECT = -6;
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    public static final int ERROR_FILE = -13;
    public static final int ERROR_FILE_NOT_FOUND = -14;
    public static final int ERROR_HOST_LOOKUP = -2;
    public static final int ERROR_IO = -7;
    public static final int ERROR_OK = 0;
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    public static final int ERROR_REDIRECT_LOOP = -9;
    public static final int ERROR_TIMEOUT = -8;
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    public static final int ERROR_UNKNOWN = -1;
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;
    private Object bridge;
    private Method onLoadFinishedXWalkViewInternalStringMethod;
    private Method onLoadStartedXWalkViewInternalStringMethod;
    private Method onProgressChangedXWalkViewInternalintMethod;
    private Method onReceivedLoadErrorXWalkViewInternalintStringStringMethod;
    private Method onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod;
    private Method shouldInterceptLoadRequestXWalkViewInternalStringMethod;
    private Method shouldOverrideUrlLoadingXWalkViewInternalStringMethod;

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public XWalkResourceClient(XWalkView view) {
        this.bridge = ReflectionHelper.createInstance("XWalkResourceClientInternalXWalkViewInternalConstructor", view.getBridge(), this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public void onLoadStarted(XWalkView view, String url) {
        ReflectionHelper.invokeMethod(this.onLoadStartedXWalkViewInternalStringMethod, this.bridge, view.getBridge(), url);
    }

    public void onLoadFinished(XWalkView view, String url) {
        ReflectionHelper.invokeMethod(this.onLoadFinishedXWalkViewInternalStringMethod, this.bridge, view.getBridge(), url);
    }

    public void onProgressChanged(XWalkView view, int progressInPercent) {
        ReflectionHelper.invokeMethod(this.onProgressChangedXWalkViewInternalintMethod, this.bridge, view.getBridge(), Integer.valueOf(progressInPercent));
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkView view, String url) {
        return (WebResourceResponse) ReflectionHelper.invokeMethod(this.shouldInterceptLoadRequestXWalkViewInternalStringMethod, this.bridge, view.getBridge(), url);
    }

    public void onReceivedLoadError(XWalkView view, int errorCode, String description, String failingUrl) {
        ReflectionHelper.invokeMethod(this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod, this.bridge, view.getBridge(), Integer.valueOf(errorCode), description, failingUrl);
    }

    public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod, this.bridge, view.getBridge(), url)).booleanValue();
    }

    public void onReceivedSslError(XWalkView view, ValueCallback<Boolean> callback, SslError error) {
        ReflectionHelper.invokeMethod(this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod, this.bridge, view.getBridge(), callback, error);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.bridge.getClass();
        this.onLoadStartedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadStartedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        this.onLoadFinishedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadFinishedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        this.onProgressChangedXWalkViewInternalintMethod = ReflectionHelper.loadMethod(clazz, "onProgressChangedSuper", "org.xwalk.core.internal.XWalkViewBridge", Integer.TYPE);
        this.shouldInterceptLoadRequestXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldInterceptLoadRequestSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod = ReflectionHelper.loadMethod(clazz, "onReceivedLoadErrorSuper", "org.xwalk.core.internal.XWalkViewBridge", Integer.TYPE, String.class, String.class);
        this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldOverrideUrlLoadingSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod = ReflectionHelper.loadMethod(clazz, "onReceivedSslErrorSuper", "org.xwalk.core.internal.XWalkViewBridge", ValueCallback.class, SslError.class);
    }

    static {
        ReflectionHelper.registerConstructor("XWalkResourceClientInternalXWalkViewInternalConstructor", BRIDGE_CLASS, "org.xwalk.core.internal.XWalkViewBridge", Object.class);
    }
}
