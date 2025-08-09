package com.crashlytics.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.ScrollView;
import android.widget.TextView;
import com.crashlytics.android.internal.aQ;

/* renamed from: com.crashlytics.android.p  reason: case insensitive filesystem */
final class C0199p implements Runnable {
    final /* synthetic */ C0204u a;
    final /* synthetic */ Crashlytics b;
    private /* synthetic */ Activity c;
    private /* synthetic */ X d;
    private /* synthetic */ aQ e;

    C0199p(Crashlytics crashlytics, Activity activity, C0204u uVar, X x, aQ aQVar) {
        this.b = crashlytics;
        this.c = activity;
        this.a = uVar;
        this.d = x;
        this.e = aQVar;
    }

    public final void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.c);
        C0200q qVar = new C0200q(this);
        float f = this.c.getResources().getDisplayMetrics().density;
        int a2 = Crashlytics.a(this.b, f, 5);
        TextView textView = new TextView(this.c);
        textView.setAutoLinkMask(15);
        textView.setText(this.d.b());
        textView.setTextAppearance(this.c, 16973892);
        textView.setPadding(a2, a2, a2, a2);
        textView.setFocusable(false);
        ScrollView scrollView = new ScrollView(this.c);
        scrollView.setPadding(Crashlytics.a(this.b, f, 14), Crashlytics.a(this.b, f, 2), Crashlytics.a(this.b, f, 10), Crashlytics.a(this.b, f, 12));
        scrollView.addView(textView);
        builder.setView(scrollView).setTitle(this.d.a()).setCancelable(false).setNeutralButton(this.d.c(), qVar);
        if (this.e.d) {
            builder.setNegativeButton(this.d.e(), new C0201r(this));
        }
        if (this.e.f) {
            builder.setPositiveButton(this.d.d(), new C0202s(this));
        }
        builder.show();
    }
}
