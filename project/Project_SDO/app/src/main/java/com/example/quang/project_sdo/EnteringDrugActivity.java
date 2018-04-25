package com.example.quang.project_sdo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnteringDrugActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    private EnterDrugModel enterDrugModel;
    EditText edtTenThuoc,edtCongDung,edtGia,edtNguonGoc,edtMoTa,edtSoLuong;
    Button btnXacNhan;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entering_drug_layout);

        //Ini view
        edtTenThuoc = (EditText) findViewById(R.id.edtNhapTenThuoc);
        edtCongDung = (EditText) findViewById(R.id.edtNhapCongDung);
        edtGia = (EditText) findViewById(R.id.edtNhapGia);
        edtNguonGoc = (EditText) findViewById(R.id.edtNhapNguonGoc);
        edtMoTa = (EditText) findViewById(R.id.edtNhapMoTa);
        edtSoLuong = (EditText) findViewById(R.id.edtNhapSoLuong);
        btnXacNhan = (Button) findViewById(R.id.btnOKNT);
        actionBar = getSupportActionBar();

        //Ini firebase
        mAuth = FirebaseAuth.getInstance();


        //Process button back
        actionBar.setDisplayHomeAsUpEnabled(true);


        //Process add drug
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idThuoc = FirebaseDatabase.getInstance().getReference().push().getKey();
                root = FirebaseDatabase.getInstance().getReference("Drug").child(mAuth.getUid()).child(idThuoc);
                enterDrugModel = new EnterDrugModel(edtTenThuoc.getText().toString().trim(),edtCongDung.getText().toString().trim(),edtGia.getText().toString().trim(),edtNguonGoc.getText().toString().trim(),edtMoTa.getText().toString().trim(),Integer.parseInt(edtSoLuong.getText().toString().trim()));
                root.setValue(enterDrugModel);
                Toast.makeText(EnteringDrugActivity.this,"Đã thêm thuốc",Toast.LENGTH_SHORT).show();

                edtTenThuoc.setText("");
                edtCongDung.setText("");
                edtGia.setText("");
                edtNguonGoc.setText("");
                edtMoTa.setText("");
                edtSoLuong.setText("");
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
