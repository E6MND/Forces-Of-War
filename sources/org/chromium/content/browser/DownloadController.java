package org.chromium.content.browser;

import android.content.Context;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.content.browser.DownloadInfo;

@JNINamespace("content")
public class DownloadController {
    private static final String LOGTAG = "DownloadController";
    private static DownloadNotificationService sDownloadNotificationService;
    private static final DownloadController sInstance = new DownloadController();

    public interface DownloadNotificationService {
        void onDownloadCompleted(DownloadInfo downloadInfo);

        void onDownloadUpdated(DownloadInfo downloadInfo);
    }

    private native void nativeInit();

    @CalledByNative
    public static DownloadController getInstance() {
        return sInstance;
    }

    private DownloadController() {
        nativeInit();
    }

    private static ContentViewDownloadDelegate downloadDelegateFromView(ContentViewCore view) {
        return view.getDownloadDelegate();
    }

    public static void setDownloadNotificationService(DownloadNotificationService service) {
        sDownloadNotificationService = service;
    }

    @CalledByNative
    public void newHttpGetDownload(ContentViewCore view, String url, String userAgent, String contentDisposition, String mimeType, String cookie, String referer, boolean hasUserGesture, String filename, long contentLength) {
        ContentViewDownloadDelegate downloadDelegate = downloadDelegateFromView(view);
        if (downloadDelegate != null) {
            downloadDelegate.requestHttpGetDownload(new DownloadInfo.Builder().setUrl(url).setUserAgent(userAgent).setContentDisposition(contentDisposition).setMimeType(mimeType).setCookie(cookie).setReferer(referer).setHasUserGesture(hasUserGesture).setFileName(filename).setContentLength(contentLength).setIsGETRequest(true).build());
        }
    }

    @CalledByNative
    public void onDownloadStarted(ContentViewCore view, String filename, String mimeType) {
        ContentViewDownloadDelegate downloadDelegate = downloadDelegateFromView(view);
        if (downloadDelegate != null) {
            downloadDelegate.onDownloadStarted(filename, mimeType);
        }
    }

    @CalledByNative
    public void onDownloadCompleted(Context context, String url, String mimeType, String filename, String path, long contentLength, boolean successful, int downloadId) {
        if (sDownloadNotificationService != null) {
            sDownloadNotificationService.onDownloadCompleted(new DownloadInfo.Builder().setUrl(url).setMimeType(mimeType).setFileName(filename).setFilePath(path).setContentLength(contentLength).setIsSuccessful(successful).setDescription(filename).setDownloadId(downloadId).setHasDownloadId(true).build());
        }
    }

    @CalledByNative
    public void onDownloadUpdated(Context context, String url, String mimeType, String filename, String path, long contentLength, boolean successful, int downloadId, int percentCompleted, long timeRemainingInMs) {
        if (sDownloadNotificationService != null) {
            sDownloadNotificationService.onDownloadUpdated(new DownloadInfo.Builder().setUrl(url).setMimeType(mimeType).setFileName(filename).setFilePath(path).setContentLength(contentLength).setIsSuccessful(successful).setDescription(filename).setDownloadId(downloadId).setHasDownloadId(true).setPercentCompleted(percentCompleted).setTimeRemainingInMillis(timeRemainingInMs).build());
        }
    }

    @CalledByNative
    public void onDangerousDownload(ContentViewCore view, String filename, int downloadId) {
        ContentViewDownloadDelegate downloadDelegate = downloadDelegateFromView(view);
        if (downloadDelegate != null) {
            downloadDelegate.onDangerousDownload(filename, downloadId);
        }
    }
}
