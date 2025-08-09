package org.xwalk.core.internal;

import android.net.http.SslError;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import java.lang.reflect.Method;

public class XWalkResourceClientBridge extends XWalkResourceClientInternal {
    private static final String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Method onLoadFinishedXWalkViewInternalStringMethod;
    private Method onLoadStartedXWalkViewInternalStringMethod;
    private Method onProgressChangedXWalkViewInternalintMethod;
    private Method onReceivedLoadErrorXWalkViewInternalintStringStringMethod;
    private Method onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod;
    private Method shouldInterceptLoadRequestXWalkViewInternalStringMethod;
    private Method shouldOverrideUrlLoadingXWalkViewInternalStringMethod;
    private Object wrapper;

    public Object getWrapper() {
        return this.wrapper;
    }

    public XWalkResourceClientBridge(XWalkViewBridge view, Object wrapper2) {
        super(view);
        this.wrapper = wrapper2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public void onLoadStarted(XWalkViewInternal view, String url) {
        if (view instanceof XWalkViewBridge) {
            onLoadStarted((XWalkViewBridge) view, url);
        } else {
            super.onLoadStarted(view, url);
        }
    }

    public void onLoadStarted(XWalkViewBridge view, String url) {
        ReflectionHelper.invokeMethod(this.onLoadStartedXWalkViewInternalStringMethod, this.wrapper, view.getWrapper(), url);
    }

    public void onLoadStartedSuper(XWalkViewBridge view, String url) {
        super.onLoadStarted(view, url);
    }

    public void onLoadFinished(XWalkViewInternal view, String url) {
        if (view instanceof XWalkViewBridge) {
            onLoadFinished((XWalkViewBridge) view, url);
        } else {
            super.onLoadFinished(view, url);
        }
    }

    public void onLoadFinished(XWalkViewBridge view, String url) {
        ReflectionHelper.invokeMethod(this.onLoadFinishedXWalkViewInternalStringMethod, this.wrapper, view.getWrapper(), url);
    }

    public void onLoadFinishedSuper(XWalkViewBridge view, String url) {
        super.onLoadFinished(view, url);
    }

    public void onProgressChanged(XWalkViewInternal view, int progressInPercent) {
        if (view instanceof XWalkViewBridge) {
            onProgressChanged((XWalkViewBridge) view, progressInPercent);
        } else {
            super.onProgressChanged(view, progressInPercent);
        }
    }

    public void onProgressChanged(XWalkViewBridge view, int progressInPercent) {
        ReflectionHelper.invokeMethod(this.onProgressChangedXWalkViewInternalintMethod, this.wrapper, view.getWrapper(), Integer.valueOf(progressInPercent));
    }

    public void onProgressChangedSuper(XWalkViewBridge view, int progressInPercent) {
        super.onProgressChanged(view, progressInPercent);
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewInternal view, String url) {
        if (view instanceof XWalkViewBridge) {
            return shouldInterceptLoadRequest((XWalkViewBridge) view, url);
        }
        return super.shouldInterceptLoadRequest(view, url);
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewBridge view, String url) {
        return (WebResourceResponse) ReflectionHelper.invokeMethod(this.shouldInterceptLoadRequestXWalkViewInternalStringMethod, this.wrapper, view.getWrapper(), url);
    }

    public WebResourceResponse shouldInterceptLoadRequestSuper(XWalkViewBridge view, String url) {
        WebResourceResponse ret = super.shouldInterceptLoadRequest(view, url);
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public void onReceivedLoadError(XWalkViewInternal view, int errorCode, String description, String failingUrl) {
        if (view instanceof XWalkViewBridge) {
            onReceivedLoadError((XWalkViewBridge) view, errorCode, description, failingUrl);
        } else {
            super.onReceivedLoadError(view, errorCode, description, failingUrl);
        }
    }

    public void onReceivedLoadError(XWalkViewBridge view, int errorCode, String description, String failingUrl) {
        ReflectionHelper.invokeMethod(this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod, this.wrapper, view.getWrapper(), Integer.valueOf(errorCode), description, failingUrl);
    }

    public void onReceivedLoadErrorSuper(XWalkViewBridge view, int errorCode, String description, String failingUrl) {
        super.onReceivedLoadError(view, errorCode, description, failingUrl);
    }

    public boolean shouldOverrideUrlLoading(XWalkViewInternal view, String url) {
        if (view instanceof XWalkViewBridge) {
            return shouldOverrideUrlLoading((XWalkViewBridge) view, url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    public boolean shouldOverrideUrlLoading(XWalkViewBridge view, String url) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod, this.wrapper, view.getWrapper(), url)).booleanValue();
    }

    public boolean shouldOverrideUrlLoadingSuper(XWalkViewBridge view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    public void onReceivedSslError(XWalkViewInternal view, ValueCallback<Boolean> callback, SslError error) {
        if (view instanceof XWalkViewBridge) {
            onReceivedSslError((XWalkViewBridge) view, callback, error);
        } else {
            super.onReceivedSslError(view, callback, error);
        }
    }

    public void onReceivedSslError(XWalkViewBridge view, ValueCallback<Boolean> callback, SslError error) {
        ReflectionHelper.invokeMethod(this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod, this.wrapper, view.getWrapper(), callback, error);
    }

    public void onReceivedSslErrorSuper(XWalkViewBridge view, ValueCallback<Boolean> callback, SslError error) {
        super.onReceivedSslError(view, callback, error);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.wrapper.getClass();
        this.onLoadStartedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadStarted", "org.xwalk.core.XWalkView", String.class);
        this.onLoadFinishedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadFinished", "org.xwalk.core.XWalkView", String.class);
        this.onProgressChangedXWalkViewInternalintMethod = ReflectionHelper.loadMethod(clazz, "onProgressChanged", "org.xwalk.core.XWalkView", Integer.TYPE);
        this.shouldInterceptLoadRequestXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldInterceptLoadRequest", "org.xwalk.core.XWalkView", String.class);
        this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod = ReflectionHelper.loadMethod(clazz, "onReceivedLoadError", "org.xwalk.core.XWalkView", Integer.TYPE, String.class, String.class);
        this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldOverrideUrlLoading", "org.xwalk.core.XWalkView", String.class);
        this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod = ReflectionHelper.loadMethod(clazz, "onReceivedSslError", "org.xwalk.core.XWalkView", ValueCallback.class, SslError.class);
    }
}
