package com.example.quang.project_sdo;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quang.project_sdo.Adapters.DrugAdapter;
import com.example.quang.project_sdo.Models.ListDrugModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 3/11/2018.
 */

public class ListDrugFragment extends Fragment {
    ListView listView;
    ArrayList<ListDrugModel> listDrug = new ArrayList<ListDrugModel>();
    DrugAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.list_drug_layout, container, false);

        listView = (ListView) view.findViewById(R.id.drug_listview);
        adapter = new DrugAdapter((AppCompatActivity) getContext(), R.layout.listview_drug_custom, listDrug);
        listView.setAdapter(adapter);
        return view;
    }

}
