package com.example.wjednymmiejscu.network;

import com.example.wjednymmiejscu.model.Ticker;
import com.example.wjednymmiejscu.model.TickerArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BitBayAPI {
    /**
     * get all markets stats in ticker
     */
    @GET("/trading/ticker")
    Call<TickerArray> getTickerData();
}
