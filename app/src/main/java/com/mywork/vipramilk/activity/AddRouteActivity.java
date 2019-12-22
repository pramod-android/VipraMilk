package com.mywork.vipramilk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;
import com.mywork.vipramilk.viewmodel.RouteDataViewModel;

public class AddRouteActivity extends AppCompatActivity {

    EditText editTextRouteNo,editTextRouteName,editTextRouteArea;
    Button buttonSubmit;
    RouteDataViewModel routeDataViewModel;
    boolean isUpdateReq = false;
    RouteData routeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        Intent intent = getIntent();
        if (intent.hasExtra("routedata")) {
            routeData = (RouteData) intent.getSerializableExtra("routedata");
            UpdateView(routeData);
            isUpdateReq = true;
        }

        BindUi();

        routeDataViewModel=new ViewModelProvider(this).get(RouteDataViewModel.class);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdateReq) {
                    RouteData rtData=GetUserInputsAndValidate();
                    rtData.setRouteId(routeData.getRouteId());
                    routeDataViewModel.updateRouteData(routeData);
                }else {
                    routeDataViewModel.insertRouteData(GetUserInputsAndValidate());
                }
                finish();
            }
        });


    }
    private void BindUi() {
        editTextRouteNo = (EditText) findViewById(R.id.edtRouteNumber);
        editTextRouteName = (EditText) findViewById(R.id.edtTextRouteName);
        editTextRouteArea = (EditText) findViewById(R.id.edtTextArea);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
    }
    private RouteData GetUserInputsAndValidate() {
        RouteData routeData = new RouteData();
        routeData.setRouteNumber(Integer.parseInt(editTextRouteNo.getText().toString()));
        routeData.setRouteName(editTextRouteName.getText().toString());
        routeData.setRouteArea(editTextRouteArea.getText().toString());

        return routeData;
    }
    private void UpdateView(RouteData routeData) {
        editTextRouteNo.setText(routeData.getRouteNumber());
        editTextRouteName.setText(routeData.getRouteName());
        editTextRouteArea.setText(routeData.getRouteArea());

        buttonSubmit.setText("Update");
    }

}
