package com.example.quang.project_sdo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.example.quang.project_sdo.Models.SaleStatisticModel;
import com.example.quang.project_sdo.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class SaleStatisticAdapter extends ArrayAdapter<EnterDrugModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<EnterDrugModel> saleStatisticModels;

    public SaleStatisticAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<EnterDrugModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.saleStatisticModels = objects;
    }

    public class ViewHolder{
        TextView nameDrug;
        TextView postDate;
        TextView drugPrice;
        ImageView drugImage;
        TextView drugAmount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameDrug = (TextView) convertView.findViewById(R.id.txtNameDrugSale);
            viewHolder.drugPrice = (TextView) convertView.findViewById(R.id.txtGiaSale);
            viewHolder.drugAmount = (TextView) convertView.findViewById(R.id.txtAmount);
            viewHolder.drugImage = (ImageView) convertView.findViewById(R.id.imgDrugSale);
            viewHolder.postDate = (TextView) convertView.findViewById(R.id.txtpostDate);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameDrug.setText(saleStatisticModels.get(position).getTenthuoc());
        viewHolder.drugPrice.setText(saleStatisticModels.get(position).getGia());
        viewHolder.drugAmount.setText(saleStatisticModels.get(position).getSoluong());
        viewHolder.postDate.setText(android.text.format.DateFormat.format("dd/MM/yy",saleStatisticModels.get(position).getDate()));
        Picasso.get().load(saleStatisticModels.get(position).getLinkhinh()).into(viewHolder.drugImage);
        return convertView;
    }

}
