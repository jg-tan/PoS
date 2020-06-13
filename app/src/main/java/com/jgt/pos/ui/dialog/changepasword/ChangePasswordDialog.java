package com.jgt.pos.ui.dialog.changepasword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jgt.pos.R;
import com.jgt.pos.ui.dialog.DialogResultListener;
import com.jgt.pos.utils.SharedPref;

public class ChangePasswordDialog {
    private static final String TAG = ChangePasswordDialog.class.getSimpleName();
    private static ChangePasswordDialog instance;

    private EditText etCurPw, etNewPw, etConfPw;
    private Button btnChangePw;
    private View dialogView;
    private DialogResultListener callback;
    private Context context;
    private AlertDialog dialog;

    public static ChangePasswordDialog getInstance() {
        if (null == instance) {
            synchronized (ChangePasswordDialog.class) {
                instance = new ChangePasswordDialog();
            }
        }
        return instance;
    }

    public void showDialog(Activity activityContext, View rootView, DialogResultListener listener) {
        callback = listener;
        context = activityContext;
        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_change_pw, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        initViews();

        builder.setView(dialogView);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void initViews() {
        this.etCurPw = dialogView.findViewById(R.id.dialog_change_pw_et_cur_pw);
        this.etNewPw = dialogView.findViewById(R.id.dialog_change_pw_et_new_pw);
        this.etConfPw = dialogView.findViewById(R.id.dialog_change_pw_et_confirm_pw);
        this.btnChangePw = dialogView.findViewById(R.id.dialog_change_pw_btn_change_pw);
        this.btnChangePw.setOnClickListener(this::onChangePasswordClicked);
    }

    private void onChangePasswordClicked(View view) {
        String oldPassword = etCurPw.getText().toString();
        String newPassword = etNewPw.getText().toString();
        String confirmPassword = etConfPw.getText().toString();
        String currentPassword = SharedPref.get().getPassword();

        if (TextUtils.isEmpty(oldPassword)) {
            Toast.makeText(context, "Please input current password.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!currentPassword.equals(oldPassword)) {
            Toast.makeText(context, "Wrong current password.", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(context, "Please input new password.", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(context, "Please confirm your password.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!confirmPassword.equals(newPassword)) {
            Toast.makeText(context, "Passwords do not match.", Toast.LENGTH_LONG).show();
            return;
        }

        SharedPref.get().setPassword(newPassword);
        callback.onSuccess();
        dialog.dismiss();
    }
}
