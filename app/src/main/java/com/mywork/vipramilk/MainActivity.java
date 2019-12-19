package com.mywork.vipramilk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mywork.vipramilk.activity.CustomerListActivity;

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
}
