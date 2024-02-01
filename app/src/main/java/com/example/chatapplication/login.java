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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    TextView signup;
    TextView emaill, passs;
    private FirebaseAuth auth;
    private String email, pass;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.signupp);
        emaill = findViewById(R.id.editTextTextEmailAddress);
        passs = findViewById(R.id.passwodlogin);
        login = findViewById(R.id.loginid);
        auth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, signup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emaill.getText().toString();
                pass = passs.getText().toString();
                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(login.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(login.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(login.this, "LOGIN UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
