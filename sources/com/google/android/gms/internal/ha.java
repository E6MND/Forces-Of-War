package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ha implements DialogInterface.OnClickListener {
    private final Fragment FS;
    private final int FT;
    private final Intent mIntent;
    private final Activity oe;

    public ha(Activity activity, Intent intent, int i) {
        this.oe = activity;
        this.FS = null;
        this.mIntent = intent;
        this.FT = i;
    }

    public ha(Fragment fragment, Intent intent, int i) {
        this.oe = null;
        this.FS = fragment;
        this.mIntent = intent;
        this.FT = i;
    }

    public void onClick(DialogInterface dialog, int which) {
        try {
            if (this.mIntent != null && this.FS != null) {
                this.FS.startActivityForResult(this.mIntent, this.FT);
            } else if (this.mIntent != null) {
                this.oe.startActivityForResult(this.mIntent, this.FT);
            }
            dialog.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
