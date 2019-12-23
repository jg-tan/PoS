package com.jgt.pos.ui.admin;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.jgt.pos.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * 1. Navigation Drawer
 * 2. Fragment Container
 * */
public class AdminActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        NavigationView navigationView = findViewById(R.id.nav_view_admin);
        DrawerLayout drawer = findViewById(R.id.activity_admin);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_admin_add_product,
                R.id.nav_admin_product_list,
                R.id.nav_admin_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

/**
 * When using Navigation Controller,
 * MENU and NAVIGATION IDs must be similar
 * */
