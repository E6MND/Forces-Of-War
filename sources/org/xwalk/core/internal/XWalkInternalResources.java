package org.xwalk.core.internal;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class XWalkInternalResources {
    private static final String GENERATED_RESOURCE_CLASS = "org.xwalk.core.R";
    private static final String[] INTERNAL_RESOURCE_CLASSES = {"org.chromium.content.R", "org.chromium.ui.R", "org.xwalk.core.internal.R"};
    private static final String TAG = "XWalkInternalResources";
    private static boolean loaded = false;

    XWalkInternalResources() {
    }

    private static void doResetIds(Context context) {
        ClassLoader classLoader = context.getClassLoader();
        String[] arr$ = INTERNAL_RESOURCE_CLASSES;
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            int i$2 = i$;
            if (i$2 < len$) {
                String resourceClass = arr$[i$2];
                try {
                    Class<?>[] arr$2 = classLoader.loadClass(resourceClass).getClasses();
                    int len$2 = arr$2.length;
                    int i$3 = 0;
                    while (true) {
                        int i$4 = i$3;
                        if (i$4 >= len$2) {
                            break;
                        }
                        Class<?> innerClazz = arr$2[i$4];
                        String generatedInnerClassName = innerClazz.getName().replace(resourceClass, GENERATED_RESOURCE_CLASS);
                        try {
                            Class<?> generatedInnerClazz = classLoader.loadClass(generatedInnerClassName);
                            for (Field field : innerClazz.getFields()) {
                                if (!Modifier.isFinal(field.getModifiers())) {
                                    try {
                                        field.setInt((Object) null, generatedInnerClazz.getField(field.getName()).getInt((Object) null));
                                    } catch (IllegalAccessException e) {
                                        Log.w(TAG, generatedInnerClazz.getName() + "." + field.getName() + " is not accessable.");
                                    } catch (IllegalArgumentException e2) {
                                        Log.w(TAG, generatedInnerClazz.getName() + "." + field.getName() + " is not int.");
                                    } catch (NoSuchFieldException e3) {
                                        Log.w(TAG, generatedInnerClazz.getName() + "." + field.getName() + " is not found.");
                                    }
                                }
                            }
                        } catch (ClassNotFoundException e4) {
                            Log.w(TAG, generatedInnerClassName + "is not found.");
                        }
                        i$3 = i$4 + 1;
                    }
                } catch (ClassNotFoundException e5) {
                    Log.w(TAG, resourceClass + "is not found.");
                }
                i$ = i$2 + 1;
            } else {
                return;
            }
        }
    }

    static void resetIds(Context context) {
        if (!loaded) {
            doResetIds(context);
            loaded = true;
        }
    }
}
