package com.example.quang.project_sdo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.ScheduleModel;
import com.example.quang.project_sdo.R;

import java.util.ArrayList;

/**
 * Created by ITLAB on 4/19/2018.
 */

public class ScheduleAdapter extends ArrayAdapter<ScheduleModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<ScheduleModel> listSchedule;

    public ScheduleAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ScheduleModel> objects) {
        super(context, resource,objects);
        this.context = context;
        this.layout = resource;
        this.listSchedule = objects;
    }
    public class ViewHolder {
        TextView txtOrder;
        TextView txtAdress;
        TextView txtnumberPhone;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtOrder = (TextView) convertView.findViewById(R.id.txt_order);
            viewHolder.txtAdress = (TextView) convertView.findViewById(R.id.txt_adress);
            viewHolder.txtnumberPhone = (TextView) convertView.findViewById(R.id.txt_customphone);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtOrder.setText(listSchedule.get(position).getOrderShedule());
        viewHolder.txtAdress.setText(listSchedule.get(position).getAdress());
        viewHolder.txtnumberPhone.setText(listSchedule.get(position).getPhoneNumber());
        return convertView;
    }
}
