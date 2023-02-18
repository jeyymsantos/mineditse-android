package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText etFirstName, etLastName, etPhone, etStreet, etBrgy, etCity, etProvince, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sharedPreferences = getSharedPreferences("GDSCNUBaliwag", MODE_PRIVATE);
        etFirstName = findViewById(R.id.edit_fname);
        etLastName = findViewById(R.id.edit_lname);
        etPhone = findViewById(R.id.edit_phone_num);
        etStreet = findViewById(R.id.edit_street);
        etBrgy = findViewById(R.id.edit_barangay);
        etCity = findViewById(R.id.edit_city);
        etProvince = findViewById(R.id.edit_province);
        etEmail = findViewById(R.id.edit_email);
        String email = sharedPreferences.getString("email", "");
        String first_name = sharedPreferences.getString("first_name", "");
        String last_name = sharedPreferences.getString("last_name", "");
        String street = sharedPreferences.getString("street", "");
        String barangay = sharedPreferences.getString("barangay", "");
        String city = sharedPreferences.getString("city", "");
        String province = sharedPreferences.getString("province", "");
        String phone_number = sharedPreferences.getString("phone", "");

        etFirstName.setText(first_name);
        etLastName.setText(last_name);
        etPhone.setText(phone_number);
        etStreet.setText(street);
        etBrgy.setText(barangay);
        etCity.setText(city);
        etProvince.setText(province);
        etEmail.setText(email);
    }

    public void click_cancel_edit(View view) {
        finish();
    }

    public void click_edit_back(View view) {
        finish();
    }
}