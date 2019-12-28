package com.jgt.pos.ui.shop.grid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgt.pos.R;
import com.jgt.pos.database.cart.CartViewModel;
import com.jgt.pos.database.item.Item;
import com.jgt.pos.database.item.ItemViewModel;
import com.jgt.pos.ui.shop.ShopActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopGridFragment extends Fragment {

    private RecyclerView rvListView;
    private GridLayoutManager layoutManager;
    private ShopGridAdapter adapter;
    private View rootView;
    private ItemViewModel itemViewModel;
    private CartViewModel cartViewModel;
    private ShopActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("GAB", "GRID");
        rootView = inflater.inflate(R.layout.shop_fragment_grid, container, false);
        initRecyclerView();
        initViewModel();
        return rootView;
    }

    private void initViewModel() {
        ViewModelProvider provider = ViewModelProviders.of(this);
        itemViewModel = provider.get(ItemViewModel.class);
        itemViewModel.init();
        itemViewModel.getItems().observe(this, this::onItemListChanged);

        cartViewModel = provider.get(CartViewModel.class);
        cartViewModel.init();
    }

    private void onItemListChanged(List<Item> items) {
        adapter.setProductList(items);
    }

    private void initRecyclerView() {
        activity = (ShopActivity) getActivity();
        activity.showCartButton();
        rvListView = rootView.findViewById(R.id.shop_grid_fragment_rv_item_list);
        adapter = new ShopGridAdapter();
        adapter.setOnClickListener(this::onAdapterClicked);
        layoutManager = new GridLayoutManager(activity, 4);
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
    }

    private void onAdapterClicked(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.shop_grid_btn_item:
                Item item = (Item) v.getTag();
                cartViewModel.addToCart(item);
                break;
            default:
                break;
        }
    }
}
