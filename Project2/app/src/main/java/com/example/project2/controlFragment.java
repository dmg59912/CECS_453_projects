package com.example.project2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link controlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class controlFragment extends Fragment {

    private  int position = 0;

    private Button btnBack, btnNext;
    private CheckBox chkGalleryView, chkSlideShow;

    ArrayList<Integer> animalImages =  new ArrayList<>(Arrays.asList(
            R.drawable.animal13, R.drawable.animal14,
            R.drawable.animal15, R.drawable.animal16,
            R.drawable.animal17, R.drawable.animal18));


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public controlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment controlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static controlFragment newInstance(String param1, String param2) {
        controlFragment fragment = new controlFragment();
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
        View view = inflater.inflate(R.layout.fragment_control, container, false);


        btnBack = view.findViewById(R.id.btnBack);
        btnNext = view.findViewById(R.id.btnNext);
        chkGalleryView = view.findViewById(R.id.chkGalleryView);
        chkSlideShow = view.findViewById(R.id.chkSlideShow);

        btnNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (position < animalImages.size()-1) {
                    position++;
                    show_Image();
                }

            }

        });
        btnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (position > 0) {
                    position--;
                    show_Image();
                }

            }

        });

        return view;
    }

    private void show_Image(){
        Fragment imgFrag = imageFragment.newInstance(Integer.toString(position), "New Image Frag");

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.imgFrag, imgFrag)
                .addToBackStack("")
                .commit();
    }
}