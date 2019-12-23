package com.jgt.pos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jgt.pos.ui.productscreen.ProductScreenActivity;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            context.startActivity(new Intent(context, ProductScreenActivity.class));
        }
    }
}
