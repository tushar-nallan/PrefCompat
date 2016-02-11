package com.tramsun.libs.prefcompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Init by Tushar Acharya on 10/8/15.
 */
public class PrefInternal implements PrefInterface, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final float DEFAULT_FLOAT = -1;
    private static final Integer DEFAULT_INT = -1;
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Long DEFAULT_LONG = -1L;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private HashMap<String, Subscriber<? super String>> mPrefSubscribers = new HashMap<>();

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
        storeEntry(editor.putString(key, value));
    }

    private void storeEntry(SharedPreferences.Editor editor) {
        switch (Pref.mDefaultCommitBehavior) {
            case Pref.COMMIT_BY_DEFAULT:
                editor.commit();
                break;
            case Pref.APPLY_BY_DEFAULT:
                editor.apply();
                break;
        }
    }

    @Override
    public String getString(String key) {
        return getString(key, "");
    }

    @Override
    public String getString(String key, String defaultValue) {
        return pref.getString(key, defaultValue);
    }

    @Override
    public void putFloat(String key, Float value) {
        storeEntry(editor.putFloat(key, value));
    }

    @Override
    public Float getFloat(String key) {
        return pref.getFloat(key, DEFAULT_FLOAT);
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
        storeEntry(editor.putInt(key, value));
    }

    @Override
    public Integer getInt(String key) {
        return getInt(key, DEFAULT_INT);
    }

    @Override
    public Integer getInt(String key, Integer defaultValue) {
        return pref.getInt(key, defaultValue);
    }

    @Override
    public void putBoolean(String key, Boolean value) {
        storeEntry(editor.putBoolean(key, value));
    }

    @Override
    public Boolean getBoolean(String key) {
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    @Override
    public Boolean getBoolean(String key, Boolean defaultValue) {
        return pref.getBoolean(key, defaultValue);
    }

    @Override
    public void putLong(String key, Long value) {
        storeEntry(editor.putLong(key, value));
    }

    @Override
    public Long getLong(String key) {
        return getLong(key, DEFAULT_LONG);
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
        return new ArrayList<>(Arrays.asList(getCastObject(key, String[].class, new String[0])));
    }

    @Override
    public List<String> getStringList(String key, List<String> defaultValue) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, String[].class, defaultValue.toArray(new String[defaultValue.size()]))));
    }

    @Override
    public void putFloatList(String key, List<Float> value) {
        putString(key, Serialize.toString(value.toArray()));
    }

    @Override
    public List<Float> getFloatList(String key) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Float[].class, new Float[0])));
    }

    @Override
    public List<Float> getFloatList(String key, List<Float> defaultValue) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Float[].class, defaultValue.toArray(new Float[defaultValue.size()]))));
    }

    @Override
    public void putDoubleList(String key, List<Double> value) {
        putString(key, Serialize.toString(value.toArray()));
    }

    @Override
    public List<Double> getDoubleList(String key) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Double[].class, new Double[0])));
    }

    @Override
    public List<Double> getDoubleList(String key, List<Double> defaultValue) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Double[].class, defaultValue.toArray(new Double[defaultValue.size()]))));
    }

    @Override
    public void putIntList(String key, List<Integer> value) {
        putString(key, Serialize.toString(value.toArray()));
    }

    @Override
    public List<Integer> getIntList(String key) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Integer[].class, new Integer[0])));
    }

    @Override
    public List<Integer> getIntList(String key, List<Integer> defaultValue) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Integer[].class, defaultValue.toArray(new Integer[defaultValue.size()]))));
    }

    @Override
    public void putBooleanList(String key, List<Boolean> value) {
        putString(key, Serialize.toString(value.toArray()));
    }

    @Override
    public List<Boolean> getBooleanList(String key) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Boolean[].class, new Boolean[0])));
    }

    @Override
    public List<Boolean> getBooleanList(String key, List<Boolean> defaultValue) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Boolean[].class, defaultValue.toArray(new Boolean[defaultValue.size()]))));
    }

    @Override
    public void putLongList(String key, List<Long> value) {
        putString(key, Serialize.toString(value.toArray()));
    }

    @Override
    public List<Long> getLongList(String key) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Long[].class, new Long[0])));
    }

    @Override
    public List<Long> getLongList(String key, List<Long> defaultValue) {
        return new ArrayList<>(Arrays.asList(getCastObject(key, Long[].class, defaultValue.toArray(new Long[defaultValue.size()]))));
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

    public void deInit() {
        for (String key : mPrefSubscribers.keySet()) {
            removeObservableKey(key);
        }
    }

    public Observable<String> getObservable(final String key) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                mPrefSubscribers.put(key, subscriber);
            }
        });
    }

    public void removeObservableKey(String key) {
        mPrefSubscribers.remove(key);
        if (mPrefSubscribers.isEmpty()) {
            pref.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Subscriber<? super String> subscriber = mPrefSubscribers.get(key);
        if (subscriber != null) {
            subscriber.onNext(key);
        }
    }
}
