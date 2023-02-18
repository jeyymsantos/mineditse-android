package com.example.mineditse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerHomepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    RecyclerView recyclerView;
    List<PostModel> postList = new ArrayList<>();
    String url = "https://mineditse.store/api/products";
    PostAdapter adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_homepage);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Mine Ditse");
        toolbar.setTitleTextAppearance(CustomerHomepage.this, R.style.toolbar_custom_title);
        sharedPreferences = getSharedPreferences("GDSCNUBaliwag", MODE_PRIVATE);

        drawer = findViewById(R.id.home_drawer_layout);
        NavigationView navigationView = findViewById(R.id.home_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.home_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        GetData();
    }

    private void GetData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i<response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        postList.add(new PostModel(
                                jsonObject.getString("prod_name"),
                                jsonObject.getString("prod_status"),
                                jsonObject.getString("prod_price"),
                                jsonObject.getString("prod_img_path"),
                                jsonObject.getInt("bale_id")
                        ));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    progressDialog.dismiss();
                    adapter = new PostAdapter(getApplicationContext(), postList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(CustomerHomepage.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_find_items:
                Intent findItems = new Intent(CustomerHomepage.this, FindItems.class);
                startActivity(findItems);
                break;
            case R.id.nav_products:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_mine_cart:
                //open cart overlay
                Toast.makeText(getApplicationContext(), "Open Cart", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_my_orders:
                //open my order page
                Toast.makeText(getApplicationContext(), "Show orders history.", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_my_profile:
                Intent myProfile = new Intent(CustomerHomepage.this, MyAccount.class);
                startActivity(myProfile);
                break;
            case R.id.nav_about:
                Intent about = new Intent(CustomerHomepage.this, AboutPage.class);
                startActivity(about);
                break;
            case R.id.nav_logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("logged", "false");
                editor.putString("id", "");
                editor.apply();

                Intent logout = new Intent(CustomerHomepage.this, LoginScreen.class);
                startActivity(logout);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}