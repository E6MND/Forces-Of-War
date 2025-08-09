package org.chromium.ui.picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import java.util.ArrayList;
import org.chromium.ui.R;

public class MultiFieldTimePickerDialog extends AlertDialog implements DialogInterface.OnClickListener {
    private static final int HOUR_IN_MILLIS = 3600000;
    private static final int MINUTE_IN_MILLIS = 60000;
    private static final int SECOND_IN_MILLIS = 1000;
    private final NumberPicker mAmPmSpinner;
    private final int mBaseMilli;
    private final NumberPicker mHourSpinner;
    private final boolean mIs24hourFormat;
    private final OnMultiFieldTimeSetListener mListener;
    private final NumberPicker mMilliSpinner;
    private final NumberPicker mMinuteSpinner;
    private final NumberPicker mSecSpinner;
    private final int mStep;

    public interface OnMultiFieldTimeSetListener {
        void onTimeSet(int i, int i2, int i3, int i4);
    }

    public MultiFieldTimePickerDialog(Context context, int theme, int hour, int minute, int second, int milli, int min, int max, int step, boolean is24hourFormat, OnMultiFieldTimeSetListener listener) {
        super(context, theme);
        this.mListener = listener;
        this.mStep = step;
        this.mIs24hourFormat = is24hourFormat;
        if (min >= max) {
            min = 0;
            max = 86399999;
        }
        step = (step < 0 || step >= 86400000) ? 60000 : step;
        View view = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.multi_field_time_picker_dialog, (ViewGroup) null);
        setView(view);
        this.mHourSpinner = (NumberPicker) view.findViewById(R.id.hour);
        this.mMinuteSpinner = (NumberPicker) view.findViewById(R.id.minute);
        this.mSecSpinner = (NumberPicker) view.findViewById(R.id.second);
        this.mMilliSpinner = (NumberPicker) view.findViewById(R.id.milli);
        this.mAmPmSpinner = (NumberPicker) view.findViewById(R.id.ampm);
        int minHour = min / HOUR_IN_MILLIS;
        int maxHour = max / HOUR_IN_MILLIS;
        int min2 = min - (HOUR_IN_MILLIS * minHour);
        int max2 = max - (HOUR_IN_MILLIS * maxHour);
        if (minHour == maxHour) {
            this.mHourSpinner.setEnabled(false);
            hour = minHour;
        }
        if (is24hourFormat) {
            this.mAmPmSpinner.setVisibility(8);
        } else {
            int minAmPm = minHour / 12;
            int maxAmPm = maxHour / 12;
            int amPm = hour / 12;
            this.mAmPmSpinner.setMinValue(minAmPm);
            this.mAmPmSpinner.setMaxValue(maxAmPm);
            this.mAmPmSpinner.setDisplayedValues(new String[]{context.getString(R.string.time_picker_dialog_am), context.getString(R.string.time_picker_dialog_pm)});
            hour %= 12;
            hour = hour == 0 ? 12 : hour;
            if (minAmPm == maxAmPm) {
                this.mAmPmSpinner.setEnabled(false);
                amPm = minAmPm;
                minHour %= 12;
                maxHour %= 12;
                if (minHour == 0 && maxHour == 0) {
                    minHour = 12;
                    maxHour = 12;
                } else if (minHour == 0) {
                    minHour = maxHour;
                    maxHour = 12;
                } else if (maxHour == 0) {
                    maxHour = 12;
                }
            } else {
                minHour = 1;
                maxHour = 12;
            }
            this.mAmPmSpinner.setValue(amPm);
        }
        if (minHour == maxHour) {
            this.mHourSpinner.setEnabled(false);
        }
        this.mHourSpinner.setMinValue(minHour);
        this.mHourSpinner.setMaxValue(maxHour);
        this.mHourSpinner.setValue(hour);
        NumberFormatter twoDigitPaddingFormatter = new NumberFormatter("%02d");
        int minMinute = min2 / 60000;
        int maxMinute = max2 / 60000;
        int min3 = min2 - (60000 * minMinute);
        int max3 = max2 - (60000 * maxMinute);
        if (minHour == maxHour) {
            this.mMinuteSpinner.setMinValue(minMinute);
            this.mMinuteSpinner.setMaxValue(maxMinute);
            if (minMinute == maxMinute) {
                this.mMinuteSpinner.setDisplayedValues(new String[]{twoDigitPaddingFormatter.format(minMinute)});
                this.mMinuteSpinner.setEnabled(false);
                minute = minMinute;
            }
        } else {
            this.mMinuteSpinner.setMinValue(0);
            this.mMinuteSpinner.setMaxValue(59);
        }
        if (step >= HOUR_IN_MILLIS) {
            this.mMinuteSpinner.setEnabled(false);
        }
        this.mMinuteSpinner.setValue(minute);
        this.mMinuteSpinner.setFormatter(twoDigitPaddingFormatter);
        if (step >= 60000) {
            view.findViewById(R.id.second_colon).setVisibility(8);
            this.mSecSpinner.setVisibility(8);
        }
        int minSecond = min3 / 1000;
        int maxSecond = max3 / 1000;
        int min4 = min3 - (minSecond * 1000);
        int max4 = max3 - (maxSecond * 1000);
        if (minHour == maxHour && minMinute == maxMinute) {
            this.mSecSpinner.setMinValue(minSecond);
            this.mSecSpinner.setMaxValue(maxSecond);
            if (minSecond == maxSecond) {
                this.mSecSpinner.setDisplayedValues(new String[]{twoDigitPaddingFormatter.format(minSecond)});
                this.mSecSpinner.setEnabled(false);
                second = minSecond;
            }
        } else {
            this.mSecSpinner.setMinValue(0);
            this.mSecSpinner.setMaxValue(59);
        }
        this.mSecSpinner.setValue(second);
        this.mSecSpinner.setFormatter(twoDigitPaddingFormatter);
        if (step >= 1000) {
            view.findViewById(R.id.second_dot).setVisibility(8);
            this.mMilliSpinner.setVisibility(8);
        }
        int milli2 = (((step / 2) + milli) / step) * step;
        if (step == 1 || step == 10 || step == 100) {
            if (minHour == maxHour && minMinute == maxMinute && minSecond == maxSecond) {
                this.mMilliSpinner.setMinValue(min4 / step);
                this.mMilliSpinner.setMaxValue(max4 / step);
                if (min4 == max4) {
                    this.mMilliSpinner.setEnabled(false);
                    milli2 = min4;
                }
            } else {
                this.mMilliSpinner.setMinValue(0);
                this.mMilliSpinner.setMaxValue(999 / step);
            }
            if (step == 1) {
                this.mMilliSpinner.setFormatter(new NumberFormatter("%03d"));
            } else if (step == 10) {
                this.mMilliSpinner.setFormatter(new NumberFormatter("%02d"));
            } else if (step == 100) {
                this.mMilliSpinner.setFormatter(new NumberFormatter("%d"));
            }
            this.mMilliSpinner.setValue(milli2 / step);
            this.mBaseMilli = 0;
        } else if (step < 1000) {
            ArrayList<String> strValue = new ArrayList<>();
            for (int i = min4; i < max4; i += step) {
                strValue.add(String.format("%03d", new Object[]{Integer.valueOf(i)}));
            }
            this.mMilliSpinner.setMinValue(0);
            this.mMilliSpinner.setMaxValue(strValue.size() - 1);
            this.mMilliSpinner.setValue((milli2 - min4) / step);
            this.mMilliSpinner.setDisplayedValues((String[]) strValue.toArray(new String[strValue.size()]));
            this.mBaseMilli = min4;
        } else {
            this.mBaseMilli = 0;
        }
    }

    public void onClick(DialogInterface dialog, int which) {
        notifyDateSet();
    }

    private void notifyDateSet() {
        int hour = this.mHourSpinner.getValue();
        int minute = this.mMinuteSpinner.getValue();
        int sec = this.mSecSpinner.getValue();
        int milli = (this.mMilliSpinner.getValue() * this.mStep) + this.mBaseMilli;
        if (!this.mIs24hourFormat) {
            int ampm = this.mAmPmSpinner.getValue();
            if (hour == 12) {
                hour = 0;
            }
            hour += ampm * 12;
        }
        this.mListener.onTimeSet(hour, minute, sec, milli);
    }

    private static class NumberFormatter implements NumberPicker.Formatter {
        private final String mFormat;

        NumberFormatter(String format) {
            this.mFormat = format;
        }

        public String format(int value) {
            return String.format(this.mFormat, new Object[]{Integer.valueOf(value)});
        }
    }
}
