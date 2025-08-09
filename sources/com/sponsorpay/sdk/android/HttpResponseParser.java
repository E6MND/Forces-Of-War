package com.sponsorpay.sdk.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;

public class HttpResponseParser {
    public static String extractResponseString(HttpResponse httpResponse) {
        try {
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    inStream.close();
                    return sb.toString();
                }
                sb.append(String.valueOf(line) + "\n");
            }
        } catch (IOException e) {
            return null;
        }
    }
}
