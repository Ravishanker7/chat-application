package com.example.chatapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapplication.Adapter.messageadapter;
import com.example.chatapplication.R;
import com.example.chatapplication.Modelclass.messages;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class userchat extends AppCompatActivity {

    String recoverimg,reciverid,receivername,senderuid,senderUID;

    TextView recivernamee;
    CircleImageView profileimg;

    FirebaseDatabase database;
    TextView editmessage;
    ImageView sendbtn,backbtn;
    ArrayList<messages> messagesArrayList;

    public  static  String sImage;
    public static  String rImage;
    String senderroom,reciverroom;
    RecyclerView messageAdapter;

    messageadapter adaPter;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchat);

        recoverimg=getIntent().getStringExtra("receiverimage");
        receivername=getIntent().getStringExtra("namme");
        reciverid=getIntent().getStringExtra("uid");
        backbtn=findViewById(R.id.backbuttonid);

        messagesArrayList=new ArrayList<>();
        messageAdapter=findViewById(R.id.chatrecyclerid);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messageAdapter.setLayoutManager(linearLayoutManager);
        adaPter=new messageadapter(userchat.this,messagesArrayList);
        messageAdapter.setAdapter(adaPter);

        recivernamee=findViewById(R.id.recivernameid);
        profileimg=findViewById(R.id.Userimaggeid);
        sendbtn=findViewById(R.id.sendbuttonid);
        editmessage=findViewById(R.id.messageid);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        Picasso.get().load(recoverimg).into(profileimg);
        recivernamee.setText(""+receivername);

        senderUID=auth.getUid();

        senderroom=senderUID+reciverid;
        reciverroom=reciverid+senderUID;


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(userchat.this,MainActivity.class));
            }
        });

        DatabaseReference reference=database.getReference().child("user").child(auth.getUid());

        DatabaseReference chatreference=database.getReference().child("chats").child(senderroom).child("messages");

        chatreference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messagesArrayList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    messages Messages=dataSnapshot.getValue(messages.class);
                    messagesArrayList.add(Messages);
                }
                adaPter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sImage=snapshot.child("imageUri").getValue().toString();
                rImage=recoverimg;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=editmessage.getText().toString();
                if(message.isEmpty()){
                    Toast.makeText(userchat.this, "ENTER THE MESSAGE", Toast.LENGTH_SHORT).show();
                }
                editmessage.setText("");
                Date date=new Date();

                messages Message=new messages(message,senderUID,date.getTime());
                database.getReference().child("chats").child(senderroom).child("messages")
                        .push().setValue(Message).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                database.getReference().child("chats").child(reciverroom).child("messages")
                                        .push().setValue(Message).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });
                            }
                        });

            }
        });
    }
}