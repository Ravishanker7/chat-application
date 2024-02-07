package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class settings extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    CircleImageView stgprofile;
    TextView stgname,stgUname,stgemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        stgprofile=findViewById(R.id.circleImageView2);
        stgname=findViewById(R.id.stgnameid);
        stgemail=findViewById(R.id.stggmaild);
        stgUname=findViewById(R.id.textView);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        DatabaseReference reference=database.getReference().child("user").child(auth.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child("name").getValue().toString();
                String email=snapshot.child("emaill").getValue().toString();
                String imageuri=snapshot.child("imageUri").getValue().toString();

                stgname.setText(name);
                stgemail.setText(email);
                stgUname.setText(name);
                Picasso.get().load(imageuri).into(stgprofile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}