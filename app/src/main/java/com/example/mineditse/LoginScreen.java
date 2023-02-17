package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void click_login(View view) {
        Intent login = new Intent(LoginScreen.this, CustomerHomepage.class);
        startActivity(login);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void click_createAcct(View view) {
        Intent create = new Intent(LoginScreen.this, SignUpScreen.class);
        startActivity(create);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}