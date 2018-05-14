package com.example.quang.project_sdo;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.CarrierModel;
import com.example.quang.project_sdo.Models.ShipperModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarrierFragment extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference root, rootB;
    FirebaseStorage storage;
    StorageReference mountainImagesRef;
    TextView txtUserName, txtSDT, txtAddress,txtNameC;
    TextView txtUserNameD, txtSDTD, txtAddressD,txtNameCD;
    ImageView imgUser,imgUserD;
    Button btnOk,btnCancel;
    Dialog dialoga;
    Uri downloadUrl;
    Uri imageUri;
    public CarrierFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrier_layout, container, false);

        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Infomation account");
        storage = FirebaseStorage.getInstance();
        mountainImagesRef = storage.getReferenceFromUrl(
                "gs://projectsdo-9812e.appspot.com");



        txtUserName = (TextView) view.findViewById(R.id.txtnameCarrier);
        txtSDT = (TextView) view.findViewById(R.id.txtphoneCarrier);
        txtNameC = (TextView) view.findViewById(R.id.txtNameC);
        txtAddress = (TextView) view.findViewById(R.id.txtAddressC);
        imgUser = (ImageView) view.findViewById(R.id.imgCarrier);




        Button btnLogout= (Button) view.findViewById(R.id.btnLogOut);
        Button btnDeliverySchedule = (Button) view.findViewById(R.id.btn_deliverySchedule);
        Button btnEdit = (Button) view.findViewById(R.id.btn_profileCarrier);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoga = new Dialog(getActivity());
                dialoga.setContentView(R.layout.dialog_edit_profile_seller_layout);
                dialoga.setTitle("Edit profile");

                txtUserNameD = (TextView) dialoga.findViewById(R.id.txtusernameD);
                txtUserNameD.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS | InputType.TYPE_CLASS_TEXT);
                txtSDTD = (TextView) dialoga.findViewById(R.id.txtsdtD);
                txtSDTD.setInputType(InputType.TYPE_CLASS_PHONE);
                txtAddressD = (TextView) dialoga.findViewById(R.id.txtaddressD);
                txtNameCD = (TextView) dialoga.findViewById(R.id.txtCMNDD);
                btnOk = (Button) dialoga.findViewById(R.id.btnConfirmEdit);
                btnCancel = (Button) dialoga.findViewById(R.id.btnCancelEdit);
                imgUserD = (ImageView) dialoga.findViewById(R.id.imguserD);
                imgUserD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.dialog_image_layout);
                        dialog.setTitle("Choose");
                        dialog.setCancelable(false);

                        Button btnOpen = (Button) dialog.findViewById(R.id.openGallery);
                        Button btnTakeP = (Button) dialog.findViewById(R.id.takePhoto);

                        btnOpen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                startActivityForResult(gallery, 9);
                                dialog.dismiss();
                            }
                        });

                        btnTakeP.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 10);
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                });
                loadDataD();
                dialoga.show();

            }
        });

        btnDeliverySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CarrierDeliverySchedule.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(),SignInSignUpActivity.class));
            }
        });

        loadData();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9 && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imgUserD.setImageURI(imageUri);
        }
        if (requestCode == 10 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgUserD.setImageBitmap(bitmap);

        }


    }

    public void loadData() {
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CarrierModel Data = dataSnapshot.getValue(CarrierModel.class);
                if (Data.id.equalsIgnoreCase(mAuth.getUid())) {
                    txtUserName.setText(Data.email);
                    txtSDT.setText(Data.phone);
                    txtAddress.setText(Data.address);
                    txtNameC.setText(Data.nameCarrier);
                    if(Data.linkhinh.equalsIgnoreCase("")){

                    }else {
                        Picasso.get().load(Data.linkhinh).into(imgUser);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void loadDataD(){
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CarrierModel data = dataSnapshot.getValue(CarrierModel.class);
                if (data.id.equalsIgnoreCase(mAuth.getUid())) {
                    txtUserNameD.setText(data.email);
                    txtSDTD.setText(data.phone);
                    txtAddressD.setText(data.address);
                    txtNameCD.setText(data.nameCarrier);
                    if(data.linkhinh.equalsIgnoreCase("")){

                    }else {
                        Picasso.get().load(data.linkhinh).into(imgUserD);
                    }
                }
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final DatabaseReference rootC = FirebaseDatabase.getInstance().getReference();
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("email").setValue(txtUserNameD.getText().toString());
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("phone").setValue(txtSDTD.getText().toString());
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("address").setValue(txtAddressD.getText().toString());
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("nameCarrier").setValue(txtNameCD.getText().toString());
                        //Process for image
                        Calendar calendar = Calendar.getInstance();
                        StorageReference mountainsRef = mountainImagesRef.child("image" + calendar.getTimeInMillis() + ".png");

                        imgUserD.setDrawingCacheEnabled(true);
                        imgUserD.buildDrawingCache();
                        Bitmap bitmap = imgUserD.getDrawingCache();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] dataA = baos.toByteArray();

                        UploadTask uploadTask = mountainsRef.putBytes(dataA);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                downloadUrl = taskSnapshot.getDownloadUrl();
                                rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("linkhinh").setValue(downloadUrl + "");
                            }
                        });
                        dialoga.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialoga.dismiss();
                    }
                });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
