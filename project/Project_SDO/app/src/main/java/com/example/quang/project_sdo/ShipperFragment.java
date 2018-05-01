package com.example.quang.project_sdo;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.SellerModel;
import com.example.quang.project_sdo.Models.ShipperModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShipperFragment extends Fragment {
    FirebaseStorage storage;
    StorageReference mountainImagesRef;
    FirebaseAuth mAuth;
    DatabaseReference root;
    TextView txtUserName, txtSDT, txtAddress,txtCodeB;
    TextView txtUserNameD, txtSDTD, txtAddressD,txtCodeBD;
    ImageView imgUser;
    Button btnOk,btnCancel;
    Dialog dialoga;
    Uri downloadUrl;
    Uri imageUri;

    public ShipperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view =  inflater.inflate(R.layout.fragment_shipper_layout, container, false);
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference("Infomation account");
        storage = FirebaseStorage.getInstance();
        mountainImagesRef = storage.getReferenceFromUrl(
                "gs://projectsdo-9812e.appspot.com");



        txtUserName = (TextView) view.findViewById(R.id.txtnameShipper);
        txtSDT = (TextView) view.findViewById(R.id.txtphoneShipper);
        txtCodeB = (TextView) view.findViewById(R.id.codebike);
        txtAddress = (TextView) view.findViewById(R.id.txtshipperAddress);
        imgUser = (ImageView) view.findViewById(R.id.imgshipper);





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

        Button btnEdit = (Button) view.findViewById(R.id.btn_profileShipper);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoga = new Dialog(getActivity());
                dialoga.setContentView(R.layout.dialog_edit_profile_seller_layout);
                dialoga.setTitle("Edit profile");

                txtUserNameD = (TextView) dialoga.findViewById(R.id.txtusernameD);
                txtSDTD = (TextView) dialoga.findViewById(R.id.txtsdtD);
                txtAddressD = (TextView) dialoga.findViewById(R.id.txtaddressD);
                txtCodeBD = (TextView) dialoga.findViewById(R.id.txtCMNDD);
                btnOk = (Button) dialoga.findViewById(R.id.btnConfirmEdit);
                btnCancel = (Button) dialoga.findViewById(R.id.btnCancelEdit);


                loadDataD();
                dialoga.show();
            }
        });
        loadData();


        imgUser.setOnClickListener(new View.OnClickListener() {
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
                        startActivityForResult(gallery, 7);
                        dialog.dismiss();
                    }
                });

                btnTakeP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 8);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imgUser.setImageURI(imageUri);
        }
        if (requestCode == 8 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgUser.setImageBitmap(bitmap);

        }
         /*
                //Process for image
                Calendar calendar = Calendar.getInstance();
                StorageReference mountainsRef = mountainImagesRef.child("image" + calendar.getTimeInMillis() + ".png");

                imgUser.setDrawingCacheEnabled(true);
                imgUser.buildDrawingCache();
                Bitmap bitmap = imgUser.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        downloadUrl = taskSnapshot.getDownloadUrl();
                        rootB = FirebaseDatabase.getInstance().getReference("Infomation account").child(mAuth.getUid());
                        rootB.push().child("linkhinh").setValue(downloadUrl);
                    }
                });
                */
    }

    public void loadData() {
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ShipperModel Data = dataSnapshot.getValue(ShipperModel.class);
                if (Data.id.equalsIgnoreCase(mAuth.getUid())) {
                    txtUserName.setText(Data.email);
                    txtSDT.setText(Data.phone);
                    txtAddress.setText(Data.address);
                    txtCodeB.setText(Data.vehicle);
                    Picasso.get().load(Data.linkhinh).into(imgUser);
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
                ShipperModel data = dataSnapshot.getValue(ShipperModel.class);
                if (data.id.equalsIgnoreCase(mAuth.getUid())) {
                    txtUserNameD.setText(data.email);
                    txtSDTD.setText(data.phone);
                    txtAddressD.setText(data.address);
                    txtCodeBD.setText(data.vehicle);
                }
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference rootC = FirebaseDatabase.getInstance().getReference();
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("email").setValue(txtUserNameD.getText().toString());
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("phone").setValue(txtSDTD.getText().toString());
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("address").setValue(txtAddressD.getText().toString());
                        rootC.child("Infomation account").child(mAuth.getCurrentUser().getUid()).child("vehicle").setValue(txtCodeBD.getText().toString());
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
}
