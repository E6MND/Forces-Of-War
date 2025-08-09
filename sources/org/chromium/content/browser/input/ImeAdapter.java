package org.chromium.content.browser.input;

import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.UnderlineSpan;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.VisibleForTesting;
import org.chromium.ui.picker.InputDialogContainer;

@JNINamespace("content")
public class ImeAdapter {
    private static final int COMPOSITION_KEY_CODE = 229;
    private static final int INPUT_DISMISS_DELAY = 150;
    static int sEventTypeChar;
    static int sEventTypeKeyUp;
    static int sEventTypeRawKeyDown;
    static KeyCharacterMap sKeyCharacterMap;
    static int sModifierAlt;
    static int sModifierCapsLockOn;
    static int sModifierCtrl;
    static int sModifierNumLockOn;
    static int sModifierShift;
    static char[] sSingleCharArray = new char[1];
    static int sTextInputFlagAutocompleteOff;
    static int sTextInputFlagAutocompleteOn;
    static int sTextInputFlagAutocorrectOff;
    static int sTextInputFlagAutocorrectOn;
    static int sTextInputFlagNone = 0;
    static int sTextInputFlagSpellcheckOff;
    static int sTextInputFlagSpellcheckOn;
    static int sTextInputTypeContentEditable;
    static int sTextInputTypeEmail;
    static int sTextInputTypeNone;
    static int sTextInputTypeNumber;
    static int sTextInputTypePassword;
    static int sTextInputTypeSearch;
    static int sTextInputTypeTel;
    static int sTextInputTypeText;
    static int sTextInputTypeTextArea;
    static int sTextInputTypeUrl;
    private DelayedDismissInput mDismissInput = null;
    private final Handler mHandler;
    private AdapterInputConnection mInputConnection;
    private InputMethodManagerWrapper mInputMethodManagerWrapper;
    @VisibleForTesting
    boolean mIsShowWithoutHideOutstanding = false;
    private String mLastComposeText;
    @VisibleForTesting
    int mLastSyntheticKeyCode;
    private long mNativeImeAdapterAndroid;
    private int mTextInputFlags;
    private int mTextInputType;
    private final ImeAdapterDelegate mViewEmbedder;

    public interface ImeAdapterDelegate {
        View getAttachedView();

        ResultReceiver getNewShowKeyboardReceiver();

        void onDismissInput();

        void onImeEvent();
    }

    private static native void nativeAppendBackgroundColorSpan(long j, int i, int i2, int i3);

    private static native void nativeAppendUnderlineSpan(long j, int i, int i2);

    private native void nativeAttachImeAdapter(long j);

    private native void nativeCommitText(long j, String str);

    private native void nativeCopy(long j);

    private native void nativeCut(long j);

    private native void nativeDeleteSurroundingText(long j, int i, int i2);

    private native void nativeFinishComposingText(long j);

    private native void nativePaste(long j);

    private native void nativeResetImeAdapter(long j);

    private native void nativeSelectAll(long j);

    private native boolean nativeSendKeyEvent(long j, KeyEvent keyEvent, int i, int i2, long j2, int i3, boolean z, int i4);

    private native boolean nativeSendSyntheticKeyEvent(long j, int i, long j2, int i2, int i3, int i4);

    private native void nativeSetComposingRegion(long j, int i, int i2);

    private native void nativeSetComposingText(long j, CharSequence charSequence, String str, int i);

    private native void nativeSetEditableSelectionOffsets(long j, int i, int i2);

    private native void nativeUnselect(long j);

    private class DelayedDismissInput implements Runnable {
        private long mNativeImeAdapter;

        DelayedDismissInput(long nativeImeAdapter) {
            this.mNativeImeAdapter = nativeImeAdapter;
        }

        /* access modifiers changed from: package-private */
        public void detach() {
            this.mNativeImeAdapter = 0;
        }

        public void run() {
            if (this.mNativeImeAdapter != 0) {
                ImeAdapter.this.attach(this.mNativeImeAdapter, ImeAdapter.sTextInputTypeNone, ImeAdapter.sTextInputFlagNone);
            }
            ImeAdapter.this.dismissInput(true);
        }
    }

    public ImeAdapter(InputMethodManagerWrapper wrapper, ImeAdapterDelegate embedder) {
        this.mInputMethodManagerWrapper = wrapper;
        this.mViewEmbedder = embedder;
        this.mHandler = new Handler();
    }

    public static class AdapterInputConnectionFactory {
        public AdapterInputConnection get(View view, ImeAdapter imeAdapter, Editable editable, EditorInfo outAttrs) {
            return new AdapterInputConnection(view, imeAdapter, editable, outAttrs);
        }
    }

    @VisibleForTesting
    public void setInputMethodManagerWrapper(InputMethodManagerWrapper immw) {
        this.mInputMethodManagerWrapper = immw;
    }

    /* access modifiers changed from: package-private */
    public InputMethodManagerWrapper getInputMethodManagerWrapper() {
        return this.mInputMethodManagerWrapper;
    }

    /* access modifiers changed from: package-private */
    public void setInputConnection(AdapterInputConnection inputConnection) {
        this.mInputConnection = inputConnection;
        this.mLastComposeText = null;
    }

    /* access modifiers changed from: package-private */
    public int getTextInputType() {
        return this.mTextInputType;
    }

    /* access modifiers changed from: package-private */
    public int getTextInputFlags() {
        return this.mTextInputFlags;
    }

    public static int getTextInputTypeNone() {
        return sTextInputTypeNone;
    }

    private static int getModifiers(int metaState) {
        int modifiers = 0;
        if ((metaState & 1) != 0) {
            modifiers = 0 | sModifierShift;
        }
        if ((metaState & 2) != 0) {
            modifiers |= sModifierAlt;
        }
        if ((metaState & 4096) != 0) {
            modifiers |= sModifierCtrl;
        }
        if ((1048576 & metaState) != 0) {
            modifiers |= sModifierCapsLockOn;
        }
        if ((2097152 & metaState) != 0) {
            return modifiers | sModifierNumLockOn;
        }
        return modifiers;
    }

    public void updateKeyboardVisibility(long nativeImeAdapter, int textInputType, int textInputFlags, boolean showIfNeeded) {
        this.mHandler.removeCallbacks(this.mDismissInput);
        if (this.mTextInputType == sTextInputTypeNone && !showIfNeeded) {
            return;
        }
        if (this.mNativeImeAdapterAndroid == nativeImeAdapter && this.mTextInputType == textInputType) {
            if (hasInputType() && showIfNeeded) {
                showKeyboard();
            }
        } else if (textInputType == sTextInputTypeNone) {
            this.mDismissInput = new DelayedDismissInput(nativeImeAdapter);
            this.mHandler.postDelayed(this.mDismissInput, 150);
        } else {
            attach(nativeImeAdapter, textInputType, textInputFlags);
            this.mInputMethodManagerWrapper.restartInput(this.mViewEmbedder.getAttachedView());
            if (showIfNeeded) {
                showKeyboard();
            }
        }
    }

    public void attach(long nativeImeAdapter, int textInputType, int textInputFlags) {
        if (this.mNativeImeAdapterAndroid != 0) {
            nativeResetImeAdapter(this.mNativeImeAdapterAndroid);
        }
        this.mNativeImeAdapterAndroid = nativeImeAdapter;
        this.mTextInputType = textInputType;
        this.mTextInputFlags = textInputFlags;
        this.mLastComposeText = null;
        if (nativeImeAdapter != 0) {
            nativeAttachImeAdapter(this.mNativeImeAdapterAndroid);
        }
        if (this.mTextInputType == sTextInputTypeNone) {
            dismissInput(false);
        }
    }

    public void attach(long nativeImeAdapter) {
        attach(nativeImeAdapter, sTextInputTypeNone, sTextInputFlagNone);
    }

    private void showKeyboard() {
        this.mIsShowWithoutHideOutstanding = true;
        this.mInputMethodManagerWrapper.showSoftInput(this.mViewEmbedder.getAttachedView(), 0, this.mViewEmbedder.getNewShowKeyboardReceiver());
    }

    /* access modifiers changed from: private */
    public void dismissInput(boolean unzoomIfNeeded) {
        this.mIsShowWithoutHideOutstanding = false;
        View view = this.mViewEmbedder.getAttachedView();
        if (this.mInputMethodManagerWrapper.isActive(view)) {
            this.mInputMethodManagerWrapper.hideSoftInputFromWindow(view.getWindowToken(), 0, unzoomIfNeeded ? this.mViewEmbedder.getNewShowKeyboardReceiver() : null);
        }
        this.mViewEmbedder.onDismissInput();
    }

    private boolean hasInputType() {
        return this.mTextInputType != sTextInputTypeNone;
    }

    private static boolean isTextInputType(int type) {
        return type != sTextInputTypeNone && !InputDialogContainer.isDialogInputType(type);
    }

    public boolean hasTextInputType() {
        return isTextInputType(this.mTextInputType);
    }

    public boolean isSelectionPassword() {
        return this.mTextInputType == sTextInputTypePassword;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return translateAndSendNativeEvents(event);
    }

    private int shouldSendKeyEventWithKeyCode(String text) {
        if (text.length() != 1) {
            return COMPOSITION_KEY_CODE;
        }
        if (text.equals("\n")) {
            return 66;
        }
        if (text.equals("\t")) {
            return 61;
        }
        return COMPOSITION_KEY_CODE;
    }

    private static KeyEvent androidKeyEventForCharacter(char chr) {
        if (sKeyCharacterMap == null) {
            sKeyCharacterMap = KeyCharacterMap.load(-1);
        }
        sSingleCharArray[0] = chr;
        KeyEvent[] events = sKeyCharacterMap.getEvents(sSingleCharArray);
        if (events == null) {
            return null;
        }
        for (int i = 0; i < events.length; i++) {
            if (events[i].getAction() == 0 && !KeyEvent.isModifierKey(events[i].getKeyCode())) {
                return events[i];
            }
        }
        return null;
    }

    @VisibleForTesting
    public static KeyEvent getTypedKeyEventGuess(String oldtext, String newtext) {
        if (oldtext == null) {
            if (newtext.length() == 1) {
                return androidKeyEventForCharacter(newtext.charAt(0));
            }
            return null;
        } else if (newtext.length() > oldtext.length() && newtext.startsWith(oldtext)) {
            return androidKeyEventForCharacter(newtext.charAt(newtext.length() - 1));
        } else {
            if (oldtext.length() <= newtext.length() || !oldtext.startsWith(newtext)) {
                return null;
            }
            return new KeyEvent(0, 67);
        }
    }

    /* access modifiers changed from: package-private */
    public void sendKeyEventWithKeyCode(int keyCode, int flags) {
        long eventTime = SystemClock.uptimeMillis();
        this.mLastSyntheticKeyCode = keyCode;
        translateAndSendNativeEvents(new KeyEvent(eventTime, eventTime, 0, keyCode, 0, 0, -1, 0, flags));
        translateAndSendNativeEvents(new KeyEvent(SystemClock.uptimeMillis(), eventTime, 1, keyCode, 0, 0, -1, 0, flags));
    }

    /* access modifiers changed from: package-private */
    public boolean checkCompositionQueueAndCallNative(CharSequence text, int newCursorPosition, boolean isCommit) {
        int keyCode;
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        this.mViewEmbedder.onImeEvent();
        String textStr = text.toString();
        int keyCode2 = shouldSendKeyEventWithKeyCode(textStr);
        long timeStampMs = SystemClock.uptimeMillis();
        if (keyCode2 != COMPOSITION_KEY_CODE) {
            sendKeyEventWithKeyCode(keyCode2, 6);
        } else {
            KeyEvent keyEvent = getTypedKeyEventGuess(this.mLastComposeText, textStr);
            int modifiers = 0;
            if (keyEvent != null) {
                keyCode = keyEvent.getKeyCode();
                modifiers = getModifiers(keyEvent.getMetaState());
            } else if (!textStr.equals(this.mLastComposeText)) {
                keyCode = 0;
            } else {
                keyCode = -1;
            }
            if (keyCode <= 0 || !isCommit || this.mLastComposeText != null) {
                if (keyCode >= 0) {
                    nativeSendSyntheticKeyEvent(this.mNativeImeAdapterAndroid, sEventTypeRawKeyDown, timeStampMs, keyCode, modifiers, 0);
                }
                if (isCommit) {
                    nativeCommitText(this.mNativeImeAdapterAndroid, textStr);
                    textStr = null;
                } else {
                    nativeSetComposingText(this.mNativeImeAdapterAndroid, text, textStr, newCursorPosition);
                }
                if (keyCode >= 0) {
                    nativeSendSyntheticKeyEvent(this.mNativeImeAdapterAndroid, sEventTypeKeyUp, timeStampMs, keyCode, modifiers, 0);
                }
                this.mLastSyntheticKeyCode = keyCode;
            } else {
                this.mLastSyntheticKeyCode = keyCode;
                if (translateAndSendNativeEvents(keyEvent)) {
                    if (translateAndSendNativeEvents(KeyEvent.changeAction(keyEvent, 1))) {
                        return true;
                    }
                }
                return false;
            }
        }
        this.mLastComposeText = textStr;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void finishComposingText() {
        this.mLastComposeText = null;
        if (this.mNativeImeAdapterAndroid != 0) {
            nativeFinishComposingText(this.mNativeImeAdapterAndroid);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean translateAndSendNativeEvents(KeyEvent event) {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        int action = event.getAction();
        if (action != 0 && action != 1) {
            return false;
        }
        this.mViewEmbedder.onImeEvent();
        return nativeSendKeyEvent(this.mNativeImeAdapterAndroid, event, event.getAction(), getModifiers(event.getMetaState()), event.getEventTime(), event.getKeyCode(), false, event.getUnicodeChar());
    }

    /* access modifiers changed from: package-private */
    public boolean sendSyntheticKeyEvent(int eventType, long timestampMs, int keyCode, int modifiers, int unicodeChar) {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeSendSyntheticKeyEvent(this.mNativeImeAdapterAndroid, eventType, timestampMs, keyCode, modifiers, unicodeChar);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        this.mViewEmbedder.onImeEvent();
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeDeleteSurroundingText(this.mNativeImeAdapterAndroid, beforeLength, afterLength);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setEditableSelectionOffsets(int start, int end) {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeSetEditableSelectionOffsets(this.mNativeImeAdapterAndroid, start, end);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setComposingRegion(CharSequence text, int start, int end) {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeSetComposingRegion(this.mNativeImeAdapterAndroid, start, end);
        this.mLastComposeText = text != null ? text.toString() : null;
        return true;
    }

    public boolean unselect() {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeUnselect(this.mNativeImeAdapterAndroid);
        return true;
    }

    public boolean selectAll() {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeSelectAll(this.mNativeImeAdapterAndroid);
        return true;
    }

    public boolean cut() {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeCut(this.mNativeImeAdapterAndroid);
        return true;
    }

    public boolean copy() {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativeCopy(this.mNativeImeAdapterAndroid);
        return true;
    }

    public boolean paste() {
        if (this.mNativeImeAdapterAndroid == 0) {
            return false;
        }
        nativePaste(this.mNativeImeAdapterAndroid);
        return true;
    }

    @CalledByNative
    private static void initializeWebInputEvents(int eventTypeRawKeyDown, int eventTypeKeyUp, int eventTypeChar, int modifierShift, int modifierAlt, int modifierCtrl, int modifierCapsLockOn, int modifierNumLockOn) {
        sEventTypeRawKeyDown = eventTypeRawKeyDown;
        sEventTypeKeyUp = eventTypeKeyUp;
        sEventTypeChar = eventTypeChar;
        sModifierShift = modifierShift;
        sModifierAlt = modifierAlt;
        sModifierCtrl = modifierCtrl;
        sModifierCapsLockOn = modifierCapsLockOn;
        sModifierNumLockOn = modifierNumLockOn;
    }

    @CalledByNative
    private static void initializeTextInputTypes(int textInputTypeNone, int textInputTypeText, int textInputTypeTextArea, int textInputTypePassword, int textInputTypeSearch, int textInputTypeUrl, int textInputTypeEmail, int textInputTypeTel, int textInputTypeNumber, int textInputTypeContentEditable) {
        sTextInputTypeNone = textInputTypeNone;
        sTextInputTypeText = textInputTypeText;
        sTextInputTypeTextArea = textInputTypeTextArea;
        sTextInputTypePassword = textInputTypePassword;
        sTextInputTypeSearch = textInputTypeSearch;
        sTextInputTypeUrl = textInputTypeUrl;
        sTextInputTypeEmail = textInputTypeEmail;
        sTextInputTypeTel = textInputTypeTel;
        sTextInputTypeNumber = textInputTypeNumber;
        sTextInputTypeContentEditable = textInputTypeContentEditable;
    }

    @CalledByNative
    private static void initializeTextInputFlags(int textInputFlagAutocompleteOn, int textInputFlagAutocompleteOff, int textInputFlagAutocorrectOn, int textInputFlagAutocorrectOff, int textInputFlagSpellcheckOn, int textInputFlagSpellcheckOff) {
        sTextInputFlagAutocompleteOn = textInputFlagAutocompleteOn;
        sTextInputFlagAutocompleteOff = textInputFlagAutocompleteOff;
        sTextInputFlagAutocorrectOn = textInputFlagAutocorrectOn;
        sTextInputFlagAutocorrectOff = textInputFlagAutocorrectOff;
        sTextInputFlagSpellcheckOn = textInputFlagSpellcheckOn;
        sTextInputFlagSpellcheckOff = textInputFlagSpellcheckOff;
    }

    @CalledByNative
    private void focusedNodeChanged(boolean isEditable) {
        if (this.mInputConnection != null && isEditable) {
            this.mInputConnection.restartInput();
        }
    }

    @CalledByNative
    private void populateUnderlinesFromSpans(CharSequence text, long underlines) {
        if (text instanceof SpannableString) {
            SpannableString spannableString = (SpannableString) text;
            for (CharacterStyle span : (CharacterStyle[]) spannableString.getSpans(0, text.length(), CharacterStyle.class)) {
                if (span instanceof BackgroundColorSpan) {
                    nativeAppendBackgroundColorSpan(underlines, spannableString.getSpanStart(span), spannableString.getSpanEnd(span), ((BackgroundColorSpan) span).getBackgroundColor());
                } else if (span instanceof UnderlineSpan) {
                    nativeAppendUnderlineSpan(underlines, spannableString.getSpanStart(span), spannableString.getSpanEnd(span));
                }
            }
        }
    }

    @CalledByNative
    private void cancelComposition() {
        if (this.mInputConnection != null) {
            this.mInputConnection.restartInput();
        }
        this.mLastComposeText = null;
    }

    @CalledByNative
    private void setCharacterBounds(float[] characterBounds) {
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public void detach() {
        if (this.mDismissInput != null) {
            this.mHandler.removeCallbacks(this.mDismissInput);
            this.mDismissInput.detach();
        }
        this.mNativeImeAdapterAndroid = 0;
        this.mTextInputType = 0;
    }
}
