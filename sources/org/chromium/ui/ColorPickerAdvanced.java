package org.chromium.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class ColorPickerAdvanced extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    private static final int HUE_COLOR_COUNT = 7;
    private static final int HUE_SEEK_BAR_MAX = 360;
    private static final int SATURATION_COLOR_COUNT = 2;
    private static final int SATURATION_SEEK_BAR_MAX = 100;
    private static final int VALUE_COLOR_COUNT = 2;
    private static final int VALUE_SEEK_BAR_MAX = 100;
    private int mCurrentColor;
    private final float[] mCurrentHsvValues = new float[3];
    ColorPickerAdvancedComponent mHueDetails;
    private OnColorChangedListener mOnColorChangedListener;
    ColorPickerAdvancedComponent mSaturationDetails;
    ColorPickerAdvancedComponent mValueDetails;

    public ColorPickerAdvanced(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPickerAdvanced(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ColorPickerAdvanced(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOrientation(1);
        this.mHueDetails = createAndAddNewGradient(R.string.color_picker_hue, HUE_SEEK_BAR_MAX, this);
        this.mSaturationDetails = createAndAddNewGradient(R.string.color_picker_saturation, 100, this);
        this.mValueDetails = createAndAddNewGradient(R.string.color_picker_value, 100, this);
        refreshGradientComponents();
    }

    public ColorPickerAdvancedComponent createAndAddNewGradient(int textResourceId, int seekBarMax, SeekBar.OnSeekBarChangeListener seekBarListener) {
        View newComponent = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.color_picker_advanced_component, (ViewGroup) null);
        addView(newComponent);
        return new ColorPickerAdvancedComponent(newComponent, textResourceId, seekBarMax, seekBarListener);
    }

    public void setListener(OnColorChangedListener onColorChangedListener) {
        this.mOnColorChangedListener = onColorChangedListener;
    }

    public int getColor() {
        return this.mCurrentColor;
    }

    public void setColor(int color) {
        this.mCurrentColor = color;
        Color.colorToHSV(this.mCurrentColor, this.mCurrentHsvValues);
        refreshGradientComponents();
    }

    private void notifyColorChanged() {
        if (this.mOnColorChangedListener != null) {
            this.mOnColorChangedListener.onColorChanged(getColor());
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            this.mCurrentHsvValues[0] = this.mHueDetails.getValue();
            this.mCurrentHsvValues[1] = this.mSaturationDetails.getValue() / 100.0f;
            this.mCurrentHsvValues[2] = this.mValueDetails.getValue() / 100.0f;
            this.mCurrentColor = Color.HSVToColor(this.mCurrentHsvValues);
            updateHueGradient();
            updateSaturationGradient();
            updateValueGradient();
            notifyColorChanged();
        }
    }

    private void updateHueGradient() {
        float[] tempHsvValues = new float[3];
        tempHsvValues[1] = this.mCurrentHsvValues[1];
        tempHsvValues[2] = this.mCurrentHsvValues[2];
        int[] newColors = new int[7];
        for (int i = 0; i < 7; i++) {
            tempHsvValues[0] = ((float) i) * 60.0f;
            newColors[i] = Color.HSVToColor(tempHsvValues);
        }
        this.mHueDetails.setGradientColors(newColors);
    }

    private void updateSaturationGradient() {
        float[] tempHsvValues = {this.mCurrentHsvValues[0], 0.0f, this.mCurrentHsvValues[2]};
        tempHsvValues[1] = 1.0f;
        this.mSaturationDetails.setGradientColors(new int[]{Color.HSVToColor(tempHsvValues), Color.HSVToColor(tempHsvValues)});
    }

    private void updateValueGradient() {
        float[] tempHsvValues = {this.mCurrentHsvValues[0], this.mCurrentHsvValues[1], 0.0f};
        tempHsvValues[2] = 1.0f;
        this.mValueDetails.setGradientColors(new int[]{Color.HSVToColor(tempHsvValues), Color.HSVToColor(tempHsvValues)});
    }

    private void refreshGradientComponents() {
        int saturationValue = Math.max(Math.min(Math.round(this.mCurrentHsvValues[1] * 100.0f), 100), 0);
        int valueValue = Math.max(Math.min(Math.round(this.mCurrentHsvValues[2] * 100.0f), 100), 0);
        this.mHueDetails.setValue(this.mCurrentHsvValues[0]);
        this.mSaturationDetails.setValue((float) saturationValue);
        this.mValueDetails.setValue((float) valueValue);
        updateHueGradient();
        updateSaturationGradient();
        updateValueGradient();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
