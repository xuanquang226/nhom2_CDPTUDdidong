package com.example.quang.project_sdo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quang.project_sdo.Models.EnterDrugModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Calendar;

public class EnteringDrugActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference root, rootB;
    private EnterDrugModel enterDrugModel;
    private FirebaseStorage storage;
    EditText edtTenThuoc, edtCongDung, edtGia, edtNguonGoc, edtMoTa, edtSoLuong;
    Button btnXacNhan, btnXoa;
    ImageView imgThuoc;

    int REQUEST_CODE = 1;
    int PICK_IMAGE = 2;
    Uri downloadUrl;
    ActionBar actionBar;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entering_drug_layout);

        //Ini view
        edtTenThuoc = (EditText) findViewById(R.id.edtNhapTenThuoc);
        edtCongDung = (EditText) findViewById(R.id.edtNhapCongDung);
        edtGia = (EditText) findViewById(R.id.edtNhapGia);
        edtNguonGoc = (EditText) findViewById(R.id.edtNhapNguonGoc);
        edtMoTa = (EditText) findViewById(R.id.edtNhapMoTa);
        edtSoLuong = (EditText) findViewById(R.id.edtNhapSoLuong);
        btnXacNhan = (Button) findViewById(R.id.btnOKNT);

        imgThuoc = (ImageView) findViewById(R.id.imgSDrug);
        actionBar = getSupportActionBar();


        //Ini firebase
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        final StorageReference mountainImagesRef = storage.getReferenceFromUrl(
                "gs://projectsdo-9812e.appspot.com");


        //Process button back
        actionBar.setDisplayHomeAsUpEnabled(true);


        //Process add drug
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Process for image
                Calendar calendar = Calendar.getInstance();
                StorageReference mountainsRef = mountainImagesRef.child("image" + calendar.getTimeInMillis() + ".png");

                imgThuoc.setDrawingCacheEnabled(true);
                imgThuoc.buildDrawingCache();
                Bitmap bitmap = imgThuoc.getDrawingCache();
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
                        rootB.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String nameStore = dataSnapshot.child("drugstore").getValue().toString().trim();
                                String idThuoc = FirebaseDatabase.getInstance().getReference().push().getKey();
                                root = FirebaseDatabase.getInstance().getReference("Drug").child(idThuoc);
                                enterDrugModel = new EnterDrugModel(edtTenThuoc.getText().toString().trim(), edtCongDung.getText().toString().trim(), edtGia.getText().toString().trim(),
                                        edtNguonGoc.getText().toString().trim(), edtMoTa.getText().toString().trim(), edtSoLuong.getText().toString().trim(), downloadUrl + "", nameStore, idThuoc);
                                root.setValue(enterDrugModel);
                                Toast.makeText(EnteringDrugActivity.this, "Đã thêm thuốc thành công", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        });

        //Process add image
        imgThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(EnteringDrugActivity.this);
                dialog.setContentView(R.layout.dialog_image_layout);
                dialog.setTitle("Choose");
                dialog.setCancelable(false);

                Button btnOpen = (Button) dialog.findViewById(R.id.openGallery);
                Button btnTakeP = (Button) dialog.findViewById(R.id.takePhoto);

                btnOpen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(gallery, PICK_IMAGE);
                        dialog.dismiss();
                    }
                });

                btnTakeP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CODE);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgThuoc.setImageBitmap(bitmap);
            Log.d("AAAA",bitmap + "");
        }
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imgThuoc.setImageURI(imageUri);
            Log.d("AAAA",imageUri + "");

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
