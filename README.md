
[![Build Status](https://travis-ci.org/tushar-acharya/PrefCompat.svg?branch=master)](https://travis-ci.org/tushar-acharya/PrefCompat) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PrefCompat-brightgreen.svg?style=true)](https://android-arsenal.com/details/1/3003)

# PrefCompat

PrefCompat is a wrapper over the SharedPreference class in Android. It supports storing objects other than the standard primitives while decreasing the boiler plate code. It also enables listening for changes on specific keys on a SharedPreference with RxJava support.

How to Use
-------


#### Gradle Configuration

Add the following line to the gradle dependencies for your module.

```groovy
compile 'com.tramsun.libs:prefcompat:0.9.2'
```

#### Usage

First initialize the PrefCompat library in your Application class.

```java
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Pref.init(this);
    }
}
```

Then, use the Pref class to store and access data in SharedPreferences.

```java
public class MainActivity extends Activity {

    @Override public void onCreate(Bundle inState) {
        // Default SharedPreferences
        Pref.putString("name", "Tushar");
        Pref.putDouble("weight", 79.24);
        Pref.putObject("dependents", new Dependent("Radhika", 62.24));

        Double weight = Pref.getDouble("weight");
        Dependent mom = Pref.getObject("dependents", Dependent.class);

        // Named SharedPreferences can also be used
        Pref.from("Scores").putIntList("level_1", [60, 20, 80, 25, 30]);

        List<Integer> scores = Pref.from("Scores").getIntList("level_1");
    }
}
```

Listen on changes to specific key in a SharedPreference

```java
// Listen on a key in default SharedPreferences
Pref.listenOn("age").subscribe(new Action1<String>() {
    @Override
    public void call(String key) {
        Log.d(TAG, "1. Value of " + key + " changed to " + Pref.getInt(key));
    }
});

// Listen on a key in named SharedPreferences
Pref.from("scores").listenOn("user1").subscribe(new Action1<String>() {
    @Override
    public void call(String key) {
        Log.d(TAG, "2. Scores of " + key + " changed to " + Pref.from(SP_SCORES).getDoubleList(key).toString());
    }
});
```

Do note that these subscriptions are standard RxJava subscriptions, hence they need to be unsubscribed, failing which will lead to leaking of subscriptions


License
-------

    Copyright 2015 - 2016 Tushar Acharya

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
