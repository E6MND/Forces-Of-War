package org.xwalk.core.internal;

import org.chromium.base.JNINamespace;

@JNINamespace("xwalk")
public final class XWalkCookieManager {
    private native boolean nativeAcceptCookie();

    private native boolean nativeAllowFileSchemeCookies();

    private native void nativeFlushCookieStore();

    private native String nativeGetCookie(String str);

    private native boolean nativeHasCookies();

    private native void nativeRemoveAllCookie();

    private native void nativeRemoveExpiredCookie();

    private native void nativeRemoveSessionCookie();

    private native void nativeSetAcceptCookie(boolean z);

    private native void nativeSetAcceptFileSchemeCookies(boolean z);

    private native void nativeSetCookie(String str, String str2);

    public void setAcceptCookie(boolean accept) {
        nativeSetAcceptCookie(accept);
    }

    public boolean acceptCookie() {
        return nativeAcceptCookie();
    }

    public void setCookie(String url, String value) {
        nativeSetCookie(url, value);
    }

    public String getCookie(String url) {
        String cookie = nativeGetCookie(url.toString());
        if (cookie == null || cookie.trim().isEmpty()) {
            return null;
        }
        return cookie;
    }

    public void removeSessionCookie() {
        nativeRemoveSessionCookie();
    }

    public void removeAllCookie() {
        nativeRemoveAllCookie();
    }

    public boolean hasCookies() {
        return nativeHasCookies();
    }

    public void removeExpiredCookie() {
        nativeRemoveExpiredCookie();
    }

    public void flushCookieStore() {
        nativeFlushCookieStore();
    }

    public boolean allowFileSchemeCookies() {
        return nativeAllowFileSchemeCookies();
    }

    public void setAcceptFileSchemeCookies(boolean accept) {
        nativeSetAcceptFileSchemeCookies(accept);
    }
}
