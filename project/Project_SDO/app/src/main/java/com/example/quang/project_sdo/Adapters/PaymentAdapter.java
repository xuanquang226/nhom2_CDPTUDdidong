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

import com.example.quang.project_sdo.Models.OrderModel;
import com.example.quang.project_sdo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Quang on 5/19/2018.
 */

public class PaymentAdapter extends ArrayAdapter<OrderModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<OrderModel> listPayment = new ArrayList<OrderModel>();


    public PaymentAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<OrderModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.listPayment = objects;
    }

    public class ViewHolder {
        TextView drugName;
        TextView drugPrice;
        ImageView drugImage;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.drugName = (TextView) convertView.findViewById(R.id.txtNameDrugP);
            viewHolder.drugPrice = (TextView) convertView.findViewById(R.id.txtPriceDrugP);
            viewHolder.drugImage = (ImageView) convertView.findViewById(R.id.imgDrugP);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.drugName.setText(listPayment.get(position).getTen());
        viewHolder.drugPrice.setText(Integer.toString(listPayment.get(position).getGia()));
        Picasso.get().load(listPayment.get(position).getHinh()).into(viewHolder.drugImage);

        return convertView;
    }
}
