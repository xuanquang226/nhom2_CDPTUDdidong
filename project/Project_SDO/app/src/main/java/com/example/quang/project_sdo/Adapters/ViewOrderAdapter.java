package com.example.quang.project_sdo.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.LOrderModel;
import com.example.quang.project_sdo.Models.OrderModel;
import com.example.quang.project_sdo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Trang on 5/19/2018.
 */

public class ViewOrderAdapter extends ArrayAdapter<LOrderModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<LOrderModel> orderModels = new ArrayList<LOrderModel>();

    public ViewOrderAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<LOrderModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.orderModels = objects;
    }

    public class ViewHolder {
        TextView tenthuoc;
        TextView tenhang;
        TextView diachi;
        TextView tongtien;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tenthuoc = (TextView) convertView.findViewById(R.id.tenThuoc);
            viewHolder.tenhang = (TextView) convertView.findViewById(R.id.hang);
            viewHolder.diachi = (TextView) convertView.findViewById(R.id.diachi);
            viewHolder.tongtien = (TextView) convertView.findViewById(R.id.tongtien);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tenthuoc.setText(orderModels.get(position).getTenthuoc());
        viewHolder.tenhang.setText(orderModels.get(position).getHang());
        viewHolder.diachi.setText(orderModels.get(position).getDiachi());
        viewHolder.tongtien.setText(Integer.toString(orderModels.get(position).getTongtien()));
        return convertView;
    }
}
