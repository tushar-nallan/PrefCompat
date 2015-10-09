package com.tramsuninc.libs.prefcompat;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Init by Tushar Acharya on 10/8/15.
 */
public class Serialize {
    public static String toString(Object o) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(o);
            so.flush();
            return Base64.encodeToString(bo.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromString(String s, Class<T> tClass) {
        try {
            byte b[] = Base64.decode(s.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            Object o = si.readObject();
            return tClass.cast(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
