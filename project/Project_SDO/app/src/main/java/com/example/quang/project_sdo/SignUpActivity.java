package com.example.quang.project_sdo;

import android.app.ProgressDialog;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {

    EditText edtUser;
    EditText edtPass;
    EditText edtRePass;
    EditText edtAddress;
    EditText edtPhone;
    EditText edtCMND;
    EditText edtDrugStore;
    EditText edtVehicle;
    EditText edtTheAirline;
    Spinner spinner;
    private ArrayList<String> arrayListSpinner;
    //private ArrayList<mdSpinnerPlace> arrSpinnerType;
    // Khoi tao cai vi tri
    int possition;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        //Ini
        spinner = (Spinner) findViewById(R.id.spinnerChoiceAccount);

        
        arrayListSpinner = new ArrayList<String>();
        arrayListSpinner.add("User");
        arrayListSpinner.add("Seller");
        arrayListSpinner.add("Shipper");
        arrayListSpinner.add("Carriers");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayListSpinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                possition = i;
                if (possition == 0) {
                    UserSignUpFragment fragment1 = new UserSignUpFragment(); // Gọi Fragment cần hiểu thị với tên fragment1
                    // Tạo ra fragmentTransaction
                    android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    // Replace nó :
                    // NOTE : R.id.flScreen với flScreen là tên id của của FrameLayout bên layout sign_ip_layout
                    fragmentTransaction1.replace(R.id.flScreen, fragment1, "Fragment");
                    fragmentTransaction1.commit();
                } else if (possition == 1) {
                    SellerSignUpFragment fragment2 = new SellerSignUpFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.flScreen, fragment2, "Fragment");
                    fragmentTransaction2.commit();
                } else if (possition == 2) {
                    ShipperSignUpFragment fragment3 = new ShipperSignUpFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.flScreen, fragment3, "Fragment");
                    fragmentTransaction3.commit();
                } else {
                    CarriersSignUpFragment fragment4 = new CarriersSignUpFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.flScreen, fragment4, "Fragment");
                    fragmentTransaction4.commit();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Users Info");

        //Process

    }


/*
    public void registerUser() {
        final String email = edtUser.getText().toString();
        final String password = edtPass.getText().toString();
        final String address = edtAddress.getText().toString();
        final String phone = edtPhone.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userID = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_id = root.child(userID);
                            UsersModel model = new UsersModel(email, password, address, phone);
                            current_user_id.setValue(model);
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

    }


    public void registerSeller() {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userID = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_id = root.child(userID);
                            SellerModel model = new SellerModel(email, password, address, phone, cmnd, drugstore);
                            current_user_id.setValue(model);
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
    }

    public void registerShipper() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userID = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_id = root.child(userID);
                            ShipperModel model = new ShipperModel(email, password, address, phone, cmnd, vehicle, theairline);
                            current_user_id.setValue(model);
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
    }
    */

}
