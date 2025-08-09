package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.gj;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CastDevice implements SafeParcelable {
    public static final Parcelable.Creator<CastDevice> CREATOR = new b();
    private String Ae;
    String Af;
    private Inet4Address Ag;
    private String Ah;
    private String Ai;
    private String Aj;
    private int Ak;
    private List<WebImage> Al;
    private int Am;
    private final int xJ;

    private CastDevice() {
        this(2, (String) null, (String) null, (String) null, (String) null, (String) null, -1, new ArrayList(), 0);
    }

    CastDevice(int versionCode, String deviceId, String hostAddress, String friendlyName, String modelName, String deviceVersion, int servicePort, List<WebImage> icons, int capabilities) {
        this.xJ = versionCode;
        this.Ae = deviceId;
        this.Af = hostAddress;
        if (this.Af != null) {
            try {
                InetAddress byName = InetAddress.getByName(this.Af);
                if (byName instanceof Inet4Address) {
                    this.Ag = (Inet4Address) byName;
                }
            } catch (UnknownHostException e) {
                this.Ag = null;
            }
        }
        this.Ah = friendlyName;
        this.Ai = modelName;
        this.Aj = deviceVersion;
        this.Ak = servicePort;
        this.Al = icons;
        this.Am = capabilities;
    }

    public static CastDevice getFromBundle(Bundle extras) {
        if (extras == null) {
            return null;
        }
        extras.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) extras.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        return getDeviceId() == null ? castDevice.getDeviceId() == null : gj.a(this.Ae, castDevice.Ae) && gj.a(this.Ag, castDevice.Ag) && gj.a(this.Ai, castDevice.Ai) && gj.a(this.Ah, castDevice.Ah) && gj.a(this.Aj, castDevice.Aj) && this.Ak == castDevice.Ak && gj.a(this.Al, castDevice.Al) && this.Am == castDevice.Am;
    }

    public int getCapabilities() {
        return this.Am;
    }

    public String getDeviceId() {
        return this.Ae;
    }

    public String getDeviceVersion() {
        return this.Aj;
    }

    public String getFriendlyName() {
        return this.Ah;
    }

    public WebImage getIcon(int preferredWidth, int preferredHeight) {
        WebImage webImage;
        WebImage webImage2 = null;
        if (this.Al.isEmpty()) {
            return null;
        }
        if (preferredWidth <= 0 || preferredHeight <= 0) {
            return this.Al.get(0);
        }
        WebImage webImage3 = null;
        for (WebImage next : this.Al) {
            int width = next.getWidth();
            int height = next.getHeight();
            if (width < preferredWidth || height < preferredHeight) {
                if (width < preferredWidth && height < preferredHeight && (webImage2 == null || (webImage2.getWidth() < width && webImage2.getHeight() < height))) {
                    webImage = webImage3;
                }
                next = webImage2;
                webImage = webImage3;
            } else {
                if (webImage3 == null || (webImage3.getWidth() > width && webImage3.getHeight() > height)) {
                    WebImage webImage4 = webImage2;
                    webImage = next;
                    next = webImage4;
                }
                next = webImage2;
                webImage = webImage3;
            }
            webImage3 = webImage;
            webImage2 = next;
        }
        if (webImage3 == null) {
            webImage3 = webImage2 != null ? webImage2 : this.Al.get(0);
        }
        return webImage3;
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.Al);
    }

    public Inet4Address getIpAddress() {
        return this.Ag;
    }

    public String getModelName() {
        return this.Ai;
    }

    public int getServicePort() {
        return this.Ak;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public boolean hasIcons() {
        return !this.Al.isEmpty();
    }

    public int hashCode() {
        if (this.Ae == null) {
            return 0;
        }
        return this.Ae.hashCode();
    }

    public boolean isSameDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return false;
        }
        if (getDeviceId() == null) {
            return castDevice.getDeviceId() == null;
        }
        return gj.a(getDeviceId(), castDevice.getDeviceId());
    }

    public void putInBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
        }
    }

    public String toString() {
        return String.format("\"%s\" (%s)", new Object[]{this.Ah, this.Ae});
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
