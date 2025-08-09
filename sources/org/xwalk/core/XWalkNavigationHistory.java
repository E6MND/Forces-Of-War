package org.xwalk.core;

import java.lang.reflect.Method;

public class XWalkNavigationHistory {
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkNavigationHistoryBridge";
    private Object bridge;
    private Method canGoBackMethod;
    private Method canGoForwardMethod;
    private Method clearMethod;
    private Class<?> enumDirectionClass;
    private Method enumDirectionClassValueOfMethod;
    private Method getCurrentIndexMethod;
    private Method getCurrentItemMethod;
    private Method getItemAtintMethod;
    private Method hasItemAtintMethod;
    private Method navigateDirectionInternalintMethod;
    private Method sizeMethod;

    public enum Direction {
        BACKWARD,
        FORWARD
    }

    private Object ConvertDirection(Direction type) {
        return ReflectionHelper.invokeMethod(this.enumDirectionClassValueOfMethod, (Object) null, type.toString());
    }

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public XWalkNavigationHistory(Object bridge2) {
        this.bridge = bridge2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public int size() {
        return ((Integer) ReflectionHelper.invokeMethod(this.sizeMethod, this.bridge, new Object[0])).intValue();
    }

    public boolean hasItemAt(int index) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.hasItemAtintMethod, this.bridge, Integer.valueOf(index))).booleanValue();
    }

    public XWalkNavigationItem getItemAt(int index) {
        return (XWalkNavigationItem) ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(this.getItemAtintMethod, this.bridge, Integer.valueOf(index)));
    }

    public XWalkNavigationItem getCurrentItem() {
        return (XWalkNavigationItem) ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(this.getCurrentItemMethod, this.bridge, new Object[0]));
    }

    public boolean canGoBack() {
        return ((Boolean) ReflectionHelper.invokeMethod(this.canGoBackMethod, this.bridge, new Object[0])).booleanValue();
    }

    public boolean canGoForward() {
        return ((Boolean) ReflectionHelper.invokeMethod(this.canGoForwardMethod, this.bridge, new Object[0])).booleanValue();
    }

    public void navigate(Direction direction, int steps) {
        ReflectionHelper.invokeMethod(this.navigateDirectionInternalintMethod, this.bridge, ConvertDirection(direction), Integer.valueOf(steps));
    }

    public int getCurrentIndex() {
        return ((Integer) ReflectionHelper.invokeMethod(this.getCurrentIndexMethod, this.bridge, new Object[0])).intValue();
    }

    public void clear() {
        ReflectionHelper.invokeMethod(this.clearMethod, this.bridge, new Object[0]);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.bridge.getClass();
        this.enumDirectionClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkNavigationHistoryInternal$DirectionInternal");
        this.enumDirectionClassValueOfMethod = this.enumDirectionClass.getMethod("valueOf", new Class[]{String.class});
        this.sizeMethod = ReflectionHelper.loadMethod(clazz, "sizeSuper", new Object[0]);
        this.hasItemAtintMethod = ReflectionHelper.loadMethod(clazz, "hasItemAtSuper", Integer.TYPE);
        this.getItemAtintMethod = ReflectionHelper.loadMethod(clazz, "getItemAtSuper", Integer.TYPE);
        this.getCurrentItemMethod = ReflectionHelper.loadMethod(clazz, "getCurrentItemSuper", new Object[0]);
        this.canGoBackMethod = ReflectionHelper.loadMethod(clazz, "canGoBackSuper", new Object[0]);
        this.canGoForwardMethod = ReflectionHelper.loadMethod(clazz, "canGoForwardSuper", new Object[0]);
        this.navigateDirectionInternalintMethod = ReflectionHelper.loadMethod(clazz, "navigateSuper", this.enumDirectionClass, Integer.TYPE);
        this.getCurrentIndexMethod = ReflectionHelper.loadMethod(clazz, "getCurrentIndexSuper", new Object[0]);
        this.clearMethod = ReflectionHelper.loadMethod(clazz, "clearSuper", new Object[0]);
    }
}
