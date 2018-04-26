package com.example.quang.project_sdo.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.CarrierDeliveryScheduleModel;
import com.example.quang.project_sdo.R;

import java.util.ArrayList;

public class CarrierDeliveryScheduleAdapter extends ArrayAdapter<CarrierDeliveryScheduleModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<CarrierDeliveryScheduleModel> listDeliverySchedule ;

    public CarrierDeliveryScheduleAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<CarrierDeliveryScheduleModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.listDeliverySchedule = objects;
    }
    public class ViewHolder{
        TextView txt_area;
        TextView txt_order;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_area = (TextView) convertView.findViewById(R.id.txt_carrierArea);
            viewHolder.txt_order = (TextView) convertView.findViewById(R.id.txt_carrierOrder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txt_area.setText(listDeliverySchedule.get(position).getArea());
        viewHolder.txt_order.setText(listDeliverySchedule.get(position).getOrder());
        return convertView;
    }
}
