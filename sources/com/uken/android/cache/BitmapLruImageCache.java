package com.uken.android.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import com.android.volley.toolbox.ImageLoader;
import com.uken.android.common.Consts;

public class BitmapLruImageCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    private final String TAG = getClass().getSimpleName();

    public BitmapLruImageCache(int maxSize) {
        super(maxSize);
    }

    /* access modifiers changed from: protected */
    public int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    public Bitmap getBitmap(String url) {
        Bitmap image = (Bitmap) get(url);
        if (Consts.DEBUG && image != null) {
            Log.v(this.TAG, "Memory cache hit: " + url);
        }
        return image;
    }

    public void putBitmap(String url, Bitmap bitmap) {
        if (Consts.DEBUG) {
            Log.v(this.TAG, "Caching: " + url);
        }
        put(url, bitmap);
    }
}
