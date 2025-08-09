package org.chromium.content.browser.accessibility;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.json.JSONException;
import org.json.JSONObject;

class JellyBeanAccessibilityInjector extends AccessibilityInjector {
    private static final String ACCESSIBILITY_ANDROIDVOX_TEMPLATE = "cvox.AndroidVox.performAction('%1s')";
    private static final String ALIAS_TRAVERSAL_JS_INTERFACE = "accessibilityTraversal";
    private JSONObject mAccessibilityJSONObject;
    private CallbackHandler mCallback;

    protected JellyBeanAccessibilityInjector(ContentViewCore view) {
        super(view);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        info.setMovementGranularities(31);
        info.addAction(256);
        info.addAction(512);
        info.addAction(1024);
        info.addAction(2048);
        info.addAction(16);
        info.setClickable(true);
    }

    public boolean supportsAccessibilityAction(int action) {
        if (action == 256 || action == 512 || action == 1024 || action == 2048 || action == 16) {
            return true;
        }
        return false;
    }

    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (!accessibilityIsAvailable() || !this.mContentViewCore.isAlive() || !this.mInjectedScriptEnabled || !this.mScriptInjected) {
            return false;
        }
        boolean actionSuccessful = sendActionToAndroidVox(action, arguments);
        if (!actionSuccessful) {
            return actionSuccessful;
        }
        this.mContentViewCore.getWebContents().showImeIfNeeded();
        return actionSuccessful;
    }

    /* access modifiers changed from: protected */
    public void addAccessibilityApis() {
        super.addAccessibilityApis();
        if (this.mContentViewCore.getContext() != null && this.mCallback == null) {
            this.mCallback = new CallbackHandler(ALIAS_TRAVERSAL_JS_INTERFACE);
            this.mContentViewCore.addJavascriptInterface(this.mCallback, ALIAS_TRAVERSAL_JS_INTERFACE);
        }
    }

    /* access modifiers changed from: protected */
    public void removeAccessibilityApis() {
        super.removeAccessibilityApis();
        if (this.mCallback != null) {
            this.mContentViewCore.removeJavascriptInterface(ALIAS_TRAVERSAL_JS_INTERFACE);
            this.mCallback = null;
        }
    }

    private boolean sendActionToAndroidVox(int action, Bundle arguments) {
        if (this.mCallback == null) {
            return false;
        }
        if (this.mAccessibilityJSONObject == null) {
            this.mAccessibilityJSONObject = new JSONObject();
        } else {
            Iterator<String> keys = this.mAccessibilityJSONObject.keys();
            while (keys.hasNext()) {
                keys.next();
                keys.remove();
            }
        }
        try {
            this.mAccessibilityJSONObject.accumulate("action", Integer.valueOf(action));
            if (arguments != null) {
                if (action == 256 || action == 512) {
                    this.mAccessibilityJSONObject.accumulate("granularity", Integer.valueOf(arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT)));
                } else if (action == 1024 || action == 2048) {
                    this.mAccessibilityJSONObject.accumulate("element", arguments.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_HTML_ELEMENT_STRING));
                }
            }
            String jsonString = this.mAccessibilityJSONObject.toString();
            return this.mCallback.performAction(this.mContentViewCore, String.format(Locale.US, ACCESSIBILITY_ANDROIDVOX_TEMPLATE, new Object[]{jsonString}));
        } catch (JSONException e) {
            return false;
        }
    }

    private static class CallbackHandler {
        private static final String JAVASCRIPT_ACTION_TEMPLATE = "(function() {  retVal = false;  try {    retVal = %s;  } catch (e) {    retVal = false;  }  %s.onResult(%d, retVal);})()";
        private static final long RESULT_TIMEOUT = 5000;
        private final String mInterfaceName;
        private boolean mResult;
        private long mResultId;
        private final AtomicInteger mResultIdCounter;
        private final Object mResultLock;

        private CallbackHandler(String interfaceName) {
            this.mResultIdCounter = new AtomicInteger();
            this.mResultLock = new Object();
            this.mResult = false;
            this.mResultId = -1;
            this.mInterfaceName = interfaceName;
        }

        /* access modifiers changed from: private */
        public boolean performAction(ContentViewCore contentView, String code) {
            int resultId = this.mResultIdCounter.getAndIncrement();
            contentView.getWebContents().evaluateJavaScript(String.format(Locale.US, JAVASCRIPT_ACTION_TEMPLATE, new Object[]{code, this.mInterfaceName, Integer.valueOf(resultId)}), (JavaScriptCallback) null);
            return getResultAndClear(resultId);
        }

        private boolean getResultAndClear(int resultId) {
            boolean result;
            synchronized (this.mResultLock) {
                result = waitForResultTimedLocked(resultId) ? this.mResult : false;
                clearResultLocked();
            }
            return result;
        }

        private void clearResultLocked() {
            this.mResultId = -1;
            this.mResult = false;
        }

        private boolean waitForResultTimedLocked(int resultId) {
            long startTimeMillis = SystemClock.uptimeMillis();
            while (this.mResultId != ((long) resultId)) {
                try {
                    if (this.mResultId > ((long) resultId)) {
                        return false;
                    }
                    long waitTimeMillis = RESULT_TIMEOUT - (SystemClock.uptimeMillis() - startTimeMillis);
                    if (waitTimeMillis <= 0) {
                        return false;
                    }
                    this.mResultLock.wait(waitTimeMillis);
                } catch (InterruptedException e) {
                }
            }
            return true;
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        @org.chromium.content.browser.JavascriptInterface
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onResult(java.lang.String r9, java.lang.String r10) {
            /*
                r8 = this;
                long r2 = java.lang.Long.parseLong(r9)     // Catch:{ NumberFormatException -> 0x001c }
                java.lang.Object r4 = r8.mResultLock
                monitor-enter(r4)
                long r6 = r8.mResultId     // Catch:{ all -> 0x001e }
                int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                if (r1 <= 0) goto L_0x0015
                boolean r1 = java.lang.Boolean.parseBoolean(r10)     // Catch:{ all -> 0x001e }
                r8.mResult = r1     // Catch:{ all -> 0x001e }
                r8.mResultId = r2     // Catch:{ all -> 0x001e }
            L_0x0015:
                java.lang.Object r1 = r8.mResultLock     // Catch:{ all -> 0x001e }
                r1.notifyAll()     // Catch:{ all -> 0x001e }
                monitor-exit(r4)     // Catch:{ all -> 0x001e }
            L_0x001b:
                return
            L_0x001c:
                r0 = move-exception
                goto L_0x001b
            L_0x001e:
                r1 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x001e }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.accessibility.JellyBeanAccessibilityInjector.CallbackHandler.onResult(java.lang.String, java.lang.String):void");
        }
    }
}
