package org.xwalk.core.internal;

import java.lang.reflect.Method;

public class XWalkExtensionBridge extends XWalkExtensionInternal {
    private static final String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Method broadcastMessageStringMethod;
    private Method onMessageintStringMethod;
    private Method onSyncMessageintStringMethod;
    private Method postMessageintStringMethod;
    private Object wrapper;

    public Object getWrapper() {
        return this.wrapper;
    }

    public XWalkExtensionBridge(String name, String jsApi, Object wrapper2) {
        super(name, jsApi);
        this.wrapper = wrapper2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public XWalkExtensionBridge(String name, String jsApi, String[] entryPoints, Object wrapper2) {
        super(name, jsApi, entryPoints);
        this.wrapper = wrapper2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public void postMessage(int instanceID, String message) {
        ReflectionHelper.invokeMethod(this.postMessageintStringMethod, this.wrapper, Integer.valueOf(instanceID), message);
    }

    public void postMessageSuper(int instanceID, String message) {
        super.postMessage(instanceID, message);
    }

    public void broadcastMessage(String message) {
        ReflectionHelper.invokeMethod(this.broadcastMessageStringMethod, this.wrapper, message);
    }

    public void broadcastMessageSuper(String message) {
        super.broadcastMessage(message);
    }

    public void onMessage(int instanceID, String message) {
        ReflectionHelper.invokeMethod(this.onMessageintStringMethod, this.wrapper, Integer.valueOf(instanceID), message);
    }

    public String onSyncMessage(int instanceID, String message) {
        return (String) ReflectionHelper.invokeMethod(this.onSyncMessageintStringMethod, this.wrapper, Integer.valueOf(instanceID), message);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.wrapper.getClass();
        this.postMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "postMessage", Integer.TYPE, String.class);
        this.broadcastMessageStringMethod = ReflectionHelper.loadMethod(clazz, "broadcastMessage", String.class);
        this.onMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "onMessage", Integer.TYPE, String.class);
        this.onSyncMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "onSyncMessage", Integer.TYPE, String.class);
    }
}
