package org.xwalk.core;

import java.lang.reflect.Method;

public class XWalkNavigationItem {
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkNavigationItemBridge";
    private Object bridge;
    private Method getOriginalUrlMethod;
    private Method getTitleMethod;
    private Method getUrlMethod;

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public XWalkNavigationItem(Object bridge2) {
        this.bridge = bridge2;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public String getUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getUrlMethod, this.bridge, new Object[0]);
    }

    public String getOriginalUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getOriginalUrlMethod, this.bridge, new Object[0]);
    }

    public String getTitle() {
        return (String) ReflectionHelper.invokeMethod(this.getTitleMethod, this.bridge, new Object[0]);
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.bridge.getClass();
        this.getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrlSuper", new Object[0]);
        this.getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrlSuper", new Object[0]);
        this.getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitleSuper", new Object[0]);
    }
}
