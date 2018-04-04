package com.example.quang.project_sdo;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

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
            fragmentTransaction.add(R.id.flScreen, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_home,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
