package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class userchat extends AppCompatActivity {

    String recoverimg,reciverid,receivername,senderuid;

    TextView recivernamee;
    CircleImageView profileimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchat);

        recoverimg=getIntent().getStringExtra("receiverimage");
        receivername=getIntent().getStringExtra("namme");
        reciverid=getIntent().getStringExtra("uid");

        recivernamee=findViewById(R.id.recivernameid);
        profileimg=findViewById(R.id.Userimaggeid);

        Picasso.get().load(recoverimg).into(profileimg);
        recivernamee.setText(""+receivername);
    }
}