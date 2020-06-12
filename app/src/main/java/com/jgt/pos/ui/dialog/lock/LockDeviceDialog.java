package com.jgt.pos.ui.dialog.lock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jgt.pos.R;

public class LockDeviceDialog {
    private static final String TAG = LockDeviceDialog.class.getSimpleName();
    private static LockDeviceDialog instance;

    private EditText etPassword;
    private Button btnLock;
    private View dialogView;

    public static LockDeviceDialog getInstance() {
        if (null == instance) {
            synchronized (LockDeviceDialog.class) {
                instance = new LockDeviceDialog();
            }
        }
        return instance;
    }

    public void showDialog(Activity activityContext, View rootView) {
        Context context = activityContext;
        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_lock_device, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        initViews();

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initViews() {
        this.etPassword = dialogView.findViewById(R.id.dialog_et_pw);
        this.btnLock = dialogView.findViewById(R.id.dialog_btn_lock);
        this.btnLock.setOnClickListener(this::onLockClicked);
    }

    private void onLockClicked(View view) {
    }
}
