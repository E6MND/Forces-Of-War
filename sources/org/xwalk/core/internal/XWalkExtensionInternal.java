package org.xwalk.core.internal;

import org.xwalk.core.internal.extensions.XWalkExtensionAndroid;

@XWalkAPI
public abstract class XWalkExtensionInternal extends XWalkExtensionAndroid {
    @XWalkAPI
    public abstract void onMessage(int i, String str);

    @XWalkAPI
    public abstract String onSyncMessage(int i, String str);

    @XWalkAPI
    public XWalkExtensionInternal(String name, String jsApi) {
        super(name, jsApi);
    }

    @XWalkAPI
    public XWalkExtensionInternal(String name, String jsApi, String[] entryPoints) {
        super(name, jsApi, entryPoints);
    }

    /* access modifiers changed from: protected */
    public void destroyExtension() {
        super.destroyExtension();
    }

    @XWalkAPI
    public void postMessage(int instanceID, String message) {
        super.postMessage(instanceID, message);
    }

    @XWalkAPI
    public void broadcastMessage(String message) {
        super.broadcastMessage(message);
    }
}
