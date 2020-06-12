package com.jgt.pos.ui.dialog.newpassword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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

public class NewPasswordDialog {
    private static final String TAG = NewPasswordDialog.class.getSimpleName();
    private static NewPasswordDialog instance;

    private EditText etNewPw, etConfPw;
    private Button btnChangePw;
    private View dialogView;
    private Context context;
    private AlertDialog dialog;
    private DialogResultListener callback;

    public static NewPasswordDialog getInstance() {
        if (null == instance) {
            synchronized (NewPasswordDialog.class) {
                instance = new NewPasswordDialog();
            }
        }
        return instance;
    }

    public void showDialog(Activity activityContext, View rootView, DialogResultListener listener) {
        callback = listener;
        context = activityContext;
        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_pw, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        initViews();

        builder.setView(dialogView);

        dialog = builder.create();
        dialog.show();
    }

    private void initViews() {
        this.etNewPw = dialogView.findViewById(R.id.dialog_new_pw_et_new_pw);
        this.etConfPw = dialogView.findViewById(R.id.dialog_new_pw_et_confirm_pw);
        this.btnChangePw = dialogView.findViewById(R.id.dialog_new_pw_btn_change_pw);
        this.btnChangePw.setOnClickListener(this::onChangePasswordClicked);
    }

    private void onChangePasswordClicked(View view) {
        String newPassword = etNewPw.getText().toString();
        String confirmPassword = etConfPw.getText().toString();

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
