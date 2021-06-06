package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;


public class signupActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtRetype, edtEmail, edtPhone;

    private Button btnSignup;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password_sg);
        edtRetype = findViewById(R.id.edt_retype_pass);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone_sg);

        btnSignup = findViewById(R.id.btn_sign_me);

        //Adds regular expression validation to all of the editText boxes
        awesomeValidation.addValidation(this,R.id.edt_username,"[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.nameerror);
        awesomeValidation.addValidation(this,R.id.edt_password_sg,"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]).{8,32}$",R.string.passerror);
        awesomeValidation.addValidation(this,R.id.edt_retype_pass,edtPassword.getText().toString(),R.string.repasserror);
        awesomeValidation.addValidation(this,R.id.edt_email, Patterns.EMAIL_ADDRESS,R.string.emailerror);
        awesomeValidation.addValidation(this,R.id.edt_phone_sg,"^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$",R.string.phoneerror);


        btnSignup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //If the users credentials are added to the data class...
                if(awesomeValidation.validate()){

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