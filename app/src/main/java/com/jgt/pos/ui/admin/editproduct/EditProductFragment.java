package com.jgt.pos.ui.admin.editproduct;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jgt.pos.R;
import com.jgt.pos.database.item.Item;
import com.jgt.pos.database.item.ItemRepository;
import com.jgt.pos.utils.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 1. Can change PRICE
 */
public class EditProductFragment extends Fragment {

    private EditText etName, etPrice;
    private Button btnAddIcon, btnEditItem;
    private ImageView ivIcon;
    private View rootView;
    private Item item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_fragment_edit_product, container, false);
        this.etName = rootView.findViewById(R.id.admin_edit_et_item_name);
        this.etPrice = rootView.findViewById(R.id.admin_edit_et_item_price);
        this.btnAddIcon = rootView.findViewById(R.id.admin_edit_btn_add_icon);
        this.btnEditItem = rootView.findViewById(R.id.admin_edit_btn_edit_item);
        this.ivIcon = rootView.findViewById(R.id.admin_edit_iv_item_icon);
        btnAddIcon.setOnClickListener(this::onAddIconClicked);
        btnEditItem.setOnClickListener(this::onEditItemClicked);
        Bundle bundle = getArguments();
        if (null != getArguments()) {
            int itemId = bundle.getInt(Constants.EDIT_EXTRA_ITEM_ID);
            item = ItemRepository.getInstance().getItem(itemId);
            this.etName.setText(item.getName());
            this.etPrice.setText(String.valueOf(item.getPrice()));
        }
        return rootView;
    }

    public void onAddIconClicked(View view) {
    }

    public void onEditItemClicked(View view) {
        int itemId = item.getItemId();
        String itemName = item.getName();
        int itemPrice = item.getPrice();
        String itemIcon = item.getIcon();
        Log.d("EXD", "onEditClicked " + item);
        ItemRepository.getInstance().editItem(itemId, itemName, itemPrice, itemIcon);
    }
}
