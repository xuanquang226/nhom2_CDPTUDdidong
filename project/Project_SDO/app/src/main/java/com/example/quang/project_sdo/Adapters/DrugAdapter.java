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

import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.example.quang.project_sdo.Models.ListDrugModel;
import com.example.quang.project_sdo.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ITLAB on 4/4/2018.
 */

public class DrugAdapter extends ArrayAdapter<ListDrugModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<ListDrugModel> listDrug;
    //ArrayList<ListDrugModel> search;

    public DrugAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ListDrugModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.listDrug = objects;

//        this.search = new ArrayList<ListDrugModel>();
//        this.search.addAll(listDrug);
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

//    // Filter Class
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        listDrug.clear();
//        if (charText.length() == 0) {
//            listDrug.addAll(search);
//        } else {
//            for (ListDrugModel wp : search) {
//                if (wp.nameDrug.toLowerCase(Locale.getDefault())
//                        .contains(charText)) {
//                    listDrug.add(wp);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
//

