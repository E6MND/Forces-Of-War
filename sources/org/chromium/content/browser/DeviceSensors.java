package org.chromium.content.browser;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import org.chromium.base.CalledByNative;
import org.chromium.base.CollectionUtil;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

@JNINamespace("content")
class DeviceSensors implements SensorEventListener {
    static final int DEVICE_LIGHT = 2;
    static final Set<Integer> DEVICE_LIGHT_SENSORS = CollectionUtil.newHashSet(5);
    static final int DEVICE_MOTION = 1;
    static final Set<Integer> DEVICE_MOTION_SENSORS = CollectionUtil.newHashSet(1, 10, 4);
    static final int DEVICE_ORIENTATION = 0;
    static final Set<Integer> DEVICE_ORIENTATION_BACKUP_SENSORS = CollectionUtil.newHashSet(1, 2);
    static final Set<Integer> DEVICE_ORIENTATION_DEFAULT_SENSORS = CollectionUtil.newHashSet(11);
    private static final String TAG = "DeviceSensors";
    private static DeviceSensors sSingleton;
    private static Object sSingletonLock = new Object();
    private float[] mAccelerationIncludingGravityVector;
    @VisibleForTesting
    final Set<Integer> mActiveSensors = new HashSet();
    /* access modifiers changed from: private */
    public final Context mAppContext;
    boolean mDeviceLightIsActive = false;
    boolean mDeviceMotionIsActive = false;
    boolean mDeviceOrientationIsActive = false;
    Set<Integer> mDeviceOrientationSensors = DEVICE_ORIENTATION_DEFAULT_SENSORS;
    private Handler mHandler;
    private final Object mHandlerLock = new Object();
    private float[] mMagneticFieldVector;
    private long mNativePtr;
    private final Object mNativePtrLock = new Object();
    private SensorManagerProxy mSensorManagerProxy;
    private Thread mThread;
    private float[] mTruncatedRotationVector;
    boolean mUseBackupOrientationSensors = false;

    interface SensorManagerProxy {
        boolean registerListener(SensorEventListener sensorEventListener, int i, int i2, Handler handler);

        void unregisterListener(SensorEventListener sensorEventListener, int i);
    }

    private native void nativeGotAcceleration(long j, double d, double d2, double d3);

    private native void nativeGotAccelerationIncludingGravity(long j, double d, double d2, double d3);

    private native void nativeGotLight(long j, double d);

    private native void nativeGotOrientation(long j, double d, double d2, double d3);

    private native void nativeGotRotationRate(long j, double d, double d2, double d3);

    protected DeviceSensors(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return r0;
     */
    @org.chromium.base.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean start(long r8, int r10, int r11) {
        /*
            r7 = this;
            r1 = 0
            r0 = 0
            java.lang.Object r2 = r7.mNativePtrLock
            monitor-enter(r2)
            switch(r10) {
                case 0: goto L_0x0022;
                case 1: goto L_0x0043;
                case 2: goto L_0x004b;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.String r3 = "DeviceSensors"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0053 }
            r4.<init>()     // Catch:{ all -> 0x0053 }
            java.lang.String r5 = "Unknown event type: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0053 }
            java.lang.StringBuilder r4 = r4.append(r10)     // Catch:{ all -> 0x0053 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0053 }
            android.util.Log.e(r3, r4)     // Catch:{ all -> 0x0053 }
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
        L_0x0021:
            return r1
        L_0x0022:
            java.util.Set<java.lang.Integer> r1 = r7.mDeviceOrientationSensors     // Catch:{ all -> 0x0053 }
            r3 = 1
            boolean r0 = r7.registerSensors(r1, r11, r3)     // Catch:{ all -> 0x0053 }
            if (r0 != 0) goto L_0x0038
            java.util.Set<java.lang.Integer> r1 = DEVICE_ORIENTATION_BACKUP_SENSORS     // Catch:{ all -> 0x0053 }
            r7.mDeviceOrientationSensors = r1     // Catch:{ all -> 0x0053 }
            java.util.Set<java.lang.Integer> r1 = r7.mDeviceOrientationSensors     // Catch:{ all -> 0x0053 }
            r3 = 1
            boolean r0 = r7.registerSensors(r1, r11, r3)     // Catch:{ all -> 0x0053 }
            r7.mUseBackupOrientationSensors = r0     // Catch:{ all -> 0x0053 }
        L_0x0038:
            if (r0 == 0) goto L_0x0040
            r7.mNativePtr = r8     // Catch:{ all -> 0x0053 }
            r1 = 1
            r7.setEventTypeActive(r10, r1)     // Catch:{ all -> 0x0053 }
        L_0x0040:
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            r1 = r0
            goto L_0x0021
        L_0x0043:
            java.util.Set<java.lang.Integer> r1 = DEVICE_MOTION_SENSORS     // Catch:{ all -> 0x0053 }
            r3 = 0
            boolean r0 = r7.registerSensors(r1, r11, r3)     // Catch:{ all -> 0x0053 }
            goto L_0x0038
        L_0x004b:
            java.util.Set<java.lang.Integer> r1 = DEVICE_LIGHT_SENSORS     // Catch:{ all -> 0x0053 }
            r3 = 1
            boolean r0 = r7.registerSensors(r1, r11, r3)     // Catch:{ all -> 0x0053 }
            goto L_0x0038
        L_0x0053:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.DeviceSensors.start(long, int, int):boolean");
    }

    @CalledByNative
    public int getNumberActiveDeviceMotionSensors() {
        Set<Integer> deviceMotionSensors = new HashSet<>(DEVICE_MOTION_SENSORS);
        deviceMotionSensors.removeAll(this.mActiveSensors);
        return DEVICE_MOTION_SENSORS.size() - deviceMotionSensors.size();
    }

    @CalledByNative
    public boolean isUsingBackupSensorsForOrientation() {
        return this.mUseBackupOrientationSensors;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    @org.chromium.base.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop(int r7) {
        /*
            r6 = this;
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.lang.Object r3 = r6.mNativePtrLock
            monitor-enter(r3)
            switch(r7) {
                case 0: goto L_0x0025;
                case 1: goto L_0x0059;
                case 2: goto L_0x006c;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.String r2 = "DeviceSensors"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0056 }
            r4.<init>()     // Catch:{ all -> 0x0056 }
            java.lang.String r5 = "Unknown event type: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x0056 }
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch:{ all -> 0x0056 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0056 }
            android.util.Log.e(r2, r4)     // Catch:{ all -> 0x0056 }
            monitor-exit(r3)     // Catch:{ all -> 0x0056 }
        L_0x0024:
            return
        L_0x0025:
            boolean r2 = r6.mDeviceMotionIsActive     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x002e
            java.util.Set<java.lang.Integer> r2 = DEVICE_MOTION_SENSORS     // Catch:{ all -> 0x0056 }
            r1.addAll(r2)     // Catch:{ all -> 0x0056 }
        L_0x002e:
            boolean r2 = r6.mDeviceLightIsActive     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0037
            java.util.Set<java.lang.Integer> r2 = DEVICE_LIGHT_SENSORS     // Catch:{ all -> 0x0056 }
            r1.addAll(r2)     // Catch:{ all -> 0x0056 }
        L_0x0037:
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x0056 }
            java.util.Set<java.lang.Integer> r2 = r6.mActiveSensors     // Catch:{ all -> 0x0056 }
            r0.<init>(r2)     // Catch:{ all -> 0x0056 }
            r0.removeAll(r1)     // Catch:{ all -> 0x0056 }
            r6.unregisterSensors(r0)     // Catch:{ all -> 0x0056 }
            r2 = 0
            r6.setEventTypeActive(r7, r2)     // Catch:{ all -> 0x0056 }
            java.util.Set<java.lang.Integer> r2 = r6.mActiveSensors     // Catch:{ all -> 0x0056 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0054
            r4 = 0
            r6.mNativePtr = r4     // Catch:{ all -> 0x0056 }
        L_0x0054:
            monitor-exit(r3)     // Catch:{ all -> 0x0056 }
            goto L_0x0024
        L_0x0056:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0056 }
            throw r2
        L_0x0059:
            boolean r2 = r6.mDeviceOrientationIsActive     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0062
            java.util.Set<java.lang.Integer> r2 = r6.mDeviceOrientationSensors     // Catch:{ all -> 0x0056 }
            r1.addAll(r2)     // Catch:{ all -> 0x0056 }
        L_0x0062:
            boolean r2 = r6.mDeviceLightIsActive     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0037
            java.util.Set<java.lang.Integer> r2 = DEVICE_LIGHT_SENSORS     // Catch:{ all -> 0x0056 }
            r1.addAll(r2)     // Catch:{ all -> 0x0056 }
            goto L_0x0037
        L_0x006c:
            boolean r2 = r6.mDeviceMotionIsActive     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0075
            java.util.Set<java.lang.Integer> r2 = DEVICE_MOTION_SENSORS     // Catch:{ all -> 0x0056 }
            r1.addAll(r2)     // Catch:{ all -> 0x0056 }
        L_0x0075:
            boolean r2 = r6.mDeviceOrientationIsActive     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0037
            java.util.Set<java.lang.Integer> r2 = r6.mDeviceOrientationSensors     // Catch:{ all -> 0x0056 }
            r1.addAll(r2)     // Catch:{ all -> 0x0056 }
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.DeviceSensors.stop(int):void");
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        sensorChanged(event.sensor.getType(), event.values);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void sensorChanged(int type, float[] values) {
        switch (type) {
            case 1:
                if (this.mDeviceMotionIsActive) {
                    gotAccelerationIncludingGravity((double) values[0], (double) values[1], (double) values[2]);
                }
                if (this.mDeviceOrientationIsActive && this.mUseBackupOrientationSensors) {
                    getOrientationFromGeomagneticVectors(values, this.mMagneticFieldVector);
                    return;
                }
                return;
            case 2:
                if (this.mDeviceOrientationIsActive && this.mUseBackupOrientationSensors) {
                    if (this.mMagneticFieldVector == null) {
                        this.mMagneticFieldVector = new float[3];
                    }
                    System.arraycopy(values, 0, this.mMagneticFieldVector, 0, this.mMagneticFieldVector.length);
                    return;
                }
                return;
            case 4:
                if (this.mDeviceMotionIsActive) {
                    gotRotationRate((double) values[0], (double) values[1], (double) values[2]);
                    return;
                }
                return;
            case 5:
                if (this.mDeviceLightIsActive) {
                    gotLight((double) values[0]);
                    return;
                }
                return;
            case 10:
                if (this.mDeviceMotionIsActive) {
                    gotAcceleration((double) values[0], (double) values[1], (double) values[2]);
                    return;
                }
                return;
            case 11:
                if (!this.mDeviceOrientationIsActive) {
                    return;
                }
                if (values.length > 4) {
                    if (this.mTruncatedRotationVector == null) {
                        this.mTruncatedRotationVector = new float[4];
                    }
                    System.arraycopy(values, 0, this.mTruncatedRotationVector, 0, 4);
                    getOrientationFromRotationVector(this.mTruncatedRotationVector);
                    return;
                }
                getOrientationFromRotationVector(values);
                return;
            default:
                return;
        }
    }

    @VisibleForTesting
    public static double[] computeDeviceOrientationFromRotationMatrix(float[] matrixR, double[] values) {
        if (matrixR.length == 9) {
            if (matrixR[8] > 0.0f) {
                values[0] = Math.atan2((double) (-matrixR[1]), (double) matrixR[4]);
                values[1] = Math.asin((double) matrixR[7]);
                values[2] = Math.atan2((double) (-matrixR[6]), (double) matrixR[8]);
            } else if (matrixR[8] < 0.0f) {
                values[0] = Math.atan2((double) matrixR[1], (double) (-matrixR[4]));
                values[1] = -Math.asin((double) matrixR[7]);
                values[1] = (values[1] >= 0.0d ? -3.141592653589793d : 3.141592653589793d) + values[1];
                values[2] = Math.atan2((double) matrixR[6], (double) (-matrixR[8]));
            } else if (matrixR[6] > 0.0f) {
                values[0] = Math.atan2((double) (-matrixR[1]), (double) matrixR[4]);
                values[1] = Math.asin((double) matrixR[7]);
                values[2] = -1.5707963267948966d;
            } else if (matrixR[6] < 0.0f) {
                values[0] = Math.atan2((double) matrixR[1], (double) (-matrixR[4]));
                values[1] = -Math.asin((double) matrixR[7]);
                values[1] = (values[1] >= 0.0d ? -3.141592653589793d : 3.141592653589793d) + values[1];
                values[2] = -1.5707963267948966d;
            } else {
                values[0] = Math.atan2((double) matrixR[3], (double) matrixR[0]);
                values[1] = matrixR[7] > 0.0f ? 1.5707963267948966d : -1.5707963267948966d;
                values[2] = 0.0d;
            }
            if (values[0] < 0.0d) {
                values[0] = values[0] + 6.283185307179586d;
            }
        }
        return values;
    }

    private void getOrientationFromRotationVector(float[] rotationVector) {
        float[] deviceRotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(deviceRotationMatrix, rotationVector);
        double[] rotationAngles = new double[3];
        computeDeviceOrientationFromRotationMatrix(deviceRotationMatrix, rotationAngles);
        gotOrientation(Math.toDegrees(rotationAngles[0]), Math.toDegrees(rotationAngles[1]), Math.toDegrees(rotationAngles[2]));
    }

    private void getOrientationFromGeomagneticVectors(float[] acceleration, float[] magnetic) {
        float[] deviceRotationMatrix = new float[9];
        if (acceleration != null && magnetic != null && SensorManager.getRotationMatrix(deviceRotationMatrix, (float[]) null, acceleration, magnetic)) {
            double[] rotationAngles = new double[3];
            computeDeviceOrientationFromRotationMatrix(deviceRotationMatrix, rotationAngles);
            gotOrientation(Math.toDegrees(rotationAngles[0]), Math.toDegrees(rotationAngles[1]), Math.toDegrees(rotationAngles[2]));
        }
    }

    private SensorManagerProxy getSensorManagerProxy() {
        if (this.mSensorManagerProxy != null) {
            return this.mSensorManagerProxy;
        }
        SensorManager sensorManager = (SensorManager) ThreadUtils.runOnUiThreadBlockingNoException(new Callable<SensorManager>() {
            public SensorManager call() {
                return (SensorManager) DeviceSensors.this.mAppContext.getSystemService("sensor");
            }
        });
        if (sensorManager != null) {
            this.mSensorManagerProxy = new SensorManagerProxyImpl(sensorManager);
        }
        return this.mSensorManagerProxy;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setSensorManagerProxy(SensorManagerProxy sensorManagerProxy) {
        this.mSensorManagerProxy = sensorManagerProxy;
    }

    private void setEventTypeActive(int eventType, boolean value) {
        switch (eventType) {
            case 0:
                this.mDeviceOrientationIsActive = value;
                return;
            case 1:
                this.mDeviceMotionIsActive = value;
                return;
            case 2:
                this.mDeviceLightIsActive = value;
                return;
            default:
                return;
        }
    }

    private boolean registerSensors(Set<Integer> sensorTypes, int rateInMicroseconds, boolean failOnMissingSensor) {
        Set<Integer> sensorsToActivate = new HashSet<>(sensorTypes);
        sensorsToActivate.removeAll(this.mActiveSensors);
        boolean success = false;
        for (Integer sensorType : sensorsToActivate) {
            boolean result = registerForSensorType(sensorType.intValue(), rateInMicroseconds);
            if (!result && failOnMissingSensor) {
                unregisterSensors(sensorsToActivate);
                return false;
            } else if (result) {
                this.mActiveSensors.add(sensorType);
                success = true;
            }
        }
        return success;
    }

    private void unregisterSensors(Iterable<Integer> sensorTypes) {
        for (Integer sensorType : sensorTypes) {
            if (this.mActiveSensors.contains(sensorType)) {
                getSensorManagerProxy().unregisterListener(this, sensorType.intValue());
                this.mActiveSensors.remove(sensorType);
            }
        }
    }

    private boolean registerForSensorType(int type, int rateInMicroseconds) {
        SensorManagerProxy sensorManager = getSensorManagerProxy();
        if (sensorManager == null) {
            return false;
        }
        return sensorManager.registerListener(this, type, rateInMicroseconds, getHandler());
    }

    /* access modifiers changed from: protected */
    public void gotOrientation(double alpha, double beta, double gamma) {
        synchronized (this.mNativePtrLock) {
            if (this.mNativePtr != 0) {
                nativeGotOrientation(this.mNativePtr, alpha, beta, gamma);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void gotAcceleration(double x, double y, double z) {
        synchronized (this.mNativePtrLock) {
            if (this.mNativePtr != 0) {
                nativeGotAcceleration(this.mNativePtr, x, y, z);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void gotAccelerationIncludingGravity(double x, double y, double z) {
        synchronized (this.mNativePtrLock) {
            if (this.mNativePtr != 0) {
                nativeGotAccelerationIncludingGravity(this.mNativePtr, x, y, z);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void gotRotationRate(double alpha, double beta, double gamma) {
        synchronized (this.mNativePtrLock) {
            if (this.mNativePtr != 0) {
                nativeGotRotationRate(this.mNativePtr, alpha, beta, gamma);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void gotLight(double value) {
        synchronized (this.mNativePtrLock) {
            if (this.mNativePtr != 0) {
                nativeGotLight(this.mNativePtr, value);
            }
        }
    }

    private Handler getHandler() {
        Handler handler;
        synchronized (this.mHandlerLock) {
            if (this.mHandler == null) {
                HandlerThread thread = new HandlerThread("DeviceMotionAndOrientation");
                thread.start();
                this.mHandler = new Handler(thread.getLooper());
            }
            handler = this.mHandler;
        }
        return handler;
    }

    @CalledByNative
    static DeviceSensors getInstance(Context appContext) {
        DeviceSensors deviceSensors;
        synchronized (sSingletonLock) {
            if (sSingleton == null) {
                sSingleton = new DeviceSensors(appContext);
            }
            deviceSensors = sSingleton;
        }
        return deviceSensors;
    }

    static class SensorManagerProxyImpl implements SensorManagerProxy {
        private final SensorManager mSensorManager;

        SensorManagerProxyImpl(SensorManager sensorManager) {
            this.mSensorManager = sensorManager;
        }

        public boolean registerListener(SensorEventListener listener, int sensorType, int rate, Handler handler) {
            List<Sensor> sensors = this.mSensorManager.getSensorList(sensorType);
            if (sensors.isEmpty()) {
                return false;
            }
            return this.mSensorManager.registerListener(listener, sensors.get(0), rate, handler);
        }

        public void unregisterListener(SensorEventListener listener, int sensorType) {
            List<Sensor> sensors = this.mSensorManager.getSensorList(sensorType);
            if (!sensors.isEmpty()) {
                this.mSensorManager.unregisterListener(listener, sensors.get(0));
            }
        }
    }
}
