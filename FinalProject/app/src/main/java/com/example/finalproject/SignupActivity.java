package com.example.finalproject;

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

public class SignupActivity extends AppCompatActivity {

    private EditText edt_username, edt_password,edt_retype_pass, edt_email;
    private Button sign_up;

    private AwesomeValidation validation;

    Context context;


    DBHelper user_credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context = getApplicationContext();

        validation = new AwesomeValidation(ValidationStyle.BASIC);

        user_credentials = new DBHelper(getApplicationContext());


        // Initialize the EditText and Buttons from the View
        edt_username = findViewById(R.id.edt_user_signup_activity);
        edt_password = findViewById(R.id.edt_password_signup_activity);
        edt_retype_pass = findViewById(R.id.edt_retype_pass_signup_activity);
        edt_email = findViewById(R.id.edt_email_signup_activity);

        sign_up = findViewById(R.id.btn_sign_me_up);

        validation.addValidation(this,R.id.edt_user_signup_activity,"[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.nameerror);
        validation.addValidation(this,R.id.edt_password_signup_activity,"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",R.string.passerror);
        validation.addValidation(this,R.id.edt_retype_pass_signup_activity, R.id.edt_password_signup_activity,R.string.repasserror);
        validation.addValidation(this,R.id.edt_email_signup_activity, Patterns.EMAIL_ADDRESS,R.string.emailerror);

        sign_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get the essential login credentials
                String usernameCred = edt_username.getText().toString();
                String passwordCred = edt_password.getText().toString();
                String emailCred = edt_email.getText().toString();

                if( validation.validate() && user_credentials.checkUserValidation(usernameCred) == true) {
                    Toast.makeText(SignupActivity.this,"User already Exist, Please log in",Toast.LENGTH_LONG).show();
                }
                else if  (validation.validate()  && user_credentials.checkUserValidation(usernameCred) == false)
                {
                    Toast.makeText(SignupActivity.this,"Account Created ",Toast.LENGTH_LONG).show();
                    user_credentials.insertUser(usernameCred, passwordCred, emailCred);
                }
                Intent backToLogin = new Intent(getApplicationContext(),LoginActivity.class);

                startActivity(backToLogin);

            }
        });

    }
}