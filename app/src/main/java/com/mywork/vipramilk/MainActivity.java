package com.mywork.vipramilk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mywork.vipramilk.activity.AddHolidaysActivity;
import com.mywork.vipramilk.activity.CustomerListActivity;
import com.mywork.vipramilk.activity.MIlkmanListActivity;
import com.mywork.vipramilk.activity.MilkmansProductDeliveryActivity;
import com.mywork.vipramilk.activity.RouteCustListActivity;
import com.mywork.vipramilk.activity.RouteListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnCustomerClick(View view) {
        Intent intent=new Intent(MainActivity.this, CustomerListActivity.class);
        startActivity(intent);
    }

    public void OnRoutesClick(View view) {
        Intent intent=new Intent(MainActivity.this, RouteListActivity.class);
        startActivity(intent);
    }

    public void OnMilkMansClick(View view) {
        Intent intent=new Intent(MainActivity.this, MIlkmanListActivity.class);
        startActivity(intent);
    }

    public void OnAdminViewClick(View view) {
        Intent intent=new Intent(MainActivity.this, RouteCustListActivity.class);
        startActivity(intent);
    }

    public void OnProductDeliveryClick(View view) {
        Intent intent=new Intent(MainActivity.this, MilkmansProductDeliveryActivity.class);
        startActivity(intent);
    }

    public void OnHolidaySClick(View view) {
        Toast.makeText(this, "Click on customer in Customer tab", Toast.LENGTH_SHORT).show();

//        Intent intent=new Intent(MainActivity.this, AddHolidaysActivity.class);
//        startActivity(intent);
    }
}
