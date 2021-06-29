package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private static String url_list = "https://thawing-beach-68207.herokuapp.com/cars/";
    private static String url_condition = "https://thawing-beach-68207.herokuapp.com/cars/3484";

    ArrayList<HashMap<String,String>> car_make_list;
    ArrayList<HashMap<String,String>> car_model_list;
    ArrayList<HashMap<String,String>> car_item_list;

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
        car_make_spinner = findViewById(R.id.spinner_make);
        car_model_spinner = findViewById(R.id.spinner_model);

        //Initialize car data variables
        car_make_list = new ArrayList<>();
        car_model_list = new ArrayList<>();
        car_item_list = new ArrayList<>();

        spinner_make_list = new ArrayList<String>();
        spinner_model_list = new ArrayList<String>();


        if (findViewById(R.id.car_detail_container)!=null)
            two_panel = true;

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

                new GetCarList(selected_make,selected_model).execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

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

        String selected_make;
        String selected_model;

        public GetCarList(String selected_make, String selected_model){
            this.selected_make = selected_make;
            this.selected_model = selected_model;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getListJSON(selected_make, selected_model);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            RecyclerView rv = findViewById(R.id.car_list_rv);
            rv.setAdapter(new SimpleItemRecyclerViewAdapter(car_item_list));
        }

        private void getListJSON(String make, String model){

            String make_id = "";
            String model_id = "";

            car_item_list.clear();

            for(int i = 0; i < car_model_list.size(); i++)
                if(car_model_list.get(i).get("model").compareTo(model) == 0 ) {
                    make_id = car_model_list.get(i).get("vehicle_make_id");
                    model_id = car_model_list.get(i).get("id");
                    break;
                }

            HttpHandler handler = new HttpHandler();

            //Calling jasonArray and Jason object to map our Arraylist<Hashmap>
            try {
                String jsonStr = handler.makeServiceCall(url_list + make_id + "/" + model_id + "/92603");
                JSONObject list = new JSONObject(jsonStr);

                JSONArray c_list = list.getJSONArray("lists");

                for(int i = 0 ; i < c_list.length(); ++i)
                {
                    JSONObject item = c_list.getJSONObject(i);

                    String color = item.getString("color");
                    String created = item.getString("created_at");
                    String item_id = item.getString("id");
                    String image_url = item.getString("image_url");
                    String mileage = item.getString("mileage");

                    String price = item.getString("price");
                    String veh_description = item.getString("veh_description");

                    String vehicle_url = item.getString("vehicle_url");
                    String vin_number = item.getString("vin_number");

                    HashMap<String, String> vItem = new HashMap<>();

                    vItem.put("color",color);
                    vItem.put("created_at",created);
                    vItem.put("item_id",item_id);
                    vItem.put("image_url",image_url);
                    vItem.put("mileage",mileage);
                    vItem.put("model",selected_model);
                    vItem.put("price",price);
                    vItem.put("veh_description",veh_description);
                    vItem.put("make", selected_make);
                    vItem.put("vin_number",vin_number);

                    car_item_list.add(vItem);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

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
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            holder.mIdView.setText("id: " + car_item_list.get(position).get("item_id"));
            holder.mContentView.setText(car_item_list.get(position).get("make") + ": " + car_item_list.get(position).get("model"));

            holder.mView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {

                    if (MainActivity.two_panel)
                    {
                        carDetailFragment frg = carDetailFragment.newInstance(car_item_list.get(holder.getAdapterPosition()));

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.car_detail_container, frg)
                                .addToBackStack(null)
                                .commit();
                    }
                    else
                    {
                        //getApplicationContext()
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CarDetailActivity.class);

                        intent.putExtra("make", car_item_list.get(holder.getAdapterPosition()).get("make"));
                        intent.putExtra("model" , car_item_list.get(holder.getAdapterPosition()).get("model"));
                        intent.putExtra("price" , car_item_list.get(holder.getAdapterPosition()).get("price"));
                        intent.putExtra("veh_description" , car_item_list.get(holder.getAdapterPosition()).get("veh_description"));
                        intent.putExtra("created_at" , car_item_list.get(holder.getAdapterPosition()).get("created_at"));

                        context.startActivity(intent);
                    }





                }


            });

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


}