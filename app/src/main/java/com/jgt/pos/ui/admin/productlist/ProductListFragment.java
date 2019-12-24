package com.jgt.pos.ui.admin.productlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 1. Show complete lists of registered products
 * 2. ACTION: Long press on item -> Show EDIT FRAGMENT
 * 3. ACTION: Press DELETE button -> Delete item
 * */
public class ProductListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.admin_fragment_product_list, container, false);
        return root;
    }
}
