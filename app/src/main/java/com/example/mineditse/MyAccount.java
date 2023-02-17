package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    }

    public void click_edit_profile(View view) {
        Intent edit = new Intent(MyAccount.this, EditProfile.class);
        startActivity(edit);
    }

    public void click_change_pw(View view) {
        Intent change = new Intent(MyAccount.this, ChangePassword.class);
        startActivity(change);
    }

    public void click_myAcc_back(View view) {
        Intent back = new Intent(MyAccount.this, CustomerHomepage.class);
        startActivity(back);
    }
}