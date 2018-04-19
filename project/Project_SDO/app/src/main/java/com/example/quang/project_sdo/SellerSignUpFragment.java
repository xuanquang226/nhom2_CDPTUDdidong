package com.example.quang.project_sdo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellerSignUpFragment extends Fragment {

    private EditText editTextEmail;
    private EditText editTextPass;
    private EditText editTextRePass;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextCmnd;
    private EditText editTextDrugStore;

    public SellerSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frm_seller_sign_up_layout, container, false);

        editTextEmail = view.findViewById(R.id.edtEmail);
        editTextPass = view.findViewById(R.id.edtPassword);
        editTextRePass = view.findViewById(R.id.edtRePass);
        editTextAddress = view.findViewById(R.id.edtAddress);
        editTextPhone = view.findViewById(R.id.edtPhone);
        editTextCmnd = view.findViewById(R.id.edtCMND);
        editTextDrugStore = view.findViewById(R.id.edtDrugStore);
        return view;
    }

}
