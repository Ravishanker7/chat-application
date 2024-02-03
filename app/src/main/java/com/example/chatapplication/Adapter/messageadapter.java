package com.example.chatapplication.Adapter;

import static com.example.chatapplication.Activity.userchat.rImage;
import static com.example.chatapplication.Activity.userchat.sImage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.R;
import com.example.chatapplication.Modelclass.messages;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Context;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class messageadapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<messages> messagesArrayList;

    public messageadapter(Context context, ArrayList<messages> messagesArrayList) {
        this.context = context;
        this.messagesArrayList = messagesArrayList;
    }

    int item_send=1;
    int item_recieve=2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==item_send){
            View view= LayoutInflater.from(context).inflate(R.layout.senderlayout,parent,false);
            return new senderviewholder(view);
        }else{
            View view=LayoutInflater.from(context).inflate(R.layout.reciverlayout,parent,false);
            return  new reciverviewholder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messages Messages=messagesArrayList.get(position);
        if(holder.getClass()==senderviewholder.class){
            senderviewholder viewholder=(senderviewholder) holder;
            viewholder.txtmessage.setText(Messages.getMessage());
            Picasso.get().load(sImage).into(viewholder.circleImageView);
        }else{
            reciverviewholder vieWholder=(reciverviewholder) holder;
            vieWholder.txtmessage.setText(Messages.getMessage());
            Picasso.get().load(rImage).into(vieWholder.circleImageView);
        }
    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        messages Messages=messagesArrayList.get(position);
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(Messages.getSenderId()))
        {
            return item_send;
        }else{
            return item_recieve;
        }
    }

    class  senderviewholder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView txtmessage;

        public senderviewholder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.chatprofileid);
            txtmessage=itemView.findViewById(R.id.txtmsgid);
        }
    }
    class reciverviewholder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView txtmessage;
        public reciverviewholder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.chatprofileid);
            txtmessage=itemView.findViewById(R.id.txtmsgid);
        }
    }
}
