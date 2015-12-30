package com.tramsun.libs.prefcompat;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.List;

/**
 * Init by Tushar Acharya on 10/8/15.
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

    public static PrefInternal from(String name) {
        ensureInit();
        return from(name, Context.MODE_PRIVATE);
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

    public static void putStringList(String key, List<String> value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putStringList(key, value);
    }

    public static List<String> getStringList(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getStringList(key);
    }

    public static List<String> getStringList(String key, List<String> defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getStringList(key, defaultValue);
    }

    public static void putFloatList(String key, List<Float> value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putFloatList(key, value);
    }

    public static List<Float> getFloatList(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getFloatList(key);
    }

    public static List<Float> getFloatList(String key, List<Float> defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getFloatList(key, defaultValue);
    }

    public static void putDoubleList(String key, List<Double> value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putDoubleList(key, value);
    }

    public static List<Double> getDoubleList(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getDoubleList(key);
    }

    public static List<Double> getDoubleList(String key, List<Double> defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getDoubleList(key, defaultValue);
    }

    public static void putIntList(String key, List<Integer> value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putIntList(key, value);
    }

    public static List<Integer> getIntList(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getIntList(key);
    }

    public static List<Integer> getIntList(String key, List<Integer> defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getIntList(key, defaultValue);
    }

    public static void putBooleanList(String key, List<Boolean> value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putBooleanList(key, value);
    }

    public static List<Boolean> getBooleanList(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getBooleanList(key);
    }

    public static List<Boolean> getBooleanList(String key, List<Boolean> defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getBooleanList(key, defaultValue);
    }

    public static void putLongList(String key, List<Long> value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putLongList(key, value);
    }

    public static List<Long> getLongList(String key) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getLongList(key);
    }

    public static List<Long> getLongList(String key, List<Long> defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getLongList(key, defaultValue);
    }

    public static <T> void putObject(String key, T value) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        pref.putObject(key, value);
    }

    public static <T> T getObject(String key, Class<T> tClass) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getObject(key, tClass);
    }

    public static <T> T getObject(String key, Class<T> tClass, T defaultValue) {
        ensureInit();
        PrefInternal pref = prefHashMap.get(DEFAULT);
        return pref.getObject(key, tClass, defaultValue);
    }

    protected static class PrefCompatNotInitedException extends RuntimeException {
        public PrefCompatNotInitedException() {
            super("PrefCompat not initialized. Use PrefCompat.init(this) in your application");
        }
    }
}