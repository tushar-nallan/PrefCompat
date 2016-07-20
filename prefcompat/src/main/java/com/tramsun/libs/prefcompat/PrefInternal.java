package com.tramsun.libs.prefcompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * Init by Tushar Acharya on 10/8/15.
 */
public class PrefInternal implements PrefInterface {

    private static final float DEFAULT_FLOAT = -1;
    private static final Integer DEFAULT_INT = -1;
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Long DEFAULT_LONG = -1L;
    private final Observable<String> mObservable;

    private static Logger log = Logger.getLogger();

    private SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public PrefInternal(final SharedPreferences pref) {
        this.pref = pref;
        editor = pref.edit();
        mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                final SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        subscriber.onNext(key);
                    }
                };
                pref.registerOnSharedPreferenceChangeListener(listener);

                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        log.d("Un-registering PrefCompat");
                        pref.unregisterOnSharedPreferenceChangeListener(listener);
                    }
                }));
            }
        });
    }

    private <T> T getCastObject(String key, Class<T> tClass) {
        String s = pref.getString(key, null);
        log.d("getCastObject: key=" + key + ", value='" + s + "'");
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        try {
            return Serialize.fromString(s, tClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T getCastObject(String key, Class<T> tClass, T defaultValue) {
        T obj = null;

        String s = pref.getString(key, null);
        log.d("getCastObject: key=" + key + ", value='" + s + "'");
        if (!TextUtils.isEmpty(s)) {
            try {
                obj = Serialize.fromString(s, tClass);
            } catch (Exception e) {
                e.printStackTrace();
                obj = null;
            }
        }
        if (obj == null) {
            obj = defaultValue;
        }

        return obj;
    }

    @Override
    public boolean putString(String key, String value) {
        return storeEntry(editor.putString(key, value));
    }

    private boolean storeEntry(SharedPreferences.Editor editor) {
        switch (Pref.mDefaultCommitBehavior) {
            case Pref.APPLY_BY_DEFAULT:
                editor.apply();
                return true;
            case Pref.COMMIT_BY_DEFAULT:
            default:
                return editor.commit();
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
    public boolean putFloat(String key, Float value) {
        return storeEntry(editor.putFloat(key, value));
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
    public boolean putDouble(String key, Double value) {
        return putString(key, Serialize.toString(value));
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
    public boolean putInt(String key, Integer value) {
        return storeEntry(editor.putInt(key, value));
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
    public boolean putBoolean(String key, Boolean value) {
        return storeEntry(editor.putBoolean(key, value));
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
    public boolean putLong(String key, Long value) {
        return storeEntry(editor.putLong(key, value));
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
    public boolean putStringList(String key, List<String> value) {
        String[] array = new String[value.size()];
        return putString(key, Serialize.toString(value.toArray(array)));
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
    public boolean putFloatList(String key, List<Float> value) {
        Float[] array = new Float[value.size()];
        return putString(key, Serialize.toString(value.toArray(array)));
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
    public boolean putDoubleList(String key, List<Double> value) {
        Double[] array = new Double[value.size()];
        return putString(key, Serialize.toString(value.toArray(array)));
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
    public boolean putIntList(String key, List<Integer> value) {
        Integer[] array = new Integer[value.size()];
        return putString(key, Serialize.toString(value.toArray(array)));
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
    public boolean putBooleanList(String key, List<Boolean> value) {
        Boolean[] array = new Boolean[value.size()];
        return putString(key, Serialize.toString(value.toArray(array)));
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
    public boolean putLongList(String key, List<Long> value) {
        Long[] array = new Long[value.size()];
        return putString(key, Serialize.toString(value.toArray(array)));
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
    public <T> boolean putObject(String key, T value) {
        return putString(key, Serialize.toString(value));
    }

    @Override
    public <T> T getObject(String key, Class<T> tClass) {
        return getCastObject(key, tClass);
    }

    @Override
    public <T> T getObject(String key, Class<T> tClass, T defaultValue) {
        return getCastObject(key, tClass, defaultValue);
    }

    public Observable<String> listenOn(final String key) {
        return mObservable.filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.equals(key);
            }
        });
    }
}
