package com.amazon.inapp.purchasing;

import java.util.HashMap;
import java.util.Map;

final class SandboxImplementationRegistry implements ImplementationRegistry {
    private static final Map<Class, Class> classMap = new HashMap();

    static {
        classMap.put(RequestHandler.class, SandboxRequestHandler.class);
        classMap.put(ResponseHandler.class, SandboxResponseHandler.class);
        classMap.put(LogHandler.class, SandboxLogHandler.class);
    }

    SandboxImplementationRegistry() {
    }

    public <T> Class<T> getImplementation(Class<T> cls) {
        return classMap.get(cls);
    }
}
