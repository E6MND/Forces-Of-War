package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class a implements ServiceConnection {
    boolean CN = false;
    private final BlockingQueue<IBinder> CO = new LinkedBlockingQueue();

    public IBinder er() throws InterruptedException {
        if (this.CN) {
            throw new IllegalStateException();
        }
        this.CN = true;
        return this.CO.take();
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        try {
            this.CO.put(service);
        } catch (InterruptedException e) {
        }
    }

    public void onServiceDisconnected(ComponentName name) {
    }
}
