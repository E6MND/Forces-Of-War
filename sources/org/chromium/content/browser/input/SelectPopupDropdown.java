package org.chromium.content.browser.input;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import java.util.List;
import java.util.Set;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.RenderCoordinates;
import org.chromium.ui.DropdownAdapter;
import org.chromium.ui.DropdownItem;
import org.chromium.ui.DropdownPopupWindow;

public class SelectPopupDropdown implements SelectPopup {
    private final ContentViewCore mContentViewCore;
    private final Context mContext;
    private final DropdownPopupWindow mDropdownPopupWindow;
    private int mInitialSelection = -1;
    private boolean mSelectionNotified;

    public SelectPopupDropdown(ContentViewCore contentViewCore, List<SelectPopupItem> items, Rect bounds, int[] selected) {
        this.mContentViewCore = contentViewCore;
        this.mContext = this.mContentViewCore.getContext();
        this.mDropdownPopupWindow = new DropdownPopupWindow(this.mContext, this.mContentViewCore.getViewAndroidDelegate());
        this.mDropdownPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SelectPopupDropdown.this.notifySelection(new int[]{position});
                SelectPopupDropdown.this.hide();
            }
        });
        if (selected.length > 0) {
            this.mInitialSelection = selected[0];
        }
        this.mDropdownPopupWindow.setAdapter(new DropdownAdapter(this.mContext, (DropdownItem[]) items.toArray(new DropdownItem[items.size()]), (Set<Integer>) null));
        RenderCoordinates renderCoordinates = this.mContentViewCore.getRenderCoordinates();
        float anchorX = renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix((float) bounds.left));
        float anchorY = renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix((float) bounds.top));
        this.mDropdownPopupWindow.setAnchorRect(anchorX, anchorY, renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix((float) bounds.right)) - anchorX, renderCoordinates.fromPixToDip(renderCoordinates.fromLocalCssToPix((float) bounds.bottom)) - anchorY);
        this.mDropdownPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                SelectPopupDropdown.this.notifySelection((int[]) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void notifySelection(int[] indicies) {
        if (!this.mSelectionNotified) {
            this.mContentViewCore.selectPopupMenuItems(indicies);
            this.mSelectionNotified = true;
        }
    }

    public void show() {
        this.mDropdownPopupWindow.show();
        if (this.mInitialSelection >= 0) {
            this.mDropdownPopupWindow.getListView().setSelection(this.mInitialSelection);
        }
    }

    public void hide() {
        this.mDropdownPopupWindow.dismiss();
        notifySelection((int[]) null);
    }
}
