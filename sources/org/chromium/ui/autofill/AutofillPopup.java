package org.chromium.ui.autofill;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.ui.DropdownAdapter;
import org.chromium.ui.DropdownItem;
import org.chromium.ui.DropdownPopupWindow;
import org.chromium.ui.base.ViewAndroidDelegate;

public class AutofillPopup extends DropdownPopupWindow implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
    static final /* synthetic */ boolean $assertionsDisabled = (!AutofillPopup.class.desiredAssertionStatus());
    private static final int ITEM_ID_SEPARATOR_ENTRY = -3;
    private final AutofillPopupDelegate mAutofillCallback;
    private final Context mContext;
    private List<AutofillSuggestion> mSuggestions;

    public interface AutofillPopupDelegate {
        void dismissed();

        void suggestionSelected(int i);
    }

    public AutofillPopup(Context context, ViewAndroidDelegate viewAndroidDelegate, AutofillPopupDelegate autofillCallback) {
        super(context, viewAndroidDelegate);
        this.mContext = context;
        this.mAutofillCallback = autofillCallback;
        setOnItemClickListener(this);
        setOnDismissListener(this);
    }

    @SuppressLint({"InlinedApi"})
    public void filterAndShow(AutofillSuggestion[] suggestions, boolean isRtl) {
        this.mSuggestions = new ArrayList(Arrays.asList(suggestions));
        ArrayList<DropdownItem> cleanedData = new ArrayList<>();
        HashSet<Integer> separators = new HashSet<>();
        for (int i = 0; i < suggestions.length; i++) {
            if (suggestions[i].getSuggestionId() == -3) {
                separators.add(Integer.valueOf(cleanedData.size()));
            } else {
                cleanedData.add(suggestions[i]);
            }
        }
        setAdapter(new DropdownAdapter(this.mContext, (List<DropdownItem>) cleanedData, (Set<Integer>) separators));
        setRtl(isRtl);
        show();
    }

    public void hide() {
        dismiss();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int listIndex = this.mSuggestions.indexOf(((DropdownAdapter) parent.getAdapter()).getItem(position));
        if ($assertionsDisabled || listIndex > -1) {
            this.mAutofillCallback.suggestionSelected(listIndex);
            return;
        }
        throw new AssertionError();
    }

    public void onDismiss() {
        this.mAutofillCallback.dismissed();
    }
}
