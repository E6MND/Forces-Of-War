package org.chromium.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.ui.base.ViewAndroidDelegate;

public class DropdownPopupWindow extends ListPopupWindow {
    static final /* synthetic */ boolean $assertionsDisabled = (!DropdownPopupWindow.class.desiredAssertionStatus());
    ListAdapter mAdapter;
    private float mAnchorHeight;
    /* access modifiers changed from: private */
    public final View mAnchorView = this.mViewAndroidDelegate.acquireAnchorView();
    private float mAnchorWidth;
    private float mAnchorX;
    private float mAnchorY;
    private final Context mContext;
    /* access modifiers changed from: private */
    public View.OnLayoutChangeListener mLayoutChangeListener;
    /* access modifiers changed from: private */
    public PopupWindow.OnDismissListener mOnDismissListener;
    private boolean mRtl;
    /* access modifiers changed from: private */
    public final ViewAndroidDelegate mViewAndroidDelegate;

    public DropdownPopupWindow(Context context, ViewAndroidDelegate viewAndroidDelegate) {
        super(context, (AttributeSet) null, 0, R.style.DropdownPopupWindow);
        this.mContext = context;
        this.mViewAndroidDelegate = viewAndroidDelegate;
        this.mAnchorView.setId(R.id.dropdown_popup_window);
        this.mAnchorView.setTag(this);
        this.mLayoutChangeListener = new View.OnLayoutChangeListener() {
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (v == DropdownPopupWindow.this.mAnchorView) {
                    DropdownPopupWindow.this.show();
                }
            }
        };
        this.mAnchorView.addOnLayoutChangeListener(this.mLayoutChangeListener);
        super.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                if (DropdownPopupWindow.this.mOnDismissListener != null) {
                    DropdownPopupWindow.this.mOnDismissListener.onDismiss();
                }
                DropdownPopupWindow.this.mAnchorView.removeOnLayoutChangeListener(DropdownPopupWindow.this.mLayoutChangeListener);
                DropdownPopupWindow.this.mAnchorView.setTag((Object) null);
                DropdownPopupWindow.this.mViewAndroidDelegate.releaseAnchorView(DropdownPopupWindow.this.mAnchorView);
            }
        });
        setAnchorView(this.mAnchorView);
        Rect originalPadding = new Rect();
        getBackground().getPadding(originalPadding);
        setVerticalOffset(-originalPadding.top);
    }

    public void setAnchorRect(float x, float y, float width, float height) {
        this.mAnchorWidth = width;
        this.mAnchorHeight = height;
        this.mAnchorX = x;
        this.mAnchorY = y;
        if (this.mAnchorView != null) {
            this.mViewAndroidDelegate.setAnchorViewPosition(this.mAnchorView, this.mAnchorX, this.mAnchorY, this.mAnchorWidth, this.mAnchorHeight);
        }
    }

    public void setAdapter(ListAdapter adapter) {
        this.mAdapter = adapter;
        super.setAdapter(adapter);
    }

    public void show() {
        int i;
        setInputMethodMode(1);
        int contentWidth = measureContentWidth();
        if (((float) contentWidth) / this.mContext.getResources().getDisplayMetrics().density > this.mAnchorWidth) {
            setContentWidth(contentWidth);
            Rect displayFrame = new Rect();
            this.mAnchorView.getWindowVisibleDisplayFrame(displayFrame);
            if (getWidth() > displayFrame.width()) {
                setWidth(displayFrame.width());
            }
        } else {
            setWidth(-2);
        }
        this.mViewAndroidDelegate.setAnchorViewPosition(this.mAnchorView, this.mAnchorX, this.mAnchorY, this.mAnchorWidth, this.mAnchorHeight);
        super.show();
        getListView().setDividerHeight(0);
        ListView listView = getListView();
        if (this.mRtl) {
            i = 1;
        } else {
            i = 0;
        }
        ApiCompatibilityUtils.setLayoutDirection(listView, i);
        try {
            ListPopupWindow.class.getMethod("setForceIgnoreOutsideTouch", new Class[]{Boolean.TYPE}).invoke(this, new Object[]{true});
        } catch (Exception e) {
            Log.e("AutofillPopup", "ListPopupWindow.setForceIgnoreOutsideTouch not found", e);
        }
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        this.mOnDismissListener = listener;
    }

    public void setRtl(boolean isRtl) {
        this.mRtl = isRtl;
    }

    private int measureContentWidth() {
        if ($assertionsDisabled || this.mAdapter != null) {
            int maxWidth = 0;
            View[] itemViews = new View[this.mAdapter.getViewTypeCount()];
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            for (int i = 0; i < this.mAdapter.getCount(); i++) {
                int type = this.mAdapter.getItemViewType(i);
                itemViews[type] = this.mAdapter.getView(i, itemViews[type], (ViewGroup) null);
                View itemView = itemViews[type];
                itemView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                itemView.measure(widthMeasureSpec, heightMeasureSpec);
                maxWidth = Math.max(maxWidth, itemView.getMeasuredWidth());
            }
            return maxWidth;
        }
        throw new AssertionError("Set the adapter before showing the popup.");
    }
}
