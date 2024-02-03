package com.example.chatapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.Activity.MainActivity;
import com.example.chatapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class status extends RecyclerView.Adapter<status.viewholder> {


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CircleImageView stimg;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            stimg=itemView.findViewById(R.id.userstid);
        }
    }
}
