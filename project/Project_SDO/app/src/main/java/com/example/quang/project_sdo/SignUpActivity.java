package com.example.quang.project_sdo;

import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    EditText edtUser;
    EditText edtPass;
    EditText edtRePass;
    EditText edtAddress;
    EditText edtPhone;

    private ProgressDialog mProgress;

    private FirebaseAuth mAuth;
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        //Ini

        edtUser = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPassword);
        Button btnOk = (Button) findViewById(R.id.btnOK);
        Button btnCancel = (Button) findViewById(R.id.btnCancelU);
        edtRePass = (EditText) findViewById(R.id.edtRePass);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerChoiceAccount);

        mAuth = FirebaseAuth.getInstance();

        //Process
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress = new ProgressDialog(SignUpActivity.this);
                mProgress.setTitle("Signing Up ....");
                mProgress.setMessage("Please wait");

                final String email = edtUser.getText().toString().trim();
                final String pass = edtPass.getText().toString().trim();
                final String re_pass = edtRePass.getText().toString().trim();
                final String address = edtAddress.getText().toString().trim();
                final String phone = edtPhone.getText().toString().trim();
                root = FirebaseDatabase.getInstance().getReference().child("Users");

                if (TextUtils.isEmpty(email)) {
                    edtUser.setError("Do not leave this field blank");
                    return;
                } else if (TextUtils.isEmpty(pass)) {
                    edtPass.setError("Do not leave this field blank");
                    return;
                } else if (pass.length() < 4 || pass.length() > 30) {
                    edtPass.setError("Invalid Number Character");
                    return;
                } else if (TextUtils.isEmpty(re_pass)) {
                    edtRePass.setError("Do not leave this field blank");
                    return;
                } else if (pass.length() < 4 || pass.length() > 30) {
                    edtRePass.setError("Invalid Number Character");
                    return;
                } else if (TextUtils.isEmpty(address)) {
                    edtAddress.setError("Do not leave this field blank");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    edtPhone.setError("Do not leave this field blank");
                    return;
                } else {
                    mProgress.show();
                    //Create user
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                mProgress.dismiss();
                                Toast.makeText(SignUpActivity.this, "Sign Up Failed",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                                mProgress.dismiss();
                                String userID = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_id = root.child(userID);
                                current_user_id.child("name").setValue(email);
                                current_user_id.child("password").setValue(pass);
                                current_user_id.child("re_password").setValue(re_pass);
                                current_user_id.child("address").setValue(address);
                                current_user_id.child("phone").setValue(phone);


                            }
                        }
                    });
                }
                edtUser.setText("");
                edtPass.setText("");
                edtRePass.setText("");
                edtAddress.setText("");
                edtPhone.setText("");
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInSignUpActivity.class);
                startActivity(intent);
            }
        });
    }


}
