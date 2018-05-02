package com.example.quang.project_sdo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Quang on 3/11/2018.
 */

public class AccountManagementFragment extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference root;
    String userType;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.account_management_layout, container, false);
        //Ini
        mAuth = FirebaseAuth.getInstance();

        account();
        return view;
    }

    public void account() {
        root = FirebaseDatabase.getInstance().getReference("Infomation account").child(mAuth.getCurrentUser().getUid());
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userType = dataSnapshot.child("accountType").getValue().toString();
                if (userType.equalsIgnoreCase("User")) {
                    UserAccountFragment fragment1 = new UserAccountFragment();
                    FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.flAccount, fragment1);
                    fragmentTransaction1.commit();
                } else if (userType.equalsIgnoreCase("Seller")) {
                    SellerManagementFragment fragment2 = new SellerManagementFragment();
                    FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.flAccount, fragment2);
                    fragmentTransaction1.commit();
                } else if (userType.equalsIgnoreCase("Shipper")) {
                    ShipperFragment fragment3 = new ShipperFragment();
                    FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.flAccount, fragment3);
                    fragmentTransaction1.commit();
                } else if (userType.equalsIgnoreCase("Carrier")) {
                    CarrierFragment fragment4 = new CarrierFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.flAccount, fragment4);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(getActivity(), "Đăng nhập để thực hiện chức năng này", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), SignInSignUpActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
