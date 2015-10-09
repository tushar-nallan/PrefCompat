package com.tramsuninc.libs.prefcompatsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tramsuninc.libs.prefcompat.Pref;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Junk j = new Junk();
        Pref.putDouble("a", 0.1234);
        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText(Pref.getDouble("a").toString());
    }

    static class Junk implements Serializable {
        int n = 5;
        String s = "lol";
    }
}
