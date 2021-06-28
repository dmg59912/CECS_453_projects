package com.example.project3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<HashMap<String,String>> car_item_list;

    SimpleItemRecyclerViewAdapter(ArrayList<HashMap<String, String>> car_item_list)
    {
        this.car_item_list = car_item_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_content,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {

        holder.mIdView.setText(car_item_list.get(position).get("item_id"));
        holder.mContentView.setText(car_item_list.get(position).get("make") + ": " + car_item_list.get(position).get("model"));


       /*holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                if (MainActivity.two_panel)
                {
                    int selectedCar = holder.getAdapterPosition();

                    carDetailFragment frg = carDetailFragment.newInstance(selectedCar);


                    .getSupportFragmentManager().beginTransaction()
                            .replace(R.id.car_detail_container, frg)
                            .addToBackStack(null)
                            .commit();

                }
                else
                {
                    //getApplicationContext()
                    Context context = v.getContext();
                    Intent intent = new Intent(context, SongDetailActivity.class);
                    intent.putExtra(SongUtils.SONG_ID_KEY, holder.getAdapterPosition());
                    context.startActivity(intent);
                }





            }


        });*/

    }

    @Override
    public int getItemCount()
    {
        return car_item_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view)
        {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.car_id);
            mContentView = view.findViewById(R.id.car_model);

        }
    }
}
