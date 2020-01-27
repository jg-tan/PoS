package com.jgt.pos.ui.shop;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jgt.pos.R;
import com.jgt.pos.database.cart.Cart;
import com.jgt.pos.database.cart.CartViewModel;
import com.jgt.pos.kiosk.KioskManager;
import com.jgt.pos.ui.admin.AdminActivity;
import com.jgt.pos.utils.ContextManager;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ShopActivity extends AppCompatActivity {
    private static final String TAG = ShopActivity.class.getSimpleName();

    private AppBarConfiguration mAppBarConfiguration;
    private MenuItem menuCart;
    private CartViewModel cartViewModel;
    private KioskManager kioskManager;
    private ActivityManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity);
        kioskManager = KioskManager.getInstance();
        am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        startKioskMode();
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
        menuCart = menu.findItem(R.id.shop_nav_cart_fragment);
        initCartViewModel();
        return true;
    }

    private void initCartViewModel() {
        ViewModelProvider provider = ViewModelProviders.of(this);
        cartViewModel = provider.get(CartViewModel.class);
        cartViewModel.init();
        cartViewModel.getCart().observe(this, this::onCartChanged);
    }

    private void onCartChanged(List<Cart> cart) {
        if (null == cart || cart.isEmpty()) {
            disableCartButton();
        } else {
            enableCartButton();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.shop_nav_admin_fragment:
                //TODO: check for password first
                disableKioskMode();
                startAdminActivity();
                finish();
                break;
            case R.id.shop_nav_cart_fragment:
                //NavController is attached to nav_host
                try {
                    Navigation.findNavController(this, R.id.shop_nav_host_fragment)
                            .navigate(R.id.on_cart_clicked);
                } catch (Exception e) {
                    Log.w(TAG, "Cart is double clicked, will cause force stop");
                }
                break;
            default:
                break;
        }
        return true;
    }

    public void disableCartButton() {
        menuCart.setEnabled(false);
        menuCart.setIcon(ContextManager.get().getDrawable(R.drawable.ic_shopping_cart_white_50p));
    }

    public void enableCartButton() {
        menuCart.setEnabled(true);
        menuCart.setIcon(ContextManager.get().getDrawable(R.drawable.ic_shopping_cart_white));
    }

    public void showCartButton() {
        if (null != menuCart) {
            menuCart.setVisible(true);
        }
    }

    public void hideCartButton() {
        if (null != menuCart) {
            menuCart.setVisible(false);
        }
    }


    private void startKioskMode() {
        kioskManager.applyLockPolicies();
        if (ActivityManager.LOCK_TASK_MODE_NONE ==
                am.getLockTaskModeState()) {
            startLockTask();
        }
    }

    private void disableKioskMode() {
        if (ActivityManager.LOCK_TASK_MODE_LOCKED ==
                am.getLockTaskModeState()) {
            stopLockTask();
        }
        kioskManager.revokeLockPolicies();
    }

    private void startAdminActivity() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}
