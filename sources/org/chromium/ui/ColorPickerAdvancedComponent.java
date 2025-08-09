package org.chromium.ui;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import org.chromium.base.ApiCompatibilityUtils;

public class ColorPickerAdvancedComponent {
    private int[] mGradientColors;
    private GradientDrawable mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, (int[]) null);
    private final View mGradientView;
    private final SeekBar mSeekBar;
    private final TextView mText;

    ColorPickerAdvancedComponent(View rootView, int textResourceId, int seekBarMax, SeekBar.OnSeekBarChangeListener seekBarListener) {
        this.mGradientView = rootView.findViewById(R.id.gradient);
        this.mText = (TextView) rootView.findViewById(R.id.text);
        this.mText.setText(textResourceId);
        this.mSeekBar = (SeekBar) rootView.findViewById(R.id.seek_bar);
        this.mSeekBar.setOnSeekBarChangeListener(seekBarListener);
        this.mSeekBar.setMax(seekBarMax);
        this.mSeekBar.setThumbOffset(rootView.getContext().getResources().getDrawable(R.drawable.color_picker_advanced_select_handle).getIntrinsicWidth() / 2);
    }

    public float getValue() {
        return (float) this.mSeekBar.getProgress();
    }

    public void setValue(float newValue) {
        this.mSeekBar.setProgress((int) newValue);
    }

    public void setGradientColors(int[] newColors) {
        this.mGradientColors = (int[]) newColors.clone();
        if (Build.VERSION.SDK_INT < 16) {
            this.mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, this.mGradientColors);
        } else {
            this.mGradientDrawable.setColors(this.mGradientColors);
        }
        ApiCompatibilityUtils.setBackgroundForView(this.mGradientView, this.mGradientDrawable);
    }
}
