package com.hotplace25.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by khnam on 2018-01-24.
 */

public class PreferencesManager {
    private static final String PREF_USER = "PREF_USER";
    private static final String PREF_META = "PREF_META";
    private static final String KEY_INSTANCE_ID = "KEY_INSTANCE_ID";

    private static SharedPreferences getSharedPreferences(Context context, String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    public static void setInstanceID(Context context, String instanceID) {
        getSharedPreferences(context, PREF_USER)
        .edit()
        .putString(KEY_INSTANCE_ID, instanceID)
        .commit();
    }

    public static String getInstanceID(Context context) {
        return getSharedPreferences(context, PREF_USER)
                .getString(KEY_INSTANCE_ID, "");
    }
}
