package com.mywork.vipramilk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.adapter.SpinnerAdapter;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;
import com.mywork.vipramilk.viewmodel.RouteDataViewModel;

import java.util.List;

public class AddCustomerActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.mywork.vipramilk.REPLY";

    EditText editTextCustName, editTextSerialNo, editTextRouteSeq, editTextAddress, editTextConTactOne,
            editTextContactTwo, editTextEmail, editTextOneLtrQty, editTextHalfQty, editTextRate, editTextDeliveryCharges;
    CheckBox checkBoxContactOne, checkBoxContactTwo;//, checkBoxDaily, checkBoxEvenDay, checkBoxOddDay;
    Spinner spinnerRoute;
    Button buttonSubmit, buttonOneLtrIncrease, buttonOneLtrDecrease, buttonHalfLtrIncrease, getButtonHalfLtrDecrease;
    RadioGroup radioGroupDeliveryOn;
    RadioButton rbDaily, rbEven, rbOdd;

    private CustomerDataViewModel customerDataViewModel;
    private RouteDataViewModel routeDataViewModel;

    int oneltr = 0, halfLtr = 0;
    boolean isUpdateReq = false;
    CustomerData customerData;

    SpinnerAdapter adapter;

    List<RouteData> routeDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        BindUi();
        editTextCustName = (EditText) findViewById(R.id.edtTextCustomerName);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        Intent intent = getIntent();
        if (intent.hasExtra("customerdata")) {
            customerData = (CustomerData) intent.getSerializableExtra("customerdata");
           // UpdateView(customerData);
            isUpdateReq = true;
        }

        customerDataViewModel = new ViewModelProvider(this).get(CustomerDataViewModel.class);
        routeDataViewModel = new ViewModelProvider(this).get(RouteDataViewModel.class);

        routeDataViewModel.getAllRoutes().observe(this, new Observer<List<RouteData>>() {
            @Override
            public void onChanged(List<RouteData> routeData) {
                if(!isUpdateReq) {
                    RouteData routeData1 = new RouteData();
                    routeData1.setRouteName("Select Route");
                    routeData1.setRouteNumber(000);
                    routeData.add(0, routeData1);
                    adapter = new SpinnerAdapter(AddCustomerActivity.this,
                            android.R.layout.simple_spinner_item,
                            routeData);
                    spinnerRoute.setAdapter(adapter);
                }else {
                    routeDataList = routeData;
                    adapter = new SpinnerAdapter(AddCustomerActivity.this,
                            android.R.layout.simple_spinner_item,
                            routeData);
                    spinnerRoute.setAdapter(adapter);
                    UpdateView(customerData);
                }





            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isUpdateReq) {
                    CustomerData custData = GetUserInputsAndValidate();
                    custData.setCustomerId(customerData.getCustomerId());
                    customerDataViewModel.updateCustomerData(custData);
                } else {
                    customerDataViewModel.insertCustomerData(GetUserInputsAndValidate());
                }
                finish();
            }
        });
        buttonOneLtrIncrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpdateOneLtr(1);
            }
        });
        buttonOneLtrDecrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpdateOneLtr(0);
            }
        });
        buttonHalfLtrIncrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpdateHalfLtr(1);
            }
        });
        getButtonHalfLtrDecrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpdateHalfLtr(0);
            }
        });


    }

    private void UpdateView(CustomerData customerData) {
        editTextCustName.setText(customerData.getCustomerName());
        editTextAddress.setText(customerData.getAddress());
        editTextConTactOne.setText(customerData.getContactOne());
        editTextContactTwo.setText(customerData.getContactTwo());
        editTextEmail.setText(customerData.getContactEmail());

        editTextSerialNo.setText(String.valueOf(customerData.getcustomerSerialNo()));
        editTextRouteSeq.setText(String.valueOf(customerData.getRouteSequence()));
        editTextOneLtrQty.setText(String.valueOf(customerData.getQtyOneLtr()));
        editTextHalfQty.setText(String.valueOf(customerData.getQtyHalfLtr()));

        editTextRate.setText(String.valueOf(customerData.getRate()));
        editTextDeliveryCharges.setText(String.valueOf(customerData.getDeliveryCharges()));


        if (customerData.getContactOne().equals(customerData.getContactWhatsapp())) {
            checkBoxContactOne.setChecked(true);
            checkBoxContactTwo.setChecked(false);
        }
        if (customerData.getContactOne().equals(customerData.getContactWhatsapp())) {
            checkBoxContactOne.setChecked(false);
            checkBoxContactTwo.setChecked(true);
        }
        for(int i=0;i<routeDataList.size();i++){
            if(customerData.getRouteId()==routeDataList.get(i).getRouteId()){

                //int t=i+1;
                spinnerRoute.setSelection(i);
            }
        }


        switch (customerData.getDeliveryOn()) {

            case ("Daily"): {
                radioGroupDeliveryOn.clearCheck();

                rbDaily.setChecked(true);
                break;
            }
            case ("EvenDay"): {
                radioGroupDeliveryOn.clearCheck();
                rbEven.setChecked(true);
                break;
            }
            case ("OddDay"): {
                radioGroupDeliveryOn.clearCheck();
                rbOdd.setChecked(true);

                break;
            }
        }




        buttonSubmit.setText("Update");
    }

    private CustomerData GetUserInputsAndValidate() {
        CustomerData customerData = new CustomerData();
        customerData.setCustomerName(editTextCustName.getText().toString());

        customerData.setAddress(editTextAddress.getText().toString());
        customerData.setContactOne(editTextConTactOne.getText().toString());
        customerData.setContactTwo(editTextContactTwo.getText().toString());
        customerData.setContactEmail(editTextEmail.getText().toString());

        customerData.setcustomerSerialNo(Integer.valueOf(editTextSerialNo.getText().toString()));
        customerData.setRouteSequence(Integer.valueOf(editTextRouteSeq.getText().toString()));
        customerData.setQtyOneLtr(Integer.valueOf(editTextOneLtrQty.getText().toString()));
        customerData.setQtyHalfLtr(Integer.valueOf(editTextHalfQty.getText().toString()));

        customerData.setRate(Double.valueOf(editTextRate.getText().toString()));
        customerData.setDeliveryCharges(Double.valueOf(editTextDeliveryCharges.getText().toString()));

        String deliveryon = ((RadioButton) radioGroupDeliveryOn.findViewById(radioGroupDeliveryOn.getCheckedRadioButtonId())).getText().toString();

        customerData.setDeliveryOn(deliveryon);

        String whatsNo = "";
        if (checkBoxContactOne.isChecked()) {
            whatsNo = editTextConTactOne.getText().toString();
        }

        if (checkBoxContactTwo.isChecked()) {
            whatsNo = editTextContactTwo.getText().toString();
        }


        customerData.setContactWhatsapp(whatsNo);
        customerData.setActive(true);

        customerData.setRouteId((int)adapter.getItemIDByPos(spinnerRoute.getSelectedItemPosition()));
        return customerData;
    }

    private void BindUi() {
        editTextSerialNo = (EditText) findViewById(R.id.edtRouteNumber);
        editTextRouteSeq = (EditText) findViewById(R.id.edtRouteSequence);
        editTextCustName = (EditText) findViewById(R.id.edtTextCustomerName);
        editTextAddress = (EditText) findViewById(R.id.edtTextArea);
        editTextConTactOne = (EditText) findViewById(R.id.edtContactOne);
        editTextContactTwo = (EditText) findViewById(R.id.edtContactTwo);
        editTextEmail = (EditText) findViewById(R.id.edtEmailId);
        editTextOneLtrQty = (EditText) findViewById(R.id.editTextOneLiter);
        editTextHalfQty = (EditText) findViewById(R.id.editTextHalfLiter);
        editTextRate = (EditText) findViewById(R.id.edtRate);

        editTextDeliveryCharges = (EditText) findViewById(R.id.edtDeliveryCharges);

        buttonOneLtrIncrease = (Button) findViewById(R.id.buttonOneLiterIncrease);
        buttonOneLtrDecrease = (Button) findViewById(R.id.buttonOneLiterDecrease);
        buttonHalfLtrIncrease = (Button) findViewById(R.id.buttonHalfLiterIncrease);
        getButtonHalfLtrDecrease = (Button) findViewById(R.id.buttonHalfLiterDecrease);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        checkBoxContactOne = (CheckBox) findViewById(R.id.checkBoxContactOne);
        checkBoxContactTwo = (CheckBox) findViewById(R.id.checkBoxContactTwo);

        radioGroupDeliveryOn = (RadioGroup) findViewById(R.id.radioGroupDeliveryOn);

        spinnerRoute = (Spinner) findViewById(R.id.spinnerSelectRoute);

        rbDaily = (RadioButton) findViewById(R.id.radioButtonDaily);
        rbEven = (RadioButton) findViewById(R.id.radioButtonEvenDay);
        rbOdd = (RadioButton) findViewById(R.id.radioButtonOddDay);


    }

    private void UpdateOneLtr(int val) {
        if (val == 0) {
            if (oneltr > 0) {
                --oneltr;
                editTextOneLtrQty.setText(String.valueOf(oneltr));
            }
        }
        if (val == 1) {
            if (oneltr < 10) {
                ++oneltr;
                editTextOneLtrQty.setText(String.valueOf(oneltr));
            }
        }
    }

    private void UpdateHalfLtr(int val) {
        if (val == 0) {
            if (halfLtr > 0) {
                --halfLtr;
                editTextHalfQty.setText(String.valueOf(halfLtr));
            }
        }
        if (val == 1) {
            if (halfLtr < 10) {
                ++halfLtr;
                editTextHalfQty.setText(String.valueOf(halfLtr));
            }
        }
    }
}
