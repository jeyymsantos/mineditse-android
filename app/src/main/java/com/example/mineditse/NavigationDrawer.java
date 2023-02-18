//package com.example.mineditse;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//    private DrawerLayout drawer;
//    SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_navigation_drawer);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        sharedPreferences = getSharedPreferences("GDSCNUBaliwag", MODE_PRIVATE);
//
//        drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
//                R.string.navigation_drawer_open, R.string.navigtaion_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_find_items:
//                Intent findItems = new Intent(NavigationDrawer.this, FindItems.class);
//                startActivity(findItems);
//                break;
//            case R.id.nav_products:
//                Intent products = new Intent(NavigationDrawer.this, CustomerHomepage.class);
//                startActivity(products);
//                break;
//            case R.id.nav_mine_cart:
//                //open cart overlay
//                Toast.makeText(getApplicationContext(), "Open Cart", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_my_orders:
//                //open my order page
//                Toast.makeText(getApplicationContext(), "Show orders history.", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_my_profile:
//                Intent myProfile = new Intent(NavigationDrawer.this, MyAccount.class);
//                startActivity(myProfile);
//                break;
//            case R.id.nav_about:
//                Intent about = new Intent(NavigationDrawer.this, AboutPage.class);
//                startActivity(about);
//                break;
//            case R.id.nav_logout: {
//
//
//                break;
//            }
//        }
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    public void onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//}