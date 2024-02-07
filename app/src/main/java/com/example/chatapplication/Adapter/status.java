package com.example.chatapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.Activity.MainActivity;
import com.example.chatapplication.Modelclass.users;
import com.example.chatapplication.Modelclass.userstatusmodel;
import com.example.chatapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class status extends RecyclerView.Adapter<status.viewholder> {


    MainActivity mainActivity;
    ArrayList<userstatusmodel> statusArrayList;

    public status(MainActivity mainActivity, ArrayList<userstatusmodel> statusArrayList) {
        this.mainActivity = mainActivity;
        this.statusArrayList = statusArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mainActivity).inflate(R.layout.status,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        userstatusmodel Userstatus=statusArrayList.get(position);
        Log.d("StatusAdapter", "Image URL: " + Userstatus.imageUri);
        Picasso.get().load(Userstatus.imageUri).into(holder.stimg);

    }

    @Override
    public int getItemCount() {

        return statusArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CircleImageView stimg;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            stimg=itemView.findViewById(R.id.userstid);
        }
    }
}
