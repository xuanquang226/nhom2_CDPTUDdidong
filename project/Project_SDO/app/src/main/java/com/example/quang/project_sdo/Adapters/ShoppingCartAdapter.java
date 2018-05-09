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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.OrderModel;
import com.example.quang.project_sdo.Models.ShoppingCartModel;
import com.example.quang.project_sdo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends ArrayAdapter<OrderModel> {

    AppCompatActivity context;
    int layout;
    ArrayList<OrderModel> listShoppingCart;
    int dem;
    int soLuong = 0;
    DatabaseReference root;
    FirebaseAuth mAuth;
    ArrayList<String> keys;


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
        CheckBox chkSelected;

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
            viewHolder.chkSelected = (CheckBox) convertView.findViewById(R.id.chkSelected);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.drugName.setText(listShoppingCart.get(position).getTen());
        viewHolder.drugPrice.setText(Integer.toString(listShoppingCart.get(position).getGia()));
        viewHolder.drugAmount.setText(Integer.toString(listShoppingCart.get(position).getSoLuong()));

        Picasso.get().load(listShoppingCart.get(position).getHinh()).into(viewHolder.drugImage);






        if (dem >= 10) {
            viewHolder.btnIncrease.setVisibility(View.INVISIBLE);
            viewHolder.btnDecrease.setVisibility(View.VISIBLE);
        } else if (dem < 1) {
            viewHolder.btnDecrease.setVisibility(View.INVISIBLE);
        } else if (dem >= 1) {
            viewHolder.btnDecrease.setVisibility(View.VISIBLE);
            viewHolder.btnIncrease.setVisibility(View.VISIBLE);
        }

        if (listShoppingCart.get(position).isChecked()) {
            viewHolder.chkSelected.setChecked(true);
        } else {
            viewHolder.chkSelected.setChecked(false);
        }

        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.chkSelected.isChecked()) {
                    viewHolder.btnIncrease.setClickable(true);
                    viewHolder.btnDecrease.setClickable(true);
                    listShoppingCart.get(position).setChecked(true);
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
                            } else if (dem <= 1) {
                                viewHolder.btnDecrease.setVisibility(View.INVISIBLE);
                            } else if (dem >= 1) {
                                viewHolder.btnDecrease.setVisibility(View.VISIBLE);
                                viewHolder.btnIncrease.setVisibility(View.VISIBLE);
                            }

                            //Log.d("price",viewHolder.drugPrice.get);
                        }
                    });
                    viewHolder.btnDecrease.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dem = listShoppingCart.get(position).getSoLuong();
                            dem--;
                            viewHolder.drugAmount.setText(Integer.toString(dem));
                            int defaultPrice = listShoppingCart.get(position).getGia();
                            int sum = dem * defaultPrice;
                            viewHolder.drugPrice.setText(sum + "");
                            listShoppingCart.get(position).setSoLuong(dem);
                            if (dem >= 10) {
                                viewHolder.btnIncrease.setVisibility(View.INVISIBLE);
                                viewHolder.btnDecrease.setVisibility(View.VISIBLE);
                            } else if (dem <= 1) {
                                viewHolder.btnDecrease.setVisibility(View.INVISIBLE);
                            } else if (dem >= 1) {
                                viewHolder.btnDecrease.setVisibility(View.VISIBLE);
                                viewHolder.btnIncrease.setVisibility(View.VISIBLE);
                            }

                        }
                    });
                } else {
                    listShoppingCart.get(position).setChecked(false);
                    viewHolder.btnIncrease.setClickable(false);
                    viewHolder.btnDecrease.setClickable(false);

                }
            }
        });
        return convertView;
    }

    public void changeData(){
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Order").child(mAuth.getUid());
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                keys.add(key);
                OrderModel orderModel = dataSnapshot.getValue(OrderModel.class);
                listShoppingCart.add(orderModel);
                for (int i = 0; i < listShoppingCart.size(); i++){
                    if (listShoppingCart.get(i).isChecked){

                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
