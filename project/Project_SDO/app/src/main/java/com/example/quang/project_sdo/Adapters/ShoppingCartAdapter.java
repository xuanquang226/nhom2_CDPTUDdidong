package com.example.quang.project_sdo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.ShoppingCartModel;
import com.example.quang.project_sdo.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends ArrayAdapter<ShoppingCartModel> {

    AppCompatActivity context;
    int layout;
    ArrayList<ShoppingCartModel> listShoppingCart;
    int dem;

    public ShoppingCartAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ShoppingCartModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.listShoppingCart = objects;
    }

    public class ViewHolder {
        TextView drugName;
        TextView drugPrice;
        TextView drugAmount;
        ImageView drugImage;
        Button btnDecrease;
        Button btnIncrease;


    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.drugName = (TextView) convertView.findViewById(R.id.txtShoppingCartDrugName);
            viewHolder.drugPrice = (TextView) convertView.findViewById(R.id.txtShoppingCartPrice);
            viewHolder.drugAmount = (TextView) convertView.findViewById(R.id.txtPriceShoppingCart);
            viewHolder.drugImage = (ImageView) convertView.findViewById(R.id.imgShoppingCartDrug);
            viewHolder.btnDecrease = (Button) convertView.findViewById(R.id.btnDecrease);
            viewHolder.btnIncrease = (Button) convertView.findViewById(R.id.btnIncrease);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.drugName.setText(listShoppingCart.get(position).getDrugName());
        viewHolder.drugPrice.setText(Integer.toString(listShoppingCart.get(position).getDrugPrice()));
        viewHolder.drugAmount.setText(listShoppingCart.get(position).getDrugTextAmount());
        viewHolder.drugImage.setImageResource(listShoppingCart.get(position).getDrugImage());


        viewHolder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem = Integer.parseInt(listShoppingCart.get(position).getDrugTextAmount());
                dem++;
                viewHolder.drugAmount.setText(Integer.toString(dem));
                int defaultPrice = (Integer.parseInt(listShoppingCart.get(position).getDrugPrice().toString()));
                int sum = dem * defaultPrice;
                viewHolder.drugPrice.setText(sum + "");
                listShoppingCart.get(position).setDrugTextAmount(dem+"");


                //Log.d("price",viewHolder.drugPrice.get);
            }
        });

        viewHolder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem = Integer.parseInt(listShoppingCart.get(position).getDrugTextAmount());
                dem--;
                viewHolder.drugAmount.setText(Integer.toString(dem));
                int defaultPrice = (Integer.parseInt(listShoppingCart.get(position).getDrugPrice().toString()));
                int sum = dem * defaultPrice;
                viewHolder.drugPrice.setText(sum + "");
                listShoppingCart.get(position).setDrugTextAmount(dem+"");
            }
        });


        return convertView;
    }


}
