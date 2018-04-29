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

import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Quang on 4/29/2018.
 */

public class DrugAdapter extends ArrayAdapter<EnterDrugModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<EnterDrugModel> listDrug;


    public DrugAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<EnterDrugModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.listDrug = objects;

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
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameDrug = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtNameDrug);
            viewHolder.priceDrug = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtGia);
            viewHolder.nameStore = (TextView) convertView.findViewById(com.example.quang.project_sdo.R.id.txtDrugStore);
            viewHolder.drugImage = (ImageView) convertView.findViewById(com.example.quang.project_sdo.R.id.imgDrug);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameDrug.setText(listDrug.get(position).getTenthuoc());
        viewHolder.priceDrug.setText(listDrug.get(position).getGia());
        Picasso.get().load(listDrug.get(position).getLinkhinh()).into(viewHolder.drugImage);
        viewHolder.nameStore.setText(listDrug.get(position).getTenshop());

        return convertView;
    }

}
