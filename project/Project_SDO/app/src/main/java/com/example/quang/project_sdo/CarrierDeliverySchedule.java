package com.example.quang.project_sdo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.CarrierDeliveryScheduleAdapter;
import com.example.quang.project_sdo.Models.CarrierDeliveryScheduleModel;

import java.util.ArrayList;

public class CarrierDeliverySchedule extends AppCompatActivity {
    ListView listView;
    ArrayList<CarrierDeliveryScheduleModel> listCarrierSchedule = new ArrayList<>();
    CarrierDeliveryScheduleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier_delivery_schedule);

        listView = (ListView) findViewById(R.id.lv_deliverySchedule);
        listCarrierSchedule.add(new CarrierDeliveryScheduleModel("Đồng Nai","panacitamol"));
        listCarrierSchedule.add(new CarrierDeliveryScheduleModel("TP HCM","panadol"));
        listCarrierSchedule.add(new CarrierDeliveryScheduleModel("Hà Nội","redbull"));
        adapter = new CarrierDeliveryScheduleAdapter(CarrierDeliverySchedule.this, R.layout.carrier_deliveryschedule_custom, listCarrierSchedule);
        listView.setAdapter(adapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
