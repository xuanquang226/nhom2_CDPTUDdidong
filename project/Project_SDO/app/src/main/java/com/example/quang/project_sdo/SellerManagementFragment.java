package com.example.quang.project_sdo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;


public class SellerManagementFragment extends Fragment {
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.seller_management_layout,container,false);
        //Ini
        mAuth = FirebaseAuth.getInstance();
        Button btnAdddrugs = (Button) view.findViewById(R.id.btnadddrugs);
        Button btnViewStatistics = (Button) view.findViewById(R.id.btnviewStatistics);
        Button btnEditprofile = (Button) view.findViewById(R.id.btnprofile);
        Button btnLogout = (Button) view.findViewById(R.id.btnlayout);




        //Process
        btnAdddrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnteringDrugActivity.class);
                startActivity(intent);
            }
        });

        btnViewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SalesStatisticsActivity.class);
                startActivity(intent);
            }
        });

        btnEditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ArcticleDetailActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignInSignUpActivity.class);
                startActivity(intent);
                mAuth.signOut();
            }
        });
        return view;
    }
}
