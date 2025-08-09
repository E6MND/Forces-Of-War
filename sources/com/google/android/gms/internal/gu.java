package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public final class gu extends Drawable implements Drawable.Callback {
    private b FA;
    private Drawable FB;
    private Drawable FC;
    private boolean FD;
    private boolean FE;
    private boolean FF;
    private int FG;
    private boolean Fm;
    private int Fs;
    private long Ft;
    private int Fu;
    private int Fv;
    private int Fw;
    private int Fx;
    private int Fy;
    private boolean Fz;

    private static final class a extends Drawable {
        /* access modifiers changed from: private */
        public static final a FH = new a();
        private static final C0058a FI = new C0058a();

        /* renamed from: com.google.android.gms.internal.gu$a$a  reason: collision with other inner class name */
        private static final class C0058a extends Drawable.ConstantState {
            private C0058a() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return a.FH;
            }
        }

        private a() {
        }

        public void draw(Canvas canvas) {
        }

        public Drawable.ConstantState getConstantState() {
            return FI;
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int alpha) {
        }

        public void setColorFilter(ColorFilter cf) {
        }
    }

    static final class b extends Drawable.ConstantState {
        int FJ;
        int FK;

        b(b bVar) {
            if (bVar != null) {
                this.FJ = bVar.FJ;
                this.FK = bVar.FK;
            }
        }

        public int getChangingConfigurations() {
            return this.FJ;
        }

        public Drawable newDrawable() {
            return new gu(this);
        }
    }

    public gu(Drawable drawable, Drawable drawable2) {
        this((b) null);
        drawable = drawable == null ? a.FH : drawable;
        this.FB = drawable;
        drawable.setCallback(this);
        this.FA.FK |= drawable.getChangingConfigurations();
        drawable2 = drawable2 == null ? a.FH : drawable2;
        this.FC = drawable2;
        drawable2.setCallback(this);
        this.FA.FK |= drawable2.getChangingConfigurations();
    }

    gu(b bVar) {
        this.Fs = 0;
        this.Fw = 255;
        this.Fy = 0;
        this.Fm = true;
        this.FA = new b(bVar);
    }

    public boolean canConstantState() {
        if (!this.FD) {
            this.FE = (this.FB.getConstantState() == null || this.FC.getConstantState() == null) ? false : true;
            this.FD = true;
        }
        return this.FE;
    }

    public void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.Fs) {
            case 1:
                this.Ft = SystemClock.uptimeMillis();
                this.Fs = 2;
                break;
            case 2:
                if (this.Ft >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.Ft)) / ((float) this.Fx);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.Fs = 0;
                    }
                    this.Fy = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.Fv - this.Fu))) + ((float) this.Fu));
                    break;
                }
                break;
        }
        z2 = z;
        int i = this.Fy;
        boolean z3 = this.Fm;
        Drawable drawable = this.FB;
        Drawable drawable2 = this.FC;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.Fw) {
                drawable2.setAlpha(this.Fw);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.Fw - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.Fw);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.Fw);
        }
        invalidateSelf();
    }

    public Drawable fb() {
        return this.FC;
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.FA.FJ | this.FA.FK;
    }

    public Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.FA.FJ = getChangingConfigurations();
        return this.FA;
    }

    public int getIntrinsicHeight() {
        return Math.max(this.FB.getIntrinsicHeight(), this.FC.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(this.FB.getIntrinsicWidth(), this.FC.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!this.FF) {
            this.FG = Drawable.resolveOpacity(this.FB.getOpacity(), this.FC.getOpacity());
            this.FF = true;
        }
        return this.FG;
    }

    public void invalidateDrawable(Drawable who) {
        Drawable.Callback callback;
        if (iq.fX() && (callback = getCallback()) != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Drawable mutate() {
        if (!this.Fz && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.FB.mutate();
            this.FC.mutate();
            this.Fz = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        this.FB.setBounds(bounds);
        this.FC.setBounds(bounds);
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        Drawable.Callback callback;
        if (iq.fX() && (callback = getCallback()) != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    public void setAlpha(int alpha) {
        if (this.Fy == this.Fw) {
            this.Fy = alpha;
        }
        this.Fw = alpha;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter cf) {
        this.FB.setColorFilter(cf);
        this.FC.setColorFilter(cf);
    }

    public void startTransition(int durationMillis) {
        this.Fu = 0;
        this.Fv = this.Fw;
        this.Fy = 0;
        this.Fx = durationMillis;
        this.Fs = 1;
        invalidateSelf();
    }

    public void unscheduleDrawable(Drawable who, Runnable what) {
        Drawable.Callback callback;
        if (iq.fX() && (callback = getCallback()) != null) {
            callback.unscheduleDrawable(this, what);
        }
    }
}
