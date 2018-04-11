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
import com.example.quang.project_sdo.Models.StomachModel;

import java.util.ArrayList;

/**
 * Created by PC on 11/04/2018.
 */

public class StomachAdapter extends ArrayAdapter<StomachModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<StomachModel> stomachModels;

    public StomachAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<StomachModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.stomachModels = objects;
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

        StomachAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new StomachAdapter.ViewHolder();
            viewHolder.nameDrug = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtNameDrug);
            viewHolder.priceDrug = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtGia);
            viewHolder.nameStore = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtDrugStore);
            viewHolder.drugImage = (ImageView) convertView.findViewById(com.example.quang.project_sdo.R.id.imgDrug);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (StomachAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.nameDrug.setText(stomachModels.get(position).getNameDrug());
        viewHolder.nameStore.setText(stomachModels.get(position).getNameStore());
        viewHolder.priceDrug.setText(stomachModels.get(position).getPriceDrug());
        viewHolder.drugImage.setImageResource(stomachModels.get(position).getImageDrug());
        return convertView;
    }
}
