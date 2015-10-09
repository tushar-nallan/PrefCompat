package com.tramsuninc.libs.prefcompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Init by tusharacharya on 10/8/15.
 */
public class PrefInternal implements PrefInterface {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public PrefInternal(SharedPreferences pref) {
        this.pref = pref;
        this.editor = pref.edit();
    }

    private <T> T getCastObject(String key, Class<T> tClass) {
        String s = pref.getString(key, null);
        Log.e("getCastObject", "key=" + key + ", value='" + s + "'");
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        try {
            return Serialize.fromString(s, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    private <T> T getCastObject(String key, Class<T> tClass, T defaultValue) {
        T obj = null;

        String s = pref.getString(key, null);
        if (!TextUtils.isEmpty(s)) {
            try {
                obj = Serialize.fromString(s, tClass);
            } catch (Exception e) {
                obj = null;
            }
        }
        if (obj == null) {
            obj = defaultValue;
        }

        return obj;
    }

    @Override
    public void putString(String key, String value) {
        editor.putString(key, value).commit();
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return pref.getString(key, defaultValue);
    }

    @Override
    public void putFloat(String key, Float value) {
        editor.putFloat(key, value).commit();
    }

    @Override
    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    @Override
    public Float getFloat(String key, Float defaultValue) {
        return pref.getFloat(key, defaultValue);
    }

    @Override
    public void putDouble(String key, Double value) {
        putString(key, Serialize.toString(value));
    }

    @Override
    public Double getDouble(String key) {
        return getCastObject(key, Double.class);
    }

    @Override
    public Double getDouble(String key, Double defaultValue) {
        return getCastObject(key, Double.class, defaultValue);
    }

    @Override
    public void putInt(String key, Integer value) {
        editor.putInt(key, value).commit();
    }

    @Override
    public Integer getInt(String key) {
        return getInt(key, null);
    }

    @Override
    public Integer getInt(String key, Integer defaultValue) {
        return pref.getInt(key, defaultValue);
    }

    @Override
    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value).commit();
    }

    @Override
    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    @Override
    public Boolean getBoolean(String key, Boolean defaultValue) {
        return pref.getBoolean(key, defaultValue);
    }

    @Override
    public void putLong(String key, Long value) {
        editor.putLong(key, value).commit();
    }

    @Override
    public Long getLong(String key) {
        return getLong(key, null);
    }

    @Override
    public Long getLong(String key, Long defaultValue) {
        return pref.getLong(key, defaultValue);
    }

    @Override
    public void putStringList(String key, List<String> value) {
        putString(key, Serialize.toString(value.toArray()));
    }

    @Override
    public List<String> getStringList(String key) {
        return Arrays.asList(getCastObject(key, String[].class, new String[0]));
    }

    @Override
    public List<String> getStringList(String key, List<String> defaultValue) {
        return null;
    }

    @Override
    public void putFloatList(String key, List<Float> value) {

    }

    @Override
    public List<Float> getFloatList(String key) {
        return null;
    }

    @Override
    public List<Float> getFloatList(String key, List<Float> defaultValue) {
        return null;
    }

    @Override
    public void putDoubleList(String key, List<Double> value) {

    }

    @Override
    public List<Double> getDoubleList(String key) {
        return null;
    }

    @Override
    public List<Double> getDoubleList(String key, List<Double> defaultValue) {
        return null;
    }

    @Override
    public void putIntList(String key, List<Integer> value) {

    }

    @Override
    public List<Integer> getIntList(String key) {
        return null;
    }

    @Override
    public List<Integer> getIntList(String key, List<Integer> defaultValue) {
        return null;
    }

    @Override
    public void putBooleanList(String key, List<Boolean> value) {

    }

    @Override
    public List<Boolean> getBooleanList(String key) {
        return null;
    }

    @Override
    public List<Boolean> getBooleanList(String key, List<Boolean> defaultValue) {
        return null;
    }

    @Override
    public void putLongList(String key, List<Long> value) {

    }

    @Override
    public List<Long> getLongList(String key) {
        return null;
    }

    @Override
    public List<Long> getLongList(String key, List<Long> defaultValue) {
        return null;
    }

    @Override
    public <T> void putObject(String key, T value) {
        putString(key, Serialize.toString(value));
    }

    @Override
    public <T> T getObject(String key, Class<T> tClass) {
        return getCastObject(key, tClass);
    }

    @Override
    public <T> T getObject(String key, Class<T> tClass, T defaultValue) {
        return getCastObject(key, tClass, defaultValue);
    }

}
