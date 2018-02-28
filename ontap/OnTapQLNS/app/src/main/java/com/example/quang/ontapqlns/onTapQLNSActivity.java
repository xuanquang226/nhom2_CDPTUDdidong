package com.example.quang.ontapqlns;


import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class onTapQLNSActivity extends AppCompatActivity {
    ArrayList<QLNS> nhanSu = new ArrayList<QLNS>();
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_tap_qlns_layout);

        //Ini
        final EditText edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        final EditText edtSTK = (EditText) findViewById(R.id.edtSoThichKhac);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdgroup);
        RadioButton rbtnDH = (RadioButton) findViewById(R.id.rbtnDH);
        RadioButton rbtnCD = (RadioButton) findViewById(R.id.rbtnCD);
        RadioButton rbtnTC = (RadioButton) findViewById(R.id.rbtnTC);
        final CheckBox chkDS = (CheckBox) findViewById(R.id.chkDS);
        final CheckBox chkNN = (CheckBox) findViewById(R.id.chkNN);
        Button btnOK = (Button) findViewById(R.id.btnOK);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        ListView listView = (ListView) findViewById(R.id.listviewA);

        adapter = new MyAdapter(onTapQLNSActivity.this, R.layout.listview_custom, nhanSu);
        listView.setAdapter(adapter);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bc = -1;
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rbtnDH:
                        bc = 0;
                        break;
                    case R.id.rbtnCD:
                        bc = 1;
                        break;
                    case R.id.rbtnTC:
                        bc = 2;
                        break;
                }

                String sothich = " ";
                if (chkDS.isChecked()) {
                    sothich += chkDS.getText().toString() + ",";
                }
                if (chkNN.isChecked()) {
                    sothich += chkNN.getText().toString() + ",";
                }
                sothich += edtSTK.getText().toString();

                QLNS nhanvien = new QLNS(edtHoTen.getText().toString(), bc, sothich);
                nhanSu.add(nhanvien);

                if (!edtHoTen.getText().toString().isEmpty()) {
                    edtHoTen.setText("");
                    edtSTK.setText(" ");
                    radioGroup.clearCheck();
                    chkDS.setChecked(false);
                    chkNN.setChecked(false);
                }
                adapter.notifyDataSetChanged();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registerForContextMenu(listView);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteItem:
                deleteItem();
                return true;
            case R.id.updateItem:
                updateItem();
                return true;
            case R.id.saveItem:
                saveItem();
                return true;
            case R.id.loadItem:
                loadItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        nhanSu.get(menuInfo.position).setChecked(true);
        switch (item.getItemId()) {
            case R.id.deleteItem:
                deleteItem();
                return true;
            case R.id.updateItem:
                updateItem();
                return true;
            case R.id.saveItem:
                saveItem();
                return true;
            case R.id.loadItem:
                loadItem();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deleteItem() {
        for (int i = 0; i < nhanSu.size(); i ++){
            if(nhanSu.get(i).isChecked()){
                Database database = new Database(this);
                database.deleteItem(nhanSu.get(i));
                nhanSu.remove(i);
                --i;
            }
            adapter.notifyDataSetChanged();
        }
    }

    public void updateItem() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Chỉnh sửa thông tin");
        dialog.setContentView(R.layout.dialog_update);

        final EditText edtHoTen = (EditText) dialog.findViewById(R.id.edtHoTen);
        final EditText edtSTK = (EditText) dialog.findViewById(R.id.edtSoThichKhac);
        final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.rdgroup);
        RadioButton rbtnDH = (RadioButton) dialog.findViewById(R.id.rbtnDH);
        RadioButton rbtnCD = (RadioButton) dialog.findViewById(R.id.rbtnCD);
        RadioButton rbtnTC = (RadioButton) dialog.findViewById(R.id.rbtnTC);
        final CheckBox chkDS = (CheckBox) dialog.findViewById(R.id.chkDS);
        final CheckBox chkNN = (CheckBox) dialog.findViewById(R.id.chkNN);
        Button btnOK = (Button) dialog.findViewById(R.id.btnOK);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bc = -1;
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rbtnDH:
                        bc = 0;
                        break;
                    case R.id.rbtnCD:
                        bc = 1;
                        break;
                    case R.id.rbtnTC:
                        bc = 2;
                        break;
                }

                String sothich = " ";
                if (chkDS.isChecked()) {
                    sothich += chkDS.getText().toString() + ",";
                }
                if (chkNN.isChecked()) {
                    sothich += chkNN.getText().toString() + ",";
                }
                sothich += edtSTK.getText().toString();
                for(QLNS nhanvien:nhanSu){
                    Database database = new Database(onTapQLNSActivity.this);
                    database.updateItem(nhanvien, new QLNS(edtHoTen.getText().toString(),bc,sothich));
                    nhanvien.setHoten(edtHoTen.getText().toString());
                    nhanvien.setBangcap(bc);
                    nhanvien.setSothich(sothich);
                }
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void saveItem() {
        Database database = new Database(this);
        database.saveItem(nhanSu);
    }

    public void loadItem() {
        Database database = new Database(this);
        nhanSu.clear();
        database.loadItem(nhanSu);
        adapter.notifyDataSetChanged();
    }
}
