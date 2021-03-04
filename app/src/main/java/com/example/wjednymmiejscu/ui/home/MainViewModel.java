package com.example.wjednymmiejscu.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.TickerArray;
import com.example.wjednymmiejscu.network.TickerRequests;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private MutableLiveData<TickerArray> currencyList = new MutableLiveData<>();
    private TickerRequests tickerRequests = new TickerRequests();

    public MutableLiveData<TickerArray> getCurrencyList() {
        return currencyList;
    }

    public void loadRates(){
        tickerRequests.loadTicker(currencyList);
    }
}
