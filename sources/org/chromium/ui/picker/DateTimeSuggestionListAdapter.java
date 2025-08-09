package org.chromium.ui.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import org.chromium.ui.R;

class DateTimeSuggestionListAdapter extends ArrayAdapter<DateTimeSuggestion> {
    private final Context mContext;

    DateTimeSuggestionListAdapter(Context context, List<DateTimeSuggestion> objects) {
        super(context, R.layout.date_time_suggestion, objects);
        this.mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = convertView;
        if (convertView == null) {
            layout = LayoutInflater.from(this.mContext).inflate(R.layout.date_time_suggestion, parent, false);
        }
        TextView labelView = (TextView) layout.findViewById(R.id.date_time_suggestion_value);
        TextView sublabelView = (TextView) layout.findViewById(R.id.date_time_suggestion_label);
        if (position == getCount() - 1) {
            labelView.setText(this.mContext.getText(R.string.date_picker_dialog_other_button_label));
            sublabelView.setText("");
        } else {
            labelView.setText(((DateTimeSuggestion) getItem(position)).localizedValue());
            sublabelView.setText(((DateTimeSuggestion) getItem(position)).label());
        }
        return layout;
    }

    public int getCount() {
        return super.getCount() + 1;
    }
}
