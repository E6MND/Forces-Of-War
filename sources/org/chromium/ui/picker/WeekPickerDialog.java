package org.chromium.ui.picker;

import android.content.Context;
import org.chromium.ui.R;
import org.chromium.ui.picker.TwoFieldDatePickerDialog;

public class WeekPickerDialog extends TwoFieldDatePickerDialog {
    public WeekPickerDialog(Context context, TwoFieldDatePickerDialog.OnValueSetListener callBack, int year, int weekOfYear, double minValue, double maxValue) {
        this(context, 0, callBack, year, weekOfYear, minValue, maxValue);
    }

    public WeekPickerDialog(Context context, int theme, TwoFieldDatePickerDialog.OnValueSetListener callBack, int year, int weekOfYear, double minValue, double maxValue) {
        super(context, theme, callBack, year, weekOfYear, minValue, maxValue);
        setTitle(R.string.week_picker_dialog_title);
    }

    /* access modifiers changed from: protected */
    public TwoFieldDatePicker createPicker(Context context, double minValue, double maxValue) {
        return new WeekPicker(context, minValue, maxValue);
    }

    public WeekPicker getWeekPicker() {
        return (WeekPicker) this.mPicker;
    }
}
