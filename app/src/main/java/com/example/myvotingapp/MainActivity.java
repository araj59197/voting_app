package com.example.myvotingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DrawerLayout drawer;
        NavigationView navview;
        Toolbar toolbar;


        //////DRAWER LAYOUT
        drawer = findViewById(R.id.drawerlayout);
        navview = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);



        ///step 1
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.off_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        loadfragment(new AFragment());

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.home) {
                    replacefragment(new HomeFragment());
                    Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.Aboutus) {
                    replacefragment(new Aboutus());
                    Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.admin) {
                    replacefragment(new admin());
                    Toast.makeText(MainActivity.this, "Admin Login", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.userlogin) {
                    Intent intent = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.Viewres) {
                    webfrag(new setting());
                    Toast.makeText(MainActivity.this, "Election Result Display here!", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.logout) {
                    Intent intent = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(intent);
                    return true;

                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void replacefragment(HomeFragment Fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, Fragment);
        fragmentTransaction.commit();
    }



    private void replacefragment(admin Fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, Fragment);
        fragmentTransaction.commit();
    }

    private void replacefragment(Aboutus Fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, Fragment);
        fragmentTransaction.commit();
    }

    private void webfrag(setting Fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, Fragment);
        fragmentTransaction.commit();
    }


}


