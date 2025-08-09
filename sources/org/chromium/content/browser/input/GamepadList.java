package org.chromium.content.browser.input;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.util.TimeUtils;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;

@JNINamespace("content")
public class GamepadList {
    static final /* synthetic */ boolean $assertionsDisabled = (!GamepadList.class.desiredAssertionStatus());
    private static final int MAX_GAMEPADS = 4;
    private int mAttachedToWindowCounter;
    private final GamepadDevice[] mGamepadDevices;
    private InputManager.InputDeviceListener mInputDeviceListener;
    private InputManager mInputManager;
    private boolean mIsGamepadAccessed;
    private final Object mLock;

    private native void nativeSetGamepadData(long j, int i, boolean z, boolean z2, String str, long j2, float[] fArr, float[] fArr2);

    @TargetApi(16)
    private GamepadList() {
        this.mLock = new Object();
        this.mGamepadDevices = new GamepadDevice[4];
        this.mInputDeviceListener = new InputManager.InputDeviceListener() {
            public void onInputDeviceChanged(int deviceId) {
                GamepadList.this.onInputDeviceChangedImpl(deviceId);
            }

            public void onInputDeviceRemoved(int deviceId) {
                GamepadList.this.onInputDeviceRemovedImpl(deviceId);
            }

            public void onInputDeviceAdded(int deviceId) {
                GamepadList.this.onInputDeviceAddedImpl(deviceId);
            }
        };
    }

    @TargetApi(16)
    private void initializeDevices() {
        int[] deviceIds = this.mInputManager.getInputDeviceIds();
        for (int device : deviceIds) {
            InputDevice inputDevice = InputDevice.getDevice(device);
            if (isGamepadDevice(inputDevice)) {
                registerGamepad(inputDevice);
            }
        }
    }

    public static void onAttachedToWindow(Context context) {
        if (!$assertionsDisabled && !ThreadUtils.runningOnUiThread()) {
            throw new AssertionError();
        } else if (isGamepadSupported()) {
            getInstance().attachedToWindow(context);
        }
    }

    @TargetApi(16)
    private void attachedToWindow(Context context) {
        int i = this.mAttachedToWindowCounter;
        this.mAttachedToWindowCounter = i + 1;
        if (i == 0) {
            this.mInputManager = (InputManager) context.getSystemService("input");
            synchronized (this.mLock) {
                initializeDevices();
            }
            this.mInputManager.registerInputDeviceListener(this.mInputDeviceListener, (Handler) null);
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public static void onDetachedFromWindow() {
        if (!$assertionsDisabled && !ThreadUtils.runningOnUiThread()) {
            throw new AssertionError();
        } else if (isGamepadSupported()) {
            getInstance().detachedFromWindow();
        }
    }

    @TargetApi(16)
    private void detachedFromWindow() {
        int i = this.mAttachedToWindowCounter - 1;
        this.mAttachedToWindowCounter = i;
        if (i == 0) {
            synchronized (this.mLock) {
                for (int i2 = 0; i2 < 4; i2++) {
                    this.mGamepadDevices[i2] = null;
                }
            }
            this.mInputManager.unregisterInputDeviceListener(this.mInputDeviceListener);
            this.mInputManager = null;
        }
    }

    /* access modifiers changed from: private */
    public void onInputDeviceChangedImpl(int deviceId) {
    }

    /* access modifiers changed from: private */
    public void onInputDeviceRemovedImpl(int deviceId) {
        synchronized (this.mLock) {
            unregisterGamepad(deviceId);
        }
    }

    /* access modifiers changed from: private */
    public void onInputDeviceAddedImpl(int deviceId) {
        InputDevice inputDevice = InputDevice.getDevice(deviceId);
        if (isGamepadDevice(inputDevice)) {
            synchronized (this.mLock) {
                registerGamepad(inputDevice);
            }
        }
    }

    private static GamepadList getInstance() {
        if ($assertionsDisabled || isGamepadSupported()) {
            return LazyHolder.INSTANCE;
        }
        throw new AssertionError();
    }

    private int getDeviceCount() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (getDevice(i) != null) {
                count++;
            }
        }
        return count;
    }

    private boolean isDeviceConnected(int index) {
        if (index >= 4 || getDevice(index) == null) {
            return false;
        }
        return true;
    }

    private GamepadDevice getDeviceById(int deviceId) {
        for (int i = 0; i < 4; i++) {
            GamepadDevice gamepad = this.mGamepadDevices[i];
            if (gamepad != null && gamepad.getId() == deviceId) {
                return gamepad;
            }
        }
        return null;
    }

    private GamepadDevice getDevice(int index) {
        if ($assertionsDisabled || (index >= 0 && index < 4)) {
            return this.mGamepadDevices[index];
        }
        throw new AssertionError();
    }

    public static boolean dispatchKeyEvent(KeyEvent event) {
        if (isGamepadSupported() && isGamepadEvent(event)) {
            return getInstance().handleKeyEvent(event);
        }
        return false;
    }

    private boolean handleKeyEvent(KeyEvent event) {
        boolean z = false;
        synchronized (this.mLock) {
            if (this.mIsGamepadAccessed) {
                GamepadDevice gamepad = getGamepadForEvent(event);
                if (gamepad != null) {
                    z = gamepad.handleKeyEvent(event);
                }
            }
        }
        return z;
    }

    public static boolean onGenericMotionEvent(MotionEvent event) {
        if (isGamepadSupported() && isGamepadEvent(event)) {
            return getInstance().handleMotionEvent(event);
        }
        return false;
    }

    private boolean handleMotionEvent(MotionEvent event) {
        boolean z = false;
        synchronized (this.mLock) {
            if (this.mIsGamepadAccessed) {
                GamepadDevice gamepad = getGamepadForEvent(event);
                if (gamepad != null) {
                    z = gamepad.handleMotionEvent(event);
                }
            }
        }
        return z;
    }

    private int getNextAvailableIndex() {
        for (int i = 0; i < 4; i++) {
            if (getDevice(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private boolean registerGamepad(InputDevice inputDevice) {
        int index = getNextAvailableIndex();
        if (index == -1) {
            return false;
        }
        this.mGamepadDevices[index] = new GamepadDevice(index, inputDevice);
        return true;
    }

    private void unregisterGamepad(int deviceId) {
        GamepadDevice gamepadDevice = getDeviceById(deviceId);
        if (gamepadDevice != null) {
            this.mGamepadDevices[gamepadDevice.getIndex()] = null;
        }
    }

    private static boolean isGamepadDevice(InputDevice inputDevice) {
        if (inputDevice != null && (inputDevice.getSources() & 16777232) == 16777232) {
            return true;
        }
        return false;
    }

    private GamepadDevice getGamepadForEvent(InputEvent event) {
        return getDeviceById(event.getDeviceId());
    }

    public static boolean isGamepadEvent(MotionEvent event) {
        return (event.getSource() & 16777232) == 16777232;
    }

    public static boolean isGamepadEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case TimeUtils.HUNDRED_DAY_FIELD_LEN:
            case BrailleInputEvent.CMD_ACTIVATE_CURRENT:
            case 21:
            case 22:
                return true;
            default:
                return KeyEvent.isGamepadButton(keyCode);
        }
    }

    private static boolean isGamepadSupported() {
        return Build.VERSION.SDK_INT >= 16;
    }

    @CalledByNative
    static void updateGamepadData(long webGamepadsPtr) {
        if (isGamepadSupported()) {
            getInstance().grabGamepadData(webGamepadsPtr);
        }
    }

    private void grabGamepadData(long webGamepadsPtr) {
        synchronized (this.mLock) {
            for (int i = 0; i < 4; i++) {
                GamepadDevice device = getDevice(i);
                if (device != null) {
                    device.updateButtonsAndAxesMapping();
                    nativeSetGamepadData(webGamepadsPtr, i, device.isStandardGamepad(), true, device.getName(), device.getTimestamp(), device.getAxes(), device.getButtons());
                } else {
                    nativeSetGamepadData(webGamepadsPtr, i, false, false, (String) null, 0, (float[]) null, (float[]) null);
                }
            }
        }
    }

    @CalledByNative
    static void notifyForGamepadsAccess(boolean isAccessPaused) {
        if (isGamepadSupported()) {
            getInstance().setIsGamepadAccessed(!isAccessPaused);
        }
    }

    private void setIsGamepadAccessed(boolean isGamepadAccessed) {
        synchronized (this.mLock) {
            this.mIsGamepadAccessed = isGamepadAccessed;
            if (isGamepadAccessed) {
                for (int i = 0; i < 4; i++) {
                    GamepadDevice gamepadDevice = getDevice(i);
                    if (gamepadDevice != null) {
                        gamepadDevice.clearData();
                    }
                }
            }
        }
    }

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final GamepadList INSTANCE = new GamepadList();

        private LazyHolder() {
        }
    }
}
