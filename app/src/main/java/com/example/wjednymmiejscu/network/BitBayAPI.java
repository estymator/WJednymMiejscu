package com.example.wjednymmiejscu.network;

import com.example.wjednymmiejscu.model.Ticker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BitBayAPI {
    @GET("/{curr1}{curr2}/ticker.json")
    Call<Ticker> getTickerData(
            @Path("curr1") String curr1,
            @Path("curr2") String curr2
    );
}
