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
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.adapter.CustomerListAdapter;

import com.mywork.vipramilk.adapter.RouteListAdapter;

import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;

import com.mywork.vipramilk.viewmodel.RouteDataViewModel;

import java.util.List;

public class RouteListActivity extends AppCompatActivity implements RouteListAdapter.ItemClickListener {
    private static final String TAG = "RouteListActivity";
    private RouteDataViewModel routeDataViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RouteListAdapter adapter = new RouteListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        routeDataViewModel = new ViewModelProvider(this).get(RouteDataViewModel.class);

        routeDataViewModel.getAllRoutes().observe(this, new Observer<List<RouteData>>() {
            @Override
            public void onChanged(@Nullable final List<RouteData> routeDataList) {
                // Update the cached copy of the words in the adapter.
                adapter.setRoutes(routeDataList);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RouteListActivity.this, AddRouteActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            CustomerData customerData =new CustomerData(data.getSerializableExtra(AddCustomerActivity.EXTRA_REPLY));
//            routeDataViewModel.insertCustomerData(customerData);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "empty not saved",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(View view, RouteData routeData) {
        Toast.makeText(this, "Item click", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemEditClick(View view, RouteData routeData) {
        Intent intent = new Intent(RouteListActivity.this, AddRouteActivity.class);
        intent.putExtra("routeData",routeData);
        startActivity(intent);
    }

}
