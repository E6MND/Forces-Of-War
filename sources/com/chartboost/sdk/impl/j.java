package com.chartboost.sdk.impl;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.d;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class j {
    /* access modifiers changed from: private */
    public static int g = 0;
    private static HttpClient h = null;
    public String a;
    public c b;
    public String c;
    public SparseArray<b> d;
    public int e = 1;
    public String f = "Loading...";

    public interface c {
        void a(k kVar);

        void a(JSONObject jSONObject, k kVar);
    }

    protected class b implements Serializable {
        public k a;
        public JSONObject b;
        public Integer c;

        public b(k kVar, JSONObject jSONObject) {
            this.a = kVar;
            this.b = jSONObject;
        }
    }

    public j(String str, c cVar, String str2) {
        this.a = str == null ? "https://www.chartboost.com" : str;
        this.b = cVar;
        this.c = str2;
        this.d = new SparseArray<>();
    }

    public void a(k kVar) {
        if (!l.a()) {
            b(kVar);
            return;
        }
        int i = this.e;
        this.e = i + 1;
        b bVar = new b(kVar, (JSONObject) null);
        bVar.c = Integer.valueOf(i);
        this.d.put(i, bVar);
        new a().execute(new b[]{bVar});
    }

    /* access modifiers changed from: private */
    public void b(k kVar) {
        JSONArray jSONArray;
        if (kVar.i && this.c != null) {
            SharedPreferences a2 = d.a();
            String str = "CBQueuedRequests-" + this.c;
            try {
                JSONObject d2 = kVar.d();
                if (d2 != null) {
                    String string = a2.getString(str, (String) null);
                    if (string != null) {
                        try {
                            jSONArray = new JSONArray(new JSONTokener(string));
                        } catch (Exception e2) {
                            Log.w("Chartboost", "Failure reading saved request list");
                            jSONArray = new JSONArray();
                        }
                    } else {
                        jSONArray = new JSONArray();
                    }
                    jSONArray.put(d2);
                    SharedPreferences.Editor edit = a2.edit();
                    edit.putString(str, jSONArray.toString());
                    edit.commit();
                }
            } catch (Exception e3) {
                Log.w("Chartboost", "Unable to save failed request");
            }
        } else if (this.b != null) {
            this.b.a(kVar);
        }
    }

    public void a() {
        SharedPreferences a2;
        String str;
        String string;
        if (l.a() && (string = a2.getString(str, (String) null)) != null) {
            SharedPreferences.Editor edit = (a2 = d.a()).edit();
            edit.putString((str = "CBQueuedRequests-" + this.c), (String) null);
            edit.commit();
            try {
                JSONArray jSONArray = new JSONArray(new JSONTokener(string));
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        k a3 = k.a(jSONArray.getJSONObject(i));
                        if (a3 != null) {
                            a(a3);
                        }
                    } catch (Exception e2) {
                        Log.w("Chartboost", "Retrying request failed");
                    }
                }
            } catch (Exception e3) {
                Log.w("Chartboost", "Retrying request list failed");
            }
        }
    }

    protected class a extends AsyncTask<b, Void, b> {
        protected a() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public b doInBackground(b... bVarArr) {
            int c;
            b bVar = bVarArr[0];
            k kVar = bVar.a;
            HttpPost httpPost = new HttpPost(String.valueOf(j.this.a) + kVar.a());
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpPost.setHeader("Accept", "application/json; charset=UTF-8");
            httpPost.setHeader("X-Chartboost-Client", "Chartboost-Android-SDK 3.1.5");
            httpPost.setHeader("X-Chartboost-API", "3.1.5");
            Map<String, String> c2 = kVar.c();
            if (c2 != null) {
                for (String next : c2.keySet()) {
                    httpPost.setHeader(next, c2.get(next));
                }
            }
            synchronized (j.this) {
                c = j.g + 1;
                j.g = c;
            }
            HttpResponse httpResponse = null;
            try {
                if (kVar.e != null) {
                    StringEntity stringEntity = new StringEntity(kVar.e.toString());
                    stringEntity.setContentType(new BasicHeader("Content-Type", "application/json"));
                    httpPost.setEntity(stringEntity);
                } else {
                    Log.i("HTTP Request Body " + c + " " + kVar.b, "<empty>");
                }
                httpResponse = j.b().execute(httpPost);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode >= 300 || statusCode < 200) {
                    Log.w("Chartboost", "Request failed. Response code: " + statusCode + ", body: " + httpResponse);
                    httpResponse.getEntity().consumeContent();
                    return bVar;
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"), 2048);
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        bufferedReader.close();
                        kVar.j = new JSONObject(new JSONTokener(sb.toString()));
                        httpResponse.getEntity().consumeContent();
                        return bVar;
                    }
                    sb.append(readLine).append("\n");
                }
            } catch (Exception e) {
                Log.e("Chartboost", "Exception on http request: " + e.getLocalizedMessage());
                if (httpResponse != null) {
                    try {
                        if (httpResponse.getEntity() != null) {
                            httpResponse.getEntity().consumeContent();
                        }
                    } catch (Exception e2) {
                    }
                }
                return bVar;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(b bVar) {
            j.this.d.remove(bVar.c.intValue());
            if (bVar.a.j == null) {
                j.this.b(bVar.a);
            } else if (j.this.b != null) {
                j.this.b.a(bVar.a.j, bVar.a);
            }
        }
    }

    public static HttpClient b() {
        if (h != null) {
            return h;
        }
        try {
            final Application application = (Application) Chartboost.sharedChartboost().getContext().getApplicationContext();
            h = new DefaultHttpClient() {
                /* access modifiers changed from: protected */
                public ClientConnectionManager createClientConnectionManager() {
                    SchemeRegistry schemeRegistry = new SchemeRegistry();
                    schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                    schemeRegistry.register(new Scheme("https", a(), 443));
                    HttpParams params = getParams();
                    HttpConnectionParams.setConnectionTimeout(params, Chartboost.sharedChartboost().getTimeout());
                    HttpConnectionParams.setSoTimeout(params, Chartboost.sharedChartboost().getTimeout());
                    HttpProtocolParams.setUserAgent(params, j.b(application, HttpProtocolParams.getUserAgent(params)));
                    return new ThreadSafeClientConnManager(params, schemeRegistry);
                }

                /* access modifiers changed from: protected */
                public SocketFactory a() {
                    try {
                        Class<?> cls = Class.forName("android.net.SSLSessionCache");
                        Object newInstance = cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{application});
                        return (SocketFactory) Class.forName("android.net.SSLCertificateSocketFactory").getMethod("getHttpSocketFactory", new Class[]{Integer.TYPE, cls}).invoke((Object) null, new Object[]{Integer.valueOf(Chartboost.sharedChartboost().getTimeout()), newInstance});
                    } catch (Exception e) {
                        return SSLSocketFactory.getSocketFactory();
                    }
                }
            };
            return h;
        } catch (Exception e2) {
            try {
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                ClientConnectionManager connectionManager = defaultHttpClient.getConnectionManager();
                HttpParams params = defaultHttpClient.getParams();
                h = new DefaultHttpClient(new ThreadSafeClientConnManager(params, connectionManager.getSchemeRegistry()), params);
                return h;
            } catch (Exception e3) {
                h = new DefaultHttpClient();
                return h;
            }
        }
    }

    /* access modifiers changed from: private */
    public static String b(Application application, String str) {
        try {
            String str2 = application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionName;
            StringBuilder sb = new StringBuilder();
            sb.append(application.getPackageName());
            sb.append("/");
            sb.append(str2);
            sb.append(" (");
            sb.append("Linux; U; Android ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("; ");
            sb.append(Locale.getDefault());
            sb.append("; ");
            sb.append(Build.PRODUCT);
            sb.append(")");
            if (str != null) {
                sb.append(" ");
                sb.append(str);
            }
            return sb.toString();
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException(e2);
        }
    }
}
