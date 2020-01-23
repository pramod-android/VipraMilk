package com.mywork.vipramilk.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.adapter.AdminCustomerListAdapter;
import com.mywork.vipramilk.adapter.CustomerListAdapter;
import com.mywork.vipramilk.entity.Customer;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.HolidayData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;
import com.mywork.vipramilk.viewmodel.HolidayDataViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class CustomersListOfRouteActivity extends AppCompatActivity implements AdminCustomerListAdapter.ItemClickListener {

    private static final String TAG = "CustomersOfRoute";
    private CustomerDataViewModel customerDataViewModel;
    String oddEven;
    List<CustomerData> custDataList=new ArrayList<>();
    int routeID;
    AdminCustomerListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_list_of_route);

        Calendar calendar = Calendar.getInstance();
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        int mothOfYear=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        if ( dayOfMonth % 2 == 0 )
            oddEven="EvenDay";
        else
            oddEven="OddDay";


        Intent intent = getIntent();

        if (intent.hasExtra("routeData")) {
            RouteData routeData = (RouteData) intent.getSerializableExtra("routeData");
            routeID = routeData.getRouteId();
            //custID = intent.getIntExtra("custid",0);
        } else {
            routeID = intent.getIntExtra("routeId", 0);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new AdminCustomerListAdapter(this);
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

                        new myAsyncTask(customerDataViewModel,cd,"day"+dayOfMonth).execute(cd.getCustomerId(),mothOfYear,year);
//                        if(customerDataViewModel.checIsHolidays("day"+dayOfMonth,custID,mothOfYear,year)==0) {
//                            custDataList.add(cd);
//                        }
                    }else if(cd.getDeliveryOn().equals(oddEven)){
                        new myAsyncTask(customerDataViewModel,cd,"day"+dayOfMonth).execute(cd.getcustomerId(),mothOfYear,year);
                       // (String day,int custId,int month,int year)
//                        if(customerDataViewModel.checIsHolidays("day"+dayOfMonth,custID,mothOfYear,year)==0) {
//                            custDataList.add(cd);
//                        }
                    }
                }
            }
        });
    }

    private class myAsyncTask extends AsyncTask<Integer, Void, HolidayData> {


        CustomerDataViewModel viewModel;
        CustomerData customerData;
        String day;

        public myAsyncTask(CustomerDataViewModel model, CustomerData hData,String daystr) {
            viewModel = model;
            customerData = hData;
            day=daystr;

        }

        @Override
        protected HolidayData doInBackground(Integer... params) {
            return viewModel.checkIsHolidays(day,params[0],params[1],params[2]);

        }

        @Override
        protected void onPostExecute(HolidayData holidayData) {

            Gson gson=new Gson();
            String jsonString = gson.toJson(holidayData);
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    if(keys.next().equals(day)){

                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



//            if (isholiday.getDay16() == 0) {
//                custDataList.add(customerData);
//            }
            adapter.setCustomers(custDataList);

        }
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
