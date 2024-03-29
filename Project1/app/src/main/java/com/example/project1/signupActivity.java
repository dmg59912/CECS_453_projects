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


public class signupActivity extends AppCompatActivity
{

    private EditText edtUsername, edtPassword, edtRetype, edtEmail, edtPhone;
    private Button btnSignup;

    private AwesomeValidation awesomeValidation;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //
        context = getApplicationContext();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Initialize the EditText and Buttonsfrom the View
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password_sg);
        edtRetype = findViewById(R.id.edt_retype_pass);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone_sg);

        btnSignup = findViewById(R.id.btn_sign_me);

        //Adds regular expression validation to all of the editText boxes
        awesomeValidation.addValidation(this,R.id.edt_username,"[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.nameerror);
        awesomeValidation.addValidation(this,R.id.edt_password_sg,"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",R.string.passerror);
        awesomeValidation.addValidation(this,R.id.edt_retype_pass, R.id.edt_password_sg,R.string.repasserror);
        awesomeValidation.addValidation(this,R.id.edt_email, Patterns.EMAIL_ADDRESS,R.string.emailerror);
        awesomeValidation.addValidation(this,R.id.edt_phone_sg,"^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$",R.string.phoneerror);

        // set an onClickListener to the Signup Button
        btnSignup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                // Get the essential login credentials
                String usernameCred = edtUsername.getText().toString();
                String passwordCred = edtPassword.getText().toString();

                // If the users credentials are valid for the data class to add...
                if(awesomeValidation.validate() && !MainActivity.data.CheckUsername(usernameCred)){

                    // Add the credentials
                    MainActivity.data.AddCredential(usernameCred,passwordCred);

                    // Message the user for success
                    Toast.makeText(signupActivity.this, "Credentials validated and added!", Toast.LENGTH_SHORT).show();

                    // Set the intent to the welcomeActivity and send it the username string
                    Intent welcomeActivity = new Intent(context, MainActivity.class);
                    //welcomeActivity.putExtra("Username", usernameCred);
                    startActivity(welcomeActivity);

                    return;

                }else if(awesomeValidation.validate() && MainActivity.data.CheckUsername(usernameCred)){ // Username already exists in Data class

                    Toast.makeText(signupActivity.this, "Username already exists, Try Again.", Toast.LENGTH_SHORT).show();

                } else{ // Credentials are input incorrectly

                    Toast.makeText(signupActivity.this, "Invalid Credentials, Try Again.", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


}