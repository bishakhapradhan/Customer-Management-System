package com.example.bit6thsem.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bit6thsem.CustomerModel.CustomerData;
import com.example.bit6thsem.R;
import com.example.bit6thsem.adapters.CustomerViewAdapter;
import com.example.bit6thsem.content.BitSqliteHelper;

public class FragmentOne
        extends Fragment
        implements CustomerViewAdapter.CustomerAdapterInterface {
    public static final String TAG = "FragmentOne";

    RecyclerView recyclerView;
    BitSqliteHelper dbhelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvStudentList2);
        populateRecyclerView();

        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        Log.d(TAG, "onViewCreated: name value: " + name);

    }

    private void populateRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        CustomerViewAdapter adapter = new CustomerViewAdapter(getContext(),
                new BitSqliteHelper(getContext()).getALlCustomerRecords(),
                this);
        adapter.setData(dbhelper.getALlCustomerRecords());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void customerRecordUpdate(CustomerData customerData) {
        Log.d(TAG, "customerRecordUpdate: here at FragmentOne !!!");
    }

    @Override
    public void deleteCustomerRecord(int CustomerId) {

    }
}
