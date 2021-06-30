package com.example.project3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link carDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carDetailFragment extends Fragment {

    private TextView make_model_txt, price_txt, created_txt;
    private EditText details_txt;
    private ImageView car_image;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String make, model, price, detail, created;


    public carDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment carDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static carDetailFragment newInstance(String param1, String param2) {
        carDetailFragment fragment = new carDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            make = getArguments().getString("make");
            model = getArguments().getString("model");
            price = getArguments().getString("price");
            detail = getArguments().getString("veh_description");
            created = getArguments().getString("created_at");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_detail, container, false);

        make_model_txt = view.findViewById(R.id.make_model_frag_txt);
        price_txt = view.findViewById(R.id.price_frag_txt);
        created_txt = view.findViewById(R.id.update_frag_txt);
        details_txt = view.findViewById(R.id.details_frag_txt);
        car_image = view.findViewById(R.id.car_frag_img);


        make_model_txt.setText(make + " - " + model);
        price_txt.setText("$" + price);
        details_txt.setText(detail);
        created_txt.setText(created);
        return view;



    }

    public static carDetailFragment newInstance(HashMap<String, String> car_details)
    {
        carDetailFragment frg  = new carDetailFragment();
        Bundle arguments = new Bundle();

        arguments.putString("make", car_details.get("make"));
        arguments.putString("model" , car_details.get("model"));
        arguments.putString("price" , car_details.get("price"));
        arguments.putString("veh_description" , car_details.get("veh_description"));
        arguments.putString("created_at" , car_details.get("created_at"));
        frg.setArguments(arguments);
        return frg;
    }
}