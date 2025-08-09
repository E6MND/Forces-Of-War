package org.chromium.ui.picker;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import java.util.Calendar;
import java.util.TimeZone;
import org.chromium.ui.R;

public abstract class TwoFieldDatePicker extends FrameLayout {
    private Calendar mCurrentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private OnMonthOrWeekChangedListener mMonthOrWeekChangedListener;
    /* access modifiers changed from: private */
    public final NumberPicker mPositionInYearSpinner;
    /* access modifiers changed from: private */
    public final NumberPicker mYearSpinner;

    public interface OnMonthOrWeekChangedListener {
        void onMonthOrWeekChanged(TwoFieldDatePicker twoFieldDatePicker, int i, int i2);
    }

    /* access modifiers changed from: protected */
    public abstract Calendar getDateForValue(double d);

    /* access modifiers changed from: protected */
    public abstract int getMaxPositionInYear(int i);

    /* access modifiers changed from: protected */
    public abstract int getMaxYear();

    /* access modifiers changed from: protected */
    public abstract int getMinPositionInYear(int i);

    /* access modifiers changed from: protected */
    public abstract int getMinYear();

    public abstract int getPositionInYear();

    /* access modifiers changed from: protected */
    public abstract void setCurrentDate(int i, int i2);

    public TwoFieldDatePicker(Context context, double minValue, double maxValue) {
        super(context, (AttributeSet) null, 16843612);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.two_field_date_picker, this, true);
        NumberPicker.OnValueChangeListener onChangeListener = new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int year = TwoFieldDatePicker.this.getYear();
                int positionInYear = TwoFieldDatePicker.this.getPositionInYear();
                if (picker == TwoFieldDatePicker.this.mPositionInYearSpinner) {
                    positionInYear = newVal;
                    if (oldVal == picker.getMaxValue() && newVal == picker.getMinValue()) {
                        year++;
                        positionInYear = TwoFieldDatePicker.this.getMinPositionInYear(year);
                    } else if (oldVal == picker.getMinValue() && newVal == picker.getMaxValue()) {
                        year--;
                        positionInYear = TwoFieldDatePicker.this.getMaxPositionInYear(year);
                    }
                } else if (picker == TwoFieldDatePicker.this.mYearSpinner) {
                    year = newVal;
                } else {
                    throw new IllegalArgumentException();
                }
                TwoFieldDatePicker.this.setCurrentDate(year, positionInYear);
                TwoFieldDatePicker.this.updateSpinners();
                TwoFieldDatePicker.this.notifyDateChanged();
            }
        };
        if (minValue >= maxValue) {
            this.mMinDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.mMinDate.set(0, 0, 1);
            this.mMaxDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.mMaxDate.set(9999, 0, 1);
        } else {
            this.mMinDate = getDateForValue(minValue);
            this.mMaxDate = getDateForValue(maxValue);
        }
        this.mPositionInYearSpinner = (NumberPicker) findViewById(R.id.position_in_year);
        this.mPositionInYearSpinner.setOnLongPressUpdateInterval(200);
        this.mPositionInYearSpinner.setOnValueChangedListener(onChangeListener);
        this.mYearSpinner = (NumberPicker) findViewById(R.id.year);
        this.mYearSpinner.setOnLongPressUpdateInterval(100);
        this.mYearSpinner.setOnValueChangedListener(onChangeListener);
    }

    public void init(int year, int positionInYear, OnMonthOrWeekChangedListener onMonthOrWeekChangedListener) {
        setCurrentDate(year, positionInYear);
        updateSpinners();
        this.mMonthOrWeekChangedListener = onMonthOrWeekChangedListener;
    }

    public boolean isNewDate(int year, int positionInYear) {
        return (getYear() == year && getPositionInYear() == positionInYear) ? false : true;
    }

    public void updateDate(int year, int positionInYear) {
        if (isNewDate(year, positionInYear)) {
            setCurrentDate(year, positionInYear);
            updateSpinners();
            notifyDateChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void setCurrentDate(Calendar date) {
        this.mCurrentDate = date;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        onPopulateAccessibilityEvent(event);
        return true;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);
        event.getText().add(DateUtils.formatDateTime(getContext(), this.mCurrentDate.getTimeInMillis(), 20));
    }

    public int getYear() {
        return this.mCurrentDate.get(1);
    }

    /* access modifiers changed from: protected */
    public Calendar getMaxDate() {
        return this.mMaxDate;
    }

    /* access modifiers changed from: protected */
    public Calendar getMinDate() {
        return this.mMinDate;
    }

    /* access modifiers changed from: protected */
    public Calendar getCurrentDate() {
        return this.mCurrentDate;
    }

    /* access modifiers changed from: protected */
    public NumberPicker getPositionInYearSpinner() {
        return this.mPositionInYearSpinner;
    }

    /* access modifiers changed from: protected */
    public NumberPicker getYearSpinner() {
        return this.mYearSpinner;
    }

    /* access modifiers changed from: protected */
    public void updateSpinners() {
        this.mPositionInYearSpinner.setDisplayedValues((String[]) null);
        this.mPositionInYearSpinner.setMinValue(getMinPositionInYear(getYear()));
        this.mPositionInYearSpinner.setMaxValue(getMaxPositionInYear(getYear()));
        this.mPositionInYearSpinner.setWrapSelectorWheel(!this.mCurrentDate.equals(this.mMinDate) && !this.mCurrentDate.equals(this.mMaxDate));
        this.mYearSpinner.setMinValue(getMinYear());
        this.mYearSpinner.setMaxValue(getMaxYear());
        this.mYearSpinner.setWrapSelectorWheel(false);
        this.mYearSpinner.setValue(getYear());
        this.mPositionInYearSpinner.setValue(getPositionInYear());
    }

    /* access modifiers changed from: protected */
    public void notifyDateChanged() {
        sendAccessibilityEvent(4);
        if (this.mMonthOrWeekChangedListener != null) {
            this.mMonthOrWeekChangedListener.onMonthOrWeekChanged(this, getYear(), getPositionInYear());
        }
    }
}
