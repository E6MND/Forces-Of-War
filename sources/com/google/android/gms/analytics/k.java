package com.google.android.gms.analytics;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.google.android.gms.analytics.j;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

abstract class k<T extends j> {
    Context mContext;
    a<T> tJ;

    public interface a<U extends j> {
        void a(String str, int i);

        void c(String str, String str2);

        void c(String str, boolean z);

        U cw();

        void d(String str, String str2);
    }

    public k(Context context, a<T> aVar) {
        this.mContext = context;
        this.tJ = aVar;
    }

    private T a(XmlResourceParser xmlResourceParser) {
        try {
            xmlResourceParser.next();
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (xmlResourceParser.getEventType() == 2) {
                    String lowerCase = xmlResourceParser.getName().toLowerCase();
                    if (lowerCase.equals("screenname")) {
                        String attributeValue = xmlResourceParser.getAttributeValue((String) null, "name");
                        String trim = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue) && !TextUtils.isEmpty(trim)) {
                            this.tJ.c(attributeValue, trim);
                        }
                    } else if (lowerCase.equals("string")) {
                        String attributeValue2 = xmlResourceParser.getAttributeValue((String) null, "name");
                        String trim2 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue2) && trim2 != null) {
                            this.tJ.d(attributeValue2, trim2);
                        }
                    } else if (lowerCase.equals("bool")) {
                        String attributeValue3 = xmlResourceParser.getAttributeValue((String) null, "name");
                        String trim3 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue3) && !TextUtils.isEmpty(trim3)) {
                            try {
                                this.tJ.c(attributeValue3, Boolean.parseBoolean(trim3));
                            } catch (NumberFormatException e) {
                                aa.A("Error parsing bool configuration value: " + trim3);
                            }
                        }
                    } else if (lowerCase.equals("integer")) {
                        String attributeValue4 = xmlResourceParser.getAttributeValue((String) null, "name");
                        String trim4 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue4) && !TextUtils.isEmpty(trim4)) {
                            try {
                                this.tJ.a(attributeValue4, Integer.parseInt(trim4));
                            } catch (NumberFormatException e2) {
                                aa.A("Error parsing int configuration value: " + trim4);
                            }
                        }
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (XmlPullParserException e3) {
            aa.A("Error parsing tracker configuration file: " + e3);
        } catch (IOException e4) {
            aa.A("Error parsing tracker configuration file: " + e4);
        }
        return this.tJ.cw();
    }

    public T r(int i) {
        try {
            return a(this.mContext.getResources().getXml(i));
        } catch (Resources.NotFoundException e) {
            aa.D("inflate() called with unknown resourceId: " + e);
            return null;
        }
    }
}
