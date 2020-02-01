package com.jgt.pos.ui.admin.saleshistory;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jgt.pos.R;
import com.jgt.pos.database.sales.Sales;
import com.jgt.pos.utils.TimeFormatter;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SalesHistoryFragment extends Fragment {
    private static final long INVALID_TIMESTAMP = -1;
    private EditText etMonthFrom, etMonthTo,
            etDayFrom, etDayTo,
            etYearFrom, etYearTo;
    private Button btnExport, btnSearch;
    private RecyclerView rvListView;
    private LinearLayoutManager layoutManager;
    private SalesHistoryAdapter adapter;
    private View rootView;
    private SalesHistoryViewModel salesHistoryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_fragment_sales_history, container, false);
        initViews();
        initViewModel();
        initRecyclerView();
        return rootView;
    }

    private void initViews() {
        this.etMonthFrom = rootView.findViewById(R.id.admin_sales_list_et_mm_from);
        this.etMonthTo = rootView.findViewById(R.id.admin_sales_list_et_mm_to);
        this.etDayFrom = rootView.findViewById(R.id.admin_sales_list_et_dd_from);
        this.etDayTo = rootView.findViewById(R.id.admin_sales_list_et_dd_to);
        this.etYearFrom = rootView.findViewById(R.id.admin_sales_list_et_yyyy_from);
        this.etYearTo = rootView.findViewById(R.id.admin_sales_list_et_yyyy_to);
        this.btnExport = rootView.findViewById(R.id.admin_sales_btn_export);
        this.btnSearch = rootView.findViewById(R.id.admin_sales_list_btn_search);
        this.btnExport.setOnClickListener(this::onExportClicked);
        this.btnSearch.setOnClickListener(this::onSearchClicked);
    }

    private void initRecyclerView() {
        Activity activity = getActivity();
        rvListView = rootView.findViewById(R.id.admin_sales_history_fragment_rv_sales_list);
        adapter = new SalesHistoryAdapter();
        layoutManager = new LinearLayoutManager(activity);
        rvListView.setHasFixedSize(true);
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
    }

    private void initViewModel() {
        salesHistoryViewModel = ViewModelProviders.of(this).get(SalesHistoryViewModel.class);
        salesHistoryViewModel.init();
        salesHistoryViewModel.getSalesList().observe(this, this::onSalesListChanged);
    }

    private void onSalesListChanged(List<Sales> sales) {
        adapter.setSalesList(sales);
    }

    private void onSearchClicked(View view) {
        String monthFrom = etMonthFrom.getText().toString();
        String monthTo = etMonthTo.getText().toString();
        String dayFrom = etDayFrom.getText().toString();
        String dayTo = etDayTo.getText().toString();
        String yearFrom = etYearFrom.getText().toString();
        String yearTo = etYearTo.getText().toString();

        long timestampFrom = parseDateToTimestamp(monthFrom, dayFrom, yearFrom);
        long timestampTo = parseDateToTimestamp(monthTo, dayTo, yearTo);

        if (INVALID_TIMESTAMP != timestampFrom
                && INVALID_TIMESTAMP != timestampTo
                && timestampTo >= timestampFrom) {
            salesHistoryViewModel.searchSales(timestampFrom, timestampTo).observe(this, this::onSalesListChanged);
        } else {
            Toast.makeText(getActivity(), "Please enter valid date range", Toast.LENGTH_SHORT).show();
        }
    }

    private void onExportClicked(View view) {
    }

    private long parseDateToTimestamp(String month, String day, String year) {
        if (!TextUtils.isEmpty(month)
                && !TextUtils.isEmpty(day)
                && !TextUtils.isEmpty(year)) {
            String date = year + "-" + month + "-" + day;
            try {
                return TimeFormatter.toTimestamp(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return INVALID_TIMESTAMP;
            }
        } else {
            return INVALID_TIMESTAMP;
        }
    }
}
