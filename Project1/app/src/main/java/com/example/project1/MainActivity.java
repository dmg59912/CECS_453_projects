/*
    Assignment1
    Written by:
        Arjang Fahim 098765432
        Arjang Fahim 123455678


 */

package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;




public class MainActivity extends AppCompatActivity  {

    private Button btnlogin;
    private EditText edt_user,edt_pass;
    public Data check = new Data();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //matching by ID
        btnlogin = findViewById(R.id.btn_login);
        edt_user = findViewById(R.id.edt_user_name);
        edt_pass = findViewById(R.id.edt_password);

        btnlogin.setOnClickListener(new View.OnClickListener() {

           // boolean check_if_user_exist = check.CheckCredentials(check_user,check_password);
            @Override
            public void onClick(View view) {
                //going to check if user and password is in the hashmap
                String check_user = edt_user.getText().toString();
                String check_password =  edt_pass.getText().toString();

                if( check.CheckCredentials(check_user,check_password)== true)
                {
                    String info = "Welcome " + check_user;
                    Intent intent = new Intent(getApplicationContext(),welcomeActivity.class);
                    intent.putExtra("Info",info);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed to log in " ,Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}