package com.jgt.pos.ui.dialog.lock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jgt.pos.R;
import com.jgt.pos.ui.dialog.DialogResultListener;
import com.jgt.pos.utils.SharedPref;

public class LockDeviceDialog {
    private static final String TAG = LockDeviceDialog.class.getSimpleName();
    private static LockDeviceDialog instance;

    private EditText etPassword;
    private Button btnLock;
    private View dialogView;
    private Context context;
    private DialogResultListener callback;
    private AlertDialog dialog;

    public static LockDeviceDialog getInstance() {
        if (null == instance) {
            synchronized (LockDeviceDialog.class) {
                instance = new LockDeviceDialog();
            }
        }
        return instance;
    }

    public void showDialog(Activity activityContext, View rootView, DialogResultListener listener) {
        callback = listener;
        context = activityContext;
        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_lock_device, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        initViews();

        builder.setView(dialogView);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void initViews() {
        this.etPassword = dialogView.findViewById(R.id.dialog_lock_device_et_pw);
        this.btnLock = dialogView.findViewById(R.id.dialog_lock_device_btn_lock);
        this.btnLock.setOnClickListener(this::onLockClicked);
    }

    private void onLockClicked(View view) {
        String password = SharedPref.get().getPassword();

        if (password.equals(etPassword.getText().toString())) {
            callback.onSuccess();
        } else {
            Toast.makeText(context, "Wrong password.", Toast.LENGTH_LONG).show();
        }
    }
}
