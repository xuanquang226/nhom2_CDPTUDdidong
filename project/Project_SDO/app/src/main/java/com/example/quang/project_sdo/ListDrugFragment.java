package com.example.quang.project_sdo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quang.project_sdo.Adapters.DrugAdapter;
import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDrugFragment extends Fragment {


    ListView listView;
    ArrayList<EnterDrugModel> listDrug = new ArrayList<EnterDrugModel>();
    DrugAdapter adapter;
    ImageView img;
    DatabaseReference root;
    FirebaseAuth mAuth;



    ArrayList<EnterDrugModel> searchDrug = new ArrayList<EnterDrugModel>();
    DrugAdapter searchArray = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.list_drug_layout, container, false);
        setHasOptionsMenu(true);
        img = (ImageView) view.findViewById(R.id.imgDrug);
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();

        //List view
        listView = (ListView) view.findViewById(R.id.drugList);
        adapter = new DrugAdapter((AppCompatActivity) getContext(), R.layout.listview_drug_custom, listDrug);
        listView.setAdapter(adapter);
        loadData();



        return view;

    }

    public void loadData() {
        root.child("Drug").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EnterDrugModel drugModel = dataSnapshot.getValue(EnterDrugModel.class);
                listDrug.add(new EnterDrugModel(drugModel.tenthuoc, drugModel.congdung, drugModel.gia, drugModel.nguongoc,drugModel.mota,drugModel.soluong,drugModel.linkhinh,drugModel.tenshop,drugModel.id));
                adapter.notifyDataSetChanged();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Bundle bundle = new Bundle();
                        bundle.putString("hinhanh",listDrug.get(i).linkhinh);
                        bundle.putString("congdung",listDrug.get(i).congdung);
                        bundle.putString("nguongoc",listDrug.get(i).nguongoc);
                        bundle.putString("mota",listDrug.get(i).mota);
                        bundle.putString("gia",listDrug.get(i).gia);
                        bundle.putString("tenshop",listDrug.get(i).tenshop);
                        Intent intent = new Intent(getActivity(),DrugDetailActivity.class);
                        intent.putExtra("data",bundle);
                        startActivity(intent);
                    }
                });
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // dua nut search vao action bar

        inflater.inflate(R.menu.menu_search_home, menu);
        // tao 1 search view
        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.menuSearchHome).getActionView();
        //bat su kien cho nut search

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(BackStackActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase(Locale.getDefault());
//                searchDrug.clear();
//                if(newText.length() == 0)
//                {
//                    searchDrug.addAll(listDrug);
//
//
//                }else
//                {
//                    for (ListDrugModel listDrugModel  : listDrug)
//                    {
//                        if (listDrugModel.nameDrug.toLowerCase(Locale.getDefault()).contains(newText)){
//                            searchDrug.add(listDrugModel);
//                        }
//                    }
//                }
//                searchArray = new DrugAdapter((AppCompatActivity) getActivity(),R.layout.listview_home_custom,searchDrug);
//                listView.setAdapter(adapter);
                if (newText != null && !newText.isEmpty()) {
                    ArrayList<EnterDrugModel> listFound = new ArrayList<EnterDrugModel>();
                    for (EnterDrugModel item : listDrug) {
                        if (item.tenthuoc.toLowerCase(Locale.getDefault()).contains(newText)) {
                            listFound.add(item);
                        }
                    }
                    adapter = new DrugAdapter((AppCompatActivity) getActivity(), R.layout.listview_drug_custom, listFound);
                    listView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    adapter = new DrugAdapter((AppCompatActivity) getActivity(), R.layout.listview_drug_custom, listDrug);
                    listView.setAdapter(adapter);
                }

                adapter.notifyDataSetChanged();
                if (newText.length() == 0) {
                    searchView.clearFocus();
                }

                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
