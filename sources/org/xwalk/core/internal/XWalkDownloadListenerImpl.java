package org.xwalk.core.internal;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;
import com.facebook.internal.AnalyticsEvents;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class XWalkDownloadListenerImpl implements DownloadListener {
    /* access modifiers changed from: private */
    public static String DOWNLOAD_ALREADY_EXISTS_TOAST;
    /* access modifiers changed from: private */
    public static String DOWNLOAD_FAILED_TOAST;
    /* access modifiers changed from: private */
    public static String DOWNLOAD_FINISHED_TOAST;
    private static String DOWNLOAD_NO_PERMISSION_TOAST;
    private static String DOWNLOAD_START_TOAST;
    /* access modifiers changed from: private */
    public Context mContext;

    public XWalkDownloadListenerImpl(Context context) {
        this.mContext = context;
        DOWNLOAD_START_TOAST = this.mContext.getString(R.string.download_start_toast);
        DOWNLOAD_NO_PERMISSION_TOAST = this.mContext.getString(R.string.download_no_permission_toast);
        DOWNLOAD_ALREADY_EXISTS_TOAST = this.mContext.getString(R.string.download_already_exists_toast);
        DOWNLOAD_FAILED_TOAST = this.mContext.getString(R.string.download_failed_toast);
        DOWNLOAD_FINISHED_TOAST = this.mContext.getString(R.string.download_finished_toast);
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        String fileName = getFileName(url, contentDisposition, mimetype);
        if (checkWriteExternalPermission()) {
            Uri src = Uri.parse(url);
            if (src.getScheme().equals("http") || src.getScheme().equals("https")) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                getDownloadManager().enqueue(request);
                popupMessages(DOWNLOAD_START_TOAST + fileName);
                return;
            }
            new FileTransfer(url, fileName).execute(new Void[0]);
        }
    }

    private String getFileName(String url, String contentDisposition, String mimetype) {
        String extension;
        String fileName = URLUtil.guessFileName(url, contentDisposition, mimetype);
        int extensionIndex = fileName.lastIndexOf(".");
        String extension2 = null;
        if (extensionIndex > 1 && extensionIndex < fileName.length()) {
            extension2 = fileName.substring(extensionIndex + 1);
        }
        if (extension2 != null || (extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimetype)) == null) {
            return fileName;
        }
        return fileName + "." + extension;
    }

    private DownloadManager getDownloadManager() {
        return (DownloadManager) this.mContext.getSystemService("download");
    }

    private boolean checkWriteExternalPermission() {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        popupMessages(DOWNLOAD_NO_PERMISSION_TOAST);
        return false;
    }

    /* access modifiers changed from: private */
    public void popupMessages(String message) {
        Toast.makeText(this.mContext, message, 0).show();
    }

    private class FileTransfer extends AsyncTask<Void, Void, String> {
        String fileName;
        String url;

        public FileTransfer(String url2, String fileName2) {
            this.url = url2;
            this.fileName = fileName2;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x004c A[SYNTHETIC, Splitter:B:23:0x004c] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0051 A[Catch:{ IOException -> 0x0055 }] */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x005f A[SYNTHETIC, Splitter:B:31:0x005f] */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0064 A[Catch:{ IOException -> 0x0068 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.Void... r9) {
            /*
                r8 = this;
                r2 = 0
                r5 = 0
                java.lang.String r6 = android.os.Environment.DIRECTORY_DOWNLOADS
                java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r6)
                java.io.File r1 = new java.io.File
                java.lang.String r6 = r8.fileName
                r1.<init>(r0, r6)
                boolean r6 = r1.exists()
                if (r6 == 0) goto L_0x0018
                java.lang.String r6 = "Existed"
            L_0x0017:
                return r6
            L_0x0018:
                java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0046 }
                r3.<init>(r1)     // Catch:{ IOException -> 0x0046 }
                org.xwalk.core.internal.XWalkDownloadListenerImpl r6 = org.xwalk.core.internal.XWalkDownloadListenerImpl.this     // Catch:{ IOException -> 0x0072, all -> 0x006f }
                android.content.Context r6 = r6.mContext     // Catch:{ IOException -> 0x0072, all -> 0x006f }
                java.lang.String r7 = r8.url     // Catch:{ IOException -> 0x0072, all -> 0x006f }
                java.io.InputStream r5 = org.xwalk.core.internal.AndroidProtocolHandler.open(r6, r7)     // Catch:{ IOException -> 0x0072, all -> 0x006f }
                if (r3 == 0) goto L_0x0030
                if (r5 == 0) goto L_0x0030
                r8.streamTransfer(r5, r3)     // Catch:{ IOException -> 0x0072, all -> 0x006f }
            L_0x0030:
                if (r5 == 0) goto L_0x0035
                r5.close()     // Catch:{ IOException -> 0x003e }
            L_0x0035:
                if (r3 == 0) goto L_0x003a
                r3.close()     // Catch:{ IOException -> 0x003e }
            L_0x003a:
                r2 = r3
            L_0x003b:
                java.lang.String r6 = "Finished"
                goto L_0x0017
            L_0x003e:
                r4 = move-exception
                r4.printStackTrace()
                java.lang.String r6 = "Failed"
                r2 = r3
                goto L_0x0017
            L_0x0046:
                r4 = move-exception
            L_0x0047:
                r4.printStackTrace()     // Catch:{ all -> 0x005c }
                if (r5 == 0) goto L_0x004f
                r5.close()     // Catch:{ IOException -> 0x0055 }
            L_0x004f:
                if (r2 == 0) goto L_0x003b
                r2.close()     // Catch:{ IOException -> 0x0055 }
                goto L_0x003b
            L_0x0055:
                r4 = move-exception
                r4.printStackTrace()
                java.lang.String r6 = "Failed"
                goto L_0x0017
            L_0x005c:
                r6 = move-exception
            L_0x005d:
                if (r5 == 0) goto L_0x0062
                r5.close()     // Catch:{ IOException -> 0x0068 }
            L_0x0062:
                if (r2 == 0) goto L_0x0067
                r2.close()     // Catch:{ IOException -> 0x0068 }
            L_0x0067:
                throw r6
            L_0x0068:
                r4 = move-exception
                r4.printStackTrace()
                java.lang.String r6 = "Failed"
                goto L_0x0017
            L_0x006f:
                r6 = move-exception
                r2 = r3
                goto L_0x005d
            L_0x0072:
                r4 = move-exception
                r2 = r3
                goto L_0x0047
            */
            throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.XWalkDownloadListenerImpl.FileTransfer.doInBackground(java.lang.Void[]):java.lang.String");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String result) {
            if (result.equals(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED)) {
                XWalkDownloadListenerImpl.this.popupMessages(XWalkDownloadListenerImpl.DOWNLOAD_FAILED_TOAST);
            } else if (result.equals("Existed")) {
                XWalkDownloadListenerImpl.this.popupMessages(XWalkDownloadListenerImpl.DOWNLOAD_ALREADY_EXISTS_TOAST);
            } else if (result.equals("Finished")) {
                XWalkDownloadListenerImpl.this.popupMessages(XWalkDownloadListenerImpl.DOWNLOAD_FINISHED_TOAST);
            }
        }

        private void streamTransfer(InputStream src, OutputStream dst) throws IOException {
            byte[] buf = new byte[1024];
            while (true) {
                int length = src.read(buf);
                if (length > 0) {
                    dst.write(buf, 0, length);
                } else {
                    return;
                }
            }
        }
    }
}
