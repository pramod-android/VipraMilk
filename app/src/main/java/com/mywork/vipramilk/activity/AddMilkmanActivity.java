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
import com.mywork.vipramilk.entity.Milkman;
import com.mywork.vipramilk.entity.MilkmanData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;
import com.mywork.vipramilk.viewmodel.MilkManDataViewModel;
import com.mywork.vipramilk.viewmodel.RouteDataViewModel;

import java.util.List;

public class AddMilkmanActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.mywork.vipramilk.REPLY";

    EditText editTextMilkManName, editTextConTactOne,
            editTextContactTwo, editTextEmail;
    CheckBox checkBoxContactOne, checkBoxContactTwo;
    Spinner spinnerRoute;
    Button buttonSubmit;

    private MilkManDataViewModel milkManDataViewModel;
    private RouteDataViewModel routeDataViewModel;

    boolean isUpdateReq = false;
    MilkmanData milkmanData;

    SpinnerAdapter adapter;

    List<RouteData> routeDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_milkman);
        BindUi();
        buttonSubmit = findViewById(R.id.buttonSubmit);

        Intent intent = getIntent();
        if (intent.hasExtra("milkmandata")) {
            milkmanData = (MilkmanData) intent.getSerializableExtra("milkmandata");
            // UpdateView(customerData);
            isUpdateReq = true;
        }

        milkManDataViewModel = new ViewModelProvider(this).get(MilkManDataViewModel.class);
        routeDataViewModel = new ViewModelProvider(this).get(RouteDataViewModel.class);

        routeDataViewModel.getAllRoutes().observe(this, new Observer<List<RouteData>>() {
            @Override
            public void onChanged(List<RouteData> routeData) {
                if (!isUpdateReq) {
                    RouteData routeData1 = new RouteData();
                    routeData1.setRouteName("Select Route");
                    routeData1.setRouteNumber(000);
                    routeData.add(0, routeData1);
                    adapter = new SpinnerAdapter(AddMilkmanActivity.this,
                            android.R.layout.simple_spinner_item,
                            routeData);
                    spinnerRoute.setAdapter(adapter);
                } else {
                    routeDataList = routeData;
                    adapter = new SpinnerAdapter(AddMilkmanActivity.this,
                            android.R.layout.simple_spinner_item,
                            routeData);
                    spinnerRoute.setAdapter(adapter);
                    UpdateView(milkmanData);
                }


            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isUpdateReq) {
                    MilkmanData milkmanData = GetUserInputsAndValidate();
                    milkmanData.setMilkmanId(milkmanData.getMilkmanId());
                    milkManDataViewModel.updateMilkmanData(milkmanData);
                } else {
                    milkManDataViewModel.insertMilkmanData(GetUserInputsAndValidate());
                }
                finish();
            }
        });
    }

    private void UpdateView(MilkmanData milkmanData) {
        editTextMilkManName.setText(milkmanData.getMilkmanName());
        editTextConTactOne.setText(milkmanData.getContactOne());
        editTextContactTwo.setText(milkmanData.getContactTwo());
        editTextEmail.setText(milkmanData.getContactEmail());


        if (milkmanData.getContactOne().equals(milkmanData.getContactWhatsapp())) {
            checkBoxContactOne.setChecked(true);
            checkBoxContactTwo.setChecked(false);
        }
        if (milkmanData.getContactOne().equals(milkmanData.getContactWhatsapp())) {
            checkBoxContactOne.setChecked(false);
            checkBoxContactTwo.setChecked(true);
        }
        for (int i = 0; i < routeDataList.size(); i++) {
            if (milkmanData.getRouteId() == routeDataList.get(i).getRouteId()) {

                //int t=i+1;
                spinnerRoute.setSelection(i);
            }
        }

        buttonSubmit.setText("Update");
    }

    private MilkmanData GetUserInputsAndValidate() {
        MilkmanData milknamData = new MilkmanData();
        milknamData.setMilkmanName(editTextMilkManName.getText().toString());
        milknamData.setContactOne(editTextConTactOne.getText().toString());
        milknamData.setContactTwo(editTextContactTwo.getText().toString());
        milknamData.setContactEmail(editTextEmail.getText().toString());

        String whatsNo = "";
        if (checkBoxContactOne.isChecked()) {
            whatsNo = editTextConTactOne.getText().toString();
        }
        if (checkBoxContactTwo.isChecked()) {
            whatsNo = editTextContactTwo.getText().toString();
        }

        milknamData.setContactWhatsapp(whatsNo);
        milknamData.setRouteId((int) adapter.getItemIDByPos(spinnerRoute.getSelectedItemPosition()));
        return milknamData;
    }

    private void BindUi() {
        editTextMilkManName = (EditText) findViewById(R.id.edtTextMilkmanName);
        editTextConTactOne = (EditText) findViewById(R.id.edtContactOne);
        editTextContactTwo = (EditText) findViewById(R.id.edtContactTwo);
        editTextEmail = (EditText) findViewById(R.id.edtEmailId);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        checkBoxContactOne = (CheckBox) findViewById(R.id.checkBoxContactOne);
        checkBoxContactTwo = (CheckBox) findViewById(R.id.checkBoxContactTwo);

        spinnerRoute = (Spinner) findViewById(R.id.spinnerSelectRoute);


    }


}
