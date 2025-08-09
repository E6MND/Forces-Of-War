package org.chromium.content.browser;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import org.chromium.content.R;

public class SelectActionModeCallback implements ActionMode.Callback {
    private final ActionHandler mActionHandler;
    private final Context mContext;
    private boolean mEditable;
    private final boolean mIncognito;
    private boolean mIsPasswordType;

    public interface ActionHandler {
        void copy();

        void cut();

        boolean isSelectionEditable();

        boolean isSelectionPassword();

        boolean isShareAvailable();

        boolean isWebSearchAvailable();

        void onDestroyActionMode();

        void paste();

        void search();

        void selectAll();

        void share();
    }

    protected SelectActionModeCallback(Context context, ActionHandler actionHandler, boolean incognito) {
        this.mContext = context;
        this.mActionHandler = actionHandler;
        this.mIncognito = incognito;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.setTitle((CharSequence) null);
        mode.setSubtitle((CharSequence) null);
        this.mEditable = this.mActionHandler.isSelectionEditable();
        this.mIsPasswordType = this.mActionHandler.isSelectionPassword();
        createActionMenu(mode, menu);
        return true;
    }

    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        boolean isEditableNow = this.mActionHandler.isSelectionEditable();
        boolean isPasswordNow = this.mActionHandler.isSelectionPassword();
        if (this.mEditable == isEditableNow && this.mIsPasswordType == isPasswordNow) {
            return false;
        }
        this.mEditable = isEditableNow;
        this.mIsPasswordType = isPasswordNow;
        menu.clear();
        createActionMenu(mode, menu);
        return true;
    }

    private void createActionMenu(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.select_action_menu, menu);
        if (!this.mEditable || !canPaste()) {
            menu.removeItem(R.id.select_action_menu_paste);
        }
        if (!this.mEditable) {
            menu.removeItem(R.id.select_action_menu_cut);
        }
        if (this.mEditable || !this.mActionHandler.isShareAvailable()) {
            menu.removeItem(R.id.select_action_menu_share);
        }
        if (this.mEditable || this.mIncognito || !this.mActionHandler.isWebSearchAvailable()) {
            menu.removeItem(R.id.select_action_menu_web_search);
        }
        if (this.mIsPasswordType) {
            menu.removeItem(R.id.select_action_menu_copy);
            menu.removeItem(R.id.select_action_menu_cut);
        }
    }

    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.select_action_menu_select_all) {
            this.mActionHandler.selectAll();
        } else if (id == R.id.select_action_menu_cut) {
            this.mActionHandler.cut();
            mode.finish();
        } else if (id == R.id.select_action_menu_copy) {
            this.mActionHandler.copy();
            mode.finish();
        } else if (id == R.id.select_action_menu_paste) {
            this.mActionHandler.paste();
            mode.finish();
        } else if (id == R.id.select_action_menu_share) {
            this.mActionHandler.share();
            mode.finish();
        } else if (id != R.id.select_action_menu_web_search) {
            return false;
        } else {
            this.mActionHandler.search();
            mode.finish();
        }
        return true;
    }

    public void onDestroyActionMode(ActionMode mode) {
        this.mActionHandler.onDestroyActionMode();
    }

    private boolean canPaste() {
        return ((ClipboardManager) getContext().getSystemService("clipboard")).hasPrimaryClip();
    }
}
