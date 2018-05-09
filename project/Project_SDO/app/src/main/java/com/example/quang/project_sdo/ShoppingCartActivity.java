package com.example.quang.project_sdo;

import android.content.Intent;
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
    ArrayList<String> keys = new ArrayList<String>();
    ShoppingCartAdapter adapter;
    Button btnContinue, btnOrder, btnIncrease, btnDecrease;
    TextView txtSoLuong, txtGiaCa, txtGiaCaA, txtTotal;
    int dem = 0;
    int sum = 0;
    DatabaseReference root;
    FirebaseAuth mAuth;
    int i, total;
    OrderModel orderModel;



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
        txtTotal = (TextView) findViewById(R.id.txtTotal);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.delete_item:
                delete();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void delete() {
        root.child(mAuth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey().toString();
                keys.add(key);
                for (i = 0; i < listShopping.size(); i++) {
                    for(int j = 0; j < keys.size(); j++){
                        if(!listShopping.get(i).getKey().equals(null) && !keys.get(j).equals(null)){
                            if (listShopping.get(i).isChecked() && listShopping.get(i).getKey().equals(keys.get(j))) {
                                listShopping.remove(i);
                                root.child(mAuth.getUid()).child(keys.get(i)).setValue(null);
                                keys.remove(j);
                                --i;
                                --j;
                                adapter.notifyDataSetChanged();
                            }else{

                            }
                        }else{
                            Toast.makeText(ShoppingCartActivity.this,"Giỏ hàng rỗng",Toast.LENGTH_SHORT).show();
                        }
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
                listShopping.add(orderModel);
                adapter = new ShoppingCartAdapter(ShoppingCartActivity.this, R.layout.listview_shoppingcart_custom, listShopping);
                listView.setAdapter(adapter);

                //set total price textview
                for (i = 0; i < listShopping.size(); i++) {
                    if (!listShopping.get(i).isChecked()) {
                        total += listShopping.get(i).getGia();
                        Log.d("AAAA",total +"");
                        txtTotal.setText(total+"");
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ShoppingCartActivity.this,"AAA",Toast.LENGTH_SHORT);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_cart_item, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
