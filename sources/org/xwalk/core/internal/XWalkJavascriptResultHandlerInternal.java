package org.xwalk.core.internal;

import org.chromium.base.ThreadUtils;

@XWalkAPI(createInternally = true, impl = XWalkJavascriptResultInternal.class)
public class XWalkJavascriptResultHandlerInternal implements XWalkJavascriptResultInternal {
    /* access modifiers changed from: private */
    public XWalkContentsClientBridge mBridge;
    /* access modifiers changed from: private */
    public final int mId;

    XWalkJavascriptResultHandlerInternal(XWalkContentsClientBridge bridge, int id) {
        this.mBridge = bridge;
        this.mId = id;
    }

    XWalkJavascriptResultHandlerInternal() {
        this.mBridge = null;
        this.mId = -1;
    }

    @XWalkAPI
    public void confirm() {
        confirmWithResult((String) null);
    }

    @XWalkAPI
    public void confirmWithResult(final String promptResult) {
        ThreadUtils.runOnUiThread((Runnable) new Runnable() {
            public void run() {
                if (XWalkJavascriptResultHandlerInternal.this.mBridge != null) {
                    XWalkJavascriptResultHandlerInternal.this.mBridge.confirmJsResult(XWalkJavascriptResultHandlerInternal.this.mId, promptResult);
                }
                XWalkContentsClientBridge unused = XWalkJavascriptResultHandlerInternal.this.mBridge = null;
            }
        });
    }

    @XWalkAPI
    public void cancel() {
        ThreadUtils.runOnUiThread((Runnable) new Runnable() {
            public void run() {
                if (XWalkJavascriptResultHandlerInternal.this.mBridge != null) {
                    XWalkJavascriptResultHandlerInternal.this.mBridge.cancelJsResult(XWalkJavascriptResultHandlerInternal.this.mId);
                }
                XWalkContentsClientBridge unused = XWalkJavascriptResultHandlerInternal.this.mBridge = null;
            }
        });
    }
}
