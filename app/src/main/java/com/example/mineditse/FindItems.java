package com.example.mineditse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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

public class FindItems extends AppCompatActivity {

    RecyclerView recyclerView;
    List<PostModel> postList = new ArrayList<>();
    List<PostModel> filterList = new ArrayList<>();
    String url = "https://mineditse.store/api/products";
    PostAdapter adapter;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_items);

        recyclerView = findViewById(R.id.search_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        GetData();

        search = findViewById(R.id.prod_search_bar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList.clear();

                if(s.toString().isEmpty()) {
                    recyclerView.setAdapter(new PostAdapter(getApplicationContext(), postList));
                    adapter.notifyDataSetChanged();
                }
                else {
                    Filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    private void Filter(String text) {
        for(PostModel post:postList) {
            if(post.getProdName().equalsIgnoreCase(text)) {
                filterList.add(post);
            }
        }
        recyclerView.setAdapter(new PostAdapter(getApplicationContext(), filterList));
        adapter.notifyDataSetChanged();
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
                Toast.makeText(FindItems.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }


    public void click_find_back(View view) {
        Intent back = new Intent(FindItems.this, CustomerHomepage.class);
        startActivity(back);
    }
}