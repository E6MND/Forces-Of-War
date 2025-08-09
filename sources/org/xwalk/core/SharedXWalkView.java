package org.xwalk.core;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

public class SharedXWalkView extends XWalkView {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static boolean initialized = false;

    static {
        boolean z;
        if (!SharedXWalkView.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    public SharedXWalkView(XWalkActivity context, AttributeSet attrs, SharedXWalkExceptionHandler handler) {
        super((Context) verifyActivity(context), attrs);
    }

    public SharedXWalkView(Context context, XWalkActivity activity) {
        super(context, verifyActivity(activity));
    }

    private static Activity verifyActivity(XWalkActivity context) {
        if (!initialized) {
            initialize(context, (SharedXWalkExceptionHandler) null);
        }
        return context;
    }

    public static void initialize(Context context, SharedXWalkExceptionHandler handler) {
        if (!initialized) {
            if ($assertionsDisabled || (context.getApplicationContext() instanceof XWalkApplication)) {
                ReflectionHelper.allowCrossPackage();
                if (handler != null) {
                    ReflectionHelper.setExceptionHandler(handler);
                }
                initialized = true;
                return;
            }
            throw new AssertionError();
        }
    }

    public static boolean containsLibrary() {
        return !ReflectionHelper.shouldUseLibrary();
    }

    public static boolean isUsingLibrary() {
        return ReflectionHelper.isUsingLibrary();
    }
}
