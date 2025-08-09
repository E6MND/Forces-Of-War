package org.chromium.content.browser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;

public class PepperPluginManager {
    private static final String DESCRIPTION = "description";
    private static final String FILENAME = "filename";
    private static final String LOGTAG = "PepperPluginManager";
    private static final String MIMETYPE = "mimetype";
    private static final String NAME = "name";
    public static final String PEPPER_PLUGIN_ACTION = "org.chromium.intent.PEPPERPLUGIN";
    public static final String PEPPER_PLUGIN_ROOT = "/system/lib/pepperplugin/";
    private static final String VERSION = "version";

    private static String getPluginDescription(Bundle metaData) {
        String mimetype;
        String filename = metaData.getString(FILENAME);
        if (filename == null || filename.isEmpty() || (mimetype = metaData.getString(MIMETYPE)) == null || mimetype.isEmpty()) {
            return null;
        }
        StringBuffer plugin = new StringBuffer(PEPPER_PLUGIN_ROOT);
        plugin.append(filename);
        String name = metaData.getString(NAME);
        String description = metaData.getString("description");
        String version = metaData.getString(VERSION);
        if (name != null && !name.isEmpty()) {
            plugin.append("#");
            plugin.append(name);
            if (description != null && !description.isEmpty()) {
                plugin.append("#");
                plugin.append(description);
                if (version != null && !version.isEmpty()) {
                    plugin.append("#");
                    plugin.append(version);
                }
            }
        }
        plugin.append(';');
        plugin.append(mimetype);
        return plugin.toString();
    }

    public static String getPlugins(Context context) {
        StringBuffer ret = new StringBuffer();
        PackageManager pm = context.getPackageManager();
        for (ResolveInfo info : pm.queryIntentServices(new Intent(PEPPER_PLUGIN_ACTION), 132)) {
            ServiceInfo serviceInfo = info.serviceInfo;
            if (serviceInfo == null || serviceInfo.metaData == null || serviceInfo.packageName == null) {
                Log.e(LOGTAG, "Can't get service information from " + info);
            } else {
                try {
                    PackageInfo pkgInfo = pm.getPackageInfo(serviceInfo.packageName, 0);
                    if (!(pkgInfo == null || (pkgInfo.applicationInfo.flags & 1) == 0)) {
                        Log.i(LOGTAG, "The given plugin package is preloaded: " + serviceInfo.packageName);
                        String plugin = getPluginDescription(serviceInfo.metaData);
                        if (plugin != null) {
                            if (ret.length() > 0) {
                                ret.append(',');
                            }
                            ret.append(plugin);
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(LOGTAG, "Can't find plugin: " + serviceInfo.packageName);
                }
            }
        }
        return ret.toString();
    }
}
