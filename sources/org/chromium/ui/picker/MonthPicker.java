package org.chromium.ui.picker;

import android.content.Context;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.ui.R;
import org.chromium.ui.picker.TwoFieldDatePicker;

public class MonthPicker extends TwoFieldDatePicker {
    private static final int MONTHS_NUMBER = 12;
    private final String[] mShortMonths = DateFormatSymbols.getInstance(Locale.getDefault()).getShortMonths();

    public MonthPicker(Context context, double minValue, double maxValue) {
        super(context, minValue, maxValue);
        getPositionInYearSpinner().setContentDescription(getResources().getString(R.string.accessibility_date_picker_month));
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        init(cal.get(1), cal.get(2), (TwoFieldDatePicker.OnMonthOrWeekChangedListener) null);
    }

    public static Calendar createDateFromValue(double value) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.clear();
        cal.set((int) Math.min((value / 12.0d) + 1970.0d, 2.147483647E9d), (int) (value % 12.0d), 1);
        return cal;
    }

    /* access modifiers changed from: protected */
    public Calendar getDateForValue(double value) {
        return createDateFromValue(value);
    }

    /* access modifiers changed from: protected */
    public void setCurrentDate(int year, int month) {
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        date.set(year, month, 1);
        if (date.before(getMinDate())) {
            setCurrentDate(getMinDate());
        } else if (date.after(getMaxDate())) {
            setCurrentDate(getMaxDate());
        } else {
            setCurrentDate(date);
        }
    }

    /* access modifiers changed from: protected */
    public void updateSpinners() {
        super.updateSpinners();
        getPositionInYearSpinner().setDisplayedValues((String[]) Arrays.copyOfRange(this.mShortMonths, getPositionInYearSpinner().getMinValue(), getPositionInYearSpinner().getMaxValue() + 1));
    }

    public int getMonth() {
        return getCurrentDate().get(2);
    }

    public int getPositionInYear() {
        return getMonth();
    }

    /* access modifiers changed from: protected */
    public int getMaxYear() {
        return getMaxDate().get(1);
    }

    /* access modifiers changed from: protected */
    public int getMinYear() {
        return getMinDate().get(1);
    }

    /* access modifiers changed from: protected */
    public int getMaxPositionInYear(int year) {
        if (year == getMaxDate().get(1)) {
            return getMaxDate().get(2);
        }
        return 11;
    }

    /* access modifiers changed from: protected */
    public int getMinPositionInYear(int year) {
        if (year == getMinDate().get(1)) {
            return getMinDate().get(2);
        }
        return 0;
    }
}
