package com.example.quang.project_sdo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.project_sdo.Adapters.PaymentAdapter;
import com.example.quang.project_sdo.Models.CarrierModel;
import com.example.quang.project_sdo.Models.LOrderModel;
import com.example.quang.project_sdo.Models.NameCarrierModel;
import com.example.quang.project_sdo.Models.OrderModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PaymentConfirmActivity extends AppCompatActivity {
    ArrayList<OrderModel> listPay = new ArrayList<OrderModel>();
    ArrayList<NameCarrierModel> nameCarriers = new ArrayList<NameCarrierModel>();
    PaymentAdapter paymentAdapter;
    OrderModel model;
    FirebaseAuth mAuth;
    DatabaseReference root, root2,root3;
    ListView listView;
    ActionBar actionBar;
    TextView price;
    Button payment;
    Spinner spinner;
    String name;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirm_layout);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Payment Confirmation");
        actionBar.setDisplayHomeAsUpEnabled(true);


        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Order").child(mAuth.getUid());
        root2 = FirebaseDatabase.getInstance().getReference();
        root3 = FirebaseDatabase.getInstance().getReference("LOrder");
        listView = (ListView) findViewById(R.id.lvConfirm);

        payment = (Button) findViewById(R.id.btnPayment);
        price = (TextView) findViewById(R.id.txtPriceDrugPA);
        spinner = (Spinner) findViewById(R.id.spinnerPayment);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name = nameCarriers.get(position).getNamecarrier();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loadData();
        getNameCarrier();


    }

    public void loadData() {
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                model = dataSnapshot.getValue(OrderModel.class);
                listPay.add(new OrderModel(model.ten, model.hinh, model.gia, model.soLuong, model.key, model.giagoc, model.tongtien, model.diachi));
                paymentAdapter = new PaymentAdapter(PaymentConfirmActivity.this, R.layout.listview_payment_custom, listPay);
                listView.setAdapter(paymentAdapter);
                price.setText(Integer.toString(model.tongtien));
                a = listPay.get(0).getTen();
                for (int i = 1; i < listPay.size(); i++){
                    if(!listPay.get(i).getTen().equalsIgnoreCase("")){
                        a += listPay.get(i).getTen();
                        Log.d("AAAA",a);
                    }
                }

                payment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        root3.push().setValue(new LOrderModel(a,model.tongtien,model.diachi,name,mAuth.getUid()));
                        root.setValue(null);
                    }
                });
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

    public void getNameCarrier() {
        root2.child("Carrier").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                NameCarrierModel nameCarrierModel = dataSnapshot.getValue(NameCarrierModel.class);
                nameCarriers.add(new NameCarrierModel(nameCarrierModel.namecarrier));
                Log.d("AAAA",nameCarrierModel.namecarrier);
                ArrayList<String> nameCarrier = new ArrayList<String>();
                for (int i =  0; i < nameCarriers.size(); i++){
                    nameCarrier.add(nameCarriers.get(i).namecarrier);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PaymentConfirmActivity.this, android.R.layout.simple_spinner_dropdown_item, nameCarrier);
                spinner.setAdapter(arrayAdapter);
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
        }
        return super.onOptionsItemSelected(item);
    }
}
