package org.chromium.content.browser;

class FileDescriptorInfo {
    public boolean mAutoClose;
    public int mFd;
    public int mId;

    FileDescriptorInfo(int id, int fd, boolean autoClose) {
        this.mId = id;
        this.mFd = fd;
        this.mAutoClose = autoClose;
    }
}
