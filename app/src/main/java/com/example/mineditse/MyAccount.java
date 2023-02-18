package com.example.mineditse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MyAccount extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView tvFirstName, tvLastName, tvPhone, tvAddress, tvEmail, tvPassword;
    Button btnDeactivate;
    TextView tvError;
    ProgressBar loading;

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
        btnDeactivate = findViewById(R.id.btn_myAcc_deleteAcc);
        tvError = findViewById(R.id.error);
        loading = findViewById(R.id.loading);

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

        btnDeactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deactivate("Deactivate Account", "Are you sure you want to deactivate your account?");
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(MyAccount.this, CustomerHomepage.class));
        finish();
    }

    private void Deactivate(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyAccount.this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            loading.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);

            String id = sharedPreferences.getString("id", "");

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "https://mineditse.store/api/deactivate";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.setVisibility(View.GONE);

                            if(!response.equals("Your account is now deactivated!")){
                                tvError.setText(response);
                                tvError.setVisibility(View.VISIBLE);
                                return;
                            }

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.putString("logged", "false");
                            editor.apply();

                            Toast.makeText(MyAccount.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MyAccount.this, MainActivity.class));
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
                    return paramV;
                }
            };
            queue.add(stringRequest);
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void click_edit_profile(View view) {
        Intent edit = new Intent(MyAccount.this, EditProfile.class);
        startActivity(edit);
        finish();
    }

    public void click_myAcc_back(View view) {
        startActivity(new Intent(MyAccount.this, CustomerHomepage.class));
        finish();
    }
}