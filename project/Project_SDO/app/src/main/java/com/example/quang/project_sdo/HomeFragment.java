package com.example.quang.project_sdo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    ImageView img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.home_layout, container, false);

        img = (ImageView) view.findViewById(R.id.imgHomeDrug);
        listView = (ListView) view.findViewById(R.id.lstHome);


        listHomeDrug = new ArrayList<ListDrugForHomeModel>();


        adapter = new HomeListDrugAdapter((AppCompatActivity) getContext(), R.layout.listview_home_custom, listHomeDrug);


        listView.setAdapter(adapter);
        return view;
    }
}
