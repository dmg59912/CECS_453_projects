package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;


public class signupActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtRetype, edtEmail, edtPhone;
    private Button btnSignup;

    private AwesomeValidation awesomeValidation;

    private Data credentialData;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context = getApplicationContext();

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

                String usernameCred = edtUsername.getText().toString();
                String passwordCred = edtPassword.getText().toString();


                //If the users credentials are added to the data class...
                if(awesomeValidation.validate() && !credentialData.CheckUsername(usernameCred)){

                    credentialData.AddCredential(usernameCred,passwordCred);

                    Toast.makeText(signupActivity.this, "Credentials validated and added!", Toast.LENGTH_SHORT).show();

                    Intent welcomeActivity = new Intent(context, welcomeActivity.class);
                    welcomeActivity.putExtra("username", usernameCred);
                    startActivity(welcomeActivity);

                    return;

                }else if(awesomeValidation.validate() && credentialData.CheckUsername(usernameCred)){

                    Toast.makeText(signupActivity.this, "Username already exists, Try Again.", Toast.LENGTH_SHORT).show();

                } else{

                    Toast.makeText(signupActivity.this, "Invalid Credentials, Try Again.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}