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
import com.mywork.vipramilk.adapter.CustomerListAdapter;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomersListOfRouteActivity extends AppCompatActivity implements AdminCustomerListAdapter.ItemClickListener {

    private static final String TAG = "CustomersOfRoute";
    private CustomerDataViewModel customerDataViewModel;
    String oddEven;
    List<CustomerData> custDataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_list_of_route);

        Calendar calendar = Calendar.getInstance();
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        int mothOfYear=calendar.get(Calendar.MONTH);
        if ( dayOfMonth % 2 == 0 )
            oddEven="EvenDay";
        else
            oddEven="OddDay";


        Intent intent = getIntent();

        int routeID;
        if (intent.hasExtra("routeData")) {
            RouteData routeData = (RouteData) intent.getSerializableExtra("routeData");
            routeID = routeData.getRouteId();
        } else {
            routeID = intent.getIntExtra("routeId", 0);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AdminCustomerListAdapter adapter = new AdminCustomerListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        customerDataViewModel = new ViewModelProvider(this).get(CustomerDataViewModel.class);

        customerDataViewModel.getRouteCustomers(routeID).observe(this, new Observer<List<CustomerData>>() {
            @Override
            public void onChanged(@Nullable final List<CustomerData> customerDataList) {
                // Update the cached copy of the words in the adapter.

                for(CustomerData cd: customerDataList){
                    if(cd.getDeliveryOn().equals("Daily")){
                        custDataList.add(cd);
                    }else if(cd.getDeliveryOn().equals(oddEven)){
                        custDataList.add(cd);
                    }
                }
                adapter.setCustomers(custDataList);
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
    public void onItemDeleteClick(View view, CustomerData customerData) {
        //customerDataViewModel.moveToDeleted(false,customerData.getcustomerId());
    }

    private void openWhatsApp(String number) {
        try {
            number = number.replace(" ", "").replace("+", "");

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number) + "@s.whatsapp.net");
            startActivity(sendIntent);

        } catch (Exception e) {
            Log.e(TAG, "ERROR_OPEN_MESSANGER" + e.toString());
        }
    }
}
