package com.amazon.inapp.purchasing;

import java.util.HashMap;
import java.util.Map;

final class KiwiImplementationRegistry implements ImplementationRegistry {
    private static final Map<Class, Class> classMap = new HashMap();

    static {
        classMap.put(RequestHandler.class, KiwiRequestHandler.class);
        classMap.put(ResponseHandler.class, KiwiResponseHandler.class);
        classMap.put(LogHandler.class, KiwiLogHandler.class);
    }

    KiwiImplementationRegistry() {
    }

    public <T> Class<T> getImplementation(Class<T> cls) {
        return classMap.get(cls);
    }
}
