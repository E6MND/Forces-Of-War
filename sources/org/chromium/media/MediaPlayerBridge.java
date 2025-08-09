package org.chromium.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Base64InputStream;
import android.util.Log;
import android.view.Surface;
import com.facebook.internal.ServerProtocol;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
public class MediaPlayerBridge {
    private static final String TAG = "MediaPlayerBridge";
    private static ResourceLoadingFilter sResourceLoadFilter = null;
    private LoadDataUriTask mLoadDataUriTask;
    /* access modifiers changed from: private */
    public long mNativeMediaPlayerBridge;
    private MediaPlayer mPlayer;

    /* access modifiers changed from: private */
    public native void nativeOnDidSetDataUriDataSource(long j, boolean z);

    public static class ResourceLoadingFilter {
        public boolean shouldOverrideResourceLoading(MediaPlayer mediaPlayer, Context context, Uri uri) {
            return false;
        }
    }

    public static void setResourceLoadingFilter(ResourceLoadingFilter filter) {
        sResourceLoadFilter = filter;
    }

    @CalledByNative
    private static MediaPlayerBridge create(long nativeMediaPlayerBridge) {
        return new MediaPlayerBridge(nativeMediaPlayerBridge);
    }

    protected MediaPlayerBridge(long nativeMediaPlayerBridge) {
        this.mNativeMediaPlayerBridge = nativeMediaPlayerBridge;
    }

    protected MediaPlayerBridge() {
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void destroy() {
        if (this.mLoadDataUriTask != null) {
            this.mLoadDataUriTask.cancel(true);
            this.mLoadDataUriTask = null;
        }
        this.mNativeMediaPlayerBridge = 0;
    }

    /* access modifiers changed from: protected */
    public MediaPlayer getLocalPlayer() {
        if (this.mPlayer == null) {
            this.mPlayer = new MediaPlayer();
        }
        return this.mPlayer;
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void setSurface(Surface surface) {
        getLocalPlayer().setSurface(surface);
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public boolean prepareAsync() {
        try {
            getLocalPlayer().prepareAsync();
            return true;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Unable to prepare MediaPlayer.", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public boolean isPlaying() {
        return getLocalPlayer().isPlaying();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public int getVideoWidth() {
        return getLocalPlayer().getVideoWidth();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public int getVideoHeight() {
        return getLocalPlayer().getVideoHeight();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public int getCurrentPosition() {
        return getLocalPlayer().getCurrentPosition();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public int getDuration() {
        return getLocalPlayer().getDuration();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void release() {
        getLocalPlayer().release();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void setVolume(double volume) {
        getLocalPlayer().setVolume((float) volume, (float) volume);
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void start() {
        getLocalPlayer().start();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void pause() {
        getLocalPlayer().pause();
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void seekTo(int msec) throws IllegalStateException {
        getLocalPlayer().seekTo(msec);
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public boolean setDataSource(Context context, String url, String cookies, String userAgent, boolean hideUrlLog) {
        Uri uri = Uri.parse(url);
        HashMap<String, String> headersMap = new HashMap<>();
        if (hideUrlLog) {
            headersMap.put("x-hide-urls-from-log", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        if (!TextUtils.isEmpty(cookies)) {
            headersMap.put("Cookie", cookies);
        }
        if (!TextUtils.isEmpty(userAgent)) {
            headersMap.put("User-Agent", userAgent);
        }
        if (Build.VERSION.SDK_INT > 19) {
            headersMap.put("allow-cross-domain-redirect", "false");
        }
        try {
            if (sResourceLoadFilter != null && sResourceLoadFilter.shouldOverrideResourceLoading(getLocalPlayer(), context, uri)) {
                return true;
            }
            getLocalPlayer().setDataSource(context, uri, headersMap);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public boolean setDataSourceFromFd(int fd, long offset, long length) {
        try {
            ParcelFileDescriptor parcelFd = ParcelFileDescriptor.adoptFd(fd);
            getLocalPlayer().setDataSource(parcelFd.getFileDescriptor(), offset, length);
            parcelFd.close();
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Failed to set data source from file descriptor: " + e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public boolean setDataUriDataSource(Context context, String url) {
        int headerStop;
        if (this.mLoadDataUriTask != null) {
            this.mLoadDataUriTask.cancel(true);
            this.mLoadDataUriTask = null;
        }
        if (!url.startsWith("data:") || (headerStop = url.indexOf(44)) == -1) {
            return false;
        }
        String header = url.substring(0, headerStop);
        String data = url.substring(headerStop + 1);
        String[] headerInfo = header.substring(5).split(";");
        if (headerInfo.length != 2 || !"base64".equals(headerInfo[1])) {
            return false;
        }
        this.mLoadDataUriTask = new LoadDataUriTask(context, data);
        this.mLoadDataUriTask.execute(new Void[0]);
        return true;
    }

    private class LoadDataUriTask extends AsyncTask<Void, Void, Boolean> {
        static final /* synthetic */ boolean $assertionsDisabled = (!MediaPlayerBridge.class.desiredAssertionStatus());
        private final Context mContext;
        private final String mData;
        private File mTempFile;

        public LoadDataUriTask(Context context, String data) {
            this.mData = data;
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... params) {
            boolean z;
            FileOutputStream fos = null;
            try {
                this.mTempFile = File.createTempFile("decoded", "mediadata");
                FileOutputStream fos2 = new FileOutputStream(this.mTempFile);
                try {
                    Base64InputStream decoder = new Base64InputStream(new ByteArrayInputStream(this.mData.getBytes()), 0);
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int len = decoder.read(buffer);
                        if (len == -1) {
                            break;
                        }
                        fos2.write(buffer, 0, len);
                    }
                    decoder.close();
                    z = true;
                    if (fos2 != null) {
                        try {
                            fos2.close();
                        } catch (IOException e) {
                        }
                    }
                    FileOutputStream fileOutputStream = fos2;
                } catch (IOException e2) {
                    fos = fos2;
                } catch (Throwable th) {
                    th = th;
                    fos = fos2;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                try {
                    z = false;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e5) {
                        }
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean result) {
            if (isCancelled()) {
                deleteFile();
                return;
            }
            try {
                MediaPlayerBridge.this.getLocalPlayer().setDataSource(this.mContext, Uri.fromFile(this.mTempFile));
            } catch (IOException e) {
                result = false;
            }
            deleteFile();
            if ($assertionsDisabled || MediaPlayerBridge.this.mNativeMediaPlayerBridge != 0) {
                MediaPlayerBridge.this.nativeOnDidSetDataUriDataSource(MediaPlayerBridge.this.mNativeMediaPlayerBridge, result.booleanValue());
                return;
            }
            throw new AssertionError();
        }

        private void deleteFile() {
            if (this.mTempFile != null && !this.mTempFile.delete()) {
                Log.e(MediaPlayerBridge.TAG, "Failed to delete temporary file: " + this.mTempFile);
                if (!$assertionsDisabled) {
                    throw new AssertionError();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener listener) {
        getLocalPlayer().setOnBufferingUpdateListener(listener);
    }

    /* access modifiers changed from: protected */
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        getLocalPlayer().setOnCompletionListener(listener);
    }

    /* access modifiers changed from: protected */
    public void setOnErrorListener(MediaPlayer.OnErrorListener listener) {
        getLocalPlayer().setOnErrorListener(listener);
    }

    /* access modifiers changed from: protected */
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener listener) {
        getLocalPlayer().setOnPreparedListener(listener);
    }

    /* access modifiers changed from: protected */
    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener listener) {
        getLocalPlayer().setOnSeekCompleteListener(listener);
    }

    /* access modifiers changed from: protected */
    public void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener listener) {
        getLocalPlayer().setOnVideoSizeChangedListener(listener);
    }

    protected static class AllowedOperations {
        private final boolean mCanPause;
        private final boolean mCanSeekBackward;
        private final boolean mCanSeekForward;

        public AllowedOperations(boolean canPause, boolean canSeekForward, boolean canSeekBackward) {
            this.mCanPause = canPause;
            this.mCanSeekForward = canSeekForward;
            this.mCanSeekBackward = canSeekBackward;
        }

        @CalledByNative("AllowedOperations")
        private boolean canPause() {
            return this.mCanPause;
        }

        @CalledByNative("AllowedOperations")
        private boolean canSeekForward() {
            return this.mCanSeekForward;
        }

        @CalledByNative("AllowedOperations")
        private boolean canSeekBackward() {
            return this.mCanSeekBackward;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x00db A[Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0108 A[Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }] */
    @org.chromium.base.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.chromium.media.MediaPlayerBridge.AllowedOperations getAllowedOperations() {
        /*
            r18 = this;
            android.media.MediaPlayer r10 = r18.getLocalPlayer()
            r0 = 1
            r2 = 1
            r1 = 1
            java.lang.Class r13 = r10.getClass()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.String r14 = "getMetadata"
            r15 = 2
            java.lang.Class[] r15 = new java.lang.Class[r15]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r16 = 0
            java.lang.Class r17 = java.lang.Boolean.TYPE     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r15[r16] = r17     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r16 = 1
            java.lang.Class r17 = java.lang.Boolean.TYPE     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r15[r16] = r17     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.reflect.Method r6 = r13.getDeclaredMethod(r14, r15)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13 = 1
            r6.setAccessible(r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13 = 2
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            r15 = 0
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r15)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 1
            r15 = 0
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r15)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Object r3 = r6.invoke(r10, r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            if (r3 == 0) goto L_0x011f
            java.lang.Class r8 = r3.getClass()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.String r13 = "has"
            r14 = 1
            java.lang.Class[] r14 = new java.lang.Class[r14]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r15 = 0
            java.lang.Class r16 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14[r15] = r16     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.reflect.Method r7 = r8.getDeclaredMethod(r13, r14)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.String r13 = "getBoolean"
            r14 = 1
            java.lang.Class[] r14 = new java.lang.Class[r14]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r15 = 0
            java.lang.Class r16 = java.lang.Integer.TYPE     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14[r15] = r16     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.reflect.Method r5 = r8.getDeclaredMethod(r13, r14)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.String r13 = "PAUSE_AVAILABLE"
            java.lang.reflect.Field r13 = r8.getField(r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Object r13 = r13.get(r14)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Integer r13 = (java.lang.Integer) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            int r9 = r13.intValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.String r13 = "SEEK_FORWARD_AVAILABLE"
            java.lang.reflect.Field r13 = r8.getField(r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Object r13 = r13.get(r14)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Integer r13 = (java.lang.Integer) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            int r12 = r13.intValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.String r13 = "SEEK_BACKWARD_AVAILABLE"
            java.lang.reflect.Field r13 = r8.getField(r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Object r13 = r13.get(r14)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Integer r13 = (java.lang.Integer) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            int r11 = r13.intValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13 = 1
            r7.setAccessible(r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13 = 1
            r5.setAccessible(r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13 = 1
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r9)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Object r13 = r7.invoke(r3, r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            boolean r13 = r13.booleanValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            if (r13 == 0) goto L_0x00c4
            r13 = 1
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r9)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Object r13 = r5.invoke(r3, r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            boolean r13 = r13.booleanValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            if (r13 == 0) goto L_0x0125
        L_0x00c4:
            r0 = 1
        L_0x00c5:
            r13 = 1
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r12)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Object r13 = r7.invoke(r3, r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            boolean r13 = r13.booleanValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            if (r13 == 0) goto L_0x00f1
            r13 = 1
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r12)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Object r13 = r5.invoke(r3, r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            boolean r13 = r13.booleanValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            if (r13 == 0) goto L_0x0127
        L_0x00f1:
            r2 = 1
        L_0x00f2:
            r13 = 1
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r11)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Object r13 = r7.invoke(r3, r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            boolean r13 = r13.booleanValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            if (r13 == 0) goto L_0x011e
            r13 = 1
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r14 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r11)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            r13[r14] = r15     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Object r13 = r5.invoke(r3, r13)     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            boolean r13 = r13.booleanValue()     // Catch:{ NoSuchMethodException -> 0x012b, InvocationTargetException -> 0x0145, IllegalAccessException -> 0x015f, NoSuchFieldException -> 0x0179 }
            if (r13 == 0) goto L_0x0129
        L_0x011e:
            r1 = 1
        L_0x011f:
            org.chromium.media.MediaPlayerBridge$AllowedOperations r13 = new org.chromium.media.MediaPlayerBridge$AllowedOperations
            r13.<init>(r0, r2, r1)
            return r13
        L_0x0125:
            r0 = 0
            goto L_0x00c5
        L_0x0127:
            r2 = 0
            goto L_0x00f2
        L_0x0129:
            r1 = 0
            goto L_0x011f
        L_0x012b:
            r4 = move-exception
            java.lang.String r13 = "MediaPlayerBridge"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Cannot find getMetadata() method: "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r4)
            java.lang.String r14 = r14.toString()
            android.util.Log.e(r13, r14)
            goto L_0x011f
        L_0x0145:
            r4 = move-exception
            java.lang.String r13 = "MediaPlayerBridge"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Cannot invoke MediaPlayer.getMetadata() method: "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r4)
            java.lang.String r14 = r14.toString()
            android.util.Log.e(r13, r14)
            goto L_0x011f
        L_0x015f:
            r4 = move-exception
            java.lang.String r13 = "MediaPlayerBridge"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Cannot access metadata: "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r4)
            java.lang.String r14 = r14.toString()
            android.util.Log.e(r13, r14)
            goto L_0x011f
        L_0x0179:
            r4 = move-exception
            java.lang.String r13 = "MediaPlayerBridge"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Cannot find matching fields in Metadata class: "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r4)
            java.lang.String r14 = r14.toString()
            android.util.Log.e(r13, r14)
            goto L_0x011f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaPlayerBridge.getAllowedOperations():org.chromium.media.MediaPlayerBridge$AllowedOperations");
    }
}
