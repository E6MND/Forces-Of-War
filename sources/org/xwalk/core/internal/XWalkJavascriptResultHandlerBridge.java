package org.xwalk.core.internal;

import com.facebook.widget.FacebookDialog;
import java.lang.reflect.Method;

public class XWalkJavascriptResultHandlerBridge extends XWalkJavascriptResultHandlerInternal {
    private static final String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Method cancelMethod;
    private Method confirmMethod;
    private Method confirmWithResultStringMethod;
    private XWalkJavascriptResultHandlerInternal internal = null;
    private Object wrapper;

    public Object getWrapper() {
        return this.wrapper;
    }

    XWalkJavascriptResultHandlerBridge(XWalkJavascriptResultHandlerInternal internal2) {
        this.internal = internal2;
        this.wrapper = ReflectionHelper.createInstance("XWalkJavascriptResultHandlerBridgeConstructor", this);
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public void confirm() {
        ReflectionHelper.invokeMethod(this.confirmMethod, this.wrapper, new Object[0]);
    }

    public void confirmSuper() {
        if (this.internal == null) {
            super.confirm();
        } else {
            this.internal.confirm();
        }
    }

    public void confirmWithResult(String promptResult) {
        ReflectionHelper.invokeMethod(this.confirmWithResultStringMethod, this.wrapper, promptResult);
    }

    public void confirmWithResultSuper(String promptResult) {
        if (this.internal == null) {
            super.confirmWithResult(promptResult);
        } else {
            this.internal.confirmWithResult(promptResult);
        }
    }

    public void cancel() {
        ReflectionHelper.invokeMethod(this.cancelMethod, this.wrapper, new Object[0]);
    }

    public void cancelSuper() {
        if (this.internal == null) {
            super.cancel();
        } else {
            this.internal.cancel();
        }
    }

    private void reflectionInit() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = this.wrapper.getClass();
        this.confirmMethod = ReflectionHelper.loadMethod(clazz, "confirm", new Object[0]);
        this.confirmWithResultStringMethod = ReflectionHelper.loadMethod(clazz, "confirmWithResult", String.class);
        this.cancelMethod = ReflectionHelper.loadMethod(clazz, FacebookDialog.COMPLETION_GESTURE_CANCEL, new Object[0]);
    }

    static {
        ReflectionHelper.registerConstructor("XWalkJavascriptResultHandlerBridgeConstructor", "org.xwalk.core.XWalkJavascriptResultHandler", Object.class);
    }
}
