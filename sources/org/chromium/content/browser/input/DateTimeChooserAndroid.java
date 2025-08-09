package org.chromium.content.browser.input;

import android.app.Activity;
import android.content.Context;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.picker.DateTimeSuggestion;
import org.chromium.ui.picker.InputDialogContainer;

@JNINamespace("content")
class DateTimeChooserAndroid {
    private final InputDialogContainer mInputDialogContainer;
    /* access modifiers changed from: private */
    public final long mNativeDateTimeChooserAndroid;

    /* access modifiers changed from: private */
    public native void nativeCancelDialog(long j);

    /* access modifiers changed from: private */
    public native void nativeReplaceDateTime(long j, double d);

    private DateTimeChooserAndroid(Context context, long nativeDateTimeChooserAndroid) {
        this.mNativeDateTimeChooserAndroid = nativeDateTimeChooserAndroid;
        this.mInputDialogContainer = new InputDialogContainer(context, new InputDialogContainer.InputActionDelegate() {
            public void replaceDateTime(double value) {
                DateTimeChooserAndroid.this.nativeReplaceDateTime(DateTimeChooserAndroid.this.mNativeDateTimeChooserAndroid, value);
            }

            public void cancelDateTimeDialog() {
                DateTimeChooserAndroid.this.nativeCancelDialog(DateTimeChooserAndroid.this.mNativeDateTimeChooserAndroid);
            }
        });
    }

    private void showDialog(int dialogType, double dialogValue, double min, double max, double step, DateTimeSuggestion[] suggestions) {
        this.mInputDialogContainer.showDialog(dialogType, dialogValue, min, max, step, suggestions);
    }

    @CalledByNative
    private static DateTimeChooserAndroid createDateTimeChooser(WindowAndroid windowAndroid, long nativeDateTimeChooserAndroid, int dialogType, double dialogValue, double min, double max, double step, DateTimeSuggestion[] suggestions) {
        Activity windowAndroidActivity = (Activity) windowAndroid.getActivity().get();
        if (windowAndroidActivity == null) {
            return null;
        }
        DateTimeChooserAndroid chooser = new DateTimeChooserAndroid(windowAndroidActivity, nativeDateTimeChooserAndroid);
        chooser.showDialog(dialogType, dialogValue, min, max, step, suggestions);
        return chooser;
    }

    @CalledByNative
    private static DateTimeSuggestion[] createSuggestionsArray(int size) {
        return new DateTimeSuggestion[size];
    }

    @CalledByNative
    private static void setDateTimeSuggestionAt(DateTimeSuggestion[] array, int index, double value, String localizedValue, String label) {
        array[index] = new DateTimeSuggestion(value, localizedValue, label);
    }

    @CalledByNative
    private static void initializeDateInputTypes(int textInputTypeDate, int textInputTypeDateTime, int textInputTypeDateTimeLocal, int textInputTypeMonth, int textInputTypeTime, int textInputTypeWeek) {
        InputDialogContainer.initializeInputTypes(textInputTypeDate, textInputTypeDateTime, textInputTypeDateTimeLocal, textInputTypeMonth, textInputTypeTime, textInputTypeWeek);
    }
}
