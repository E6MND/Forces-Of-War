package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.internal.gu;
import com.google.android.gms.internal.gv;
import com.google.android.gms.internal.gw;
import com.google.android.gms.internal.gx;
import com.google.android.gms.internal.gy;
import com.google.android.gms.internal.hl;
import java.lang.ref.WeakReference;

public abstract class a {
    final C0013a Fj;
    protected int Fk = 0;
    protected int Fl = 0;
    private boolean Fm = true;
    private boolean Fn = false;
    protected int Fo;

    /* renamed from: com.google.android.gms.common.images.a$a  reason: collision with other inner class name */
    static final class C0013a {
        public final Uri uri;

        public C0013a(Uri uri2) {
            this.uri = uri2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0013a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return hl.equal(((C0013a) obj).uri, this.uri);
        }

        public int hashCode() {
            return hl.hashCode(this.uri);
        }
    }

    public static final class b extends a {
        private WeakReference<ImageView> Fp;

        public b(ImageView imageView, int i) {
            super((Uri) null, i);
            gy.c(imageView);
            this.Fp = new WeakReference<>(imageView);
        }

        public b(ImageView imageView, Uri uri) {
            super(uri, 0);
            gy.c(imageView);
            this.Fp = new WeakReference<>(imageView);
        }

        private void a(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof gw)) {
                int fd = ((gw) imageView).fd();
                if (this.Fl != 0 && fd == this.Fl) {
                    return;
                }
            }
            boolean b = b(z, z2);
            Drawable a = b ? a(imageView.getDrawable(), drawable) : drawable;
            imageView.setImageDrawable(a);
            if (imageView instanceof gw) {
                gw gwVar = (gw) imageView;
                gwVar.f(z3 ? this.Fj.uri : null);
                gwVar.al(z4 ? this.Fl : 0);
            }
            if (b) {
                ((gu) a).startTransition(250);
            }
        }

        /* access modifiers changed from: protected */
        public void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.Fp.get();
            if (imageView != null) {
                a(imageView, drawable, z, z2, z3);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.Fp.get();
            ImageView imageView2 = (ImageView) ((b) obj).Fp.get();
            return (imageView2 == null || imageView == null || !hl.equal(imageView2, imageView)) ? false : true;
        }

        public int hashCode() {
            return 0;
        }
    }

    public static final class c extends a {
        private WeakReference<ImageManager.OnImageLoadedListener> Fq;

        public c(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            gy.c(onImageLoadedListener);
            this.Fq = new WeakReference<>(onImageLoadedListener);
        }

        /* access modifiers changed from: protected */
        public void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageManager.OnImageLoadedListener onImageLoadedListener;
            if (!z2 && (onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.Fq.get()) != null) {
                onImageLoadedListener.onImageLoaded(this.Fj.uri, drawable, z3);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            c cVar = (c) obj;
            ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.Fq.get();
            ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) cVar.Fq.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && hl.equal(onImageLoadedListener2, onImageLoadedListener) && hl.equal(cVar.Fj, this.Fj);
        }

        public int hashCode() {
            return hl.hashCode(this.Fj);
        }
    }

    public a(Uri uri, int i) {
        this.Fj = new C0013a(uri);
        this.Fl = i;
    }

    private Drawable a(Context context, gx gxVar, int i) {
        Resources resources = context.getResources();
        if (this.Fo <= 0) {
            return resources.getDrawable(i);
        }
        gx.a aVar = new gx.a(i, this.Fo);
        Drawable drawable = (Drawable) gxVar.get(aVar);
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = resources.getDrawable(i);
        if ((this.Fo & 1) != 0) {
            drawable2 = a(resources, drawable2);
        }
        gxVar.put(aVar, drawable2);
        return drawable2;
    }

    /* access modifiers changed from: protected */
    public Drawable a(Resources resources, Drawable drawable) {
        return gv.a(resources, drawable);
    }

    /* access modifiers changed from: protected */
    public gu a(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof gu) {
            drawable = ((gu) drawable).fb();
        }
        return new gu(drawable, drawable2);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, Bitmap bitmap, boolean z) {
        gy.c(bitmap);
        if ((this.Fo & 1) != 0) {
            bitmap = gv.a(bitmap);
        }
        a(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, gx gxVar) {
        Drawable drawable = null;
        if (this.Fk != 0) {
            drawable = a(context, gxVar, this.Fk);
        }
        a(drawable, false, true, false);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, gx gxVar, boolean z) {
        Drawable drawable = null;
        if (this.Fl != 0) {
            drawable = a(context, gxVar, this.Fl);
        }
        a(drawable, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Drawable drawable, boolean z, boolean z2, boolean z3);

    public void aj(int i) {
        this.Fl = i;
    }

    /* access modifiers changed from: protected */
    public boolean b(boolean z, boolean z2) {
        return this.Fm && !z2 && (!z || this.Fn);
    }
}
