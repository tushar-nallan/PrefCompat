package com.tramsun.libs.prefcompat;

import com.google.gson.Gson;

public class Serialize {
  public static String toString(Object o) {
    return new Gson().toJson(o);
  }

  @SuppressWarnings("unchecked") public static <T> T fromString(String s, Class<T> tClass) {
    return new Gson().fromJson(s, tClass);
  }
}
