package com.chartboost.sdk.impl;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

public class n {
    private static /* synthetic */ int[] a;
    private static /* synthetic */ int[] b;

    public interface a {
        void a(a aVar);
    }

    public enum b {
        CBAnimationTypeNone,
        CBAnimationTypePerspectiveRotate,
        CBAnimationTypeBounce,
        CBAnimationTypePerspectiveZoom,
        CBAnimationTypeSlideFromBottom,
        CBAnimationTypeSlideFromTop
    }

    static /* synthetic */ int[] a() {
        int[] iArr = a;
        if (iArr == null) {
            iArr = new int[CBOrientation.Difference.values().length];
            try {
                iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            a = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] b() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[b.values().length];
            try {
                iArr[b.CBAnimationTypeBounce.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.CBAnimationTypeNone.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.CBAnimationTypePerspectiveRotate.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[b.CBAnimationTypePerspectiveZoom.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[b.CBAnimationTypeSlideFromBottom.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[b.CBAnimationTypeSlideFromTop.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            b = iArr;
        }
        return iArr;
    }

    public static void a(b bVar, a aVar) {
        a(bVar, aVar, (a) null);
    }

    public static void a(b bVar, a aVar, a aVar2) {
        b(bVar, aVar, aVar2, true);
    }

    public static void b(b bVar, a aVar, a aVar2) {
        c(bVar, aVar, aVar2, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r1 = r8.h.c();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(com.chartboost.sdk.impl.n.b r7, com.chartboost.sdk.impl.a r8, com.chartboost.sdk.impl.n.a r9, java.lang.Boolean r10) {
        /*
            if (r8 == 0) goto L_0x0006
            com.chartboost.sdk.impl.s r0 = r8.h
            if (r0 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            com.chartboost.sdk.impl.s r0 = r8.h
            android.view.View r1 = r0.c()
            if (r1 == 0) goto L_0x0006
            android.view.ViewTreeObserver r6 = r1.getViewTreeObserver()
            boolean r0 = r6.isAlive()
            if (r0 == 0) goto L_0x0006
            com.chartboost.sdk.impl.n$1 r0 = new com.chartboost.sdk.impl.n$1
            r2 = r7
            r3 = r8
            r4 = r9
            r5 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            r6.addOnGlobalLayoutListener(r0)
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.n.b(com.chartboost.sdk.impl.n$b, com.chartboost.sdk.impl.a, com.chartboost.sdk.impl.n$a, java.lang.Boolean):void");
    }

    /* access modifiers changed from: private */
    public static void c(b bVar, a aVar, a aVar2, Boolean bool) {
        View c;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        p pVar;
        ScaleAnimation scaleAnimation;
        TranslateAnimation translateAnimation;
        p pVar2;
        ScaleAnimation scaleAnimation2;
        TranslateAnimation translateAnimation2;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 1.0f));
        if (aVar != null && aVar.h != null && (c = aVar.h.c()) != null) {
            float width = (float) c.getWidth();
            float height = (float) c.getHeight();
            float f7 = (1.0f - 0.4f) / 2.0f;
            CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
            switch (b()[bVar.ordinal()]) {
                case 2:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            if (!bool.booleanValue()) {
                                pVar = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            } else {
                                pVar = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            }
                        case 3:
                            if (!bool.booleanValue()) {
                                pVar = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            } else {
                                pVar = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            }
                        case 4:
                            if (!bool.booleanValue()) {
                                pVar = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            } else {
                                pVar = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            }
                        default:
                            if (!bool.booleanValue()) {
                                pVar = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            } else {
                                pVar = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            }
                    }
                    pVar.setDuration(600);
                    pVar.setFillAfter(true);
                    animationSet.addAnimation(pVar);
                    if (bool.booleanValue()) {
                        scaleAnimation = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                    } else {
                        scaleAnimation = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                    }
                    scaleAnimation.setDuration(600);
                    scaleAnimation.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation);
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            if (!bool.booleanValue()) {
                                translateAnimation = new TranslateAnimation(0.0f, width * f7, 0.0f, height);
                                break;
                            } else {
                                translateAnimation = new TranslateAnimation(width * f7, 0.0f, (-height) * 0.4f, 0.0f);
                                break;
                            }
                        case 3:
                            if (!bool.booleanValue()) {
                                translateAnimation = new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f7);
                                break;
                            } else {
                                translateAnimation = new TranslateAnimation(width, 0.0f, height * f7, 0.0f);
                                break;
                            }
                        case 4:
                            if (!bool.booleanValue()) {
                                translateAnimation = new TranslateAnimation(0.0f, width * f7, 0.0f, (-height) * 0.4f);
                                break;
                            } else {
                                translateAnimation = new TranslateAnimation(width * f7, 0.0f, height, 0.0f);
                                break;
                            }
                        default:
                            if (!bool.booleanValue()) {
                                translateAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f7);
                                break;
                            } else {
                                translateAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f7, 0.0f);
                                break;
                            }
                    }
                    translateAnimation.setDuration(600);
                    translateAnimation.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation);
                    break;
                case 3:
                    if (!bool.booleanValue()) {
                        ScaleAnimation scaleAnimation3 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation3.setDuration(600);
                        scaleAnimation3.setStartOffset(0);
                        scaleAnimation3.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation3);
                        break;
                    } else {
                        ScaleAnimation scaleAnimation4 = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation4.setDuration((long) Math.round(((float) 600) * 0.6f));
                        scaleAnimation4.setStartOffset(0);
                        scaleAnimation4.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation4);
                        ScaleAnimation scaleAnimation5 = new ScaleAnimation(1.0f, 0.81818175f, 1.0f, 0.81818175f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation5.setDuration((long) Math.round(((float) 600) * 0.19999999f));
                        scaleAnimation5.setStartOffset((long) Math.round(((float) 600) * 0.6f));
                        scaleAnimation5.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation5);
                        ScaleAnimation scaleAnimation6 = new ScaleAnimation(1.0f, 1.1111112f, 1.0f, 1.1111112f, 1, 0.5f, 1, 0.5f);
                        scaleAnimation6.setDuration((long) Math.round(((float) 600) * 0.099999964f));
                        scaleAnimation6.setStartOffset((long) Math.round(((float) 600) * 0.8f));
                        scaleAnimation6.setFillAfter(true);
                        animationSet.addAnimation(scaleAnimation6);
                        break;
                    }
                case 4:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            if (!bool.booleanValue()) {
                                pVar2 = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            } else {
                                pVar2 = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            }
                        case 3:
                            if (!bool.booleanValue()) {
                                pVar2 = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            } else {
                                pVar2 = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            }
                        case 4:
                            if (!bool.booleanValue()) {
                                pVar2 = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            } else {
                                pVar2 = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                                break;
                            }
                        default:
                            if (!bool.booleanValue()) {
                                pVar2 = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            } else {
                                pVar2 = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                                break;
                            }
                    }
                    pVar2.setDuration(600);
                    pVar2.setFillAfter(true);
                    animationSet.addAnimation(pVar2);
                    if (bool.booleanValue()) {
                        scaleAnimation2 = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                    } else {
                        scaleAnimation2 = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                    }
                    scaleAnimation2.setDuration(600);
                    scaleAnimation2.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation2);
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 2:
                            if (!bool.booleanValue()) {
                                translateAnimation2 = new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f7);
                                break;
                            } else {
                                translateAnimation2 = new TranslateAnimation(width, 0.0f, height * f7, 0.0f);
                                break;
                            }
                        case 3:
                            if (!bool.booleanValue()) {
                                translateAnimation2 = new TranslateAnimation(0.0f, width * f7, 0.0f, (-height) * 0.4f);
                                break;
                            } else {
                                translateAnimation2 = new TranslateAnimation(width * f7, 0.0f, height, 0.0f);
                                break;
                            }
                        case 4:
                            if (!bool.booleanValue()) {
                                translateAnimation2 = new TranslateAnimation(0.0f, width, 0.0f, height * f7);
                                break;
                            } else {
                                translateAnimation2 = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f7, 0.0f);
                                break;
                            }
                        default:
                            if (!bool.booleanValue()) {
                                translateAnimation2 = new TranslateAnimation(0.0f, width * f7, 0.0f, height);
                                break;
                            } else {
                                translateAnimation2 = new TranslateAnimation(width * f7, 0.0f, (-height) * 0.4f, 0.0f);
                                break;
                            }
                    }
                    translateAnimation2.setDuration(600);
                    translateAnimation2.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation2);
                    break;
                case 5:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 1:
                            f6 = bool.booleanValue() ? height : 0.0f;
                            if (bool.booleanValue()) {
                                height = 0.0f;
                            }
                            f4 = 0.0f;
                            f5 = 0.0f;
                            break;
                        case 2:
                            float f8 = bool.booleanValue() ? -width : 0.0f;
                            f4 = bool.booleanValue() ? 0.0f : -width;
                            height = 0.0f;
                            f5 = f8;
                            f6 = 0.0f;
                            break;
                        case 3:
                            f6 = bool.booleanValue() ? -height : 0.0f;
                            height = bool.booleanValue() ? 0.0f : -height;
                            f5 = 0.0f;
                            f4 = 0.0f;
                            break;
                        case 4:
                            float f9 = bool.booleanValue() ? width : 0.0f;
                            if (bool.booleanValue()) {
                                width = 0.0f;
                            }
                            height = 0.0f;
                            f4 = width;
                            f5 = f9;
                            f6 = 0.0f;
                            break;
                        default:
                            height = 0.0f;
                            f6 = 0.0f;
                            f4 = 0.0f;
                            f5 = 0.0f;
                            break;
                    }
                    TranslateAnimation translateAnimation3 = new TranslateAnimation(f5, f4, f6, height);
                    translateAnimation3.setDuration(600);
                    translateAnimation3.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation3);
                    break;
                case 6:
                    switch (a()[forcedOrientationDifference.ordinal()]) {
                        case 1:
                            f3 = bool.booleanValue() ? -height : 0.0f;
                            f = bool.booleanValue() ? 0.0f : -height;
                            width = 0.0f;
                            f2 = 0.0f;
                            break;
                        case 2:
                            float f10 = bool.booleanValue() ? width : 0.0f;
                            if (bool.booleanValue()) {
                                width = 0.0f;
                            }
                            f = 0.0f;
                            f2 = f10;
                            f3 = 0.0f;
                            break;
                        case 3:
                            f3 = bool.booleanValue() ? height : 0.0f;
                            if (bool.booleanValue()) {
                                height = 0.0f;
                            }
                            f = height;
                            width = 0.0f;
                            f2 = 0.0f;
                            break;
                        case 4:
                            float f11 = bool.booleanValue() ? -width : 0.0f;
                            width = bool.booleanValue() ? 0.0f : -width;
                            f = 0.0f;
                            f2 = f11;
                            f3 = 0.0f;
                            break;
                        default:
                            f = 0.0f;
                            f3 = 0.0f;
                            width = 0.0f;
                            f2 = 0.0f;
                            break;
                    }
                    TranslateAnimation translateAnimation4 = new TranslateAnimation(f2, width, f3, f);
                    translateAnimation4.setDuration(600);
                    translateAnimation4.setFillAfter(true);
                    animationSet.addAnimation(translateAnimation4);
                    break;
            }
            final a aVar3 = aVar2;
            final a aVar4 = aVar;
            animationSet.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    if (a.this != null) {
                        a.this.a(aVar4);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            c.startAnimation(animationSet);
        }
    }
}
