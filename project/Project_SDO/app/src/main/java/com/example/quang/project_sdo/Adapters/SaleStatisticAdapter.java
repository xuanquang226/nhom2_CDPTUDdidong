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

import com.example.quang.project_sdo.Models.SaleStatisticModel;
import com.example.quang.project_sdo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SaleStatisticAdapter extends ArrayAdapter<SaleStatisticModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<SaleStatisticModel> saleStatisticModels;

    public SaleStatisticAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<SaleStatisticModel> objects) {
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
            viewHolder.drugImage = (ImageView) convertView.findViewById(R.id.imgShoppingCartDrug);
            viewHolder.postDate = (TextView) convertView.findViewById(R.id.txtpostDate);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameDrug.setText(saleStatisticModels.get(position).getDrugName());
        viewHolder.drugPrice.setText(saleStatisticModels.get(position).getDrugPrice());
        viewHolder.drugAmount.setText(saleStatisticModels.get(position).getDrugAmount());
        Picasso.get().load(saleStatisticModels.get(position).drugImage).into(viewHolder.drugImage);
        return convertView;
    }

}

