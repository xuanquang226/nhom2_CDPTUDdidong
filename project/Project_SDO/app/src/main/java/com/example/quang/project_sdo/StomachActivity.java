package com.example.quang.project_sdo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.CoughAdapter;
import com.example.quang.project_sdo.Adapters.StomachAdapter;
import com.example.quang.project_sdo.Models.CoughModel;
import com.example.quang.project_sdo.Models.ListDrugModel;
import com.example.quang.project_sdo.Models.StomachModel;

import java.util.ArrayList;

public class StomachActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<StomachModel> listStomachDrug = new ArrayList<StomachModel>();
    StomachAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stomach_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //ListView
        listView = (ListView) findViewById(R.id.stomachdrug_listview);
        listStomachDrug.clear();
        listStomachDrug = new ArrayList<StomachModel>();
        listStomachDrug.add(new StomachModel("Thuốc Cefixim","ABC Store","120.000",R.drawable.img_cefixim));
        listStomachDrug.add(new StomachModel("thuốc Giảm Đau ","777 Store","150.000",R.drawable.img_giamdau));
        listStomachDrug.add(new StomachModel("thuốc An Thần","666 Store","180.000",R.drawable.img_anthan));
        listStomachDrug.add(new StomachModel("thuốc Kháng Viêm","5555 Store","220.000",R.drawable.img_khangviem));
        adapter = new StomachAdapter(this,R.layout.listview_drug_custom,listStomachDrug);
        listView.setAdapter(adapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
