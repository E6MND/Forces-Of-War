package org.xwalk.core.internal;

import java.lang.reflect.Method;
import org.xwalk.core.internal.XWalkNavigationHistoryInternal;

public class XWalkNavigationHistoryBridge extends XWalkNavigationHistoryInternal {
    private static final String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Method canGoBackMethod;
    private Method canGoForwardMethod;
    private Method clearMethod;
    private Class<?> enumDirectionClass;
    private Method enumDirectionClassValueOfMethod;
    private Method getCurrentIndexMethod;
    private Method getCurrentItemMethod;
    private Method getItemAtintMethod;
    private Method hasItemAtintMethod;
    private XWalkNavigationHistoryInternal internal = null;
    private Method navigateDirectionInternalintMethod;
    private Method sizeMethod;
    private Object wrapper;

    public Object getWrapper() {
        return this.wrapper;
    }

    XWalkNavigationHistoryBridge(XWalkNavigationHistoryInternal internal2) {
        this.internal = internal2;
        this.wrapper = ReflectionHelper.createInstance("XWalkNavigationHistoryBridgeConstructor", this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Object ConvertDirectionInternal(XWalkNavigationHistoryInternal.DirectionInternal type) {
        return ReflectionHelper.invokeMethod(this.enumDirectionClassValueOfMethod, (Object) null, type.toString());
    }

    public int size() {
        return ((Integer) ReflectionHelper.invokeMethod(this.sizeMethod, this.wrapper, new Object[0])).intValue();
    }

    public int sizeSuper() {
        if (this.internal == null) {
            return super.size();
        }
        return this.internal.size();
    }

    public boolean hasItemAt(int index) {
        return ((Boolean) ReflectionHelper.invokeMethod(this.hasItemAtintMethod, this.wrapper, Integer.valueOf(index))).booleanValue();
    }

    public boolean hasItemAtSuper(int index) {
        if (this.internal == null) {
            return super.hasItemAt(index);
        }
        return this.internal.hasItemAt(index);
    }

    public XWalkNavigationItemInternal getItemAt(int index) {
        return (XWalkNavigationItemBridge) ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(this.getItemAtintMethod, this.wrapper, Integer.valueOf(index)));
    }

    public XWalkNavigationItemBridge getItemAtSuper(int index) {
        XWalkNavigationItemInternal ret;
        if (this.internal == null) {
            ret = super.getItemAt(index);
        } else {
            ret = this.internal.getItemAt(index);
        }
        if (ret == null) {
            return null;
        }
        return ret instanceof XWalkNavigationItemBridge ? (XWalkNavigationItemBridge) ret : new XWalkNavigationItemBridge(ret);
    }

    public XWalkNavigationItemInternal getCurrentItem() {
        return (XWalkNavigationItemBridge) ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(this.getCurrentItemMethod, this.wrapper, new Object[0]));
    }

    public XWalkNavigationItemBridge getCurrentItemSuper() {
        XWalkNavigationItemInternal ret;
        if (this.internal == null) {
            ret = super.getCurrentItem();
        } else {
            ret = this.internal.getCurrentItem();
        }
        if (ret == null) {
            return null;
        }
        return ret instanceof XWalkNavigationItemBridge ? (XWalkNavigationItemBridge) ret : new XWalkNavigationItemBridge(ret);
    }

    public boolean canGoBack() {
        return ((Boolean) ReflectionHelper.invokeMethod(this.canGoBackMethod, this.wrapper, new Object[0])).booleanValue();
    }

    public boolean canGoBackSuper() {
        if (this.internal == null) {
            return super.canGoBack();
        }
        return this.internal.canGoBack();
    }

    public boolean canGoForward() {
        return ((Boolean) ReflectionHelper.invokeMethod(this.canGoForwardMethod, this.wrapper, new Object[0])).booleanValue();
    }

    public boolean canGoForwardSuper() {
        if (this.internal == null) {
            return super.canGoForward();
        }
        return this.internal.canGoForward();
    }

    public void navigate(XWalkNavigationHistoryInternal.DirectionInternal direction, int steps) {
        ReflectionHelper.invokeMethod(this.navigateDirectionInternalintMethod, this.wrapper, ConvertDirectionInternal(direction), Integer.valueOf(steps));
    }

    public void navigateSuper(XWalkNavigationHistoryInternal.DirectionInternal direction, int steps) {
        if (this.internal == null) {
            super.navigate(direction, steps);
        } else {
            this.internal.navigate(direction, steps);
        }
    }

    public int getCurrentIndex() {
        return ((Integer) ReflectionHelper.invokeMethod(this.getCurrentIndexMethod, this.wrapper, new Object[0])).intValue();
    }

    public int getCurrentIndexSuper() {
        if (this.internal == null) {
            return super.getCurrentIndex();
        }
        return this.internal.getCurrentIndex();
    }

    public void clear() {
        ReflectionHelper.invokeMethod(this.clearMethod, this.wrapper, new Object[0]);
    }

    public void clearSuper() {
        if (this.internal == null) {
            super.clear();
        } else {
            this.internal.clear();
        }
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.wrapper.getClass();
        this.enumDirectionClass = clazz.getClassLoader().loadClass("org.xwalk.core.XWalkNavigationHistory$Direction");
        this.enumDirectionClassValueOfMethod = this.enumDirectionClass.getMethod("valueOf", new Class[]{String.class});
        this.sizeMethod = ReflectionHelper.loadMethod(clazz, "size", new Object[0]);
        this.hasItemAtintMethod = ReflectionHelper.loadMethod(clazz, "hasItemAt", Integer.TYPE);
        this.getItemAtintMethod = ReflectionHelper.loadMethod(clazz, "getItemAt", Integer.TYPE);
        this.getCurrentItemMethod = ReflectionHelper.loadMethod(clazz, "getCurrentItem", new Object[0]);
        this.canGoBackMethod = ReflectionHelper.loadMethod(clazz, "canGoBack", new Object[0]);
        this.canGoForwardMethod = ReflectionHelper.loadMethod(clazz, "canGoForward", new Object[0]);
        this.navigateDirectionInternalintMethod = ReflectionHelper.loadMethod(clazz, "navigate", this.enumDirectionClass, Integer.TYPE);
        this.getCurrentIndexMethod = ReflectionHelper.loadMethod(clazz, "getCurrentIndex", new Object[0]);
        this.clearMethod = ReflectionHelper.loadMethod(clazz, "clear", new Object[0]);
    }

    static {
        ReflectionHelper.registerConstructor("XWalkNavigationHistoryBridgeConstructor", "org.xwalk.core.XWalkNavigationHistory", Object.class);
    }
}
