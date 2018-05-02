package com.example.quang.project_sdo.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.ChatDetailActivity;
import com.example.quang.project_sdo.Models.ListChatModel;
import com.example.quang.project_sdo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Trang on 4/5/2018.
 */

public class ChatAdapter extends ArrayAdapter<ListChatModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<ListChatModel> chatUser;
    private FirebaseAuth mAuth;
    DatabaseReference root;
    private ArrayList<ListChatModel> chatModels = new ArrayList<ListChatModel>();

    public ChatAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ListChatModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.chatUser = objects;
    }
    public class ViewHolder{
        ImageView ava;
        TextView txtNameChat;
        TextView txtRecentChat;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();

        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout,parent,false);
            viewHolder.ava = (ImageView) convertView.findViewById(R.id.imgAvaChat);
            viewHolder.txtNameChat = (TextView) convertView.findViewById(R.id.txtNameChat);
            viewHolder.txtRecentChat = (TextView) convertView.findViewById(R.id.txtChatRecent);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        root.child("Info chat").limitToLast(2).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final ListChatModel chatModel = dataSnapshot.getValue(ListChatModel.class);
                chatModels.add(new ListChatModel(chatModel.name, chatModel.message, chatModel.avatar, chatModel.id, chatModel.idShop,chatModel.nameShop));

                if(mAuth.getCurrentUser().getUid().equalsIgnoreCase(chatModel.idShop)){
                    Picasso.get().load(chatUser.get(position).getAvatar()).into(viewHolder.ava);
                    viewHolder.txtNameChat.setText(chatUser.get(position).getName());
                    viewHolder.txtRecentChat.setText(chatUser.get(position).getMessage());
                }else if(mAuth.getCurrentUser().getUid().equalsIgnoreCase(chatModel.id)){
                    Picasso.get().load(chatUser.get(position).getAvatar()).into(viewHolder.ava);
                    viewHolder.txtNameChat.setText(chatUser.get(position).getNameShop());
                    viewHolder.txtRecentChat.setText(chatUser.get(position).getMessage());
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
        return convertView;
    }
}
