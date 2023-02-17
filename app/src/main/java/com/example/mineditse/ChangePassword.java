package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    public void click_cancel_change(View view) {
        Intent cancel = new Intent(ChangePassword.this, MyAccount.class);
        startActivity(cancel);
    }

    public void click_change_back(View view) {
        Intent back = new Intent(ChangePassword.this, MyAccount.class);
        startActivity(back);
    }
}