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

import com.example.quang.project_sdo.Models.OrderModel;
import com.example.quang.project_sdo.Models.ShoppingCartModel;
import com.example.quang.project_sdo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends ArrayAdapter<OrderModel> {

    AppCompatActivity context;
    int layout;
    ArrayList<OrderModel> listShoppingCart;
    int dem;
    int soLuong = 0;

    public ShoppingCartAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<OrderModel> objects) {
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
        TextView txtTotal;

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
            viewHolder.txtTotal = (TextView) convertView.findViewById(R.id.txtTotal);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.drugName.setText(listShoppingCart.get(position).getTen());
        viewHolder.drugPrice.setText(Integer.toString(listShoppingCart.get(position).getGia()));
        viewHolder.drugAmount.setText(Integer.toString(listShoppingCart.get(position).getSoLuong()));

        Picasso.get().load(listShoppingCart.get(position).getHinh()).into(viewHolder.drugImage);



        viewHolder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem = listShoppingCart.get(position).getSoLuong();
                dem++;
                viewHolder.drugAmount.setText(Integer.toString(dem));
                int defaultPrice = listShoppingCart.get(position).getGia();
                int sum = dem * defaultPrice;
                viewHolder.drugPrice.setText(sum + "");
                listShoppingCart.get(position).setSoLuong(dem);
                if (dem >= 10) {
                    viewHolder.btnIncrease.setVisibility(View.INVISIBLE);
                    viewHolder.btnDecrease.setVisibility(View.VISIBLE);
                }
                else if (dem <= 1) {
                    viewHolder.btnDecrease.setVisibility(View.INVISIBLE);
                }
                else if (dem >= 1) {
                    viewHolder.btnDecrease.setVisibility(View.VISIBLE);
                    viewHolder.btnIncrease.setVisibility(View.VISIBLE);
                }

                //Log.d("price",viewHolder.drugPrice.get);
            }
        });

        viewHolder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem =listShoppingCart.get(position).getSoLuong();
                dem--;
                viewHolder.drugAmount.setText(Integer.toString(dem));
                int defaultPrice = listShoppingCart.get(position).getGia();
                int sum = dem * defaultPrice;
                viewHolder.drugPrice.setText(sum + "");
                listShoppingCart.get(position).setSoLuong(dem);
                if (dem >= 10) {
                    viewHolder.btnIncrease.setVisibility(View.INVISIBLE);
                    viewHolder.btnDecrease.setVisibility(View.VISIBLE);
                }
                else if (dem <= 1) {
                    viewHolder.btnDecrease.setVisibility(View.INVISIBLE);
                }
                else if (dem >= 1) {
                    viewHolder.btnDecrease.setVisibility(View.VISIBLE);
                    viewHolder.btnIncrease.setVisibility(View.VISIBLE);
                }
            }
        });

        if (dem >= 10) {
            viewHolder.btnIncrease.setVisibility(View.INVISIBLE);
            viewHolder.btnDecrease.setVisibility(View.VISIBLE);
        }
        else if (dem < 1) {
            viewHolder.btnDecrease.setVisibility(View.INVISIBLE);
        }
        else if (dem >= 1) {
            viewHolder.btnDecrease.setVisibility(View.VISIBLE);
            viewHolder.btnIncrease.setVisibility(View.VISIBLE);
        }



        return convertView;
    }


}
