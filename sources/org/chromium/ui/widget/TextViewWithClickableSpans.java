package org.chromium.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.PopupMenu;
import android.widget.TextView;

public class TextViewWithClickableSpans extends TextView {
    /* access modifiers changed from: private */
    public AccessibilityManager mAccessibilityManager;

    public TextViewWithClickableSpans(Context context) {
        super(context);
        init();
    }

    public TextViewWithClickableSpans(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewWithClickableSpans(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if (!TextViewWithClickableSpans.this.mAccessibilityManager.isTouchExplorationEnabled()) {
                    return false;
                }
                TextViewWithClickableSpans.this.openDisambiguationMenu();
                return true;
            }
        });
    }

    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (action != 16) {
            return super.performAccessibilityAction(action, arguments);
        }
        handleAccessibilityClick();
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean superResult = super.onTouchEvent(event);
        if (event.getAction() == 1 || !this.mAccessibilityManager.isTouchExplorationEnabled() || touchIntersectsAnyClickableSpans(event)) {
            return superResult;
        }
        handleAccessibilityClick();
        return true;
    }

    private boolean touchIntersectsAnyClickableSpans(MotionEvent event) {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return false;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        int x2 = x - getTotalPaddingLeft();
        int y2 = y - getTotalPaddingTop();
        int x3 = x2 + getScrollX();
        Layout layout = getLayout();
        int off = layout.getOffsetForHorizontal(layout.getLineForVertical(y2 + getScrollY()), (float) x3);
        if (((ClickableSpan[]) ((SpannableString) text).getSpans(off, off, ClickableSpan.class)).length > 0) {
            return true;
        }
        return false;
    }

    private ClickableSpan[] getClickableSpans() {
        CharSequence text = getText();
        if (!(text instanceof SpannableString)) {
            return null;
        }
        SpannableString spannable = (SpannableString) text;
        return (ClickableSpan[]) spannable.getSpans(0, spannable.length(), ClickableSpan.class);
    }

    private void handleAccessibilityClick() {
        ClickableSpan[] clickableSpans = getClickableSpans();
        if (clickableSpans != null && clickableSpans.length != 0) {
            if (clickableSpans.length == 1) {
                clickableSpans[0].onClick(this);
            } else {
                openDisambiguationMenu();
            }
        }
    }

    /* access modifiers changed from: private */
    public void openDisambiguationMenu() {
        ClickableSpan[] clickableSpans = getClickableSpans();
        if (clickableSpans != null && clickableSpans.length != 0) {
            SpannableString spannable = (SpannableString) getText();
            PopupMenu popup = new PopupMenu(getContext(), this);
            Menu menu = popup.getMenu();
            for (final ClickableSpan clickableSpan : clickableSpans) {
                menu.add(spannable.subSequence(spannable.getSpanStart(clickableSpan), spannable.getSpanEnd(clickableSpan))).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        clickableSpan.onClick(TextViewWithClickableSpans.this);
                        return true;
                    }
                });
            }
            popup.show();
        }
    }
}
