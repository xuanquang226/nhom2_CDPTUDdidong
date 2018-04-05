package com.example.quang.project_sdo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.ListChatForUserModel;
import com.example.quang.project_sdo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trang on 4/5/2018.
 */

public class ChatForUserAdapter extends ArrayAdapter<ListChatForUserModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<ListChatForUserModel> chatUser;

    public ChatForUserAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ListChatForUserModel> objects) {
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
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layout,parent,false);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            viewHolder.ava = (ImageView) convertView.findViewById(R.id.imgAvaChat);
            viewHolder.txtNameChat = (TextView) convertView.findViewById(R.id.txtNameChat);
            viewHolder.txtRecentChat = (TextView) convertView.findViewById(R.id.txtChatRecent);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ava.setImageResource(chatUser.get(position).getAvatarShop());
        viewHolder.txtNameChat.setText(chatUser.get(position).getNameShop());
        viewHolder.txtRecentChat.setText(chatUser.get(position).getRecentChatS());
        return convertView;
    }
}
