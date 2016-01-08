# PrefCompat

PrefCompat is a wrapper over the SharedPreference class in Android. It supports storing objects other than the standard primitives while decreasing the boiler plate code.

How to Use
-------


#### Gradle Configuration

Add the following line to the gradle dependencies for your module.

```groovy
compile 'com.tramsun.libs:prefcompat:0.6'
```

#### Basic Usage

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
