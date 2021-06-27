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

    private static String url_make = "https://thawing-beach-68207.herokuapp.com/carmakes";
    private static String url_model = "https://thawing-beach-68207.herokuapp.com/cars/10/20/92603";

    ArrayList<HashMap<String,String>> car_make_list;
    ArrayList<HashMap<String, String>> car_model_list;


    private boolean two_panel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        RecyclerView car_view = findViewById(R.id.car_list);
        //car_view.setAdapter(new SimpleItemR);

        car_make_list = new ArrayList<>();
        car_model_list = new ArrayList<>();


        list_view = findViewById(R.id.list);

        if (findViewById(R.id.car_detail_container)!=null)
            two_panel = true;

        //test place holders for now ///////////////////////////////////////////////////////

        /////testing first url "https://thawing-beach-68207.herokuapp.com/carmakes";
       // new GetCarMake().execute();

        ////testing second url "https://thawing-beach-68207.herokuapp.com/cars/10/20/92603"
        new GetCarModel().execute();
        //////////////////////////////////////////////////////////////////////////////
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

    private class GetCarModel extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void ...arg0)
        {
            GetModesJason();

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(MainActivity.this,car_model_list,R.layout.list_model_test,new String[]{"color","created_at","id","image_url",
                    "mileage","model","price","veh_description","vin_number"},new int[]{R.id.txt_color,R.id.txt_created,R.id.txt_id_model,R.id.image_model,R.id.txt_milage,
                    R.id.txt_model,R.id.txt_price,R.id.txt_veh_description,R.id.txt_vin_number});

            list_view.setAdapter(adapter);
        }



    }


    private void GetModesJason()
    {
        HttpCarModel car_model = new HttpCarModel();

        //Caling jasonArray and Jason object to map our Arraylist<Hashmap>
            try {
                String jSonStr = car_model.makeServiceCall(url_model);
                JSONObject jsonObj = new JSONObject(jSonStr);
                JSONArray c_models = jsonObj.getJSONArray("lists");

                for(int i = 0 ; i < c_models.length(); ++i)
                {
                    JSONObject models_array  =  c_models.getJSONObject(i);

                    String color = models_array.getString("color");
                    String created = models_array.getString("created_at");
                    String id = models_array.getString("id");
                    String image_url = models_array.getString("image_url");
                    String mileage = models_array.getString("mileage");
                    String model = models_array.getString("model");
                    String price = models_array.getString("price");
                    String veh_description = models_array.getString("veh_description");
                    String vin_number = models_array.getString("vin_number");

                    HashMap<String, String> vModels = new HashMap<>();

                    vModels.put("color",color);
                    vModels.put("created_at",created);
                    vModels.put("id",id);
                    vModels.put("image_url",image_url);
                    vModels.put("mileage",mileage);
                    vModels.put("model",model);
                    vModels.put("price",price);
                    vModels.put("veh_description",veh_description);
                    vModels.put("vin_number",vin_number);

                    car_model_list.add(vModels);


                }

            } catch (MalformedURLException | JSONException e) {
                e.printStackTrace();
            }

    }

    private void GetMakesJason()
    {
        HttpCarMakes car_makes = new HttpCarMakes();

        //Calling Json Array and adding items to car_make_list
        String jsonStr;

        {
            try {
                jsonStr = car_makes.makeServiceCall(url_make);
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