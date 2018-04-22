package com.example.quang.project_sdo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShipperFragment extends Fragment {
    FirebaseAuth mAuth;


    public ShipperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = super.onCreateView(inflater, container, savedInstanceState);
        view =  inflater.inflate(R.layout.fragment_shipper_layout, container, false);
        mAuth = FirebaseAuth.getInstance();

        Button btn_deliverySchedule = (Button) view.findViewById(R.id.btn_deliverySchedule);
        btn_deliverySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShipperDeliverySchedule.class);
                startActivity(intent);
            }
        });

        Button btnSignOutt = (Button) view.findViewById(R.id.btnLogOut);
        btnSignOutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(),SignInSignUpActivity.class));
            }
        });
        return view;


    }
}