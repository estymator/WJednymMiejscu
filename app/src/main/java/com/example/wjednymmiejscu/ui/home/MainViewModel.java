package com.example.wjednymmiejscu.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.TickerArray;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    MutableLiveData<ArrayList<TickerArray>> CurrencyList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<TickerArray>> getCurrencyList() {
        return CurrencyList;
    }
}
