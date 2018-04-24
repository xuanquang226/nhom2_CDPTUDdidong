package com.example.quang.project_sdo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quang.project_sdo.Adapters.HomeListDrugAdapter;
import com.example.quang.project_sdo.Adapters.ShoppingCartAdapter;
import com.example.quang.project_sdo.Models.ListDrugForHomeModel;
import com.example.quang.project_sdo.Models.ShoppingCartModel;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ShoppingCartActivity extends AppCompatActivity {
    ActionBar actionBar;
    ListView listView;
    ArrayList<ShoppingCartModel> listShopping = new ArrayList<>();
    ShoppingCartAdapter adapter = null;
    Button btnContinue, btnOrder, btnIncrease, btnDecrease;
    TextView txtSoLuong, txtGiaCa, txtGiaCaA;
    int dem = 0;
    int sum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_layout);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Shopping Cart");

        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        txtSoLuong = (TextView) findViewById(R.id.txtPriceShoppingCart);
        txtGiaCa = (TextView) findViewById(R.id.txtShoppingCartPrice);
        txtGiaCaA = (TextView) findViewById(R.id.priceA);
        //ListView
        listView = (ListView) findViewById(R.id.lvShoppingCart);
        listShopping.clear();
        listShopping = new ArrayList<ShoppingCartModel>();
        listShopping.add(new ShoppingCartModel("Thuốc Cefixim", 200, R.drawable.img_cefixim, "1"));
        listShopping.add(new ShoppingCartModel("Thuốc Giảm Đau", 200, R.drawable.img_giamdau, "1"));
        listShopping.add(new ShoppingCartModel("Thuốc An Thần", 300, R.drawable.img_anthan, "1"));
        listShopping.add(new ShoppingCartModel("Thuốc Kháng Viêm", 500, R.drawable.img_khangviem, "1"));
        adapter = new ShoppingCartAdapter(this, R.layout.listview_shoppingcart_custom, listShopping);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //processing for two buttons Continue and Order
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCartActivity.this,BackStackActivity.class));
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingCartActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
    }


}
