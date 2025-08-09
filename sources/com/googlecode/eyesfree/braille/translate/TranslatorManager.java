package com.googlecode.eyesfree.braille.translate;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.googlecode.eyesfree.braille.translate.ITranslatorService;
import com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback;

public class TranslatorManager {
    private static final String ACTION_TRANSLATOR_SERVICE = "com.googlecode.eyesfree.braille.service.ACTION_TRANSLATOR_SERVICE";
    public static final int ERROR = -1;
    /* access modifiers changed from: private */
    public static final String LOG_TAG = TranslatorManager.class.getSimpleName();
    private static final int MAX_REBIND_ATTEMPTS = 5;
    private static final int REBIND_DELAY_MILLIS = 500;
    public static final int SUCCESS = 0;
    private static final Intent mServiceIntent = new Intent(ACTION_TRANSLATOR_SERVICE);
    /* access modifiers changed from: private */
    public Connection mConnection;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final TranslatorManagerHandler mHandler = new TranslatorManagerHandler();
    /* access modifiers changed from: private */
    public int mNumFailedBinds = 0;
    /* access modifiers changed from: private */
    public OnInitListener mOnInitListener;
    /* access modifiers changed from: private */
    public final ServiceCallback mServiceCallback = new ServiceCallback();

    public interface OnInitListener {
        void onInit(int i);
    }

    static /* synthetic */ int access$704(TranslatorManager x0) {
        int i = x0.mNumFailedBinds + 1;
        x0.mNumFailedBinds = i;
        return i;
    }

    public TranslatorManager(Context context, OnInitListener onInitListener) {
        this.mContext = context;
        this.mOnInitListener = onInitListener;
        doBindService();
    }

    public void destroy() {
        doUnbindService();
        this.mHandler.destroy();
    }

    public BrailleTranslator getTranslator(String tableName) {
        ITranslatorService localService = getTranslatorService();
        if (localService != null) {
            try {
                if (localService.checkTable(tableName)) {
                    return new BrailleTranslatorImpl(tableName);
                }
            } catch (RemoteException ex) {
                Log.e(LOG_TAG, "Error in getTranslator", ex);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void doBindService() {
        Connection localConnection = new Connection();
        if (!this.mContext.bindService(mServiceIntent, localConnection, 1)) {
            Log.e(LOG_TAG, "Failed to bind to service");
            this.mHandler.scheduleRebind();
            return;
        }
        this.mConnection = localConnection;
        Log.i(LOG_TAG, "Bound to translator service");
    }

    /* access modifiers changed from: private */
    public void doUnbindService() {
        if (this.mConnection != null) {
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
        }
    }

    /* access modifiers changed from: private */
    public ITranslatorService getTranslatorService() {
        Connection localConnection = this.mConnection;
        if (localConnection != null) {
            return localConnection.mService;
        }
        return null;
    }

    private class Connection implements ServiceConnection {
        /* access modifiers changed from: private */
        public volatile ITranslatorService mService;

        private Connection() {
        }

        public void onServiceConnected(ComponentName className, IBinder binder) {
            Log.i(TranslatorManager.LOG_TAG, "Connected to translation service");
            ITranslatorService localService = ITranslatorService.Stub.asInterface(binder);
            try {
                localService.setCallback(TranslatorManager.this.mServiceCallback);
                this.mService = localService;
                synchronized (TranslatorManager.this.mHandler) {
                    int unused = TranslatorManager.this.mNumFailedBinds = 0;
                }
            } catch (RemoteException ex) {
                Log.e(TranslatorManager.LOG_TAG, "Error when setting callback", ex);
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.e(TranslatorManager.LOG_TAG, "Disconnected from translator service");
            this.mService = null;
            TranslatorManager.this.mHandler.scheduleRebind();
        }
    }

    private class BrailleTranslatorImpl implements BrailleTranslator {
        private final String mTable;

        public BrailleTranslatorImpl(String table) {
            this.mTable = table;
        }

        public byte[] translate(String text) {
            ITranslatorService localService = TranslatorManager.this.getTranslatorService();
            if (localService != null) {
                try {
                    return localService.translate(text, this.mTable);
                } catch (RemoteException ex) {
                    Log.e(TranslatorManager.LOG_TAG, "Error in translate", ex);
                }
            }
            return null;
        }

        public String backTranslate(byte[] cells) {
            ITranslatorService localService = TranslatorManager.this.getTranslatorService();
            if (localService != null) {
                try {
                    return localService.backTranslate(cells, this.mTable);
                } catch (RemoteException ex) {
                    Log.e(TranslatorManager.LOG_TAG, "Error in backTranslate", ex);
                }
            }
            return null;
        }
    }

    private class ServiceCallback extends ITranslatorServiceCallback.Stub {
        private ServiceCallback() {
        }

        public void onInit(int status) {
            TranslatorManager.this.mHandler.onInit(status);
        }
    }

    private class TranslatorManagerHandler extends Handler {
        private static final int MSG_ON_INIT = 1;
        private static final int MSG_REBIND_SERVICE = 2;

        private TranslatorManagerHandler() {
        }

        public void onInit(int status) {
            obtainMessage(1, status, 0).sendToTarget();
        }

        public void destroy() {
            OnInitListener unused = TranslatorManager.this.mOnInitListener = null;
            removeCallbacksAndMessages((Object) null);
        }

        public void scheduleRebind() {
            synchronized (this) {
                if (TranslatorManager.this.mNumFailedBinds < 5) {
                    sendEmptyMessageDelayed(2, (long) (TranslatorManager.REBIND_DELAY_MILLIS << TranslatorManager.this.mNumFailedBinds));
                    TranslatorManager.access$704(TranslatorManager.this);
                } else {
                    onInit(-1);
                }
            }
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    handleOnInit(msg.arg1);
                    return;
                case 2:
                    handleRebindService();
                    return;
                default:
                    return;
            }
        }

        private void handleOnInit(int status) {
            if (TranslatorManager.this.mOnInitListener != null) {
                TranslatorManager.this.mOnInitListener.onInit(status);
                OnInitListener unused = TranslatorManager.this.mOnInitListener = null;
            }
        }

        private void handleRebindService() {
            if (TranslatorManager.this.mConnection != null) {
                TranslatorManager.this.doUnbindService();
            }
            TranslatorManager.this.doBindService();
        }
    }
}
