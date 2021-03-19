package com.example.wjednymmiejscu.ui.home;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wjednymmiejscu.adapters.CurrencyAdapter;
import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.TickerArray;
import com.example.wjednymmiejscu.network.TickerRequests;

import java.util.ArrayList;
import java.util.HashMap;

public class MainViewModel extends ViewModel{
    private static final String TAG="MainVMTAG";
    private final String[] marketsList={"BTC-PLN","ZEC-PLN","BTG-PLN","BCC-PLN","ETH-PLN","LSK-PLN","LTC-PLN"};
    private MutableLiveData<TickerArray> currencyList = new MutableLiveData<>();
    private MutableLiveData<String> calculatorFirstRowCurr=new MutableLiveData<>();
    private MutableLiveData<String> calculatorSecondRowCurr = new MutableLiveData<>();
    private Double calculatorAmount;
    private MutableLiveData<Double> calculatorResult = new MutableLiveData<>();
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

    public String[] getMarketsList() {
        return marketsList;
    }

    public void setCurrencyAdapterDataSource(HashMap<String, Ticker> currencyAdapterDataSource) {
        this.currencyAdapterDataSource = currencyAdapterDataSource;
    }

    public MutableLiveData<String> getCalculatorFirstRowCurr() {
        return calculatorFirstRowCurr;
    }

    public MutableLiveData<String> getCalculatorSecondRowCurr() {
        return calculatorSecondRowCurr;
    }

    public double getCalculatorAmount() {
        return calculatorAmount;
    }

    public void setCalculatorAmount(double calculatorAmount) {
        this.calculatorAmount = calculatorAmount;
    }

    public MutableLiveData<Double> getCalculatorResult() {
        return calculatorResult;
    }

    public void instantiateCalculatorValues(){
        calculatorFirstRowCurr.setValue("BTC");
        calculatorSecondRowCurr.setValue("BTC");
        calculatorAmount=1d;
    }

    public void calculate(){
        if(calculatorAmount!=null&&calculatorSecondRowCurr.getValue()!=null&&calculatorFirstRowCurr.getValue()!=null&&currencyAdapterDataSource.size()>0)
        {
            Log.v(TAG,"calculate "+calculatorAmount+" "+calculatorFirstRowCurr.getValue()+" to "+calculatorSecondRowCurr.getValue());
            Double firstRate = null;
            Double secondRate = null;
            try {
                firstRate = currencyAdapterDataSource.get(calculatorFirstRowCurr.getValue()+"-PLN").getRate();
                secondRate = currencyAdapterDataSource.get(calculatorSecondRowCurr.getValue()+"-PLN").getRate();
                Double result = firstRate/secondRate*calculatorAmount;
                calculatorResult.setValue(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
