package com.example.quang.project_sdo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.project_sdo.Models.SellerModel;
import com.example.quang.project_sdo.Models.UsersModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.app.Activity.RESULT_OK;


public class SellerManagementFragment extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    FirebaseStorage storage;
    StorageReference mountainImagesRef;
    Uri imageUri;
    TextView txtUserName, txtSDT, txtAddress;
    ImageView imgUser;
    Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.seller_management_layout,container,false);
        //Ini
        mAuth = FirebaseAuth.getInstance();
        txtUserName = (TextView) view.findViewById(R.id.txtusername);
        txtSDT = (TextView) view.findViewById(R.id.txtsdt);
        txtAddress = (TextView) view.findViewById(R.id.txtaddress);
        imgUser = (ImageView) view.findViewById(R.id.imguser);

        Button btnAdddrugs = (Button) view.findViewById(R.id.btnadddrugs);
        Button btnViewStatistics = (Button) view.findViewById(R.id.btnviewStatistics);
        Button btnEditprofile = (Button) view.findViewById(R.id.btnprofile);
        Button btnLogout = (Button) view.findViewById(R.id.btnlayout);
        imgUser = (ImageView) view.findViewById(R.id.imguser);
        root = FirebaseDatabase.getInstance().getReference("Infomation account");
        storage = FirebaseStorage.getInstance();
        mountainImagesRef = storage.getReferenceFromUrl(
                "gs://projectsdo-9812e.appspot.com");

        loadData();

        //Process
        btnAdddrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnteringDrugActivity.class);
                startActivity(intent);
            }
        });

        btnViewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnEditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_edit_profile_layout);
                dialog.setTitle("Edit profile");
                dialog.show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BackStackActivity.class);
                startActivity(intent);
                mAuth.signOut();
            }
        });


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
                        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(gallery, 5);
                        dialog.dismiss();
                    }
                });

                btnTakeP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 6);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return view;
    }

    public void loadData() {
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SellerModel Data = dataSnapshot.getValue(SellerModel.class);
                if (Data.id.equalsIgnoreCase(mAuth.getUid())) {
                    txtUserName.setText(Data.email);
                    txtSDT.setText(Data.phone);
                    txtAddress.setText(Data.address);
                    //Picasso.get().load(Data.linkhinh).into(imgUser);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 6 && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgUser.setImageBitmap(bitmap);
        }
        if (requestCode == 5 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imgUser.setImageURI(imageUri);

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

        super.onActivityResult(requestCode, resultCode, data);
    }
}
