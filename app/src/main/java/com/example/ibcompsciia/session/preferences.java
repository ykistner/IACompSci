package com.example.ibcompsciia.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class preferences {
    private static final String KEY_DATA = "key_data";
    private static final String NAME_DATA = "name_data";

    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKey(Context context, String s){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_DATA,s);
        editor.apply();
    }

    public static String getKeyData(Context context){
        return getSharedPreferences(context).getString(KEY_DATA, "");
    }

    public static void setNameData(Context context, String s){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(NAME_DATA,s);
        editor.apply();
    }

    public static String getNameData(Context context){
        return getSharedPreferences(context).getString(NAME_DATA, "");
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(KEY_DATA);
        editor.remove(NAME_DATA);
        editor.apply();
    }
}
