package com.google.android.gms.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.ho;
import com.google.android.gms.internal.hp;

public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int Dg;
    private View Dh;
    private View.OnClickListener Di;
    private int mSize;

    public SignInButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public SignInButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignInButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.Di = null;
        setStyle(0, 0);
    }

    private static Button a(Context context, int i, int i2) {
        hp hpVar = new hp(context);
        hpVar.a(context.getResources(), i, i2);
        return hpVar;
    }

    private void z(Context context) {
        if (this.Dh != null) {
            removeView(this.Dh);
        }
        try {
            this.Dh = ho.b(context, this.mSize, this.Dg);
        } catch (g.a e) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            this.Dh = a(context, this.mSize, this.Dg);
        }
        addView(this.Dh);
        this.Dh.setEnabled(isEnabled());
        this.Dh.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.Di != null && view == this.Dh) {
            this.Di.onClick(this);
        }
    }

    public void setColorScheme(int colorScheme) {
        setStyle(this.mSize, colorScheme);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.Dh.setEnabled(enabled);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.Di = listener;
        if (this.Dh != null) {
            this.Dh.setOnClickListener(this);
        }
    }

    public void setSize(int buttonSize) {
        setStyle(buttonSize, this.Dg);
    }

    public void setStyle(int buttonSize, int colorScheme) {
        hn.a(buttonSize >= 0 && buttonSize < 3, "Unknown button size %d", Integer.valueOf(buttonSize));
        hn.a(colorScheme >= 0 && colorScheme < 2, "Unknown color scheme %s", Integer.valueOf(colorScheme));
        this.mSize = buttonSize;
        this.Dg = colorScheme;
        z(getContext());
    }
}
