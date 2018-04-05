package com.example.quang.project_sdo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.ListDrugForHomeModel;
import com.example.quang.project_sdo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trang on 4/5/2018.
 */

public class HomeListDrugAdapter extends ArrayAdapter<ListDrugForHomeModel> {

    AppCompatActivity context;
    int layout;
    ArrayList<ListDrugForHomeModel> homeListDrug;
    public HomeListDrugAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ListDrugForHomeModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.homeListDrug = objects;
    }

    public class ViewHolder {
        TextView nameDrug;
        TextView postDate;
        TextView description;
        ImageView drugImage;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layout, parent, false);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            viewHolder.nameDrug = (TextView) convertView.findViewById(R.id.txtHomeDrugName);
            viewHolder.postDate = (TextView) convertView.findViewById(R.id.txtHomePostDate);
            viewHolder.description = (TextView) convertView.findViewById(R.id.txtHomeDescription);
            viewHolder.drugImage = (ImageView) convertView.findViewById(R.id.imgHomeDrug);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameDrug.setText(homeListDrug.get(position).getDrugName());
        viewHolder.postDate.setText(homeListDrug.get(position).getDrugPostDate());
        viewHolder.description.setText(homeListDrug.get(position).getDrugDescription());
        viewHolder.drugImage.setImageResource(homeListDrug.get(position).getDrugImage());


        return convertView;
    }


}
