package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.google.android.c2dm.C2DMBaseReceiver;
import com.google.android.c2dm.C2DMessaging;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GoogleCloudMessaging {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    static GoogleCloudMessaging Up;
    private PendingIntent Uq;
    final BlockingQueue<Intent> Ur = new LinkedBlockingQueue();
    private Handler Us = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            GoogleCloudMessaging.this.Ur.add((Intent) msg.obj);
        }
    };
    private Messenger Ut = new Messenger(this.Us);
    private Context kM;

    private void a(String str, String str2, long j, int i, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException(ERROR_MAIN_THREAD);
        } else if (str == null) {
            throw new IllegalArgumentException("Missing 'to'");
        } else {
            Intent intent = new Intent("com.google.android.gcm.intent.SEND");
            intent.putExtras(bundle);
            h(intent);
            intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
            intent.putExtra("google.to", str);
            intent.putExtra("google.message_id", str2);
            intent.putExtra("google.ttl", Long.toString(j));
            intent.putExtra("google.delay", Integer.toString(i));
            this.kM.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
        }
    }

    private void c(String... strArr) {
        String d = d(strArr);
        Intent intent = new Intent(C2DMessaging.REQUEST_REGISTRATION_INTENT);
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        intent.putExtra("google.messenger", this.Ut);
        h(intent);
        intent.putExtra(C2DMessaging.EXTRA_SENDER, d);
        this.kM.startService(intent);
    }

    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        GoogleCloudMessaging googleCloudMessaging;
        synchronized (GoogleCloudMessaging.class) {
            if (Up == null) {
                Up = new GoogleCloudMessaging();
                Up.kM = context;
            }
            googleCloudMessaging = Up;
        }
        return googleCloudMessaging;
    }

    private void iJ() {
        Intent intent = new Intent(C2DMessaging.REQUEST_UNREGISTRATION_INTENT);
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        this.Ur.clear();
        intent.putExtra("google.messenger", this.Ut);
        h(intent);
        this.kM.startService(intent);
    }

    public void close() {
        iK();
    }

    /* access modifiers changed from: package-private */
    public String d(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(',').append(strArr[i]);
        }
        return sb.toString();
    }

    public String getMessageType(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            return null;
        }
        String stringExtra = intent.getStringExtra("message_type");
        return stringExtra == null ? MESSAGE_TYPE_MESSAGE : stringExtra;
    }

    /* access modifiers changed from: package-private */
    public synchronized void h(Intent intent) {
        if (this.Uq == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.Uq = PendingIntent.getBroadcast(this.kM, 0, intent2, 0);
        }
        intent.putExtra("app", this.Uq);
    }

    /* access modifiers changed from: package-private */
    public synchronized void iK() {
        if (this.Uq != null) {
            this.Uq.cancel();
            this.Uq = null;
        }
    }

    public String register(String... senderIds) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException(ERROR_MAIN_THREAD);
        }
        this.Ur.clear();
        c(senderIds);
        try {
            Intent poll = this.Ur.poll(5000, TimeUnit.MILLISECONDS);
            if (poll == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            String stringExtra = poll.getStringExtra(C2DMBaseReceiver.EXTRA_REGISTRATION_ID);
            if (stringExtra != null) {
                return stringExtra;
            }
            poll.getStringExtra("error");
            String stringExtra2 = poll.getStringExtra("error");
            if (stringExtra2 != null) {
                throw new IOException(stringExtra2);
            }
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void send(String to, String msgId, long timeToLive, Bundle data) throws IOException {
        a(to, msgId, timeToLive, -1, data);
    }

    public void send(String to, String msgId, Bundle data) throws IOException {
        send(to, msgId, -1, data);
    }

    public void unregister() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException(ERROR_MAIN_THREAD);
        }
        iJ();
        try {
            Intent poll = this.Ur.poll(5000, TimeUnit.MILLISECONDS);
            if (poll == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            } else if (poll.getStringExtra(C2DMBaseReceiver.EXTRA_UNREGISTERED) == null) {
                String stringExtra = poll.getStringExtra("error");
                if (stringExtra != null) {
                    throw new IOException(stringExtra);
                }
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }
}
