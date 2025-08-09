package org.xwalk.core.internal;

import java.lang.reflect.Method;

public class XWalkNavigationItemBridge extends XWalkNavigationItemInternal {
    private static final String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Method getOriginalUrlMethod;
    private Method getTitleMethod;
    private Method getUrlMethod;
    private XWalkNavigationItemInternal internal = null;
    private Object wrapper;

    public Object getWrapper() {
        return this.wrapper;
    }

    XWalkNavigationItemBridge(XWalkNavigationItemInternal internal2) {
        this.internal = internal2;
        this.wrapper = ReflectionHelper.createInstance("XWalkNavigationItemBridgeConstructor", this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public String getUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getUrlMethod, this.wrapper, new Object[0]);
    }

    public String getUrlSuper() {
        String ret;
        if (this.internal == null) {
            ret = super.getUrl();
        } else {
            ret = this.internal.getUrl();
        }
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public String getOriginalUrl() {
        return (String) ReflectionHelper.invokeMethod(this.getOriginalUrlMethod, this.wrapper, new Object[0]);
    }

    public String getOriginalUrlSuper() {
        String ret;
        if (this.internal == null) {
            ret = super.getOriginalUrl();
        } else {
            ret = this.internal.getOriginalUrl();
        }
        if (ret == null) {
            return null;
        }
        return ret;
    }

    public String getTitle() {
        return (String) ReflectionHelper.invokeMethod(this.getTitleMethod, this.wrapper, new Object[0]);
    }

    public String getTitleSuper() {
        String ret;
        if (this.internal == null) {
            ret = super.getTitle();
        } else {
            ret = this.internal.getTitle();
        }
        if (ret == null) {
            return null;
        }
        return ret;
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.wrapper.getClass();
        this.getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrl", new Object[0]);
        this.getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrl", new Object[0]);
        this.getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitle", new Object[0]);
    }

    static {
        ReflectionHelper.registerConstructor("XWalkNavigationItemBridgeConstructor", "org.xwalk.core.XWalkNavigationItem", Object.class);
    }
}
