package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean two_panel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        RecyclerView car_view = findViewById(R.id.car_list);
        //car_view.setAdapter(new SimpleItemR);

        if (findViewById(R.id.car_detail_container)!=null)
            two_panel = true;
    }

    //class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>
   // {

   //}

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ViewHolder(View view)
        {
            super(view);


        }
    }
}