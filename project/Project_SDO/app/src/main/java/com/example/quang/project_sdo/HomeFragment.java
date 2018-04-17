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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quang.project_sdo.Adapters.DrugAdapter;
import com.example.quang.project_sdo.Adapters.HomeListDrugAdapter;
import com.example.quang.project_sdo.Models.HeadacheModel;
import com.example.quang.project_sdo.Models.ListDrugForHomeModel;
import com.example.quang.project_sdo.Models.ListDrugModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Quang on 3/11/2018.
 */

public class HomeFragment extends Fragment {
    ListView listView;
    ArrayList<ListDrugForHomeModel> listHomeDrug = new ArrayList<ListDrugForHomeModel>();
    ArrayList<ListDrugForHomeModel> searchDrug = new ArrayList<ListDrugForHomeModel>();
    HomeListDrugAdapter adapter = null;
    HomeListDrugAdapter searchArray = null;
    View view = null;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_layout, container, false);
        setHasOptionsMenu(true);
        //ListView
        listView = (ListView) view.findViewById(R.id.listViewHome);
        listHomeDrug.clear();
        listHomeDrug = new ArrayList<ListDrugForHomeModel>();
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc Cefixim","Thuốc dùng cho bệnh đau dạ dày, viêm loét....","10-04-2018",R.drawable.img_cefixim));
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc Giảm Đau","Thuốc được dùng cho các tiểu phẩu...","10-02-2018",R.drawable.img_giamdau));
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc An Thần","Thuốc sử dụng cho bệnh nhân sau khi phẫu thuật....","25-03-2018",R.drawable.img_anthan));
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc Kháng Viêm","Thuốc sử dụng cho các trường hợp viêm cánh quá nặng ....","1-04-2018",R.drawable.img_khangviem));
        adapter = new HomeListDrugAdapter((AppCompatActivity) getContext(), R.layout.listview_home_custom, listHomeDrug);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // dua nut search vao action bar

        inflater.inflate(R.menu.menu_search_home,menu);
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
                Log.d("ABC", newText);

                newText = newText.toLowerCase(Locale.getDefault());
//                searchDrug.clear();
//                if(newText.length() == 0)
//                {
//                    searchDrug.addAll(listHomeDrug);
//
//
//                }else
//                {
//                    for (ListDrugForHomeModel listDrugForHomeModel  : listHomeDrug)
//                    {
//                        if (listDrugForHomeModel.getDrugName().toLowerCase(Locale.getDefault()).contains(newText)){
//                            searchDrug.add(listDrugForHomeModel);
//                        }
//                    }
//                }
//                searchArray = new HomeListDrugAdapter((AppCompatActivity) getActivity(),R.layout.listview_home_custom,searchDrug);
//                listView.setAdapter(adapter);

                if (newText != null && !newText.isEmpty()) {
                    ArrayList<ListDrugForHomeModel> listFound = new ArrayList<ListDrugForHomeModel>();
                    for(ListDrugForHomeModel item:listHomeDrug) {
                        if (item.getDrugName().toLowerCase(Locale.getDefault()).contains(newText)) {
                            listFound.add(item);
                        }
                    }
                    adapter = new HomeListDrugAdapter((AppCompatActivity) getActivity(),R.layout.listview_home_custom, listFound);
                    listView.setAdapter(adapter);

                }
                else {
                    adapter = new HomeListDrugAdapter((AppCompatActivity) getActivity(),R.layout.listview_home_custom, listHomeDrug);
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
//
//    // Filter Class
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        listHomeDrug.clear();
//        if (charText.length() == 0) {
//            listHomeDrug.addAll(searchDrug);
//        } else {
//            for (ListDrugForHomeModel wp : searchDrug) {
//                if (wp.getDrugName().toLowerCase(Locale.getDefault())
//                        .contains(charText)) {
//                    listHomeDrug.add(wp);
//                }
//            }
//        }
//        adapter.notifyDataSetChanged();
//    }

//aa
}
