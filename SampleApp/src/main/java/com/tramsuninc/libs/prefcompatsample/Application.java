package com.tramsuninc.libs.prefcompatsample;

import com.tramsuninc.libs.prefcompat.Pref;

/**
 * Init by Tushar Acharya on 10/8/15.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Pref.init(this);
    }
}
