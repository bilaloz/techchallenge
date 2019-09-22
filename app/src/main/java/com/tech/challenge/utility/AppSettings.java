package com.tech.challenge.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.orhanobut.hawk.Hawk;


/**
 * Responsible to manage AppSettings
 * @author bilal
 * @version 1.0.0
 */

public class AppSettings {

    /**
     * AppSettings Data Management
     */

    private static final String SETTINGS_NAME = "default_settings";
    private static AppSettings sSharedPrefs;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;
    private String TAG = "WidgetAppSettings";


    private AppSettings(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        if (!Hawk.isBuilt())
            Hawk.init(context)
                    .setLogInterceptor(message -> Log.d("Hawk_Helper", "Message : " + message))
                    .build();
    }

    public static AppSettings getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new AppSettings(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    void set(String key, String val) {
        Hawk.put(key, val);
    }

    public String getString(String key, String defaultValue) {
        try {
            return Hawk.get(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Hawk.get(key, defaultValue);
    }

    public boolean set(String key, boolean val) {
        Hawk.put(key, val);
        return val;
    }



}
