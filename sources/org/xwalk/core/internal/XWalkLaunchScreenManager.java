package org.xwalk.core.internal;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import org.chromium.content.browser.ContentViewRenderView;

public class XWalkLaunchScreenManager implements ContentViewRenderView.FirstRenderedFrameListener, DialogInterface.OnShowListener, DialogInterface.OnDismissListener, PageLoadListener {
    private static final String BORDER_MODE_REPEAT = "repeat";
    private static final String BORDER_MODE_ROUND = "round";
    private static final String BORDER_MODE_STRETCH = "stretch";
    private static String mIntentFilterStr;
    /* access modifiers changed from: private */
    public Activity mActivity = this.mXWalkView.getActivity();
    /* access modifiers changed from: private */
    public int mCurrentOrientation;
    /* access modifiers changed from: private */
    public boolean mCustomHideLaunchScreen;
    private boolean mFirstFrameReceived;
    /* access modifiers changed from: private */
    public Dialog mLaunchScreenDialog;
    private BroadcastReceiver mLaunchScreenReadyWhenReceiver;
    /* access modifiers changed from: private */
    public Context mLibContext;
    /* access modifiers changed from: private */
    public OrientationEventListener mOrientationListener;
    private boolean mPageLoadFinished;
    /* access modifiers changed from: private */
    public ReadyWhenType mReadyWhen;
    private XWalkViewInternal mXWalkView;

    private enum BorderModeType {
        REPEAT,
        STRETCH,
        ROUND,
        NONE
    }

    private enum ReadyWhenType {
        FIRST_PAINT,
        USER_INTERACTIVE,
        COMPLETE,
        CUSTOM
    }

    public XWalkLaunchScreenManager(Context context, XWalkViewInternal xwView) {
        this.mXWalkView = xwView;
        this.mLibContext = context;
        mIntentFilterStr = this.mActivity.getPackageName() + ".hideLaunchScreen";
    }

    public void displayLaunchScreen(String readyWhen, final String imageBorderList) {
        if (this.mXWalkView != null) {
            setReadyWhen(readyWhen);
            this.mActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Drawable bgDrawable;
                    int bgResId = XWalkLaunchScreenManager.this.mActivity.getResources().getIdentifier("launchscreen_bg", "drawable", XWalkLaunchScreenManager.this.mActivity.getPackageName());
                    if (bgResId != 0 && (bgDrawable = XWalkLaunchScreenManager.this.mActivity.getResources().getDrawable(bgResId)) != null) {
                        Dialog unused = XWalkLaunchScreenManager.this.mLaunchScreenDialog = new Dialog(XWalkLaunchScreenManager.this.mLibContext, 16974064);
                        int parentVisibility = XWalkLaunchScreenManager.this.mActivity.getWindow().getDecorView().getSystemUiVisibility();
                        WindowManager.LayoutParams parentParams = XWalkLaunchScreenManager.this.mActivity.getWindow().getAttributes();
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.getWindow().getDecorView().setSystemUiVisibility(parentVisibility);
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.getWindow().setAttributes(parentParams);
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                                if (keyCode != 4) {
                                    return true;
                                }
                                XWalkLaunchScreenManager.this.performHideLaunchScreen();
                                XWalkLaunchScreenManager.this.mActivity.onBackPressed();
                                return true;
                            }
                        });
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.setOnShowListener(XWalkLaunchScreenManager.this);
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.setOnDismissListener(XWalkLaunchScreenManager.this);
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.getWindow().setBackgroundDrawable(bgDrawable);
                        RelativeLayout root = XWalkLaunchScreenManager.this.getLaunchScreenLayout(imageBorderList);
                        if (root != null) {
                            XWalkLaunchScreenManager.this.mLaunchScreenDialog.setContentView(root);
                        }
                        XWalkLaunchScreenManager.this.mLaunchScreenDialog.show();
                        OrientationEventListener unused2 = XWalkLaunchScreenManager.this.mOrientationListener = new OrientationEventListener(XWalkLaunchScreenManager.this.mActivity, 3) {
                            public void onOrientationChanged(int ori) {
                                RelativeLayout root;
                                if (XWalkLaunchScreenManager.this.mLaunchScreenDialog != null && XWalkLaunchScreenManager.this.mLaunchScreenDialog.isShowing() && XWalkLaunchScreenManager.this.getScreenOrientation() != XWalkLaunchScreenManager.this.mCurrentOrientation && (root = XWalkLaunchScreenManager.this.getLaunchScreenLayout(imageBorderList)) != null) {
                                    XWalkLaunchScreenManager.this.mLaunchScreenDialog.setContentView(root);
                                }
                            }
                        };
                        XWalkLaunchScreenManager.this.mOrientationListener.enable();
                        if (XWalkLaunchScreenManager.this.mReadyWhen == ReadyWhenType.CUSTOM) {
                            XWalkLaunchScreenManager.this.registerBroadcastReceiver();
                        }
                    }
                }
            });
        }
    }

    public void onFirstFrameReceived() {
        this.mFirstFrameReceived = true;
        hideLaunchScreenWhenReady();
    }

    public void onShow(DialogInterface dialog) {
        this.mActivity.getWindow().setBackgroundDrawable((Drawable) null);
        if (this.mFirstFrameReceived) {
            hideLaunchScreenWhenReady();
        }
    }

    public void onDismiss(DialogInterface dialog) {
        this.mOrientationListener.disable();
        this.mOrientationListener = null;
    }

    public void onPageFinished(String url) {
        this.mPageLoadFinished = true;
        hideLaunchScreenWhenReady();
    }

    public static String getHideLaunchScreenFilterStr() {
        return mIntentFilterStr;
    }

    public int getScreenOrientation() {
        Display display = this.mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if (size.x < size.y) {
            return 1;
        }
        return 2;
    }

    /* access modifiers changed from: private */
    public RelativeLayout getLaunchScreenLayout(String imageBorderList) {
        String[] borders = imageBorderList.split(";");
        if (borders.length < 1) {
            return parseImageBorder("");
        }
        int orientation = getScreenOrientation();
        this.mCurrentOrientation = orientation;
        if (borders.length < 2 || orientation != 2) {
            if (borders.length != 3 || orientation != 1) {
                return parseImageBorder(borders[0]);
            }
            if (borders[2].equals("empty")) {
                return parseImageBorder("");
            }
            if (borders[2].isEmpty()) {
                return parseImageBorder(borders[0]);
            }
            return parseImageBorder(borders[2]);
        } else if (borders[1].equals("empty")) {
            return parseImageBorder("");
        } else {
            if (borders[1].isEmpty()) {
                return parseImageBorder(borders[0]);
            }
            return parseImageBorder(borders[1]);
        }
    }

    private int getSuitableSize(int maxSize, int divider) {
        int finalSize = divider;
        float minMod = (float) divider;
        while (divider > 1) {
            int mod = maxSize % divider;
            if (mod == 0) {
                return divider;
            }
            if (((float) mod) < minMod) {
                minMod = (float) mod;
                finalSize = divider;
            }
            divider--;
        }
        return finalSize;
    }

    private ImageView getSubImageView(Bitmap img, int x, int y, int width, int height, BorderModeType mode, int maxWidth, int maxHeight) {
        if (img == null || width <= 0 || height <= 0) {
            return null;
        }
        if (!new Rect(0, 0, img.getWidth(), img.getHeight()).contains(new Rect(x, y, x + width, y + height))) {
            return null;
        }
        Bitmap subImage = Bitmap.createBitmap(img, x, y, width, height);
        ImageView subImageView = new ImageView(this.mActivity);
        if (mode == BorderModeType.ROUND) {
            int originW = subImage.getWidth();
            int originH = subImage.getHeight();
            int newW = originW;
            int newH = originH;
            if (maxWidth > 0) {
                newW = getSuitableSize(maxWidth, originW);
            }
            if (maxHeight > 0) {
                newH = getSuitableSize(maxHeight, originH);
            }
            subImage = Bitmap.createScaledBitmap(subImage, newW, newH, true);
            mode = BorderModeType.REPEAT;
        }
        if (mode == BorderModeType.REPEAT) {
            BitmapDrawable drawable = new BitmapDrawable(this.mActivity.getResources(), subImage);
            drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            subImageView.setImageDrawable(drawable);
            subImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return subImageView;
        } else if (mode == BorderModeType.STRETCH) {
            subImageView.setImageBitmap(subImage);
            subImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return subImageView;
        } else {
            subImageView.setImageBitmap(subImage);
            return subImageView;
        }
    }

    private int getStatusBarHeight() {
        int resourceId = this.mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return this.mActivity.getResources().getDimensionPixelSize(resourceId);
        }
        return 25;
    }

    private RelativeLayout parseImageBorder(String imageBorder) {
        Bitmap img;
        int topBorder = 0;
        int rightBorder = 0;
        int leftBorder = 0;
        int bottomBorder = 0;
        BorderModeType horizontalMode = BorderModeType.STRETCH;
        BorderModeType verticalMode = BorderModeType.STRETCH;
        if (imageBorder.equals("empty")) {
            imageBorder = "";
        }
        String[] items = imageBorder.split(" ");
        ArrayList<String> borders = new ArrayList<>();
        ArrayList<BorderModeType> modes = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            if (item.endsWith("px")) {
                borders.add(item.replaceAll("px", ""));
            } else if (item.equals(BORDER_MODE_REPEAT)) {
                modes.add(BorderModeType.REPEAT);
            } else if (item.equals(BORDER_MODE_STRETCH)) {
                modes.add(BorderModeType.STRETCH);
            } else if (item.equals(BORDER_MODE_ROUND)) {
                modes.add(BorderModeType.ROUND);
            }
        }
        try {
            if (borders.size() == 1) {
                bottomBorder = Integer.valueOf(borders.get(0)).intValue();
                leftBorder = bottomBorder;
                rightBorder = bottomBorder;
                topBorder = bottomBorder;
            } else if (borders.size() == 2) {
                bottomBorder = Integer.valueOf(borders.get(0)).intValue();
                topBorder = bottomBorder;
                leftBorder = Integer.valueOf(borders.get(1)).intValue();
                rightBorder = leftBorder;
            } else if (borders.size() == 3) {
                leftBorder = Integer.valueOf(borders.get(1)).intValue();
                rightBorder = leftBorder;
                topBorder = Integer.valueOf(borders.get(0)).intValue();
                bottomBorder = Integer.valueOf(borders.get(2)).intValue();
            } else if (borders.size() == 4) {
                topBorder = Integer.valueOf(borders.get(0)).intValue();
                rightBorder = Integer.valueOf(borders.get(1)).intValue();
                leftBorder = Integer.valueOf(borders.get(2)).intValue();
                bottomBorder = Integer.valueOf(borders.get(3)).intValue();
            }
        } catch (NumberFormatException e) {
            bottomBorder = 0;
            leftBorder = 0;
            rightBorder = 0;
            topBorder = 0;
        }
        DisplayMetrics matrix = this.mActivity.getResources().getDisplayMetrics();
        int topBorder2 = (int) TypedValue.applyDimension(1, (float) topBorder, matrix);
        int rightBorder2 = (int) TypedValue.applyDimension(1, (float) rightBorder, matrix);
        int leftBorder2 = (int) TypedValue.applyDimension(1, (float) leftBorder, matrix);
        int bottomBorder2 = (int) TypedValue.applyDimension(1, (float) bottomBorder, matrix);
        if (modes.size() == 1) {
            verticalMode = modes.get(0);
            horizontalMode = verticalMode;
        } else if (modes.size() == 2) {
            horizontalMode = modes.get(0);
            verticalMode = modes.get(1);
        }
        int imgResId = this.mActivity.getResources().getIdentifier("launchscreen_img", "drawable", this.mActivity.getPackageName());
        if (imgResId == 0 || (img = BitmapFactory.decodeResource(this.mActivity.getResources(), imgResId)) == null) {
            return null;
        }
        RelativeLayout relativeLayout = new RelativeLayout(this.mActivity);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (borders.size() == 0) {
            ImageView imageView = new ImageView(this.mActivity);
            imageView.setImageBitmap(img);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            relativeLayout.addView(imageView, layoutParams);
            return relativeLayout;
        }
        Display display = this.mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if ((this.mActivity.getWindow().getAttributes().flags & 1024) == 0) {
            size.y -= getStatusBarHeight();
        }
        ImageView subImageView = getSubImageView(img, 0, 0, leftBorder2, topBorder2, BorderModeType.NONE, 0, 0);
        if (subImageView != null) {
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(9, -1);
            layoutParams2.addRule(10, -1);
            relativeLayout.addView(subImageView, layoutParams2);
        }
        ImageView subImageView2 = getSubImageView(img, leftBorder2, 0, (img.getWidth() - leftBorder2) - rightBorder2, topBorder2, horizontalMode, (size.x - leftBorder2) - rightBorder2, 0);
        if (subImageView2 != null) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams3.addRule(10, -1);
            layoutParams3.addRule(14, -1);
            layoutParams3.leftMargin = leftBorder2;
            layoutParams3.rightMargin = rightBorder2;
            relativeLayout.addView(subImageView2, layoutParams3);
        }
        ImageView subImageView3 = getSubImageView(img, img.getWidth() - rightBorder2, 0, rightBorder2, topBorder2, BorderModeType.NONE, 0, 0);
        if (subImageView3 != null) {
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(11, -1);
            layoutParams4.addRule(10, -1);
            relativeLayout.addView(subImageView3, layoutParams4);
        }
        ImageView subImageView4 = getSubImageView(img, 0, topBorder2, leftBorder2, (img.getHeight() - topBorder2) - bottomBorder2, verticalMode, 0, (size.y - topBorder2) - bottomBorder2);
        if (subImageView4 != null) {
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams5.addRule(9, -1);
            layoutParams5.addRule(13, -1);
            layoutParams5.topMargin = topBorder2;
            layoutParams5.bottomMargin = bottomBorder2;
            relativeLayout.addView(subImageView4, layoutParams5);
        }
        ImageView subImageView5 = getSubImageView(img, leftBorder2, topBorder2, (img.getWidth() - leftBorder2) - rightBorder2, (img.getHeight() - topBorder2) - bottomBorder2, BorderModeType.NONE, 0, 0);
        if (subImageView5 != null) {
            subImageView5.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams6.leftMargin = leftBorder2;
            layoutParams6.topMargin = topBorder2;
            layoutParams6.rightMargin = rightBorder2;
            layoutParams6.bottomMargin = bottomBorder2;
            relativeLayout.addView(subImageView5, layoutParams6);
        }
        ImageView subImageView6 = getSubImageView(img, img.getWidth() - rightBorder2, topBorder2, rightBorder2, (img.getHeight() - topBorder2) - bottomBorder2, verticalMode, 0, (size.y - topBorder2) - bottomBorder2);
        if (subImageView6 != null) {
            RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams7.addRule(13, -1);
            layoutParams7.addRule(11, -1);
            layoutParams7.topMargin = topBorder2;
            layoutParams7.bottomMargin = bottomBorder2;
            relativeLayout.addView(subImageView6, layoutParams7);
        }
        ImageView subImageView7 = getSubImageView(img, 0, img.getHeight() - bottomBorder2, leftBorder2, bottomBorder2, BorderModeType.NONE, 0, 0);
        if (subImageView7 != null) {
            RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams8.addRule(9, -1);
            layoutParams8.addRule(12, -1);
            relativeLayout.addView(subImageView7, layoutParams8);
        }
        ImageView subImageView8 = getSubImageView(img, leftBorder2, img.getHeight() - bottomBorder2, (img.getWidth() - leftBorder2) - rightBorder2, bottomBorder2, horizontalMode, (size.x - leftBorder2) - rightBorder2, 0);
        if (subImageView8 != null) {
            RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams9.addRule(14, -1);
            layoutParams9.addRule(12, -1);
            layoutParams9.leftMargin = leftBorder2;
            layoutParams9.rightMargin = rightBorder2;
            relativeLayout.addView(subImageView8, layoutParams9);
        }
        ImageView subImageView9 = getSubImageView(img, img.getWidth() - rightBorder2, img.getHeight() - bottomBorder2, rightBorder2, bottomBorder2, BorderModeType.NONE, 0, 0);
        if (subImageView9 == null) {
            return relativeLayout;
        }
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams10.addRule(11, -1);
        layoutParams10.addRule(12, -1);
        relativeLayout.addView(subImageView9, layoutParams10);
        return relativeLayout;
    }

    /* access modifiers changed from: private */
    public void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(mIntentFilterStr);
        this.mLaunchScreenReadyWhenReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                boolean unused = XWalkLaunchScreenManager.this.mCustomHideLaunchScreen = true;
                XWalkLaunchScreenManager.this.hideLaunchScreenWhenReady();
            }
        };
        this.mActivity.registerReceiver(this.mLaunchScreenReadyWhenReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    public void hideLaunchScreenWhenReady() {
        if (this.mLaunchScreenDialog != null && this.mFirstFrameReceived) {
            if (this.mReadyWhen == ReadyWhenType.FIRST_PAINT) {
                performHideLaunchScreen();
            } else if (this.mReadyWhen == ReadyWhenType.USER_INTERACTIVE) {
                performHideLaunchScreen();
            } else if (this.mReadyWhen == ReadyWhenType.COMPLETE) {
                if (this.mPageLoadFinished) {
                    performHideLaunchScreen();
                }
            } else if (this.mReadyWhen == ReadyWhenType.CUSTOM && this.mCustomHideLaunchScreen) {
                performHideLaunchScreen();
            }
        }
    }

    /* access modifiers changed from: private */
    public void performHideLaunchScreen() {
        this.mLaunchScreenDialog.dismiss();
        this.mLaunchScreenDialog = null;
        if (this.mReadyWhen == ReadyWhenType.CUSTOM) {
            this.mActivity.unregisterReceiver(this.mLaunchScreenReadyWhenReceiver);
        }
    }

    private void setReadyWhen(String readyWhen) {
        if (readyWhen.equals("first-paint")) {
            this.mReadyWhen = ReadyWhenType.FIRST_PAINT;
        } else if (readyWhen.equals("user-interactive")) {
            this.mReadyWhen = ReadyWhenType.USER_INTERACTIVE;
        } else if (readyWhen.equals("complete")) {
            this.mReadyWhen = ReadyWhenType.COMPLETE;
        } else if (readyWhen.equals("custom")) {
            this.mReadyWhen = ReadyWhenType.CUSTOM;
        } else {
            this.mReadyWhen = ReadyWhenType.FIRST_PAINT;
        }
    }
}
