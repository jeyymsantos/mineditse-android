package com.example.mineditse;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginLocalStorage {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String token;

    public LoginLocalStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LOGIN_STORAGE_API", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getToken() {
        token = sharedPreferences.getString("TOKEN", "");
        return token;
    }

    public void setToken(String token) {
        editor.putString("TOKEN", token);
        editor.commit();
        this.token = token;
    }
}
