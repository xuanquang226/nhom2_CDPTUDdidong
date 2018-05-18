package com.example.quang.project_sdo;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.project_sdo.Adapters.HomeListDrugAdapter;
import com.example.quang.project_sdo.Adapters.ShoppingCartAdapter;
import com.example.quang.project_sdo.Models.ListDrugForHomeModel;
import com.example.quang.project_sdo.Models.OrderModel;
import com.example.quang.project_sdo.Models.ShoppingCartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ShoppingCartActivity extends AppCompatActivity {
    ActionBar actionBar;
    ListView listView;
    ArrayList<OrderModel> listShopping = new ArrayList<OrderModel>();
    ArrayList<OrderModel> listShoppings = new ArrayList<OrderModel>();
    ShoppingCartAdapter adapter;
    Button btnContinue, btnOrder, btnIncrease, btnDecrease;
    TextView txtSoLuong, txtGiaCa, txtGiaCaA, txtTotal;
    DatabaseReference root;
    FirebaseAuth mAuth;
    int total;
    int total2;
    OrderModel orderModel, orderModel2;
    Handler handler, handler2;
    ShoppingCartAdapter a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_layout);
        root = FirebaseDatabase.getInstance().getReference("Order");
        mAuth = FirebaseAuth.getInstance();
        actionBar = getSupportActionBar();
        actionBar.setTitle("Shopping Cart");
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        txtSoLuong = (TextView) findViewById(R.id.txtPriceShoppingCart);
        txtGiaCa = (TextView) findViewById(R.id.txtShoppingCartPrice);
        txtGiaCaA = (TextView) findViewById(R.id.priceA);
        //ListView
        listView = (ListView) findViewById(R.id.lvShoppingCart);

        loadData();

        //processing for two buttons Continue and Order
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCartActivity.this, BackStackActivity.class));
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingCartActivity.this, PaymentConfirmActivity.class);
                startActivity(intent);
            }
        });
        handler = new Handler();
        handler2 = new Handler();
    }


    public void delete() {
        root.child(mAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (int i = 0; i < listShopping.size(); i++) {
                    if (listShopping.get(i).isChecked()) {
                        root.child(mAuth.getUid()).child(listShopping.get(i).getKey()).setValue(null);
                        listShopping.remove(i);
                        --i;
                        adapter.notifyDataSetChanged();
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

    public void loadData() {
        root.child(mAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                orderModel = dataSnapshot.getValue(OrderModel.class);
                listShopping.add(new OrderModel(orderModel.ten, orderModel.hinh, orderModel.gia, orderModel.soLuong, orderModel.key, orderModel.giagoc, orderModel.tongtien,orderModel.diachi));
                adapter = new ShoppingCartAdapter(ShoppingCartActivity.this, R.layout.listview_shoppingcart_custom, listShopping);
                listView.setAdapter(adapter);
                listShoppings.add(new OrderModel(orderModel.ten, orderModel.hinh, orderModel.gia, orderModel.soLuong, orderModel.key, orderModel.giagoc, orderModel.tongtien,orderModel.diachi));
                for (int i = 0; i < listShoppings.size(); i++) {
                    if (!listShoppings.get(i).isChecked()) {
                        total += listShoppings.get(i).gia;
                    }
                }
                listShoppings.clear();
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

    public void setPrice() {
        root.child(mAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                orderModel = dataSnapshot.getValue(OrderModel.class);
                listShoppings.add(orderModel);
                for (int i = 0; i < listShoppings.size(); i++) {
                    if (!listShoppings.get(i).isChecked()) {
                        total2 += listShoppings.get(i).gia;
                        listShoppings.remove(i);
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
        total2 = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_cart_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.delete_item:
                delete();
                setHandler();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setHandler() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setPrice();
                pushPrice();
            }
        }, 1500);

        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                getPriceAfterDelete();
            }
        }, 4000);

    }

    public void pushPrice() {
        root.child(mAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                orderModel = dataSnapshot.getValue(OrderModel.class);
                root.child(mAuth.getUid()).child(orderModel.getKey()).child("tongtien").setValue(total2);

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

    public void getPriceAfterDelete() {
        root.child(mAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                orderModel2 = dataSnapshot.getValue(OrderModel.class);
                listShoppings.add(new OrderModel(orderModel2.ten, orderModel2.hinh, orderModel2.gia, orderModel2.soLuong, orderModel2.key, orderModel2.giagoc, orderModel2.tongtien,orderModel2.diachi));
                Toast.makeText(ShoppingCartActivity.this, "Tổng số tiền " + listShoppings.get(0).getTongtien(), Toast.LENGTH_SHORT).show();
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
