package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signupActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtRetype, edtEmail, edtPhone;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password_sg);
        edtRetype = findViewById(R.id.edt_retype_pass);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone_sg);

        btnSignup = findViewById(R.id.btn_sign_me);

        btnSignup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //If the users credentials are added to the data class...
                if(true){

                    // set intent
                    // putExtra data class
                    // start the welcomeActivity
                    return;

                }else if(false /* if credentials are already in the data class... */ ){

                    // toast user that they need to use login

                } else{

                    //Toast user that the credentials are invalid

                }

            }
        });


    }
}