package com.tramsun.libs.prefcompatsample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tramsun.libs.prefcompat.Pref;

import java.io.Serializable;
import java.util.Arrays;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    public static final String SP_SCORES = "SP_SCORES";
    public static final String AGE = "AGE";
    public static final String USER1 = "USER1";
    public static final String PERSON1 = "PERSON1";
    private static final String TAG = "MainActivity";
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompositeSubscription = new CompositeSubscription();

        // Named SharedPreferences
        Pref.from(SP_SCORES).putDoubleList(USER1, Arrays.asList(1.1, 2.2, 3.33));
        Log.d(TAG, "scores of USER1 =" + Pref.from(SP_SCORES).getDoubleList(USER1).toString());

        // Default SharedPreferences
        Pref.putObject(PERSON1, new Person("Tushar", 24));
        Log.d(TAG, "PERSON1=" + Pref.getObject(PERSON1, Person.class));

        Button incrementButton = (Button) findViewById(R.id.increment);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Incrementing age from " + Pref.getInt(AGE, 0));
                Pref.putInt(AGE, Pref.getInt(AGE, 0) + 1);
            }
        });

        // Listen on a key for default SharedPreferences
        Subscription subscription1 = Pref.listenOn(AGE).subscribe(new Action1<String>() {
            @Override
            public void call(String key) {
                Log.d(TAG, "1. Value of " + key + " changed to " + Pref.getInt(key));
                Toast.makeText(getApplicationContext(), "1. Value of " + key + " changed to " + Pref.getInt(key), Toast.LENGTH_SHORT).show();
            }
        });
        mCompositeSubscription.add(subscription1);

        // Listen on a key for named SharedPreferences
        Subscription subscription2 = Pref.from(SP_SCORES).listenOn(USER1).subscribe(new Action1<String>() {
            @Override
            public void call(String key) {
                Log.d(TAG, "2. Scores of " + key + " changed to " + Pref.from(SP_SCORES).getDoubleList(key).toString());
            }
        });
        mCompositeSubscription.add(subscription2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Pref.from(SP_SCORES).putDoubleList(USER1, Arrays.asList(3.3, 4.4, 5.5));
            }
        }, 1000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    static class Person implements Serializable {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
