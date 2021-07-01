package com.example.finalproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalproject.MainActivity;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<LineGraphSeries<DataPoint>> mSeries;


    public HomeViewModel() {
        mSeries = new MutableLiveData<>();
    }

    public LiveData<LineGraphSeries<DataPoint>> getDataDaily(String DATA_TIME_FRAME) {

        return  mSeries;
    }

    public LiveData<LineGraphSeries<DataPoint>> getDataOverTime(String DATA_TIME_FRAME) {

        int count;

        switch(DATA_TIME_FRAME){
            case "Week":
                count = 7;
                break;

            case "Month":
                count = 30;
                break;

            case "Year":
                count = 365;
                break;

            default:
               count = 0;

        }

        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE,count * -1);

        MainActivity.initializeTestData();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        for(int i = 0; i < count; i++){

            Date compDate = calender.getTime();

            // ID | String Income/EXpense | DOuble AMount | Date date

            // Select Income AND Amount AND Date where ID

            series.appendData(new DataPoint(Date.parse(MainActivity.TEST_DATA.get(i).get("date")), Double.parseDouble(MainActivity.TEST_DATA.get(i).get("amount"))),true,count);

            calender.add(Calendar.DATE,1);
        }

        mSeries.setValue(series);


        return mSeries;
    }




}