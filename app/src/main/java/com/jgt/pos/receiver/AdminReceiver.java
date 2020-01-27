package com.jgt.pos.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AdminReceiver extends DeviceAdminReceiver {
    private static final String TAG = AdminReceiver.class.getSimpleName();

    @Override
    public void onEnabled(Context context, Intent intent) {
        Log.d(TAG, "@AdminReceiver: onEnabled");
        super.onEnabled(context, intent);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Log.d(TAG, "@AdminReceiver: onDisabled");
        super.onDisabled(context, intent);
    }
}
