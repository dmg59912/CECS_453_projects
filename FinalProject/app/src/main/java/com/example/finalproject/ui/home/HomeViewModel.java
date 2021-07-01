package com.example.finalproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mUsername;
    private MutableLiveData<LineGraphSeries<DataPoint>> mSeries;


    public HomeViewModel() {

        mUsername = new MutableLiveData<>();
        mSeries = new MutableLiveData<>();

        mUsername.setValue("@string/username");
        mSeries.setValue(new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        }));
    }

    public LiveData<String> getText() {
        return mUsername;
    }

    public LiveData<LineGraphSeries<DataPoint>> getData(int i) {
        return mSeries;
    }


}