package com.jgt.pos.ui.admin.settings;

import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jgt.pos.R;
import com.jgt.pos.kiosk.KioskManager;
import com.jgt.pos.ui.shop.ShopActivity;

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
    private Button btnLock;
    private View rootView;
    private KioskManager kioskManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_fragment_settings, container, false);
        this.btnLock = rootView.findViewById(R.id.admin_settings_btn_lock);
        this.btnLock.setOnClickListener(this::onLockClicked);
        kioskManager = KioskManager.getInstance();
//        kioskManager.disableKiosk();
        return rootView;
    }

    private void onLockClicked(View view) {
        kioskManager.enableKiosk();
        Intent intent = new Intent(getActivity(), ShopActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
