package com.example.chatapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class selectimage extends AppCompatActivity implements View.OnClickListener {

    CircleImageView img1,img2,img3,img4,img5,img6,img7,img8;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectimage);

        img1=findViewById(R.id.sltid1);
        img2=findViewById(R.id.sltid2);
        img3=findViewById(R.id.sltid3);
        img4=findViewById(R.id.sltid4);
        img5=findViewById(R.id.sltid5);
        img6=findViewById(R.id.sltid6);
        img7=findViewById(R.id.sltid7);
        img8=findViewById(R.id.sltid8);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);

    }
    public void onClick(View v) {
        // Handle click events for each CircleImageView
        String imageUri;
        if (v.getId() == R.id.sltid1) {
            imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_13.png?alt=media&token=1ac99737-8d53-4272-9232-9806bd1e55a2";
        } else if (v.getId() == R.id.sltid2) {
            imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_14.png?alt=media&token=aa0f0b78-afa2-4e70-8db8-381aaa662676";
        } else if (v.getId() == R.id.sltid3) {
            imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_15.png?alt=media&token=e6ff88c9-4242-4544-a7a2-53c20b7a4e89";
        } else if (v.getId() == R.id.sltid4) {
            imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_16.png?alt=media&token=174e34ca-f61b-4400-827b-9b87735dd890";
        } else if (v.getId() == R.id.sltid5) {
            imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_18.png?alt=media&token=877b8979-746f-4512-ba87-a27d96e16bf6";
        } else if (v.getId() == R.id.sltid6) {
            imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_19.png?alt=media&token=b16dedd5-8ec3-4b5e-a4ed-89d5fb6a28a0";
        } else if (v.getId() == R.id.sltid7) {
            imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_20.png?alt=media&token=53edd60f-b465-4306-b492-bf3fc9b6f044";
        } else if (v.getId() == R.id.sltid8) {
            imageUri="https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_21.png?alt=media&token=a0877f72-a20f-437e-8e03-c1e3ebea7ad9";
        } else {
            imageUri = null;
        }
        if (imageUri != null) {
            updateImageUri(imageUri);
        }
    }
    private void updateImageUri(String imageUri) {
        if (auth.getCurrentUser() != null) {
            String userId = auth.getCurrentUser().getUid();
            DatabaseReference userRef = database.getReference().child("user").child(userId);
            userRef.child("imageUri").setValue(imageUri)
                    .addOnSuccessListener(aVoid -> {
                        // Image URI updated successfully
                        startActivity(new Intent(selectimage.this,MainActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        // Failed to update image URI
                        Toast.makeText(selectimage.this, "Failed to update image", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
