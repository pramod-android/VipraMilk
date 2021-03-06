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

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.adapter.AdminCustomerListAdapter;
import com.mywork.vipramilk.adapter.MilkmanCustomerListAdapter;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.SaleData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;
import com.mywork.vipramilk.viewmodel.MilkManDataViewModel;
import com.mywork.vipramilk.viewmodel.SaleDataViewModel;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MilkmansProductDeliveryActivity extends AppCompatActivity implements MilkmanCustomerListAdapter.ItemClickListener{
    private static final String TAG = "DeliveryActivity";
    private CustomerDataViewModel customerDataViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milkmans_product_delivery);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MilkmanCustomerListAdapter adapter = new MilkmanCustomerListAdapter(this);
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
    }


    @Override
    public void onItemClick(View view, CustomerData customerData) {
        Toast.makeText(this, "Item click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemMessageClick(View view, CustomerData customerData) {
//        openWhatsApp(customerData.getContactWhatsapp());
    }

    @Override
    public void onItemEditClick(View view, CustomerData customerData) {

//        Intent intent = new Intent(CustomersListOfRouteActivity.this, AddCustomerActivity.class);
//        intent.putExtra("customerdata",customerData);
//        startActivity(intent);

    }

    @Override
    public void onItemDeliveredClick(View view, CustomerData customerData) {
        SaleDataViewModel saleDataViewModel=new ViewModelProvider(this).get(SaleDataViewModel.class);

        SaleData saleData=new SaleData();
        saleData.setCustomerId(customerData.getCustomerId());
        saleData.setHalfLiter(customerData.getQtyHalfLtr());
        saleData.setOneLiter(customerData.getQtyOneLtr());

        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();
            int day = now.getDayOfMonth();
            saleData.setYear(year);
            saleData.setMonth(month);
            saleData.setDay(day);
        }

        saleDataViewModel.insertSaleData(saleData);


        //customerDataViewModel.moveToDeleted(false,customerData.getcustomerId());
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
