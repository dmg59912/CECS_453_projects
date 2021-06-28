package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView list_view;
    private ProgressDialog pDialog;

    private Spinner car_make_spinner;
    private Spinner car_model_spinner;

    private static String url_make = "https://thawing-beach-68207.herokuapp.com/carmakes";
    private static String url_model = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/";

    ArrayList<HashMap<String,String>> car_make_list;
    ArrayList<HashMap<String,String>> car_model_list;

    List<String> spinner_make_list;
    List<String> spinner_model_list;

    private String selected_make;
    private String selected_model;

    public static boolean two_panel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        //Initialize View Variables
        //list_view = findViewById(R.id.list);
        car_make_spinner = findViewById(R.id.spinner_make);
        car_model_spinner = findViewById(R.id.spinner_model);

        //Initialize car data variables
        car_make_list = new ArrayList<>();
        car_model_list = new ArrayList<>();

        spinner_make_list = new ArrayList<String>();
        spinner_model_list = new ArrayList<String>();



        if (findViewById(R.id.car_detail_container)!=null)
            two_panel = true;


        //test place holders for now ///////////////////////////////////////////////////////

        /////testing first url "https://thawing-beach-68207.herokuapp.com/carmakes";
        new GetCarMake().execute();


        car_make_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected_make = car_make_spinner.getItemAtPosition(position).toString();

                new GetCarModel(selected_make).execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        car_model_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_model = car_model_spinner.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this, selected_model, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ////testing second url "https://thawing-beach-68207.herokuapp.com/cars/10/20/92603"
        //new GetCarModel().execute();
        //////////////////////////////////////////////////////////////////////////////

        //RecyclerView car_view = findViewById(R.id.car_list);
        //car_view.setAdapter(new SimpleItemRecyclerViewAdapter(car_make_list));




    }

   private class GetCarMake extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait ...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void ...arg0)
        {
           GetMakesJSON();
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

            if (pDialog.isShowing())
            {
                pDialog.dismiss();
            }

            super.onPostExecute(result);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, spinner_make_list);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            car_make_spinner.setAdapter(dataAdapter);
        }

        private void GetMakesJSON()
        {
            HttpHandler handler = new HttpHandler();

            //Calling Json Array and adding items to car_make_list
            String jsonStr;


            try {

                jsonStr = handler.makeServiceCall(url_make);
                JSONArray c_makes = new JSONArray(jsonStr);

                for(int i = 0; i < c_makes.length(); ++i)
                {
                    JSONObject car_array = c_makes.getJSONObject(i);

                    String id = car_array.getString("id");
                    String make = car_array.getString("vehicle_make");

                    HashMap<String,String> vMake = new HashMap<>();
                    vMake.put("id", id);
                    vMake.put("vehicle_make",make);

                    spinner_make_list.add(make);
                    car_make_list.add(vMake);
                }

            }
            catch (JSONException e) {

                e.printStackTrace();

            }

            Collections.sort(spinner_make_list);

        }

    }

    private class GetCarModel extends AsyncTask<Void,Void,Void>
    {
        String selected_make;

        public GetCarModel(String selected_make) {
            this.selected_make = selected_make;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void ...arg0)
        {
            GetModelsJSON(selected_make);
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, spinner_model_list);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            car_model_spinner.setAdapter(dataAdapter);

        }

        private void GetModelsJSON(String make)
        {
            String id = "0";
            spinner_model_list.clear();

            for(int i = 0; i < car_make_list.size(); i++)
                if(car_make_list.get(i).get("vehicle_make").compareTo(make) == 0) {
                    id = car_make_list.get(i).get("id");
                    break;
                }

            HttpHandler handler = new HttpHandler();

            //Calling jasonArray and Jason object to map our Arraylist<Hashmap>
            try {
                String jsonStr = handler.makeServiceCall(url_model + id);
                JSONArray c_models = new JSONArray(jsonStr);

                for(int i = 0 ; i < c_models.length(); ++i)
                {

                    JSONObject models_array  =  c_models.getJSONObject(i);

                    String model = models_array.getString("model");
                    String v_make_id = models_array.getString("vehicle_make_id");

                    HashMap<String,String> vModel = new HashMap<>();
                    vModel.put("id", id);
                    vModel.put("model",model);
                    vModel.put("vehicle_make_id",v_make_id);

                    spinner_model_list.add(model);
                    car_model_list.add(vModel);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Collections.sort(spinner_model_list);
        }

    }
    private class GetCarList extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            getListJSON();

            return null;
        }

        private void getListJSON(){
            HttpHandler handler = new HttpHandler();

            //Calling jasonArray and Jason object to map our Arraylist<Hashmap>
            try {
                String jsonStr = handler.makeServiceCall(url_model + id);
                JSONArray c_models = new JSONArray(jsonStr);

                for(int i = 0 ; i < c_models.length(); ++i)
                {

                    JSONObject models_array  =  c_models.getJSONObject(i);

                    String model = models_array.getString("model");
                    String v_make_id = models_array.getString("vehicle_make_id");

                    HashMap<String,String> vModel = new HashMap<>();
                    vModel.put("id", id);
                    vModel.put("model",model);
                    vModel.put("vehicle_make_id",v_make_id);

                    spinner_model_list.add(model);
                    car_model_list.add(vModel);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

/*
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
                    */



    class ViewHolder extends RecyclerView.ViewHolder
    {
        ViewHolder(View view)
        {
            super(view);


        }
    }


}