package com.jgt.pos.ui.admin.saleshistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jgt.pos.R;
import com.jgt.pos.database.sales.Sales;
import com.jgt.pos.utils.TimeFormatter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SalesHistoryAdapter extends RecyclerView.Adapter<SalesHistoryAdapter.SalesHistoryViewHolder> {
    private List<Sales> salesList;

    @NonNull
    @Override
    public SalesHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.admin_adapter_sales_history, parent, false);
        return new SalesHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesHistoryViewHolder holder, int position) {
        Sales salesItem = salesList.get(position);
        if (null != salesItem) {
            holder.setSalesItem(salesItem);
        }
    }

    @Override
    public int getItemCount() {
        if (null != salesList) {
            return salesList.size();
        }
        return 0;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
        notifyDataSetChanged();
    }

    class SalesHistoryViewHolder extends RecyclerView.ViewHolder {
        private Sales salesItem;
        private TextView tvDate, tvName, tvPriceSingle, tvPriceTotal, tvQty;

        SalesHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvDate = itemView.findViewById(R.id.admin_sales_list_tv_item_date);
            this.tvName = itemView.findViewById(R.id.admin_sales_list_tv_item_name);
            this.tvPriceSingle = itemView.findViewById(R.id.admin_sales_list_tv_price_single);
            this.tvQty = itemView.findViewById(R.id.admin_sales_list_tv_item_qty);
            this.tvPriceTotal = itemView.findViewById(R.id.admin_sales_list_tv_price_total);
        }

        void setSalesItem(Sales mSalesItem) {
            this.salesItem = mSalesItem;
            tvDate.setText(TimeFormatter.toDate(salesItem.getTimestamp()));
            tvName.setText(salesItem.getName());
            int price = salesItem.getPrice();
            int qty = salesItem.getQuantity();
            int total = price * qty;
            tvPriceSingle.setText(String.valueOf(price));
            tvQty.setText(String.valueOf(qty));
            tvPriceTotal.setText(String.valueOf(total));
        }
    }
}
