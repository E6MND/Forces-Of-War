package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.media.TransportMediator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public class q {
    private ScrollView a;
    private HorizontalScrollView b;
    private ViewGroup c;
    /* access modifiers changed from: private */
    public LinearLayout d;
    /* access modifiers changed from: private */
    public BaseAdapter e;
    /* access modifiers changed from: private */
    public List<List<View>> f;
    /* access modifiers changed from: private */
    public List<List<View>> g;
    /* access modifiers changed from: private */
    public List<Integer> h;
    /* access modifiers changed from: private */
    public int i;
    private DataSetObserver j = new DataSetObserver() {
        public void onChanged() {
            LinearLayout.LayoutParams layoutParams;
            int childCount = q.this.d.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = q.this.d.getChildAt(i);
                ((List) q.this.g.get(((Integer) q.this.h.get(i)).intValue())).remove(childAt);
                ((List) q.this.f.get(((Integer) q.this.h.get(i)).intValue())).add(childAt);
            }
            q.this.h.clear();
            q.this.d.removeAllViews();
            int count = q.this.e.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                int itemViewType = q.this.e.getItemViewType(i2);
                List list = (List) q.this.g.get(itemViewType);
                List list2 = (List) q.this.f.get(itemViewType);
                q.this.h.add(Integer.valueOf(itemViewType));
                View view = null;
                if (!list2.isEmpty()) {
                    view = (View) list2.get(0);
                    list2.remove(0);
                }
                View view2 = q.this.e.getView(i2, view, q.this.d);
                list.add(view2);
                if (q.this.i == 0) {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-1, -2);
                }
                if (i2 < count - 1) {
                    layoutParams.setMargins(0, 0, 0, 1);
                }
                q.this.d.addView(view2, layoutParams);
            }
            q.this.d.requestLayout();
        }
    };

    public q(Context context, int i2) {
        this.d = new LinearLayout(context);
        this.i = i2;
        this.d.setOrientation(i2);
        if (i2 == 0) {
            this.c = a(context);
        } else {
            this.c = b(context);
        }
        this.c.addView(this.d);
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.d.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent ev) {
                try {
                    View currentFocus = ((Activity) q.this.d.getContext()).getCurrentFocus();
                    if (currentFocus != null) {
                        ((InputMethodManager) q.this.d.getContext().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getApplicationWindowToken(), 0);
                    }
                } catch (Exception e) {
                }
                return false;
            }
        });
    }

    private HorizontalScrollView a(Context context) {
        if (this.b == null) {
            this.b = new HorizontalScrollView(context);
            this.b.setFillViewport(true);
        }
        return this.b;
    }

    private ScrollView b(Context context) {
        if (this.a == null) {
            this.a = new ScrollView(context);
            this.a.setFillViewport(true);
        }
        return this.a;
    }

    public ViewGroup a() {
        return this.c;
    }

    public void a(BaseAdapter baseAdapter) {
        if (this.e != null) {
            this.e.unregisterDataSetObserver(this.j);
        }
        this.e = baseAdapter;
        this.e.registerDataSetObserver(this.j);
        this.d.removeAllViews();
        this.f.clear();
        this.g.clear();
        for (int i2 = 0; i2 < this.e.getViewTypeCount(); i2++) {
            this.f.add(new ArrayList());
            this.g.add(new ArrayList());
        }
        this.h.clear();
        this.e.notifyDataSetChanged();
    }

    public void a(int i2) {
        if (i2 != this.i) {
            this.i = i2;
            this.d.setOrientation(i2);
            this.c.removeView(this.d);
            if (i2 == 0) {
                this.c = a(d());
            } else {
                this.c = b(d());
            }
            this.c.addView(this.d);
        }
    }

    private Context d() {
        return this.d.getContext();
    }

    public LinearLayout b() {
        return this.d;
    }

    public void c() {
        if (this.c == this.a && this.a != null) {
            this.a.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
        } else if (this.c == this.b && this.b != null) {
            this.b.fullScroll(66);
        }
    }
}
