package com.example.gardenproyect.ui.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServiceViewModel_Jardinero extends ViewModel {

    private MutableLiveData<String> mText;

    public ServiceViewModel_Jardinero() {
        mText = new MutableLiveData<>();
        mText.setValue("This is service fragment jardinero");
    }

    public LiveData<String> getText() {
        return mText;
    }
}