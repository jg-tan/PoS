package com.jgt.pos.ui.admin.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jgt.pos.R;
import com.jgt.pos.kiosk.KioskManager;
import com.jgt.pos.ui.dialog.DialogManager;
import com.jgt.pos.ui.shop.ShopActivity;
import com.jgt.pos.utils.Constants;
import com.jgt.pos.utils.SharedPref;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 1. Export to CSV
 * 2. Lock/Unlock (Slider)
 * 3. Change password
 * 4. Delete Sales -> Ask for password
 */
public class SettingsFragment extends Fragment {
    private static final String TAG = SettingsFragment.class.getSimpleName();

    private Button btnLock, btnChangePw;
    private View rootView;
    private KioskManager kioskManager;

    //TODO: TEMP
    private Button BTNCLEARDEVICEOWNER;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_fragment_settings, container, false);
        this.btnLock = rootView.findViewById(R.id.admin_settings_btn_lock);
        this.btnChangePw = rootView.findViewById(R.id.admin_settings_btn_change_pw);
        this.btnLock.setOnClickListener(this::onLockClicked);
        this.btnChangePw.setOnClickListener(this::onChangePwClicked);

        this.BTNCLEARDEVICEOWNER = rootView.findViewById(R.id.TEMP_CLEAR_DEVICE_OWNER);
        //TODO: TEMP
        this.BTNCLEARDEVICEOWNER.setOnClickListener(this::ONREMOVECLICKED);
        kioskManager = KioskManager.getInstance();
//        kioskManager.disableKiosk();
        return rootView;
    }

    //TODO: TEMP
    private void ONREMOVECLICKED(View view) {
        kioskManager.clearDeviceOwner();
    }

    private void onChangePwClicked(View view) {
        DialogManager.getInstance().showChangePasswordDialog(getActivity(), rootView, this::onChangePasswordSuccess);
    }

    private void onLockClicked(View view) {
         String password = SharedPref.get().getPassword();

        if (Constants.DEFAULT_PASSWORD.equals(password)) {
            Log.d(TAG, "No password set yet.");
            DialogManager.getInstance().showNewPassword(getActivity(), rootView, this::onChangePasswordSuccess);
        } else {
            DialogManager.getInstance().showLockDeviceDialog(getActivity(), rootView, this::onLockSuccess);
        }
    }

    private void onLockSuccess() {
        kioskManager.enableKiosk();
        Intent intent = new Intent(getActivity(), ShopActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    private void onChangePasswordSuccess() {
        Toast.makeText(getActivity(), "Successfully changed password. \nPlease lock again and input new password.", Toast.LENGTH_LONG).show();
    }
}
