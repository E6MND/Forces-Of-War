package org.chromium.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ColorPickerDialog extends AlertDialog implements OnColorChangedListener {
    private final ColorPickerAdvanced mAdvancedColorPicker;
    /* access modifiers changed from: private */
    public int mCurrentColor = this.mInitialColor;
    private final View mCurrentColorView;
    /* access modifiers changed from: private */
    public final int mInitialColor;
    private final OnColorChangedListener mListener;
    private final Button mMoreButton;
    private final ColorPickerSimple mSimpleColorPicker;

    public ColorPickerDialog(Context context, OnColorChangedListener listener, int color, ColorSuggestion[] suggestions) {
        super(context, 0);
        this.mListener = listener;
        this.mInitialColor = color;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        View title = inflater.inflate(R.layout.color_picker_dialog_title, (ViewGroup) null);
        setCustomTitle(title);
        this.mCurrentColorView = title.findViewById(R.id.selected_color_view);
        ((TextView) title.findViewById(R.id.title)).setText(R.string.color_picker_dialog_title);
        setButton(-1, context.getString(R.string.color_picker_button_set), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ColorPickerDialog.this.tryNotifyColorSet(ColorPickerDialog.this.mCurrentColor);
            }
        });
        setButton(-2, context.getString(R.string.color_picker_button_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ColorPickerDialog.this.tryNotifyColorSet(ColorPickerDialog.this.mInitialColor);
            }
        });
        setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                ColorPickerDialog.this.tryNotifyColorSet(ColorPickerDialog.this.mInitialColor);
            }
        });
        View content = inflater.inflate(R.layout.color_picker_dialog_content, (ViewGroup) null);
        setView(content);
        this.mMoreButton = (Button) content.findViewById(R.id.more_colors_button);
        this.mMoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ColorPickerDialog.this.showAdvancedView();
            }
        });
        this.mAdvancedColorPicker = (ColorPickerAdvanced) content.findViewById(R.id.color_picker_advanced);
        this.mAdvancedColorPicker.setVisibility(8);
        this.mSimpleColorPicker = (ColorPickerSimple) content.findViewById(R.id.color_picker_simple);
        this.mSimpleColorPicker.init(suggestions, this);
        updateCurrentColor(this.mInitialColor);
    }

    public void onColorChanged(int color) {
        updateCurrentColor(color);
    }

    /* access modifiers changed from: private */
    public void showAdvancedView() {
        findViewById(R.id.more_colors_button_border).setVisibility(8);
        findViewById(R.id.color_picker_simple).setVisibility(8);
        this.mAdvancedColorPicker.setVisibility(0);
        this.mAdvancedColorPicker.setListener(this);
        this.mAdvancedColorPicker.setColor(this.mCurrentColor);
    }

    /* access modifiers changed from: private */
    public void tryNotifyColorSet(int color) {
        if (this.mListener != null) {
            this.mListener.onColorChanged(color);
        }
    }

    private void updateCurrentColor(int color) {
        this.mCurrentColor = color;
        if (this.mCurrentColorView != null) {
            this.mCurrentColorView.setBackgroundColor(color);
        }
    }
}
