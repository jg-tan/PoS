package com.jgt.pos.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPref instance;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private SharedPref() {
        pref = ContextManager.get().getSharedPreferences(Constants.POS_SHARED_PREF, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static SharedPref get() {
        if(null == instance) {
            synchronized (SharedPref.class) {
                instance = new SharedPref();
            }
        }
        return instance;
    }

    public void setPassword(String password) {
        editor.putString(Constants.ADMIN_PASSWORD, password);
        editor.commit();
    }

    public String getPassword() {
        return pref.getString(Constants.ADMIN_PASSWORD, "");
    }
}
