package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SignUpScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
    }

    public void click_signin(View view) {
        Intent signin = new Intent(SignUpScreen.this, LoginScreen.class);
        startActivity(signin);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
    public void click_register(View view) {
        Intent reg = new Intent(SignUpScreen.this, LoginScreen.class);
        startActivity(reg);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}