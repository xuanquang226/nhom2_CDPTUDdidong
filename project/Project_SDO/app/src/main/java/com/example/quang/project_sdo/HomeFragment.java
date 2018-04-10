package com.example.quang.project_sdo;

import android.content.Intent;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.DrugAdapter;
import com.example.quang.project_sdo.Adapters.HomeListDrugAdapter;
import com.example.quang.project_sdo.Models.ListDrugForHomeModel;
import com.example.quang.project_sdo.Models.ListDrugModel;

import java.util.ArrayList;

/**
 * Created by Quang on 3/11/2018.
 */

public class HomeFragment extends Fragment {
    ListView listView;
    ArrayList<ListDrugForHomeModel> listHomeDrug = new ArrayList<ListDrugForHomeModel>();
    HomeListDrugAdapter adapter;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.home_layout, container, false);

        //ListView
        listView = (ListView) view.findViewById(R.id.listViewHome);
        listHomeDrug.clear();
        listHomeDrug = new ArrayList<ListDrugForHomeModel>();
        listHomeDrug.add(new ListDrugForHomeModel("thuốc đau đầu","ABC Store","120.000",R.drawable.drug));
        listHomeDrug.add(new ListDrugForHomeModel("thuốc sad","777 Store","150.000",R.drawable.drug));
        listHomeDrug.add(new ListDrugForHomeModel("thuốc phá thai","666 Store","180.000",R.drawable.drug));
        listHomeDrug.add(new ListDrugForHomeModel("thuốc bbb","5555 Store","220.000",R.drawable.drug));
        adapter = new HomeListDrugAdapter((AppCompatActivity) getContext(), R.layout.listview_home_custom, listHomeDrug);
        listView.setAdapter(adapter);
        return view;
    }


}
