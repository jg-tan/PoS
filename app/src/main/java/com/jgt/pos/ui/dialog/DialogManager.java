package com.jgt.pos.ui.dialog;

import android.app.Activity;
import android.view.View;

import com.jgt.pos.ui.dialog.changepasword.ChangePasswordDialog;
import com.jgt.pos.ui.dialog.lock.LockDeviceDialog;

public class DialogManager {
    public static final String TAG = DialogManager.class.getSimpleName();
    private static DialogManager instance;

    public static DialogManager getInstance() {
        if (null == instance) {
            synchronized (DialogManager.class) {
                instance = new DialogManager();
            }
        }
        return instance;
    }

    public void showChangePasswordDialog(Activity activityContext, View rootView) {
        ChangePasswordDialog.getInstance().showDialog(activityContext, rootView);
    }

    public void showLockDeviceDialog(Activity activityContext, View rootView) {
        LockDeviceDialog.getInstance().showDialog(activityContext, rootView);
    }
}
