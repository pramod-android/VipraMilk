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
import com.mywork.vipramilk.adapter.MilkmanListAdapter;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.MilkmanData;
import com.mywork.vipramilk.viewmodel.CustomerDataViewModel;
import com.mywork.vipramilk.viewmodel.MilkManDataViewModel;

import java.util.List;

public class MIlkmanListActivity extends AppCompatActivity implements MilkmanListAdapter.ItemClickListener {
    private static final String TAG = "MIlkmanListActivity";
    private MilkManDataViewModel milkManDataViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milkman_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MilkmanListAdapter adapter = new MilkmanListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        milkManDataViewModel = new ViewModelProvider(this).get(MilkManDataViewModel.class);

        milkManDataViewModel.getAllMilkmans().observe(this, new Observer<List<MilkmanData>>() {
            @Override
            public void onChanged(@Nullable final List<MilkmanData> milkmanDataList) {
                // Update the cached copy of the words in the adapter.
                adapter.setCustomers(milkmanDataList);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MIlkmanListActivity.this, AddMilkmanActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(View view, MilkmanData milkmanData) {
        Toast.makeText(this, "Item click", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemMessageClick(View view, MilkmanData milkmanData) {
        openWhatsApp(milkmanData.getContactWhatsapp());

    }

    @Override
    public void onItemEditClick(View view, MilkmanData milkmanData) {
        Intent intent = new Intent(MIlkmanListActivity.this, AddMilkmanActivity.class);
        intent.putExtra("milkmandata",milkmanData);
        startActivity(intent);
    }

    @Override
    public void onItemDeleteClick(View view, MilkmanData milkmanData) {
        milkManDataViewModel.deleteMilkMan(milkmanData.getMilkmanId());

    }

    private void openWhatsApp(String number) {
        try {
            number = number.replace(" ", "").replace("+", "");

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number)+"@s.whatsapp.net");
            startActivity(sendIntent);

        } catch(Exception e) {
            Log.e(TAG, "ERROR_OPEN_MESSANGER"+e.toString());
        }
    }
}
