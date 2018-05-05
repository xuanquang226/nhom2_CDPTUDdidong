package com.example.quang.project_sdo;

import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatDetailActivity extends AppCompatActivity {
    ArrayList<ChatDetailModel> chatDetailModels = new ArrayList<ChatDetailModel>();
    private String UserName = "";
    String imgUser, imgSeller, idAccountUser, idAccountSeller;
    EditText edtMess;

    private int nowItem = 6;
    private int morePerTime = 3;

    private FirebaseListAdapter<ChatDetailModel> adapter;
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    String keyID;
    String getIDSeller, getIDSellerI, getIDSellerA;
    String getIDUser, getIDUserA;
    String nameUser, nameUserA, nameSeller, nameSellerA;
    String nameSellerI;
    String chatUser = "";
    String chatSeller = "";
    String imgUserA, imgSellerA;
    TextView lblMess;
    LinearLayout llChatMess;
    ImageView imgSender;
    ImageView imgReceiver;
    SwipeRefreshLayout pullToRefresh;
    ListView lvChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail_layout);

        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();
        UserName = mAuth.getCurrentUser().getEmail();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        edtMess = (EditText) findViewById(R.id.edtInputChat);
        final ImageButton btnSend = (ImageButton) findViewById(R.id.imgSend);
        lvChat = (ListView) findViewById(R.id.lvChat);
        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshPull);


        //Get data from intent

        getDataForNameSellerI();
        getDataForNameSellerA();

        adapter = getMessage();
        lvChat.setAdapter(adapter);
        loadItem();

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

                //Load item
                loadItem();
            }
        });


    }

    private FirebaseListAdapter<ChatDetailModel> getMessage() {
        return new FirebaseListAdapter<ChatDetailModel>(ChatDetailActivity.this, ChatDetailModel.class, R.layout.chat_detail_custom_layout, FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectsdo-9812e.firebaseio.com/Info chat").orderByKey().limitToLast(nowItem)) {
            @Override
            protected void populateView(View v, final ChatDetailModel model, int position) {
                TextView lblName = (TextView) v.findViewById(R.id.lblName);
                lblMess = (TextView) v.findViewById(R.id.lblMess);
                TextView lblDate = (TextView) v.findViewById(R.id.lblDate);
                imgReceiver = (ImageView) v.findViewById(R.id.imgReceiver);
                imgSender = (ImageView) v.findViewById(R.id.imgSender);
                llChatMess = (LinearLayout) v.findViewById(R.id.llChatMess);

                // check if this is a receiver or not

                // set data

                if (getIDSellerI != null) {
                    if ((mAuth.getUid().equalsIgnoreCase(model.getIdUser()) && (getIDSellerI.equalsIgnoreCase(model.getIdSeller())))) {
                        if (model.getNameUser().equalsIgnoreCase(UserName) && !model.getChatUser().equalsIgnoreCase("")) {
                            model.setSender(true);
                            lblName.setText(model.getNameUser());
                            lblMess.setText(model.getChatUser());
                            lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                            Picasso.get().load(model.getImgUser()).into(imgSender);
                        } else if (model.getNameUser().equalsIgnoreCase(UserName) && model.getChatUser().equalsIgnoreCase("")) {
                            model.setSender(false);
                            lblName.setText(model.getNameSeller());
                            lblMess.setText(model.getChatSeller());
                            lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                            Picasso.get().load(model.getImgSeller()).into(imgReceiver);
                        }
                    } else if ((mAuth.getUid().equalsIgnoreCase(model.getIdSeller()) && (getIDUser.equalsIgnoreCase(model.getIdUser())))) {
                        if (model.getNameSeller().equalsIgnoreCase(UserName) && !model.getChatSeller().equalsIgnoreCase("")) {
                            {
                                model.setSender(true);
                                lblName.setText(model.getNameSeller());
                                lblMess.setText(model.getChatSeller());
                                lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                                Picasso.get().load(model.getImgSeller()).into(imgSender);
                            }
                        } else if (model.getNameSeller().equalsIgnoreCase(UserName) && model.getChatUser().equalsIgnoreCase("")) {
                            model.setSender(false);
                            lblName.setText(model.getNameUser());
                            lblMess.setText(model.getChatUser());
                            lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                            Picasso.get().load(model.getImgUser()).into(imgReceiver);
                        }
                    }
                } else {
                    if ((mAuth.getUid().equalsIgnoreCase(model.getIdUser()) && (getIDSellerA.equalsIgnoreCase(model.getIdSeller())))) {
                        if (model.getNameUser().equalsIgnoreCase(UserName) && !model.getChatUser().equalsIgnoreCase("")) {
                            model.setSender(true);
                            lblName.setText(model.getNameUser());
                            lblMess.setText(model.getChatUser());
                            lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                            Picasso.get().load(model.getImgUser()).into(imgSender);
                        } else if (model.getNameUser().equalsIgnoreCase(UserName) && model.getChatUser().equalsIgnoreCase("")) {
                            model.setSender(false);
                            lblName.setText(model.getNameSeller());
                            lblMess.setText(model.getChatSeller());
                            lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                            Picasso.get().load(model.getImgSeller()).into(imgReceiver);
                        }
                    } else if ((mAuth.getUid().equalsIgnoreCase(model.getIdSeller()) && (getIDUserA.equalsIgnoreCase(model.getIdUser())))) {
                        if (model.getNameSeller().equalsIgnoreCase(UserName) && !model.getChatSeller().equalsIgnoreCase("")) {
                            {
                                model.setSender(true);
                                lblName.setText(model.getNameSeller());
                                lblMess.setText(model.getChatSeller());
                                lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                                Picasso.get().load(model.getImgSeller()).into(imgSender);
                            }
                        } else if (model.getNameSeller().equalsIgnoreCase(UserName) && model.getChatSeller().equalsIgnoreCase("")) {
                            model.setSender(false);
                            lblName.setText(model.getNameUser());
                            lblMess.setText(model.getChatUser());
                            lblDate.setText(DateFormat.format("dd/MM/yy hh:mm", model.getCreatedDate()));
                            Picasso.get().load(model.getImgUser()).into(imgReceiver);
                        }
                    }
                }

                if (model.isSender())

                {
                    // set visibility n
                    imgReceiver.setVisibility(View.INVISIBLE);
                    imgSender.setVisibility(View.VISIBLE);

                    // set default img
                    //imgSender.setImageResource(avatarID);

                    // set color

                    if (lblMess.getText().equals("")) {
                        lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_empty_style));
                        llChatMess.setVisibility(View.GONE);
                        imgSender.setVisibility(View.GONE);
                        imgReceiver.setVisibility(View.GONE);
                    } else {
                        lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_sender_style));
                    }


                    // all on the right (sender)
                    llChatMess.setGravity(Gravity.END);
                } else

                {
                    // set visibility
                    imgReceiver.setVisibility(View.VISIBLE);
                    imgSender.setVisibility(View.INVISIBLE);

                    // set default img
                    //imgReceiver.setImageResource(avatarID);

                    // set color

                    if (lblMess.getText().equals("")) {
                        lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_empty_style));
                        llChatMess.setVisibility(View.GONE);
                        imgSender.setVisibility(View.GONE);
                        imgReceiver.setVisibility(View.GONE);
                    } else {
                        lblMess.setBackground(ContextCompat.getDrawable(ChatDetailActivity.this, R.drawable.chat_receiver_style));
                    }


                    //lblMess.setBackgroundColor(Color.parseColor("#95a5a6"));


                    // mess should be in the left (receiver)
                    llChatMess.setGravity(Gravity.START);
                }
                // }

            }

        };
    }

    public void getLinkImage() {
        root.child("Infomation account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Get image and id account
                if (getIDSellerI != null) {
                    idAccountUser = dataSnapshot.child(mAuth.getUid()).child("id").getValue().toString();
                    idAccountSeller = dataSnapshot.child(getIDSellerI).child("id").getValue().toString();

                    if (mAuth.getUid().equalsIgnoreCase(idAccountUser)) {
                        imgUser = dataSnapshot.child(idAccountUser).child("linkhinh").getValue().toString();
                        getIDUser = dataSnapshot.child(idAccountUser).child("id").getValue().toString();
                    }
                    if (getIDSellerI.equalsIgnoreCase(idAccountSeller)) {
                        imgSeller = dataSnapshot.child(idAccountSeller).child("linkhinh").getValue().toString();
                        getIDSeller = dataSnapshot.child(idAccountSeller).child("id").getValue().toString();
                    }

                    //Get nameUser and nameSeller;
                    if (nameSellerI != null) {
                        if (mAuth.getUid().equalsIgnoreCase(getIDUser)) {
                            nameUser = mAuth.getCurrentUser().getEmail();
                        } else if (mAuth.getUid().equalsIgnoreCase(getIDSeller)) {
                            nameSeller = mAuth.getCurrentUser().getEmail();
                        }
                    }


                } else {
                    idAccountUser = dataSnapshot.child(mAuth.getUid()).child("id").getValue().toString();
                    idAccountSeller = dataSnapshot.child(getIDSellerA).child("id").getValue().toString();


                    if (mAuth.getUid().equalsIgnoreCase(idAccountUser)) {
                        imgUser = dataSnapshot.child(idAccountUser).child("linkhinh").getValue().toString();
                        getIDUser = dataSnapshot.child(idAccountUser).child("id").getValue().toString();
                    }
                    if (getIDSellerA.equalsIgnoreCase(idAccountSeller)) {
                        imgSeller = dataSnapshot.child(idAccountSeller).child("linkhinh").getValue().toString();
                        getIDSeller = dataSnapshot.child(idAccountSeller).child("id").getValue().toString();
                    }


                    //Get nameUser and nameSeller;
                    if (nameSellerA != null) {
                        if (mAuth.getUid().equalsIgnoreCase(getIDUser)) {
                            nameUser = mAuth.getCurrentUser().getEmail();
                        } else if (mAuth.getUid().equalsIgnoreCase(getIDSeller)) {
                            nameSeller = mAuth.getCurrentUser().getEmail();
                        }
                    }

                }


                //Send data to server
                if (nameSellerI != null) {
                    FirebaseDatabase.getInstance().getReference("Info chat").child(keyID).setValue(new ChatDetailModel(getIDUser, getIDSeller, imgUser, imgSeller, edtMess.getText().toString(), chatSeller, true, nameUser, nameSellerI));
                } else {
                    if (mAuth.getUid().equalsIgnoreCase(getIDUserA)) {
                        FirebaseDatabase.getInstance().getReference("Info chat").child(keyID).setValue(new ChatDetailModel(getIDUserA, getIDSellerA, imgUserA, imgUserA, edtMess.getText().toString(), "", true, nameUserA, nameSellerA));
                    } else if (mAuth.getUid().equalsIgnoreCase(getIDSellerA)) {
                        FirebaseDatabase.getInstance().getReference("Info chat").child(keyID).setValue(new ChatDetailModel(getIDUserA, getIDSellerA, imgUserA, imgSellerA, "", edtMess.getText().toString(), true, nameUserA, nameSellerA));
                    }
                }


                // Clear the input
                edtMess.setText("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getDataForNameSellerI() {
        if (getIntent() != null && getIntent().getBundleExtra("Data") != null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("Data");
            nameSellerI = bundle.getString("tenshopA");
            getIDSellerI = bundle.getString("idshop");
        }
    }

    public void getDataForNameSellerA() {
        if (getIntent() != null && getIntent().getBundleExtra("dataChat") != null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("dataChat");
            nameSellerA = bundle.getString("nameSeller");
            nameUserA = bundle.getString("nameUser");
            getIDSellerA = bundle.getString("idSeller");
            getIDUserA = bundle.getString("idUser");
            imgUserA = bundle.getString("imgUser");
            imgSellerA = bundle.getString("imgSeller");
        }
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
    public void loadItem(){
        int scrollX = lvChat.getScrollX();
        int scrollY = lvChat.getScrollY();

        // set again data
        adapter = getMessage();

        // set item again
        lvChat.setAdapter(adapter);

        // scroll
        lvChat.scrollTo(scrollX, scrollY);

        // stop refreshing
        pullToRefresh.setRefreshing(false);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getUid().equalsIgnoreCase(getIDSeller)) {
            onBackPressed();
            Toast.makeText(ChatDetailActivity.this, "Không thể tự chat với mình được", Toast.LENGTH_SHORT).show();
        }
    }
}