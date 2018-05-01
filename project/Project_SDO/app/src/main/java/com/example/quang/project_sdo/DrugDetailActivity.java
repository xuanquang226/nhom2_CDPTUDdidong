package com.example.quang.project_sdo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DrugDetailActivity extends AppCompatActivity {
    private TextView txtCongDung, txtNguonGoc, txtMota, txtTenShop,txtGia;
    private ImageView imgHinhThuoc;
    private Button btnChat, btnAdd;
    private ActionBar actionBar;
    String idShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_detail_layout);

        //Ini
        txtCongDung = (TextView) findViewById(R.id.txtCongDungD);
        txtNguonGoc = (TextView) findViewById(R.id.txtNguonGocD);
        txtMota = (TextView) findViewById(R.id.txtMoTaD);
        txtTenShop = (TextView) findViewById(R.id.txtTenShopD);
        txtGia = (TextView) findViewById(R.id.txtGia);

        imgHinhThuoc = (ImageView) findViewById(R.id.imgDrugD);

        btnChat = (Button) findViewById(R.id.btnChat);
        btnAdd = (Button) findViewById(R.id.btnOrder);

        //Process
        if(getIntent() != null && getIntent().getBundleExtra("data") != null){
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("data");
            txtCongDung.setText(bundle.getString("congdung"));
            txtNguonGoc.setText(bundle.getString("nguongoc"));
            txtMota.setText(bundle.getString("mota"));
            txtTenShop.setText(bundle.getString("tenshop"));
            txtGia.setText(bundle.getString("gia"));
            idShop = bundle.getString("idShop");
            Picasso.get().load(bundle.getString("hinhanh")).into(imgHinhThuoc);
        }

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(DrugDetailActivity.this,ChatDetailActivity.class);
                Bundle bundleA = new Bundle();
                bundleA.putString("idshop",idShop);
                newIntent.putExtra("Data",bundleA);
                startActivity(newIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
