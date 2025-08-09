package org.chromium.ui.base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.ui.R;

@JNINamespace("ui")
public class Clipboard {
    private final ClipboardManager mClipboardManager;
    private final Context mContext;

    public Clipboard(Context context) {
        this.mContext = context;
        this.mClipboardManager = (ClipboardManager) context.getSystemService("clipboard");
    }

    @CalledByNative
    private static Clipboard create(Context context) {
        return new Clipboard(context);
    }

    @CalledByNative
    private String getCoercedText() {
        CharSequence sequence;
        ClipData clip = this.mClipboardManager.getPrimaryClip();
        if (clip == null || clip.getItemCount() <= 0 || (sequence = clip.getItemAt(0).coerceToText(this.mContext)) == null) {
            return null;
        }
        return sequence.toString();
    }

    @CalledByNative
    private String getHTMLText() {
        ClipData clip;
        if (!isHTMLClipboardSupported() || (clip = this.mClipboardManager.getPrimaryClip()) == null || clip.getItemCount() <= 0) {
            return null;
        }
        return clip.getItemAt(0).getHtmlText();
    }

    public void setText(String label, String text) {
        setPrimaryClipNoException(ClipData.newPlainText(label, text));
    }

    @CalledByNative
    public void setText(String text) {
        setText((String) null, text);
    }

    public void setHTMLText(String html, String label, String text) {
        if (isHTMLClipboardSupported()) {
            setPrimaryClipNoException(ClipData.newHtmlText(label, text, html));
        }
    }

    @CalledByNative
    public void setHTMLText(String html, String text) {
        setHTMLText(html, (String) null, text);
    }

    @CalledByNative
    private static boolean isHTMLClipboardSupported() {
        return ApiCompatibilityUtils.isHTMLClipboardSupported();
    }

    private void setPrimaryClipNoException(ClipData clip) {
        try {
            this.mClipboardManager.setPrimaryClip(clip);
        } catch (Exception e) {
            Toast.makeText(this.mContext, this.mContext.getString(R.string.copy_to_clipboard_failure_message), 0).show();
        }
    }
}
