package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class welcomeActivity extends AppCompatActivity {

    private TextView txtuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtuser = findViewById(R.id.txt_welcome);

        Intent intent = getIntent();
        String user = intent.getStringExtra("Info");
        txtuser.setText(user);
    }
}