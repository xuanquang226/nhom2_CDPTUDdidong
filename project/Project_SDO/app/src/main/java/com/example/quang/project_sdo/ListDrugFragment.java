package com.example.quang.project_sdo;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.DrugAdapter;
import com.example.quang.project_sdo.Adapters.HomeListDrugAdapter;
import com.example.quang.project_sdo.Models.ListDrugForHomeModel;
import com.example.quang.project_sdo.Models.ListDrugModel;

import java.util.ArrayList;
import java.util.List;
//ss
/**
 * Created by Quang on 3/11/2018.
 */

public class ListDrugFragment extends Fragment {
    ListView listView;
    ArrayList<ListDrugModel> listDrug = new ArrayList<ListDrugModel>();
    DrugAdapter adapter;
    ImageView img;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;

    private ActionBarDrawerToggle mToggle;


//hjgjhgjhgffffffffcc
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.list_drug_layout, container, false);

        img = (ImageView) view.findViewById(R.id.imgDrug);

        //NavigationView
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawLayout);
        mToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        navigationView = (NavigationView) view.findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ThuocA:
                        setThuocA();
                        mDrawerLayout.closeDrawer(Gravity.LEFT,true);
                        return true;
                    case R.id.ThuocB:
                        setThuocB();
                        mDrawerLayout.closeDrawer(Gravity.LEFT,true);
                        return true;

                }
                return true;
            }
        });
        //List view
        listView = (ListView) view.findViewById(R.id.drug_listview);


        listDrug = new ArrayList<ListDrugModel>();

        listDrug.add(new ListDrugModel("thuốc đau đầu","ABC Store","120.000",R.drawable.drug,R.drawable.coin));
        listDrug.add(new ListDrugModel("thuốc sad","777 Store","150.000",R.drawable.drug,R.drawable.coin));
        listDrug.add(new ListDrugModel("thuốc phá thai","666 Store","180.000",R.drawable.drug,R.drawable.coin));
        listDrug.add(new ListDrugModel("thuốc bbb","5555 Store","220.000",R.drawable.drug,R.drawable.coin));
        adapter = new DrugAdapter((AppCompatActivity) getContext(), R.layout.listview_drug_custom, listDrug);


        listView.setAdapter(adapter);
        return view;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.isDrawerIndicatorEnabled() ){
            mDrawerLayout.openDrawer(Gravity.LEFT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setThuocA(){
        listDrug.clear();
        listDrug.add(new ListDrugModel("thuốc A","ABC Store","120.000",R.drawable.drug,R.drawable.coin));
        listDrug.add(new ListDrugModel("thuốc B","ABC Store","120.000",R.drawable.drug,R.drawable.coin));
        listDrug.add(new ListDrugModel("thuốc B","ABC Store","120.000",R.drawable.drug,R.drawable.coin));
        adapter = new DrugAdapter((AppCompatActivity) getContext(), R.layout.listview_drug_custom, listDrug);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void setThuocB(){
        listDrug.clear();
        listDrug.add(new ListDrugModel("thuốc E","ABC Store","120.000",R.drawable.drug,R.drawable.coin));
        listDrug.add(new ListDrugModel("thuốc F","ABC Store","120.000",R.drawable.drug,R.drawable.coin));
        listDrug.add(new ListDrugModel("thuốc H","ABC Store","120.000",R.drawable.drug,R.drawable.coin));
        adapter = new DrugAdapter((AppCompatActivity) getContext(), R.layout.listview_drug_custom, listDrug);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
