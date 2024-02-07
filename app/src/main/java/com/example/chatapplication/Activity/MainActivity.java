package com.example.chatapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chatapplication.Adapter.status;
import com.example.chatapplication.Modelclass.userstatusmodel;
import com.example.chatapplication.R;
import com.example.chatapplication.Adapter.Useradapter;
import com.example.chatapplication.Modelclass.users;
import com.example.chatapplication.settings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    RecyclerView mainrecyclerview,statusrcl;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Useradapter adapter;
    CircleImageView profilebtn;
    status Status;
    ArrayList<users> usersArrayList;
    ArrayList<userstatusmodel> statusArrayList;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainrecyclerview=findViewById(R.id.recyclerviewmainid);
        statusrcl=findViewById(R.id.profileview);
        profilebtn=findViewById(R.id.circleImageView);

        logout=findViewById(R.id.lagoutid);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference reference2=database.getReference().child("user").child(auth.getUid());

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profille=snapshot.child("imageUri").getValue().toString();
                Picasso.get().load(profille).into(profilebtn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        usersArrayList=new ArrayList<>();
        statusArrayList=new ArrayList<>();
        DatabaseReference reference=database.getReference().child("user");
        DatabaseReference reference1=database.getReference().child("user");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                statusArrayList.clear(); // Clear the list before adding data
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    userstatusmodel Userstatusmodel = dataSnapshot.getValue(userstatusmodel.class);
                    if (Userstatusmodel != null) {
                        statusArrayList.add(Userstatusmodel);
                    }
                }
                Status.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    users Users=dataSnapshot.getValue(users.class);
                    if (Users != null && !Users.id.equals(auth.getUid())) {
                        usersArrayList.add(Users);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        statusrcl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Status = new status(MainActivity.this, statusArrayList);
        statusrcl.setAdapter(Status);

        mainrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Useradapter(MainActivity.this,usersArrayList);
        mainrecyclerview.setAdapter(adapter);

        if(auth.getCurrentUser()==null){
            startActivity(new Intent(   MainActivity.this, login.class));
            finish();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,login.class));
                finish();
            }
        });
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, settings.class);
                startActivity(intent);

            }
        });
    }
}