package com.example.asignment1.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.asignment1.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();

    }
    public void init(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },3000);
}}
