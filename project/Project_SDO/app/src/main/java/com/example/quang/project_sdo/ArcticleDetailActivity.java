package com.example.quang.project_sdo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ArcticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arcticle_detail_layout);

        //Ini
        ImageView imgArcticle = (ImageView) findViewById(R.id.imageArcticle);
        TextView txtArcticle = (TextView) findViewById(R.id.txtArcticleDrug);
        TextView txtDrugName = (TextView) findViewById(R.id.titleDrug);
        ActionBar actionBar = getSupportActionBar();

        //Process
        actionBar.setTitle("Article Drug");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Data");
        txtArcticle.setText(bundle.getString("mota"));
        txtDrugName.setText(bundle.getString("tenthuoc"));
        Picasso.get().load(bundle.getString("hinhanh")).into(imgArcticle);
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
