package com.example.chatapplication.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.Activity.MainActivity;
import com.example.chatapplication.R;
import com.example.chatapplication.Activity.userchat;
import com.example.chatapplication.Modelclass.users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Useradapter extends RecyclerView.Adapter<Useradapter.viewholder> {

    MainActivity mainActivity;
    ArrayList<users> usersArrayList;
    public Useradapter(MainActivity mainActivity, ArrayList<users> usersArrayList) {
        this.mainActivity=mainActivity;
        this.usersArrayList=usersArrayList;
    }

    @NonNull
    @Override
    public Useradapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.useritems,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Useradapter.viewholder holder, int position) {

        users Users=usersArrayList.get(position);
        holder.usernamee.setText(Users.name);
        holder.userstatus.setText(Users.lastmessage);
        Picasso.get().load(Users.imageUri).into(holder.userimg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainActivity, userchat.class);
                intent.putExtra("namme",Users.getName());
                intent.putExtra("receiverimage",Users.getImageUri());
                intent.putExtra("uid",Users.getId());
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView userimg;
        TextView usernamee;
        TextView userstatus;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            userimg=itemView.findViewById(R.id.userimagemain);
            usernamee=itemView.findViewById(R.id.nameidd);
            userstatus=itemView.findViewById(R.id.lastmessageid);

        }
    }
}
