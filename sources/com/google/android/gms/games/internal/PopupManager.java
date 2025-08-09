package com.google.android.gms.games.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.internal.iq;
import java.lang.ref.WeakReference;

public class PopupManager {
    protected GamesClientImpl OV;
    protected PopupLocationInfo OW;

    public static final class PopupLocationInfo {
        public IBinder OX;
        public int OY;
        public int bottom;
        public int gravity;
        public int left;
        public int right;
        public int top;

        private PopupLocationInfo(int gravity2, IBinder windowToken) {
            this.OY = -1;
            this.left = 0;
            this.top = 0;
            this.right = 0;
            this.bottom = 0;
            this.gravity = gravity2;
            this.OX = windowToken;
        }

        public Bundle hJ() {
            Bundle bundle = new Bundle();
            bundle.putInt("popupLocationInfo.gravity", this.gravity);
            bundle.putInt("popupLocationInfo.displayId", this.OY);
            bundle.putInt("popupLocationInfo.left", this.left);
            bundle.putInt("popupLocationInfo.top", this.top);
            bundle.putInt("popupLocationInfo.right", this.right);
            bundle.putInt("popupLocationInfo.bottom", this.bottom);
            return bundle;
        }
    }

    private static final class PopupManagerHCMR1 extends PopupManager implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
        private boolean Ns = false;
        private WeakReference<View> OZ;

        protected PopupManagerHCMR1(GamesClientImpl gamesClientImpl, int gravity) {
            super(gamesClientImpl, gravity);
        }

        private void h(View view) {
            Display display;
            int i = -1;
            if (iq.gc() && (display = view.getDisplay()) != null) {
                i = display.getDisplayId();
            }
            IBinder windowToken = view.getWindowToken();
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int width = view.getWidth();
            int height = view.getHeight();
            this.OW.OY = i;
            this.OW.OX = windowToken;
            this.OW.left = iArr[0];
            this.OW.top = iArr[1];
            this.OW.right = iArr[0] + width;
            this.OW.bottom = iArr[1] + height;
            if (this.Ns) {
                hG();
                this.Ns = false;
            }
        }

        /* access modifiers changed from: protected */
        public void cl(int i) {
            this.OW = new PopupLocationInfo(i, (IBinder) null);
        }

        public void g(View view) {
            this.OV.hr();
            if (this.OZ != null) {
                View view2 = (View) this.OZ.get();
                Context context = this.OV.getContext();
                if (view2 == null && (context instanceof Activity)) {
                    view2 = ((Activity) context).getWindow().getDecorView();
                }
                if (view2 != null) {
                    view2.removeOnAttachStateChangeListener(this);
                    ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                    if (iq.gb()) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    } else {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    }
                }
            }
            this.OZ = null;
            Context context2 = this.OV.getContext();
            if (view == null && (context2 instanceof Activity)) {
                View findViewById = ((Activity) context2).findViewById(16908290);
                if (findViewById == null) {
                    findViewById = ((Activity) context2).getWindow().getDecorView();
                }
                GamesLog.j("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
                view = findViewById;
            }
            if (view != null) {
                h(view);
                this.OZ = new WeakReference<>(view);
                view.addOnAttachStateChangeListener(this);
                view.getViewTreeObserver().addOnGlobalLayoutListener(this);
                return;
            }
            GamesLog.k("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
        }

        public void hG() {
            if (this.OW.OX != null) {
                PopupManager.super.hG();
            } else {
                this.Ns = this.OZ != null;
            }
        }

        public void onGlobalLayout() {
            View view;
            if (this.OZ != null && (view = (View) this.OZ.get()) != null) {
                h(view);
            }
        }

        public void onViewAttachedToWindow(View v) {
            h(v);
        }

        public void onViewDetachedFromWindow(View v) {
            this.OV.hr();
            v.removeOnAttachStateChangeListener(this);
        }
    }

    private PopupManager(GamesClientImpl gamesClientImpl, int gravity) {
        this.OV = gamesClientImpl;
        cl(gravity);
    }

    public static PopupManager a(GamesClientImpl gamesClientImpl, int i) {
        return iq.fY() ? new PopupManagerHCMR1(gamesClientImpl, i) : new PopupManager(gamesClientImpl, i);
    }

    /* access modifiers changed from: protected */
    public void cl(int i) {
        this.OW = new PopupLocationInfo(i, new Binder());
    }

    public void g(View view) {
    }

    public void hG() {
        this.OV.a(this.OW.OX, this.OW.hJ());
    }

    public Bundle hH() {
        return this.OW.hJ();
    }

    public IBinder hI() {
        return this.OW.OX;
    }

    public void setGravity(int gravity) {
        this.OW.gravity = gravity;
    }
}
