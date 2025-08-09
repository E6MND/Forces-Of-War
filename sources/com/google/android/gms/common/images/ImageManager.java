package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.images.a;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.gy;
import com.google.android.gms.internal.hr;
import com.google.android.gms.internal.iq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    /* access modifiers changed from: private */
    public static final Object EU = new Object();
    /* access modifiers changed from: private */
    public static HashSet<Uri> EV = new HashSet<>();
    private static ImageManager EW;
    private static ImageManager EX;
    /* access modifiers changed from: private */
    public final ExecutorService EY = Executors.newFixedThreadPool(4);
    /* access modifiers changed from: private */
    public final b EZ;
    /* access modifiers changed from: private */
    public final gx Fa;
    /* access modifiers changed from: private */
    public final Map<a, ImageReceiver> Fb;
    /* access modifiers changed from: private */
    public final Map<Uri, ImageReceiver> Fc;
    /* access modifiers changed from: private */
    public final Map<Uri, Long> Fd;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());

    private final class ImageReceiver extends ResultReceiver {
        /* access modifiers changed from: private */
        public final ArrayList<a> Fe = new ArrayList<>();
        private final Uri mUri;

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public void b(a aVar) {
            gy.ay("ImageReceiver.addImageRequest() must be called in the main thread");
            this.Fe.add(aVar);
        }

        public void c(a aVar) {
            gy.ay("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.Fe.remove(aVar);
        }

        public void fa() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }

        public void onReceiveResult(int resultCode, Bundle resultData) {
            ImageManager.this.EY.execute(new c(this.mUri, (ParcelFileDescriptor) resultData.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    private static final class a {
        static int a(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }

    private static final class b extends hr<a.C0013a, Bitmap> {
        public b(Context context) {
            super(A(context));
        }

        private static int A(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (int) (((float) (((!((context.getApplicationInfo().flags & AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START) != 0) || !iq.fX()) ? activityManager.getMemoryClass() : a.a(activityManager)) * AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START)) * 0.33f);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public int sizeOf(a.C0013a aVar, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void entryRemoved(boolean z, a.C0013a aVar, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, aVar, bitmap, bitmap2);
        }
    }

    private final class c implements Runnable {
        private final ParcelFileDescriptor Fg;
        private final Uri mUri;

        public c(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.Fg = parcelFileDescriptor;
        }

        public void run() {
            gy.az("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.Fg != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.Fg.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.mUri, e);
                    z = true;
                }
                try {
                    this.Fg.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new f(this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                Log.w("ImageManager", "Latch interrupted while posting " + this.mUri);
            }
        }
    }

    private final class d implements Runnable {
        private final a Fh;

        public d(a aVar) {
            this.Fh = aVar;
        }

        public void run() {
            gy.ay("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.Fb.get(this.Fh);
            if (imageReceiver != null) {
                ImageManager.this.Fb.remove(this.Fh);
                imageReceiver.c(this.Fh);
            }
            a.C0013a aVar = this.Fh.Fj;
            if (aVar.uri == null) {
                this.Fh.a(ImageManager.this.mContext, ImageManager.this.Fa, true);
                return;
            }
            Bitmap a = ImageManager.this.a(aVar);
            if (a != null) {
                this.Fh.a(ImageManager.this.mContext, a, true);
                return;
            }
            Long l = (Long) ImageManager.this.Fd.get(aVar.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.Fh.a(ImageManager.this.mContext, ImageManager.this.Fa, true);
                    return;
                }
                ImageManager.this.Fd.remove(aVar.uri);
            }
            this.Fh.a(ImageManager.this.mContext, ImageManager.this.Fa);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.Fc.get(aVar.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(aVar.uri);
                ImageManager.this.Fc.put(aVar.uri, imageReceiver2);
            }
            imageReceiver2.b(this.Fh);
            if (!(this.Fh instanceof a.c)) {
                ImageManager.this.Fb.put(this.Fh, imageReceiver2);
            }
            synchronized (ImageManager.EU) {
                if (!ImageManager.EV.contains(aVar.uri)) {
                    ImageManager.EV.add(aVar.uri);
                    imageReceiver2.fa();
                }
            }
        }
    }

    private static final class e implements ComponentCallbacks2 {
        private final b EZ;

        public e(b bVar) {
            this.EZ = bVar;
        }

        public void onConfigurationChanged(Configuration newConfig) {
        }

        public void onLowMemory() {
            this.EZ.evictAll();
        }

        public void onTrimMemory(int level) {
            if (level >= 60) {
                this.EZ.evictAll();
            } else if (level >= 20) {
                this.EZ.trimToSize(this.EZ.size() / 2);
            }
        }
    }

    private final class f implements Runnable {
        private boolean Fi;
        private final CountDownLatch kI;
        private final Bitmap mBitmap;
        private final Uri mUri;

        public f(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.Fi = z;
            this.kI = countDownLatch;
        }

        private void a(ImageReceiver imageReceiver, boolean z) {
            ArrayList a = imageReceiver.Fe;
            int size = a.size();
            for (int i = 0; i < size; i++) {
                a aVar = (a) a.get(i);
                if (z) {
                    aVar.a(ImageManager.this.mContext, this.mBitmap, false);
                } else {
                    ImageManager.this.Fd.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    aVar.a(ImageManager.this.mContext, ImageManager.this.Fa, false);
                }
                if (!(aVar instanceof a.c)) {
                    ImageManager.this.Fb.remove(aVar);
                }
            }
        }

        public void run() {
            gy.ay("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.EZ != null) {
                if (this.Fi) {
                    ImageManager.this.EZ.evictAll();
                    System.gc();
                    this.Fi = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.EZ.put(new a.C0013a(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.Fc.remove(this.mUri);
            if (imageReceiver != null) {
                a(imageReceiver, z);
            }
            this.kI.countDown();
            synchronized (ImageManager.EU) {
                ImageManager.EV.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean withMemoryCache) {
        this.mContext = context.getApplicationContext();
        if (withMemoryCache) {
            this.EZ = new b(this.mContext);
            if (iq.ga()) {
                eX();
            }
        } else {
            this.EZ = null;
        }
        this.Fa = new gx();
        this.Fb = new HashMap();
        this.Fc = new HashMap();
        this.Fd = new HashMap();
    }

    /* access modifiers changed from: private */
    public Bitmap a(a.C0013a aVar) {
        if (this.EZ == null) {
            return null;
        }
        return (Bitmap) this.EZ.get(aVar);
    }

    public static ImageManager a(Context context, boolean z) {
        if (z) {
            if (EX == null) {
                EX = new ImageManager(context, true);
            }
            return EX;
        }
        if (EW == null) {
            EW = new ImageManager(context, false);
        }
        return EW;
    }

    public static ImageManager create(Context context) {
        return a(context, false);
    }

    private void eX() {
        this.mContext.registerComponentCallbacks(new e(this.EZ));
    }

    public void a(a aVar) {
        gy.ay("ImageManager.loadImage() must be called in the main thread");
        new d(aVar).run();
    }

    public void loadImage(ImageView imageView, int resId) {
        a((a) new a.b(imageView, resId));
    }

    public void loadImage(ImageView imageView, Uri uri) {
        a((a) new a.b(imageView, uri));
    }

    public void loadImage(ImageView imageView, Uri uri, int defaultResId) {
        a.b bVar = new a.b(imageView, uri);
        bVar.aj(defaultResId);
        a((a) bVar);
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri) {
        a((a) new a.c(listener, uri));
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri, int defaultResId) {
        a.c cVar = new a.c(listener, uri);
        cVar.aj(defaultResId);
        a((a) cVar);
    }
}
