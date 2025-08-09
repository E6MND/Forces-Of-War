package com.uken.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import com.uken.android.util.UkenEventLog;

public class AuthenticationManager {
    private static String CREDENTIAL_TOKEN_KEY = "CredentialTokenKey";
    private static AuthenticationManager singleton = null;
    private String credentialToken = null;
    private Context mainContext = null;

    public static AuthenticationManager getInstance(Context context) {
        if (singleton == null) {
            singleton = new AuthenticationManager(context);
        }
        return singleton;
    }

    private AuthenticationManager(Context context) {
        this.mainContext = context;
    }

    public String getCredentialToken(boolean load) {
        if (load) {
            loadSettings();
        }
        return this.credentialToken;
    }

    public String getCredentialToken() {
        return getCredentialToken(true);
    }

    public void setCredentialToken(String credentialToken2) {
        this.credentialToken = credentialToken2;
        saveSettings();
    }

    public void removeCredential() {
        this.credentialToken = "";
        saveSettings();
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = Prefs.get(this.mainContext).edit();
        editor.putString(CREDENTIAL_TOKEN_KEY, this.credentialToken);
        editor.commit();
        UkenEventLog.setCredentialToken();
    }

    private void loadSettings() {
        this.credentialToken = Prefs.get(this.mainContext).getString(CREDENTIAL_TOKEN_KEY, "");
        UkenEventLog.setCredentialToken();
    }
}
