package com.example.quang.project_sdo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quang.project_sdo.Models.CarrierModel;
import com.example.quang.project_sdo.Models.NameCarrierModel;
import com.example.quang.project_sdo.Models.SellerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarriersSignUpFragment extends Fragment {

    private EditText edtUser;
    private EditText edtPass;
    private EditText editTextRePass;
    private EditText edtAddress;
    private EditText edtPhone;
    private EditText edtCMND;
    private EditText edtNameCarrier;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    DatabaseReference root,root2;

    public CarriersSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_carriers_sign_up_layout, container, false);
        edtUser = view.findViewById(R.id.edtEmail);
        edtPass = view.findViewById(R.id.edtPassword);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtCMND = view.findViewById(R.id.edtCMND);
        edtNameCarrier = view.findViewById(R.id.edtCarrier);
        mAuth = FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance().getReference("Infomation account");

        root2 = FirebaseDatabase.getInstance().getReference("Carrier");
        Button btnOK = view.findViewById(R.id.btnOKCa);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress = new ProgressDialog(getActivity());
                mProgress.setTitle("Signing Up ....");
                mProgress.setMessage("Please wait");
                mProgress.setCancelable(false);
                mProgress.show();

                final String email = edtUser.getText().toString();
                final String password = edtPass.getText().toString();
                final String address = edtAddress.getText().toString();
                final String phone = edtPhone.getText().toString();
                final String cmnd = edtCMND.getText().toString();
                final String nameCarrier = edtNameCarrier.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String accountType = "Carrier";
                                    String userID = mAuth.getCurrentUser().getUid();
                                    String linkhinh = "";
                                    root.child(mAuth.getUid()).setValue(new CarrierModel(email, password, address, phone, cmnd, nameCarrier, accountType,userID,linkhinh));
                                    root2.push().setValue(new NameCarrierModel(nameCarrier));
                                    Toast.makeText(getActivity(), "SignUp Successfully", Toast.LENGTH_SHORT).show();
                                    mProgress.dismiss();
                                    startActivity(new Intent(getActivity(), BackStackActivity.class));
                                } else {
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    mProgress.dismiss();

                                }
                            }
                        });
            }
        });
        return view;
    }

}
