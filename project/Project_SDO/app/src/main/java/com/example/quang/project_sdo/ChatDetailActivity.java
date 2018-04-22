package com.example.quang.project_sdo;

import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.project_sdo.Models.ChatDetailModel;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatDetailActivity extends AppCompatActivity {
    ArrayList<ChatDetailModel> mess = new ArrayList<>();
    private String UserName = "";
    private int Avatar = 1;

    private int nowItem = 10;
    private int morePerTime = 12;

    private FirebaseListAdapter<ChatDetailModel> adapter;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail_layout);

        mAuth = FirebaseAuth.getInstance();

        UserName = mAuth.getCurrentUser().getEmail();
        Toast.makeText(this, UserName, Toast.LENGTH_LONG).show();

        final EditText edtMess = (EditText) findViewById(R.id.edtInputChat);
        final ImageButton btnSend = (ImageButton) findViewById(R.id.imgSend);
        final ListView lvChat = (ListView) findViewById(R.id.lvChat);
        final SwipeRefreshLayout pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshPull);
        Avatar = 1;


        adapter = getMessage();
        lvChat.setAdapter(adapter);


        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // get now scroll
                int scrollX = lvChat.getScrollX();
                int scrollY = lvChat.getScrollY();
                // increase item to get
                nowItem = nowItem + morePerTime;

                // set again data
                adapter = getMessage();

                // set item again
                lvChat.setAdapter(adapter);

                // scroll
                lvChat.scrollTo(scrollX, scrollY);

                // stop refreshing
                pullToRefresh.setRefreshing(false);
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // created first to get keyID
                String keyID = FirebaseDatabase.getInstance().getReference().push().getKey();
                // set data
                FirebaseDatabase.getInstance().getReference("Info chat").child(keyID).setValue(new ChatDetailModel(UserName, edtMess.getText().toString(), Avatar, true));

                // Clear the input
                edtMess.setText("");

                // hide keyboard
                InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });


    }

    private FirebaseListAdapter<ChatDetailModel> getMessage() {
        return new FirebaseListAdapter<ChatDetailModel>(ChatDetailActivity.this, ChatDetailModel.class, R.layout.chat_detail_custom_layout, FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectsdo-9812e.firebaseio.com/Info chat").orderByKey().limitToLast(nowItem)){
            @Override
            protected void populateView(View v, ChatDetailModel model, int position) {
                TextView lblName = (TextView) v.findViewById(R.id.lblName);
                TextView lblMess = (TextView) v.findViewById(R.id.lblMess);
                TextView lblDate = (TextView) v.findViewById(R.id.lblDate);
                ImageView imgReceiver = (ImageView) v.findViewById(R.id.imgReceiver);
                ImageView imgSender = (ImageView) v.findViewById(R.id.imgSender);
                LinearLayout llChatMess = (LinearLayout) v.findViewById(R.id.llChatMess);

                // check if this is a receiver or not
                if (model.getName().equals(UserName) == false) {
                    model.setSender(false);
                }

                @DrawableRes int avatarID = getAvatar(model.getAvatar());


                // set data
                lblName.setText(model.getName());
                lblMess.setText(model.getMessage());
                lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));

                if (model.isSender()) {
                    // set visibility
                    imgReceiver.setVisibility(View.INVISIBLE);
                    imgSender.setVisibility(View.VISIBLE);

                    // set default img
                    imgSender.setImageResource(avatarID);

                    // set color
                    //lblMess.setBackgroundColor(Color.parseColor("#3498db"));
                    lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_sender_style));

                    // all on the right (sender)
                    llChatMess.setGravity(Gravity.END);
                } else {
                    // set visibility
                    imgReceiver.setVisibility(View.VISIBLE);
                    imgSender.setVisibility(View.INVISIBLE);

                    // set default img
                    imgReceiver.setImageResource(avatarID);

                    // set color
                    //lblMess.setBackgroundColor(Color.parseColor("#95a5a6"));
                    lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_receiver_style));

                    // mess should be in the left (receiver)
                    llChatMess.setGravity(Gravity.START);
                }
            }
        };
    }

    private @DrawableRes
    int getAvatar(int type) {
        switch (type) {
            case 1:
                return R.drawable.userchat;
            default:
                return R.drawable.userchata;
        }
    }

}
