package com.example.wjednymmiejscu.ui.currency;

import androidx.lifecycle.ViewModel;

import com.example.wjednymmiejscu.model.Ticker;

public class CurrencyViewModel extends ViewModel {
    private final String TAG = "CurrencyViewModelTAG";

    private Ticker ticker;

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}
