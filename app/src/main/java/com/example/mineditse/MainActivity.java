package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("GDSCNUBaliwag", MODE_PRIVATE);

        if(sharedPreferences.getString("logged", "false").equals("true")){
            Intent i = new Intent(getApplicationContext(), CustomerHomepage.class);
            startActivity(i);
            finish();
        }
    }

    public void click_getStarted(View view) {
        Intent start = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(start);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}