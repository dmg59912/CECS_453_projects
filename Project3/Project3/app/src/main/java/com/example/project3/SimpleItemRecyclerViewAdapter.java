package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project3.content.CarUtils;

import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<CarUtils.Car> mValues;

    SimpleItemRecyclerViewAdapter(List<CarUtils.Car> items)
    {
        mValues = items;
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
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(position + 1));
        holder.mContentView.setText(mValues.get(position).car_model);


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
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        CarUtils.Car mItem;

        ViewHolder(View view)
        {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);

        }
    }
}
