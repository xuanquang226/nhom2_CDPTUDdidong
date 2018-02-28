package com.example.quang.ontapqlns;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 1/4/2018.
 */

public class MyAdapter extends ArrayAdapter<QLNS> {
    AppCompatActivity context;
    int layout;
    ArrayList<QLNS> nhanSu;


    public MyAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<QLNS> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.nhanSu = objects;
    }

    public class ViewHolder {
        LinearLayout bangcap;
        TextView hoten;
        TextView sothich;
        CheckBox chkListItem;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.bangcap = (LinearLayout) convertView.findViewById(R.id.bc_color);
            viewHolder.hoten = (TextView) convertView.findViewById(R.id.txthoten);
            viewHolder.sothich = (TextView) convertView.findViewById(R.id.txtsothich);
            viewHolder.chkListItem = (CheckBox) convertView.findViewById(R.id.chkList);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (nhanSu.get(position).getBangcap() == 0) {
            viewHolder.bangcap.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else if (nhanSu.get(position).getBangcap() == 1) {
            viewHolder.bangcap.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else if (nhanSu.get(position).getBangcap() == 2) {
            viewHolder.bangcap.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            viewHolder.bangcap.setBackgroundColor(context.getResources().getColor(R.color.colorA));
        }

        viewHolder.hoten.setText(nhanSu.get(position).getHoten());
        viewHolder.sothich.setText(nhanSu.get(position).getSothich());

        if(nhanSu.get(position).isChecked()){
            viewHolder.chkListItem.setChecked(true);
        }else{
            viewHolder.chkListItem.setChecked(false);
        }

        viewHolder.chkListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.chkListItem.isChecked()){
                    nhanSu.get(position).setChecked(true);
                }else{
                    nhanSu.get(position).setChecked(false);
                }
            }
        });

        return convertView;
    }
}
