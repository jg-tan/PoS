package com.jgt.pos.kiosk;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.app.admin.SystemUpdatePolicy;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.UserManager;
import android.provider.Settings;

import com.jgt.pos.receiver.AdminReceiver;
import com.jgt.pos.ui.shop.ShopActivity;
import com.jgt.pos.utils.ContextManager;

public class KioskManager {
    private static final String TAG = KioskManager.class.getSimpleName();
    private static KioskManager instance;
    private Context context;
    private DevicePolicyManager dpm;
    private PackageManager pm;
    private ActivityManager am;
    private ComponentName adminReceiverComponent, lockTaskComponent;

    private KioskManager() {
        this.context = ContextManager.get();
        dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        pm = context.getPackageManager();
        am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        adminReceiverComponent = new ComponentName(context, AdminReceiver.class);
        lockTaskComponent = new ComponentName(context, ShopActivity.class);
    }

    public static KioskManager getInstance() {
        if (null == instance) {
            synchronized (KioskManager.class) {
                instance = new KioskManager();
            }
        }
        return instance;
    }

    public void applyLockPolicies() {
        setPolicies(true);
    }

    public void revokeLockPolicies() {
        setPolicies(false);
    }

    public void clearDeviceOwner() {
        dpm.clearDeviceOwnerApp("com.jgt.pos");
    }

    public void enableKiosk() {
//        dpm.clearDeviceOwnerApp("com.jgt.pos");
        pm.setComponentEnabledSetting(
                lockTaskComponent,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void disableKiosk() {
        pm.setComponentEnabledSetting(
                lockTaskComponent,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void setPolicies(boolean active) {
        // set user restrictions
        setUserRestriction(UserManager.DISALLOW_SAFE_BOOT, active);
        setUserRestriction(UserManager.DISALLOW_FACTORY_RESET, active);
        setUserRestriction(UserManager.DISALLOW_ADD_USER, active);
        setUserRestriction(UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA, active);
        setUserRestriction(UserManager.DISALLOW_ADJUST_VOLUME, active);

        // disable keyguard and status bar
        dpm.setKeyguardDisabled(adminReceiverComponent, active);
        dpm.setStatusBarDisabled(adminReceiverComponent, active);

        // enable STAY_ON_WHILE_PLUGGED_IN
        enableStayOnWhilePluggedIn(active);

        // set system update policy
        if (active) {
            dpm.setSystemUpdatePolicy(adminReceiverComponent,
                    SystemUpdatePolicy.createWindowedInstallPolicy(60, 120));
        } else {
            dpm.setSystemUpdatePolicy(adminReceiverComponent,
                    null);
        }

        // set this Activity as a lock task package

        dpm.setLockTaskPackages(adminReceiverComponent,
                active ? new String[]{context.getPackageName()} : new String[]{});

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MAIN);
        intentFilter.addCategory(Intent.CATEGORY_HOME);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        if (active) {
            dpm.addPersistentPreferredActivity(
                    adminReceiverComponent, intentFilter, new ComponentName(
                            context.getPackageName(), ShopActivity.class.getName()));
        } else {
            dpm.clearPackagePersistentPreferredActivities(
                    adminReceiverComponent, context.getPackageName());
        }
    }

    private void setUserRestriction(String restriction, boolean disallow) {
        if (disallow) {
            dpm.addUserRestriction(adminReceiverComponent,
                    restriction);
        } else {
            dpm.clearUserRestriction(adminReceiverComponent,
                    restriction);
        }
    }

    private void enableStayOnWhilePluggedIn(boolean enabled) {
        if (enabled) {
            dpm.setGlobalSetting(
                    adminReceiverComponent,
                    Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
                    Integer.toString(BatteryManager.BATTERY_PLUGGED_AC
                            | BatteryManager.BATTERY_PLUGGED_USB
                            | BatteryManager.BATTERY_PLUGGED_WIRELESS));
        } else {
            dpm.setGlobalSetting(
                    adminReceiverComponent,
                    Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
                    "0"
            );
        }
    }
}
