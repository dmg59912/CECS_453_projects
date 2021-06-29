package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CarDetailActivity extends AppCompatActivity {


    private TextView make_model_txt, price_txt, details_txt, created_txt;
    private ImageView car_image;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        make_model_txt = findViewById(R.id.make_model_text);
        price_txt = findViewById(R.id.price_text);
        details_txt = findViewById(R.id.details_text);
        created_txt = findViewById(R.id.created_txt);
        car_image = findViewById(R.id.car_image);
        back_button = findViewById(R.id.back_button);

        Intent intent = getIntent();

        make_model_txt.setText(intent.getStringExtra("make") + " - " + intent.getStringExtra("model"));
        price_txt.setText(intent.getStringExtra("price"));
        details_txt.setText(intent.getStringExtra("veh_description"));
        created_txt.setText(intent.getStringExtra("created_at"));


        back_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}