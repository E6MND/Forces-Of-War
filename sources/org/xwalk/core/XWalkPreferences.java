package org.xwalk.core;

public class XWalkPreferences {
    public static final String ALLOW_UNIVERSAL_ACCESS_FROM_FILE = "allow-universal-access-from-file";
    public static final String ANIMATABLE_XWALK_VIEW = "animatable-xwalk-view";
    private static final String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkPreferencesBridge";
    public static final String JAVASCRIPT_CAN_OPEN_WINDOW = "javascript-can-open-window";
    public static final String PROFILE_NAME = "profile-name";
    public static final String REMOTE_DEBUGGING = "remote-debugging";
    public static final String SUPPORT_MULTIPLE_WINDOWS = "support-multiple-windows";
    private Object bridge;

    /* access modifiers changed from: package-private */
    public Object getBridge() {
        return this.bridge;
    }

    public static void setValue(String key, boolean enabled) {
        ReflectionHelper.invokeMethod(ReflectionHelper.loadMethod(ReflectionHelper.loadClass(BRIDGE_CLASS), "setValue", String.class, Boolean.TYPE), (Object) null, key, Boolean.valueOf(enabled));
    }

    public static void setValue(String key, int value) {
        ReflectionHelper.invokeMethod(ReflectionHelper.loadMethod(ReflectionHelper.loadClass(BRIDGE_CLASS), "setValue", String.class, Integer.TYPE), (Object) null, key, Integer.valueOf(value));
    }

    public static void setValue(String key, String value) {
        ReflectionHelper.invokeMethod(ReflectionHelper.loadMethod(ReflectionHelper.loadClass(BRIDGE_CLASS), "setValue", String.class, String.class), (Object) null, key, value);
    }

    public static boolean getValue(String key) {
        return ((Boolean) ReflectionHelper.invokeMethod(ReflectionHelper.loadMethod(ReflectionHelper.loadClass(BRIDGE_CLASS), "getValue", String.class), (Object) null, key)).booleanValue();
    }

    public static boolean getBooleanValue(String key) {
        return ((Boolean) ReflectionHelper.invokeMethod(ReflectionHelper.loadMethod(ReflectionHelper.loadClass(BRIDGE_CLASS), "getBooleanValue", String.class), (Object) null, key)).booleanValue();
    }

    public static int getIntegerValue(String key) {
        return ((Integer) ReflectionHelper.invokeMethod(ReflectionHelper.loadMethod(ReflectionHelper.loadClass(BRIDGE_CLASS), "getIntegerValue", String.class), (Object) null, key)).intValue();
    }

    public static String getStringValue(String key) {
        return (String) ReflectionHelper.invokeMethod(ReflectionHelper.loadMethod(ReflectionHelper.loadClass(BRIDGE_CLASS), "getStringValue", String.class), (Object) null, key);
    }
}
