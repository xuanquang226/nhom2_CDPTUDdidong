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
import android.widget.TextView;

import com.example.quang.project_sdo.Adapters.ChatAdapter;
import com.example.quang.project_sdo.Models.ListChatModel;

import java.util.ArrayList;

/**
 * Created by Quang on 3/11/2018.
 */

public class ChatFragment extends Fragment {
    private ArrayList<ListChatModel> chatModels = new ArrayList<ListChatModel>();
    private ChatAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view =  inflater.inflate(R.layout.chat_layout,container,false);

        ImageView ava = (ImageView) view.findViewById(R.id.imgChat);
        TextView name = (TextView) view.findViewById(R.id.txtNameChat);
        TextView chat = (TextView) view.findViewById(R.id.txtChatRecent);

        ListView listView = (ListView) view.findViewById(R.id.listChat);

        chatModels.add(new ListChatModel("Shop A","Thuốc này có tác dụng phụ không shop",R.drawable.account));
        chatModels.add(new ListChatModel("Shop B","Thuốc này có tác dụng phụ không shop",R.drawable.account));
        chatModels.add(new ListChatModel("Shop C","Thuốc này có tác dụng phụ không shop",R.drawable.account));

        adapter = new ChatAdapter((AppCompatActivity) getActivity(),R.layout.list_chat_custom,chatModels);
        listView.setAdapter(adapter);

        return view;
    }
}
