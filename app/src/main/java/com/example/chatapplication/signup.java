package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class signup extends AppCompatActivity {

    private TextView email,pass;
    FirebaseAuth myauth;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email=findViewById(R.id.semail);
        pass=findViewById(R.id.spass);
        myauth=FirebaseAuth.getInstance();
        String emaill=email.getText().toString();
        String passs=pass.getText().toString();
        signup=findViewById(R.id.signupid);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myauth.createUserWithEmailAndPassword(emaill, passs)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(signup.this, "ACCOUNT CREATED", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signup.this,MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(signup.this, "ACCOUNT NOT CREATED", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}