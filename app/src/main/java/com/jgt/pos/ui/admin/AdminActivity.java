package com.jgt.pos.ui.admin;

import android.os.Bundle;

import com.jgt.pos.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 1. Navigation Drawer
 * 2. Fragment Container
 * */
public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
}
