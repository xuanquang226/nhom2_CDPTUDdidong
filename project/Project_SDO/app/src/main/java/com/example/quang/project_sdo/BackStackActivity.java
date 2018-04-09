package com.example.quang.project_sdo;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class BackStackActivity extends AppCompatActivity {
    ImageButton imgHome;
    ImageButton imgDrug;
    ImageButton imgChat;
    ImageButton imgAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_stack_layout);

        //Process
        imgHome = (ImageButton) findViewById(R.id.imgHome);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(new HomeFragment());
            }
        });

        imgDrug = (ImageButton) findViewById(R.id.imgDrug);
        imgDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(new ListDrugFragment());
            }
        });

        imgChat = (ImageButton) findViewById(R.id.imgChat);
        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(new ChatFragment());
            }
        });

        imgAccount = (ImageButton) findViewById(R.id.imgAccount);
        imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(new AccountManagementFragment());
            }
        });


        addFragment(new HomeFragment());
    }

    protected void addFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.flScreen, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // dua nut search vao action bar
        getMenuInflater().inflate(R.menu.menu_search_home,menu);
       // tao 1 search view
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.menuSearchHome).getActionView();
        //bat su kien cho nut search

       searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               Toast.makeText(BackStackActivity.this, query, Toast.LENGTH_SHORT).show();
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               Log.d("ABC", newText);
               return false;
           }
       });
        return super.onCreateOptionsMenu(menu);
    }
}
