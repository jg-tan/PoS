package com.jgt.pos.ui.shop.grid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.ui.admin.productlist.ProductListAdapter;
import com.jgt.pos.utils.TestUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopGridFragment extends Fragment {

    private RecyclerView rvListView;
    private GridLayoutManager layoutManager;
    private ShopGridAdapter adapter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GAB", "GRID");
        rootView = inflater.inflate(R.layout.shop_fragment_grid, container, false);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        Activity activity = getActivity();
        rvListView = rootView.findViewById(R.id.shop_grid_fragment_rv_item_list);
        adapter = new ShopGridAdapter();
        layoutManager = new GridLayoutManager(activity, 4);
        adapter.setProductList(TestUtils.getTestProductList());
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
