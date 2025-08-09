package org.xwalk.core;

import java.lang.reflect.Method;

public class XWalkJavascriptResultHandler implements XWalkJavascriptResult {
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkJavascriptResultHandlerBridge";
    private Object bridge;
    private Method cancelMethod;
    private Method confirmMethod;
    private Method confirmWithResultStringMethod;

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public XWalkJavascriptResultHandler(Object bridge2) {
        this.bridge = bridge2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public void confirm() {
        ReflectionHelper.invokeMethod(this.confirmMethod, this.bridge, new Object[0]);
    }

    public void confirmWithResult(String promptResult) {
        ReflectionHelper.invokeMethod(this.confirmWithResultStringMethod, this.bridge, promptResult);
    }

    public void cancel() {
        ReflectionHelper.invokeMethod(this.cancelMethod, this.bridge, new Object[0]);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.bridge.getClass();
        this.confirmMethod = ReflectionHelper.loadMethod(clazz, "confirmSuper", new Object[0]);
        this.confirmWithResultStringMethod = ReflectionHelper.loadMethod(clazz, "confirmWithResultSuper", String.class);
        this.cancelMethod = ReflectionHelper.loadMethod(clazz, "cancelSuper", new Object[0]);
    }
}
