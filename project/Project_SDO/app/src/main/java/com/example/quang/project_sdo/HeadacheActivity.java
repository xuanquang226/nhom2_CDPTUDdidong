package com.example.quang.project_sdo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.CoughAdapter;
import com.example.quang.project_sdo.Adapters.HeadacheAdapter;
import com.example.quang.project_sdo.Models.CoughModel;
import com.example.quang.project_sdo.Models.HeadacheModel;

import java.util.ArrayList;

public class HeadacheActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<HeadacheModel> listHeadacheDrug = new ArrayList<HeadacheModel>();
    HeadacheAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headache_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //ListView
        listView = (ListView) findViewById(R.id.headachedrug_listview);
        listHeadacheDrug.clear();
        listHeadacheDrug = new ArrayList<HeadacheModel>();
        listHeadacheDrug.add(new HeadacheModel("Thuốc Cefixim","ABC Store","120.000",R.drawable.img_cefixim));
        listHeadacheDrug.add(new HeadacheModel("thuốc Giảm Đau ","777 Store","150.000",R.drawable.img_giamdau));
        listHeadacheDrug.add(new HeadacheModel("thuốc An Thần","666 Store","180.000",R.drawable.img_anthan));
        listHeadacheDrug.add(new HeadacheModel("thuốc Kháng Viêm","5555 Store","220.000",R.drawable.img_khangviem));
        adapter = new HeadacheAdapter(this,R.layout.listview_drug_custom,listHeadacheDrug);
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
