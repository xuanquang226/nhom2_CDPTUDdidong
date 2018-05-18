package com.example.quang.project_sdo;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.project_sdo.Adapters.HomeListDrugAdapter;
import com.example.quang.project_sdo.Adapters.SaleStatisticAdapter;
import com.example.quang.project_sdo.Adapters.ShoppingCartAdapter;
import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.example.quang.project_sdo.Models.ListDrugForHomeModel;
import com.example.quang.project_sdo.Models.OrderModel;
import com.example.quang.project_sdo.Models.SaleStatisticModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;

public class SaleStatisticActivity extends AppCompatActivity {
    ActionBar actionBar;
    ListView listView;
    ArrayList<EnterDrugModel> listSale = new ArrayList<EnterDrugModel>();
    SaleStatisticAdapter adapter;
    TextView txtSoLuong, txtGia, txtTen, txtDate,txtTotalPrice;
    ImageView imgDrug;
    DatabaseReference root;
    FirebaseAuth mAuth;
    int i, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale_statistic_layout);
        actionBar = getSupportActionBar();
        actionBar.setTitle("View Statistic");
        actionBar.setDisplayHomeAsUpEnabled(true);
        txtTen = (TextView) findViewById(R.id.txtNameDrugSale);
        txtSoLuong = (TextView) findViewById(R.id.txtAmount);
        txtGia = (TextView) findViewById(R.id.txtGiaSale);
        txtDate = (TextView) findViewById(R.id.txtpostDate);
        txtTen = (TextView) findViewById(R.id.txtNameDrugSale);
        imgDrug = (ImageView) findViewById(R.id.imgDrug);
        txtTotalPrice = (TextView) findViewById(R.id.totalPrice);
        //Ini firebase
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Drug");


        //ListView
        listView = (ListView) findViewById(R.id.lsvsaleSta);

        loadData();
    }

    public void loadData() {
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final EnterDrugModel data = dataSnapshot.getValue(EnterDrugModel.class);
                if(mAuth.getUid().equalsIgnoreCase(data.idShop)) {
                    listSale.add(new EnterDrugModel(data.tenthuoc, data.congdung, data.gia, data.nguongoc, data.mota, data.soluong, data.linkhinh, data.tenshop, data.id, data.idShop));
                    adapter = new SaleStatisticAdapter(SaleStatisticActivity.this, R.layout.listview_salestatistic_custom, listSale);
                    Log.d("aaaa", data.getTenthuoc());

                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    //set total price textview
                    for (i = 0; i < listSale.size(); i++) {
                        if (!listSale.get(i).isChecked()){
                            total += Integer.parseInt(listSale.get(i).getGia());
                            Log.d("AAAA",total +"");
                            txtTotalPrice.setText(total+" Đ ");
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(SaleStatisticActivity.this,"AAA",Toast.LENGTH_SHORT);
                        }

                    }
                }
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
