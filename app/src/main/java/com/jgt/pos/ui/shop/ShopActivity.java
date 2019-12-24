package com.jgt.pos.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jgt.pos.R;
import com.jgt.pos.ui.admin.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ShopActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity);
        Toolbar toolbar = findViewById(R.id.tb_shop);
        setSupportActionBar(toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.shop_nav_cart_fragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.shop_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(toolbar, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.shop_nav_admin_fragment:
                //TODO: check for password first
                this.startActivity(new Intent(this, AdminActivity.class));
                break;
            case R.id.shop_nav_cart_fragment:
                //NavController is attached to nav_host
                try {
                    Navigation.findNavController(this, R.id.shop_nav_host_fragment)
                            .navigate(R.id.on_cart_clicked);
                } catch (Exception e) {
                    Log.w("GAB", "Cart is double clicked, will cause force stop");
                }
                break;
            default:
                break;
        }
        return true;
    }
}
