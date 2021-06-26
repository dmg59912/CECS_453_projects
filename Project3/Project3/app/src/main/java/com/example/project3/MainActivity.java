package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView list_view;
    private ProgressDialog pDialog;

    private static String url = "https://thawing-beach-68207.herokuapp.com/carmakes";

    ArrayList<HashMap<String,String>> car_make_list;


    private boolean two_panel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        RecyclerView car_view = findViewById(R.id.car_list);
        //car_view.setAdapter(new SimpleItemR);

        car_make_list = new ArrayList<>();
        list_view = findViewById(R.id.list);


        if (findViewById(R.id.car_detail_container)!=null)
            two_panel = true;

        new GetCarMake().execute();
    }

    //class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>
   // {

   //}

    private class GetCarMake extends AsyncTask<Void, Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void ...arg0)
        {
           GetMakesJason();

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(MainActivity.this,car_make_list, R.layout.list_test,new String[]{"id","vehicle_make"},
                    new int[]{R.id.txt_id,R.id.txt_car_make});

            list_view.setAdapter(adapter);

        }
    }

    private void GetMakesJason()
    {
        HttpCarMakes car_makes = new HttpCarMakes();

        //Calling Json Array and adding items to car_make_list
        String jsonStr;

        {
            try {
                jsonStr = car_makes.makeServiceCall(url);
                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray c_makes = jsonObj.getJSONArray("Car_make");

                for(int i = 0; i < c_makes.length(); ++i)
                {
                    JSONObject car_array = c_makes.getJSONObject(i);

                    String id = car_array.getString("id");
                    String make = car_array.getString("vehicle_make");

                    HashMap<String,String> vMake = new HashMap<>();
                    vMake.put("id", id);
                    vMake.put("vehicle_make",make);

                    car_make_list.add(vMake);

                }

            } catch (MalformedURLException | JSONException e) {
                e.printStackTrace();
            }
        }


    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ViewHolder(View view)
        {
            super(view);


        }
    }


}