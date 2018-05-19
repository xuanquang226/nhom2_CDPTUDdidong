package com.example.quang.project_sdo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.ViewOrderAdapter;
import com.example.quang.project_sdo.Models.LOrderModel;
import com.example.quang.project_sdo.Models.OrderModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewOrderActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference root;
    ViewOrderAdapter adapter;
    ArrayList<LOrderModel> orderModels = new ArrayList<LOrderModel>();
    ActionBar actionBar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_order_layout);
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("LOrder");
        listView = (ListView) findViewById(R.id.lvViewOrder);
        actionBar = getSupportActionBar();
        actionBar.setTitle("View Order");
        actionBar.setDisplayHomeAsUpEnabled(true);
        loadData();
    }

    public void loadData() {
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                LOrderModel orderModel = dataSnapshot.getValue(LOrderModel.class);
                orderModels.add(orderModel);
                adapter = new ViewOrderAdapter(ViewOrderActivity.this, R.layout.listview_custom_vieworder, orderModels);
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
