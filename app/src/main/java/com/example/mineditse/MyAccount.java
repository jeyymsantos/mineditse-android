package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyAccount extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView tvFirstName, tvLastName, tvPhone, tvAddress, tvEmail, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        sharedPreferences = getSharedPreferences("GDSCNUBaliwag", MODE_PRIVATE);
        tvFirstName = findViewById(R.id.view_first_name);
        tvLastName = findViewById(R.id.view_last_name);
        tvPhone = findViewById(R.id.view_phone_num);
        tvAddress = findViewById(R.id.view_address);
        tvEmail = findViewById(R.id.view_email);
        tvPassword = findViewById(R.id.view_password);

        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        String first_name = sharedPreferences.getString("first_name", "");
        String last_name = sharedPreferences.getString("last_name", "");
        String street = sharedPreferences.getString("street", "");
        String barangay = sharedPreferences.getString("barangay", "");
        String city = sharedPreferences.getString("city", "");
        String province = sharedPreferences.getString("province", "");
        String phone_number = sharedPreferences.getString("phone", "");

        tvFirstName.setText(first_name);
        tvLastName.setText(last_name);
        tvPhone.setText(phone_number);
        tvAddress.setText(street + ", " + barangay + ", " + city + ", " + province);
        tvEmail.setText(email);
        tvPassword.setText(password);
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