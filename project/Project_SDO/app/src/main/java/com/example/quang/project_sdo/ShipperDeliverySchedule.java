package com.example.quang.project_sdo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.ScheduleAdapter;
import com.example.quang.project_sdo.Models.ScheduleModel;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ShipperDeliverySchedule extends AppCompatActivity {
    ListView listView;
    ArrayList<ScheduleModel> listSchedule = new ArrayList<ScheduleModel>();
    ScheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipper_delivery_schedule);

        listView = (ListView) findViewById(R.id.lv_deliverySchedule);
        listSchedule.add(new ScheduleModel("product ABC","TDC","09091111515"));
        adapter = new ScheduleAdapter(ShipperDeliverySchedule.this, R.layout.listview_schedule_custom, listSchedule);
        listView.setAdapter(adapter);
    }
}
