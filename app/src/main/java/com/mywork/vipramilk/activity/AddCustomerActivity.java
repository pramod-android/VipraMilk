package com.mywork.vipramilk.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;

import java.util.List;

public class AddCustomerActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.mywork.vipramilk.REPLY";

    EditText editTextCustName,editTextSerialNo,editTextRouteSeq,editTextAddress,editTextConTactOne,
    editTextContactTwo,editTextEmail,editTextOneLtrQty,editTextHalfQty,editTextRate,editTextDeliveryCharges;
    CheckBox checkBoxContactOne,checkBoxContactTwo,checkBoxDaily,checkBoxEvenDay,checkBoxOddDay;
    Spinner spinnerRoute;
    Button buttonSubmit;
    private CustomerDataViewModel customerDataViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        BindUi();
        editTextCustName = (EditText) findViewById(R.id.edtTextCustName);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        customerDataViewModel = new ViewModelProvider(this).get(CustomerDataViewModel.class);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String custName = editTextCustName.getText().toString();

                CustomerData customerData = new CustomerData();
                customerData.setcustomerName(custName);

                customerDataViewModel.insertCustomerData(customerData);
                finish();
            }
        });

    }

    private void BindUi() {
        editTextSerialNo = (EditText) findViewById(R.id.edtSerialNumber);
        editTextRouteSeq = (EditText) findViewById(R.id.edtRouteSequence);
        editTextCustName = (EditText) findViewById(R.id.edtTextCustName);
        editTextAddress = (EditText) findViewById(R.id.edtTextAddress);
        editTextConTactOne = (EditText) findViewById(R.id.edtContactOne);
        editTextContactTwo = (EditText) findViewById(R.id.edtContactTwo);
        editTextEmail = (EditText) findViewById(R.id.edtEmailId);
        editTextCustName = (EditText) findViewById(R.id.edtTextCustName);
        editTextCustName = (EditText) findViewById(R.id.edtTextCustName);
        editTextCustName = (EditText) findViewById(R.id.edtTextCustName);


        buttonSubmit = findViewById(R.id.buttonSubmit);

    }
}
