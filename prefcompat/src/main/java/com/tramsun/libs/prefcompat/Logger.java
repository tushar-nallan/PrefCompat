package com.tramsun.libs.prefcompat;

import android.util.Log;

public class Logger {
  private static final String TAG = Pref.class.getSimpleName();
  private static int logLevel = Log.ERROR;
  static Logger sLogger;

  public static void setLogLevel(int logLevel) {
    if (logLevel >= Log.VERBOSE && logLevel <= Log.ASSERT) {
      Logger.logLevel = logLevel;
    } else {
      Log.e(TAG, "Invalid log level. Defaulting to Log.ERROR");
      Logger.logLevel = Log.ERROR;
    }
  }

  public static Logger getLogger() {
    if (sLogger == null) {
      sLogger = new Logger();
    }
    return sLogger;
  }

  public void v(String msg) {
    if (logLevel <= Log.VERBOSE) {
      Log.d(TAG, msg);
    }
  }

  public void i(String msg) {
    if (logLevel <= Log.INFO) {
      Log.d(TAG, msg);
    }
  }

  public void d(String msg) {
    if (logLevel <= Log.DEBUG) {
      Log.d(TAG, msg);
    }
  }

  public void w(String msg) {
    if (logLevel <= Log.WARN) {
      Log.d(TAG, msg);
    }
  }

  public void e(String msg) {
    if (logLevel <= Log.ERROR) {
      Log.d(TAG, msg);
    }
  }

  public void a(String msg) {
    if (logLevel <= Log.ASSERT) {
      Log.d(TAG, msg);
    }
  }
}
