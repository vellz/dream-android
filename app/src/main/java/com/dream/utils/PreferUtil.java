package com.dream.utils;

import android.content.Context;

public class PreferUtil {
    public static boolean saveString(Context context, String key, String value) {
        context.getSharedPreferences(Constant.COOKIESTR, Context.MODE_PRIVATE).edit().putString(key, value).commit();
        return true;
    }

    public static String getString(Context context, String key) {

        return context.getSharedPreferences(Constant.COOKIESTR, Context.MODE_PRIVATE).getString(key, "0");
    }
}
