package org.chromium.content.browser.input;

import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import org.chromium.base.VisibleForTesting;

public class AdapterInputConnection extends BaseInputConnection {
    private static final boolean DEBUG = false;
    public static final int INVALID_COMPOSITION = -1;
    public static final int INVALID_SELECTION = -1;
    private static final String TAG = "AdapterInputConnection";
    private final Editable mEditable;
    private final ImeAdapter mImeAdapter;
    private final View mInternalView;
    private int mLastUpdateCompositionEnd = -1;
    private int mLastUpdateCompositionStart = -1;
    private int mLastUpdateSelectionEnd = -1;
    private int mLastUpdateSelectionStart = -1;
    private int mNumNestedBatchEdits = 0;
    private boolean mSingleLine;

    @VisibleForTesting
    AdapterInputConnection(View view, ImeAdapter imeAdapter, Editable editable, EditorInfo outAttrs) {
        super(view, true);
        this.mInternalView = view;
        this.mImeAdapter = imeAdapter;
        this.mImeAdapter.setInputConnection(this);
        this.mEditable = editable;
        removeComposingSpans(this.mEditable);
        this.mSingleLine = true;
        outAttrs.imeOptions = 301989888;
        outAttrs.inputType = 161;
        int inputType = imeAdapter.getTextInputType();
        int inputFlags = imeAdapter.getTextInputFlags();
        if ((ImeAdapter.sTextInputFlagAutocompleteOff & inputFlags) != 0) {
            outAttrs.inputType |= AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END;
        }
        if (inputType == ImeAdapter.sTextInputTypeText) {
            outAttrs.imeOptions |= 2;
            if ((ImeAdapter.sTextInputFlagAutocorrectOff & inputFlags) == 0) {
                outAttrs.inputType |= 32768;
            }
        } else if (inputType == ImeAdapter.sTextInputTypeTextArea || inputType == ImeAdapter.sTextInputTypeContentEditable) {
            outAttrs.inputType |= 147456;
            if ((ImeAdapter.sTextInputFlagAutocorrectOff & inputFlags) == 0) {
                outAttrs.inputType |= 32768;
            }
            outAttrs.imeOptions |= 1;
            this.mSingleLine = false;
        } else if (inputType == ImeAdapter.sTextInputTypePassword) {
            outAttrs.inputType = 225;
            outAttrs.imeOptions |= 2;
        } else if (inputType == ImeAdapter.sTextInputTypeSearch) {
            outAttrs.imeOptions |= 3;
        } else if (inputType == ImeAdapter.sTextInputTypeUrl) {
            outAttrs.inputType = 17;
            outAttrs.imeOptions |= 2;
        } else if (inputType == ImeAdapter.sTextInputTypeEmail) {
            outAttrs.inputType = 209;
            outAttrs.imeOptions |= 2;
        } else if (inputType == ImeAdapter.sTextInputTypeTel) {
            outAttrs.inputType = 3;
            outAttrs.imeOptions |= 5;
        } else if (inputType == ImeAdapter.sTextInputTypeNumber) {
            outAttrs.inputType = FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;
            outAttrs.imeOptions |= 5;
        }
        outAttrs.initialSelStart = Selection.getSelectionStart(this.mEditable);
        outAttrs.initialSelEnd = Selection.getSelectionEnd(this.mEditable);
        this.mLastUpdateSelectionStart = Selection.getSelectionStart(this.mEditable);
        this.mLastUpdateSelectionEnd = Selection.getSelectionEnd(this.mEditable);
        Selection.setSelection(this.mEditable, outAttrs.initialSelStart, outAttrs.initialSelEnd);
        updateSelectionIfRequired();
    }

    @VisibleForTesting
    public void updateState(String text, int selectionStart, int selectionEnd, int compositionStart, int compositionEnd, boolean isNonImeChange) {
        if (isNonImeChange) {
            String text2 = text.replace(160, ' ');
            int selectionStart2 = Math.min(selectionStart, text2.length());
            int selectionEnd2 = Math.min(selectionEnd, text2.length());
            int compositionStart2 = Math.min(compositionStart, text2.length());
            int compositionEnd2 = Math.min(compositionEnd, text2.length());
            if (!this.mEditable.toString().equals(text2)) {
                this.mEditable.replace(0, this.mEditable.length(), text2);
            }
            Selection.setSelection(this.mEditable, selectionStart2, selectionEnd2);
            if (compositionStart2 == compositionEnd2) {
                removeComposingSpans(this.mEditable);
            } else {
                super.setComposingRegion(compositionStart2, compositionEnd2);
            }
            updateSelectionIfRequired();
        }
    }

    public Editable getEditable() {
        return this.mEditable;
    }

    private void updateSelectionIfRequired() {
        if (this.mNumNestedBatchEdits == 0) {
            int selectionStart = Selection.getSelectionStart(this.mEditable);
            int selectionEnd = Selection.getSelectionEnd(this.mEditable);
            int compositionStart = getComposingSpanStart(this.mEditable);
            int compositionEnd = getComposingSpanEnd(this.mEditable);
            if (this.mLastUpdateSelectionStart != selectionStart || this.mLastUpdateSelectionEnd != selectionEnd || this.mLastUpdateCompositionStart != compositionStart || this.mLastUpdateCompositionEnd != compositionEnd) {
                getInputMethodManagerWrapper().updateSelection(this.mInternalView, selectionStart, selectionEnd, compositionStart, compositionEnd);
                this.mLastUpdateSelectionStart = selectionStart;
                this.mLastUpdateSelectionEnd = selectionEnd;
                this.mLastUpdateCompositionStart = compositionStart;
                this.mLastUpdateCompositionEnd = compositionEnd;
            }
        }
    }

    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        if (maybePerformEmptyCompositionWorkaround(text)) {
            return true;
        }
        super.setComposingText(text, newCursorPosition);
        updateSelectionIfRequired();
        return this.mImeAdapter.checkCompositionQueueAndCallNative(text, newCursorPosition, false);
    }

    public boolean commitText(CharSequence text, int newCursorPosition) {
        boolean z = true;
        if (maybePerformEmptyCompositionWorkaround(text)) {
            return true;
        }
        super.commitText(text, newCursorPosition);
        updateSelectionIfRequired();
        ImeAdapter imeAdapter = this.mImeAdapter;
        if (text.length() <= 0) {
            z = false;
        }
        return imeAdapter.checkCompositionQueueAndCallNative(text, newCursorPosition, z);
    }

    public boolean performEditorAction(int actionCode) {
        if (actionCode == 5) {
            restartInput();
            this.mImeAdapter.sendSyntheticKeyEvent(ImeAdapter.sEventTypeRawKeyDown, SystemClock.uptimeMillis(), 61, 0, 0);
            return true;
        }
        this.mImeAdapter.sendKeyEventWithKeyCode(66, 22);
        return true;
    }

    public boolean performContextMenuAction(int id) {
        switch (id) {
            case 16908319:
                return this.mImeAdapter.selectAll();
            case 16908320:
                return this.mImeAdapter.cut();
            case 16908321:
                return this.mImeAdapter.copy();
            case 16908322:
                return this.mImeAdapter.paste();
            default:
                return false;
        }
    }

    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        ExtractedText et = new ExtractedText();
        et.text = this.mEditable.toString();
        et.partialEndOffset = this.mEditable.length();
        et.selectionStart = Selection.getSelectionStart(this.mEditable);
        et.selectionEnd = Selection.getSelectionEnd(this.mEditable);
        et.flags = this.mSingleLine ? 1 : 0;
        return et;
    }

    public boolean beginBatchEdit() {
        this.mNumNestedBatchEdits++;
        return true;
    }

    public boolean endBatchEdit() {
        if (this.mNumNestedBatchEdits == 0) {
            return false;
        }
        this.mNumNestedBatchEdits--;
        if (this.mNumNestedBatchEdits == 0) {
            updateSelectionIfRequired();
        }
        if (this.mNumNestedBatchEdits != 0) {
            return true;
        }
        return false;
    }

    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        int originalBeforeLength = beforeLength;
        int originalAfterLength = afterLength;
        int beforeLength2 = Math.min(beforeLength, Selection.getSelectionStart(this.mEditable));
        int afterLength2 = Math.min(afterLength, this.mEditable.length() - Selection.getSelectionEnd(this.mEditable));
        super.deleteSurroundingText(beforeLength2, afterLength2);
        updateSelectionIfRequired();
        int keyCode = 0;
        if (originalBeforeLength == 1 && originalAfterLength == 0) {
            keyCode = 67;
        } else if (originalBeforeLength == 0 && originalAfterLength == 1) {
            keyCode = 112;
        }
        if (keyCode == 0) {
            return this.mImeAdapter.sendSyntheticKeyEvent(ImeAdapter.sEventTypeRawKeyDown, SystemClock.uptimeMillis(), keyCode, 0, 0) & this.mImeAdapter.deleteSurroundingText(beforeLength2, afterLength2) & this.mImeAdapter.sendSyntheticKeyEvent(ImeAdapter.sEventTypeKeyUp, SystemClock.uptimeMillis(), keyCode, 0, 0);
        }
        this.mImeAdapter.sendKeyEventWithKeyCode(keyCode, 6);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        if (r10.getKeyCode() != 112) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sendKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            r8 = 112(0x70, float:1.57E-43)
            r7 = 67
            r5 = 0
            r6 = 1
            int r4 = r10.getAction()
            if (r4 != r6) goto L_0x0047
            int r4 = r10.getKeyCode()
            if (r4 != r7) goto L_0x0016
            r9.deleteSurroundingText(r6, r5)
        L_0x0015:
            return r6
        L_0x0016:
            int r4 = r10.getKeyCode()
            if (r4 != r8) goto L_0x0020
            r9.deleteSurroundingText(r5, r6)
            goto L_0x0015
        L_0x0020:
            int r3 = r10.getUnicodeChar()
            if (r3 == 0) goto L_0x0041
            android.text.Editable r4 = r9.mEditable
            int r1 = android.text.Selection.getSelectionStart(r4)
            android.text.Editable r4 = r9.mEditable
            int r0 = android.text.Selection.getSelectionEnd(r4)
            if (r1 <= r0) goto L_0x0037
            r2 = r1
            r1 = r0
            r0 = r2
        L_0x0037:
            android.text.Editable r4 = r9.mEditable
            char r5 = (char) r3
            java.lang.String r5 = java.lang.Character.toString(r5)
            r4.replace(r1, r0, r5)
        L_0x0041:
            org.chromium.content.browser.input.ImeAdapter r4 = r9.mImeAdapter
            r4.translateAndSendNativeEvents(r10)
            goto L_0x0015
        L_0x0047:
            int r4 = r10.getAction()
            if (r4 != 0) goto L_0x0041
            int r4 = r10.getKeyCode()
            r5 = 66
            if (r4 != r5) goto L_0x0064
            r9.beginBatchEdit()
            r9.finishComposingText()
            org.chromium.content.browser.input.ImeAdapter r4 = r9.mImeAdapter
            r4.translateAndSendNativeEvents(r10)
            r9.endBatchEdit()
            goto L_0x0015
        L_0x0064:
            int r4 = r10.getKeyCode()
            if (r4 == r7) goto L_0x0015
            int r4 = r10.getKeyCode()
            if (r4 != r8) goto L_0x0041
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.AdapterInputConnection.sendKeyEvent(android.view.KeyEvent):boolean");
    }

    public boolean finishComposingText() {
        if (getComposingSpanStart(this.mEditable) != getComposingSpanEnd(this.mEditable)) {
            super.finishComposingText();
            updateSelectionIfRequired();
            this.mImeAdapter.finishComposingText();
        }
        return true;
    }

    public boolean setSelection(int start, int end) {
        int textLength = this.mEditable.length();
        if (start < 0 || end < 0 || start > textLength || end > textLength) {
            return true;
        }
        super.setSelection(start, end);
        updateSelectionIfRequired();
        return this.mImeAdapter.setEditableSelectionOffsets(start, end);
    }

    /* access modifiers changed from: package-private */
    public void restartInput() {
        getInputMethodManagerWrapper().restartInput(this.mInternalView);
        this.mNumNestedBatchEdits = 0;
    }

    public boolean setComposingRegion(int start, int end) {
        int textLength = this.mEditable.length();
        int a = Math.min(start, end);
        int b = Math.max(start, end);
        if (a < 0) {
            a = 0;
        }
        if (b < 0) {
            b = 0;
        }
        if (a > textLength) {
            a = textLength;
        }
        if (b > textLength) {
            b = textLength;
        }
        if (a == b) {
            removeComposingSpans(this.mEditable);
        } else {
            super.setComposingRegion(a, b);
        }
        updateSelectionIfRequired();
        CharSequence regionText = null;
        if (b > a) {
            regionText = this.mEditable.subSequence(a, b);
        }
        return this.mImeAdapter.setComposingRegion(regionText, a, b);
    }

    /* access modifiers changed from: package-private */
    public boolean isActive() {
        return getInputMethodManagerWrapper().isActive(this.mInternalView);
    }

    private InputMethodManagerWrapper getInputMethodManagerWrapper() {
        return this.mImeAdapter.getInputMethodManagerWrapper();
    }

    private boolean maybePerformEmptyCompositionWorkaround(CharSequence text) {
        int selectionStart = Selection.getSelectionStart(this.mEditable);
        int selectionEnd = Selection.getSelectionEnd(this.mEditable);
        int compositionStart = getComposingSpanStart(this.mEditable);
        int compositionEnd = getComposingSpanEnd(this.mEditable);
        if (!TextUtils.isEmpty(text) || selectionStart != selectionEnd || compositionStart == -1 || compositionEnd == -1) {
            return false;
        }
        beginBatchEdit();
        finishComposingText();
        int selection = Selection.getSelectionStart(this.mEditable);
        deleteSurroundingText(selection - compositionStart, selection - compositionEnd);
        endBatchEdit();
        return true;
    }

    @VisibleForTesting
    static class ImeState {
        public final int compositionEnd;
        public final int compositionStart;
        public final int selectionEnd;
        public final int selectionStart;
        public final String text;

        public ImeState(String text2, int selectionStart2, int selectionEnd2, int compositionStart2, int compositionEnd2) {
            this.text = text2;
            this.selectionStart = selectionStart2;
            this.selectionEnd = selectionEnd2;
            this.compositionStart = compositionStart2;
            this.compositionEnd = compositionEnd2;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ImeState getImeStateForTesting() {
        return new ImeState(this.mEditable.toString(), Selection.getSelectionStart(this.mEditable), Selection.getSelectionEnd(this.mEditable), getComposingSpanStart(this.mEditable), getComposingSpanEnd(this.mEditable));
    }
}
