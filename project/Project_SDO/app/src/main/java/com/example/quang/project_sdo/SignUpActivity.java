package com.example.quang.project_sdo;

import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quang.project_sdo.Models.UsersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference root;
    String email,password,re_pass,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        //Ini

        edtUser = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPassword);
        Button btnOk = (Button) findViewById(R.id.btnOK);
        Button btnCancel = (Button) findViewById(R.id.btnCancelU);
        final EditText edtRePass = (EditText) findViewById(R.id.edtRePass);
        final EditText edtAddress = (EditText) findViewById(R.id.edtAddress);
        final EditText edtPhone = (EditText) findViewById(R.id.edtPhone);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerChoiceAccount);

        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Users Info");

        //Process
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress = new ProgressDialog(SignUpActivity.this);
                mProgress.setTitle("Signing Up ....");
                mProgress.setMessage("Please wait");
                mProgress.setCanceledOnTouchOutside(false);


                email = edtUser.getText().toString();
                password = edtPass.getText().toString();
                re_pass = edtRePass.getText().toString().trim();
                address = edtAddress.getText().toString().trim();
                phone = edtPhone.getText().toString().trim();

                mProgress.show();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    register();
                                    Toast.makeText(SignUpActivity.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                                    mProgress.dismiss();
                                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    mProgress.dismiss();

                                }
                            }
                        });
                edtUser.setText("");
                edtPass.setText("");
                edtRePass.setText("");
                edtAddress.setText("");
                edtPhone.setText("");
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

    public void register(){
        String userID = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_id = root.child(userID);
        UsersModel model = new UsersModel(email,password,address,phone);
        current_user_id.setValue(model);
    }

}
