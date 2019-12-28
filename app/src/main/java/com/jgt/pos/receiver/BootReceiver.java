package com.jgt.pos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jgt.pos.ui.shop.ShopActivity;
import com.jgt.pos.utils.Constants;
import com.jgt.pos.utils.SharedPref;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            //TODO: Set device to kiosk mode if locked
            String password = SharedPref.get().getPassword();
            if (!Constants.DEFAULT_PASSWORD.equals(password)) {
                context.startActivity(new Intent(context, ShopActivity.class));
            }
        }
    }
}
