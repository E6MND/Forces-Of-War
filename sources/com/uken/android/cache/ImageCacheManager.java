package com.uken.android.cache;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebResourceResponse;
import com.android.volley.Cache;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.uken.android.common.Consts;
import com.uken.android.common.UkenActivity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ImageCacheManager {
    private static String ASSETS_PIPELINE_PATH = "/assets/";
    private static String AWS_PATH = "uwars.s3.amazonaws.com/";
    private static String CONSOLE_PATH = "/assets.";
    public static int IMAGECACHE_QUALITY = 70;
    private static String TAG = "ImageCacheManager";
    private static ImageCacheManager mInstance;
    private AssetManager mAssetManager;
    private ImageLoader.ImageCache mImageCache;
    /* access modifiers changed from: private */
    public ImageLoader mImageLoader;

    public static ImageCacheManager getInstance() {
        if (mInstance == null) {
            mInstance = new ImageCacheManager();
        }
        return mInstance;
    }

    public void init(Context context, String uniqueName, int cacheSize, int quality) {
        this.mImageCache = new BitmapLruImageCache(cacheSize);
        this.mImageLoader = new ImageLoader(RequestManager.getRequestQueue(), this.mImageCache);
        this.mAssetManager = context.getAssets();
    }

    public Bitmap getBitmap(String url) {
        try {
            return this.mImageCache.getBitmap(createKey(url));
        } catch (NullPointerException e) {
            throw new IllegalStateException("Cache Not initialized");
        }
    }

    public void putBitmap(String url, Bitmap bitmap) {
        try {
            this.mImageCache.putBitmap(createKey(url), bitmap);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Cache Not initialized");
        }
    }

    public WebResourceResponse getImageFromCache(String url) {
        if (isImageCacheable(url)) {
            InputStream is = null;
            if (UkenActivity.IMAGES_PRELOADED) {
                is = getInputStreamFromAssets(url);
            }
            if (is == null) {
                Bitmap image = getBitmap(url);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                if (image != null) {
                    if (url.contains(".png")) {
                        image.compress(Bitmap.CompressFormat.PNG, IMAGECACHE_QUALITY, stream);
                    } else {
                        image.compress(Bitmap.CompressFormat.JPEG, IMAGECACHE_QUALITY, stream);
                    }
                    is = new ByteArrayInputStream(stream.toByteArray());
                } else {
                    Cache.Entry entry = RequestManager.getRequestQueue().getCache().get(url);
                    if (entry != null) {
                        Bitmap image2 = BitmapFactory.decodeByteArray(entry.data, 0, entry.data.length);
                        if (url.contains(".png")) {
                            image2.compress(Bitmap.CompressFormat.PNG, IMAGECACHE_QUALITY, stream);
                        } else {
                            image2.compress(Bitmap.CompressFormat.JPEG, IMAGECACHE_QUALITY, stream);
                        }
                        is = new ByteArrayInputStream(stream.toByteArray());
                    }
                }
            }
            if (is != null) {
                return new WebResourceResponse("", "", is);
            }
        }
        return null;
    }

    public void cacheImage(final String url) {
        if (isImageCacheable(url)) {
            getImage(url, new ImageLoader.ImageListener() {
                public void onErrorResponse(VolleyError error) {
                }

                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (response.getBitmap() != null) {
                        ImageCacheManager.this.putBitmap(url, response.getBitmap());
                    }
                }
            });
        }
    }

    public void getImage(final String url, final ImageLoader.ImageListener listener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                ImageCacheManager.this.mImageLoader.get(url, listener);
            }
        });
    }

    public ImageLoader getImageLoader() {
        return this.mImageLoader;
    }

    private String createKey(String url) {
        return String.valueOf(url.hashCode());
    }

    private boolean isImageCacheable(String url) {
        if (!url.contains(ASSETS_PIPELINE_PATH) && !url.contains(CONSOLE_PATH) && !url.contains(AWS_PATH)) {
            return false;
        }
        if (url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".gif") || url.endsWith(".svg")) {
            return true;
        }
        return false;
    }

    private InputStream getInputStreamFromAssets(String url) {
        try {
            String data = new URL(url).getPath();
            if (data.startsWith("/")) {
                data = data.substring(1);
            }
            InputStream is = this.mAssetManager.open(data);
            if (!Consts.DEBUG || is == null) {
                return is;
            }
            Log.d(TAG, "Retrieving preloaded asset: " + data);
            return is;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean clientSupportsImageCache() {
        return Build.VERSION.SDK_INT >= 11 && UkenActivity.IMAGES_PRELOADED;
    }
}
