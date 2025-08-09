package org.chromium.components.web_contents_delegate_android;

import android.app.Activity;
import android.content.Context;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.ui.ColorPickerDialog;
import org.chromium.ui.ColorSuggestion;
import org.chromium.ui.OnColorChangedListener;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace("web_contents_delegate_android")
public class ColorChooserAndroid {
    /* access modifiers changed from: private */
    public final ColorPickerDialog mDialog;
    /* access modifiers changed from: private */
    public final long mNativeColorChooserAndroid;

    /* access modifiers changed from: private */
    public native void nativeOnColorChosen(long j, int i);

    private ColorChooserAndroid(long nativeColorChooserAndroid, Context context, int initialColor, ColorSuggestion[] suggestions) {
        OnColorChangedListener listener = new OnColorChangedListener() {
            public void onColorChanged(int color) {
                ColorChooserAndroid.this.mDialog.dismiss();
                ColorChooserAndroid.this.nativeOnColorChosen(ColorChooserAndroid.this.mNativeColorChooserAndroid, color);
            }
        };
        this.mNativeColorChooserAndroid = nativeColorChooserAndroid;
        this.mDialog = new ColorPickerDialog(context, listener, initialColor, suggestions);
    }

    private void openColorChooser() {
        this.mDialog.show();
    }

    @CalledByNative
    public void closeColorChooser() {
        this.mDialog.dismiss();
    }

    @CalledByNative
    public static ColorChooserAndroid createColorChooserAndroid(long nativeColorChooserAndroid, WindowAndroid windowAndroid, int initialColor, ColorSuggestion[] suggestions) {
        Activity windowAndroidActivity = (Activity) windowAndroid.getActivity().get();
        if (windowAndroidActivity == null) {
            return null;
        }
        ColorChooserAndroid chooser = new ColorChooserAndroid(nativeColorChooserAndroid, windowAndroidActivity, initialColor, suggestions);
        chooser.openColorChooser();
        return chooser;
    }

    @CalledByNative
    private static ColorSuggestion[] createColorSuggestionArray(int size) {
        return new ColorSuggestion[size];
    }

    @CalledByNative
    private static void addToColorSuggestionArray(ColorSuggestion[] array, int index, int color, String label) {
        array[index] = new ColorSuggestion(color, label);
    }
}
