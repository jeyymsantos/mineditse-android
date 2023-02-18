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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText etFirstName, etLastName, etPhone, etStreet, etBarangay, etCity, etProvince;
    TextView tvError;
    ProgressBar loading;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sharedPreferences = getSharedPreferences("GDSCNUBaliwag", MODE_PRIVATE);
        etFirstName = findViewById(R.id.edit_fname);
        etLastName = findViewById(R.id.edit_lname);
        etPhone = findViewById(R.id.edit_phone_num);
        etStreet = findViewById(R.id.edit_street);
        etBarangay = findViewById(R.id.edit_barangay);
        etCity = findViewById(R.id.edit_city);
        etProvince = findViewById(R.id.edit_province);
        btnSave = findViewById(R.id.btn_save_edit);

        tvError = findViewById(R.id.error);
        loading = findViewById(R.id.loading);

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
        etBarangay.setText(barangay);
        etCity.setText(city);
        etProvince.setText(province);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_edit_save();
            }
        });
    }

    public void click_cancel_edit(View view) {
        startActivity(new Intent(EditProfile.this, MyAccount.class));
        finish();
    }

    public void click_edit_back(View view) {
        startActivity(new Intent(EditProfile.this, MyAccount.class));
        finish();
    }

    private void click_edit_save() {
        loading.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);

        String id = sharedPreferences.getString("id", "");
        String first_name = etFirstName.getText().toString();
        String last_name = etLastName.getText().toString();
        String street = etStreet.getText().toString();
        String barangay = etBarangay.getText().toString();
        String city = etCity.getText().toString();
        String province = etProvince.getText().toString();
        String phone_number = etPhone.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://mineditse.store/api/edit";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.setVisibility(View.GONE);

                        if(!response.equals("Customer updated successfully!")){
                            tvError.setText(response);
                            tvError.setVisibility(View.VISIBLE);
                            return;
                        }

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("first_name", first_name);
                        editor.putString("last_name", last_name);
                        editor.putString("street", street);
                        editor.putString("barangay", barangay);
                        editor.putString("city", city);
                        editor.putString("province", province);
                        editor.putString("phone", phone_number);
                        editor.apply();

                        Toast.makeText(EditProfile.this, "Update Successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditProfile.this, MyAccount.class));
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
                tvError.setText(error.getLocalizedMessage());
                tvError.setVisibility(View.VISIBLE);
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id", id);
                paramV.put("first_name", first_name);
                paramV.put("last_name", last_name);
                paramV.put("phone", phone_number);
                paramV.put("street", street);
                paramV.put("barangay", barangay);
                paramV.put("city", city);
                paramV.put("province", province);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }


}