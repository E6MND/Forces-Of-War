package com.amazon.inapp.purchasing;

interface ImplementationRegistry {
    <T> Class<T> getImplementation(Class<T> cls);
}
