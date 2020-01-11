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
import com.mywork.vipramilk.R;
import com.mywork.vipramilk.adapter.AdminRouteListAdapter;
import com.mywork.vipramilk.adapter.RouteListAdapter;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.viewmodel.RouteDataViewModel;

import java.util.List;

public class RouteCustListActivity extends AppCompatActivity implements AdminRouteListAdapter.ItemClickListener {
        private static final String TAG = "RouteCustListActivity";
        private RouteDataViewModel routeDataViewModel;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_route_cust_list);
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final AdminRouteListAdapter adapter = new AdminRouteListAdapter(this);
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
        }

        @Override
        public void onItemClick(View view, RouteData routeData) {
            android.content.Intent intent = new Intent(RouteCustListActivity.this, CustomersListOfRouteActivity.class);
            intent.putExtra("routeData",routeData);
            startActivity(intent);
        }


        @Override
        public void onItemEditClick(View view, RouteData routeData) {
            android.content.Intent intent = new Intent(RouteCustListActivity.this, AddRouteActivity.class);
            intent.putExtra("routeData",routeData);
            startActivity(intent);
        }

    }

