package com.example.quang.project_sdo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quang.project_sdo.Models.ShipperModel;
import com.example.quang.project_sdo.Models.UsersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShipperSignUpFragment extends Fragment {
    private EditText editTextEmail;
    private EditText editTextPass;
    private EditText editTextRePass;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextCmnd;
    private EditText editTextVehicle;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    DatabaseReference root;

    public ShipperSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.frm_shipper_sign_up_layout, container, false);
        editTextEmail = view.findViewById(R.id.edtEmail);
        editTextPass = view.findViewById(R.id.edtPassword);
        editTextRePass = view.findViewById(R.id.edtRePass);
        editTextAddress = view.findViewById(R.id.edtAddress);
        editTextPhone = view.findViewById(R.id.edtPhone);
        editTextCmnd = view.findViewById(R.id.edtCMND);
        editTextVehicle = view.findViewById(R.id.edtVehidcle);
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Infomation account");

        Button btnOK = view.findViewById(R.id.btnOKS);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress = new ProgressDialog(getActivity());
                mProgress.setTitle("Signing Up ....");
                mProgress.setMessage("Please wait");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();

                final String email = editTextEmail.getText().toString();
                final String password = editTextPass.getText().toString();
                final String address = editTextAddress.getText().toString();
                final String phone = editTextPhone.getText().toString();
                final String cmnd = editTextCmnd.getText().toString();
                final String vehicle = editTextVehicle.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String accountType = "Shipper";
                                    String userID = mAuth.getCurrentUser().getUid();
                                    String linkhinh = "";
                                    DatabaseReference current_user_id = root.child(userID);
                                    ShipperModel model = new ShipperModel(email, password, address, phone,cmnd,vehicle,accountType,userID,linkhinh);
                                    current_user_id.setValue(model);
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
