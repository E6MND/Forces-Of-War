package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.WorkQueue;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static Handler handler;
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    public static void downloadAsync(ImageRequest request) {
        if (request != null) {
            RequestKey key = new RequestKey(request.getImageUri(), request.getCallerTag());
            synchronized (pendingRequests) {
                DownloaderContext downloaderContext = pendingRequests.get(key);
                if (downloaderContext != null) {
                    downloaderContext.request = request;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                } else {
                    enqueueCacheRead(request, key, request.isCachedRedirectAllowed());
                }
            }
        }
    }

    public static boolean cancelRequest(ImageRequest request) {
        boolean cancelled = false;
        RequestKey key = new RequestKey(request.getImageUri(), request.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(key);
            if (downloaderContext != null) {
                cancelled = true;
                if (downloaderContext.workItem.cancel()) {
                    pendingRequests.remove(key);
                } else {
                    downloaderContext.isCancelled = true;
                }
            }
        }
        return cancelled;
    }

    public static void prioritizeRequest(ImageRequest request) {
        RequestKey key = new RequestKey(request.getImageUri(), request.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(key);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache(context);
    }

    private static void enqueueCacheRead(ImageRequest request, RequestKey key, boolean allowCachedRedirects) {
        enqueueRequest(request, key, cacheReadQueue, new CacheReadWorkItem(request.getContext(), key, allowCachedRedirects));
    }

    private static void enqueueDownload(ImageRequest request, RequestKey key) {
        enqueueRequest(request, key, downloadQueue, new DownloadImageWorkItem(request.getContext(), key));
    }

    private static void enqueueRequest(ImageRequest request, RequestKey key, WorkQueue workQueue, Runnable workItem) {
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = request;
            pendingRequests.put(key, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(workItem);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r1 = r6.request;
        r5 = r1.getCallback();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void issueResponse(com.facebook.internal.ImageDownloader.RequestKey r8, java.lang.Exception r9, android.graphics.Bitmap r10, boolean r11) {
        /*
            com.facebook.internal.ImageDownloader$DownloaderContext r6 = removePendingRequest(r8)
            if (r6 == 0) goto L_0x0021
            boolean r0 = r6.isCancelled
            if (r0 != 0) goto L_0x0021
            com.facebook.internal.ImageRequest r1 = r6.request
            com.facebook.internal.ImageRequest$Callback r5 = r1.getCallback()
            if (r5 == 0) goto L_0x0021
            android.os.Handler r7 = getHandler()
            com.facebook.internal.ImageDownloader$1 r0 = new com.facebook.internal.ImageDownloader$1
            r2 = r9
            r3 = r11
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            r7.post(r0)
        L_0x0021:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.issueResponse(com.facebook.internal.ImageDownloader$RequestKey, java.lang.Exception, android.graphics.Bitmap, boolean):void");
    }

    /* access modifiers changed from: private */
    public static void readFromCache(RequestKey key, Context context, boolean allowCachedRedirects) {
        URI redirectUri;
        InputStream cachedStream = null;
        boolean isCachedRedirect = false;
        if (allowCachedRedirects && (redirectUri = UrlRedirectCache.getRedirectedUri(context, key.uri)) != null) {
            cachedStream = ImageResponseCache.getCachedImageStream(redirectUri, context);
            isCachedRedirect = cachedStream != null;
        }
        if (!isCachedRedirect) {
            cachedStream = ImageResponseCache.getCachedImageStream(key.uri, context);
        }
        if (cachedStream != null) {
            Bitmap bitmap = BitmapFactory.decodeStream(cachedStream);
            Utility.closeQuietly(cachedStream);
            issueResponse(key, (Exception) null, bitmap, isCachedRedirect);
            return;
        }
        DownloaderContext downloaderContext = removePendingRequest(key);
        if (downloaderContext != null && !downloaderContext.isCancelled) {
            enqueueDownload(downloaderContext.request, key);
        }
    }

    /* JADX WARNING: type inference failed for: r16v4, types: [java.net.URLConnection] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void download(com.facebook.internal.ImageDownloader.RequestKey r19, android.content.Context r20) {
        /*
            r5 = 0
            r14 = 0
            r8 = 0
            r2 = 0
            r10 = 1
            java.net.URL r15 = new java.net.URL     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r0 = r19
            java.net.URI r0 = r0.uri     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r16 = r0
            java.lang.String r16 = r16.toString()     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r15.<init>(r16)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            java.net.URLConnection r16 = r15.openConnection()     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r0 = r16
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r5 = r0
            r16 = 0
            r0 = r16
            r5.setInstanceFollowRedirects(r0)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            int r16 = r5.getResponseCode()     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            switch(r16) {
                case 200: goto L_0x00b6;
                case 301: goto L_0x006a;
                case 302: goto L_0x006a;
                default: goto L_0x002b;
            }     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
        L_0x002b:
            java.io.InputStream r14 = r5.getErrorStream()     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            java.io.InputStreamReader r11 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r11.<init>(r14)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r16 = 128(0x80, float:1.794E-43)
            r0 = r16
            char[] r3 = new char[r0]     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r9.<init>()     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
        L_0x003f:
            r16 = 0
            int r0 = r3.length     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r17 = r0
            r0 = r16
            r1 = r17
            int r4 = r11.read(r3, r0, r1)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            if (r4 <= 0) goto L_0x00c1
            r16 = 0
            r0 = r16
            r9.append(r3, r0, r4)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            goto L_0x003f
        L_0x0056:
            r7 = move-exception
            r8 = r7
            com.facebook.internal.Utility.closeQuietly(r14)
            com.facebook.internal.Utility.disconnectQuietly(r5)
        L_0x005e:
            if (r10 == 0) goto L_0x0069
            r16 = 0
            r0 = r19
            r1 = r16
            issueResponse(r0, r8, r2, r1)
        L_0x0069:
            return
        L_0x006a:
            r10 = 0
            java.lang.String r16 = "location"
            r0 = r16
            java.lang.String r12 = r5.getHeaderField(r0)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            boolean r16 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r12)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            if (r16 != 0) goto L_0x00af
            java.net.URI r13 = new java.net.URI     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r13.<init>(r12)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r0 = r19
            java.net.URI r0 = r0.uri     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r16 = r0
            r0 = r20
            r1 = r16
            com.facebook.internal.UrlRedirectCache.cacheUriRedirect(r0, r1, r13)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            com.facebook.internal.ImageDownloader$DownloaderContext r6 = removePendingRequest(r19)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            if (r6 == 0) goto L_0x00af
            boolean r0 = r6.isCancelled     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r16 = r0
            if (r16 != 0) goto L_0x00af
            com.facebook.internal.ImageRequest r0 = r6.request     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r16 = r0
            com.facebook.internal.ImageDownloader$RequestKey r17 = new com.facebook.internal.ImageDownloader$RequestKey     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r0 = r19
            java.lang.Object r0 = r0.tag     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r18 = r0
            r0 = r17
            r1 = r18
            r0.<init>(r13, r1)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r18 = 0
            enqueueCacheRead(r16, r17, r18)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
        L_0x00af:
            com.facebook.internal.Utility.closeQuietly(r14)
            com.facebook.internal.Utility.disconnectQuietly(r5)
            goto L_0x005e
        L_0x00b6:
            r0 = r20
            java.io.InputStream r14 = com.facebook.internal.ImageResponseCache.interceptAndCacheImageStream(r0, r5)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r14)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            goto L_0x00af
        L_0x00c1:
            com.facebook.internal.Utility.closeQuietly(r11)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            com.facebook.FacebookException r8 = new com.facebook.FacebookException     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            java.lang.String r16 = r9.toString()     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            r0 = r16
            r8.<init>((java.lang.String) r0)     // Catch:{ IOException -> 0x0056, URISyntaxException -> 0x00d0, all -> 0x00d9 }
            goto L_0x00af
        L_0x00d0:
            r7 = move-exception
            r8 = r7
            com.facebook.internal.Utility.closeQuietly(r14)
            com.facebook.internal.Utility.disconnectQuietly(r5)
            goto L_0x005e
        L_0x00d9:
            r16 = move-exception
            com.facebook.internal.Utility.closeQuietly(r14)
            com.facebook.internal.Utility.disconnectQuietly(r5)
            throw r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.download(com.facebook.internal.ImageDownloader$RequestKey, android.content.Context):void");
    }

    private static synchronized Handler getHandler() {
        Handler handler2;
        synchronized (ImageDownloader.class) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    private static DownloaderContext removePendingRequest(RequestKey key) {
        DownloaderContext remove;
        synchronized (pendingRequests) {
            remove = pendingRequests.remove(key);
        }
        return remove;
    }

    private static class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        URI uri;

        RequestKey(URI url, Object tag2) {
            this.uri = url;
            this.tag = tag2;
        }

        public int hashCode() {
            return ((this.uri.hashCode() + 1073) * HASH_MULTIPLIER) + this.tag.hashCode();
        }

        public boolean equals(Object o) {
            if (o == null || !(o instanceof RequestKey)) {
                return false;
            }
            RequestKey compareTo = (RequestKey) o;
            return compareTo.uri == this.uri && compareTo.tag == this.tag;
        }
    }

    private static class DownloaderContext {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;

        private DownloaderContext() {
        }
    }

    private static class CacheReadWorkItem implements Runnable {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        CacheReadWorkItem(Context context2, RequestKey key2, boolean allowCachedRedirects2) {
            this.context = context2;
            this.key = key2;
            this.allowCachedRedirects = allowCachedRedirects2;
        }

        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }

    private static class DownloadImageWorkItem implements Runnable {
        private Context context;
        private RequestKey key;

        DownloadImageWorkItem(Context context2, RequestKey key2) {
            this.context = context2;
            this.key = key2;
        }

        public void run() {
            ImageDownloader.download(this.key, this.context);
        }
    }
}
