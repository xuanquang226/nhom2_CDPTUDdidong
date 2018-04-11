package com.example.quang.project_sdo.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.CoughModel;

import java.util.ArrayList;

/**
 * Created by PC on 11/04/2018.
 */

public class CoughAdapter extends ArrayAdapter<CoughModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<CoughModel> coughModels;

    public CoughAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<CoughModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.coughModels = objects;
    }

    public class ViewHolder {
        TextView nameDrug;
        TextView priceDrug;
        TextView nameStore;
        ImageView drugImage;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CoughAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new CoughAdapter.ViewHolder();
            viewHolder.nameDrug = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtNameDrug);
            viewHolder.priceDrug = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtGia);
            viewHolder.nameStore = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtDrugStore);
            viewHolder.drugImage = (ImageView) convertView.findViewById(com.example.quang.project_sdo.R.id.imgDrug);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CoughAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.nameDrug.setText(coughModels.get(position).getNameDrug());
        viewHolder.nameStore.setText(coughModels.get(position).getNameStore());
        viewHolder.priceDrug.setText(coughModels.get(position).getPriceDrug());
        viewHolder.drugImage.setImageResource(coughModels.get(position).getImageDrug());
        return convertView;
    }
}
