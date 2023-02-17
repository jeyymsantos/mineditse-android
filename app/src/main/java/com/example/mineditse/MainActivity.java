package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click_getStarted(View view) {
        Intent start = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(start);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}