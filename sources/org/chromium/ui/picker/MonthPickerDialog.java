package org.chromium.ui.picker;

import android.content.Context;
import org.chromium.ui.R;
import org.chromium.ui.picker.TwoFieldDatePickerDialog;

public class MonthPickerDialog extends TwoFieldDatePickerDialog {
    public MonthPickerDialog(Context context, TwoFieldDatePickerDialog.OnValueSetListener callBack, int year, int monthOfYear, double minMonth, double maxMonth) {
        super(context, callBack, year, monthOfYear, minMonth, maxMonth);
        setTitle(R.string.month_picker_dialog_title);
    }

    /* access modifiers changed from: protected */
    public TwoFieldDatePicker createPicker(Context context, double minValue, double maxValue) {
        return new MonthPicker(context, minValue, maxValue);
    }

    public MonthPicker getMonthPicker() {
        return (MonthPicker) this.mPicker;
    }
}
