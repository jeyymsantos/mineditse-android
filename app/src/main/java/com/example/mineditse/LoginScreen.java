package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginScreen extends AppCompatActivity {

    EditText email, password;
    String user_email, user_password;
    TextView error;
    LoginLocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = findViewById(R.id.login_input_email);
        password = findViewById(R.id.login_input_password);
        error = findViewById(R.id.login_view_error);

        localStorage = new LoginLocalStorage(LoginScreen.this);
    }

    public void click_login(View view) {
        user_email = email.getText().toString();
        user_password = password.getText().toString();

        if (user_email.isEmpty() && user_password.isEmpty()) {
            error.setText("All fields are required.");
        }
        else {
            Login();
        }
//        Intent login = new Intent(LoginScreen.this, CustomerHomepage.class);
//        startActivity(login);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void Login() {
        JSONObject params = new JSONObject();
        try {
            params.put("email", user_email);
            params.put("password", user_password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data = params.toString();
        String url = "https://mineditse.store/api/login";

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(LoginScreen.this, url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if(code == 200) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String token = response.getString("token");
                                localStorage.setToken(token);
                                Intent intentLogin = new Intent(LoginScreen.this, CustomerHomepage.class);
                                startActivity(intentLogin);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (code == 422){
                            try{
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                error.setText(msg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (code == 401) {
                            try{
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                error.setText(msg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            Toast.makeText(LoginScreen.this, "Error" + code, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public void click_createAcct(View view) {
        Intent create = new Intent(LoginScreen.this, SignUpScreen.class);
        startActivity(create);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}