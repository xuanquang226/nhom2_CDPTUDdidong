package com.example.quang.project_sdo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.project_sdo.Adapters.ChatAdapter;
import com.example.quang.project_sdo.Adapters.DrugAdapter;
import com.example.quang.project_sdo.Models.ChatDetailModel;
import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.example.quang.project_sdo.Models.ListChatModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Quang on 3/11/2018.
 */

public class ChatFragment extends Fragment {
    private ArrayList<ListChatModel> chatModels = new ArrayList<ListChatModel>();
    private ChatAdapter adapter;
    private FirebaseAuth mAuth;
    DatabaseReference root;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.chat_layout, container, false);

        //Ini
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();
        //ImageView ava = (ImageView) view.findViewById(R.id.imgChat);
        TextView name = (TextView) view.findViewById(R.id.txtNameChat);
        TextView chat = (TextView) view.findViewById(R.id.txtChatRecent);

        listView = (ListView) view.findViewById(R.id.listChat);

        loadData();

        return view;
    }

    public void loadData() {
        root.child("Info chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (mAuth.getCurrentUser() != null) {
                    final ListChatModel chatModel = dataSnapshot.getValue(ListChatModel.class);
                    if ((mAuth.getUid().equalsIgnoreCase(chatModel.getIdUser())) || (mAuth.getUid().equalsIgnoreCase(chatModel.getIdSeller()))) {
                        chatModels.add(new ListChatModel(chatModel.idUser, chatModel.idSeller, chatModel.imgUser, chatModel.imgSeller, chatModel.chatUser, chatModel.chatSeller, chatModel.nameUser, chatModel.nameSeller));
                        adapter = new ChatAdapter((AppCompatActivity) getContext(), R.layout.list_chat_custom, chatModels);
                        listView.setAdapter(adapter);
                        for (int x = chatModels.size() - 1; x > 0 ; x--) {
                            for (int y = chatModels.size() - 2; y >= 0; y--) {
                                if ((mAuth.getUid().equalsIgnoreCase(chatModel.getIdUser()))) {
                                    if (chatModels.get(x).getNameSeller().equalsIgnoreCase(chatModels.get(y).getNameSeller())) {
                                        chatModels.remove(y);
                                        adapter.notifyDataSetChanged();
                                    }
                                } else if ((mAuth.getUid().equalsIgnoreCase(chatModel.getIdSeller()))) {
                                    if (chatModels.get(x).getNameUser().equalsIgnoreCase(chatModels.get(y).getNameUser())) {
                                        chatModels.remove(y);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    }
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Bundle bundle = new Bundle();
                            bundle.putString("idUser", chatModels.get(i).idUser);
                            bundle.putString("idSeller", chatModels.get(i).idSeller);
                            bundle.putString("imgUser", chatModels.get(i).imgUser);
                            bundle.putString("imgSeller", chatModels.get(i).imgSeller);
                            bundle.putString("nameUser", chatModels.get(i).nameUser);
                            bundle.putString("nameSeller", chatModels.get(i).nameSeller);
                            bundle.putString("nameUser", chatModels.get(i).nameUser);

                            Intent intent = new Intent(getActivity(), ChatDetailActivity.class);
                            intent.putExtra("dataChat", bundle);
                            startActivity(intent);

                        }
                    });
                }
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
    public void onStart() {
        super.onStart();
        if (mAuth.getUid() == null) {
            Toast.makeText(getActivity(), "Đăng nhập để thực hiện chức năng này", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), SignInSignUpActivity.class));
        }
    }

}
