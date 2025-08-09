package org.xwalk.core;

import android.app.Activity;
import android.content.res.Resources;

public abstract class XWalkActivity extends Activity {
    public Resources getResources() {
        return getApplicationContext().getResources();
    }
}
