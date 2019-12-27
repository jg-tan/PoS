package com.jgt.pos.ui.admin.productlist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.database.item.Item;
import com.jgt.pos.database.item.ItemViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 1. Show complete lists of registered products
 * 2. ACTION: Long press on item -> Show EDIT FRAGMENT
 * 3. ACTION: Press DELETE button -> Delete item
 */
public class ProductListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rvListView;
    private LinearLayoutManager layoutManager;
    private ProductListAdapter adapter;
    private View rootView;
    private ItemViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GAB", "GRID");
        rootView = inflater.inflate(R.layout.admin_fragment_product_list, container, false);
        initRecyclerView();
        hideButtonsFromLabel();
        initViewModel();
        return rootView;
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        viewModel.init();
        viewModel.getItems().observe(this, this::onItemListChanged);
    }

    private void onItemListChanged(List<Item> items) {
        adapter.setProductList(items);
    }

    private void initRecyclerView() {
        Activity activity = getActivity();
        rvListView = rootView.findViewById(R.id.admin_product_list_fragment_rv_item_list);
        adapter = new ProductListAdapter();
        adapter.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(activity);
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
    }

    private void hideButtonsFromLabel() {
        rootView.findViewById(R.id.admin_product_list_iv_item_icon).setVisibility(View.INVISIBLE);
        rootView.findViewById(R.id.admin_product_list_btn_delete).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.admin_product_list_btn_delete:
                Item item = (Item) v.getTag();
                viewModel.deleteItem(item.getName());
                break;
            default:
                break;
        }
    }
}
