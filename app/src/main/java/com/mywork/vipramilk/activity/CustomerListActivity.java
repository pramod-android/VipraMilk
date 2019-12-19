package com.mywork.vipramilk.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mywork.vipramilk.CustomerListAdapter;
import com.mywork.vipramilk.MainActivity;
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity implements CustomerListAdapter.ItemClickListener {
    private CustomerDataViewModel customerDataViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CustomerListAdapter adapter = new CustomerListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        customerDataViewModel = new ViewModelProvider(this).get(CustomerDataViewModel.class);

        customerDataViewModel.getAllCustomers().observe(this, new Observer<List<CustomerData>>() {
            @Override
            public void onChanged(@Nullable final List<CustomerData> customerDataList) {
                // Update the cached copy of the words in the adapter.
                adapter.setCustomers(customerDataList);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerListActivity.this, AddCustomerActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            CustomerData customerData =new CustomerData(data.getSerializableExtra(AddCustomerActivity.EXTRA_REPLY));
//            customerDataViewModel.insertCustomerData(customerData);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "empty not saved",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(View view, CustomerData customerData) {
        Toast.makeText(this, "Item click", Toast.LENGTH_SHORT).show();
    }
}
