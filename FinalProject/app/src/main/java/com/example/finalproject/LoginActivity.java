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

    DBHelper users_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btn_login = findViewById(R.id.btn_log);
        btn_signup = findViewById(R.id.btn_signup);

        edt_password = findViewById(R.id.edt_password);
        edt_username = findViewById(R.id.edt_user_signup_activity);



        //log in user to main account
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users_data = new DBHelper(getApplicationContext());

                String check_user = edt_username.getText().toString();
                String check_password = edt_password.getText().toString();

                //Will first check if the text fields are empty on login
                if(check_user.compareTo("") == 0 || check_password.compareTo("") == 0)
                {
                    if (check_user.compareTo("") == 0 && check_password.compareTo("") == 0)
                        Toast.makeText(getApplicationContext(), "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
                    else if(check_user.compareTo("") ==0)
                        Toast.makeText(getApplicationContext(), "Please Enter a Username ", Toast.LENGTH_SHORT).show();
                    else if(check_password.compareTo("") == 0)
                        Toast.makeText(getApplicationContext(), "Please Enter a Password", Toast.LENGTH_SHORT).show();
                }
                //Will check if the user password is incorrect
                else if (users_data.checkUserValidation(check_user)== true && users_data.logIn_check(check_user,check_password) == false )
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                }
                //Will check if the user exist in the dabase
                else if(users_data.logIn_check(check_user, check_password) == false)
                {
                    Toast.makeText(getApplicationContext(), "User NOT IN DATABASE", Toast.LENGTH_LONG).show();
                }
                //if user password is correct and does exist in data base, the user will be rediredted to main activuty
                else
                {
                    Intent main = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(main);
                }

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