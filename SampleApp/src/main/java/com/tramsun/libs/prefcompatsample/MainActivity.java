package com.tramsun.libs.prefcompatsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tramsun.libs.prefcompat.Pref;

import java.io.Serializable;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String SCORES = "SP_SCORES";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Named SharedPreferences
        Pref.from(SCORES).putDoubleList("a", Arrays.asList(1.1, 2.2, 3.33));
        Log.d(TAG, "scores=" + Pref.from(SCORES).getDoubleList("a").toString());

        // Default SharedPreferences
        Pref.putObject("person1", new Person("Tushar", 20));
        Log.d(TAG, "person1=" + Pref.getObject("person1", Person.class));
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
