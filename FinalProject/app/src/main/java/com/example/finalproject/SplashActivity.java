package com.example.finalproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);


        int SPLASH_TIME = 2000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splash = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splash);
                finish();
            }
        },SPLASH_TIME);
    }
}