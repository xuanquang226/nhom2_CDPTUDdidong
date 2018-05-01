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
import android.widget.ProgressBar;
import android.widget.Toast;

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
public class UserSignUpFragment extends Fragment {

    private EditText edtUser;
    private EditText edtPass;
    private EditText edtAddress;
    private EditText edtPhone;
    private Button btnOK;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    DatabaseReference root;


    public UserSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frm_user_sign_up_layout, container, false);
        edtUser = view.findViewById(R.id.edtEmail);
        edtPass = view.findViewById(R.id.edtPassword);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        mAuth = FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance().getReference("Infomation account");

        btnOK = view.findViewById(R.id.btnOKU);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress = new ProgressDialog(getActivity());
                mProgress.setTitle("Signing Up ....");
                mProgress.setMessage("Please wait");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();

                final String email = edtUser.getText().toString();
                final String password = edtPass.getText().toString();
                final String address = edtAddress.getText().toString();
                final String phone = edtPhone.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userID = mAuth.getCurrentUser().getUid();
                                    String accountType = "User";
                                    String linkhinh = "";
                                    DatabaseReference current_user_id = root.child(userID);
                                    UsersModel model = new UsersModel(email, password, address, phone,accountType,userID,linkhinh);
                                    current_user_id.setValue(model);
                                    Toast.makeText(getActivity(), "SignUp Successfully", Toast.LENGTH_SHORT).show();
                                    mProgress.dismiss();
                                    startActivity(new Intent(getActivity(), SignInActivity.class));
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
