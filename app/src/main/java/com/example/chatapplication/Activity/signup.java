package com.example.chatapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapplication.R;
import com.example.chatapplication.Modelclass.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        imageUri = "https://firebasestorage.googleapis.com/v0/b/chat-application-70cf1.appspot.com/o/img_13.png?alt=media&token=1ac99737-8d53-4272-9232-9806bd1e55a2";
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emaill=email.getText().toString();
                passs=pass.getText().toString();
                name=Name.getText().toString();

                lastmessage="good morning";
                if(emaill.isEmpty() || passs.isEmpty() || name.isEmpty()){
                    Toast.makeText(signup.this, "ENTER THE DETAILS", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!emaill.matches(emailRegex)) {
                    Toast.makeText(signup.this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.length()<6){
                    Toast.makeText(signup.this, "PASSWORD MUST BE 6 CHARACTERS", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                                    startActivity(new Intent(signup.this,selectimage.class));
                                } else {
                                    Toast.makeText(signup.this, "ACCOUNT NOT CREATED", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}