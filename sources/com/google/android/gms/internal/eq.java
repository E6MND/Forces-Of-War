package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.Map;

public final class eq {
    /* access modifiers changed from: private */
    public final Context mContext;
    private int mState;
    private String sl;
    private final float sm;
    private float sn;
    private float so;
    private float sp;

    public eq(Context context) {
        this.mState = 0;
        this.mContext = context;
        this.sm = context.getResources().getDisplayMetrics().density;
    }

    public eq(Context context, String str) {
        this(context);
        this.sl = str;
    }

    private void a(int i, float f, float f2) {
        if (i == 0) {
            this.mState = 0;
            this.sn = f;
            this.so = f2;
            this.sp = f2;
        } else if (this.mState == -1) {
        } else {
            if (i == 2) {
                if (f2 > this.so) {
                    this.so = f2;
                } else if (f2 < this.sp) {
                    this.sp = f2;
                }
                if (this.so - this.sp > 30.0f * this.sm) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (f - this.sn >= 50.0f * this.sm) {
                        this.sn = f;
                        this.mState++;
                    }
                } else if ((this.mState == 1 || this.mState == 3) && f - this.sn <= -50.0f * this.sm) {
                    this.sn = f;
                    this.mState++;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (f > this.sn) {
                        this.sn = f;
                    }
                } else if (this.mState == 2 && f < this.sn) {
                    this.sn = f;
                }
            } else if (i == 1 && this.mState == 4) {
                showDialog();
            }
        }
    }

    private void showDialog() {
        final String str;
        if (!TextUtils.isEmpty(this.sl)) {
            Uri build = new Uri.Builder().encodedQuery(this.sl).build();
            StringBuilder sb = new StringBuilder();
            Map<String, String> b = ep.b(build);
            for (String next : b.keySet()) {
                sb.append(next).append(" = ").append(b.get(next)).append("\n\n");
            }
            str = sb.toString().trim();
            if (TextUtils.isEmpty(str)) {
                str = "No debug information";
            }
        } else {
            str = "No debug information";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setMessage(str);
        builder.setTitle("Ad Information");
        builder.setPositiveButton("Share", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                eq.this.mContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", str), "Share via"));
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    public void c(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    public void x(String str) {
        this.sl = str;
    }
}
