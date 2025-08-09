package org.xwalk.core;

import java.lang.reflect.Method;

public abstract class XWalkExtension {
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkExtensionBridge";
    private Object bridge;
    private Method broadcastMessageStringMethod;
    private Method postMessageintStringMethod;

    public abstract void onMessage(int i, String str);

    public abstract String onSyncMessage(int i, String str);

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public XWalkExtension(String name, String jsApi) {
        this.bridge = ReflectionHelper.createInstance("XWalkExtensionInternalStringStringConstructor", name, jsApi, this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public XWalkExtension(String name, String jsApi, String[] entryPoints) {
        this.bridge = ReflectionHelper.createInstance("XWalkExtensionInternalStringStringString[]Constructor", name, jsApi, entryPoints, this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public void postMessage(int instanceID, String message) {
        ReflectionHelper.invokeMethod(this.postMessageintStringMethod, this.bridge, Integer.valueOf(instanceID), message);
    }

    public void broadcastMessage(String message) {
        ReflectionHelper.invokeMethod(this.broadcastMessageStringMethod, this.bridge, message);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.bridge.getClass();
        this.postMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "postMessageSuper", Integer.TYPE, String.class);
        this.broadcastMessageStringMethod = ReflectionHelper.loadMethod(clazz, "broadcastMessageSuper", String.class);
    }

    static {
        ReflectionHelper.registerConstructor("XWalkExtensionInternalStringStringConstructor", BRIDGE_CLASS, String.class, String.class, Object.class);
        ReflectionHelper.registerConstructor("XWalkExtensionInternalStringStringString[]Constructor", BRIDGE_CLASS, String.class, String.class, String[].class, Object.class);
    }
}
