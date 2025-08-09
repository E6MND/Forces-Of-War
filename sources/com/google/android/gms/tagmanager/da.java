package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class da implements ab {
    private final Context ahH;
    private final String ahY = a("GoogleTagManager", "4.00", Build.VERSION.RELEASE, c(Locale.getDefault()), Build.MODEL, Build.ID);
    private final HttpClient ahZ;
    private a aia;

    public interface a {
        void a(ap apVar);

        void b(ap apVar);

        void c(ap apVar);
    }

    da(HttpClient httpClient, Context context, a aVar) {
        this.ahH = context.getApplicationContext();
        this.ahZ = httpClient;
        this.aia = aVar;
    }

    private HttpEntityEnclosingRequest a(URL url) {
        URISyntaxException e;
        BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        try {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString());
            try {
                basicHttpEntityEnclosingRequest.addHeader("User-Agent", this.ahY);
            } catch (URISyntaxException e2) {
                e = e2;
                bh.D("Exception sending hit: " + e.getClass().getSimpleName());
                bh.D(e.getMessage());
                return basicHttpEntityEnclosingRequest;
            }
        } catch (URISyntaxException e3) {
            URISyntaxException uRISyntaxException = e3;
            basicHttpEntityEnclosingRequest = null;
            e = uRISyntaxException;
            bh.D("Exception sending hit: " + e.getClass().getSimpleName());
            bh.D(e.getMessage());
            return basicHttpEntityEnclosingRequest;
        }
        return basicHttpEntityEnclosingRequest;
    }

    private void a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        int available;
        StringBuffer stringBuffer = new StringBuffer();
        for (Header obj : httpEntityEnclosingRequest.getAllHeaders()) {
            stringBuffer.append(obj.toString()).append("\n");
        }
        stringBuffer.append(httpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        if (httpEntityEnclosingRequest.getEntity() != null) {
            try {
                InputStream content = httpEntityEnclosingRequest.getEntity().getContent();
                if (content != null && (available = content.available()) > 0) {
                    byte[] bArr = new byte[available];
                    content.read(bArr);
                    stringBuffer.append("POST:\n");
                    stringBuffer.append(new String(bArr)).append("\n");
                }
            } catch (IOException e) {
                bh.C("Error Writing hit to log...");
            }
        }
        bh.C(stringBuffer.toString());
    }

    static String c(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage().toLowerCase());
        if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
            sb.append("-").append(locale.getCountry().toLowerCase());
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public String a(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    public boolean cx() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.ahH.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        bh.C("...no network connectivity");
        return false;
    }

    /* access modifiers changed from: package-private */
    public URL d(ap apVar) {
        try {
            return new URL(apVar.lJ());
        } catch (MalformedURLException e) {
            bh.A("Error trying to parse the GTM url.");
            return null;
        }
    }

    public void g(List<ap> list) {
        boolean z;
        int min = Math.min(list.size(), 40);
        boolean z2 = true;
        int i = 0;
        while (i < min) {
            ap apVar = list.get(i);
            URL d = d(apVar);
            if (d == null) {
                bh.D("No destination: discarding hit.");
                this.aia.b(apVar);
                z = z2;
            } else {
                HttpEntityEnclosingRequest a2 = a(d);
                if (a2 == null) {
                    this.aia.b(apVar);
                    z = z2;
                } else {
                    HttpHost httpHost = new HttpHost(d.getHost(), d.getPort(), d.getProtocol());
                    a2.addHeader("Host", httpHost.toHostString());
                    a(a2);
                    if (z2) {
                        try {
                            bn.t(this.ahH);
                            z2 = false;
                        } catch (ClientProtocolException e) {
                            bh.D("ClientProtocolException sending hit; discarding hit...");
                            this.aia.b(apVar);
                            z = z2;
                        } catch (IOException e2) {
                            bh.D("Exception sending hit: " + e2.getClass().getSimpleName());
                            bh.D(e2.getMessage());
                            this.aia.c(apVar);
                            z = z2;
                        }
                    }
                    HttpResponse execute = this.ahZ.execute(httpHost, a2);
                    int statusCode = execute.getStatusLine().getStatusCode();
                    HttpEntity entity = execute.getEntity();
                    if (entity != null) {
                        entity.consumeContent();
                    }
                    if (statusCode != 200) {
                        bh.D("Bad response: " + execute.getStatusLine().getStatusCode());
                        this.aia.c(apVar);
                    } else {
                        this.aia.a(apVar);
                    }
                    z = z2;
                }
            }
            i++;
            z2 = z;
        }
    }
}
