package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity{

    EditText rfirsname, rlastname, rphonenum, rstreet, rbarangay, rcity, rprovince, remail, rpassword;
    ProgressBar progressBar;
    TextView tvError;
    Button regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        //register
        tvError = findViewById(R.id.error);
        rfirsname = findViewById(R.id.signup_input_fname);
        rlastname = findViewById(R.id.signup_input_lname);
        rphonenum = findViewById(R.id.signup_input_phoneNum);
        rstreet = findViewById(R.id.signup_input_street);
        rbarangay = findViewById(R.id.signup_input_barangay);
        rcity = findViewById(R.id.signup_input_city);
        remail = findViewById(R.id.signup_input_email);
        rprovince = findViewById(R.id.signup_input_address);
        rpassword = findViewById(R.id.signup_create_password);
        progressBar = findViewById(R.id.loading);

        regbtn = findViewById(R.id.btn_register);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.GONE);

                String fname = rfirsname.getText().toString();
                String lname = rlastname.getText().toString();
                String phonenum = rphonenum.getText().toString();
                String street = rstreet.getText().toString();
                String barangay = rbarangay.getText().toString();
                String city = rcity.getText().toString();
                String province = rprovince.getText().toString();
                String email = remail.getText().toString();
                String password = rpassword.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://mineditse.store/api/create";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);

                                if(response.equals("Customer saved successfully!")){
                                    Toast.makeText(SignUpScreen.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else if(response.equals("All fields required!")){
                                    tvError.setText(response);
                                    tvError.setVisibility(View.VISIBLE);
                                }else if(response.equals("Email already exist!")){
                                    tvError.setText(response);
                                    tvError.setVisibility(View.VISIBLE);
                                }else{
                                    tvError.setText("Make sure your email is valid and password is at least 8 characters.");
                                    tvError.setVisibility(View.VISIBLE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("first_name", fname);
                        paramV.put("last_name", lname);
                        paramV.put("phone",phonenum);
                        paramV.put("street",street);
                        paramV.put("barangay",barangay);
                        paramV.put("city",city);
                        paramV.put("province",province);
                        paramV.put("email", email);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);

            }
        });

    }

    public void click_signin(View view) {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}