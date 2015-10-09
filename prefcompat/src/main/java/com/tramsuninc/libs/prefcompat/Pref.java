package com.tramsuninc.libs.prefcompat;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.HashMap;

/**
 * Init by tusharacharya on 10/8/15.
 */
public class Pref {

    public static final String DEFAULT = "";

    static Context mContext = null;

    static HashMap<String, PrefInternal> prefHashMap;

    public static void init(Context ctx) {
        if (ctx == null) {
            throw new NullPointerException("PrefCompat.init called will null context");
        }

        mContext = ctx.getApplicationContext();
        prefHashMap = new HashMap<>();
        prefHashMap.put(DEFAULT, new PrefInternal(PreferenceManager.getDefaultSharedPreferences(mContext)));
    }

    public static PrefInternal from(String name, int mode) {
        ensureInit();
        PrefInternal prefInternal;
        if (prefHashMap.containsKey(name)) {
            prefInternal = prefHashMap.get(name);
        } else {
            prefInternal = new PrefInternal(mContext.getSharedPreferences(name, mode));
            prefHashMap.put(name, prefInternal);
        }
        return prefInternal;
    }

    protected static void ensureInit() throws PrefCompatNotInitedException {
        if (mContext == null || prefHashMap == null || prefHashMap.get(DEFAULT) == null) {
            throw new PrefCompatNotInitedException();
        }
    }

    public static void putString(String key, String value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putString(key, value);
    }

    public static String getString(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getString(key);
    }

    public static String getString(String key, String defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getString(key, defaultValue);
    }

    public static void putFloat(String key, Float value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putFloat(key, value);
    }

    public static Float getFloat(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getFloat(key);
    }

    public static Float getFloat(String key, Float defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getFloat(key, defaultValue);
    }

    public static void putDouble(String key, Double value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putDouble(key, value);
    }

    public static Double getDouble(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getDouble(key);
    }

    public static Double getDouble(String key, Double defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getDouble(key, defaultValue);
    }

    public static void putInt(String key, Integer value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putInt(key, value);
    }

    public static Integer getInt(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getInt(key);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getInt(key, defaultValue);
    }

    public static void putBoolean(String key, Boolean value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putBoolean(key, value);
    }

    public static Boolean getBoolean(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getBoolean(key);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getBoolean(key, defaultValue);
    }

    public static void putLong(String key, Long value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putLong(key, value);
    }

    public static Long getLong(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getLong(key);
    }

    public static Long getLong(String key, Long defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getLong(key, defaultValue);
    }

    public static <T> void putObject(String key, T value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putObject(key, value);
    }

    public static <T> T getObject(String key, Class<T> tClass) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getObject(key, tClass, null);
    }

    public static <T> T getObject(String key, Class<T> tClass, T defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getObject(key, tClass, defaultValue);
    }

    public PrefInternal from(String name) {
        ensureInit();
        return from(name, Context.MODE_PRIVATE);
    }

    protected static class PrefCompatNotInitedException extends RuntimeException {
        public PrefCompatNotInitedException() {
            super("PrefCompat not initialized. Use PrefCompat.init(this) in your application");
        }
    }
}