package com.jgt.pos.ui.dialog;

import android.app.Activity;
import android.view.View;

import com.jgt.pos.ui.dialog.changepasword.ChangePasswordDialog;
import com.jgt.pos.ui.dialog.lock.LockDeviceDialog;
import com.jgt.pos.ui.dialog.newpassword.NewPasswordDialog;

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

    public void showNewPassword(Activity activityContext, View rootView, DialogResultListener listener) {
        NewPasswordDialog.getInstance().showDialog(activityContext, rootView, listener);
    }

    public void showChangePasswordDialog(Activity activityContext, View rootView, DialogResultListener listener) {
        ChangePasswordDialog.getInstance().showDialog(activityContext, rootView, listener);
    }

    public void showLockDeviceDialog(Activity activityContext, View rootView, DialogResultListener listener) {
        LockDeviceDialog.getInstance().showDialog(activityContext, rootView, listener);
    }
}
