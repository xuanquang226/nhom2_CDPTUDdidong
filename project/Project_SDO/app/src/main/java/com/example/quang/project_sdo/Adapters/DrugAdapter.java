package com.example.quang.project_sdo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.ListDrugModel;
import com.example.quang.project_sdo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ITLAB on 4/4/2018.
 */

public class DrugAdapter extends ArrayAdapter<ListDrugModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<ListDrugModel> listDrug;

    public DrugAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ListDrugModel> objects) {
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

        viewHolder.nameDrug.setText(listDrug.get(position).getNameDrug());
        viewHolder.nameStore.setText(listDrug.get(position).getNameStore());
        viewHolder.priceDrug.setText(listDrug.get(position).getPriceDrug());
        viewHolder.drugImage.setImageResource(listDrug.get(position).getImageDrug());
        return convertView;
    }
}
