package com.tramsuninc.libs.prefcompat.lib;

import android.app.Application;
import android.content.Context;

/**
 * Init by tusharacharya on 10/8/15.
 */
public class PrefCompat {
    Context mContext;

    PrefCompat pref;

    public void init(Application mApp) {
        this.mContext = mApp;
    }

    private void assertInit() throws PrefCompatNotInitedException {
        if(mContext == null) {
            throw new PrefCompatNotInitedException();
        }
    }

    private class PrefCompatNotInitedException extends Exception {
        public PrefCompatNotInitedException() {
            super("PrefCompat not initialized. Use PrefCompat.init(this) in your application");
        }
    }
}
