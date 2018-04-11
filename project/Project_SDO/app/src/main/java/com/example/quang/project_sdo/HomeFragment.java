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
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc Cefixim","Thuốc dùng cho bệnh đau dạ dày, viêm loét....","10-04-2018",R.drawable.img_cefixim));
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc Giảm Đau","Thuốc được dùng cho các tiểu phẩu...","10-02-2018",R.drawable.img_giamdau));
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc An Thần","Thuốc sử dụng cho bệnh nhân sau khi phẫu thuật....","25-03-2018",R.drawable.img_anthan));
        listHomeDrug.add(new ListDrugForHomeModel("Thuốc Kháng Viêm","Thuốc sử dụng cho các trường hợp viêm cánh quá nặng ....","1-04-2018",R.drawable.img_khangviem));
        adapter = new HomeListDrugAdapter((AppCompatActivity) getContext(), R.layout.listview_home_custom, listHomeDrug);
        listView.setAdapter(adapter);
        return view;
    }


}
