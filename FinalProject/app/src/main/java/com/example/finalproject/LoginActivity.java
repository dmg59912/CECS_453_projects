package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    private Button btn_login,btn_signup;
    private EditText edt_username,edt_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btn_login = findViewById(R.id.btn_log);
        btn_signup = findViewById(R.id.btn_signup);

        edt_password = findViewById(R.id.edt_password);
        edt_username = findViewById(R.id.edt_user_signup_activity);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String check_user = edt_username.getText().toString();
                String check_password = edt_password.getText().toString();

                Toast.makeText(getApplicationContext(),check_user + "  " + check_password,Toast.LENGTH_SHORT).show();

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext(),SignupActivity.class);

                startActivity(signup);
            }
        });
    }
}