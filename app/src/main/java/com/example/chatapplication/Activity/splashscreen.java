package com.example.chatapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.chatapplication.R;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(splashscreen.this, login.class));
        }
    },3000);

    }
}