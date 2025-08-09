package org.chromium.components.web_contents_delegate_android;

import android.graphics.Point;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.CalledByNative;
import org.chromium.content.R;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.RenderCoordinates;

class ValidationMessageBubble {
    private PopupWindow mPopup;

    @CalledByNative
    private static ValidationMessageBubble createAndShow(ContentViewCore contentViewCore, int anchorX, int anchorY, int anchorWidth, int anchorHeight, String mainText, String subText) {
        return new ValidationMessageBubble(contentViewCore, makePixRectInScreen(contentViewCore, anchorX, anchorY, anchorWidth, anchorHeight), mainText, subText);
    }

    private ValidationMessageBubble(ContentViewCore contentViewCore, RectF anchor, String mainText, String subText) {
        ViewGroup root = (ViewGroup) View.inflate(contentViewCore.getContext(), R.layout.validation_message_bubble, (ViewGroup) null);
        this.mPopup = new PopupWindow(root);
        updateTextViews(root, mainText, subText);
        measure(contentViewCore.getRenderCoordinates());
        Point origin = adjustWindowPosition(contentViewCore, (int) (anchor.centerX() - getAnchorOffset()), (int) anchor.bottom);
        this.mPopup.showAtLocation(contentViewCore.getContainerView(), 0, origin.x, origin.y);
    }

    @CalledByNative
    private void close() {
        if (this.mPopup != null) {
            this.mPopup.dismiss();
            this.mPopup = null;
        }
    }

    @CalledByNative
    private void setPositionRelativeToAnchor(ContentViewCore contentViewCore, int anchorX, int anchorY, int anchorWidth, int anchorHeight) {
        RectF anchor = makePixRectInScreen(contentViewCore, anchorX, anchorY, anchorWidth, anchorHeight);
        Point origin = adjustWindowPosition(contentViewCore, (int) (anchor.centerX() - getAnchorOffset()), (int) anchor.bottom);
        this.mPopup.update(origin.x, origin.y, this.mPopup.getWidth(), this.mPopup.getHeight());
    }

    private static RectF makePixRectInScreen(ContentViewCore contentViewCore, int anchorX, int anchorY, int anchorWidth, int anchorHeight) {
        RenderCoordinates coordinates = contentViewCore.getRenderCoordinates();
        float yOffset = getWebViewOffsetYPixInScreen(contentViewCore);
        return new RectF(coordinates.fromLocalCssToPix((float) anchorX), coordinates.fromLocalCssToPix((float) anchorY) + yOffset, coordinates.fromLocalCssToPix((float) (anchorX + anchorWidth)), coordinates.fromLocalCssToPix((float) (anchorY + anchorHeight)) + yOffset);
    }

    private static float getWebViewOffsetYPixInScreen(ContentViewCore contentViewCore) {
        int[] location = new int[2];
        contentViewCore.getContainerView().getLocationOnScreen(location);
        return ((float) location[1]) + contentViewCore.getRenderCoordinates().getContentOffsetYPix();
    }

    private static void updateTextViews(ViewGroup root, String mainText, String subText) {
        ((TextView) root.findViewById(R.id.main_text)).setText(mainText);
        TextView subTextView = (TextView) root.findViewById(R.id.sub_text);
        if (!TextUtils.isEmpty(subText)) {
            subTextView.setText(subText);
        } else {
            ((ViewGroup) subTextView.getParent()).removeView(subTextView);
        }
    }

    private void measure(RenderCoordinates coordinates) {
        this.mPopup.setWindowLayoutMode(-2, -2);
        this.mPopup.getContentView().setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.mPopup.getContentView().measure(View.MeasureSpec.makeMeasureSpec(coordinates.getLastFrameViewportWidthPixInt(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(coordinates.getLastFrameViewportHeightPixInt(), Integer.MIN_VALUE));
    }

    private float getAnchorOffset() {
        View root = this.mPopup.getContentView();
        int width = root.getMeasuredWidth();
        int arrowWidth = root.findViewById(R.id.arrow_image).getMeasuredWidth();
        return ApiCompatibilityUtils.isLayoutRtl(root) ? (float) (((width * 3) / 4) - (arrowWidth / 2)) : (float) ((width / 4) + (arrowWidth / 2));
    }

    private Point adjustWindowPosition(ContentViewCore contentViewCore, int x, int y) {
        RenderCoordinates coordinates = contentViewCore.getRenderCoordinates();
        int viewWidth = coordinates.getLastFrameViewportWidthPixInt();
        int viewBottom = ((int) getWebViewOffsetYPixInScreen(contentViewCore)) + coordinates.getLastFrameViewportHeightPixInt();
        int width = this.mPopup.getContentView().getMeasuredWidth();
        int height = this.mPopup.getContentView().getMeasuredHeight();
        if (x < 0) {
            x = 0;
        } else if (x + width > viewWidth) {
            x = viewWidth - width;
        }
        if (y + height > viewBottom) {
            y = viewBottom - height;
        }
        return new Point(x, y);
    }
}
