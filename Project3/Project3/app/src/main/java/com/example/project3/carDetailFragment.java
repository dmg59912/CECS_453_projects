package com.example.project3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link carDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_detail, container, false);
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