package org.xwalk.core.internal.extension.api.presentation;

import android.app.Presentation;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;
import org.xwalk.core.internal.extension.api.presentation.PresentationView;

public class PresentationViewJBMR1 extends PresentationView implements DialogInterface.OnShowListener, DialogInterface.OnDismissListener {
    private Presentation mPresentation;

    public PresentationViewJBMR1(Context context, Display display) {
        this.mPresentation = new Presentation(context, display);
    }

    public void show() {
        this.mPresentation.show();
    }

    public void dismiss() {
        this.mPresentation.dismiss();
    }

    public void cancel() {
        this.mPresentation.cancel();
    }

    public void setContentView(View contentView) {
        this.mPresentation.setContentView(contentView);
    }

    public Display getDisplay() {
        return this.mPresentation.getDisplay();
    }

    public void setPresentationListener(PresentationView.PresentationListener listener) {
        super.setPresentationListener(listener);
        if (this.mListener != null) {
            this.mPresentation.setOnShowListener(this);
            this.mPresentation.setOnDismissListener(this);
            return;
        }
        this.mPresentation.setOnShowListener((DialogInterface.OnShowListener) null);
        this.mPresentation.setOnDismissListener((DialogInterface.OnDismissListener) null);
    }

    public void onShow(DialogInterface dialog) {
        if (this.mListener != null) {
            this.mListener.onShow(this);
        }
    }

    public void onDismiss(DialogInterface dialog) {
        if (this.mListener != null) {
            this.mListener.onDismiss(this);
        }
    }
}
