package org.xwalk.core.internal.extension.api.contacts;

import android.util.Pair;
import com.google.android.gms.common.Scopes;
import com.uken.android.common.ModalActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class ContactConstants {
    public static final String CUSTOM_MIMETYPE_GENDER = "vnd.android.cursor.item/contact_custom_gender";
    public static final String CUSTOM_MIMETYPE_LASTUPDATED = "vnd.android.cursor.item/contact_custom_lastupdated";
    public static final Map<String, String> addressDataMap = createStringMap(new String[]{"data4", "streetAddress", "data6", "locality", "data8", "region", "data9", "postalCode", "data10", "countryName"});
    public static final Map<String, String> addressTypeMap = new HashMap<String, String>() {
        {
            put(MessagingSmsConsts.TYPE, "data2");
            put("isPrimary", "is_primary");
            put("isSuperPrimary", "is_super_primary");
        }
    };
    public static final Map<String, Integer> addressTypeValuesMap = new HashMap<String, Integer>() {
        {
            put("work", 2);
            put("home", 1);
            put("other", 3);
        }
    };
    public static final Map<String, String> companyDataMap = createDataMap("data1");
    public static final Map<String, Pair<String, String>> contactDataMap = createTripleMap(new String[]{"id", "contact_id", null, "displayName", "data1", "vnd.android.cursor.item/name", "familyNames", "data3", "vnd.android.cursor.item/name", "givenNames", "data2", "vnd.android.cursor.item/name", "middleNames", "data5", "vnd.android.cursor.item/name", "additionalNames", "data5", "vnd.android.cursor.item/name", "honorificPrefixes", "data4", "vnd.android.cursor.item/name", "honorificSuffixes", "data6", "vnd.android.cursor.item/name", "nickNames", "data1", "vnd.android.cursor.item/nickname", "categories", "data1", "vnd.android.cursor.item/group_membership", "gender", "data1", CUSTOM_MIMETYPE_GENDER, "lastUpdated", "data1", CUSTOM_MIMETYPE_LASTUPDATED, "birthday", "data1", "vnd.android.cursor.item/contact_event", "anniversary", "data1", "vnd.android.cursor.item/contact_event", "emails", "data1", "vnd.android.cursor.item/email_v2", "photos", "data15", "vnd.android.cursor.item/photo", "urls", "data1", "vnd.android.cursor.item/website", "phoneNumbers", "data1", "vnd.android.cursor.item/phone_v2", "addresses", null, "vnd.android.cursor.item/postal-address_v2", "streetAddress", "data4", "vnd.android.cursor.item/postal-address_v2", "locality", "data6", "vnd.android.cursor.item/postal-address_v2", "region", "data8", "vnd.android.cursor.item/postal-address_v2", "postalCode", "data9", "vnd.android.cursor.item/postal-address_v2", "countryName", "data10", "vnd.android.cursor.item/postal-address_v2", "organizations", "data1", "vnd.android.cursor.item/organization", "jobTitles", "data4", "vnd.android.cursor.item/organization", "notes", "data1", "vnd.android.cursor.item/note", "impp", "data1", "vnd.android.cursor.item/im"});
    public static final List<ContactMap> contactMapList = new ArrayList<ContactMap>() {
        {
            add(new ContactMap("emails", ContactConstants.emailDataMap, ContactConstants.emailTypeMap, ContactConstants.emailTypeValuesMap));
            add(new ContactMap("photos", ContactConstants.photoDataMap, (Map<String, String>) null, (Map<String, Integer>) null));
            add(new ContactMap("urls", ContactConstants.websiteDataMap, ContactConstants.websiteTypeMap, ContactConstants.websiteTypeValuesMap));
            add(new ContactMap("phoneNumbers", ContactConstants.phoneDataMap, ContactConstants.phoneTypeMap, ContactConstants.phoneTypeValuesMap));
            add(new ContactMap("addresses", ContactConstants.addressDataMap, ContactConstants.addressTypeMap, ContactConstants.addressTypeValuesMap));
            add(new ContactMap("organizations", ContactConstants.companyDataMap, (Map<String, String>) null, (Map<String, Integer>) null));
            add(new ContactMap("jobTitles", ContactConstants.jobtitleDataMap, (Map<String, String>) null, (Map<String, Integer>) null));
            add(new ContactMap("notes", ContactConstants.noteDataMap, (Map<String, String>) null, (Map<String, Integer>) null));
            add(new ContactMap("impp", ContactConstants.imDataMap, ContactConstants.imTypeMap, ContactConstants.imTypeValuesMap));
        }
    };
    public static final Map<String, String> emailDataMap = createValueMap("data1");
    public static final Map<String, String> emailTypeMap = new HashMap<String, String>() {
        {
            put(MessagingSmsConsts.TYPE, "data2");
            put("isPrimary", "is_primary");
            put("isSuperPrimary", "is_super_primary");
        }
    };
    public static final Map<String, Integer> emailTypeValuesMap = new HashMap<String, Integer>() {
        {
            put("work", 2);
            put("home", 1);
            put("mobile", 4);
        }
    };
    public static final Map<String, String> findFieldMap = createStringMap(new String[]{"familyName", "familyNames", "givenName", "givenNames", "middleName", "middleNames", "additionalName", "additionalNames", "honorificPrefix", "honorificPrefixes", "honorificSuffix", "honorificSuffixes", "nickName", "nickNames", "email", "emails", "photo", "photos", "url", "urls", "phoneNumber", "phoneNumbers", "organization", "organizations", "jobTitle", "jobTitles", "note", "notes"});
    public static final Map<String, String> imDataMap = createValueMap("data1");
    public static final Map<String, Integer> imProtocolMap = new HashMap<String, Integer>() {
        {
            put("aim", 0);
            put("msn", 1);
            put("ymsgr", 2);
            put("skype", 3);
            put("qq", 4);
            put("gtalk", 5);
            put("icq", 6);
            put("jabber", 7);
            put("netmeeting", 8);
        }
    };
    public static final Map<String, String> imTypeMap = new HashMap<String, String>() {
        {
            put(MessagingSmsConsts.TYPE, "data2");
            put("isPrimary", "is_primary");
            put("isSuperPrimary", "is_super_primary");
        }
    };
    public static final Map<String, Integer> imTypeValuesMap = new HashMap<String, Integer>() {
        {
            put("work", 2);
            put("home", 1);
            put("other", 3);
        }
    };
    public static final Map<String, String> jobtitleDataMap = createDataMap("data4");
    public static final Map<String, String> noteDataMap = createDataMap("data1");
    public static final Map<String, String> phoneDataMap = createValueMap("data1");
    public static final Map<String, String> phoneTypeMap = new HashMap<String, String>() {
        {
            put(MessagingSmsConsts.TYPE, "data2");
            put("isPrimary", "is_primary");
            put("isSuperPrimary", "is_super_primary");
        }
    };
    public static final Map<String, Integer> phoneTypeValuesMap = new HashMap<String, Integer>() {
        {
            put("home", 1);
            put("mobile", 2);
            put("work", 3);
            put("fax_work", 4);
            put("fax_home", 5);
            put("pager", 6);
            put("other", 7);
            put(ModalActivity.CALLBACK, 8);
            put("car", 9);
            put("company_main", 10);
            put("isdn", 11);
            put("main", 12);
            put("other_fax", 13);
            put("radio", 14);
            put("telex", 15);
            put("tty_tdd", 16);
            put("mobile", 17);
            put("work_pager", 18);
            put("assistant", 19);
            put("mms", 20);
        }
    };
    public static final Map<String, String> photoDataMap = createDataMap("data15");
    public static final Map<String, String> websiteDataMap = createValueMap("data1");
    public static final Map<String, String> websiteTypeMap = new HashMap<String, String>() {
        {
            put(MessagingSmsConsts.TYPE, "data2");
            put("isPrimary", "is_primary");
            put("isSuperPrimary", "is_super_primary");
        }
    };
    public static final Map<String, Integer> websiteTypeValuesMap = new HashMap<String, Integer>() {
        {
            put("blog", 2);
            put("ftp", 6);
            put("home", 4);
            put("homepage", 1);
            put("other", 7);
            put(Scopes.PROFILE, 3);
            put("work", 5);
        }
    };

    private static Map<String, Pair<String, String>> createTripleMap(String[] triplets) {
        Map<String, Pair<String, String>> result = new HashMap<>();
        for (int i = 0; i < triplets.length; i += 3) {
            result.put(triplets[i], new Pair(triplets[i + 1], triplets[i + 2]));
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<String, String> createStringMap(String[] pairs) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            result.put(pairs[i], pairs[i + 1]);
        }
        return Collections.unmodifiableMap(result);
    }

    private static Map<String, String> createDataMap(String name) {
        return createStringMap(new String[]{"data", name});
    }

    private static Map<String, String> createValueMap(String name) {
        return createStringMap(new String[]{name, "value"});
    }

    public static class ContactMap {
        public Map<String, String> mDataMap;
        public String mMimeType;
        public String mName;
        public Map<String, String> mTypeMap;
        public Map<String, Integer> mTypeValueMap;

        public ContactMap(String n, Map<String, String> datas, Map<String, String> types, Map<String, Integer> typeValues) {
            this.mName = n;
            this.mMimeType = (String) ContactConstants.contactDataMap.get(n).second;
            this.mDataMap = datas;
            this.mTypeMap = types;
            this.mTypeValueMap = typeValues;
        }
    }
}
