package org.chromium.ui.picker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.chromium.ui.R;
import org.chromium.ui.picker.DateTimePickerDialog;
import org.chromium.ui.picker.MultiFieldTimePickerDialog;
import org.chromium.ui.picker.TwoFieldDatePickerDialog;

public class InputDialogContainer {
    private static int sTextInputTypeDate;
    private static int sTextInputTypeDateTime;
    /* access modifiers changed from: private */
    public static int sTextInputTypeDateTimeLocal;
    /* access modifiers changed from: private */
    public static int sTextInputTypeMonth;
    private static int sTextInputTypeTime;
    private static int sTextInputTypeWeek;
    private final Context mContext;
    /* access modifiers changed from: private */
    public AlertDialog mDialog;
    /* access modifiers changed from: private */
    public boolean mDialogAlreadyDismissed;
    /* access modifiers changed from: private */
    public final InputActionDelegate mInputActionDelegate;

    public interface InputActionDelegate {
        void cancelDateTimeDialog();

        void replaceDateTime(double d);
    }

    public static void initializeInputTypes(int textInputTypeDate, int textInputTypeDateTime, int textInputTypeDateTimeLocal, int textInputTypeMonth, int textInputTypeTime, int textInputTypeWeek) {
        sTextInputTypeDate = textInputTypeDate;
        sTextInputTypeDateTime = textInputTypeDateTime;
        sTextInputTypeDateTimeLocal = textInputTypeDateTimeLocal;
        sTextInputTypeMonth = textInputTypeMonth;
        sTextInputTypeTime = textInputTypeTime;
        sTextInputTypeWeek = textInputTypeWeek;
    }

    public static boolean isDialogInputType(int type) {
        return type == sTextInputTypeDate || type == sTextInputTypeTime || type == sTextInputTypeDateTime || type == sTextInputTypeDateTimeLocal || type == sTextInputTypeMonth || type == sTextInputTypeWeek;
    }

    public InputDialogContainer(Context context, InputActionDelegate inputActionDelegate) {
        this.mContext = context;
        this.mInputActionDelegate = inputActionDelegate;
    }

    public void showPickerDialog(int dialogType, double dialogValue, double min, double max, double step) {
        Calendar cal;
        if (Double.isNaN(dialogValue)) {
            cal = Calendar.getInstance();
            cal.set(14, 0);
        } else if (dialogType == sTextInputTypeMonth) {
            cal = MonthPicker.createDateFromValue(dialogValue);
        } else if (dialogType == sTextInputTypeWeek) {
            cal = WeekPicker.createDateFromValue(dialogValue);
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
            gregorianCalendar.setTimeInMillis((long) dialogValue);
            cal = gregorianCalendar;
        }
        if (dialogType == sTextInputTypeDate) {
            showPickerDialog(dialogType, cal.get(1), cal.get(2), cal.get(5), 0, 0, 0, 0, 0, min, max, step);
        } else if (dialogType == sTextInputTypeTime) {
            showPickerDialog(dialogType, 0, 0, 0, cal.get(11), cal.get(12), 0, 0, 0, min, max, step);
        } else if (dialogType == sTextInputTypeDateTime || dialogType == sTextInputTypeDateTimeLocal) {
            showPickerDialog(dialogType, cal.get(1), cal.get(2), cal.get(5), cal.get(11), cal.get(12), cal.get(13), cal.get(14), 0, min, max, step);
        } else if (dialogType == sTextInputTypeMonth) {
            showPickerDialog(dialogType, cal.get(1), cal.get(2), 0, 0, 0, 0, 0, 0, min, max, step);
        } else if (dialogType == sTextInputTypeWeek) {
            showPickerDialog(dialogType, WeekPicker.getISOWeekYearForDate(cal), 0, 0, 0, 0, 0, 0, WeekPicker.getWeekForDate(cal), min, max, step);
        }
    }

    /* access modifiers changed from: package-private */
    public void showSuggestionDialog(int dialogType, double dialogValue, double min, double max, double step, DateTimeSuggestion[] suggestions) {
        ListView suggestionListView = new ListView(this.mContext);
        final DateTimeSuggestionListAdapter adapter = new DateTimeSuggestionListAdapter(this.mContext, Arrays.asList(suggestions));
        suggestionListView.setAdapter(adapter);
        final int i = dialogType;
        final double d = dialogValue;
        final double d2 = min;
        final double d3 = max;
        final double d4 = step;
        suggestionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == adapter.getCount() - 1) {
                    InputDialogContainer.this.dismissDialog();
                    InputDialogContainer.this.showPickerDialog(i, d, d2, d3, d4);
                    return;
                }
                InputDialogContainer.this.mInputActionDelegate.replaceDateTime(((DateTimeSuggestion) adapter.getItem(position)).value());
                InputDialogContainer.this.dismissDialog();
                boolean unused = InputDialogContainer.this.mDialogAlreadyDismissed = true;
            }
        });
        int dialogTitleId = R.string.date_picker_dialog_title;
        if (dialogType == sTextInputTypeTime) {
            dialogTitleId = R.string.time_picker_dialog_title;
        } else if (dialogType == sTextInputTypeDateTime || dialogType == sTextInputTypeDateTimeLocal) {
            dialogTitleId = R.string.date_time_picker_dialog_title;
        } else if (dialogType == sTextInputTypeMonth) {
            dialogTitleId = R.string.month_picker_dialog_title;
        } else if (dialogType == sTextInputTypeWeek) {
            dialogTitleId = R.string.week_picker_dialog_title;
        }
        this.mDialog = new AlertDialog.Builder(this.mContext).setTitle(dialogTitleId).setView(suggestionListView).setNegativeButton(this.mContext.getText(17039360), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                InputDialogContainer.this.dismissDialog();
            }
        }).create();
        this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                if (InputDialogContainer.this.mDialog == dialog && !InputDialogContainer.this.mDialogAlreadyDismissed) {
                    boolean unused = InputDialogContainer.this.mDialogAlreadyDismissed = true;
                    InputDialogContainer.this.mInputActionDelegate.cancelDateTimeDialog();
                }
            }
        });
        this.mDialogAlreadyDismissed = false;
        this.mDialog.show();
    }

    public void showDialog(int type, double value, double min, double max, double step, DateTimeSuggestion[] suggestions) {
        dismissDialog();
        if (suggestions == null) {
            showPickerDialog(type, value, min, max, step);
        } else {
            showSuggestionDialog(type, value, min, max, step, suggestions);
        }
    }

    /* access modifiers changed from: protected */
    public void showPickerDialog(int dialogType, int year, int month, int monthDay, int hourOfDay, int minute, int second, int millis, int week, double min, double max, double step) {
        if (isDialogShowing()) {
            this.mDialog.dismiss();
        }
        int stepTime = (int) step;
        if (dialogType == sTextInputTypeDate) {
            ChromeDatePickerDialog dialog = new ChromeDatePickerDialog(this.mContext, new DateListener(dialogType), year, month, monthDay);
            DateDialogNormalizer.normalize(dialog.getDatePicker(), dialog, year, month, monthDay, 0, 0, (long) min, (long) max);
            dialog.setTitle(this.mContext.getText(R.string.date_picker_dialog_title));
            this.mDialog = dialog;
        } else if (dialogType == sTextInputTypeTime) {
            this.mDialog = new MultiFieldTimePickerDialog(this.mContext, 0, hourOfDay, minute, second, millis, (int) min, (int) max, stepTime, DateFormat.is24HourFormat(this.mContext), new FullTimeListener(dialogType));
        } else if (dialogType == sTextInputTypeDateTime || dialogType == sTextInputTypeDateTimeLocal) {
            this.mDialog = new DateTimePickerDialog(this.mContext, new DateTimeListener(dialogType), year, month, monthDay, hourOfDay, minute, DateFormat.is24HourFormat(this.mContext), min, max);
        } else if (dialogType == sTextInputTypeMonth) {
            this.mDialog = new MonthPickerDialog(this.mContext, new MonthOrWeekListener(dialogType), year, month, min, max);
        } else if (dialogType == sTextInputTypeWeek) {
            this.mDialog = new WeekPickerDialog(this.mContext, new MonthOrWeekListener(dialogType), year, week, min, max);
        }
        this.mDialog.setButton(-1, this.mContext.getText(R.string.date_picker_dialog_set), (DialogInterface.OnClickListener) this.mDialog);
        this.mDialog.setButton(-2, this.mContext.getText(17039360), (DialogInterface.OnClickListener) null);
        this.mDialog.setButton(-3, this.mContext.getText(R.string.date_picker_dialog_clear), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                boolean unused = InputDialogContainer.this.mDialogAlreadyDismissed = true;
                InputDialogContainer.this.mInputActionDelegate.replaceDateTime(Double.NaN);
            }
        });
        this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                if (!InputDialogContainer.this.mDialogAlreadyDismissed) {
                    boolean unused = InputDialogContainer.this.mDialogAlreadyDismissed = true;
                    InputDialogContainer.this.mInputActionDelegate.cancelDateTimeDialog();
                }
            }
        });
        this.mDialogAlreadyDismissed = false;
        this.mDialog.show();
    }

    /* access modifiers changed from: package-private */
    public boolean isDialogShowing() {
        return this.mDialog != null && this.mDialog.isShowing();
    }

    /* access modifiers changed from: package-private */
    public void dismissDialog() {
        if (isDialogShowing()) {
            this.mDialog.dismiss();
        }
    }

    private class DateListener implements DatePickerDialog.OnDateSetListener {
        private final int mDialogType;

        DateListener(int dialogType) {
            this.mDialogType = dialogType;
        }

        public void onDateSet(DatePicker view, int year, int month, int monthDay) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, year, month, monthDay, 0, 0, 0, 0, 0);
        }
    }

    private class FullTimeListener implements MultiFieldTimePickerDialog.OnMultiFieldTimeSetListener {
        private final int mDialogType;

        FullTimeListener(int dialogType) {
            this.mDialogType = dialogType;
        }

        public void onTimeSet(int hourOfDay, int minute, int second, int milli) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, 0, 0, 0, hourOfDay, minute, second, milli, 0);
        }
    }

    private class DateTimeListener implements DateTimePickerDialog.OnDateTimeSetListener {
        private final int mDialogType;
        private final boolean mLocal;

        public DateTimeListener(int dialogType) {
            this.mLocal = dialogType == InputDialogContainer.sTextInputTypeDateTimeLocal;
            this.mDialogType = dialogType;
        }

        public void onDateTimeSet(DatePicker dateView, TimePicker timeView, int year, int month, int monthDay, int hourOfDay, int minute) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, year, month, monthDay, hourOfDay, minute, 0, 0, 0);
        }
    }

    private class MonthOrWeekListener implements TwoFieldDatePickerDialog.OnValueSetListener {
        private final int mDialogType;

        MonthOrWeekListener(int dialogType) {
            this.mDialogType = dialogType;
        }

        public void onValueSet(int year, int positionInYear) {
            if (this.mDialogType == InputDialogContainer.sTextInputTypeMonth) {
                InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, year, positionInYear, 0, 0, 0, 0, 0, 0);
            } else {
                InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, year, 0, 0, 0, 0, 0, 0, positionInYear);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setFieldDateTimeValue(int dialogType, int year, int month, int monthDay, int hourOfDay, int minute, int second, int millis, int week) {
        if (!this.mDialogAlreadyDismissed) {
            this.mDialogAlreadyDismissed = true;
            if (dialogType == sTextInputTypeMonth) {
                this.mInputActionDelegate.replaceDateTime((double) (((year - 1970) * 12) + month));
            } else if (dialogType == sTextInputTypeWeek) {
                this.mInputActionDelegate.replaceDateTime((double) WeekPicker.createDateFromWeek(year, week).getTimeInMillis());
            } else if (dialogType == sTextInputTypeTime) {
                this.mInputActionDelegate.replaceDateTime((double) (TimeUnit.HOURS.toMillis((long) hourOfDay) + TimeUnit.MINUTES.toMillis((long) minute) + TimeUnit.SECONDS.toMillis((long) second) + ((long) millis)));
            } else {
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                cal.clear();
                cal.set(1, year);
                cal.set(2, month);
                cal.set(5, monthDay);
                cal.set(11, hourOfDay);
                cal.set(12, minute);
                cal.set(13, second);
                cal.set(14, millis);
                this.mInputActionDelegate.replaceDateTime((double) cal.getTimeInMillis());
            }
        }
    }
}
