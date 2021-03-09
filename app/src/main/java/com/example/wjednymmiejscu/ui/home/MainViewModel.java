package com.example.wjednymmiejscu.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wjednymmiejscu.adapters.CurrencyAdapter;
import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.TickerArray;
import com.example.wjednymmiejscu.network.TickerRequests;

import java.util.ArrayList;
import java.util.HashMap;

public class MainViewModel extends ViewModel {
    private final String[] marketsList={"BTC-PLN","ZEC-PLN","BTG-PLN","BCC-PLN","ETH-PLN","LSK-PLN","LTC-PLN"};
    private MutableLiveData<TickerArray> currencyList = new MutableLiveData<>();
    private TickerRequests tickerRequests = new TickerRequests();
    private HashMap<String,Ticker> currencyAdapterDataSource = new HashMap<>();
    private CurrencyAdapter currencyAdapter = new CurrencyAdapter(currencyAdapterDataSource, marketsList);

    public MutableLiveData<TickerArray> getCurrencyList() {
        return currencyList;
    }

    public void loadRates(){
        tickerRequests.loadTicker(currencyList);
    }

    public CurrencyAdapter getCurrencyAdapter() {
        return currencyAdapter;
    }

    public HashMap<String, Ticker> getCurrencyAdapterDataSource() {
        return currencyAdapterDataSource;
    }



    public void setCurrencyAdapterDataSource(HashMap<String, Ticker> currencyAdapterDataSource) {
        this.currencyAdapterDataSource = currencyAdapterDataSource;
    }
}
