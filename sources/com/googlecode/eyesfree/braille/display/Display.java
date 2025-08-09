package com.googlecode.eyesfree.braille.display;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.googlecode.eyesfree.braille.display.IBrailleService;
import com.googlecode.eyesfree.braille.display.IBrailleServiceCallback;

public class Display {
    public static final String ACTION_DISPLAY_SERVICE = "com.googlecode.eyesfree.braille.service.ACTION_DISPLAY_SERVICE";
    /* access modifiers changed from: private */
    public static final String LOG_TAG = Display.class.getSimpleName();
    private static final int MAX_REBIND_ATTEMPTS = 5;
    private static final int REBIND_DELAY_MILLIS = 500;
    public static final int STATE_CONNECTED = 1;
    public static final int STATE_ERROR = -1;
    public static final int STATE_NOT_CONNECTED = 0;
    private static final int STATE_UNKNOWN = -2;
    private static final Intent mServiceIntent = new Intent(ACTION_DISPLAY_SERVICE);
    /* access modifiers changed from: private */
    public int currentConnectionState;
    /* access modifiers changed from: private */
    public Connection mConnection;
    /* access modifiers changed from: private */
    public final OnConnectionStateChangeListener mConnectionStateChangeListener;
    private final Context mContext;
    /* access modifiers changed from: private */
    public BrailleDisplayProperties mDisplayProperties;
    /* access modifiers changed from: private */
    public final DisplayHandler mHandler;
    /* access modifiers changed from: private */
    public volatile OnInputEventListener mInputEventListener;
    /* access modifiers changed from: private */
    public int mNumFailedBinds;
    /* access modifiers changed from: private */
    public ServiceCallback mServiceCallback;

    public interface OnConnectionStateChangeListener {
        void onConnectionStateChanged(int i);
    }

    public interface OnInputEventListener {
        void onInputEvent(BrailleInputEvent brailleInputEvent);
    }

    static /* synthetic */ int access$404(Display x0) {
        int i = x0.mNumFailedBinds + 1;
        x0.mNumFailedBinds = i;
        return i;
    }

    public Display(Context context, OnConnectionStateChangeListener listener) {
        this(context, listener, (Handler) null);
    }

    public Display(Context context, OnConnectionStateChangeListener listener, Handler handler) {
        this.currentConnectionState = -2;
        this.mServiceCallback = new ServiceCallback();
        this.mNumFailedBinds = 0;
        this.mContext = context;
        this.mConnectionStateChangeListener = listener;
        if (handler == null) {
            this.mHandler = new DisplayHandler();
        } else {
            this.mHandler = new DisplayHandler(handler);
        }
        doBindService();
    }

    public void setOnInputEventListener(OnInputEventListener listener) {
        this.mInputEventListener = listener;
    }

    public BrailleDisplayProperties getDisplayProperties() {
        return this.mDisplayProperties;
    }

    public void displayDots(byte[] patterns) {
        IBrailleService localService = getBrailleService();
        if (localService != null) {
            try {
                localService.displayDots(patterns);
            } catch (RemoteException ex) {
                Log.e(LOG_TAG, "Error in displayDots", ex);
            }
        } else {
            Log.v(LOG_TAG, "Error in displayDots: service not connected");
        }
    }

    public void shutdown() {
        doUnbindService();
    }

    private class Connection implements ServiceConnection {
        /* access modifiers changed from: private */
        public volatile IBrailleService mService;

        private Connection() {
        }

        public void onServiceConnected(ComponentName className, IBinder binder) {
            Log.i(Display.LOG_TAG, "Connected to braille service");
            IBrailleService localService = IBrailleService.Stub.asInterface(binder);
            try {
                localService.registerCallback(Display.this.mServiceCallback);
                this.mService = localService;
                synchronized (Display.this.mHandler) {
                    int unused = Display.this.mNumFailedBinds = 0;
                }
            } catch (RemoteException e) {
                Log.e(Display.LOG_TAG, "Failed to register callback on service", e);
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            this.mService = null;
            Log.e(Display.LOG_TAG, "Disconnected from braille service");
            Display.this.mHandler.reportConnectionState(0, (BrailleDisplayProperties) null);
            Display.this.mHandler.scheduleRebind();
        }
    }

    private class ServiceCallback extends IBrailleServiceCallback.Stub {
        private ServiceCallback() {
        }

        public void onDisplayConnected(BrailleDisplayProperties displayProperties) {
            Display.this.mHandler.reportConnectionState(1, displayProperties);
        }

        public void onDisplayDisconnected() {
            Display.this.mHandler.reportConnectionState(0, (BrailleDisplayProperties) null);
        }

        public void onInput(BrailleInputEvent inputEvent) {
            Display.this.mHandler.reportInputEvent(inputEvent);
        }
    }

    /* access modifiers changed from: private */
    public void doBindService() {
        Connection localConnection = new Connection();
        if (!this.mContext.bindService(mServiceIntent, localConnection, 1)) {
            Log.e(LOG_TAG, "Failed to bind Service");
            this.mHandler.scheduleRebind();
            return;
        }
        this.mConnection = localConnection;
        Log.i(LOG_TAG, "Bound to braille service");
    }

    /* access modifiers changed from: private */
    public void doUnbindService() {
        IBrailleService localService = getBrailleService();
        if (localService != null) {
            try {
                localService.unregisterCallback(this.mServiceCallback);
            } catch (RemoteException e) {
            }
        }
        if (this.mConnection != null) {
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
        }
    }

    private IBrailleService getBrailleService() {
        Connection localConnection = this.mConnection;
        if (localConnection != null) {
            return localConnection.mService;
        }
        return null;
    }

    private class DisplayHandler extends Handler {
        private static final int MSG_REBIND_SERVICE = 3;
        private static final int MSG_REPORT_CONNECTION_STATE = 1;
        private static final int MSG_REPORT_INPUT_EVENT = 2;

        public DisplayHandler() {
        }

        public DisplayHandler(Handler handler) {
            super(handler.getLooper());
        }

        public void reportConnectionState(int newState, BrailleDisplayProperties displayProperties) {
            obtainMessage(1, newState, 0, displayProperties).sendToTarget();
        }

        public void reportInputEvent(BrailleInputEvent event) {
            obtainMessage(2, event).sendToTarget();
        }

        public void scheduleRebind() {
            synchronized (this) {
                if (Display.this.mNumFailedBinds < 5) {
                    int delay = Display.REBIND_DELAY_MILLIS << Display.this.mNumFailedBinds;
                    sendEmptyMessageDelayed(3, (long) delay);
                    Display.access$404(Display.this);
                    Log.w(Display.LOG_TAG, String.format("Will rebind to braille service in %d ms.", new Object[]{Integer.valueOf(delay)}));
                } else {
                    reportConnectionState(-1, (BrailleDisplayProperties) null);
                }
            }
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    handleReportConnectionState(msg.arg1, (BrailleDisplayProperties) msg.obj);
                    return;
                case 2:
                    handleReportInputEvent((BrailleInputEvent) msg.obj);
                    return;
                case 3:
                    handleRebindService();
                    return;
                default:
                    return;
            }
        }

        private void handleReportConnectionState(int newState, BrailleDisplayProperties displayProperties) {
            BrailleDisplayProperties unused = Display.this.mDisplayProperties = displayProperties;
            if (!(newState == Display.this.currentConnectionState || Display.this.mConnectionStateChangeListener == null)) {
                Display.this.mConnectionStateChangeListener.onConnectionStateChanged(newState);
            }
            int unused2 = Display.this.currentConnectionState = newState;
        }

        private void handleReportInputEvent(BrailleInputEvent event) {
            OnInputEventListener localListener = Display.this.mInputEventListener;
            if (localListener != null) {
                localListener.onInputEvent(event);
            }
        }

        private void handleRebindService() {
            if (Display.this.mConnection != null) {
                Display.this.doUnbindService();
            }
            Display.this.doBindService();
        }
    }
}
