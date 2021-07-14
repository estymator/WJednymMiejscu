package com.example.wjednymmiejscu.ui.currency;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wjednymmiejscu.model.LastTransactions;
import com.example.wjednymmiejscu.model.MarketStatsResponse;
import com.example.wjednymmiejscu.model.OrderBook;
import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.Transaction;
import com.example.wjednymmiejscu.network.MarketStatsRequests;
import com.example.wjednymmiejscu.network.OrderBookRequests;
import com.example.wjednymmiejscu.network.TransactionsRequests;

public class CurrencyViewModel extends ViewModel {
    private final String TAG = "CurrencyViewModelTAG";
    private OrderBookRequests orderBookRequests = new OrderBookRequests();
    private TransactionsRequests transactionsRequests = new TransactionsRequests();
    private MarketStatsRequests marketStatsRequests = new MarketStatsRequests();
    private MutableLiveData<MarketStatsResponse> marketStatsResult = new MutableLiveData<>();
    private MutableLiveData<LastTransactions> lastTransactionsResult = new MutableLiveData<>();
    private MutableLiveData<OrderBook> orderBookResult = new MutableLiveData<>();

    private Ticker ticker;

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
        Log.v(TAG, ticker.toString());

    }

    void loadMarketData(){
        orderBookRequests.loadOrderBook(this.ticker.getMarket().getCode(), orderBookResult);
        marketStatsRequests.loadMarketStats(this.ticker.getMarket().getCode(), marketStatsResult);
        transactionsRequests.loadLastTransaction(this.ticker.getMarket().getCode(), lastTransactionsResult);

    }
    public MutableLiveData<MarketStatsResponse> getMarketStatsResult() {
        return marketStatsResult;
    }

    public MutableLiveData<LastTransactions> getLastTransactionsResult() {
        return lastTransactionsResult;
    }

    public MutableLiveData<OrderBook> getOrderBookResult() {
        return orderBookResult;
    }
}
