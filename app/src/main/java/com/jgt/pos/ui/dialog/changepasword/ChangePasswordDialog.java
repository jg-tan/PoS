package com.jgt.pos.ui.dialog.changepasword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jgt.pos.R;

public class ChangePasswordDialog {
    private static final String TAG = ChangePasswordDialog.class.getSimpleName();
    private static ChangePasswordDialog instance;

    private EditText etCurPw, etNewPw, etConfPw;
    private Button btnChangePw;
    private View dialogView;

    public static ChangePasswordDialog getInstance() {
        if (null == instance) {
            synchronized (ChangePasswordDialog.class) {
                instance = new ChangePasswordDialog();
            }
        }
        return instance;
    }

    public void showDialog(Activity activityContext, View rootView) {
        Context context = activityContext;
        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_change_pw, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        initViews();

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initViews() {
        this.etCurPw = dialogView.findViewById(R.id.dialog_et_cur_pw);
        this.etNewPw = dialogView.findViewById(R.id.dialog_et_new_pw);
        this.etConfPw = dialogView.findViewById(R.id.dialog_et_confirm_pw);
        this.btnChangePw = dialogView.findViewById(R.id.dialog_btn_change_pw);
        this.btnChangePw.setOnClickListener(this::onChangePasswordClicked);
    }

    private void onChangePasswordClicked(View view) {
    }
}
