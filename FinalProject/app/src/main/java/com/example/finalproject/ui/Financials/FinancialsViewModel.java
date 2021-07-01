package com.example.finalproject.ui.Financials;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FinancialsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FinancialsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Finances fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
