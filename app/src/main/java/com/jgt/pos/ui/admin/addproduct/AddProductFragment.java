package com.jgt.pos.ui.admin.addproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jgt.pos.R;
import com.jgt.pos.database.item.Item;
import com.jgt.pos.database.item.ItemRepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddProductFragment extends Fragment {

    private EditText etName, etPrice;
    private Button btnAddIcon, btnAddItem;
    private ImageView ivIcon;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_fragment_add_product, container, false);
        this.etName = rootView.findViewById(R.id.admin_add_et_item_name);
        this.etPrice = rootView.findViewById(R.id.admin_add_et_item_price);
        this.btnAddIcon = rootView.findViewById(R.id.admin_add_btn_add_icon);
        this.btnAddItem = rootView.findViewById(R.id.admin_add_btn_add_item);
        this.ivIcon = rootView.findViewById(R.id.admin_add_iv_item_icon);
        btnAddIcon.setOnClickListener(this::onAddIconClicked);
        btnAddItem.setOnClickListener(this::onAddItemClicked);
        return rootView;
    }

    public void onAddIconClicked(View view) {
    }

    public void onAddItemClicked(View view) {
        Item item = new Item();
        item.setName(etName.getText().toString());
        item.setPrice(Integer.parseInt(etPrice.getText().toString()));
        item.setIcon(null); //TODO
        ItemRepository.getInstance().addItem(item);
    }
}
