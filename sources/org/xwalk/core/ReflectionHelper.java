package org.xwalk.core;

import android.content.Context;
import android.content.pm.PackageManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionHelper {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String INTERNAL_PACKAGE = "org.xwalk.core.internal";
    private static final String LIBRARY_APK_PACKAGE = "org.xwalk.core";
    private static boolean sAllowCrossPackage = false;
    private static boolean sAlreadyUsingLibrary = false;
    private static Context sBridgeContext = null;
    private static ClassLoader sBridgeOrWrapperLoader = null;
    private static Map<Class<?>, Method> sBridgeWrapperMap = new HashMap();
    private static Map<String, ConstructorHelper> sConstructorHelperMap = new HashMap();
    private static Map<String, Constructor<?>> sConstructorMap = new HashMap();
    private static SharedXWalkExceptionHandler sExceptionHandler = null;
    private static boolean sIsWrapper = isWrapper();

    static {
        boolean z;
        if (!ReflectionHelper.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    static class ConstructorHelper {
        private String fullClassName;
        private Object[] paramTypes;

        /* access modifiers changed from: package-private */
        public Constructor<?> loadConstructor() {
            Class<?> clazz = ReflectionHelper.loadClass(this.fullClassName);
            if (clazz == null) {
                return null;
            }
            Class<?>[] params = new Class[this.paramTypes.length];
            for (int i = 0; i < this.paramTypes.length; i++) {
                Object type = this.paramTypes[i];
                if (type instanceof Class) {
                    params[i] = (Class) type;
                } else if (type instanceof String) {
                    params[i] = ReflectionHelper.loadClass((String) type);
                }
            }
            try {
                return clazz.getConstructor(params);
            } catch (NoSuchMethodException e) {
                ReflectionHelper.handleException((Exception) e);
                return null;
            }
        }

        ConstructorHelper(String className, Object... paramTypes2) {
            this.fullClassName = className;
            this.paramTypes = paramTypes2;
        }
    }

    static void setExceptionHandler(SharedXWalkExceptionHandler handler) {
        sExceptionHandler = handler;
    }

    static boolean isUsingLibrary() {
        return sAlreadyUsingLibrary;
    }

    static boolean shouldUseLibrary() {
        if (sAlreadyUsingLibrary) {
            return true;
        }
        if ($assertionsDisabled || isWrapper()) {
            try {
                Class<?> delegateClass = ReflectionHelper.class.getClassLoader().loadClass("org.xwalk.core.internal.XWalkViewDelegate");
                if (delegateClass == null) {
                    return true;
                }
                try {
                    delegateClass.getDeclaredMethod("loadXWalkLibrary", new Class[]{Context.class}).invoke((Object) null, new Object[]{null});
                    return false;
                } catch (NoSuchMethodException e) {
                    return true;
                } catch (IllegalArgumentException e2) {
                    return true;
                } catch (IllegalAccessException e3) {
                    return true;
                } catch (InvocationTargetException e4) {
                    return true;
                } catch (UnsatisfiedLinkError e5) {
                    return true;
                }
            } catch (ClassNotFoundException e6) {
                return true;
            }
        } else {
            throw new AssertionError();
        }
    }

    public static Context getBridgeContext() {
        return sBridgeContext;
    }

    public static void allowCrossPackage() {
        sAllowCrossPackage = true;
    }

    public static void init() {
        if (!$assertionsDisabled && !isWrapper()) {
            throw new AssertionError();
        } else if (shouldUseLibrary()) {
            if (!sAllowCrossPackage) {
                handleException("Use SharedXWalkView if you want to support shared mode");
            }
            XWalkApplication app = XWalkApplication.getApplication();
            if (app == null) {
                handleException("Shared mode requires XWalkApplication");
                return;
            }
            try {
                sBridgeContext = app.createPackageContext(LIBRARY_APK_PACKAGE, 3);
                sAlreadyUsingLibrary = true;
            } catch (PackageManager.NameNotFoundException e) {
                handleException((Exception) e);
            }
            if (sBridgeContext != null) {
                app.addResource(sBridgeContext.getResources());
                initClassLoader(sBridgeContext.getClassLoader(), sBridgeContext);
            }
        } else {
            initClassLoader(ReflectionHelper.class.getClassLoader(), (Context) null);
        }
    }

    public static void initClassLoader(ClassLoader loader, Context bridgeContext) {
        sBridgeOrWrapperLoader = loader;
        sBridgeContext = bridgeContext;
        sBridgeWrapperMap.clear();
        sConstructorMap.clear();
        try {
            for (String name : sConstructorHelperMap.keySet()) {
                ConstructorHelper helper = sConstructorHelperMap.get(name);
                if (helper != null) {
                    sConstructorMap.put(name, helper.loadConstructor());
                }
            }
            if (sIsWrapper) {
                sBridgeOrWrapperLoader.loadClass("org.xwalk.core.internal.ReflectionHelper").getMethod("initClassLoader", new Class[]{ClassLoader.class, Context.class}).invoke((Object) null, new Object[]{ReflectionHelper.class.getClassLoader(), sBridgeContext});
                return;
            }
            Class<?> javascriptInterface = sBridgeOrWrapperLoader.loadClass("org.xwalk.core.JavascriptInterface");
            ReflectionHelper.class.getClassLoader().loadClass("org.xwalk.core.internal.XWalkContent").getDeclaredMethod("setJavascriptInterfaceClass", new Class[]{javascriptInterface.getClass()}).invoke((Object) null, new Object[]{javascriptInterface});
        } catch (Exception e) {
            handleException(e);
        }
    }

    public static void registerConstructor(String name, String clazz, Object... params) {
        sConstructorHelperMap.put(name, new ConstructorHelper(clazz, params));
    }

    public static Class<?> loadClass(String clazz) {
        if (sBridgeOrWrapperLoader == null) {
            init();
        }
        if (sBridgeOrWrapperLoader == null) {
            return null;
        }
        try {
            return sBridgeOrWrapperLoader.loadClass(clazz);
        } catch (ClassNotFoundException e) {
            handleException((Exception) e);
            return null;
        }
    }

    public static Method loadMethod(Class<?> clazz, String name, Object... paramTypes) {
        if (sBridgeOrWrapperLoader == null) {
            return null;
        }
        Class<?>[] params = new Class[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Object type = paramTypes[i];
            if (type instanceof Class) {
                params[i] = (Class) type;
            } else if (type instanceof String) {
                params[i] = loadClass((String) type);
            }
        }
        try {
            return clazz.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            handleException((Exception) e);
            return null;
        }
    }

    public static void handleException(Exception e) {
        e.printStackTrace();
        if (!isWrapper() || sExceptionHandler == null || !sExceptionHandler.handleException(e)) {
            throw new RuntimeException(e);
        }
    }

    public static void handleException(String e) {
        handleException((Exception) new RuntimeException(e));
    }

    public static Object createInstance(String name, Object... parameters) {
        ConstructorHelper helper;
        Constructor<?> creator = sConstructorMap.get(name);
        if (creator == null && (helper = sConstructorHelperMap.get(name)) != null) {
            creator = helper.loadConstructor();
            sConstructorMap.put(name, creator);
        }
        if (creator == null) {
            return null;
        }
        try {
            return creator.newInstance(parameters);
        } catch (IllegalArgumentException e) {
            handleException((Exception) e);
            return null;
        } catch (InstantiationException e2) {
            handleException((Exception) e2);
            return null;
        } catch (IllegalAccessException e3) {
            handleException((Exception) e3);
            return null;
        } catch (InvocationTargetException e4) {
            handleException((Exception) e4);
            return null;
        }
    }

    public static Object invokeMethod(Method m, Object instance, Object... parameters) {
        if (sBridgeOrWrapperLoader == null || m == null) {
            return null;
        }
        try {
            return m.invoke(instance, parameters);
        } catch (IllegalArgumentException e) {
            handleException((Exception) e);
            return null;
        } catch (IllegalAccessException e2) {
            handleException((Exception) e2);
            return null;
        } catch (InvocationTargetException e3) {
            handleException((Exception) e3);
            return null;
        } catch (NullPointerException e4) {
            handleException((Exception) e4);
            return null;
        }
    }

    public static Object getBridgeOrWrapper(Object instance) {
        if (sBridgeOrWrapperLoader == null || instance == null) {
            return null;
        }
        Class<?> clazz = instance.getClass();
        Method method = sBridgeWrapperMap.get(clazz);
        if (method == null) {
            String methodName = "getBridge";
            if (sIsWrapper) {
                methodName = "getWrapper";
            }
            try {
                method = clazz.getDeclaredMethod(methodName, new Class[0]);
            } catch (NoSuchMethodException e) {
                handleException((Exception) e);
            }
            if (method == null) {
                return invokeMethod(method, instance, new Object[0]);
            }
            sBridgeWrapperMap.put(clazz, method);
        }
        if (method.isAccessible()) {
            return invokeMethod(method, instance, new Object[0]);
        }
        method.setAccessible(true);
        Object invokeMethod = invokeMethod(method, instance, new Object[0]);
        method.setAccessible(false);
        return invokeMethod;
    }

    private static boolean isWrapper() {
        return !ReflectionHelper.class.getPackage().getName().equals(INTERNAL_PACKAGE);
    }
}
