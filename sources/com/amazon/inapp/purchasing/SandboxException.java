package com.amazon.inapp.purchasing;

class SandboxException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public SandboxException() {
    }

    public SandboxException(String str) {
        super(str);
    }

    public SandboxException(String str, Throwable th) {
        super(str, th);
    }

    public SandboxException(Throwable th) {
        super(th);
    }
}
