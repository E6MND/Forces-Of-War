package com.googlecode.eyesfree.braille.selfbraille;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.googlecode.eyesfree.braille.selfbraille.ISelfBrailleService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SelfBrailleClient {
    private static final String ACTION_SELF_BRAILLE_SERVICE = "com.googlecode.eyesfree.braille.service.ACTION_SELF_BRAILLE_SERVICE";
    private static final String BRAILLE_BACK_PACKAGE = "com.googlecode.eyesfree.brailleback";
    private static final byte[] EYES_FREE_CERT_SHA1 = {-101, 66, 76, 45, 39, -83, 81, -92, 42, 51, 126, 11, -74, -103, 28, 118, -20, -92, 68, 97};
    /* access modifiers changed from: private */
    public static final String LOG_TAG = SelfBrailleClient.class.getSimpleName();
    private static final int MAX_REBIND_ATTEMPTS = 5;
    private static final int REBIND_DELAY_MILLIS = 500;
    private static final Intent mServiceIntent = new Intent(ACTION_SELF_BRAILLE_SERVICE).setPackage(BRAILLE_BACK_PACKAGE);
    private final boolean mAllowDebugService;
    /* access modifiers changed from: private */
    public volatile Connection mConnection;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final SelfBrailleHandler mHandler = new SelfBrailleHandler();
    private final Binder mIdentity = new Binder();
    /* access modifiers changed from: private */
    public int mNumFailedBinds = 0;
    /* access modifiers changed from: private */
    public boolean mShutdown = false;

    static /* synthetic */ int access$604(SelfBrailleClient x0) {
        int i = x0.mNumFailedBinds + 1;
        x0.mNumFailedBinds = i;
        return i;
    }

    public SelfBrailleClient(Context context, boolean allowDebugService) {
        this.mContext = context;
        this.mAllowDebugService = allowDebugService;
        doBindService();
    }

    public void shutdown() {
        this.mShutdown = true;
        doUnbindService();
    }

    public void write(WriteData writeData) {
        writeData.validate();
        ISelfBrailleService localService = getSelfBrailleService();
        if (localService != null) {
            try {
                localService.write(this.mIdentity, writeData);
            } catch (RemoteException ex) {
                Log.e(LOG_TAG, "Self braille write failed", ex);
            }
        }
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
        Log.i(LOG_TAG, "Bound to self braille service");
    }

    /* access modifiers changed from: private */
    public void doUnbindService() {
        if (this.mConnection != null) {
            ISelfBrailleService localService = getSelfBrailleService();
            if (localService != null) {
                try {
                    localService.disconnect(this.mIdentity);
                } catch (RemoteException e) {
                }
            }
            this.mContext.unbindService(this.mConnection);
            this.mConnection = null;
        }
    }

    private ISelfBrailleService getSelfBrailleService() {
        Connection localConnection = this.mConnection;
        if (localConnection != null) {
            return localConnection.mService;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean verifyPackage() {
        try {
            PackageInfo pi = this.mContext.getPackageManager().getPackageInfo(BRAILLE_BACK_PACKAGE, 64);
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                for (Signature signature : pi.signatures) {
                    digest.update(signature.toByteArray());
                    if (MessageDigest.isEqual(EYES_FREE_CERT_SHA1, digest.digest())) {
                        return true;
                    }
                    digest.reset();
                }
                if (!this.mAllowDebugService) {
                    return false;
                }
                Log.w(LOG_TAG, String.format("*** %s connected to BrailleBack with invalid (debug?) signature ***", new Object[]{this.mContext.getPackageName()}));
                return true;
            } catch (NoSuchAlgorithmException ex) {
                Log.e(LOG_TAG, "SHA-1 not supported", ex);
                return false;
            }
        } catch (PackageManager.NameNotFoundException ex2) {
            Log.w(LOG_TAG, "Can't verify package com.googlecode.eyesfree.brailleback", ex2);
            return false;
        }
    }

    private class Connection implements ServiceConnection {
        /* access modifiers changed from: private */
        public volatile ISelfBrailleService mService;

        private Connection() {
        }

        public void onServiceConnected(ComponentName className, IBinder binder) {
            if (!SelfBrailleClient.this.verifyPackage()) {
                Log.w(SelfBrailleClient.LOG_TAG, String.format("Service certificate mismatch for %s, dropping connection", new Object[]{SelfBrailleClient.BRAILLE_BACK_PACKAGE}));
                SelfBrailleClient.this.mHandler.unbindService();
                return;
            }
            Log.i(SelfBrailleClient.LOG_TAG, "Connected to self braille service");
            this.mService = ISelfBrailleService.Stub.asInterface(binder);
            synchronized (SelfBrailleClient.this.mHandler) {
                int unused = SelfBrailleClient.this.mNumFailedBinds = 0;
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.e(SelfBrailleClient.LOG_TAG, "Disconnected from self braille service");
            this.mService = null;
            SelfBrailleClient.this.mHandler.scheduleRebind();
        }
    }

    private class SelfBrailleHandler extends Handler {
        private static final int MSG_REBIND_SERVICE = 1;
        private static final int MSG_UNBIND_SERVICE = 2;

        private SelfBrailleHandler() {
        }

        public void scheduleRebind() {
            synchronized (this) {
                if (SelfBrailleClient.this.mNumFailedBinds < 5) {
                    sendEmptyMessageDelayed(1, (long) (SelfBrailleClient.REBIND_DELAY_MILLIS << SelfBrailleClient.this.mNumFailedBinds));
                    SelfBrailleClient.access$604(SelfBrailleClient.this);
                }
            }
        }

        public void unbindService() {
            sendEmptyMessage(2);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    handleRebindService();
                    return;
                case 2:
                    handleUnbindService();
                    return;
                default:
                    return;
            }
        }

        private void handleRebindService() {
            if (!SelfBrailleClient.this.mShutdown) {
                if (SelfBrailleClient.this.mConnection != null) {
                    SelfBrailleClient.this.doUnbindService();
                }
                SelfBrailleClient.this.doBindService();
            }
        }

        private void handleUnbindService() {
            SelfBrailleClient.this.doUnbindService();
        }
    }
}
