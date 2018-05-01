package com.example.quang.project_sdo.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.ListChatModel;
import com.example.quang.project_sdo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Trang on 4/5/2018.
 */

public class ChatAdapter extends ArrayAdapter<ListChatModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<ListChatModel> chatUser;

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
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
        Picasso.get().load(chatUser.get(position).getAvatar()).into(viewHolder.ava);
        viewHolder.txtNameChat.setText(chatUser.get(position).getName());
        viewHolder.txtRecentChat.setText(chatUser.get(position).getMessage());
        return convertView;
    }
}
