package com.example.quang.project_sdo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Quang on 3/11/2018.
 */

public class AccountManagementFragment extends Fragment {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthLis;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.account_management_layout, container, false);
        //Ini
        mAuth = FirebaseAuth.getInstance();
        Button btnViewOrder = (Button) view.findViewById(R.id.btnOrder);
        Button btnShopping = (Button) view.findViewById(R.id.btnshopping);
        Button btnEditProfile = (Button) view.findViewById(R.id.btnEP);
        Button btnLogout = (Button) view.findViewById(R.id.btnLogOut);

        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(),SignInSignUpActivity.class));
                Toast.makeText(getActivity(),"Đã thoát",Toast.LENGTH_SHORT).show();
            }
        });

        mAuthLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() == null) {
                    Toast.makeText(getActivity(),"Đăng nhập để thực hiện chức năng này",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), SignInSignUpActivity.class));
                }
            }
        };


        return view;
    }
    //aaa



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthLis);
    }
}
