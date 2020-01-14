package com.mywork.vipramilk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.CustomerData;

public class CustomerDetailsActivity extends AppCompatActivity {

    TextView textView;
    Button buttonAddHolidays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        textView=findViewById(R.id.textView);
        buttonAddHolidays=findViewById(R.id.buttonAddHolidays);

        Intent intent=getIntent();

        CustomerData customerData= (CustomerData) intent.getSerializableExtra("data");

        buttonAddHolidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CustomerDetailsActivity.this,AddHolidaysActivity.class);
                intent1.putExtra("custid",customerData.getCustomerId());
                startActivity(intent1);
            }
        });

        textView.setText(customerData.getCustomerName()+"\n"
                +customerData.getAddress()+"\n"
                +customerData.getContactOne()+"\n"
                +customerData.getContactTwo()+"\n"
                +customerData.getContactEmail()+"\n"
                +customerData.getContactWhatsapp()+"\n"
                +customerData.getRouteId()+"\n"
                +customerData.getDeliveryOn()+"\n");

    }
}
