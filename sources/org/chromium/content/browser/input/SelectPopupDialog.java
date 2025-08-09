package org.chromium.content.browser.input;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import org.chromium.content.R;
import org.chromium.content.browser.ContentViewCore;

public class SelectPopupDialog implements SelectPopup {
    private static final int[] SELECT_DIALOG_ATTRS = {R.attr.select_dialog_multichoice, R.attr.select_dialog_singlechoice};
    private final ContentViewCore mContentViewCore;
    private final Context mContext = this.mContentViewCore.getContext();
    /* access modifiers changed from: private */
    public final AlertDialog mListBoxPopup;
    private boolean mSelectionNotified;

    public SelectPopupDialog(ContentViewCore contentViewCore, List<SelectPopupItem> items, boolean multiple, int[] selected) {
        this.mContentViewCore = contentViewCore;
        final ListView listView = new ListView(this.mContext);
        listView.setCacheColorHint(0);
        AlertDialog.Builder b = new AlertDialog.Builder(this.mContext).setView(listView).setCancelable(true).setInverseBackgroundForced(true);
        if (multiple) {
            b.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    SelectPopupDialog.this.notifySelection(SelectPopupDialog.getSelectedIndices(listView));
                }
            });
            b.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    SelectPopupDialog.this.notifySelection((int[]) null);
                }
            });
        }
        this.mListBoxPopup = b.create();
        listView.setAdapter(new SelectPopupAdapter(this.mContext, getSelectDialogLayout(multiple), items));
        listView.setFocusableInTouchMode(true);
        if (multiple) {
            listView.setChoiceMode(2);
            for (int itemChecked : selected) {
                listView.setItemChecked(itemChecked, true);
            }
        } else {
            listView.setChoiceMode(1);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                    SelectPopupDialog.this.notifySelection(SelectPopupDialog.getSelectedIndices(listView));
                    SelectPopupDialog.this.mListBoxPopup.dismiss();
                }
            });
            if (selected.length > 0) {
                listView.setSelection(selected[0]);
                listView.setItemChecked(selected[0], true);
            }
        }
        this.mListBoxPopup.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                SelectPopupDialog.this.notifySelection((int[]) null);
            }
        });
    }

    private int getSelectDialogLayout(boolean isMultiChoice) {
        TypedArray styledAttributes = this.mContext.obtainStyledAttributes(R.style.SelectPopupDialog, SELECT_DIALOG_ATTRS);
        int resourceId = styledAttributes.getResourceId(isMultiChoice ? 0 : 1, 0);
        styledAttributes.recycle();
        return resourceId;
    }

    /* access modifiers changed from: private */
    public static int[] getSelectedIndices(ListView listView) {
        SparseBooleanArray sparseArray = listView.getCheckedItemPositions();
        int selectedCount = 0;
        for (int i = 0; i < sparseArray.size(); i++) {
            if (sparseArray.valueAt(i)) {
                selectedCount++;
            }
        }
        int[] indices = new int[selectedCount];
        int j = 0;
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            if (sparseArray.valueAt(i2)) {
                indices[j] = sparseArray.keyAt(i2);
                j++;
            }
        }
        return indices;
    }

    /* access modifiers changed from: private */
    public void notifySelection(int[] indicies) {
        if (!this.mSelectionNotified) {
            this.mContentViewCore.selectPopupMenuItems(indicies);
            this.mSelectionNotified = true;
        }
    }

    public void show() {
        this.mListBoxPopup.show();
    }

    public void hide() {
        this.mListBoxPopup.cancel();
        notifySelection((int[]) null);
    }
}
