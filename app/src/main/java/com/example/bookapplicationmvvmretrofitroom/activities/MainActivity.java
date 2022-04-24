package com.example.bookapplicationmvvmretrofitroom.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookapplicationmvvmretrofitroom.R;
import com.example.bookapplicationmvvmretrofitroom.fragment.DisplayBooksFragment;
import com.example.bookapplicationmvvmretrofitroom.fragment.FavoriteDisplayBooksFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav =findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DisplayBooksFragment()).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setSelectedItemId(R.id.nav_display_books);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_display_books:k:
                    selectedFragment = new DisplayBooksFragment();
                    break;
            }
            switch (item.getItemId()) {
                case R.id.nav_favorite_books:
                    selectedFragment = new FavoriteDisplayBooksFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };



}