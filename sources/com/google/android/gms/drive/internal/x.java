package com.google.android.gms.drive.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.internal.ac;
import com.google.android.gms.internal.hn;

public class x<C extends DriveEvent> extends ac.a {
    private final int In;
    private final DriveEvent.Listener<C> Jk;
    private final a<C> Jl;

    private static class a<E extends DriveEvent> extends Handler {
        private a(Looper looper) {
            super(looper);
        }

        public void a(DriveEvent.Listener<E> listener, E e) {
            sendMessage(obtainMessage(1, new Pair(listener, e)));
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Pair pair = (Pair) msg.obj;
                    ((DriveEvent.Listener) pair.first).onEvent((DriveEvent) pair.second);
                    return;
                default:
                    Log.wtf("EventCallback", "Don't know how to handle this event");
                    return;
            }
        }
    }

    public x(Looper looper, int i, DriveEvent.Listener<C> listener) {
        this.In = i;
        this.Jk = listener;
        this.Jl = new a<>(looper);
    }

    public void a(OnEventResponse onEventResponse) throws RemoteException {
        hn.A(this.In == onEventResponse.getEventType());
        switch (onEventResponse.getEventType()) {
            case 1:
                this.Jl.a(this.Jk, onEventResponse.gr());
                return;
            case 2:
                this.Jl.a(this.Jk, onEventResponse.gs());
                return;
            default:
                Log.w("EventCallback", "Unexpected event type:" + onEventResponse.getEventType());
                return;
        }
    }
}
