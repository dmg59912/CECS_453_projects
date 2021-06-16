package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{


    ArrayList<Integer> animalImages =  new ArrayList<>(Arrays.asList(
            R.drawable.animal13, R.drawable.animal14,
            R.drawable.animal15, R.drawable.animal16,
            R.drawable.animal17, R.drawable.animal18));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}