package com.mywork.vipramilk.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mywork.vipramilk.adapter.CustomerListAdapter;
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity implements CustomerListAdapter.ItemClickListener {
    private static final String TAG = "CustomerListActivity";
    private CustomerDataViewModel customerDataViewModel;

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
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemClick(View view, CustomerData customerData) {
        Toast.makeText(this, "Item click", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,CustomerDetailsActivity.class);
        intent.putExtra("data",customerData);
        startActivity(intent);
    }

    @Override
    public void onItemMessageClick(View view, CustomerData customerData) {
        openWhatsApp(customerData.getContactWhatsapp());
    }

    @Override
    public void onItemEditClick(View view, CustomerData customerData) {
        Intent intent = new Intent(CustomerListActivity.this, AddCustomerActivity.class);
        intent.putExtra("customerdata",customerData);
        startActivity(intent);
    }

    @Override
    public void onItemDeleteClick(View view, CustomerData customerData) {
        customerDataViewModel.moveToDeleted(false,customerData.getcustomerId());
    }

    private void openWhatsApp(String number) {
        try {
            number = number.replace(" ", "").replace("+", "");

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number)+"@s.whatsapp.net");
            startActivity(sendIntent);

        } catch(Exception e) {
            Log.e(TAG, "ERROR_OPEN_MESSANGER"+e.toString());
        }
    }
}
