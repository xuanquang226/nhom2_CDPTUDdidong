package com.example.quang.project_sdo;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatDetailActivity extends AppCompatActivity {
    ArrayList<ChatDetailModel> mess = new ArrayList<ChatDetailModel>();
    private String UserName = "";
    String avatar;
    EditText edtMess;

    private int nowItem = 10;
    private int morePerTime = 12;

    private FirebaseListAdapter<ChatDetailModel> adapter;
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    String idShop, idShopA, keyID, idUser, hinhUser, nameShop, nameShopB, userNameA, nameDefault;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail_layout);

        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();
        UserName = mAuth.getCurrentUser().getEmail();
        nameDefault = mAuth.getCurrentUser().getEmail();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null && getIntent().getBundleExtra("Data") != null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("Data");
            idShop = bundle.getString("idshop");
            nameShop = bundle.getString("tenshopA");
            Log.d("BBBB", nameShop + "");
        } else if (getIntent() != null && getIntent().getBundleExtra("dataChat") != null) {
            Intent intentA = getIntent();
            Bundle bundleA = intentA.getBundleExtra("dataChat");
            idUser = bundleA.getString("id");
            idShopA = bundleA.getString("idshopA");
            hinhUser = bundleA.getString("hinhanh");
            nameShopB = bundleA.getString("tenshopB");
            userNameA = bundleA.getString("tenuser");
        }


        edtMess = (EditText) findViewById(R.id.edtInputChat);
        final ImageButton btnSend = (ImageButton) findViewById(R.id.imgSend);
        final ListView lvChat = (ListView) findViewById(R.id.lvChat);
        final SwipeRefreshLayout pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshPull);


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
                keyID = FirebaseDatabase.getInstance().getReference().push().getKey();
                // set data
                getLinkImage();

                // hide keyboard
                InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });


    }

    private FirebaseListAdapter<ChatDetailModel> getMessage() {
        return new FirebaseListAdapter<ChatDetailModel>(ChatDetailActivity.this, ChatDetailModel.class, R.layout.chat_detail_custom_layout, FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectsdo-9812e.firebaseio.com/Info chat").orderByKey().limitToLast(nowItem)) {
            @Override
            protected void populateView(View v, final ChatDetailModel model, int position) {
                TextView lblName = (TextView) v.findViewById(R.id.lblName);
                TextView lblMess = (TextView) v.findViewById(R.id.lblMess);
                TextView lblDate = (TextView) v.findViewById(R.id.lblDate);
                ImageView imgReceiver = (ImageView) v.findViewById(R.id.imgReceiver);
                ImageView imgSender = (ImageView) v.findViewById(R.id.imgSender);
                LinearLayout llChatMess = (LinearLayout) v.findViewById(R.id.llChatMess);

                // check if this is a receiver or not
                if (!model.getNameDefault().equals(UserName)) {
                    model.setSender(false);
                }
                // set data
                if (idShop != null && mAuth.getUid().equalsIgnoreCase(model.getID()) && idShop.equalsIgnoreCase(model.getIdShop())) {
                    lblName.setText(model.getNameDefault());
                    lblMess.setText(model.getMessage());
                    lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                }
                if ((idShop == null) && ((mAuth.getUid().equalsIgnoreCase(idShopA) && idUser.equalsIgnoreCase(model.getID())) || (mAuth.getUid().equalsIgnoreCase(idUser) && idShopA.equalsIgnoreCase(model.getIdShop())))) {
                    lblName.setText(model.getNameDefault());
                    lblMess.setText(model.getMessage());
                    lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                }


                if (model.isSender()) {
                    // set visibility a
                    imgReceiver.setVisibility(View.INVISIBLE);
                    imgSender.setVisibility(View.VISIBLE);

                    // set default img
                    //imgSender.setImageResource(avatarID);

                    // set color
                    if (lblMess.getText().equals("")) {
                        for (position = 0; position < mess.size(); position++) {
                            lblMess.setVisibility(View.INVISIBLE);
                            lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_empty_style));
                            mess.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_sender_style));
                    }

                    // all on the right (sender)
                    llChatMess.setGravity(Gravity.END);
                } else {
                    // set visibility
                    imgReceiver.setVisibility(View.VISIBLE);
                    imgSender.setVisibility(View.INVISIBLE);

                    // set default img
                    //imgReceiver.setImageResource(avatarID);

                    // set color
                    if (lblMess.getText().equals("")) {
                        for (position = 0; position < mess.size(); position++) {
                            lblMess.setVisibility(View.INVISIBLE);
                            lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_empty_style));
                            mess.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_receiver_style));
                    }


                    //lblMess.setBackgroundColor(Color.parseColor("#95a5a6"));


                    // mess should be in the left (receiver)
                    llChatMess.setGravity(Gravity.START);
                }
            }
        };
    }


    public void getLinkImage() {
        root.child("Infomation account").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                avatar = dataSnapshot.child("linkhinh").getValue().toString();

                if (mAuth.getUid().equalsIgnoreCase(idUser)) {
                    FirebaseDatabase.getInstance().getReference("Info chat").child(keyID).setValue(new ChatDetailModel(UserName, edtMess.getText() + "", avatar, true, idShopA, mAuth.getUid(), nameShop, nameDefault));
                } else if (mAuth.getUid().equalsIgnoreCase(idShopA)) {
                    FirebaseDatabase.getInstance().getReference("Info chat").child(keyID).setValue(new ChatDetailModel(userNameA, edtMess.getText() + "", avatar, true, mAuth.getUid(), idUser, nameShopB, nameDefault));
                } else if (!mAuth.getUid().equalsIgnoreCase(idShop)) {
                    FirebaseDatabase.getInstance().getReference("Info chat").child(keyID).setValue(new ChatDetailModel(UserName, edtMess.getText() + "", avatar, true, idShop, mAuth.getUid(), nameShop, nameDefault));
                }

                // Clear the input
                edtMess.setText("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getUid().equalsIgnoreCase(idShop)) {
            onBackPressed();
            Toast.makeText(ChatDetailActivity.this, "Không thể tự chat với mình được", Toast.LENGTH_SHORT).show();
        }
    }
}
