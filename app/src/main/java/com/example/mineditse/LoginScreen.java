package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    TextView tvError;
    ProgressBar loading;
    EditText etEmail, etPassword;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        sharedPreferences = getSharedPreferences("GDSCNUBaliwag", MODE_PRIVATE);

        if(sharedPreferences.getString("logged", "false").equals("true")){
            Intent i = new Intent(getApplicationContext(), CustomerHomepage.class);
            startActivity(i);
            finish();
        }

        tvError = findViewById(R.id.error);
        loading = findViewById(R.id.loading);
        etEmail = findViewById(R.id.login_input_email);
        etPassword = findViewById(R.id.login_input_password);
    }

    public void click_login(View view) {
        Login();
    }

    public void click_createAcct(View view) {
        Intent create = new Intent(LoginScreen.this, SignUpScreen.class);
        startActivity(create);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void Login(){
        loading.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="https://mineditse.store/api/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.setVisibility(View.GONE);

                        try{
                            JSONObject user = new JSONObject(response);
                            String type = user.getString("cust_type");

                            if(type.equals("DEACTIVATED")){
                                tvError.setText("Sorry, your account is deactivated. Reach out Mine Ditse to reactivate your account.");
                                tvError.setVisibility(View.VISIBLE);
                                return;
                            }

                            String id = user.getString("cust_id");
                            String first_name = user.getString("first_name");
                            String last_name = user.getString("last_name");
                            String street = user.getString("cust_street");
                            String barangay = user.getString("cust_barangay");
                            String city = user.getString("cust_city");
                            String province = user.getString("cust_province");
                            String phone = user.getString("phone_number");

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("logged", "true");
                            editor.putString("id", id);
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putString("first_name", first_name);
                            editor.putString("last_name", last_name);
                            editor.putString("street", street);
                            editor.putString("barangay", barangay);
                            editor.putString("city", city);
                            editor.putString("province", province);
                            editor.putString("phone", phone);
                            editor.apply();

                            Intent i = new Intent(LoginScreen.this, CustomerHomepage.class);
                            Toast.makeText(LoginScreen.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }catch(Exception e){
                            tvError.setText(response);
                            tvError.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.setVisibility(View.GONE);
                tvError.setText(error.getLocalizedMessage());
                tvError.setVisibility(View.VISIBLE);
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("email", email);
                paramV.put("password", password);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}