package com.example.quang.project_sdo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edtUsername;
    EditText edtPassword;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);

        //Ini
        mAuth = FirebaseAuth.getInstance();
        edtUsername = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPass);
        Button btnSignIn = (Button) findViewById(R.id.btnSignInP);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        mProgress = new ProgressDialog(SignInActivity.this);
        mProgress.setTitle("Signing....");
        mProgress.setMessage("Waiting for sign in success");
        mProgress.setCancelable(false);


        //Process
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.show();
                String email = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(SignInActivity.this, "Sign In Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignInActivity.this, BackStackActivity.class);
                                    startActivity(intent);
                                    mProgress.dismiss();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    String TAG = SignInActivity.class.getSimpleName();
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(SignInActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    mProgress.dismiss();
                                }
                            }
                        });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,SignInSignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
