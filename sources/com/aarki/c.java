package com.aarki;

import android.os.AsyncTask;
import com.aarki.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLException;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class c {

    interface b {
        void a(int i);

        void a(JSONObject jSONObject);
    }

    /* renamed from: com.aarki.c$c  reason: collision with other inner class name */
    static class C0002c {
        String a;
        int b;

        public C0002c(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    c(String str) {
    }

    public static String a(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update(str.getBytes("UTF-8"));
        byte[] digest = instance.digest();
        StringBuffer stringBuffer = new StringBuffer();
        Formatter formatter = new Formatter(stringBuffer);
        int length = digest.length;
        for (int i = 0; i < length; i++) {
            formatter.format("%02x", new Object[]{Byte.valueOf(digest[i])});
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, List<NameValuePair> list, b bVar) {
        final HttpEntity urlEncodedFormEntity = list == null ? null : new UrlEncodedFormEntity(list);
        try {
            final URI uri = new URI(str);
            try {
                final String str2 = str;
                final b bVar2 = bVar;
                new AsyncTask<Void, Void, C0002c>(this) {
                    /* access modifiers changed from: protected */
                    public final /* synthetic */ Object doInBackground(Object[] objArr) {
                        return a();
                    }

                    /* access modifiers changed from: protected */
                    public final /* synthetic */ void onPostExecute(Object obj) {
                        C0002c cVar = (C0002c) obj;
                        if (bVar2 == null) {
                            return;
                        }
                        if (cVar.a == null) {
                            bVar2.a(cVar.b);
                            return;
                        }
                        try {
                            bVar2.a(new JSONObject(cVar.a));
                        } catch (JSONException e) {
                            bVar2.a(cVar.b);
                        }
                    }

                    private C0002c a() {
                        BasicHttpParams basicHttpParams = new BasicHttpParams();
                        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 5000);
                        HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
                        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
                        defaultHttpClient.addRequestInterceptor(new HttpRequestInterceptor(this) {
                            public final void process(HttpRequest httpRequest, HttpContext httpContext) {
                                if (!httpRequest.containsHeader("Accept-Encoding")) {
                                    httpRequest.addHeader("Accept-Encoding", "gzip");
                                }
                            }
                        });
                        defaultHttpClient.addResponseInterceptor(new HttpResponseInterceptor(this) {
                            public final void process(HttpResponse httpResponse, HttpContext httpContext) {
                                Header contentEncoding = httpResponse.getEntity().getContentEncoding();
                                if (contentEncoding != null) {
                                    for (HeaderElement name : contentEncoding.getElements()) {
                                        if (name.getName().equalsIgnoreCase("gzip")) {
                                            httpResponse.setEntity(new a(httpResponse.getEntity()));
                                            return;
                                        }
                                    }
                                }
                            }
                        });
                        HttpPost httpPost = new HttpPost(uri);
                        if (urlEncodedFormEntity != null) {
                            httpPost.setEntity(urlEncodedFormEntity);
                        }
                        try {
                            "Full URL: " + str2;
                            HttpResponse execute = defaultHttpClient.execute(httpPost);
                            "Response: " + execute.getStatusLine().toString();
                            String entityUtils = EntityUtils.toString(execute.getEntity());
                            int statusCode = execute.getStatusLine().getStatusCode();
                            if (statusCode == 200) {
                                return new C0002c(entityUtils, statusCode);
                            }
                            "Request failed with status: " + execute.getStatusLine().toString() + " body: " + entityUtils;
                            return new C0002c((String) null, statusCode);
                        } catch (IOException | SocketTimeoutException | SSLException | ClientProtocolException e) {
                            return new C0002c((String) null, 0);
                        }
                    }

                    /* access modifiers changed from: protected */
                    public final void onPreExecute() {
                    }

                    /* access modifiers changed from: protected */
                    public final void onCancelled() {
                        if (bVar2 != null) {
                            bVar2.a(0);
                        }
                    }
                }.execute(new Void[0]);
                "URL: " + str + ", Params: " + new UrlEncodedFormEntity(list);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e2) {
        }
    }

    static String a(String str, List<NameValuePair> list) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append("?");
        }
        if (list != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= list.size()) {
                    break;
                }
                if (i2 > 0) {
                    sb.append("&");
                }
                sb.append(list.get(i2).getName());
                sb.append("=");
                sb.append(a.AnonymousClass1.C0000a.b(list.get(i2).getValue()));
                i = i2 + 1;
            }
        }
        return sb.toString();
    }

    static List<NameValuePair> b(String str) {
        ArrayList arrayList = new ArrayList();
        for (String split : str.split("&")) {
            String[] split2 = split.split("=");
            if (split2.length == 2) {
                arrayList.add(new BasicNameValuePair(split2[0], a.AnonymousClass1.C0000a.c(split2[1])));
            }
        }
        return arrayList;
    }

    static class a extends HttpEntityWrapper {
        public a(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public final InputStream getContent() throws IOException, IllegalStateException {
            return new GZIPInputStream(this.wrappedEntity.getContent());
        }

        public final long getContentLength() {
            return -1;
        }
    }
}
