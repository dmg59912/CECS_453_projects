package com.example.finalproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentHomeBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private final ArrayList<String> REPORT_GRAPH_TIMEFRAMES = new ArrayList<String>(){
        {
            add("Week");
            add("Month");
            add("Year");
        }
    };

    public static final int WEEK_TIMEFRAME_LABELS = 7, MONTH_TIMEFRAME_LABELS = 4, YEAR_TIMEFRAME_LABEL = 3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final GraphView homeReportBarGraph = binding.homeReportBarGraph;
        final GraphView homeReportGraph = binding.homeReportGraph;
        final Spinner homeReportGraphSpinner = binding.homeReportGraphSpinner;

        // Setup Spinner to contain the different Time Frames for the finances over time graph
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, REPORT_GRAPH_TIMEFRAMES);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        homeReportGraphSpinner.setAdapter(spinnerAdapter);

        // Spinner selects a Time Frame...
        homeReportGraphSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Get selected Time Frame from the spinner
                String SELECTED_REPORT_TIME_FRAME = homeReportGraphSpinner.getSelectedItem().toString();

                // Get the data for the finances over time graph with the selected time frame
                homeViewModel.getDataOverTime(SELECTED_REPORT_TIME_FRAME).observe(getViewLifecycleOwner(), new Observer<LineGraphSeries<DataPoint>>() {
                    @Override
                    public void onChanged(LineGraphSeries<DataPoint> dataSeries) {

                        homeReportGraph.removeAllSeries();
                        homeReportGraph.addSeries(dataSeries);

                        homeReportGraph.setTitle(homeReportGraphSpinner.getSelectedItem().toString());
                        homeReportGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));


                        switch(SELECTED_REPORT_TIME_FRAME){
                            case "Week":
                                homeReportGraph.getGridLabelRenderer().setNumHorizontalLabels(WEEK_TIMEFRAME_LABELS);
                                break;

                            case "Month":
                                homeReportGraph.getGridLabelRenderer().setNumHorizontalLabels(MONTH_TIMEFRAME_LABELS);
                                break;

                            case "Year":
                                homeReportGraph.getGridLabelRenderer().setNumHorizontalLabels(YEAR_TIMEFRAME_LABEL);
                                break;

                            default:
                                homeReportGraph.getGridLabelRenderer().setNumHorizontalLabels(0);

                        }




                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}