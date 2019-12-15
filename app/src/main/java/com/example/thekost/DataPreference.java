package com.example.thekost;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

public class DataPreference {
    private static final String PREF_NAME = "user_pref";

    private static final String LOGIN = "isLogin";

    private final SharedPreferences preferences;

    public DataPreference(Context context){
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setLogin(String login){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString(LOGIN, login);
        editor.apply();
    }

   public String getLogin(){
        return preferences.getString(LOGIN, "0");
   }
}
