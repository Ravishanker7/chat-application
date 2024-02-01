package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class signup extends AppCompatActivity {

    private TextView email,pass,Name;
    FirebaseAuth myauth;
    private Button signup;
    private String emaill,passs,name;
    Uri imageuri;
    String imageUri;
    FirebaseDatabase database;
    FirebaseStorage storage;
    String lastmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email=findViewById(R.id.semail);
        pass=findViewById(R.id.spass);
        myauth=FirebaseAuth.getInstance();
        Name=findViewById(R.id.namee);
        signup=findViewById(R.id.signupid);

        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emaill=email.getText().toString();
                passs=pass.getText().toString();
                name=Name.getText().toString();
                lastmessage="good morning";
                imageUri="https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/Profilee.png?alt=media&token=57a2e99b-1c53-4d7a-9c4e-5152880d6cc1";
                myauth.createUserWithEmailAndPassword(emaill, passs)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(signup.this, "ACCOUNT CREATED", Toast.LENGTH_SHORT).show();
                                    String id=task.getResult().getUser().getUid();
                                    DatabaseReference reference=database.getReference().child("user").child(id);
                                    StorageReference storageReference=storage.getReference().child("upload").child(id);
                                    users Users=new users(imageUri,emaill,name,passs,id,lastmessage);
                                    reference.setValue(Users);
                                    startActivity(new Intent(signup.this,MainActivity.class));
                                } else {
                                    Toast.makeText(signup.this, "ACCOUNT NOT CREATED", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}